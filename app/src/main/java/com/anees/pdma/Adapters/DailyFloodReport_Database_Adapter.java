package com.anees.pdma.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.anees.pdma.Activities.Login;
import com.anees.pdma.DBase.Database;
import com.anees.pdma.FragmentClasses.DatePickerFragment;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;
import com.anees.pdma.model.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyFloodReport_Database_Adapter {

    String floodDateInDB;
    private Context context;
    private Database database;
    private SQLiteDatabase db;

    public DailyFloodReport_Database_Adapter(Context context) {
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

    public void saveFloodReportToServer(final GetterSetter floodReport) {
        if (internetConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.ADD_FLOOD_REPORT_SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("response");
                        if (Response.equals("OK")) {

                            addFloodReport(floodReport, DatabaseConstants.SYNC_STATUS_OK);
                            if (floodReport.getFlow_status().equals("Extremely High")) {
                                sendPushNotification();
                            }
                            Toast.makeText(context, "Flood Report Added Successfully To Server", Toast.LENGTH_SHORT).show();
                        } else {
                            addFloodReport(floodReport, DatabaseConstants.SYNC_STATUS_FAILED);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    addFloodReport(floodReport, DatabaseConstants.SYNC_STATUS_FAILED);
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("User_ID", Login.user_id);
                    params.put("River_Name", floodReport.getRiver_name());
                    params.put("Location", floodReport.getLocation());
                    params.put("Flow_Status", floodReport.getFlow_status());
                    params.put("Date", floodReport.getDate());
                    params.put("Time", floodReport.getTime());
                    params.put("Remarks", floodReport.getRemarks());
                    params.put("Discharge_Cusecs", floodReport.getDischarge_cusecs());
                    params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                    return params;
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        } else {
            addFloodReport(floodReport, DatabaseConstants.SYNC_STATUS_FAILED);
        }
    }

    private void addFloodReport(GetterSetter floodReport, int sync_status)            //method is called from server method
    {
        openDB();

        ContentValues floodReportValues = new ContentValues();
        floodReportValues.put("User_ID", Login.user_id);
        floodReportValues.put("River_Name", floodReport.getRiver_name());
        floodReportValues.put("Location", floodReport.getLocation());
        floodReportValues.put("Flow_Status", floodReport.getFlow_status());
        floodReportValues.put("Date", floodReport.getDate());
        floodReportValues.put("Time", floodReport.getTime());
        floodReportValues.put("Remarks", floodReport.getRemarks());
        floodReportValues.put("Discharge_Cusecs", floodReport.getDischarge_cusecs());
        floodReportValues.put("sync_status", sync_status);

        db.insert("flood_reports", null, floodReportValues);
        Toast.makeText(context, "Flood Report Added Successfully To Local Database", Toast.LENGTH_SHORT).show();

        db.close();
    }

    private void sendPushNotification() {

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
                params.put("title", "Alert");
                params.put("message", "Flood Status Extremely High");
                params.put("click_action", "FloodReport");

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    //this method will be called from FloodReportNetworkMonitor Class
    public void sendPushNotificationIfNetOff() {

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
                params.put("title", "Alert");
                params.put("message", "Flood Status Extremely High");
                params.put("click_action", "FloodReport");

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public boolean updateFloodReportSyncStatus(int id, int sync_status, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", sync_status);
        db.update("flood_reports", contentValues, "Flood_Report_ID=" + id, null);
        db.close();
        return true;
    }

    public Cursor readFloodReportsFromLocal(SQLiteDatabase db) {
        String[] columns = {"Flood_Report_ID, User_ID, River_Name, Location, Flow_Status, Date, Time, Remarks, Discharge_Cusecs, sync_status"};
        return (db.query("flood_reports", columns, null, null, null, null, "Flood_Report_ID DESC"));
    }

    /**
     * METHODS FOR RETRIEVING DATA FROM SERVER
     **/

    private void floodReportTableBuild() {
        openDB();
        db.execSQL("CREATE TABLE IF NOT EXISTS flood_reports(Flood_Report_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, River_Name TEXT, Location TEXT, Flow_Status TEXT, Date TEXT, Time TEXT, Remarks TEXT, Discharge_Cusecs TEXT, sync_status integer)");
    }

    private void deleteFloodReportTable() {
        openDB();
        db.delete("flood_reports", "sync_status = ?", new String[]{Integer.toString(DatabaseConstants.SYNC_STATUS_OK)}); //deletes all those flood reports data which are successfully stored on server
        db.close();
    }

    private void getFloodReportsFromServer() {
        new AsyncTask<Void, Void, Void>() {
            String FinalJSonResult;

            @Override
            protected Void doInBackground(Void... voids) {
                HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.GET_FLOOD_REPORTS_SERVER_URL);
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
                                    String floodReportId = jsonObject.getString("Flood_Report_ID");
                                    String user_id = jsonObject.getString("User_ID");
                                    String riverName = jsonObject.getString("River_Name");
                                    String location = jsonObject.getString("Location");
                                    String flowStatus = jsonObject.getString("Flow_Status");
                                    String date = jsonObject.getString("Date");
                                    String time = jsonObject.getString("Time");
                                    String remarks = jsonObject.getString("Remarks");
                                    String dischargeCusecs = jsonObject.getString("Discharge_Cusecs");
                                    String sync_status = jsonObject.getString("sync_status");

                                    openDB();
                                    ContentValues floodReportValues = new ContentValues();
                                    floodReportValues.put("Flood_Report_ID", floodReportId);
                                    floodReportValues.put("User_ID", user_id);
                                    floodReportValues.put("River_Name", riverName);
                                    floodReportValues.put("Location", location);
                                    floodReportValues.put("Flow_Status", flowStatus);
                                    floodReportValues.put("Date", date);
                                    floodReportValues.put("Time", time);
                                    floodReportValues.put("Remarks", remarks);
                                    floodReportValues.put("Discharge_Cusecs", dischargeCusecs);
                                    floodReportValues.put("sync_status", sync_status);
                                    db.insert("flood_reports", null, floodReportValues);
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

    public void updateFloodReportsList()       //method is called in AppLaunchingScreen, MainScreen, etc
    {
        floodReportTableBuild();
        deleteFloodReportTable();
        getFloodReportsFromServer();
    }

    /**
     * METHODS FOR RETRIEVING DATA FROM SQLite
     **/

    public List<GetterSetter> getAllFloodReportsFromLocal() {
        openReadableDB();
        String[] columns = {"Flood_Report_ID, User_ID, River_Name, Location, Flow_Status, Date, Time, Remarks, Discharge_Cusecs, sync_status"};
        ArrayList<GetterSetter> floodReportArrayList = new ArrayList<>();
        Cursor cursor = db.query("flood_reports", columns, null, null, null, null, "Date DESC, Flood_Report_ID DESC");
        while (cursor.moveToNext()) {
            GetterSetter floodReport = new GetterSetter();
            floodReport.setId(cursor.getInt(cursor.getColumnIndex("Flood_Report_ID")));
            floodReport.setRiver_name(cursor.getString(cursor.getColumnIndex("River_Name")));
            floodReport.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
            floodReport.setFlow_status(cursor.getString(cursor.getColumnIndex("Flow_Status")));
            floodReport.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            floodReport.setTime(cursor.getString(cursor.getColumnIndex("Time")));
            floodReport.setRemarks(cursor.getString(cursor.getColumnIndex("Remarks")));
            floodReport.setDischarge_cusecs(cursor.getString(cursor.getColumnIndex("Discharge_Cusecs")));
            floodReport.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
            floodReportArrayList.add(floodReport);
        }
        cursor.close();
        db.close();
        return floodReportArrayList;
    }

    public List<GetterSetter> getUserFloodReportsFromLocal()         //method is called in ManageableFloodReportsScreenUser class
    {
        openReadableDB();

        String[] columns = new String[]{"Flood_Report_ID, River_Name, Location, Flow_Status, Date, Time, Remarks, Discharge_Cusecs, sync_status"};
        String[] args = new String[]{Login.user_id};
        ArrayList<GetterSetter> floodReportArrayList = new ArrayList<>();
        Cursor cursor = db.query("flood_reports", columns, "User_ID=?", args, null, null, "Date DESC, Flood_Report_ID DESC");
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                GetterSetter floodReport = new GetterSetter();
                floodReport.setId(cursor.getInt(cursor.getColumnIndex("Flood_Report_ID")));
                floodReport.setRiver_name(cursor.getString(cursor.getColumnIndex("River_Name")));
                floodReport.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
                floodReport.setFlow_status(cursor.getString(cursor.getColumnIndex("Flow_Status")));
                floodReport.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                floodReport.setTime(cursor.getString(cursor.getColumnIndex("Time")));
                floodReport.setRemarks(cursor.getString(cursor.getColumnIndex("Remarks")));
                floodReport.setDischarge_cusecs(cursor.getString(cursor.getColumnIndex("Discharge_Cusecs")));
                floodReport.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
                floodReportArrayList.add(floodReport);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return floodReportArrayList;
    }

    /**
     * METHODS FOR UPDATION
     **/

    public void updateFloodReportOnServer(final GetterSetter floodReport) {
        if (internetConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.UPDATE_FLOOD_REPORT_SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("response");
                        if (Response.equals("OK")) {
                            updateFloodReport(floodReport, DatabaseConstants.SYNC_STATUS_OK);
                            if (floodReport.getFlow_status().equals("Extremely High")) {
                                sendPushNotification();
                            }
                            Toast.makeText(context, "Flood Report Updated Successfully On Server", Toast.LENGTH_SHORT).show();
                        } else {
                            updateFloodReport(floodReport, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    updateFloodReport(floodReport, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Flood_Report_ID", Integer.toString(floodReport.getId()));
                    params.put("River_Name", floodReport.getRiver_name());
                    params.put("Location", floodReport.getLocation());
                    params.put("Flow_Status", floodReport.getFlow_status());
                    params.put("Date", floodReport.getDate());
                    params.put("Time", floodReport.getTime());
                    params.put("Remarks", floodReport.getRemarks());
                    params.put("Discharge_Cusecs", floodReport.getDischarge_cusecs());
                    params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                    return params;
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        } else {
            updateFloodReport(floodReport, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
        }
    }

    private void updateFloodReport(GetterSetter floodReport, int sync_status) {
        openDB();

        ContentValues myContent = new ContentValues();
        myContent.put("River_Name", floodReport.getRiver_name());
        myContent.put("Location", floodReport.getLocation());
        myContent.put("Date", floodReport.getDate());
        myContent.put("Time", floodReport.getTime());
        myContent.put("Flow_Status", floodReport.getFlow_status());
        myContent.put("Discharge_Cusecs", floodReport.getDischarge_cusecs());
        myContent.put("Remarks", floodReport.getRemarks());
        myContent.put("sync_status", sync_status);

        String arg_id[] = new String[]{Integer.toString(floodReport.getId())};
        db.update("flood_reports", myContent, "Flood_Report_ID = ?", arg_id);
        Toast.makeText(context, "Flood Report Updated Successfully on Local Database", Toast.LENGTH_SHORT).show();
        db.close();
    }

    public String validateFloodReportDate()        //method is called in FloodReportsScreen class
    {
        openReadableDB();

        String args[] = new String[]{DatePickerFragment.floodReportDate};
        Cursor cursor = db.query("flood_reports", null, "Date=?", args, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                floodDateInDB = cursor.getString(cursor.getColumnIndex("Date"));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return floodDateInDB;
    }

    /*
     * method for uploading data(unsync) on server*/

    public void uploadUnsyncDataOnServer()
    {
        if (internetConnected()) {
            final Database database = new Database(context);
            final SQLiteDatabase db = database.getWritableDatabase();
            final DailyFloodReport_Database_Adapter floodReportDatabaseAdapter = new DailyFloodReport_Database_Adapter(context);

            Cursor cursor = floodReportDatabaseAdapter.readFloodReportsFromLocal(db);
            while (cursor.moveToNext()) {
                int sync_status = cursor.getInt(cursor.getColumnIndex("sync_status"));
                if (sync_status == DatabaseConstants.SYNC_STATUS_FAILED) {
                    final int id = cursor.getInt(cursor.getColumnIndex("Flood_Report_ID"));
                    final int user_id = cursor.getInt(cursor.getColumnIndex("User_ID"));
                    final String river_name = cursor.getString(cursor.getColumnIndex("River_Name"));
                    final String location = cursor.getString(cursor.getColumnIndex("Location"));
                    final String date = cursor.getString(cursor.getColumnIndex("Date"));
                    final String time = cursor.getString(cursor.getColumnIndex("Time"));
                    final String flow_status = cursor.getString(cursor.getColumnIndex("Flow_Status"));
                    final String discharge_cusecs = cursor.getString(cursor.getColumnIndex("Discharge_Cusecs"));
                    final String remarks = cursor.getString(cursor.getColumnIndex("Remarks"));

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.ADD_FLOOD_REPORT_SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    Database database = new Database(context);
                                    SQLiteDatabase db = database.getWritableDatabase();
                                    floodReportDatabaseAdapter.updateFloodReportSyncStatus(id, DatabaseConstants.SYNC_STATUS_OK, db);
                                    floodReportDatabaseAdapter.sendPushNotificationIfNetOff();
                                    context.sendBroadcast(new Intent(DatabaseConstants.FLOOD_REPORT_SAVED_BROADCAST));
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
                            params.put("River_Name", river_name);
                            params.put("Location", location);
                            params.put("Flow_Status", flow_status);
                            params.put("Date", date);
                            params.put("Time", time);
                            params.put("Remarks", remarks);
                            params.put("Discharge_Cusecs", discharge_cusecs);
                            params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);
                }
                if (sync_status == DatabaseConstants.UPDATE_SYNC_STATUS_FAILED) {
                    final int id = cursor.getInt(cursor.getColumnIndex("Flood_Report_ID"));
                    final String river_name = cursor.getString(cursor.getColumnIndex("River_Name"));
                    final String location = cursor.getString(cursor.getColumnIndex("Location"));
                    final String date = cursor.getString(cursor.getColumnIndex("Date"));
                    final String time = cursor.getString(cursor.getColumnIndex("Time"));
                    final String flow_status = cursor.getString(cursor.getColumnIndex("Flow_Status"));
                    final String discharge_cusecs = cursor.getString(cursor.getColumnIndex("Discharge_Cusecs"));
                    final String remarks = cursor.getString(cursor.getColumnIndex("Remarks"));

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.UPDATE_FLOOD_REPORT_SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    Database database = new Database(context);
                                    SQLiteDatabase db = database.getWritableDatabase();
                                    floodReportDatabaseAdapter.updateFloodReportSyncStatus(id, DatabaseConstants.SYNC_STATUS_OK, db);
                                    floodReportDatabaseAdapter.sendPushNotificationIfNetOff();
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
                            params.put("Flood_Report_ID", Integer.toString(id));
                            params.put("River_Name", river_name);
                            params.put("Location", location);
                            params.put("Flow_Status", flow_status);
                            params.put("Date", date);
                            params.put("Time", time);
                            params.put("Remarks", remarks);
                            params.put("Discharge_Cusecs", discharge_cusecs);
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
