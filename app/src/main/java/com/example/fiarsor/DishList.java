package com.example.fiarsor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fiarsor.Interface.ItemClickListener;
import com.example.fiarsor.Model.dish;
import com.example.fiarsor.ViewHolder.DishViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DishList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference DishList;

    String categoryId="";
    FirebaseRecyclerAdapter<dish, DishViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        database = FirebaseDatabase.getInstance();
        DishList = database.getReference("Dishes");


        recyclerView = (RecyclerView)findViewById(R.id.recycler_dish);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null){
            loadListDish(categoryId);
        }
    }

    private void loadListDish(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<dish, DishViewHolder>(dish.class,R.layout.dish_item,DishViewHolder.class,DishList.orderByChild("MenuId").equalTo(categoryId)){
            @Override
            protected void populateViewHolder(DishViewHolder dishViewHolder, dish dish, int i) {
                dishViewHolder.dish_name.setText(dish.getName());
                Picasso.get().load(dish.getImage()).into(dishViewHolder.dish_image);

                final dish local = dish;
                dishViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                       // Toast.makeText(DishList.this, ""+local.getName(), Toast.LENGTH_LONG).show();

                        Intent dishDetail = new Intent(DishList.this,DishDetail.class);
                        dishDetail.putExtra("DishId",adapter.getRef(position).getKey());
                        startActivity(dishDetail);
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }
}
