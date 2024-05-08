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
import com.anees.pdma.Activities.District_Selection;
import com.anees.pdma.Activities.Login;
import com.anees.pdma.DBase.Database;
import com.anees.pdma.R;
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

import spencerstudios.com.fab_toast.FabToast;

public class ReliefActivitiesDatabase_Adapter {

    private Context context;
    private Database database;
    private SQLiteDatabase db;

    public ReliefActivitiesDatabase_Adapter(Context context) {
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

    public void saveReliefActivityToServer(final GetterSetter reliefActivity) {
        if (internetConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.ADD_RELIEF_ACTIVITY_SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("response");
                        if (Response.equals("OK")) {
                            addReliefActivity(reliefActivity, DatabaseConstants.SYNC_STATUS_OK);
                            FabToast.makeText(context, context.getString(R.string.add_server_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
                        } else {
                            addReliefActivity(reliefActivity, DatabaseConstants.SYNC_STATUS_FAILED);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    addReliefActivity(reliefActivity, DatabaseConstants.SYNC_STATUS_FAILED);
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("User_ID", Login.user_id);
                    params.put("Disaster_Type", reliefActivity.getDisaster());
                    params.put("Location", reliefActivity.getLocation());
                    params.put("Date", reliefActivity.getDate());
                    params.put("Food_Packages", Integer.toString(reliefActivity.getFood_packages()));
                    params.put("Wheat_Flours", Integer.toString(reliefActivity.getWheat_flours()));
                    params.put("Pulses", Integer.toString(reliefActivity.getPulses()));
                    params.put("Tents", Integer.toString(reliefActivity.getTents()));
                    params.put("Blankets", Integer.toString(reliefActivity.getBlankets()));
                    params.put("Life_Jackets", Integer.toString(reliefActivity.getLife_jackets()));
                    params.put("Aqua_Tablets", Integer.toString(reliefActivity.getAqua_tablets()));
                    params.put("Gas_Cylinders", Integer.toString(reliefActivity.getGas_cylinders()));
                    params.put("Mats", Integer.toString(reliefActivity.getMats()));
                    params.put("Mosquito_Nets", Integer.toString(reliefActivity.getMosquito_nets()));
                    params.put("Water_Coolers", Integer.toString(reliefActivity.getWater_coolers()));
                    params.put("Buckets", Integer.toString(reliefActivity.getBuckets()));
                    params.put("Pillows", Integer.toString(reliefActivity.getPillows()));
                    params.put("Quilts", Integer.toString(reliefActivity.getQuilts()));
                    params.put("Kitchen_Sets", Integer.toString(reliefActivity.getKitchen_sets()));
                    params.put("Other_Items", Integer.toString(reliefActivity.getOther_items()));
                    params.put("Camps", Integer.toString(reliefActivity.getCamps()));
                    params.put("Coverage", reliefActivity.getCoverage());
                    params.put("Report_Issued_By", reliefActivity.getReport_issued());
                    params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                    return params;
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        } else {
            addReliefActivity(reliefActivity, DatabaseConstants.SYNC_STATUS_FAILED);
        }
    }

    private void addReliefActivity(GetterSetter reliefActivity, int sync_status)         //method is called in AddReliefActivity class
    {
        openDB();

        ContentValues reliefActivityValues = new ContentValues();
        reliefActivityValues.put("User_ID", Login.user_id);
        reliefActivityValues.put("Disaster_Type", reliefActivity.getDisaster());
        reliefActivityValues.put("Location", reliefActivity.getLocation());
        reliefActivityValues.put("Date", reliefActivity.getDate());
        reliefActivityValues.put("Food_Packages", reliefActivity.getFood_packages());
        reliefActivityValues.put("Wheat_Flours", reliefActivity.getWheat_flours());
        reliefActivityValues.put("Pulses", reliefActivity.getPulses());
        reliefActivityValues.put("Tents", reliefActivity.getTents());
        reliefActivityValues.put("Blankets", reliefActivity.getBlankets());
        reliefActivityValues.put("Life_Jackets", reliefActivity.getLife_jackets());
        reliefActivityValues.put("Aqua_Tablets", reliefActivity.getAqua_tablets());
        reliefActivityValues.put("Gas_Cylinders", reliefActivity.getGas_cylinders());
        reliefActivityValues.put("Mats", reliefActivity.getMats());
        reliefActivityValues.put("Mosquito_Nets", reliefActivity.getMosquito_nets());
        reliefActivityValues.put("Water_Coolers", reliefActivity.getWater_coolers());
        reliefActivityValues.put("Buckets", reliefActivity.getBuckets());
        reliefActivityValues.put("Pillows", reliefActivity.getPillows());
        reliefActivityValues.put("Quilts", reliefActivity.getQuilts());
        reliefActivityValues.put("Kitchen_Sets", reliefActivity.getKitchen_sets());
        reliefActivityValues.put("Other_Items", reliefActivity.getOther_items());
        reliefActivityValues.put("Camps", reliefActivity.getCamps());
        reliefActivityValues.put("Coverage", reliefActivity.getCoverage());
        reliefActivityValues.put("Report_Issued_By", reliefActivity.getReport_issued());
        reliefActivityValues.put("sync_status", sync_status);

        db.insert("relief_activities_new", null, reliefActivityValues);
        FabToast.makeText(context, context.getString(R.string.add_local_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
        db.close();
    }

    public boolean updateReliefActivitySyncStatus(int id, int sync_status, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", sync_status);
        db.update("relief_activities_new", contentValues, "Relief_Activity_ID=" + id, null);
        db.close();
        return true;
    }

    public Cursor readReliefActivitiesFromLocal(SQLiteDatabase db) {
        String[] columns = {"Relief_Activity_ID, User_ID, Disaster_Type, Location, Date, Food_Packages, Wheat_Flours, Pulses, Tents, Blankets, Life_Jackets, Aqua_Tablets, Gas_Cylinders, Mats, Mosquito_Nets, Water_Coolers, Buckets, Pillows, Quilts, Kitchen_Sets, Other_Items, Camps, Coverage, Report_Issued_By, sync_status"};
        return (db.query("relief_activities_new", columns, null, null, null, null, "Relief_Activity_ID DESC"));
    }

    /**
     * METHODS FOR RETRIEVING DATA FROM SERVER
     **/

    private void reliefActivityTableBuild() {
        openDB();
        db.execSQL("CREATE TABLE IF NOT EXISTS relief_activities_new(Relief_Activity_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, Disaster_Type TEXT, Location TEXT, Date TEXT, Food_Packages INTEGER, Wheat_Flours INTEGER, Pulses INTEGER, Tents INTEGER, Blankets INTEGER, Life_Jackets INTEGER, Aqua_Tablets INTEGER, Gas_Cylinders INTEGER, Mats INTEGER, Mosquito_Nets INTEGER, Water_Coolers INTEGER, Buckets INTEGER, Pillows INTEGER, Quilts INTEGER, Kitchen_Sets INTEGER, Other_Items INTEGER, Camps INTEGER, Coverage TEXT, Report_Issued_By TEXT, sync_status integer)");
    }

    private void deleteReliefActivityTable() {
        openDB();
        db.delete("relief_activities_new", "sync_status = ?", new String[]{Integer.toString(DatabaseConstants.SYNC_STATUS_OK)}); //deletes all those relief activities data which are successfully stored on server
        db.close();
    }

    private void getReliefActivitiesFromServer() {
        new AsyncTask<Void, Void, Void>() {
            String FinalJSonResult;

            @Override
            protected Void doInBackground(Void... voids) {
                HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.GET_RELIEF_ACTIVITIES_SERVER_URL);
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

                                    String relief_activity_id = jsonObject.getString("Relief_Activity_ID");
                                    String user_id = jsonObject.getString("User_ID");
                                    String disaster = jsonObject.getString("Disaster_Type");
                                    String location = jsonObject.getString("Location");
                                    String date = jsonObject.getString("Date");
                                    String food_packages = jsonObject.getString("Food_Packages");
                                    String wheat_flours = jsonObject.getString("Wheat_Flours");
                                    String pulses = jsonObject.getString("Pulses");
                                    String tents = jsonObject.getString("Tents");
                                    String blankets = jsonObject.getString("Blankets");
                                    String life_jackets = jsonObject.getString("Life_Jackets");
                                    String aqua_tablets = jsonObject.getString("Aqua_Tablets");
                                    String gas_cylinders = jsonObject.getString("Gas_Cylinders");
                                    String mats = jsonObject.getString("Mats");
                                    String mosquito_nets = jsonObject.getString("Mosquito_Nets");
                                    String water_coolers = jsonObject.getString("Water_Coolers");
                                    String buckets = jsonObject.getString("Buckets");
                                    String pillows = jsonObject.getString("Pillows");
                                    String quilts = jsonObject.getString("Quilts");
                                    String kitchen_sets = jsonObject.getString("Kitchen_Sets");
                                    String other_items = jsonObject.getString("Other_Items");
                                    String camps = jsonObject.getString("Camps");
                                    String coverage = jsonObject.getString("Coverage");
                                    String report_issued= jsonObject.getString("Report_Issued_By");
                                    String sync_status = jsonObject.getString("sync_status");

                                    openDB();
                                    ContentValues reliefActivityValues = new ContentValues();
                                    reliefActivityValues.put("Relief_Activity_ID", relief_activity_id);
                                    reliefActivityValues.put("User_ID", user_id);
                                    reliefActivityValues.put("Disaster_Type", disaster);
                                    reliefActivityValues.put("Location", location);
                                    reliefActivityValues.put("Date", date);
                                    reliefActivityValues.put("Food_Packages", food_packages);
                                    reliefActivityValues.put("Wheat_Flours", wheat_flours);
                                    reliefActivityValues.put("Pulses", pulses);
                                    reliefActivityValues.put("Tents", tents);
                                    reliefActivityValues.put("Blankets", blankets);
                                    reliefActivityValues.put("Life_Jackets", life_jackets);
                                    reliefActivityValues.put("Aqua_Tablets", aqua_tablets);
                                    reliefActivityValues.put("Gas_Cylinders", gas_cylinders);
                                    reliefActivityValues.put("Mats", mats);
                                    reliefActivityValues.put("Mosquito_Nets", mosquito_nets);
                                    reliefActivityValues.put("Water_Coolers", water_coolers);
                                    reliefActivityValues.put("Buckets", buckets);
                                    reliefActivityValues.put("Pillows", pillows);
                                    reliefActivityValues.put("Quilts", quilts);
                                    reliefActivityValues.put("Kitchen_Sets", kitchen_sets);
                                    reliefActivityValues.put("Other_Items", other_items);
                                    reliefActivityValues.put("Camps", camps);
                                    reliefActivityValues.put("Coverage", coverage);
                                    reliefActivityValues.put("Report_Issued_By", report_issued);
                                    reliefActivityValues.put("sync_status", sync_status);
                                    db.insert("relief_activities_new", null, reliefActivityValues);
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

    public void updateReliefActivitiesList()       //method is called in MainScreen, ReliefActivityScreen, ManageableReliefActivityScreen & ManageableReliefActivityScreenUser class
    {
        reliefActivityTableBuild();
        deleteReliefActivityTable();
        getReliefActivitiesFromServer();
    }

    /**
     * METHODS FOR RETRIEVING DATA FROM SQLite
     **/

    public List<GetterSetter> getAllReliefActivitiesFromLocal()        //method is called in ReliefActivityScreen & ManageableReliefActivityScreen class
    {
        openReadableDB();
        String[] columns = {"Relief_Activity_ID, User_ID, Disaster_Type, Location, Date, Food_Packages, Wheat_Flours, Pulses, Tents, Blankets, Life_Jackets, Aqua_Tablets, Gas_Cylinders, Mats, Mosquito_Nets, Water_Coolers, Buckets, Pillows, Quilts, Kitchen_Sets, Other_Items, Camps, Coverage, Report_Issued_By, sync_status"};
        Cursor cursor = db.query("relief_activities_new", columns, null, null, null, null, "Date DESC, Relief_Activity_ID DESC");
        ArrayList<GetterSetter> reliefActivityList = new ArrayList<>();
        while (cursor.moveToNext()) {
            GetterSetter reliefActivity = new GetterSetter();
            reliefActivity.setId(cursor.getInt(cursor.getColumnIndex("Relief_Activity_ID")));
            reliefActivity.setDisaster(cursor.getString(cursor.getColumnIndex("Disaster_Type")));
            reliefActivity.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
            reliefActivity.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            reliefActivity.setFood_packages(cursor.getInt(cursor.getColumnIndex("Food_Packages")));
            reliefActivity.setWheat_flours(cursor.getInt(cursor.getColumnIndex("Wheat_Flours")));
            reliefActivity.setPulses(cursor.getInt(cursor.getColumnIndex("Pulses")));
            reliefActivity.setTents(cursor.getInt(cursor.getColumnIndex("Tents")));
            reliefActivity.setBlankets(cursor.getInt(cursor.getColumnIndex("Blankets")));
            reliefActivity.setLife_jackets(cursor.getInt(cursor.getColumnIndex("Life_Jackets")));
            reliefActivity.setAqua_tablets(cursor.getInt(cursor.getColumnIndex("Aqua_Tablets")));
            reliefActivity.setGas_cylinders(cursor.getInt(cursor.getColumnIndex("Gas_Cylinders")));
            reliefActivity.setMats(cursor.getInt(cursor.getColumnIndex("Mats")));
            reliefActivity.setMosquito_nets(cursor.getInt(cursor.getColumnIndex("Mosquito_Nets")));
            reliefActivity.setWater_coolers(cursor.getInt(cursor.getColumnIndex("Water_Coolers")));
            reliefActivity.setBuckets(cursor.getInt(cursor.getColumnIndex("Buckets")));
            reliefActivity.setPillows(cursor.getInt(cursor.getColumnIndex("Pillows")));
            reliefActivity.setQuilts(cursor.getInt(cursor.getColumnIndex("Quilts")));
            reliefActivity.setKitchen_sets(cursor.getInt(cursor.getColumnIndex("Kitchen_Sets")));
            reliefActivity.setOther_items(cursor.getInt(cursor.getColumnIndex("Other_Items")));
            reliefActivity.setCamps(cursor.getInt(cursor.getColumnIndex("Camps")));
            reliefActivity.setCoverage(cursor.getString(cursor.getColumnIndex("Coverage")));
            reliefActivity.setReport_issued(cursor.getString(cursor.getColumnIndex("Report_Issued_By")));
            reliefActivity.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
            reliefActivityList.add(reliefActivity);
        }
        cursor.close();
        db.close();
        return reliefActivityList;
    }

    public List<GetterSetter> getUserReliefActivitiesFromLocal()       //method is called in ManageableReliefActivityScreenUser class
    {
        openReadableDB();
        String[] columns = new String[]{"Relief_Activity_ID, Disaster_Type, Location, Date, Food_Packages, Wheat_Flours, Pulses, Tents, Blankets, Life_Jackets, Aqua_Tablets, Gas_Cylinders, Mats, Mosquito_Nets, Water_Coolers, Buckets, Pillows, Quilts, Kitchen_Sets, Other_Items, Camps, Coverage, Report_Issued_By, sync_status"};
        String[] args = new String[]{Login.user_id};
        List<GetterSetter> reliefActivityList = new ArrayList<>();
        Cursor cursor = db.query("relief_activities_new", columns, "User_ID=?", args, null, null, "Date DESC, Relief_Activity_ID DESC");
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                GetterSetter reliefActivity = new GetterSetter();
                reliefActivity.setId(cursor.getInt(cursor.getColumnIndex("Relief_Activity_ID")));
                reliefActivity.setDisaster(cursor.getString(cursor.getColumnIndex("Disaster_Type")));
                reliefActivity.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
                reliefActivity.setDate(cursor.getString(cursor.getColumnIndex("Date")));
                reliefActivity.setFood_packages(cursor.getInt(cursor.getColumnIndex("Food_Packages")));
                reliefActivity.setWheat_flours(cursor.getInt(cursor.getColumnIndex("Wheat_Flours")));
                reliefActivity.setPulses(cursor.getInt(cursor.getColumnIndex("Pulses")));
                reliefActivity.setTents(cursor.getInt(cursor.getColumnIndex("Tents")));
                reliefActivity.setBlankets(cursor.getInt(cursor.getColumnIndex("Blankets")));
                reliefActivity.setLife_jackets(cursor.getInt(cursor.getColumnIndex("Life_Jackets")));
                reliefActivity.setAqua_tablets(cursor.getInt(cursor.getColumnIndex("Aqua_Tablets")));
                reliefActivity.setGas_cylinders(cursor.getInt(cursor.getColumnIndex("Gas_Cylinders")));
                reliefActivity.setMats(cursor.getInt(cursor.getColumnIndex("Mats")));
                reliefActivity.setMosquito_nets(cursor.getInt(cursor.getColumnIndex("Mosquito_Nets")));
                reliefActivity.setWater_coolers(cursor.getInt(cursor.getColumnIndex("Water_Coolers")));
                reliefActivity.setBuckets(cursor.getInt(cursor.getColumnIndex("Buckets")));
                reliefActivity.setPillows(cursor.getInt(cursor.getColumnIndex("Pillows")));
                reliefActivity.setQuilts(cursor.getInt(cursor.getColumnIndex("Quilts")));
                reliefActivity.setKitchen_sets(cursor.getInt(cursor.getColumnIndex("Kitchen_Sets")));
                reliefActivity.setOther_items(cursor.getInt(cursor.getColumnIndex("Other_Items")));
                reliefActivity.setCamps(cursor.getInt(cursor.getColumnIndex("Camps")));
                reliefActivity.setCoverage(cursor.getString(cursor.getColumnIndex("Coverage")));
                reliefActivity.setReport_issued(cursor.getString(cursor.getColumnIndex("Report_Issued_By")));
                reliefActivity.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
                reliefActivityList.add(reliefActivity);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reliefActivityList;
    }

    public List<GetterSetter> getSpecificReliefActivities()
    {
        openReadableDB();
        String[] columns = {"Relief_Activity_ID, User_ID, Disaster_Type, Location, Date, Food_Packages, Wheat_Flours, Pulses, Tents, Blankets, Life_Jackets, Aqua_Tablets, Gas_Cylinders, Mats, Mosquito_Nets, Water_Coolers, Buckets, Pillows, Quilts, Kitchen_Sets, Other_Items, Camps, Coverage, Report_Issued_By, sync_status"};
        String[] args = new String[]{District_Selection.location};
        Cursor cursor = db.query("relief_activities_new", columns, "Location=?", args, null, null, "Date DESC, Relief_Activity_ID DESC");
        ArrayList<GetterSetter> reliefActivityList = new ArrayList<>();
        while (cursor.moveToNext()) {
            GetterSetter reliefActivity = new GetterSetter();
            reliefActivity.setId(cursor.getInt(cursor.getColumnIndex("Relief_Activity_ID")));
            reliefActivity.setDisaster(cursor.getString(cursor.getColumnIndex("Disaster_Type")));
            reliefActivity.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
            reliefActivity.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            reliefActivity.setFood_packages(cursor.getInt(cursor.getColumnIndex("Food_Packages")));
            reliefActivity.setWheat_flours(cursor.getInt(cursor.getColumnIndex("Wheat_Flours")));
            reliefActivity.setPulses(cursor.getInt(cursor.getColumnIndex("Pulses")));
            reliefActivity.setTents(cursor.getInt(cursor.getColumnIndex("Tents")));
            reliefActivity.setBlankets(cursor.getInt(cursor.getColumnIndex("Blankets")));
            reliefActivity.setLife_jackets(cursor.getInt(cursor.getColumnIndex("Life_Jackets")));
            reliefActivity.setAqua_tablets(cursor.getInt(cursor.getColumnIndex("Aqua_Tablets")));
            reliefActivity.setGas_cylinders(cursor.getInt(cursor.getColumnIndex("Gas_Cylinders")));
            reliefActivity.setMats(cursor.getInt(cursor.getColumnIndex("Mats")));
            reliefActivity.setMosquito_nets(cursor.getInt(cursor.getColumnIndex("Mosquito_Nets")));
            reliefActivity.setWater_coolers(cursor.getInt(cursor.getColumnIndex("Water_Coolers")));
            reliefActivity.setBuckets(cursor.getInt(cursor.getColumnIndex("Buckets")));
            reliefActivity.setPillows(cursor.getInt(cursor.getColumnIndex("Pillows")));
            reliefActivity.setQuilts(cursor.getInt(cursor.getColumnIndex("Quilts")));
            reliefActivity.setKitchen_sets(cursor.getInt(cursor.getColumnIndex("Kitchen_Sets")));
            reliefActivity.setOther_items(cursor.getInt(cursor.getColumnIndex("Other_Items")));
            reliefActivity.setCamps(cursor.getInt(cursor.getColumnIndex("Camps")));
            reliefActivity.setCoverage(cursor.getString(cursor.getColumnIndex("Coverage")));
            reliefActivity.setReport_issued(cursor.getString(cursor.getColumnIndex("Report_Issued_By")));
            reliefActivity.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
            reliefActivityList.add(reliefActivity);
        }
        cursor.close();
        db.close();
        return reliefActivityList;
    }

    /**
     * METHODS FOR UPDATING RELIEF ACTIVITY
     **/

    public void updateReliefActivityOnServer(final GetterSetter reliefActivity)           //method is called in UpdateReliefActivity class
    {
        if (internetConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.UPDATE_RELIEF_ACTIVITY_SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("response");
                        if (Response.equals("OK")) {
                            updateReliefActivity(reliefActivity, DatabaseConstants.SYNC_STATUS_OK);
                            FabToast.makeText(context, context.getString(R.string.update_server_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
                        } else {
                            updateReliefActivity(reliefActivity, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    updateReliefActivity(reliefActivity, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Relief_Activity_ID", Integer.toString(reliefActivity.getId()));
                    params.put("Disaster_Type", reliefActivity.getDisaster());
                    params.put("Location", reliefActivity.getLocation());
                    params.put("Date", reliefActivity.getDate());
                    params.put("Food_Packages", Integer.toString(reliefActivity.getFood_packages()));
                    params.put("Wheat_Flours", Integer.toString(reliefActivity.getWheat_flours()));
                    params.put("Pulses", Integer.toString(reliefActivity.getPulses()));
                    params.put("Tents", Integer.toString(reliefActivity.getTents()));
                    params.put("Blankets", Integer.toString(reliefActivity.getBlankets()));
                    params.put("Life_Jackets", Integer.toString(reliefActivity.getLife_jackets()));
                    params.put("Aqua_Tablets", Integer.toString(reliefActivity.getAqua_tablets()));
                    params.put("Gas_Cylinders", Integer.toString(reliefActivity.getGas_cylinders()));
                    params.put("Mats", Integer.toString(reliefActivity.getMats()));
                    params.put("Mosquito_Nets", Integer.toString(reliefActivity.getMosquito_nets()));
                    params.put("Water_Coolers", Integer.toString(reliefActivity.getWater_coolers()));
                    params.put("Buckets", Integer.toString(reliefActivity.getBuckets()));
                    params.put("Pillows", Integer.toString(reliefActivity.getPillows()));
                    params.put("Quilts", Integer.toString(reliefActivity.getQuilts()));
                    params.put("Kitchen_Sets", Integer.toString(reliefActivity.getKitchen_sets()));
                    params.put("Other_Items", Integer.toString(reliefActivity.getOther_items()));
                    params.put("Camps", Integer.toString(reliefActivity.getCamps()));
                    params.put("Coverage", reliefActivity.getCoverage());
                    params.put("Report_Issued_By", reliefActivity.getReport_issued());
                    params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                    return params;
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        } else {
            updateReliefActivity(reliefActivity, DatabaseConstants.UPDATE_SYNC_STATUS_FAILED);
        }
    }

    private void updateReliefActivity(GetterSetter reliefActivity, int sync_status) {
        openDB();

        ContentValues reliefActivityValues = new ContentValues();
        reliefActivityValues.put("Disaster_Type", reliefActivity.getDisaster());
        reliefActivityValues.put("Location", reliefActivity.getLocation());
        reliefActivityValues.put("Date", reliefActivity.getDate());
        reliefActivityValues.put("Food_Packages", reliefActivity.getFood_packages());
        reliefActivityValues.put("Wheat_Flours", reliefActivity.getWheat_flours());
        reliefActivityValues.put("Pulses", reliefActivity.getPulses());
        reliefActivityValues.put("Tents", reliefActivity.getTents());
        reliefActivityValues.put("Blankets", reliefActivity.getBlankets());
        reliefActivityValues.put("Life_Jackets", reliefActivity.getLife_jackets());
        reliefActivityValues.put("Aqua_Tablets", reliefActivity.getAqua_tablets());
        reliefActivityValues.put("Gas_Cylinders", reliefActivity.getGas_cylinders());
        reliefActivityValues.put("Mats", reliefActivity.getMats());
        reliefActivityValues.put("Mosquito_Nets", reliefActivity.getMosquito_nets());
        reliefActivityValues.put("Water_Coolers", reliefActivity.getWater_coolers());
        reliefActivityValues.put("Buckets", reliefActivity.getBuckets());
        reliefActivityValues.put("Pillows", reliefActivity.getPillows());
        reliefActivityValues.put("Quilts", reliefActivity.getQuilts());
        reliefActivityValues.put("Kitchen_Sets", reliefActivity.getKitchen_sets());
        reliefActivityValues.put("Other_Items", reliefActivity.getOther_items());
        reliefActivityValues.put("Camps", reliefActivity.getCamps());
        reliefActivityValues.put("Coverage", reliefActivity.getCoverage());
        reliefActivityValues.put("Report_Issued_By", reliefActivity.getReport_issued());
        reliefActivityValues.put("sync_status", sync_status);
        String arg_id[] = new String[]{Integer.toString(reliefActivity.getId())};
        db.update("relief_activities_new", reliefActivityValues, "Relief_Activity_ID = ?", arg_id);
        FabToast.makeText(context, context.getString(R.string.update_local_success), FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();

        db.close();
    }

    /*
     * method for uploading data(unsync) on server*/

    public void uploadUnsyncDataOnServer() {
        if (internetConnected()) {
            final Database database = new Database(context);
            final SQLiteDatabase db = database.getWritableDatabase();
            final ReliefActivitiesDatabase_Adapter reliefActivityDatabaseAdapter = new ReliefActivitiesDatabase_Adapter(context);

            Cursor cursor = reliefActivityDatabaseAdapter.readReliefActivitiesFromLocal(db);

            while (cursor.moveToNext()) {
                int sync_status = cursor.getInt(cursor.getColumnIndex("sync_status"));
                if (sync_status == DatabaseConstants.SYNC_STATUS_FAILED) {
                    final int id = cursor.getInt(cursor.getColumnIndex("Relief_Activity_ID"));
                    final int user_id = cursor.getInt(cursor.getColumnIndex("User_ID"));
                    final String disaster_type = cursor.getString(cursor.getColumnIndex("Disaster_Type"));
                    final String location = cursor.getString(cursor.getColumnIndex("Location"));
                    final String date = cursor.getString(cursor.getColumnIndex("Date"));
                    final int food_packages=cursor.getInt(cursor.getColumnIndex("Food_Packages"));
                    final int wheat_flours=cursor.getInt(cursor.getColumnIndex("Wheat_Flours"));
                    final int pulses=cursor.getInt(cursor.getColumnIndex("Pulses"));
                    final int tents=cursor.getInt(cursor.getColumnIndex("Tents"));
                    final int blankets=cursor.getInt(cursor.getColumnIndex("Blankets"));
                    final int life_jackets=cursor.getInt(cursor.getColumnIndex("Life_Jackets"));
                    final int aqua_tablets=cursor.getInt(cursor.getColumnIndex("Aqua_Tablets"));
                    final int gas_cylinders=cursor.getInt(cursor.getColumnIndex("Gas_Cylinders"));
                    final int mats=cursor.getInt(cursor.getColumnIndex("Mats"));
                    final int mosquito_nets=cursor.getInt(cursor.getColumnIndex("Mosquito_Nets"));
                    final int water_coolers=cursor.getInt(cursor.getColumnIndex("Water_Coolers"));
                    final int buckets=cursor.getInt(cursor.getColumnIndex("Buckets"));
                    final int pillows=cursor.getInt(cursor.getColumnIndex("Pillows"));
                    final int quilts=cursor.getInt(cursor.getColumnIndex("Quilts"));
                    final int kitchen_sets=cursor.getInt(cursor.getColumnIndex("Kitchen_Sets"));
                    final int other_items=cursor.getInt(cursor.getColumnIndex("Other_Items"));
                    final int camps=cursor.getInt(cursor.getColumnIndex("Camps"));
                    final String coverage=cursor.getString(cursor.getColumnIndex("Coverage"));
                    final String report_issued = cursor.getString(cursor.getColumnIndex("Report_Issued_By"));

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.ADD_RELIEF_ACTIVITY_SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    Database database = new Database(context);
                                    SQLiteDatabase db = database.getWritableDatabase();
                                    reliefActivityDatabaseAdapter.updateReliefActivitySyncStatus(id, DatabaseConstants.SYNC_STATUS_OK, db);
                                    context.sendBroadcast(new Intent(DatabaseConstants.RELIEF_ACTIVITY_SAVED_BROADCAST));
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
                            params.put("Disaster_Type", disaster_type);
                            params.put("Location", location);
                            params.put("Date", date);
                            params.put("Food_Packages", Integer.toString(food_packages));
                            params.put("Wheat_Flours", Integer.toString(wheat_flours));
                            params.put("Pulses", Integer.toString(pulses));
                            params.put("Tents", Integer.toString(tents));
                            params.put("Blankets", Integer.toString(blankets));
                            params.put("Life_Jackets", Integer.toString(life_jackets));
                            params.put("Aqua_Tablets", Integer.toString(aqua_tablets));
                            params.put("Gas_Cylinders", Integer.toString(gas_cylinders));
                            params.put("Mats", Integer.toString(mats));
                            params.put("Mosquito_Nets", Integer.toString(mosquito_nets));
                            params.put("Water_Coolers", Integer.toString(water_coolers));
                            params.put("Buckets", Integer.toString(buckets));
                            params.put("Pillows", Integer.toString(pillows));
                            params.put("Quilts", Integer.toString(quilts));
                            params.put("Kitchen_Sets", Integer.toString(kitchen_sets));
                            params.put("Other_Items", Integer.toString(other_items));
                            params.put("Camps", Integer.toString(camps));
                            params.put("Coverage", coverage);
                            params.put("Report_Issued_By", report_issued);
                            params.put("sync_status", Integer.toString(DatabaseConstants.SYNC_STATUS_OK));
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);
                }
                if (sync_status == DatabaseConstants.UPDATE_SYNC_STATUS_FAILED) {
                    final int id = cursor.getInt(cursor.getColumnIndex("Relief_Activity_ID"));
                    final String disaster_type = cursor.getString(cursor.getColumnIndex("Disaster_Type"));
                    final String location = cursor.getString(cursor.getColumnIndex("Location"));
                    final String date = cursor.getString(cursor.getColumnIndex("Date"));
                    final int food_packages=cursor.getInt(cursor.getColumnIndex("Food_Packages"));
                    final int wheat_flours=cursor.getInt(cursor.getColumnIndex("Wheat_Flours"));
                    final int pulses=cursor.getInt(cursor.getColumnIndex("Pulses"));
                    final int tents=cursor.getInt(cursor.getColumnIndex("Tents"));
                    final int blankets=cursor.getInt(cursor.getColumnIndex("Blankets"));
                    final int life_jackets=cursor.getInt(cursor.getColumnIndex("Life_Jackets"));
                    final int aqua_tablets=cursor.getInt(cursor.getColumnIndex("Aqua_Tablets"));
                    final int gas_cylinders=cursor.getInt(cursor.getColumnIndex("Gas_Cylinders"));
                    final int mats=cursor.getInt(cursor.getColumnIndex("Mats"));
                    final int mosquito_nets=cursor.getInt(cursor.getColumnIndex("Mosquito_Nets"));
                    final int water_coolers=cursor.getInt(cursor.getColumnIndex("Water_Coolers"));
                    final int buckets=cursor.getInt(cursor.getColumnIndex("Buckets"));
                    final int pillows=cursor.getInt(cursor.getColumnIndex("Pillows"));
                    final int quilts=cursor.getInt(cursor.getColumnIndex("Quilts"));
                    final int kitchen_sets=cursor.getInt(cursor.getColumnIndex("Kitchen_Sets"));
                    final int other_items=cursor.getInt(cursor.getColumnIndex("Other_Items"));
                    final int camps=cursor.getInt(cursor.getColumnIndex("Camps"));
                    final String coverage=cursor.getString(cursor.getColumnIndex("Coverage"));
                    final String report_issued = cursor.getString(cursor.getColumnIndex("Report_Issued_By"));

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DatabaseConstants.UPDATE_RELIEF_ACTIVITY_SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    Database database = new Database(context);
                                    SQLiteDatabase db = database.getWritableDatabase();
                                    reliefActivityDatabaseAdapter.updateReliefActivitySyncStatus(id, DatabaseConstants.SYNC_STATUS_OK, db);
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
                            params.put("Relief_Activity_ID", Integer.toString(id));
                            params.put("Disaster_Type", disaster_type);
                            params.put("Location", location);
                            params.put("Date", date);
                            params.put("Food_Packages", Integer.toString(food_packages));
                            params.put("Wheat_Flours", Integer.toString(wheat_flours));
                            params.put("Pulses", Integer.toString(pulses));
                            params.put("Tents", Integer.toString(tents));
                            params.put("Blankets", Integer.toString(blankets));
                            params.put("Life_Jackets", Integer.toString(life_jackets));
                            params.put("Aqua_Tablets", Integer.toString(aqua_tablets));
                            params.put("Gas_Cylinders", Integer.toString(gas_cylinders));
                            params.put("Mats", Integer.toString(mats));
                            params.put("Mosquito_Nets", Integer.toString(mosquito_nets));
                            params.put("Water_Coolers", Integer.toString(water_coolers));
                            params.put("Buckets", Integer.toString(buckets));
                            params.put("Pillows", Integer.toString(pillows));
                            params.put("Quilts", Integer.toString(quilts));
                            params.put("Kitchen_Sets", Integer.toString(kitchen_sets));
                            params.put("Other_Items", Integer.toString(other_items));
                            params.put("Camps", Integer.toString(camps));
                            params.put("Coverage", coverage);
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
