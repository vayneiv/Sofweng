package com.example.jerem.eatsmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class home_restaurant extends AppCompatActivity {
    TextView welcMSG,description,location,cuisine,price;
    Bundle extras;
    String userName;
    LoginDataBaseAdapter loginDataBaseAdapter;
    ToggleButton desc;
    KeyListener editdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_restaurant);
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
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        welcMSG=new TextView(this);
        welcMSG=(TextView)findViewById(R.id.textView4);
        welcMSG.setText(loginDataBaseAdapter.getrestauname(userName));
        description=new TextView(this);
        description=(TextView)findViewById(R.id.textView13);
        description.setText(loginDataBaseAdapter.getrestaudesc(userName));
        cuisine=new TextView(this);
        cuisine=(TextView)findViewById(R.id.textView15);
        cuisine.setText("Not Implemented");
        price=new TextView(this);
        price=(TextView)findViewById(R.id.textView17);
        price.setText("Not Implemented");
        location=new TextView(this);
        location=(TextView)findViewById(R.id.textView18);
        location.setText(loginDataBaseAdapter.getrestauloc(userName));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_restaurant, menu);
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

