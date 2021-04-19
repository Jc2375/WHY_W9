package com.example.why_w9;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OrderToDeliverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtOrderName;
    private ItemClickListener itemClickListener;
    public OrderToDeliverViewHolder(View itemView) {
        super(itemView);
        txtOrderName=(TextView)itemView.findViewById(R.id.order_num2);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(), false);
    }


}
