package com.example.jerem.eatsmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class restaurantSignUp extends Activity
{
    EditText editTextUserName,editTextPassword,editTextConfirmPassword, editTextRestauName, editTextDescription, editTextLocation;
    ImageButton btnCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up);

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get References of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
        editTextRestauName=(EditText)findViewById(R.id.editTextRestauName);
        editTextDescription=(EditText)findViewById(R.id.editTextDescription);
        editTextLocation=(EditText)findViewById(R.id.editTextLocation);
        btnCreateAccount=(ImageButton)findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                String RestauName = editTextRestauName.getText().toString();
                String Description = editTextDescription.getText().toString();
                String Location = editTextLocation.getText().toString();
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
                    loginDataBaseAdapter.insertEntryrestau(userName, password,RestauName,Description,Location);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
    public void onCuisineChecked(View v) // for cuisine checkboxes
    {
        //standard way of handle cuisine checkbox entries. can be transferred to signup button
        //reference:http://developer.android.com/guide/topics/ui/controls/checkbox.html
        /*boolean cuisine_checked = ((CheckBox) v).isChecked();
        switch(v.getId())
        {
            case R.id.checkboxCuisine1:
                if (cuisine_checked)
                {
                    //save checkbox to database
                }
                else
                {
                    //remove from database
                }break;
            case R.id.checkboxCuisine2:
                if (cuisine_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxCuisine3:
                if (cuisine_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxCuisine4:
                if (cuisine_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxCuisine5:
                if (cuisine_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxCuisine6:
                if (cuisine_checked)
                {

                }
                else
                {

                }break;
        }*/

    }
    public void onBudgetChecked(View v) // for budget checkboxes
    {
        //standard way of handle budget checkbox entries. can be transferred to signup button
        /*boolean budget_checked = ((CheckBox) v).isChecked();
        switch(v.getId())
        {
            case R.id.checkboxBudget1:
                if (budget_checked)
                {
                    //save checkbox to database
                }
                else
                {
                    //remove from database
                }break;
            case R.id.checkboxBudget2:
                if (budget_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxBudget3:
                if (budget_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxBudget4:
                if (budget_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxBudget5:
                if (budget_checked)
                {

                }
                else
                {

                }break;
            case R.id.checkboxBudget6:
                if (budget_checked)
                {

                }
                else
                {

                }break;
        }*/
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}