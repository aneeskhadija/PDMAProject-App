package com.anees.pdma.FragmentClasses;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.anees.pdma.R;

import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static String floodReportDate;
    private DatePicker datePicker;

    public interface DateDialogListener {
        void onFinishDialog(Date date);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);
        datePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        return new AlertDialog.Builder (getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = datePicker.getYear();
                                int mon = datePicker.getMonth() + 1;
                                int day = datePicker.getDayOfMonth();
                                //Date date = new GregorianCalendar(year+"-"+checkDigit(mon)+"-"+day);
                                Date date = new GregorianCalendar(year,mon,day).getTime();
                                floodReportDate=year+ "-" + checkDigit(mon)  + "-" + checkDigit(day);;
                                DateDialogListener activity = (DateDialogListener) getActivity();
                                activity.onFinishDialog(date);
                                dismiss();
                            }
                        })
                .create();
    }

    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }

}
