package com.anees.pdma.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anees.pdma.R;
import com.anees.pdma.model.GetterSetter;

import java.util.ArrayList;

public class IncidentRecycler_Adapter extends RecyclerView.Adapter<IncidentRecycler_Adapter.IncidentViewHolder>{

    private ArrayList<GetterSetter> listIncident;

    // Provide a reference to the views for each data item
    // provide access to all the views for a data item in a view holder
    public class IncidentViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tv_disaster_type, tv_location, tv_date, tv_time, tv_loses, tv_injuries, tv_houses_damaged, tv_affected_road, tv_alternate_road, tv_information, tv_report_issued;  //Fields of TextView

        //Constructor
        private IncidentViewHolder(View view) {
            super(view);
            //initializing all view objects
            tv_disaster_type = (TextView) view.findViewById(R.id.incident_disaster_tv);       //These fields are assigned to the ID's of incidents_list_row.xml file
            tv_location = (TextView) view.findViewById(R.id.incident_location_tv);
            tv_date = (TextView) view.findViewById(R.id.incident_date_tv);
            tv_time = (TextView) view.findViewById(R.id.incident_time_tv);
            tv_loses = (TextView) view.findViewById(R.id.incident_loses_tv);
            tv_injuries = (TextView) view.findViewById(R.id.incident_injuries_tv);
            tv_houses_damaged = (TextView) view.findViewById(R.id.incident_houses_damaged_tv);
            tv_affected_road = (TextView) view.findViewById(R.id.incident_affected_road_tv);
            tv_alternate_road = (TextView) view.findViewById(R.id.incident_alternate_road_tv);
            tv_information = (TextView) view.findViewById(R.id.incident_information_tv);
            tv_report_issued = (TextView) view.findViewById(R.id.report_issued_tv);
        }
    }

    public IncidentRecycler_Adapter(ArrayList<GetterSetter> listIncident) {
        this.listIncident = listIncident;
    }

    @Override   // each data item is just a string in this case
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.incidents_list_view, parent, false);  // set the view's size, margins, paddings and layout parameters
        return new IncidentViewHolder(itemView);        // create a new view
    }

    @Override   // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(IncidentViewHolder holder, int position) {
        //get element from dataset at this position
        //replace the contents of the view with that element
        GetterSetter incident = listIncident.get(position);   //Giving reference of Incident Model class and assigning it the value by getting the position of row from ArrayList known as listIncident
        holder.tv_disaster_type.setText(incident.getDisaster_type());   //Setting text in TextView fields by calling the setText method and getting values from Model class getMethod. holder holds the value in the fields
        holder.tv_location.setText(incident.getLocation());
        holder.tv_date.setText(incident.getDate());
        holder.tv_time.setText(incident.getTime());
        holder.tv_loses.setText(incident.getLoses());
        holder.tv_injuries.setText(incident.getInjuries());
        holder.tv_houses_damaged.setText(incident.getHouses_damaged());
        holder.tv_affected_road.setText(incident.getAffected_road());
        holder.tv_alternate_road.setText(incident.getAlternate_road());
        holder.tv_information.setText(incident.getMore_information());
        holder.tv_report_issued.setText(incident.getReport_issued());
    }

    @Override
    public int getItemCount() {
        return listIncident.size(); // Return the size of dataset (invoked by the layout manager)
    }


}
