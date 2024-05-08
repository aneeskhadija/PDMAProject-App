package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anees.pdma.Adapters.IncidentDatabase_Adapter;
import com.anees.pdma.Adapters.IncidentRecycler_Adapter;
import com.anees.pdma.R;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.FirebaseNotificationService;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;

import java.util.ArrayList;

import me.leolin.shortcutbadger.ShortcutBadger;
import spencerstudios.com.fab_toast.FabToast;

public class IncidentReport extends AppCompatActivity {

    IncidentDatabase_Adapter incidentDatabaseAdapter = new IncidentDatabase_Adapter(this);
    private RecyclerView recyclerViewIncidents;
    private ArrayList<GetterSetter> listIncident = new ArrayList<>();
    private IncidentRecycler_Adapter incidentRecyclerAdapter;

    ProgressDialog progressDialog;
    BroadcastReceiver broadcastReceiver;

    public static GetterSetter incident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_report);

        recyclerViewIncidents = (RecyclerView) findViewById(R.id.incident_recycler_view);
        incidentRecyclerAdapter = new IncidentRecycler_Adapter(listIncident);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewIncidents.setLayoutManager(mLayoutManager);
        recyclerViewIncidents.setItemAnimator(new DefaultItemAnimator());
        recyclerViewIncidents.setHasFixedSize(true);
        recyclerViewIncidents.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewIncidents.setAdapter(incidentRecyclerAdapter);

        checkNotificationCount();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getIncidentsFromLocal();
            }
        };
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flood_screen_action_bar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Switch case for the items of action bar
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_sync:
                syncDataWithServer();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getIncidentsFromLocal() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listIncident.clear();
                listIncident.addAll(incidentDatabaseAdapter.getAllIncidentsFromLocal());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                incidentRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(DatabaseConstants.INCIDENT_SAVED_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog!=null)
        {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    protected void onResume() {
        super.onResume();
        getIncidentsFromLocal();
    }

    private boolean internetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void syncDataWithServer() {
        if (internetConnected(getApplicationContext()))
        {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.CONNECT_WITH_SERVER);
                    try {
                        httpServiceClass.ExecutePostRequest();
                        if (httpServiceClass.getResponseCode() == 200) {
                            incidentDatabaseAdapter.uploadUnsyncDataOnServer();
                            incidentDatabaseAdapter.updateIncidentsList();
                            publishProgress();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressDialog = new ProgressDialog(IncidentReport.this, R.style.MyProgressDialogTheme);
                    progressDialog.setCancelable(false);
                    progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    progressDialog.show();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progressDialog.dismiss();
                    getIncidentsFromLocal();
                }

                @Override
                protected void onProgressUpdate(Void... values) {
                    super.onProgressUpdate(values);
                    FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.synchronized_success_message), FabToast.LENGTH_SHORT, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();
                }
            }.execute();
        }
        else {
            FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.synchronized_error_message), FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
        }
    }

    private void checkNotificationCount()
    {
        if (FirebaseNotificationService.incidentBadgeCount>=1)
        {
            FirebaseNotificationService.totalBadgeCount=FirebaseNotificationService.totalBadgeCount-FirebaseNotificationService.incidentBadgeCount;
            FirebaseNotificationService.incidentBadgeCount=0;
            ShortcutBadger.applyCount(getApplicationContext(), FirebaseNotificationService.totalBadgeCount);
        }
    }

}