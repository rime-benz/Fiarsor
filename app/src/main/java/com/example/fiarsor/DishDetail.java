package com.example.fiarsor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fiarsor.Database.Database;
import com.example.fiarsor.Model.Order;
import com.example.fiarsor.Model.dish;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DishDetail extends AppCompatActivity {

    TextView dish_name,dish_price,dish_description;
    ImageView dish_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String dishId="";
    FirebaseDatabase database;
    DatabaseReference dishes;

    dish currentDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);

        database =  FirebaseDatabase.getInstance();
        dishes = database.getReference("Dishes");

        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart =  (FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        dishId,
                        currentDish.getName(),
                        currentDish.getPrice(),
                        currentDish.getDiscount(),
                        numberButton.getNumber()
                ));
                Toast.makeText(DishDetail.this,"Added to Cart",Toast.LENGTH_LONG).show();
            }
        });

        dish_description = (TextView)findViewById(R.id.dish_description);
        dish_name = (TextView)findViewById(R.id.dish_name);
        dish_price = (TextView)findViewById(R.id.dish_price);
        dish_image = (ImageView)findViewById(R.id.img_dish);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);

        if (getIntent() != null)
            dishId = getIntent().getStringExtra("DishId");
        if (!dishId.isEmpty()){
            getDetailDish(dishId);
        }

    }

    private void getDetailDish(String dishId) {
        dishes.child(dishId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentDish= dataSnapshot.getValue(dish.class);

                Picasso.get().load(currentDish.getImage()).into(dish_image);

                collapsingToolbarLayout.setTitle(currentDish.getName());

                dish_price.setText(currentDish.getPrice());
                dish_name.setText(currentDish.getName());
                dish_description.setText(currentDish.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
