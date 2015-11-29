package com.example.jerem.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class search extends AppCompatActivity {
    Bundle extras;
    String userName;
    EditText entry;
    Intent to_search_result;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String searchEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
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
        entry = (EditText) findViewById(R.id.editText);
    }
    public void to_search_result(View v)
    {
        searchEntry = entry.getText().toString();
        if(loginDataBaseAdapter.getrestauname(searchEntry)=="NOT EXIST"){
            Toast.makeText(getApplicationContext(), "No Results", Toast.LENGTH_LONG).show();
        }
        to_search_result= new Intent(this, search_result.class);
        to_search_result.putExtra("Username", userName);
        to_search_result.putExtra("Restaurant Name", searchEntry);
        startActivity(to_search_result); //transfer activity
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
