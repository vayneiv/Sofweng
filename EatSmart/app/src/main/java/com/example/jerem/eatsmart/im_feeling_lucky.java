package com.example.jerem.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class im_feeling_lucky extends AppCompatActivity {
    Bundle extras;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String userName, restauName;
    boolean cat_1;
    boolean cat_2;
    boolean cat_3;
    boolean cat_4;
    boolean cat_5;
    boolean cat_6;
    boolean cat_7;
    boolean cat_8;
    boolean cat_9;
    boolean no_cat;
    int budget;
    int location;
    TextView Resto_name;
    Intent to_search_result;
    int budget_db;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_feeling_lucky);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        budget_db = 0;
        restauName = null;
        if (savedInstanceState == null) {
            //fetching extra data passed with intents in a Bundle type variable
            extras = getIntent().getExtras();
            if (extras == null) {
                cat_1 = false;
                cat_2 = false;
                cat_3 = false;
                cat_4 = false;
                cat_5 = false;
                cat_6 = false;
                cat_7 = false;
                cat_8 = false;
                cat_9 = false;
                budget = 0;
                location = 0;
            } else {
                cat_1 = extras.getBoolean("Cafe", cat_1);
                cat_2 = extras.getBoolean("Buffet", cat_2);
                cat_3 = extras.getBoolean("Dessert", cat_3);
                cat_4 = extras.getBoolean("Bar", cat_4);
                cat_5 = extras.getBoolean("Grill", cat_5);
                cat_6 = extras.getBoolean("Lutong_Bahay", cat_6);
                cat_7 = extras.getBoolean("Fast_food", cat_7);
                cat_8 = extras.getBoolean("Veg", cat_8);
                cat_9 = extras.getBoolean("Fine_dining", cat_9);
                budget = extras.getInt("Price", budget);
                location = extras.getInt("Location", location);
            }
        }
        restauName=IFL();
        Resto_name=new TextView(this);
        Resto_name=(TextView)findViewById(R.id.textView34);
        image=(ImageView)findViewById(R.id.imageView13);
        if(restauName.equals("None")){
            Resto_name.setText("None");
        }else {
            Resto_name.setText(loginDataBaseAdapter.getrestauname(restauName));
            image.invalidate();
            image.setImageBitmap(loginDataBaseAdapter.getimage(restauName));
        }
    }
    public void to_search_result(View v)
    {
        to_search_result = new Intent(this, search_result.class);
        to_search_result.putExtra("Username", userName);
        to_search_result.putExtra("Restaurant Name", restauName);
        startActivity(to_search_result);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_im_feeling_lucky, menu);
        return true;
    }
    public String IFL() {
        ArrayList<String> choice;
        String result;
        if ((!cat_1)&&(!cat_2)&&(!cat_3)&&(!cat_4)&&(!cat_5)&&(!cat_6)&&(!cat_7)&&(!cat_8)&&(!cat_9)){
            no_cat=true;
        }
        else{
            no_cat=false;
        }
        Random r = new Random();
        choice = loginDataBaseAdapter.getList(cat_1, cat_2, cat_3, cat_4, cat_5, cat_6, cat_7, cat_8, cat_9,no_cat, location, budget);
        if (choice.equals( "None")){
            result = "None";
        }else
        {
            result = choice.get(r.nextInt(choice.size() ));
        }
        return result;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}