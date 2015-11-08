package com.example.jerem.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class search_result extends AppCompatActivity {
    Bundle extras;
    String userName, restauName;
    TextView display_user, display_restau;
    Intent to_rate_and_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        display_user = (TextView) findViewById(R.id.textView10);
        display_restau = (TextView) findViewById(R.id.textView23);
        if (savedInstanceState == null)
        {
            //fetching extra data passed with intents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                restauName = null;
            }
            else
            {
                userName= extras.getString("Username");
                restauName= extras.getString("Restaurant Name");
            }
        }
        display_user.setText("User:" + userName);
        display_restau.setText("Restaurant Name: " + "\n" + restauName);
    }
    public void to_rate_and_comment(View v)
    {
        to_rate_and_comment = new Intent(this, rate_and_comment.class);
        to_rate_and_comment.putExtra("Username", userName);
        to_rate_and_comment.putExtra("Restaurant Name", restauName);
        startActivity(to_rate_and_comment);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
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
