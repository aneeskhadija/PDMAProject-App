package com.anees.pdma.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anees.pdma.R;
import com.anees.pdma.model.GetterSetter;

import java.util.ArrayList;

public class ReliefActivitiesRecycler_Adapter extends RecyclerView.Adapter<ReliefActivitiesRecycler_Adapter.ReliefActivityViewHolder> {

    private ArrayList<GetterSetter> listReliefActivities;

    // Provide a reference to the views for each data item
    // provide access to all the views for a data item in a view holder
    public class ReliefActivityViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tv_disaster, tv_location, tv_date, tv_food_packages, tv_wheat_flours, tv_pulses, tv_tents, tv_blankets, tv_life_jackets, tv_aqua_tablets, tv_gas_cylinders, tv_mats, tv_mosquito_nets, tv_water_coolers, tv_buckets, tv_pillows, tv_quilts, tv_kitchen_sets, tv_other_items, tv_camps, tv_coverage, tv_report_issued;  //Fields of TextView

        //Constructor
        private ReliefActivityViewHolder(View view) {
            super(view);
            //initializing all view objects
            tv_disaster = (TextView) view.findViewById(R.id.relief_activity_disaster_tv);       //These fields are assigned to the ID's of relief_activity_list_row.xml file
            tv_location = (TextView) view.findViewById(R.id.relief_activity_location_tv);
            tv_date = (TextView) view.findViewById(R.id.relief_activity_date_tv);
            tv_food_packages = (TextView) view.findViewById(R.id.food_packages_tv);
            tv_wheat_flours = (TextView) view.findViewById(R.id.wheat_flours_tv);
            tv_pulses = (TextView) view.findViewById(R.id.pulses_tv);
            tv_tents = (TextView) view.findViewById(R.id.tents_tv);
            tv_blankets = (TextView) view.findViewById(R.id.blankets_tv);
            tv_life_jackets = (TextView) view.findViewById(R.id.life_jackets_tv);
            tv_aqua_tablets = (TextView) view.findViewById(R.id.aqua_tablets_tv);
            tv_gas_cylinders = (TextView) view.findViewById(R.id.gas_cylinders_tv);
            tv_mats = (TextView) view.findViewById(R.id.mats_tv);
            tv_mosquito_nets = (TextView) view.findViewById(R.id.mosquito_nets_tv);
            tv_water_coolers = (TextView) view.findViewById(R.id.water_coolers_tv);
            tv_buckets = (TextView) view.findViewById(R.id.buckets_tv);
            tv_pillows = (TextView) view.findViewById(R.id.pillows_tv);
            tv_quilts = (TextView) view.findViewById(R.id.quilts_tv);
            tv_kitchen_sets = (TextView) view.findViewById(R.id.kitchen_sets_tv);
            tv_other_items = (TextView) view.findViewById(R.id.other_items_tv);
            tv_camps = (TextView) view.findViewById(R.id.camps_tv);
            tv_coverage = (TextView) view.findViewById(R.id.coverage_tv);
            tv_report_issued = (TextView) view.findViewById(R.id.report_issued_tv);
        }
    }

    public ReliefActivitiesRecycler_Adapter(ArrayList<GetterSetter> listReliefActivities) {
        this.listReliefActivities = listReliefActivities;
    }

    //override methods of RecyclerView.Adapter Class
    @Override   // each data item is just a string in this case
    public ReliefActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.relief_activities_list_view, parent, false);  // set the view's size, margins, paddings and layout parameters
        return new ReliefActivityViewHolder(itemView);        // create a new view
    }

    @Override       // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(ReliefActivityViewHolder holder, int position) {
        //get element from dataset at this position
        //replace the contents of the view with that element
        GetterSetter reliefActivity = listReliefActivities.get(position);   //Giving reference of Incident Model class and assigning it the value by getting the position of row from ArrayList known as listIncident
        holder.tv_disaster.setText(reliefActivity.getDisaster());   //Setting text in TextView fields by calling the setText method and getting values from Model class getMethod. holder holds the value in the fields
        holder.tv_location.setText(reliefActivity.getLocation());
        holder.tv_date.setText(reliefActivity.getDate());
        holder.tv_food_packages.setText(String.valueOf(reliefActivity.getFood_packages()));
        holder.tv_wheat_flours.setText(String.valueOf(reliefActivity.getWheat_flours()));
        holder.tv_pulses.setText(String.valueOf(reliefActivity.getPulses()));
        holder.tv_tents.setText(String.valueOf(reliefActivity.getTents()));
        holder.tv_blankets.setText(String.valueOf(reliefActivity.getBlankets()));
        holder.tv_life_jackets.setText(String.valueOf(reliefActivity.getLife_jackets()));
        holder.tv_aqua_tablets.setText(String.valueOf(reliefActivity.getAqua_tablets()));
        holder.tv_gas_cylinders.setText(String.valueOf(reliefActivity.getGas_cylinders()));
        holder.tv_mats.setText(String.valueOf(reliefActivity.getMats()));
        holder.tv_mosquito_nets.setText(String.valueOf(reliefActivity.getMosquito_nets()));
        holder.tv_water_coolers.setText(String.valueOf(reliefActivity.getWater_coolers()));
        holder.tv_buckets.setText(String.valueOf(reliefActivity.getBuckets()));
        holder.tv_pillows.setText(String.valueOf(reliefActivity.getPillows()));
        holder.tv_quilts.setText(String.valueOf(reliefActivity.getQuilts()));
        holder.tv_kitchen_sets.setText(String.valueOf(reliefActivity.getKitchen_sets()));
        holder.tv_other_items.setText(String.valueOf(reliefActivity.getOther_items()));
        holder.tv_camps.setText(String.valueOf(reliefActivity.getCamps()));
        holder.tv_coverage.setText(reliefActivity.getCoverage());
        holder.tv_report_issued.setText(reliefActivity.getReport_issued());
    }

    @Override
    public int getItemCount() {
        return listReliefActivities.size(); // Return the size of dataset (invoked by the layout manager)
    }

}
