package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anees.pdma.Adapters.IncidentDatabase_Adapter;
import com.anees.pdma.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class IncidentsDisasters_Report extends AppCompatActivity {

    IncidentDatabase_Adapter incidentDatabaseAdapter = new IncidentDatabase_Adapter(this);

    public static float earthquake_count, flood_count, landslide_count, heavy_rain_count, other_count = 0f;

    PieChart pieChart;
    ArrayList<Entry> pieChartEntries;
    ArrayList<String> PieChartLabels;
    PieDataSet pieDataSet;
    PieData pieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidents_disasters_report);

        pieChart = (PieChart) findViewById(R.id.incidents_pie_chart);

        pieChartEntries = new ArrayList<>();
        PieChartLabels = new ArrayList<>();

        AddValuesToPieChartEntries();

        pieDataSet = new PieDataSet(pieChartEntries, "");

        pieData = new PieData(PieChartLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieDataSet.setValueFormatter(new PercentFormatter());
        pieDataSet.setValueTextSize(12f);

        pieChart.setData(pieData);
        pieChart.animateY(3000);       //3000 milliseconds (3 seconds)
        pieChart.setDescription("Percentage of Incidents in KP");
        pieChart.setDescriptionTextSize(20f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);
    }

    public void AddValuesToPieChartEntries() {
        incidentDatabaseAdapter.getDisastersForGraph();

        PieChartLabels.add("Earthquake");
        pieChartEntries.add(new Entry(earthquake_count, 0));
        earthquake_count = 0f;
        PieChartLabels.add("Flood");
        pieChartEntries.add(new Entry(flood_count, 1));
        flood_count = 0f;
        PieChartLabels.add("Landslide");
        pieChartEntries.add(new Entry(landslide_count, 2));
        landslide_count = 0f;
        PieChartLabels.add("Heavy Rain");
        pieChartEntries.add(new Entry(heavy_rain_count, 3));
        heavy_rain_count = 0f;
        PieChartLabels.add("Other");
        pieChartEntries.add(new Entry(other_count, 5));
        other_count = 0f;
    }
}