package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.WallpaperColors;
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

import com.anees.pdma.Adapters.WarningDatabase_Adapter;
import com.anees.pdma.Adapters.WarningRecycler_Adapter;
import com.anees.pdma.R;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.FirebaseNotificationService;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;

import java.util.ArrayList;

import me.leolin.shortcutbadger.ShortcutBadger;
import spencerstudios.com.fab_toast.FabToast;

public class WarningActivity extends AppCompatActivity {

    WarningDatabase_Adapter warningDatabaseAdapter = new WarningDatabase_Adapter(this);
    private RecyclerView recyclerViewWarnings;
    private ArrayList<GetterSetter> listWarning = new ArrayList<>();
    private WarningRecycler_Adapter warningRecyclerAdapter;

    BroadcastReceiver broadcastReceiver;

    ProgressDialog progressDialog;

    public static GetterSetter warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        recyclerViewWarnings = (RecyclerView) findViewById(R.id.warning_recycler_view);
        warningRecyclerAdapter = new WarningRecycler_Adapter(listWarning);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewWarnings.setLayoutManager(mLayoutManager);
        recyclerViewWarnings.setItemAnimator(new DefaultItemAnimator());
        recyclerViewWarnings.setHasFixedSize(true);
        recyclerViewWarnings.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewWarnings.setAdapter(warningRecyclerAdapter);

        checkNotificationCount();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getWarnings();
            }
        };
    }

    //Method for the overflow of action bar
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flood_screen_action_bar_items, menu);        //layout defined for the overflow items of action bar
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

    private void getWarnings() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listWarning.clear();
                listWarning.addAll(warningDatabaseAdapter.getAllWarningsFromLocal());
                return null;
            }

            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                warningRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(DatabaseConstants.WARNING_SAVED_BROADCAST));
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
        getWarnings();
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
                            warningDatabaseAdapter.updateWarningsList();
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
                    progressDialog = new ProgressDialog(WarningActivity.this, R.style.MyProgressDialogTheme);
                    progressDialog.setCancelable(false);
                    progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    progressDialog.show();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progressDialog.dismiss();
                    getWarnings();
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
        if (FirebaseNotificationService.warningBadgeCount>=1)
        {
            FirebaseNotificationService.totalBadgeCount=FirebaseNotificationService.totalBadgeCount-FirebaseNotificationService.warningBadgeCount;
            FirebaseNotificationService.warningBadgeCount=0;
            ShortcutBadger.applyCount(getApplicationContext(), FirebaseNotificationService.totalBadgeCount);
        }
    }
}