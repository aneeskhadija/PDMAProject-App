package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anees.pdma.Adapters.IncidentDatabase_Adapter;
import com.anees.pdma.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class IncidentsLoses_Report extends AppCompatActivity {

    IncidentDatabase_Adapter incidentDatabaseAdapter = new IncidentDatabase_Adapter(this);

    public static int loses_count, injuries_count, houses_damaged_count = 0;

    BarChart barChart;

    ArrayList<BarEntry> BarChartEntries;
    ArrayList<String> BarChartLabels;
    BarDataSet Bardataset;
    BarData BARDATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidents_loses_report);

        barChart = (BarChart) findViewById(R.id.loses_bar_graph);


        BarChartEntries = new ArrayList<>();
        BarChartLabels = new ArrayList<>();

        AddValuesToBarChartEntries();

        Bardataset = new BarDataSet(BarChartEntries, "Loses");
        BARDATA = new BarData(BarChartLabels, Bardataset);
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(BARDATA);
        barChart.animateY(2000);       //2000 milliseconds (2 seconds)

        BARDATA.setValueFormatter(new LargeValueFormatter());
    }

    private void AddValuesToBarChartEntries() {
        incidentDatabaseAdapter.getLosesForGraph();

        BarChartEntries.add(new BarEntry(loses_count, 0));
        BarChartLabels.add("Deaths");
        loses_count = 0;

        BarChartEntries.add(new BarEntry(injuries_count, 1));
        BarChartLabels.add("Injuries");
        injuries_count = 0;

        BarChartEntries.add(new BarEntry(houses_damaged_count, 2));
        BarChartLabels.add("Houses Damaged");
        houses_damaged_count = 0;
    }

   /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reports_screen_items, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    //Switch case for the items of action bar
   /* public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                Intent intent_login = new Intent(this, LoginScreen.class);
                startActivity(intent_login);
                return true;

            case R.id.action_about_us:
                Intent intent_about_us = new Intent(this, AboutUs.class);
                startActivity(intent_about_us);
                return true;

            case R.id.action_settings:
                Intent intent_settings = new Intent(this, Settings.class);
                startActivity(intent_settings);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}