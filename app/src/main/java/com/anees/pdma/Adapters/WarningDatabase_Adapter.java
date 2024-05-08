package com.anees.pdma.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.anees.pdma.DBase.Database;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarningDatabase_Adapter {

    private Context context;
    private Database database;
    private SQLiteDatabase db;

    private Geocoder geocoder;

    public WarningDatabase_Adapter(Context context) {
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
     * METHODS FOR RETRIEVING DATA FROM SERVER
     **/

    private void warningTableBuild() {
        openDB();
        db.execSQL("CREATE TABLE IF NOT EXISTS warnings(Warning_ID INTEGER PRIMARY KEY AUTOINCREMENT, Disaster_Type TEXT, Location TEXT, Warning_Level TEXT, Date TEXT, Time TEXT, More_Information TEXT, sync_status integer)");
    }

    private void deleteWarningTable() {
        openDB();
        db.delete("warnings", "sync_status = ?", new String[]{Integer.toString(DatabaseConstants.SYNC_STATUS_OK)}); //deletes all those warnings data which are successfully stored on server
        db.close();
    }

    private void getWarningsFromServer() {
        new AsyncTask<Void, Void, Void>() {
            String FinalJSonResult;

            @Override
            protected Void doInBackground(Void... voids) {
                HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.GET_WARNINGS_SERVER_URL);
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

                                    String warning_id = jsonObject.getString("Warning_ID");
                                    String disaster = jsonObject.getString("Disaster_Type");
                                    String location = jsonObject.getString("Location");
                                    String level = jsonObject.getString("Warning_Level");
                                    String date = jsonObject.getString("Date");
                                    String time = jsonObject.getString("Time");
                                    String more_information = jsonObject.getString("More_Information");
                                    String sync_status = jsonObject.getString("sync_status");

                                    openDB();
                                    ContentValues warningValues = new ContentValues();
                                    warningValues.put("Warning_ID", warning_id);
                                    warningValues.put("Disaster_Type", disaster);
                                    warningValues.put("Location", location);
                                    warningValues.put("Warning_Level", level);
                                    warningValues.put("Date", date);
                                    warningValues.put("Time", time);
                                    warningValues.put("More_Information", more_information);
                                    warningValues.put("sync_status", sync_status);
                                    db.insert("warnings", null, warningValues);
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

    //method is called in MainScreen, WarningsScreen & ManageableWarningsScreen class
    public void updateWarningsList() {
        warningTableBuild();
        deleteWarningTable();
        getWarningsFromServer();
    }

    /**
     * METHODS FOR RETRIEVING DATA FROM SQLite
     **/
    //method is called in WarningsScreen & ManageableWarningsScreen class
    public List<GetterSetter> getAllWarningsFromLocal() {
        openReadableDB();
        String[] columns = {"Warning_ID, Disaster_Type, Location, Warning_Level, Date, Time, More_Information, sync_status"};
        Cursor cursor = db.query("warnings", columns, null, null, null, null, "Date DESC, Warning_ID DESC");
        ArrayList<GetterSetter> warningList = new ArrayList<>();
        while (cursor.moveToNext()) {
            GetterSetter warning = new GetterSetter();
            warning.setId(cursor.getInt(cursor.getColumnIndex("Warning_ID")));
            warning.setDisaster_type(cursor.getString(cursor.getColumnIndex("Disaster_Type")));
            warning.setLocation(cursor.getString(cursor.getColumnIndex("Location")));
            warning.setWarning_level(cursor.getString(cursor.getColumnIndex("Warning_Level")));
            warning.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            warning.setTime(cursor.getString(cursor.getColumnIndex("Time")));
            warning.setMore_information(cursor.getString(cursor.getColumnIndex("More_Information")));
            warning.setSync_status(cursor.getInt(cursor.getColumnIndex("sync_status")));
            warningList.add(warning);
        }
        cursor.close();
        db.close();
        return warningList;
    }

    //method is called in MainScreen class
    public String getLatestWarning() {
        openReadableDB();

        String[] columns = new String[]{"Disaster_Type, Location, Date"};

        String latest_warning = new String();

        Cursor cursor = db.query("warnings", columns, null, null, null, null, "Date DESC, Warning_ID DESC");
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String warning_type = cursor.getString(cursor.getColumnIndex("Disaster_Type"));
            String location = cursor.getString(cursor.getColumnIndex("Location"));
            String date = cursor.getString(cursor.getColumnIndex("Date"));
            latest_warning = "Warning: " + warning_type + " Location: " + location + " Date: " + date;
        }
        cursor.close();
        db.close();
        return latest_warning;
    }

    //Method to show all the warnings on the map & called by EventMapScreen
   /* public LatLng getWarningsForMap() {
        geocoder = new Geocoder(context);
        List<Address> warning_addresses;
        LatLng latLngWarningAddress = null;

        openReadableDB();

        String[] warning_columns = new String[]{"Disaster_Type, Location, Date, Time"};
        Cursor warning_cursor = db.query("warnings", warning_columns, null, null, null, null, null);
        warning_cursor.moveToFirst();
        if (warning_cursor.getCount() > 0) {
            do {
                EventMapScreen.warning = warning_cursor.getString(warning_cursor.getColumnIndex("Disaster_Type"));
                EventMapScreen.warning_location = warning_cursor.getString(warning_cursor.getColumnIndex("Location"));
                EventMapScreen.warning_date = warning_cursor.getString(warning_cursor.getColumnIndex("Date"));
                EventMapScreen.warning_time = warning_cursor.getString(warning_cursor.getColumnIndex("Time"));

                try {
                    warning_addresses = geocoder.getFromLocationName(EventMapScreen.warning_location, 5);
                    if (warning_addresses == null) {
                        return null;
                    }

                    Address location = warning_addresses.get(0);
                    location.getLatitude();
                    location.getLongitude();

                    latLngWarningAddress = new LatLng(location.getLatitude(), location.getLongitude());
                    EventMapScreen.mMap.addMarker(new MarkerOptions().position(latLngWarningAddress).flat(true).zIndex(0.8f).title("Warning: " + EventMapScreen.warning)
                            .snippet(" Date: " + EventMapScreen.warning_date + " Time: " + EventMapScreen.warning_time))
                            .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.warning_icon));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            while (warning_cursor.moveToNext());
        }
        warning_cursor.close();
        db.close();
        return latLngWarningAddress;
    }*/

}
