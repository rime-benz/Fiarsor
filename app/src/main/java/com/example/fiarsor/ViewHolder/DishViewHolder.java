package com.example.fiarsor.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fiarsor.Interface.ItemClickListener;
import com.example.fiarsor.R;


public class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView dish_name;
    public ImageView dish_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DishViewHolder(@NonNull View itemView) {
        super(itemView);

        dish_name = (TextView)itemView.findViewById(R.id.dish_name);
        dish_image = (ImageView)itemView.findViewById(R.id.dish_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onclick(v,getAdapterPosition(),false);

    }
}
