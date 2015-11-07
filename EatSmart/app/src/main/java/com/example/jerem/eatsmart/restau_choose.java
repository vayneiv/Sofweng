package com.example.jerem.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        resto_profile = new Intent(this,home_restaurant.class);
        pack=loginDataBaseAdapter.getrestapack(userName);
        profile = (Button)findViewById(R.id.button9);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resto_profile.putExtra("Username", userName);
                resto_profile.putExtra("Password", password);
                resto_profile.putExtra("package",pack);
                startActivity(resto_profile); //transfer activity
                finish();
            }
        });
        resto_pack=(TextView)findViewById(R.id.textView27);
        if(pack==1){
            resto_pack.setText("CRAVING");
        }else if(pack==2){
            resto_pack.setText("STARVING");
        }else if(pack==3){
            resto_pack.setText("HUNGRY");
        }else {
            resto_pack.setText("ERROR");
        }
        craving = (ImageButton)findViewById(R.id.imageButton3);
        craving.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                pack=1;
            }
        });
        starving = (ImageButton)findViewById(R.id.imageButton4);
        starving.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pack=2;
            }
        });
        hungry = (ImageButton)findViewById(R.id.imageButton5);
        hungry.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                pack=3;
            }
        });
    }
}
