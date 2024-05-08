package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anees.pdma.R;

public class Incidents extends AppCompatActivity implements View.OnClickListener {

    Button btn_incident_report, btn_relief_activities, btn_graphical_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidents);

        btn_incident_report = findViewById(R.id.id_btn_incident_report);
        btn_relief_activities = findViewById(R.id.id_btn_relieve_activiteis);
        btn_graphical_report = findViewById(R.id.id_btn_graphical_report);

        btn_incident_report.setOnClickListener(this);
        btn_relief_activities.setOnClickListener(this);
        btn_graphical_report.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_incident_report:
                Toast.makeText(this, "Click Incidents Report", Toast.LENGTH_SHORT).show();
                Intent intent_incidents = new Intent(this, IncidentReport.class);
                startActivity(intent_incidents);
                break;

            case R.id.id_btn_relieve_activiteis:
                Toast.makeText(this, "Click Relief Activities", Toast.LENGTH_SHORT).show();
                Intent intent_relief = new Intent(this, ReliefActivities.class);
                startActivity(intent_relief);
                break;

            case R.id.id_btn_graphical_report:
                Toast.makeText(this, "Click Graphical Report", Toast.LENGTH_SHORT).show();
                Intent intent_report = new Intent(this, GraphicalReport.class);
                startActivity(intent_report);
                break;
        }
    }
}