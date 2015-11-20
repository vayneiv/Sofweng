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
        to_ifl.putExtra("Price", budget);
        to_ifl.putExtra("Location", location);
        startActivity(to_ifl); //transfer activity
    }
    public void putCategory()
    {
        to_ifl.putExtra("Cafe", cat_1);
        to_ifl.putExtra("Buffet", cat_2);
        to_ifl.putExtra("Dessert", cat_3);
        to_ifl.putExtra("Bar", cat_4);
        to_ifl.putExtra("Grill", cat_5);
        to_ifl.putExtra("Lutong_Bahay", cat_6);
        to_ifl.putExtra("Fast_food", cat_7);
        to_ifl.putExtra("Veg", cat_8);
        to_ifl.putExtra("Fine_dining", cat_9);

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
                cat_1=to_filter.getBooleanExtra("Cafe", cat_1);
                cat_2=to_filter.getBooleanExtra("Buffet", cat_2);
                cat_3=to_filter.getBooleanExtra("Dessert",cat_3);
                cat_4=to_filter.getBooleanExtra("Bar",cat_4);
                cat_5=to_filter.getBooleanExtra("Grill",cat_5);
                cat_6=to_filter.getBooleanExtra("Lutong_Bahay",cat_6);
                cat_7=to_filter.getBooleanExtra("Fast_food",cat_7);
                cat_8=to_filter.getBooleanExtra("Veg",cat_8);
                cat_9=to_filter.getBooleanExtra("Fine_dining",cat_9);
                budget=to_filter.getIntExtra("Price",budget);
                location=to_filter.getIntExtra("Location",location);
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
