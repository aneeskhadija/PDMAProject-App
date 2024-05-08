package com.anees.pdma.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anees.pdma.R;
import com.anees.pdma.model.GetterSetter;

import java.util.ArrayList;
import java.util.List;

public class WeatherAlertAdvisor_Adapter extends RecyclerView.Adapter<WeatherAlertAdvisor_Adapter.WeatherReportViewHolder> {

    private List<GetterSetter> reportsArrayList;

    //Constructor
    public WeatherAlertAdvisor_Adapter(ArrayList<GetterSetter> reportsArrayList) {
        super();
        this.reportsArrayList = reportsArrayList;
    }

    //Create view Holder
    @Override
    public WeatherReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_alert_advisor_view, parent, false);
        WeatherReportViewHolder viewHolder = new WeatherReportViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherReportViewHolder holder, int position) {
        GetterSetter weatherDailyReport = reportsArrayList.get(position);

        holder.txt_weather_alert_advisor.setText(weatherDailyReport.getReport_name());
    }

    @Override
    public int getItemCount() {
        return reportsArrayList.size();  // Return the size of dataset (invoked by the layout manager)
    }

    // Holder Class
    class WeatherReportViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_weather_alert_advisor;

        private WeatherReportViewHolder(View view)     //Constructor
        {
            super(view);
            txt_weather_alert_advisor = view.findViewById(R.id.id_weather_alert_advisor);
        }
    }
}
