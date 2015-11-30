package com.example.jerem.eatsmart;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class details_restaurant_2 extends AppCompatActivity {

    RadioGroup radioGroupBudget, radioGroupLocation;
    RadioButton radioBudget, radioLocation;
    int checkedIdBudget, checkedIdLocation;
    CheckBox Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining,price1,price2,price3,price4,loc11,loc12,loc13,loc14,loc15,loc16;
    RadioGroup pricerg,locrg;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Bundle extras;
    String userName,password,description,resto_name,contact;
    byte[] img;
    int loc,price;
    ImageButton signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_restaurant_2);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        if (savedInstanceState == null)
        {
            //fetching extra data passed with booleanents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                password=null;
            }
            else
            {
                userName= extras.getString("Username");
                password=extras.getString("Password");
                description=extras.getString("desc");
                resto_name=extras.getString("restau");
                contact=extras.getString("contact");
                img=extras.getByteArray("Logo");
            }
        }

        TextView txt = (TextView) findViewById(R.id.textView24);
        Typeface font = Typeface.createFromAsset(getAssets(), "Kenzo Regular.otf");
        txt.setTypeface(font);
        Cafe=(CheckBox)findViewById(R.id.checkBox10);
        Buffet=(CheckBox)findViewById(R.id.checkBox25);
        Dessert=(CheckBox)findViewById(R.id.checkBox11);
        Bar=(CheckBox)findViewById(R.id.checkBox12);
        Grill=(CheckBox)findViewById(R.id.checkBox13);
        Lutong_Bahay=(CheckBox)findViewById(R.id.checkBox26);
        Fast_food=(CheckBox)findViewById(R.id.checkBox27);
        Veg=(CheckBox)findViewById(R.id.checkBox24);
        Fine_dining=(CheckBox)findViewById(R.id.checkBox23);
        checkedIdBudget = -1;
        checkedIdLocation = -1;
        radioGroupBudget = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroupLocation = (RadioGroup)findViewById(R.id.radioGroup2);
        radioBudget = (RadioButton)radioGroupBudget.findViewById(radioGroupBudget.getCheckedRadioButtonId());
        radioLocation = (RadioButton)radioGroupLocation.findViewById(radioGroupLocation.getCheckedRadioButtonId());

        signUp=(ImageButton)findViewById(R.id.imageButton10);
        signUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean Cafeis = Cafe.isChecked();
                boolean Buffetis = Buffet.isChecked();
                boolean Dessertis = Dessert.isChecked();
                boolean Baris = Bar.isChecked();
                boolean Grillis = Grill.isChecked();
                boolean Lutong_Bahayis = Lutong_Bahay.isChecked();
                boolean Fast_foodis = Fast_food.isChecked();
                boolean Vegis = Veg.isChecked();
                boolean Fine_diningis = Fine_dining.isChecked();
                price();
                location();
                loginDataBaseAdapter.insertEntryrestau(userName,
                        password,
                        resto_name,
                        description,
                        Cafeis,
                        Buffetis,
                        Dessertis,
                        Baris,
                        Grillis,
                        Lutong_Bahayis,
                        Fast_foodis,
                        Vegis,
                        Fine_diningis,
                        loc,
                        price,
                        1,
                        contact,
                        img);
                finish();
            }

        });
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
}
