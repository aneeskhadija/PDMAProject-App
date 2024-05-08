package com.anees.pdma.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anees.pdma.R;
import com.anees.pdma.model.GetterSetter;

import java.util.ArrayList;

public class DailyFloodReport_Adapter extends RecyclerView.Adapter<DailyFloodReport_Adapter.FloodReportViewHolder>  {

    private ArrayList<GetterSetter> listFloodReport;

    //View Holder Class
    class FloodReportViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_riverName, tv_location, tv_flowStatus, tv_date, tv_time, tv_remarks, tv_dischargeCusecs;  //Fields of TextView
        //Constructor
        private FloodReportViewHolder(View view) {
            super(view);
            //initializing all view objects
            tv_riverName = (TextView) view.findViewById(R.id.river_name_tv);    //These fields are assigned to the ID's of flood_reports_list_row.xml file
            tv_location = (TextView) view.findViewById(R.id.location_tv);
            tv_date = (TextView) view.findViewById(R.id.date_tv);
            tv_time = (TextView) view.findViewById(R.id.time_tv);
            tv_dischargeCusecs = (TextView) view.findViewById(R.id.discharge_tv);
            tv_flowStatus = (TextView) view.findViewById(R.id.flow_status_tv);
            tv_remarks = (TextView) view.findViewById(R.id.remarks_tv);
        }
    }

    //View Constructor
    public DailyFloodReport_Adapter(ArrayList<GetterSetter> listFloodReport) {
        this.listFloodReport = listFloodReport;
    }

    @Override
    public FloodReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_flood_report_list_row, parent, false);   // set the view's size, margins, paddings and layout parameters
        return new FloodReportViewHolder(itemView);     // create a new view
    }

    @Override
    public void onBindViewHolder(FloodReportViewHolder holder, int position) {
        GetterSetter floodReport = listFloodReport.get(position);
        holder.tv_riverName.setText(floodReport.getRiver_name());
        holder.tv_location.setText(floodReport.getLocation());
        holder.tv_date.setText(floodReport.getDate());
        holder.tv_time.setText(floodReport.getTime());
        holder.tv_dischargeCusecs.setText(floodReport.getDischarge_cusecs());
        holder.tv_flowStatus.setText(floodReport.getFlow_status());
        holder.tv_remarks.setText(floodReport.getRemarks());
    }

    @Override
    public int getItemCount() {
        // Return the size of dataset (invoked by the layout manager)
        return listFloodReport.size();
    }
}
