package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anees.pdma.R;

public class GraphicalReport extends AppCompatActivity implements View.OnClickListener {

    Button btn_losses_report, btn_natural_disaster_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_report);

        btn_losses_report = findViewById(R.id.id_btn_losses_report);
        btn_natural_disaster_report = findViewById(R.id.id_btn_natural_disaster_report);

        btn_losses_report.setOnClickListener(this);
        btn_natural_disaster_report.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_losses_report:
                Toast.makeText(this, "Click Losses Report", Toast.LENGTH_SHORT).show();
                Intent intent_first_report = new Intent(getApplicationContext(), Report_Date_Selection.class);
                startActivityForResult(intent_first_report, 0);
                /*Intent intent_incidents = new Intent(this, Report_Date_Selection.class);
                startActivity(intent_incidents);*/
                break;

            case R.id.id_btn_natural_disaster_report:
                Toast.makeText(this, "Click natural disaster Activities", Toast.LENGTH_SHORT).show();
                Intent intent_second_report = new Intent(getApplicationContext(), Report_Date_Selection.class);
                startActivityForResult(intent_second_report, 1);
               /* Intent intent_relief = new Intent(this, ReliefActivities.class);
                startActivity(intent_relief);*/
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)       //0 is the code of loses report
        {
            if (resultCode == Activity.RESULT_OK) {
                Intent intent_first_report = new Intent(this, IncidentsLoses_Report.class);
                startActivity(intent_first_report);
            }
        } else if (requestCode == 1)      //1 is the code of disaster report
        {
            if (resultCode == Activity.RESULT_OK) {
                Intent intent_second_report = new Intent(this, IncidentsDisasters_Report.class);
                startActivity(intent_second_report);
            }
        }
    }
}