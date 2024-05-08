package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.anees.pdma.R;

public class Report_DateSelection extends AppCompatActivity implements View.OnClickListener {

    public static String startDate, endDate;
    Button btn_done;

    ImageView imageView_start_date_dropdown, imageView_end_date_dropdown;
    DatePicker datePicker_startDate, datePicker_endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__date_selection);

        btn_done=(Button) findViewById(R.id.button_done);
        btn_done.setOnClickListener(this);

        datePicker_startDate = (DatePicker) findViewById(R.id.start_date_picker);
        datePicker_endDate = (DatePicker) findViewById(R.id.end_date_picker);

        imageView_start_date_dropdown = (ImageView) findViewById(R.id.start_date_dropdown);
        imageView_start_date_dropdown.setOnClickListener(this);
        imageView_end_date_dropdown = (ImageView) findViewById(R.id.end_date_dropdown);
        imageView_end_date_dropdown.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.start_date_dropdown:
                if (datePicker_startDate.getVisibility()==View.GONE)
                {
                    datePicker_startDate.setVisibility(View.VISIBLE);
                    imageView_start_date_dropdown.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);

                }
                else
                {
                    datePicker_startDate.setVisibility(View.GONE);
                    imageView_start_date_dropdown.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.end_date_dropdown:
                if (datePicker_endDate.getVisibility()==View.GONE)
                {
                    datePicker_endDate.setVisibility(View.VISIBLE);
                    imageView_end_date_dropdown.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);

                }
                else
                {
                    datePicker_endDate.setVisibility(View.GONE);
                    imageView_end_date_dropdown.setImageResource(R.drawable.ic_arrow_drop_down_circle_black_24dp);
                }
                break;

            case R.id.button_done:
                getStartDate();
                getEndDate();

                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);      // Setting resultCode to Activity.RESULT_OK to identify an old activity
                finish();

                break;
        }
    }

    public String getStartDate() {
        int startYear=datePicker_startDate.getYear();
        int startMonth=datePicker_startDate.getMonth() + 1;
        int startDay=datePicker_startDate.getDayOfMonth();
        startDate = startYear+ "-" + checkDigit(startMonth)  + "-" + checkDigit(startDay);
        return startDate;
    }

    public String getEndDate() {
        int endYear=datePicker_endDate.getYear();
        int endMonth=datePicker_endDate.getMonth() + 1;
        int endDay=datePicker_endDate.getDayOfMonth();
        endDate = endYear+ "-" + checkDigit(endMonth)  + "-" + checkDigit(endDay);
        return endDate;
    }


    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }
}