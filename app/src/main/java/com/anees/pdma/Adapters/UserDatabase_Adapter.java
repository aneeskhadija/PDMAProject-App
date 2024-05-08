package com.anees.pdma.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.anees.pdma.Activities.Login;
import com.anees.pdma.DBase.Database;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.HttpServiceClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDatabase_Adapter {

    private String username, password;

    private Context context;
    private Database database;
    private SQLiteDatabase db;

    public UserDatabase_Adapter(Context context) {
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

    private void userTableBuild() {
        openDB();
        db.execSQL("CREATE TABLE IF NOT EXISTS users(User_ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Username TEXT, Password TEXT, Contact_No TEXT, Email TEXT, Designation TEXT, District TEXT, sync_status integer)");
    }

    private void deleteUserTable() {
        openDB();
        db.delete("users", "sync_status = ?", new String[]{Integer.toString(DatabaseConstants.SYNC_STATUS_OK)}); //deletes all those users data which are successfully stored on server
        db.close();
    }

    private void getUsersFromServer() {
        new AsyncTask<Void, Void, Void>() {
            String FinalJSonResult;

            @Override
            protected Void doInBackground(Void... voids) {
                HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.GET_USERS_SERVER_URL);
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

                                    String Id = jsonObject.getString("User_ID");
                                    String name = jsonObject.getString("Name");
                                    String user_name = jsonObject.getString("Username");
                                    String password = jsonObject.getString("Password");
                                    String contact_no = jsonObject.getString("Contact_No");
                                    String email = jsonObject.getString("Email");
                                    String designation = jsonObject.getString("Designation");
                                    String district = jsonObject.getString("District");
                                    String sync_status = jsonObject.getString("sync_status");

                                    openDB();
                                    ContentValues userValues = new ContentValues();
                                    userValues.put("User_ID", Id);
                                    userValues.put("Name", name);
                                    userValues.put("Username", user_name);
                                    userValues.put("Password", password);
                                    userValues.put("Contact_No", contact_no);
                                    userValues.put("Email", email);
                                    userValues.put("Designation", designation);
                                    userValues.put("District", district);
                                    userValues.put("sync_status", sync_status);
                                    db.insert("users", null, userValues);
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

    //method is called in MainScreen, & ManageableUsersScreen class
    public void updateUsersList() {
        userTableBuild();
        deleteUserTable();
        getUsersFromServer();
    }

    /**
     * METHODS FOR VALIDATING USERNAME & PASSWORD
     */

    //method is called in LoginScreen class
    public String validateUserUsername() {
        openReadableDB();

        String[] columns = new String[]{"User_ID, Name, Username, Designation"};
        String args[] = new String[]{Login.username};
        Cursor cursor = db.query("users", columns, "Username=?", args, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Login.user_id = cursor.getString(cursor.getColumnIndex("User_ID"));
                Login.name = cursor.getString(cursor.getColumnIndex("Name"));
                username = cursor.getString(cursor.getColumnIndex("Username"));
                Login.designation = cursor.getString(cursor.getColumnIndex("Designation"));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return username;
    }

    //method is called in LoginScreen class
    public String validateUserPassword() {
        openReadableDB();

        String[] columns = new String[]{"Password"};
        String args[] = new String[]{Login.username};
        Cursor cursor = db.query("users", columns, "Username=?", args, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                password = cursor.getString(cursor.getColumnIndex("Password"));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return password;
    }
}
