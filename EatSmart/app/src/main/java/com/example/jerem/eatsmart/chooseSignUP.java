package com.example.jerem.eatsmart;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class chooseSignUP extends AppCompatActivity {

    Intent consumerLogin;
    Intent restoLogin;
    ImageButton customer, restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sign_up);
        consumerLogin= new Intent(this, SignUPActivity.class);
        restoLogin= new Intent(this, restaurantSignUp.class);

        TextView txt = (TextView) findViewById(R.id.textView59);
        Typeface font = Typeface.createFromAsset(getAssets(), "Kenzo Regular.otf");
        txt.setTypeface(font);
        TextView txt1 = (TextView) findViewById(R.id.textView12);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt1.setTypeface(font1);
        customer = (ImageButton)findViewById(R.id.button3);
        customer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(consumerLogin); //transfer activity
                finish();
            }
        });
        restaurant = (ImageButton)findViewById(R.id.button4);
        restaurant.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(restoLogin); //transfer activity
                finish();
            }
        });

    }


}
