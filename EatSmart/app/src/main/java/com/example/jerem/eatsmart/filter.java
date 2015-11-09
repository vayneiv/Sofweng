package com.example.jerem.eatsmart;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class filter extends AppCompatActivity {
    Bundle extras;
    String userName, budget, location;
    Intent resultIntent;
    CheckBox Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining;
    RadioGroup radioGroupBudget, radioGroupLocation;
    RadioButton radioBudget, radioLocation;
    int price;
    int loc;
    int checkedIdBudget, checkedIdLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        budget = null;
        location = null;
        checkedIdBudget = -1;
        checkedIdLocation = -1;
        radioGroupBudget = (RadioGroup)findViewById(R.id.radioGroup5);
        radioGroupLocation = (RadioGroup)findViewById(R.id.radioGroup6);
        radioBudget = (RadioButton)radioGroupBudget.findViewById(radioGroupBudget.getCheckedRadioButtonId());
        radioLocation = (RadioButton)radioGroupLocation.findViewById(radioGroupLocation.getCheckedRadioButtonId());
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
        Cafe=(CheckBox)findViewById(R.id.checkBox);
        Buffet=(CheckBox)findViewById(R.id.checkBox2);
        Dessert=(CheckBox)findViewById(R.id.checkBox3);
        Bar=(CheckBox)findViewById(R.id.checkBox4);
        Grill=(CheckBox)findViewById(R.id.checkBox5);
        Lutong_Bahay=(CheckBox)findViewById(R.id.checkBox6);
        Fast_food=(CheckBox)findViewById(R.id.checkBox7);
        Veg=(CheckBox)findViewById(R.id.checkBox8);
        Fine_dining=(CheckBox)findViewById(R.id.checkBox9);
    }
    public void price()
    {
        checkedIdBudget = radioGroupBudget.getCheckedRadioButtonId();
        if (checkedIdBudget == -1)
        {
            price = 0;
        }
        else
        {
            radioBudget = (RadioButton)radioGroupBudget.findViewById(checkedIdBudget);
            price = radioGroupBudget.indexOfChild(radioBudget) + 1;
        }
    }
    public void location()
    {
        checkedIdLocation = radioGroupLocation.getCheckedRadioButtonId();
        if (checkedIdLocation == -1)
        {
            loc = 0;
        }
        else
        {
            radioLocation = (RadioButton)radioGroupLocation.findViewById(checkedIdLocation);
            loc = radioGroupLocation.indexOfChild(radioLocation) + 1;
        }
    }
    @Override
    public void onBackPressed()
    {
        boolean Cafeis = Cafe.isChecked();
        boolean Buffetis = Buffet.isChecked();
        boolean Dessertis = Dessert.isChecked();
        boolean Baris = Bar.isChecked();
        boolean Grillis = Grill.isChecked();
        boolean Lutong_Bahayis = Lutong_Bahay.isChecked();
        boolean Fast_foodis = Fast_food.isChecked();
        boolean Vegis = Veg.isChecked();
        boolean Fine_diningis = Fine_dining.isChecked();
        resultIntent = new Intent();
        resultIntent.putExtra("Cafe",Cafeis);
        resultIntent.putExtra("Buffet", Buffetis);
        resultIntent.putExtra("Dessert",Dessertis);
        resultIntent.putExtra("Bar",Baris);
        resultIntent.putExtra("Grill",Grillis);
        resultIntent.putExtra("Lutong_Bahay",Lutong_Bahayis);
        resultIntent.putExtra("Fast_food",Fast_foodis);
        resultIntent.putExtra("Veg",Vegis);
        resultIntent.putExtra("Fine_dining",Fine_diningis);
        price();
        location();
        resultIntent.putExtra("Price",price);
        resultIntent.putExtra("Location",loc);
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
