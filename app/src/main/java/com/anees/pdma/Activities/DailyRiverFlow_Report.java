package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.anees.pdma.Adapters.DailyRiverFlowReport_Adapter;
import com.anees.pdma.Adapters.MyRecyclerItemClickListener;
import com.anees.pdma.R;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import spencerstudios.com.fab_toast.FabToast;

public class DailyRiverFlow_Report extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 23;
    private RecyclerView recyclerView;
    private ArrayList<GetterSetter> riverFlowReportList = new ArrayList<>();
    private DailyRiverFlowReport_Adapter riverFlowReportRecyclerAdapter;
    JsonArrayRequest RequestOfJSonArray;
    RequestQueue requestQueue;
    public static GetterSetter riverFlowReport;
    public static String ReportName;
    public static String ReportPath;
    String downloadFileName = "";
    ProgressDialog progressDialog_getReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_river_flow_report);

        recyclerView = (RecyclerView) findViewById(R.id.river_flow_report_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(new MyRecyclerItemClickListener(getApplicationContext(), recyclerView, new MyRecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, final int position) {
                /*final Dialog dialog=new Dialog(DailyRiverFlow_Report.this);
                dialog.setContentView(R.layout.custom_download_alert_dialog);
                Button download_button=(Button) dialog.findViewById(R.id.dialog_button_download);
                download_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (internetConnected(getApplicationContext()))
                        {
                            riverFlowReport = riverFlowReportList.get(position);
                            ReportName = riverFlowReport.getReport_name();
                            ReportPath = riverFlowReport.getReport_path();
                            new DownloadTask(RiverFlowReportsScreen.this, ReportPath);
                            dialog.dismiss();
                        }
                        else
                        {
                            dialog.dismiss();
                            FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.download_error_message), FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
                        }
                    }
                });
                Button cancel_button=(Button) dialog.findViewById(R.id.dialog_button_cancel);
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();*/
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        askForPermission();
    }

    protected void onResume() {
        super.onResume();
        getAllReports();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog_getReports!=null)
        {
            progressDialog_getReports.dismiss();
        }
    }

    public void getReports(){

        RequestOfJSonArray = new JsonArrayRequest(DatabaseConstants.GET_RIVER_FLOW_REPORTS_SERVER_URL,

                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue = Volley.newRequestQueue(DailyRiverFlow_Report.this);
        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            GetterSetter GetDataAdapter2 = new GetterSetter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                GetDataAdapter2.setReport_name(json.getString("Report_Name"));
                GetDataAdapter2.setReport_path(json.getString("Report_Path"));
            } catch (JSONException e) {

                e.printStackTrace();
            }
            riverFlowReportList.add(GetDataAdapter2);
        }

        riverFlowReportRecyclerAdapter = new DailyRiverFlowReport_Adapter(riverFlowReportList, this);
        recyclerView.setAdapter(riverFlowReportRecyclerAdapter);
    }

    //Method for the overflow of action bar
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.flood_screen_action_bar_items, menu);        //layout defined for the overflow items of action bar
        return super.onCreateOptionsMenu(menu);
    }
    //Switch case for the items of action bar
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.action_sync:
                syncDataWithServer();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAllReports() {
        if (internetConnected(getApplicationContext())) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    HttpServiceClass httpServiceClass = new HttpServiceClass(DatabaseConstants.CONNECT_WITH_SERVER);
                    try {
                        httpServiceClass.ExecutePostRequest();
                        if (httpServiceClass.getResponseCode() == 200) {
                            getReports();
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
                    progressDialog_getReports = new ProgressDialog(DailyRiverFlow_Report.this, R.style.MyProgressDialogTheme);
                    progressDialog_getReports.setCancelable(false);
                    progressDialog_getReports.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    progressDialog_getReports.show();
                    riverFlowReportList.clear();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progressDialog_getReports.dismiss();
                }
            }.execute();
        }
        else {
            /*final Dialog dialog=new Dialog(DailyRiverFlow_Report.this);
            dialog.setTitle(R.string.network_unavailable);
            dialog.setContentView(R.layout.custom_internet_alert_dialog);
            dialog.setCancelable(false);
            Button ok_button=(Button) dialog.findViewById(R.id.dialog_button_ok);
            ok_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.show();*/
        }
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
                            getReports();
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
                    progressDialog_getReports = new ProgressDialog(DailyRiverFlow_Report.this, R.style.MyProgressDialogTheme);
                    progressDialog_getReports.setCancelable(false);
                    progressDialog_getReports.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    progressDialog_getReports.show();
                    riverFlowReportList.clear();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progressDialog_getReports.dismiss();
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

    private boolean internetConnected(Context context) {
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }

    //this is called in Android 6.0 & above versions to get run time permission from user
    private void askForPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //This is called if user has denied the permission before. In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            }
        }
    }

    /*Methods for downloading pdf reports*/
    private class CheckForSDCard {
        //Check If SD Card is present or not method
        public boolean isSDCardPresent() {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return true;
            }
            return false;
        }
    }

    public class DownloadTask {

        private static final String TAG = "Download Task";
        private Context context;
        private String downloadUrl = "";
        private ProgressDialog progressDialog;

        public DownloadTask(Context context, String downloadUrl) {
            this.context = context;

            this.downloadUrl = downloadUrl;

            downloadFileName = ReportName+".pdf";

            //Start Downloading Task
            new DownloadingTask().execute();
        }

        private class DownloadingTask extends AsyncTask<Void, Void, Void> {

            File apkStorage = null;
            File outputFile = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Downloading...");
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(Void result) {
                try {
                    if (outputFile != null) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Downloaded Successfully", Toast.LENGTH_SHORT).show();
                        viewPDF();
                    } else {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 3000);

                        progressDialog.dismiss();
                        Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Download Failed");

                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //Change button text if exception occurs

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 3000);
                    Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

                }

                super.onPostExecute(result);
            }

            @Override
            protected Void doInBackground(Void... arg0) {
                try {
                    URL url = new URL(downloadUrl);//Create Download URl
                    HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                    c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                    c.connect();//connect the URL Connection

                    //If Connection response is not OK then show Logs
                    if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        Log.e(TAG, "Server returned HTTP " + c.getResponseCode() + " " + c.getResponseMessage());

                    }

                    //Get File if SD card is present
                    if (new CheckForSDCard().isSDCardPresent()) {

                        String root= Environment.getExternalStorageDirectory().toString();
                        apkStorage = new File(root + "/Emergency Alert-PDMA KP Reports/" + "River Flow Reports");
                    } else
                        Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                    //If File is not present create directory
                    if (!apkStorage.exists()) {
                        apkStorage.mkdirs();
                        Log.e(TAG, "Directory Created.");
                    }

                    outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File

                    //Create New File if not present
                    if (!outputFile.exists()) {
                        outputFile.createNewFile();
                        Log.e(TAG, "File Created");
                    }

                    FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                    InputStream is = c.getInputStream();//Get InputStream for connection

                    byte[] buffer = new byte[1024];//Set buffer type
                    int len1 = 0;//init length
                    while ((len1 = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len1);//Write new file
                    }

                    //Close all connection after doing task
                    fos.close();
                    is.close();

                } catch (Exception e) {

                    //Read exception if something went wrong
                    e.printStackTrace();
                    outputFile = null;
                    Log.e(TAG, "Download Error Exception " + e.getMessage());
                }
                return null;
            }
        }
    }

    public void viewPDF() {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/Emergency Alert-PDMA KP Reports/" + "River Flow Reports/" + downloadFileName);
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivity(Intent.createChooser(pdfIntent, "Select pdf Viewer"));
            finish();
        } catch (Exception e) {
            Toast.makeText(DailyRiverFlow_Report.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }


}