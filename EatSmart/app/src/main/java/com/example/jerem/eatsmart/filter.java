package com.example.jerem.eatsmart;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class filter extends AppCompatActivity {
    Bundle extras;
    String userName, sample, budget;
    Intent resultIntent;
    CheckBox cat_1,cat_2,cat_3,cat_4,cat_5,cat_6,cat_7,cat_8,cat_9;
    RadioGroup radioGroupBudget;
    RadioButton radioBudget, radioLocation;
    boolean isBudget, isLocation;
    int checkedId;
    static int entry_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        entry_1 = 1;
        sample = null;
        budget = null;
        checkedId = -1;
        radioGroupBudget = (RadioGroup)findViewById(R.id.radioGroup5);
        radioBudget = (RadioButton)radioGroupBudget.findViewById(radioGroupBudget.getCheckedRadioButtonId());
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
        cat_1 =(CheckBox) findViewById(R.id.checkBox);
        cat_2 =(CheckBox) findViewById(R.id.checkBox2);
        cat_3 =(CheckBox) findViewById(R.id.checkBox3);
        cat_4 =(CheckBox) findViewById(R.id.checkBox4);
        cat_5 =(CheckBox) findViewById(R.id.checkBox5);
        cat_6 =(CheckBox) findViewById(R.id.checkBox6);
        cat_7 =(CheckBox) findViewById(R.id.checkBox7);
        cat_8 =(CheckBox) findViewById(R.id.checkBox8);
        cat_9 =(CheckBox) findViewById(R.id.checkBox9);
    }

    public void category()
    {
        if (cat_1.isChecked()){
            resultIntent.putExtra("Category1", "cafe");
        }
        if (cat_2.isChecked()){
            resultIntent.putExtra("Category2", "dessert");
        }
        if (cat_3.isChecked()){
            resultIntent.putExtra("Category3", "lutong bahay");
        }
        if (cat_4.isChecked()){
            resultIntent.putExtra("Category4", "bar");
        }
        if (cat_5.isChecked()){
            resultIntent.putExtra("Category5", "grill");
        }
        if (cat_6.isChecked()){
            resultIntent.putExtra("Category6", "fine dining");
        }
        if (cat_7.isChecked()){
            resultIntent.putExtra("Category7", "veg");
        }
        if (cat_8.isChecked()){
            resultIntent.putExtra("Category8", "buffet");
        }
        if (cat_9.isChecked()){
            resultIntent.putExtra("Category9", "fast food");
        }
    }
    public void budget()
    {
        checkedId = radioGroupBudget.getCheckedRadioButtonId();
        if (checkedId == -1)
        {
            budget = null;
        }
        else
        {
            radioBudget = (RadioButton)radioGroupBudget.findViewById(checkedId);
            budget = radioBudget.getText().toString();
        }
        resultIntent.putExtra("Budget", budget);
    }
    public void get_entry(View v)
    {
        sample = "sample string";
    }
    @Override
    public void onBackPressed()
    {
        resultIntent = new Intent();
        category();
        budget();
        resultIntent.putExtra("Filter Entry 1", sample);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
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
