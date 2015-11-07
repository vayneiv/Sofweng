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
    String userName, filter_entry_1;
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
        to_ifl.putExtra("Filter Entry 1", filter_entry_1);
        startActivity(to_ifl); //transfer activity
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
                filter_entry_1 = to_filter.getStringExtra("Filter Entry 1");
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
