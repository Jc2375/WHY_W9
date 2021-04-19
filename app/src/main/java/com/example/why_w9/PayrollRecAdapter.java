package com.example.why_w9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PayrollRecAdapter extends RecyclerView.Adapter<PayrollRecAdapter.payrollViewHolder> {
    ArrayList<PayrollModel> payrollList;
    Context context;

    public PayrollRecAdapter(Context context,ArrayList<PayrollModel>payrollList){
        this.payrollList = payrollList;
        this.context = context;
    }



    @NonNull
    @Override
    public payrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate((R.layout.payroll_card),parent,false);
        return new payrollViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull payrollViewHolder holder, int position) {
        PayrollModel model = payrollList.get(position);
        holder.name.setText(model.getName());
        holder.role.setText(model.getRole());
        holder.weeklyPay.setText(model.getWeeklyPay());
        holder.hourlyRate.setText(model.getHourlyRate());
        holder.weeklyHours.setText(model.getWeeklyHours());
    }

    @Override
    public int getItemCount() {
        return payrollList.size();
    }

    public static class payrollViewHolder extends RecyclerView.ViewHolder{

        TextView name,role,hourlyRate,weeklyPay,weeklyHours;
        public payrollViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameFieldTVp);
            role = itemView.findViewById(R.id.employeeRoleTVp);
            weeklyPay = itemView.findViewById(R.id.employeeWeeklyPayTVp);
            hourlyRate = itemView.findViewById(R.id.employeeHourlyRateTVp);
            weeklyHours = itemView.findViewById(R.id.employeeWeeklyHoursTVp);
        }
    }
}
