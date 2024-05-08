package com.anees.pdma.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.anees.pdma.Activities.IncidentsDisasters_Report;
import com.anees.pdma.Activities.IncidentsLoses_Report;
import com.anees.pdma.Activities.Login;
import com.anees.pdma.Activities.Report_DateSelection;
import com.anees.pdma.DBase.Database;
import com.anees.pdma.R;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;
import com.anees.pdma.model.MySingleton;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.commons.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spencerstudios.com.fab_toast.FabToast;

public class IncidentDatabase_Adapter {

    private Context context;
    private Database database;
    private SQLiteDatabase db;

    private Geocoder geocoder;

    public IncidentDatabase_Adapter(Context context) {
        this.context = context;
    }

    private void openDB() {
        database = new Database(context);
        db = database.getWritableDatabase();
    }

    private void openReadableDB() {
        database = new Database(context);
        db = database.getReadableDatabase();
    }

    private boolean internetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * METHODS FOR INSERTION
     **/

    public void saveIncidentToServer(final GetterSetter incident) {
        if (internetConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.ADD_INCIDENT_SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("response");
                        if (Response.equals("OK")) {

                            addIncidentToLocal(incident, DatabaseConstants.SYNC_STATUS_OK);
                            sendPushNotification(incident);
                            FabToast.makeText(context, context.getString(R.string.add_server_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
                        } else {
                            addIncidentToLocal(incident, DatabaseConstants.SYNC_STATUS_FAILED);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    addIncidentToLocal(incident, DatabaseConstants.SYNC_STATUS_FAILED);
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("User_ID", Login.user_id);
                    params.put("Disaster_Type", incident.getDisaster_type());
                    params.put("Location", incident.getLocation());
                    params.put("Date", incident.getDate());
                    params.put("Time", incident.getTime());
                    params.put("Loses", incident.getLoses());
                    params.put("Injuries", incident.getInjuries());
                    params.put("Houses_Damaged", incident.getHouses_damaged());
                    params.put("Affected_Road", incident.getAffected_road());
                    params.put("Alternate_Road", incident.getAlternate_road());
                    params.put("More_Information", incident.getMore_information());
                    params.put("Report_Issued_By", incident.getReport_issued());
                    params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                    return params;
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        } else {
            addIncidentToLocal(incident, DatabaseConstants.SYNC_STATUS_FAILED);
        }
    }

    private void addIncidentToLocal(GetterSetter incident, int sync_status)       //method is called in AddIncident class
    {
        openDB();

        ContentValues incidentValues = new ContentValues();
        incidentValues.put("User_ID", Login.user_id);
        incidentValues.put("Disaster_Type", incident.getDisaster_type());
        incidentValues.put("Location", incident.getLocation());
        incidentValues.put("Date", incident.getDate());
        incidentValues.put("Time", incident.getTime());
        incidentValues.put("Loses", incident.getLoses());
        incidentValues.put("Injuries", incident.getInjuries());
        incidentValues.put("Houses_Damaged", incident.getHouses_damaged());
        incidentValues.put("Affected_Road", incident.getAffected_road());
        incidentValues.put("Alternate_Road", incident.getAlternate_road());
        incidentValues.put("More_Information", incident.getMore_information());
        incidentValues.put("Report_Issued_By", incident.getReport_issued());
        incidentValues.put("sync_status", sync_status);

        db.insert("incidents", null, incidentValues);
        FabToast.makeText(context, context.getString(R.string.add_local_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
        db.close();
    }

    private void sendPushNotification(final GetterSetter incident) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.SEND_NOTIFICATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", "Incident " + incident.getDisaster_type());
                params.put("message", incident.getLocation());
                params.put("click_action", "Incident");

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    //this method will be called from IncidentNetworkMonitor Class
    public void sendPushNotificationIfNetOff(final String disasterType, final String location) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.SEND_NOTIFICATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", "Incident " + disasterType);
                params.put("message", location);
                params.put("click_action", "Incident");

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    //method is called in IncidentNetworkMonitor class to change the sync status of incident
    public boolean updateIncidentSyncStatus(int id, int sync_status, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", sync_status);
        db.update("incidents", contentValues, "Incident_ID=" + id, null);
        db.close();
        return true;
    }

    //method is called in IncidentNetworkMonitor class to read all the incidents
    public Cursor readIncidentsFromLocal(SQLiteDatabase db) {
        String[] columns = {"Incident_ID, User_ID, Disaster_Type, Location, Date, Time, Loses, Injuries, Houses_Damaged, Affected_Road, Alternate_Road, More_Information, Report_Issued_By, sync_status"};
        return (db.query("incidents", columns, null, null, null, null, "Incident_ID DESC"));
    }

    /**
     * METHODS FOR RETRIEVING DATA FROM SERVER
     **/

    private void incidentTableBuild() {
        openDB();
        db.execSQL("CREATE TABLE IF NOT EXISTS incidents(Incident_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, Disaster_Type TEXT, Location TEXT, Date TEXT, Time TEXT, Loses TEXT, Injuries TEXT, Houses_Damaged TEXT, Affected_Road TEXT, Alternate_Road TEXT, More_Information TEXT, Report_Issued_By TEXT, sync_status integer)");
    }

    private void deleteIncidentTable() {
        openDB();
        db.delete("incidents", "sync_status = ?", new String[]{Integer.toString(DatabaseConstants.SYNC_STATUS_OK)}); //deletes all those incidents data which are successfully stored on server
        db.close();
    }

    private void getIncidentsFromServer() {
        new AsyncTask<Void, Void, Void>() {
            String FinalJSonResult;

            @Override
            protected Void doInBackground(Void... voids) {
                HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.GET_INCIDENTS_SERVER_URL);
                try {
                    httpServiceClass.ExecutePostRequest();

                    if (httpServiceClass.getResponseCode() == 200) {
                        FinalJSonResult = httpServiceClass.getResponse();

                        if (FinalJSonResult != null) {
                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(FinalJSonResult);
                                JSONObject jsonObject;

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);

                                    String incident_id = jsonObject.getString("Incident_ID");
                                    String user_id = jsonObject.getString("User_ID");
                                    String disaster = jsonObject.getString("Disaster_Type");
                                    String location = jsonObject.getString("Location");
                                    String date = jsonObject.getString("Date");
                                    String time = jsonObject.getString("Time");
                                    String loses = jsonObject.getString("Loses");
                                    String injuries = jsonObject.getString("Injuries");
                                    String houses_damaged = jsonObject.getString("Houses_Damaged");
                                    String affected_road = jsonObject.getString("Affected_Road");
                                    String alternate_road = jsonObject.getString("Alternate_Road");
                                    String more_information = jsonObject.getString("More_Information");
                                    String report_issued= jsonObject.getString("Report_Issued_By");
                                    String sync_status = jsonObject.getString("sync_status");

                                    openDB();
                                    ContentValues incidentValues = new ContentValues();
                                    incidentValues.put("Incident_ID", incident_id);
                                    incidentValues.put("User_ID", user_id);
                                    incidentValues.put("Disaster_Type", disaster);
                                    incidentValues.put("Location", location);
                                    incidentValues.put("Date", date);
                                    incidentValues.put("Time", time);
                                    incidentValues.put("Loses", loses);
                                    incidentValues.put("Injuries", injuries);
                                    incidentValues.put("Houses_Damaged", houses_damaged);
                                    incidentValues.put("Affected_Road", affected_road);
                                    incidentValues.put("Alternate_Road", alternate_road);
                                    incidentValues.put("More_Information", more_information);
                                    incidentValues.put("Report_Issued_By", report_issued);
                                    incidentValues.put("sync_status", sync_status);
                                    db.insert("incidents", null, incidentValues);
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                db.close();
            }
        }.execute();
    }

    public void updateIncidentsList()       //method is called in MainScreen, IncidentsScreen, ManageableIncidentsScreen & ManageableIncidentsScreenUser class
    {
        incidentTableBuild();
        deleteIncidentTable();
        getIncidentsFromServer();
    }

    /**
     * METHODS FOR RETRIEVING DATA FROM SQLite
     **/

    public List<GetterSetter> getAllIncidentsFromLocal()         //method is called in IncidentsScreen & ManageableIncidentsScreen class
    {
        openReadableDB();
        String[] columns = {"Incident_ID, User_ID, Disaster_Type, Location, Date, Time, Loses, Injuries, Houses_Damaged, Affected_Road, Alternate_Road, More_Information, Report_Issued_By, sync_status"};
        ArrayList<GetterSetter> incidentList = new ArrayList<>();
        Cursor cursor = db.query("incidents", columns, null, null, null, null, "Date DESC, Incident_ID DESC");
        while (cursor.moveToNext()) {
            GetterSetter incident = new GetterSetter();
            incident.setId(cursor.getInt(cursor.getColumnIndex("Incident_ID")));
            incident.setDisaster_type(cursor.getString(cursor.getColumnIndex("Disaster_Type")));
            incident.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
            incident.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            incident.setTime(cursor.getString(cursor.getColumnIndex("Time")));
            incident.setLoses(cursor.getString(cursor.getColumnIndex("Loses")));
            incident.setInjuries(cursor.getString(cursor.getColumnIndex("Injuries")));
            incident.setHouses_damaged(cursor.getString(cursor.getColumnIndex("Houses_Damaged")));
            incident.setAffected_road(cursor.getString(cursor.getColumnIndex("Affected_Road")));
            incident.setAlternate_road(cursor.getString(cursor.getColumnIndex("Alternate_Road")));
            incident.setMore_information(cursor.getString(cursor.getColumnIndex("More_Information")));
            incident.setReport_issued(cursor.getString(cursor.getColumnIndex("Report_Issued_By")));
            incident.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
            incidentList.add(incident);
        }
        cursor.close();
        db.close();
        return incidentList;
    }

    public List<GetterSetter> getUserIncidentsFromLocal()         //method is called in ManageableIncidentsScreenUser class
    {
        openReadableDB();

        String[] columns = new String[]{"Incident_ID, Disaster_Type, Location, Date, Time, Loses, Injuries, Houses_Damaged, Affected_Road, Alternate_Road, More_Information, Report_Issued_By, sync_status"};
        String[] args = new String[]{Login.user_id};
        ArrayList<GetterSetter> incidentList = new ArrayList<>();
        Cursor cursor = db.query("incidents", columns, "User_ID=?", args, null, null, "Date DESC, Incident_ID DESC");
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                GetterSetter incident = new GetterSetter();
                incident.setId(cursor.getInt(cursor.getColumnIndex("Incident_ID")));
                incident.setDisaster_type(cursor.getString(cursor.getColumnIndex("Disaster_Type")));
                incident.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
                incident.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                incident.setTime(cursor.getString(cursor.getColumnIndex("Time")));
                incident.setLoses(cursor.getString(cursor.getColumnIndex("Loses")));
                incident.setInjuries(cursor.getString(cursor.getColumnIndex("Injuries")));
                incident.setHouses_damaged(cursor.getString(cursor.getColumnIndex("Houses_Damaged")));
                incident.setAffected_road(cursor.getString(cursor.getColumnIndex("Affected_Road")));
                incident.setAlternate_road(cursor.getString(cursor.getColumnIndex("Alternate_Road")));
                incident.setMore_information(cursor.getString(cursor.getColumnIndex("More_Information")));
                incident.setReport_issued(cursor.getString(cursor.getColumnIndex("Report_Issued_By")));
                incident.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
                incidentList.add(incident);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return incidentList;
    }

    /**
     * METHODS FOR UPDATING INCIDENT
     **/

    public void updateIncidentOnServer(final GetterSetter incident)                 //method is called in UpdateIncident class
    {
        if (internetConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.UPDATE_INCIDENT_SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("response");
                        if (Response.equals("OK")) {
                            updateIncident(incident, DatabaseConstants.SYNC_STATUS_OK);
                            sendPushNotification(incident);
                            FabToast.makeText(context, context.getString(R.string.update_server_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
                        } else {
                            updateIncident(incident, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    updateIncident(incident, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Incident_ID", Integer.toString(incident.getId()));
                    params.put("Disaster_Type", incident.getDisaster_type());
                    params.put("Location", incident.getLocation());
                    params.put("Date", incident.getDate());
                    params.put("Time", incident.getTime());
                    params.put("Loses", incident.getLoses());
                    params.put("Injuries", incident.getInjuries());
                    params.put("Houses_Damaged", incident.getHouses_damaged());
                    params.put("Affected_Road", incident.getAffected_road());
                    params.put("Alternate_Road", incident.getAlternate_road());
                    params.put("More_Information", incident.getMore_information());
                    params.put("Report_Issued_By", incident.getReport_issued());
                    params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                    return params;
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        } else {
            updateIncident(incident, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
        }
    }

    private void updateIncident(GetterSetter incident, int sync_status) {
        openDB();

        ContentValues myContent = new ContentValues();
        myContent.put("Disaster_Type", incident.getDisaster_type());
        myContent.put("Location", incident.getLocation());
        myContent.put("Date", incident.getDate());
        myContent.put("Time", incident.getTime());
        myContent.put("Loses", incident.getLoses());
        myContent.put("Injuries", incident.getInjuries());
        myContent.put("Houses_Damaged", incident.getHouses_damaged());
        myContent.put("Affected_Road", incident.getAffected_road());
        myContent.put("Alternate_Road", incident.getAlternate_road());
        myContent.put("More_Information", incident.getMore_information());
        myContent.put("Report_Issued_By", incident.getReport_issued());
        myContent.put("sync_status", sync_status);

        String arg_id[] = new String[]{Integer.toString(incident.getId())};
        db.update("incidents", myContent, "Incident_ID = ?", arg_id);
        FabToast.makeText(context, context.getString(R.string.update_local_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
        db.close();
    }

    /*METHODS FOR EVENT MAP*/

    //Method to show all the incidents on the map
    /*public LatLng getIncidentsForMap() {
        geocoder = new Geocoder(context);
        List<Address> incident_addresses;
        LatLng latLngIncidentAddress = null;

        openReadableDB();

        String[] incident_columns = new String[]{"Disaster_Type, Location, Date, Time"};
        Cursor incidents_cursor = db.query("incidents", incident_columns, null, null, null, null, null);
        incidents_cursor.moveToFirst();
        if (incidents_cursor.getCount() > 0) {
            do {
                EventMapScreen.incident = incidents_cursor.getString(incidents_cursor.getColumnIndex("Disaster_Type"));
                EventMapScreen.incident_location = incidents_cursor.getString(incidents_cursor.getColumnIndex("Location"));
                EventMapScreen.incident_date = incidents_cursor.getString(incidents_cursor.getColumnIndex("Date"));
                EventMapScreen.incident_time = incidents_cursor.getString(incidents_cursor.getColumnIndex("Time"));

                try {
                    incident_addresses = geocoder.getFromLocationName(EventMapScreen.incident_location, 5);
                    if (incident_addresses == null) {
                        return null;
                    }

                    Address location = incident_addresses.get(0);
                    location.getLatitude();
                    location.getLongitude();

                    latLngIncidentAddress = new LatLng(location.getLatitude(), location.getLongitude());
                    EventMapScreen.mMap.addMarker(new MarkerOptions().position(latLngIncidentAddress).flat(true).zIndex(1.0f)
                            .title("Incident: " + EventMapScreen.incident)
                            .snippet(" Date: " + EventMapScreen.incident_date + " Time: " + EventMapScreen.incident_time))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.incident_icon));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            while (incidents_cursor.moveToNext());
        }
        incidents_cursor.close();
        db.close();
        return latLngIncidentAddress;
    }*/

    //Method to show all the affected roads on the map
    /*public LatLng affectedRoadsForMap() {
        geocoder = new Geocoder(context);
        List<Address> affected_road_addresses;
        LatLng latLngAffectedRoadAddress = null;

        openReadableDB();

        String[] columns = new String[]{"Date, Time, Affected_Road, Alternate_Road"};
        Cursor cursor = db.query("incidents", columns, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                EventMapScreen.incident_date = cursor.getString(cursor.getColumnIndex("Date"));
                EventMapScreen.incident_time = cursor.getString(cursor.getColumnIndex("Time"));
                affected_road = cursor.getString(cursor.getColumnIndex("Affected_Road"));
                alternate_road = cursor.getString(cursor.getColumnIndex("Alternate_Road"));

                try {
                    affected_road_addresses = geocoder.getFromLocationName(affected_road, 5);
                    if (affected_road_addresses == null) {
                        return null;
                    }

                    Address affected_road_location = affected_road_addresses.get(0);
                    affected_road_location.getLatitude();
                    affected_road_location.getLongitude();

                    latLngAffectedRoadAddress = new LatLng(affected_road_location.getLatitude(), affected_road_location.getLongitude());
                    EventMapScreen.mMap.addMarker(new MarkerOptions().position(latLngAffectedRoadAddress).flat(true).zIndex(0.6f)
                            .title("Road Affected at " + EventMapScreen.incident_time + " " + EventMapScreen.incident_date)
                            .snippet("Alternate Route: " + alternate_road))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.affected_road_icon));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return latLngAffectedRoadAddress;
    }*/

    /*Methods for Incidents Report*/

    //method is called in IncidentsLosesReportScreen to get the values for loses, injuries & house damaged for bar chart
    public void getLosesForGraph() {
        openReadableDB();

        int loses, injuries, houses_damaged;
        String query = "SELECT Date, Loses, Injuries, Houses_Damaged from incidents WHERE Date BETWEEN '" + Report_DateSelection.startDate + "'  AND '" + Report_DateSelection.endDate + "' ";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            loses = cursor.getInt(cursor.getColumnIndex("Loses"));
            IncidentsLoses_Report.loses_count = IncidentsLoses_Report.loses_count + loses;
            injuries = cursor.getInt(cursor.getColumnIndex("Injuries"));
            IncidentsLoses_Report.injuries_count = IncidentsLoses_Report.injuries_count + injuries;
            houses_damaged = cursor.getInt(cursor.getColumnIndex("Houses_Damaged"));
            IncidentsLoses_Report.houses_damaged_count = IncidentsLoses_Report.houses_damaged_count + houses_damaged;
        }
        cursor.close();
        db.close();
    }

    public void getDisastersForGraph()      //method is called in IncidentsDisastersReportScreen to get the number of disasters for pie chart
    {
        openReadableDB();

        String disaster_type;
        String query = "SELECT Date, Disaster_Type from incidents WHERE Date BETWEEN '" + Report_DateSelection.startDate + "'  AND '" + Report_DateSelection.endDate + "' ";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            disaster_type = cursor.getString(cursor.getColumnIndex("Disaster_Type"));

            switch (disaster_type) {
                case "Earthquake":
                    IncidentsDisasters_Report.earthquake_count = IncidentsDisasters_Report.earthquake_count + 1f;
                    break;
                case "Flood":
                    IncidentsDisasters_Report.flood_count = IncidentsDisasters_Report.flood_count + 1f;
                    break;
                case "Landslide":
                    IncidentsDisasters_Report.landslide_count = IncidentsDisasters_Report.landslide_count + 1f;
                    break;
                case "Heavy Rain":
                    IncidentsDisasters_Report.heavy_rain_count = IncidentsDisasters_Report.heavy_rain_count + 1f;
                    break;
                default:
                    IncidentsDisasters_Report.other_count = IncidentsDisasters_Report.other_count + 1f;
                    break;
            }
        }
        cursor.close();
        db.close();
    }

    /*
     * method for uploading data(unsync) on server*/

    public void uploadUnsyncDataOnServer()
    {
        if (internetConnected()) {
            final Database database = new Database(context);
            final SQLiteDatabase db = database.getWritableDatabase();
            final IncidentDatabase_Adapter incidentDatabaseAdapter = new IncidentDatabase_Adapter(context);

            Cursor cursor = incidentDatabaseAdapter.readIncidentsFromLocal(db);
            while (cursor.moveToNext()) {
                int sync_status = cursor.getInt(cursor.getColumnIndex("sync_status"));
                if (sync_status == DatabaseConstants.SYNC_STATUS_FAILED) {
                    final int id = cursor.getInt(cursor.getColumnIndex("Incident_ID"));
                    final int user_id = cursor.getInt(cursor.getColumnIndex("User_ID"));
                    final String disaster = cursor.getString(cursor.getColumnIndex("Disaster_Type"));
                    final String location = cursor.getString(cursor.getColumnIndex("Location"));
                    final String date = cursor.getString(cursor.getColumnIndex("Date"));
                    final String time = cursor.getString(cursor.getColumnIndex("Time"));
                    final String loses = cursor.getString(cursor.getColumnIndex("Loses"));
                    final String injuries = cursor.getString(cursor.getColumnIndex("Injuries"));
                    final String houses_damaged = cursor.getString(cursor.getColumnIndex("Houses_Damaged"));
                    final String affected_road = cursor.getString(cursor.getColumnIndex("Affected_Road"));
                    final String alternate_road = cursor.getString(cursor.getColumnIndex("Alternate_Road"));
                    final String more_information = cursor.getString(cursor.getColumnIndex("More_Information"));
                    final String report_issued = cursor.getString(cursor.getColumnIndex("Report_Issued_By"));

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.ADD_INCIDENT_SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    Database database = new Database(context);
                                    SQLiteDatabase db = database.getWritableDatabase();
                                    incidentDatabaseAdapter.updateIncidentSyncStatus(id, DatabaseConstants.SYNC_STATUS_OK, db);
                                    incidentDatabaseAdapter.sendPushNotificationIfNetOff(disaster, location);
                                    context.sendBroadcast(new Intent(DatabaseConstants.INCIDENT_SAVED_BROADCAST));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("User_ID", Integer.toString(user_id));
                            params.put("Disaster_Type", disaster);
                            params.put("Location", location);
                            params.put("Date", date);
                            params.put("Time", time);
                            params.put("Loses", loses);
                            params.put("Injuries", injuries);
                            params.put("Houses_Damaged", houses_damaged);
                            params.put("Affected_Road", affected_road);
                            params.put("Alternate_Road", alternate_road);
                            params.put("More_Information", more_information);
                            params.put("Report_Issued_By", report_issued);
                            params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);
                }
                if (sync_status == DatabaseConstants.UPDATE_SYNC_STATUS_FAILED) {
                    final int id = cursor.getInt(cursor.getColumnIndex("Incident_ID"));
                    final String disaster = cursor.getString(cursor.getColumnIndex("Disaster_Type"));
                    final String location = cursor.getString(cursor.getColumnIndex("Location"));
                    final String date = cursor.getString(cursor.getColumnIndex("Date"));
                    final String time = cursor.getString(cursor.getColumnIndex("Time"));
                    final String loses = cursor.getString(cursor.getColumnIndex("Loses"));
                    final String injuries = cursor.getString(cursor.getColumnIndex("Injuries"));
                    final String houses_damaged = cursor.getString(cursor.getColumnIndex("Houses_Damaged"));
                    final String affected_road = cursor.getString(cursor.getColumnIndex("Affected_Road"));
                    final String alternate_road = cursor.getString(cursor.getColumnIndex("Alternate_Road"));
                    final String more_information = cursor.getString(cursor.getColumnIndex("More_Information"));
                    final String report_issued = cursor.getString(cursor.getColumnIndex("Report_Issued_By"));

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.UPDATE_INCIDENT_SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    Database database = new Database(context);
                                    SQLiteDatabase db = database.getWritableDatabase();
                                    incidentDatabaseAdapter.updateIncidentSyncStatus(id, DatabaseConstants.SYNC_STATUS_OK, db);
                                    incidentDatabaseAdapter.sendPushNotificationIfNetOff(disaster, location);
                                    //context.sendBroadcast(new Intent(databaseConstants.USER_SAVED_BROADCAST));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("Incident_ID", Integer.toString(id));
                            params.put("Disaster_Type", disaster);
                            params.put("Location", location);
                            params.put("Date", date);
                            params.put("Time", time);
                            params.put("Loses", loses);
                            params.put("Injuries", injuries);
                            params.put("Houses_Damaged", houses_damaged);
                            params.put("Affected_Road", affected_road);
                            params.put("Alternate_Road", alternate_road);
                            params.put("More_Information", more_information);
                            params.put("Report_Issued_By", report_issued);
                            params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);
                }
            }
            database.close();
        }
    }
}
