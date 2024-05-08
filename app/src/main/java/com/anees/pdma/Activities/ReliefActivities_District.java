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

import com.anees.pdma.Adapters.ReliefActivitiesDatabase_Adapter;
import com.anees.pdma.Adapters.ReliefActivitiesRecycler_Adapter;
import com.anees.pdma.R;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;

import java.util.ArrayList;

import spencerstudios.com.fab_toast.FabToast;

public class ReliefActivities_District extends AppCompatActivity {

    ReliefActivitiesDatabase_Adapter reliefActivityDatabaseAdapter = new ReliefActivitiesDatabase_Adapter(this);

    private RecyclerView recyclerViewReliefActivity;
    private ArrayList<GetterSetter> listReliefActivities = new ArrayList<>();
    private ReliefActivitiesRecycler_Adapter reliefActivityRecyclerAdapter;
    BroadcastReceiver broadcastReceiver;
    ProgressDialog progressDialog;
    public static GetterSetter reliefActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_activities_district);

        recyclerViewReliefActivity = (RecyclerView) findViewById(R.id.relief_activity_recycler_view);
        reliefActivityRecyclerAdapter = new ReliefActivitiesRecycler_Adapter(listReliefActivities);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewReliefActivity.setLayoutManager(mLayoutManager);
        recyclerViewReliefActivity.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReliefActivity.setHasFixedSize(true);
        recyclerViewReliefActivity.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewReliefActivity.setAdapter(reliefActivityRecyclerAdapter);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getReliefActivities();
            }
        };

        /*recyclerViewReliefActivity.addOnItemTouchListener(new MyRecyclerItemClickListener(getApplicationContext(), recyclerViewReliefActivity, new MyRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, final int position) {
                final Dialog dialog = new Dialog(ReliefActivityScreenDistrict.this);
                dialog.setContentView(R.layout.custom_share_alert_dialog);
                Button share_button = (Button) dialog.findViewById(R.id.dialog_button_share);
                share_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        askForSMSPermission();
                        reliefActivity = listReliefActivities.get(position);
                        Intent intent_sms = new Intent(Intent.ACTION_SEND);
                        intent_sms.setType("text/plain");
                        // Specify the Message
                        intent_sms.putExtra(Intent.EXTRA_TEXT, "Relief Activity" + "\nDisaster: " + reliefActivity.getDisaster() + "\nLocation: " + reliefActivity.getLocation() + "\nDate: " + reliefActivity.getDate() +
                                "\nFood Packages: " + reliefActivity.getFood_packages() + "\nWheat Flours: " + reliefActivity.getWheat_flours() + "\nPulses: " + reliefActivity.getPulses()
                                + "\nTents: " + reliefActivity.getTents() + "\nBlankets: " + reliefActivity.getBlankets() + "\nLife Jackets: " + reliefActivity.getLife_jackets()
                                + "\nAqua Tablets: " + reliefActivity.getAqua_tablets() + "\nGas Cylinders: " + reliefActivity.getGas_cylinders() + "\nMats: " + reliefActivity.getMats()
                                + "\nMosquito Nets: " + reliefActivity.getMosquito_nets() + "\nWater Coolers: " + reliefActivity.getWater_coolers() + "\nBuckets: " + reliefActivity.getBuckets()
                                + "\nPillows: " + reliefActivity.getPillows() + "\nQuilts: " + reliefActivity.getQuilts() + "\nKitchen Sets: " + reliefActivity.getKitchen_sets()
                                + "\nOther Items: " + reliefActivity.getOther_items() + "\nCamps: " + reliefActivity.getCamps()
                                + "\nCoverage: " + reliefActivity.getCoverage() + "\nReport Issued/Updated By: " + reliefActivity.getReport_issued());
                        startActivity(Intent.createChooser(intent_sms, "Share via"));
                        dialog.dismiss();
                    }
                });
                Button cancel_button = (Button) dialog.findViewById(R.id.dialog_button_cancel);
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }));*/
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

    private void getReliefActivities() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listReliefActivities.clear();
                listReliefActivities.addAll(reliefActivityDatabaseAdapter.getSpecificReliefActivities());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                reliefActivityRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(DatabaseConstants.RELIEF_ACTIVITY_SAVED_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog != null) {
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
        getReliefActivities();
    }

    //this method is called in Android 6.0 & above versions to get run time permission from user
   /* private void askForSMSPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.SEND_SMS)) {
                //This is called if user has denied the permission before. In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, SMS_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, SMS_REQUEST_CODE);
            }
        }
    }*/

    private boolean internetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void syncDataWithServer() {
        if (internetConnected(getApplicationContext())) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.CONNECT_WITH_SERVER);
                    try {
                        httpServiceClass.ExecutePostRequest();
                        if (httpServiceClass.getResponseCode() == 200) {
                            reliefActivityDatabaseAdapter.uploadUnsyncDataOnServer();
                            reliefActivityDatabaseAdapter.updateReliefActivitiesList();
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
                    progressDialog = new ProgressDialog(ReliefActivities_District.this, R.style.MyProgressDialogTheme);
                    progressDialog.setCancelable(false);
                    progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    progressDialog.show();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progressDialog.dismiss();
                    getReliefActivities();
                }

                @Override
                protected void onProgressUpdate(Void... values) {
                    super.onProgressUpdate(values);
                    FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.synchronized_success_message), FabToast.LENGTH_SHORT, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
                }
            }.execute();
        } else {
            FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.synchronized_error_message), FabToast.LENGTH_SHORT, FabToast.ERROR, FabToast.POSITION_DEFAULT).show();
        }
    }
}