package com.example.why_w9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String Tag="RecyclerView";
    private Context mContext;
    private ArrayList<food_item> food_items;


    public RecyclerAdapter(Context mContext, ArrayList<food_item> food_items) {
        this.mContext = mContext;
        this.food_items = food_items;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(food_items.get(position).getName());
        Glide.with(mContext).load(food_items.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return  food_items.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
