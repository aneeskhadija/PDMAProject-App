package com.anees.pdma.model;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.anees.pdma.Activities.Home;
import com.anees.pdma.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import me.leolin.shortcutbadger.ShortcutBadger;

public class FirebaseNotificationService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessageService";

    public static int warningBadgeCount, incidentBadgeCount, weatherReportBadgeCount, floodReportBadgeCount, totalBadgeCount=0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)         //this is method is called when app is in the foreground
    {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String activity = data.getString("click_action");

            //switch notification value for incidents
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            Boolean incident_notification_enabled = sharedPreferences.getBoolean("key_incident_notification", true);

            //switch notification value for daily situation reports
            SharedPreferences sharedPreferences_weather_report = PreferenceManager.getDefaultSharedPreferences(this);
            Boolean weather_report_notification_enabled = sharedPreferences_weather_report.getBoolean("key_daily_situation_notification", true);

            if (title.startsWith("Inc") && incident_notification_enabled) {
                displayNotification(title, message, activity);
                incidentBadgeCount= incidentBadgeCount + 1;
                totalBadgeCount=warningBadgeCount+incidentBadgeCount+weatherReportBadgeCount+floodReportBadgeCount;
                ShortcutBadger.applyCount(getApplicationContext(), totalBadgeCount); //for 1.1.4+
            } else if (title.startsWith("War")) {
                displayNotification(title, message, activity);
                warningBadgeCount= warningBadgeCount + 1;
                totalBadgeCount=warningBadgeCount+incidentBadgeCount+weatherReportBadgeCount+floodReportBadgeCount;
                ShortcutBadger.applyCount(getApplicationContext(), totalBadgeCount); //for 1.1.4+
            } else if (title.startsWith("Wea")  && weather_report_notification_enabled) {
                displayNotification(title, message, activity);
                weatherReportBadgeCount= weatherReportBadgeCount + 1;
                totalBadgeCount=warningBadgeCount+incidentBadgeCount+weatherReportBadgeCount+floodReportBadgeCount;
                ShortcutBadger.applyCount(getApplicationContext(), totalBadgeCount); //for 1.1.4+
            } else if (title.startsWith("Ale")) {
                displayNotification(title, message, activity);
                floodReportBadgeCount= floodReportBadgeCount + 1;
                totalBadgeCount=warningBadgeCount+incidentBadgeCount+weatherReportBadgeCount+floodReportBadgeCount;
                ShortcutBadger.applyCount(getApplicationContext(), totalBadgeCount); //for 1.1.4+
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    private void displayNotification(String title, String messageBody, String activity) {
        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("click_action", activity);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String notification_ringtone = sharedPreferences.getString("New Ringtone", "DEFAULT_SOUND");
        Boolean notification_ringtone_enabled = sharedPreferences.getBoolean("notifications_message", true);
        Boolean vibration_enabled = sharedPreferences.getBoolean("key_vibrate", true);

        NotificationCompat.Builder notification_builder = new NotificationCompat.Builder(this);
        //Notification object must have setSmallIcon, setContentTitle, setContentText
        notification_builder.setSmallIcon(R.drawable.pdma);
        notification_builder.setContentTitle(title);
        notification_builder.setContentText(messageBody);
        notification_builder.setAutoCancel(true);   //this is used to remove the notification from notification panel when user clicks on it

        if (notification_ringtone_enabled) {
            notification_builder.setSound(Uri.parse(notification_ringtone));
        }
        if (vibration_enabled) {
            long[] vibrate = {100, 200}; //The first index of array is number of milliseconds to wait before turning the vibrator on and the second index is number of millisecond to vibrate before turning it off
            notification_builder.setVibrate(vibrate); // if vibration is ON by RadioGroup, It will be Vibrate
        }
        notification_builder.setLights(Color.RED, 3000, 3000);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification_builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notification_builder.build());
    }

}
