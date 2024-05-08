package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anees.pdma.R;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

public class Weather_Alert_Report extends AppCompatActivity implements View.OnClickListener  {

    LinearLayout L_alert_advisors, L_daily_flood_report, L_river_flow_reports, L_pmd_alert, L_rainfall_forecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_alert_report);

        L_alert_advisors = findViewById(R.id.id_btn_alert_advisor);
        L_daily_flood_report = findViewById(R.id.id_btn_daily_flood_report);
        L_river_flow_reports = findViewById(R.id.id_btn_river_flow_report);
        L_pmd_alert = findViewById(R.id.id_btn_pmd_alert);
        L_rainfall_forecast = findViewById(R.id.id_btn_rainfall_forecast);

        L_alert_advisors.setOnClickListener(this);
        L_daily_flood_report.setOnClickListener(this);
        L_river_flow_reports.setOnClickListener(this);
        L_pmd_alert.setOnClickListener(this);
        L_rainfall_forecast.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_alert_advisor:
                Toast.makeText(this, "Click Alert Advisor!!!", Toast.LENGTH_SHORT).show();
                Intent weather_alert_advisor_intnt = new Intent(Weather_Alert_Report.this, WeatherAlertAdvisor.class);
                startActivity(weather_alert_advisor_intnt);

        break;
            case R.id.id_btn_daily_flood_report:
                Toast.makeText(this, "Click Daily Flood!!!", Toast.LENGTH_SHORT).show();
                Intent daily_flood_report_intnt = new Intent(Weather_Alert_Report.this, DailyFlood_Report.class);
                startActivity(daily_flood_report_intnt);
                break;

            case R.id.id_btn_river_flow_report:
                Toast.makeText(this, "Click River flow!!!", Toast.LENGTH_SHORT).show();
                Intent daily_river_flow_report_intnt = new Intent(Weather_Alert_Report.this, DailyRiverFlow_Report.class);
                startActivity(daily_river_flow_report_intnt);
                break;

            case R.id.id_btn_pmd_alert:
                Toast.makeText(this, "Click PMD Alert!!!", Toast.LENGTH_SHORT).show();

                new FancyGifDialog.Builder(this)
                        .setTitle("PMD Alert read in English OR Urdu") // You can also send title like R.string.from_resources
                        .setMessage("The alert, addressed to all district administrations in the province, has advised to remain alert in view of a forecast of Pakistan Meteorological Department (PMD) about heavy rainfall with thunderstorm.") // or pass like R.string.description_from_resources
                        .setNegativeBtnText("اردو") // or pass it like android.R.string.cancel
                        .setPositiveBtnBackground(R.color.colorDialogue_bg) // or pass it like R.color.positiveButton
                        .setPositiveBtnText("English") // or pass it like android.R.string.ok
                        .setNegativeBtnBackground(R.color.colorDialogue_bg1) // or pass it like R.color.negativeButton
                        .setGifResource(R.drawable.gif2)   //Pass your Gif here
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(Weather_Alert_Report.this,"Click in English",Toast.LENGTH_SHORT).show();
                                Uri weather_website = Uri.parse("http://www.rmckpk.pmd.gov.pk/KP-P-W.HTML");      //implicit intent
                                Intent intent_website = new Intent(Intent.ACTION_VIEW, weather_website);
                                startActivity(intent_website);
                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(Weather_Alert_Report.this,"Click in Urdu",Toast.LENGTH_SHORT).show();
                                Uri weather_website = Uri.parse("http://www.rmckpk.pmd.gov.pk/URDU.HTML");      //implicit intent
                                Intent intent_website = new Intent(Intent.ACTION_VIEW, weather_website);
                                startActivity(intent_website);
                            }
                        })
                        .build();

                break;

            case R.id.id_btn_rainfall_forecast:
                Toast.makeText(this, "Click Rainfall forecast!!!", Toast.LENGTH_SHORT).show();
                Intent rainfall_forecast_intnt = new Intent(Weather_Alert_Report.this, Rainfall_Forecast.class);
                startActivity(rainfall_forecast_intnt);

                break;
        }

    }
}