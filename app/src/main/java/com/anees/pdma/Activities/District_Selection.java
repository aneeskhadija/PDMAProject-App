package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.anees.pdma.R;

import java.util.ArrayList;
import java.util.List;

public class District_Selection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String location;
    Spinner spinner_location;
    Button cancel, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district__selection);

        spinner_location = (Spinner) findViewById(R.id.location_spinner);
        spinner_location.setOnItemSelectedListener(this);
        List<String> districts = new ArrayList<>();
        districts.add("Abbotabad");
        districts.add("Bannu");
        districts.add("Battagram");
        districts.add("Buner");
        districts.add("Charsadda");
        districts.add("Chitral");
        districts.add("D I Khan");
        districts.add("Dir Lower");
        districts.add("Dir Upper");
        districts.add("Hangu");
        districts.add("Haripur");
        districts.add("Karak");
        districts.add("Kohat");
        districts.add("Kohistan Lower");
        districts.add("Kohistan Upper");
        districts.add("Lakki Marwat");
        districts.add("Malakand");
        districts.add("Mansehra");
        districts.add("Mardan");
        districts.add("Nowshera");
        districts.add("Peshawar");
        districts.add("Swabi");
        districts.add("Shangla");
        districts.add("Swat");
        districts.add("Tank");
        districts.add("Tor Ghar");
        ArrayAdapter<String> districts_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districts);
        districts_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   //simple_spinner_dropdown_item it is a predefined layout for spinners
        spinner_location.setAdapter(districts_adapter);

        cancel = (Button) findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        done = (Button) findViewById(R.id.button_district_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReliefActivities_District.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Spinner spinner = (Spinner) adapterView;
        if (spinner.getId() == R.id.location_spinner) {
            location = adapterView.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}