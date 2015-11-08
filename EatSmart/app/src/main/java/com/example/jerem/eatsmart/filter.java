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
    CheckBox Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining,price1,price2,price3,price4,loc11,loc12,loc13,loc14,loc15,loc16;
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
        }Cafe=(CheckBox)findViewById(R.id.checkBox);
        Buffet=(CheckBox)findViewById(R.id.checkBox2);
        Dessert=(CheckBox)findViewById(R.id.checkBox3);
        Bar=(CheckBox)findViewById(R.id.checkBox4);
        Grill=(CheckBox)findViewById(R.id.checkBox5);
        Lutong_Bahay=(CheckBox)findViewById(R.id.checkBox6);
        Fast_food=(CheckBox)findViewById(R.id.checkBox7);
        Veg=(CheckBox)findViewById(R.id.checkBox8);
        Fine_dining=(CheckBox)findViewById(R.id.checkBox9);
        price1=(CheckBox)findViewById(R.id.checkBox33);
        price1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=1;
                price1.setChecked(true);
                price2.setChecked(false);
                price3.setChecked(false);
                price4.setChecked(false);
            }
        });
        price2=(CheckBox)findViewById(R.id.checkBox34);
        price2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=2;
                price1.setChecked(false);
                price2.setChecked(true);
                price3.setChecked(false);
                price4.setChecked(false);
            }
        });
        price3=(CheckBox)findViewById(R.id.checkBox35);
        price3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=3;
                price1.setChecked(false);
                price2.setChecked(false);
                price3.setChecked(true);
                price4.setChecked(false);
            }
        });
        price4=(CheckBox)findViewById(R.id.checkBox36);
        price4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=4;
                price1.setChecked(false);
                price2.setChecked(false);
                price3.setChecked(false);
                price4.setChecked(true);
            }
        });
        loc11=(CheckBox)findViewById(R.id.checkBox37);
        loc11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=1;
                loc11.setChecked(true);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc12=(CheckBox)findViewById(R.id.checkBox38);
        loc12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=2;
                loc11.setChecked(false);
                loc12.setChecked(true);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc13=(CheckBox)findViewById(R.id.checkBox39);
        loc13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=3;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(true);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc14=(CheckBox)findViewById(R.id.checkBox40);
        loc14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=4;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(true);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc15=(CheckBox)findViewById(R.id.checkBox41);
        loc15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=5;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(true);
                loc16.setChecked(false);
            }
        });
        loc16=(CheckBox)findViewById(R.id.checkBox42);
        loc16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=6;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(true);
            }
        });
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
