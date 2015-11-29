package com.example.jerem.eatsmart;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class restau_choose extends AppCompatActivity {

    Button profile;
    ImageButton craving,starving,hungry;
    ImageView image;
    Intent resto_profile;
    Bundle extras;
    String userName,password;
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView resto_pack;
    int pack;
    ImageView packages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restau_choose);
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
        packages=(ImageView)findViewById(R.id.imageView7);
        resto_profile = new Intent(this,home_restaurant.class);
        pack=loginDataBaseAdapter.getrestapack(userName);

        if(pack==1){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.craving_pac, getApplicationContext().getTheme()));
            } else {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.craving_pac));
            }
        }
        else if(pack==2){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.hungry_pack, getApplicationContext().getTheme()));
            } else {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.hungry_pack));
            }
        }
        else if(pack==3){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.starving_pac, getApplicationContext().getTheme()));
            } else {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.starving_pac));
            }
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.craving_pac, getApplicationContext().getTheme()));
            } else {
                packages.setImageDrawable(getResources().getDrawable(R.drawable.craving_pac));
            }
        }
        craving = (ImageButton)findViewById(R.id.imageButton3);
        craving.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                pack=1;
                Toast.makeText(getApplicationContext(), "Package is Craving", Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    packages.setImageDrawable(getResources().getDrawable(R.drawable.craving_pac, getApplicationContext().getTheme()));
                } else {
                    packages.setImageDrawable(getResources().getDrawable(R.drawable.craving_pac));
                }
            }
        });
        starving = (ImageButton)findViewById(R.id.imageButton4);
        starving.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                pack=2;
                Toast.makeText(getApplicationContext(), "Package is Hungry", Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    packages.setImageDrawable(getResources().getDrawable(R.drawable.hungry_pack, getApplicationContext().getTheme()));
                } else {
                    packages.setImageDrawable(getResources().getDrawable(R.drawable.hungry_pack));
                }
            }
        });
        hungry = (ImageButton)findViewById(R.id.imageButton5);
        hungry.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                pack=3;
                Toast.makeText(getApplicationContext(), "Package is Starving", Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    packages.setImageDrawable(getResources().getDrawable(R.drawable.starving_pac, getApplicationContext().getTheme()));
                } else {
                    packages.setImageDrawable(getResources().getDrawable(R.drawable.starving_pac));
                }
            }
        });
    }
}
