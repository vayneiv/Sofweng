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
    TextView welcMSG;
    EditText description,location,cuisine,price;
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
        description=new EditText(this);
        description=(EditText)findViewById(R.id.editText);
        description.setText(loginDataBaseAdapter.getrestaudesc(userName));
        editdesc=description.getKeyListener();
        description.setKeyListener(null);
        cuisine=new EditText(this);
        cuisine=(EditText)findViewById(R.id.editText3);
        cuisine.setText("Not Implemented");
        cuisine.setFocusable(false);
        cuisine.setClickable(false);
        price=new EditText(this);
        price=(EditText)findViewById(R.id.editText4);
        price.setText("Not Implemented");
        price.setFocusable(false);
        price.setClickable(false);
        location=new EditText(this);
        location=(EditText)findViewById(R.id.editText5);
        location.setText(loginDataBaseAdapter.getrestauloc(userName));
        location.setFocusable(false);
        location.setClickable(false);
        desc=new ToggleButton(this);
        ToggleButton desc = (ToggleButton) findViewById(R.id.toggleButton);
        desc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loginDataBaseAdapter.updaterestaudesc(userName,description.getText().toString());
                if (isChecked) {
                    description.setKeyListener(editdesc);
                } else if(!isChecked) {
                    description.setKeyListener(null);
                }
            }
        });
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

