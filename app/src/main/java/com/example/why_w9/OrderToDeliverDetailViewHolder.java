package com.example.why_w9;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OrderToDeliverDetailViewHolder extends RecyclerView.ViewHolder {
    public TextView txtOrderItem1,txtOrderItem2;
    private ItemClickListener itemClickListener;
    public OrderToDeliverDetailViewHolder(View itemView) {
        super(itemView);
        txtOrderItem1=(TextView)itemView.findViewById(R.id.order_detail_item1_todeliver);
        txtOrderItem2=(TextView)itemView.findViewById(R.id.order_detail_item2_todeliver);
    }



}