package com.example.jerem.eatsmart;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class home_customer extends AppCompatActivity {
    TextView welcMSG;
    Bundle extras;
    String userName;
    boolean cat_1;
    boolean cat_2;
    boolean cat_3;
    boolean cat_4;
    boolean cat_5;
    boolean cat_6;
    boolean cat_7;
    boolean cat_8;
    boolean cat_9;
    int budget;
    int location;
    Intent to_ifl, to_filter, to_search;
    static int requestCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);
        requestCode = 1;
        if (savedInstanceState == null)
        {
            //fetching extra data passed with intents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
            }
            else
            {
                userName= extras.getString("Username");
            }
        }
        welcMSG=new TextView(this);
        welcMSG=(TextView)findViewById(R.id.textView4);
        welcMSG.setText("Welcome " + userName);
    }
    public void to_ifl(View v)
    {
        to_ifl= new Intent(this, im_feeling_lucky.class);
        to_ifl.putExtra("Username", userName);
        putCategory();
        to_ifl.putExtra("Budget", budget);
        to_ifl.putExtra("Location", location);
        startActivity(to_ifl); //transfer activity
    }
    public void putCategory()
    {
        to_ifl.putExtra("Category1", cat_1);
        to_ifl.putExtra("Category2", cat_2);
        to_ifl.putExtra("Category3", cat_3);
        to_ifl.putExtra("Category4", cat_4);
        to_ifl.putExtra("Category5", cat_5);
        to_ifl.putExtra("Category6", cat_6);
        to_ifl.putExtra("Category7", cat_7);
        to_ifl.putExtra("Category8", cat_8);
        to_ifl.putExtra("Category9", cat_9);

    }
    public void to_filter(View v)
    {
        to_filter= new Intent(this, filter.class);
        to_filter.putExtra("Username", userName);
        startActivityForResult(to_filter, requestCode); //transfer activity
    }
    public void to_search(View v)
    {
        to_search= new Intent(this, search.class);
        to_search.putExtra("Username", userName);
        startActivity(to_search); //transfer activity
    }
    @Override
    protected void onActivityResult(int requestCode2, int resultCode, Intent to_filter)
    {
        if(requestCode2 == requestCode)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                cat_1=to_filter.getBooleanExtra("Cafe", false);
                cat_2=to_filter.getBooleanExtra("Buffet", false);
                cat_3=to_filter.getBooleanExtra("Dessert",false);
                cat_4=to_filter.getBooleanExtra("Bar",false);
                cat_5=to_filter.getBooleanExtra("Grill",false);
                cat_6=to_filter.getBooleanExtra("Lutong_Bahay",false);
                cat_7=to_filter.getBooleanExtra("Fast_food",false);
                cat_8=to_filter.getBooleanExtra("Veg",false);
                cat_9=to_filter.getBooleanExtra("Fine_dining",false);
                budget=to_filter.getIntExtra("Price",0);
                location=to_filter.getIntExtra("Location",0);
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_customer, menu);
        return true;
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
