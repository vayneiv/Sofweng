package com.example.jerem.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class true_home_restau extends AppCompatActivity {

    Button profile,packages;
    Intent resto_profile,resto_packages;
    Bundle extras;
    String userName,password;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_home_restau);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        if (savedInstanceState == null)
        {
            //fetching extra data passed with intents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                password=null;
            }
            else
            {
                userName= extras.getString("Username");
                password=extras.getString("password");
            }
        }
        resto_profile = new Intent(this,home_restaurant.class);
        profile = (Button)findViewById(R.id.button16);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resto_profile.putExtra("Username", userName);
                resto_profile.putExtra("Password", password);
                startActivity(resto_profile); //transfer activity
                finish();
            }
        });
        resto_packages=new Intent(this,restau_choose.class);
        packages = (Button)findViewById(R.id.button17);
        packages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resto_packages.putExtra("Username", userName);
                resto_packages.putExtra("Password", password);
                startActivity(resto_packages); //transfer activity
                finish();
            }
        });
    }
}
