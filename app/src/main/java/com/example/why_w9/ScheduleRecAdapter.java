package com.example.why_w9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleRecAdapter extends RecyclerView.Adapter<ScheduleRecAdapter.scViewHolder> {
    ArrayList<ScheduleModel> scheduleList;
    Context context;

    public ScheduleRecAdapter(Context context,ArrayList<ScheduleModel>scheduleList){
        this.scheduleList = scheduleList;
        this.context = context;
    }



    @NonNull
    @Override
    public scViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate((R.layout.employee_schedule_card),parent,false);
        return new scViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull scViewHolder holder, int position) {
        ScheduleModel model = scheduleList.get(position);
        holder.name.setText(model.getName());
        holder.mondayTime.setText(model.getMondayTime());
        holder.tuesdayTime.setText(model.getTuesdayTime());
        holder.wednesdayTime.setText(model.getWednesdayTime());
        holder.thursdayTime.setText(model.getThursdayTime());
        holder.fridayTime.setText(model.getFridayTime());
        holder.saturdayTime.setText(model.getSaturdayTime());
        holder.sundayTime.setText(model.getSundayTime());

    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class scViewHolder extends RecyclerView.ViewHolder{

        TextView name,mondayTime,tuesdayTime,
        wednesdayTime,thursdayTime,fridayTime,
        saturdayTime,sundayTime;

        public scViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employeeNameTV);
            mondayTime = itemView.findViewById(R.id.mondayTimeTV);
            tuesdayTime = itemView.findViewById(R.id.tuesdayTimeTV);
            wednesdayTime = itemView.findViewById(R.id.wednesdayTimeTV);
            thursdayTime = itemView.findViewById(R.id.thurdayTimeTV);
            fridayTime = itemView.findViewById(R.id.fridayTimeTV);
            saturdayTime = itemView.findViewById(R.id.saturdayTimeTV);
            sundayTime = itemView.findViewById(R.id.sundayTimeTV);
        }
    }
}
