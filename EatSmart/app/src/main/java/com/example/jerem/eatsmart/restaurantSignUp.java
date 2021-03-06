package com.example.jerem.eatsmart;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class restaurantSignUp extends Activity
{
    EditText editTextUserName,editTextPassword,editTextConfirmPassword, editTextRestauName, editTextDescription, editTextLocation;
    ImageButton btnCreateAccount;
    Intent to_details_restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up);
        to_details_restaurant = new Intent(this, details_restaurant.class);
        // get Instance  of Database Adapter
        // Get References of Views
        editTextUserName=(EditText)findViewById(R.id.editText5);
        editTextPassword=(EditText)findViewById(R.id.editText8);
        editTextConfirmPassword=(EditText)findViewById(R.id.editText9);
        btnCreateAccount=(ImageButton)findViewById(R.id.imageButton7);

        TextView txt = (TextView) findViewById(R.id.textView55);
        Typeface font = Typeface.createFromAsset(getAssets(), "Kenzo Regular.otf");
        txt.setTypeface(font);
        TextView txt1 = (TextView) findViewById(R.id.textView56);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt1.setTypeface(font1);
        TextView txt2 = (TextView) findViewById(R.id.textView57);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt2.setTypeface(font2);
        TextView txt3= (TextView) findViewById(R.id.textView58);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt3.setTypeface(font3);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();

                // check if any of the fields are vaccant
                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))//add other textfields and/or checkboxes
                {
                    Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // Save the Data in Database
                    //loginDataBaseAdapter.insertEntryrestau(userName, password,RestauName,Description,Location);
                    to_details_restaurant.putExtra("Username", userName);
                    to_details_restaurant.putExtra("Password", password);
                    startActivity(to_details_restaurant);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }
}