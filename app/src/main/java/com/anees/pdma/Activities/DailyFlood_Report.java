package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anees.pdma.Adapters.DailyFloodReport_Adapter;
import com.anees.pdma.Adapters.DailyFloodReport_Database_Adapter;
import com.anees.pdma.DBase.Database;
import com.anees.pdma.FragmentClasses.DatePickerFragment;
import com.anees.pdma.R;
import com.anees.pdma.model.DatabaseConstants;
import com.anees.pdma.model.FirebaseNotificationService;
import com.anees.pdma.model.GetterSetter;
import com.anees.pdma.model.HttpServiceClass;
import com.anees.pdma.model.Style;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import me.leolin.shortcutbadger.ShortcutBadger;
import spencerstudios.com.fab_toast.FabToast;

public class DailyFlood_Report extends AppCompatActivity implements DatePickerFragment.DateDialogListener {

    private int STORAGE_PERMISSION_CODE = 23;

    DailyFloodReport_Database_Adapter floodReportDatabaseAdapter = new DailyFloodReport_Database_Adapter(this);
    private DailyFloodReport_Adapter floodReportRecyclerAdapter;
    private Database database;
    private SQLiteDatabase db;
    private RecyclerView recyclerViewFloodReports;
    private ArrayList<GetterSetter> listFloodReport = new ArrayList<>();
    BroadcastReceiver broadcastReceiver;
    ProgressDialog progressDialog;
    int sr_no=0;
    String fileName;
    public static String floodReportTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_flood_report);

        recyclerViewFloodReports = (RecyclerView) findViewById(R.id.id_flood_report_recycler_view);
        floodReportRecyclerAdapter = new DailyFloodReport_Adapter(listFloodReport);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewFloodReports.setLayoutManager(mLayoutManager);
        recyclerViewFloodReports.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFloodReports.setHasFixedSize(true);
        recyclerViewFloodReports.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewFloodReports.setAdapter(floodReportRecyclerAdapter);

        checkNotificationCount();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getFloodReports();
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

            case R.id.action_print:
                askForPermission();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(getSupportFragmentManager(), "Select Date");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getFloodReports() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listFloodReport.clear();
                listFloodReport.addAll(floodReportDatabaseAdapter.getAllFloodReportsFromLocal());
                return null;
            }

            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                floodReportRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(DatabaseConstants.FLOOD_REPORT_SAVED_BROADCAST));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog!=null)
        {
            progressDialog.dismiss();
        }
    }

    protected void onResume() {
        super.onResume();
        getFloodReports();
    }

    //this is called in Android 6.0 & above versions to get run time permission from user for saving pdf files
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
                            floodReportDatabaseAdapter.uploadUnsyncDataOnServer();
                            floodReportDatabaseAdapter.updateFloodReportsList();
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
                    progressDialog = new ProgressDialog(DailyFlood_Report.this, R.style.MyProgressDialogTheme);
                    progressDialog.setCancelable(false);
                    progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    progressDialog.show();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progressDialog.dismiss();
                    getFloodReports();
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

    @Override
    public void onFinishDialog(Date date) {
        if (DatePickerFragment.floodReportDate.equals(floodReportDatabaseAdapter.validateFloodReportDate()))
        {
            floodReportTime=getCurrentTime();
            cretePDF();
        }
        else
        {
            FabToast.makeText(getApplicationContext(), "No Record Exists For The Selected Date", FabToast.LENGTH_SHORT, FabToast.INFORMATION, FabToast.POSITION_DEFAULT).show();
        }
    }

    public void cretePDF() {
        // Create New Blank Document
        Document document = new Document(PageSize.A4);
        // Create Directory in External Storage
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Emergency Alert-PDMA KP Reports/" + "Daily Flood Reports");

        fileName="DailyFloodReport"+DatePickerFragment.floodReportDate+".pdf";
        String FILE = myDir + "/" + fileName;

        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        // Create Pdf Writer for Writing into New Created Document
        try {
            PdfWriter pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(FILE));
            MyFooter event = new MyFooter();
            pdfWriter.setPageEvent(event);

            // Open Document for Writing into document
            document.open();
            // User Define Method
            addMetaData(document);
            addContent(document);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Close Document after writing all content
        document.close();
        //Toast.makeText(getApplicationContext(), "PDF File is Created. Location : " + FILE, Toast.LENGTH_LONG).show();
        viewPDF();
    }

    // Set PDF document Properties
    public void addMetaData(Document document)
    {
        document.addTitle("Daily Flood Report");
        document.addSubject("Daily Flood Report " + DatePickerFragment.floodReportDate);
        document.addKeywords("River Name, Location, Discharge in (cusecs)");
        document.addAuthor("PDMA KP");
        document.addCreator("Emergency Alert PDMA KP");
    }

    public void addContent(Document document) throws DocumentException {
        // Font Style for Document
        //Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD | Font.UNDERLINE, BaseColor.GRAY);
        // Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        // Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);

        // Start New Paragraph
        Paragraph firstParagraph = new Paragraph();
        firstParagraph.setFont(titleFont);  // Set Font in this Paragraph
        // Add item into Paragraph
        firstParagraph.add("GOVERNMENT OF KHYBER PAKHTUNKHWA\n FLOOD CELL IRRIGATION DEPARTMENT PESHAWAR\n DAILY FLOOD REPORT\n PHONE/FA NO. 091-9212114, 9211907\n\n");

        // Create Table into Document with 1 Row
        PdfPTable titleTable = new PdfPTable(1);
        titleTable.setWidthPercentage(100.0f);      // 100.0f mean width of table is same as Document size

        PdfPCell titleCell = new PdfPCell(new Paragraph(""));       // Create New Cell into Table
        titleCell.setBorder(Rectangle.BOTTOM);
        titleTable.addCell(titleCell);      // Add Cell into Table
        firstParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(firstParagraph);   // Add all above details into Document
        document.add(titleTable);
        document.add(titleTable);  //for line after first paragraph

        // Now Start another New Paragraph for date
        Paragraph dateParagraph = new Paragraph();
        dateParagraph.setFont(smallBold);
        dateParagraph.add("Date: " + DatePickerFragment.floodReportDate + "                                                 " +
                "                                   " +" Time: " + floodReportTime +"\n\n");
        dateParagraph.setAlignment(Element.ALIGN_LEFT);
        document.add(dateParagraph);

        PdfPTable table = new PdfPTable(7);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        try {
            table.setTotalWidth(new float[] {5, 20, 15, 13, 13, 13, 12});
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        PdfPCell cell=new PdfPCell();
        cell.setColspan(5);

        table.addCell(createLabelCell("S #"));
        table.addCell(createLabelCell("Name of Rivers"));
        table.addCell(createLabelCell("Locations"));
        table.addCell(createLabelCell("Discharge in (cusecs)"));
        table.addCell(createLabelCell("Flow Status"));
        table.addCell(createLabelCell("Remarks"));
        table.addCell(createLabelCell("Time"));

        openReadableDB();

        String[] columns = new String[]{"River_Name, Location, Flow_Status, Time, Remarks, Discharge_Cusecs"};
        String[] args = new String[]{DatePickerFragment.floodReportDate};
        Cursor cursor = db.query("flood_reports", columns, "Date=?", args, null, null, null);
        if (cursor.moveToFirst())
        {
            do {
                sr_no=sr_no + 1;
                table.addCell(Integer.toString(sr_no));
                String river_name=cursor.getString(cursor.getColumnIndex("River_Name"));
                table.addCell(river_name);
                String location=cursor.getString(cursor.getColumnIndex("Location"));
                table.addCell(location);
                String discharge=cursor.getString(cursor.getColumnIndex("Discharge_Cusecs"));
                table.addCell(discharge);
                String flow_status=cursor.getString(cursor.getColumnIndex("Flow_Status"));
                table.addCell(flow_status);
                String remarks=cursor.getString(cursor.getColumnIndex("Remarks"));
                table.addCell(remarks);
                String time=cursor.getString(cursor.getColumnIndex("Time"));
                table.addCell(time);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        document.add(table);

        Paragraph paragraph=new Paragraph();
        paragraph.setFont(smallBold);
        paragraph.add("\nTIME LAG\n\n");
        document.add(paragraph);

        document.add(timeLagTable());



        // Create new Page in PDF
        document.newPage();
    }

    public static PdfPTable timeLagTable()
    {
        PdfPTable table = new PdfPTable(4);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        try {
            table.setTotalWidth(new float[] {5, 18, 10, 8});
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        PdfPCell cell=new PdfPCell();
        cell.setColspan(4);
        cell.setRowspan(6);

        table.addCell(createLabelCell("S.No"));
        table.addCell(createLabelCell("Location"));
        table.addCell(createLabelCell("Distance in KM"));
        table.addCell(createLabelCell("Time Lag"));

        table.addCell("1");
        table.addCell("Khawaza Khela to Amandara");
        table.addCell("65");
        table.addCell("12 Hours");

        table.addCell("2");
        table.addCell("Amandara to Munda");
        table.addCell("55");
        table.addCell("9 Hours");

        table.addCell("3");
        table.addCell("Munda to Charsadda Road");
        table.addCell("40");
        table.addCell("6.5 Hours");

        table.addCell("4");
        table.addCell("Charsada to Nowshera");
        table.addCell("35");
        table.addCell("6 Hours");

        table.addCell("5");
        table.addCell("Warsak to Nowshera");
        table.addCell("60");
        table.addCell("10 Hours");

        return table;
    }

    class MyFooter extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 8, Font.ITALIC);

        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("This is a system generated document", ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 10, 0);
        }
    }

    // create cells
    private static PdfPCell createLabelCell(String text){
        // font
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));
        // set style
        Style.labelCellStyle(cell);
        return cell;
    }

    // create cells
    private static PdfPCell createValueCell(String text){
        // font
        Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);

        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));

        // set style
        Style.valueCellStyle(cell);
        return cell;
    }

    private void openReadableDB() {
        database = new Database(getApplicationContext());
        db = database.getReadableDatabase();
    }

    public void viewPDF() {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/Emergency Alert-PDMA KP Reports/" + "Daily Flood Reports/" + fileName);
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivity(Intent.createChooser(pdfIntent, "Select pdf Viewer"));
        } catch (Exception e) {
            Toast.makeText(DailyFlood_Report.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
        String time = dateformat.format(calendar.getTime());
        return time;
    }

    private void checkNotificationCount()
    {
        if (FirebaseNotificationService.floodReportBadgeCount>=1)
        {
            FirebaseNotificationService.totalBadgeCount=FirebaseNotificationService.totalBadgeCount-FirebaseNotificationService.floodReportBadgeCount;
            FirebaseNotificationService.floodReportBadgeCount=0;
            ShortcutBadger.applyCount(getApplicationContext(), FirebaseNotificationService.totalBadgeCount);
        }
    }

}