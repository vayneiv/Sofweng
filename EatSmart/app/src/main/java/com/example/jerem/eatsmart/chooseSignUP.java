package com.example.jerem.eatsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class chooseSignUP extends AppCompatActivity {

    Intent consumerLogin;
    Intent restoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void consumer(View v){
        consumerLogin= new Intent(this, SignUPActivity.class); //initialize new activity
        startActivity(consumerLogin); //transfer activity
        finish();
    }

    public void restaurant(View v){
        restoLogin= new Intent(this, restaurantSignUp.class); //initialize new activity
        startActivity(restoLogin); //transfer activity
        finish();
    }

}
