package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anees.pdma.R;

public class Weather extends AppCompatActivity {

    Button btn_daily_reports, btn_flood_reports, btn_river_flow_reports, btn_PMD_alert, btn_rainfall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        btn_daily_reports = findViewById(R.id.id_btn_daily_report);
        btn_flood_reports = findViewById(R.id.id_btn_flood_report);
        btn_river_flow_reports = findViewById(R.id.id_btn_river_report);
        btn_PMD_alert = findViewById(R.id.id_btn_pmd_alert);
        btn_rainfall = findViewById(R.id.id_btn_rain_fall);

        btn_daily_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Weather.this, "Special Daily report in progress!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_flood_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Weather.this, "Flood report in progress!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_river_flow_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Weather.this, "River flow report in progress!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_PMD_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Weather.this, "PMD alert in progress!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_rainfall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Weather.this, "Rainfall in progress!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}