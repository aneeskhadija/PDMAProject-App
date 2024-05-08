package com.anees.pdma.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anees.pdma.R;
import com.anees.pdma.model.GetterSetter;

import java.util.List;

public class DailyRiverFlowReport_Adapter extends RecyclerView.Adapter<DailyRiverFlowReport_Adapter.RiverFlowReportViewHolder> {

    private List<GetterSetter> reportsArrayList;

    class RiverFlowReportViewHolder extends RecyclerView.ViewHolder {
        private TextView ReportTitleTextView;

        private RiverFlowReportViewHolder(View view)     //Constructor
        {
            super(view);
            ReportTitleTextView = (TextView) view.findViewById(R.id.report_name_tv);
        }
    }

    public DailyRiverFlowReport_Adapter(List<GetterSetter> reportsArrayList, Context context) {
        super();
        this.reportsArrayList = reportsArrayList;
    }

    @Override
    public RiverFlowReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.river_flow_reports_list_view, parent, false);
        RiverFlowReportViewHolder viewHolder = new RiverFlowReportViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RiverFlowReportViewHolder holder, int position) {
        GetterSetter riverFlowReport=reportsArrayList.get(position);

        holder.ReportTitleTextView.setText(riverFlowReport.getReport_name());
    }

    @Override
    public int getItemCount() {
        return reportsArrayList.size();  // Return the size of dataset (invoked by the layout manager)
    }

}
