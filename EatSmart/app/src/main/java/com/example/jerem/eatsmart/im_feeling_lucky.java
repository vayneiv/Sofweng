package com.example.jerem.eatsmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class im_feeling_lucky extends AppCompatActivity {
    Bundle extras;
    String userName, filter_entry_1;
    TextView user, entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_feeling_lucky);
        if (savedInstanceState == null)
        {
            //fetching extra data passed with intents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                filter_entry_1 = null;
            }
            else
            {
                userName= extras.getString("Username");
                filter_entry_1 = extras.getString("Filter Entry 1");
            }
        }
        user = (TextView) findViewById(R.id.textView34);
        entry = (TextView) findViewById(R.id.textView35);
        user.setText("User: " + userName);
        entry.setText("Entry: " + filter_entry_1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_im_feeling_lucky, menu);
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
