package com.anees.pdma.DBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    //Database Version
    private static final int Database_Ver = 2;
    //Database Name
    private static final String Database_Name = "parrsa_db";
    //constructor
    public Database(Context context) {
        super(context, Database_Name, null, Database_Ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String User_Table = "CREATE TABLE users(User_ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Username TEXT, Password TEXT, Contact_No TEXT, Email TEXT, Designation TEXT, District TEXT, sync_status integer)";
        String Incidents_Table = "CREATE TABLE incidents(Incident_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, Disaster_Type TEXT, Location TEXT, Date TEXT, Time TEXT, Loses TEXT, Injuries TEXT, Houses_Damaged TEXT, Affected_Road TEXT, Alternate_Road TEXT, More_Information TEXT, Report_Issued_By TEXT, sync_status integer)";
        String Relief_Activities_Table = "CREATE TABLE relief_activities(Relief_Activity_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, Disaster_Type TEXT, Location TEXT, Date TEXT, Food_Items_Distributed TEXT, Non_Food_Items_Distributed TEXT, Heavy_Machinery_Deployed TEXT, More_Information TEXT, Report_Issued_By TEXT, sync_status integer)";
        String Warnings_Table = "CREATE TABLE warnings(Warning_ID INTEGER PRIMARY KEY AUTOINCREMENT, Disaster_Type TEXT, Location TEXT, Warning_Level TEXT, Date TEXT, Time TEXT, More_Information TEXT, sync_status integer)";
        String Flood_Reports_Table = "CREATE TABLE flood_reports(Flood_Report_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, River_Name TEXT, Location TEXT, Flow_Status TEXT, Date TEXT, Time TEXT, Remarks TEXT, Discharge_Cusecs TEXT, sync_status integer)";
        String Relief_Activities_New_Table = "CREATE TABLE relief_activities_new(Relief_Activity_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, Disaster_Type TEXT, Location TEXT, Date TEXT, Food_Packages INTEGER, Wheat_Flours INTEGER, Pulses INTEGER, Tents INTEGER, Blankets INTEGER, Life_Jackets INTEGER, Aqua_Tablets INTEGER, Gas_Cylinders INTEGER, Mats INTEGER, Mosquito_Nets INTEGER, Water_Coolers INTEGER, Buckets INTEGER, Pillows INTEGER, Quilts INTEGER, Kitchen_Sets INTEGER, Other_Items INTEGER, Camps INTEGER, Coverage TEXT, Report_Issued_By TEXT, sync_status INTEGER)";

        db.execSQL(User_Table);
        db.execSQL(Incidents_Table);
        db.execSQL(Relief_Activities_Table);
        db.execSQL(Warnings_Table);
        db.execSQL(Flood_Reports_Table);
        db.execSQL(Relief_Activities_New_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_Ver, int new_Ver) {
        //drop table
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS incidents");
        db.execSQL("DROP TABLE IF EXISTS relief_activities");
        db.execSQL("DROP TABLE IF EXISTS warnings");
        db.execSQL("DROP TABLE IF EXISTS flood_reports");
        db.execSQL("DROP TABLE IF EXISTS relief_activities_new");
        onCreate(db);
    }
}
