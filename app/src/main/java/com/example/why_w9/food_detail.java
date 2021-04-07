package com.example.why_w9;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import android.view.View;
import Database.*;

import android.content.Intent;


public class food_detail extends AppCompatActivity {
    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String foodId;
    FirebaseDatabase database;
    DatabaseReference foods;

    Food currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detail);
        database= FirebaseDatabase.getInstance();
        foods= database.getReference("Food");

        numberButton=(ElegantNumberButton)findViewById(R.id.number_button);
        btnCart=(FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()
                ));
                Toast.makeText(food_detail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        food_description = (TextView)findViewById(R.id.food_description);
        food_name=(TextView)findViewById(R.id.food_name);
        food_price=(TextView)findViewById(R.id.food_price);
        food_image=(ImageView)findViewById(R.id.img_food);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        if(getIntent() !=null)
        {
            foodId=getIntent().getStringExtra("FoodId");
            if(!foodId.isEmpty())
            {
                getDetailFood(foodId);
            }
        }
    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class); //overwritten as per part 5 video instruction
                Picasso.get().load(currentFood.getImage()).into(food_image); //overwritten as per part 5 video instruction
                collapsingToolbarLayout.setTitle(currentFood.getName()); //overwritten as per part 5 video instruction
                food_price.setText(currentFood.getPrice()); //overwritten as per part 5 video instruction
                food_name.setText(currentFood.getName()); //overwritten as per part 5 video instruction
                food_description.setText(currentFood.getDescription()); //overwritten as per part 5 video instruction
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
