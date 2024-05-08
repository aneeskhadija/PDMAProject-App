package com.anees.pdma.model;

public class DatabaseConstants {

    public static final int SYNC_STATUS_OK = 0;
    public static final int SYNC_STATUS_FAILED = 1;

    public static final int UPDATE_SYNC_STATUS_FAILED = 2;

    //private static final String ip_address="http://172.16.184.17";        //ims
    //private static final String ip_address="http://192.168.0.102/naturaldisastersalertforkp/";
    //private static final String ip_address="http://192.168.10.8/naturaldisastersalertforkp/";
    //private static final String ip_address="http://192.168.2.143/naturaldisastersalertforkp/";      //PDMA
    //private static final String ip_address="http://203.124.46.27";     http://192.168.0.102/naturaldisastersalertforkp/images/1.png //PDMA Server
    private static final String ip_address = "http://175.107.63.54/parrsa/";    //Data centre

    public static final String ADD_INCIDENT_SERVER_URL = ip_address + "add_incident.php";
    public static final String GET_INCIDENTS_SERVER_URL = ip_address + "get_incidents.php";
    public static final String UPDATE_INCIDENT_SERVER_URL = ip_address + "update_incident.php";
    public static final String INCIDENT_SAVED_BROADCAST = "com.pdma.fasihims.emergencyalertpdmakp.uiupdatebroadcast";

    public static final String ADD_RELIEF_ACTIVITY_SERVER_URL = ip_address + "add_relief_activity_new.php";
    public static final String GET_RELIEF_ACTIVITIES_SERVER_URL = ip_address + "get_relief_activities_new.php";
    public static final String UPDATE_RELIEF_ACTIVITY_SERVER_URL = ip_address + "update_relief_activity_new.php";
    public static final String RELIEF_ACTIVITY_SAVED_BROADCAST = "com.pdma.fasihims.emergencyalertpdmakp.uiupdatebroadcast";

    public static final String ADD_FLOOD_REPORT_SERVER_URL = ip_address + "add_flood_report.php";
    public static final String GET_FLOOD_REPORTS_SERVER_URL = ip_address + "get_flood_reports.php";
    public static final String UPDATE_FLOOD_REPORT_SERVER_URL = ip_address + "update_flood_report.php";
    public static final String FLOOD_REPORT_SAVED_BROADCAST = "com.pdma.fasihims.emergencyalertpdmakp.uiupdatebroadcast";

    public static final String GET_USERS_SERVER_URL = ip_address + "get_users.php";

    public static final String GET_WARNINGS_SERVER_URL = ip_address + "get_warnings.php";
    public static final String WARNING_SAVED_BROADCAST = "com.pdma.fasihims.emergencyalertpdmakp.uiupdatebroadcast";

    public static final String GET_REPORTS_SERVER_URL = ip_address + "get_reports.php";

    public static final String GET_RIVER_FLOW_REPORTS_SERVER_URL = ip_address + "get_river_flow_reports.php";

    public static final String SEND_NOTIFICATION = ip_address + "sendNotification.php";

    public static final String CONNECT_WITH_SERVER = ip_address + "db_connect.php";
}
