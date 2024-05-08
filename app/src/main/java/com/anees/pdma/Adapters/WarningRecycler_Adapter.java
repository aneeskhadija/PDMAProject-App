package com.anees.pdma.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anees.pdma.R;
import com.anees.pdma.model.GetterSetter;

import java.util.ArrayList;

public class WarningRecycler_Adapter extends RecyclerView.Adapter<WarningRecycler_Adapter.WarningViewHolder>{

    private ArrayList<GetterSetter> listWarning;

    // Provide a reference to the views for each data item
    // provide access to all the views for a data item in a view holder
    class WarningViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_disaster_type, tv_location, tv_warning_level, tv_date, tv_time, tv_information;  //Fields of TextView

        private WarningViewHolder(View view)     //Constructor
        {
            super(view);
            //initializing all view objects
            tv_disaster_type = (TextView) view.findViewById(R.id.warning_disaster_tv);    //These fields are assigned to the ID's of warnings_list_row.xml file
            tv_location = (TextView) view.findViewById(R.id.warning_location_tv);
            tv_warning_level = (TextView) view.findViewById(R.id.warning_level_tv);
            tv_date = (TextView) view.findViewById(R.id.warning_date_tv);
            tv_time = (TextView) view.findViewById(R.id.warning_time_tv);
            tv_information = (TextView) view.findViewById(R.id.warning_information_tv);
        }
    }

    public WarningRecycler_Adapter(ArrayList<GetterSetter> listWarning) {
        this.listWarning = listWarning;
    }

    @Override       // each data item is just a string in this case
    public WarningViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.warnings_list_view, parent, false);   // set the view's size, margins, paddings and layout parameters
        return new WarningViewHolder(itemView);     // create a new view
    }

    @Override       // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(WarningViewHolder holder, int position) {
        //get element from dataset at this position
        //replace the contents of the view with that element
        GetterSetter warning = listWarning.get(position);      //Giving reference of Warning Model class and assigning it the value by getting the position of row from ArrayList known as listWarning
        holder.tv_disaster_type.setText(warning.getDisaster_type());        //Setting text in TextView fields by calling the setText method and getting values from Model class getMethod. holder holds the value in the fields
        holder.tv_location.setText(warning.getLocation());
        holder.tv_warning_level.setText(warning.getWarning_level());
        holder.tv_date.setText(warning.getDate());
        holder.tv_time.setText(warning.getTime());
        holder.tv_information.setText(warning.getMore_information());
    }

    @Override
    public int getItemCount() {
        return listWarning.size();  // Return the size of dataset (invoked by the layout manager)
    }

}
