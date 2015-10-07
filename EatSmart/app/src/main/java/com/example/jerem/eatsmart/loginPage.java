package com.example.jerem.eatsmart;

import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
//CURRENT UPDATE: Switching Activities
//  for customer only
//  learn how to pass data to home_customer to indicate which customer from database (e.g. index of array)
public class loginPage extends AppCompatActivity {
    EditText login_user, login_pass;
    String user_stored, pass_stored, text_fail;
    Intent to_home_customer;
    Context context;
    int duration;
    Toast fail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login_user = (EditText) findViewById(R.id.editText);// find id of user text field
        login_pass = (EditText) findViewById(R.id.editText2);// find id of pass text field
        context = getApplicationContext(); //necessary for toast
        text_fail = "Try Again"; //text of toast
        duration = Toast.LENGTH_SHORT; //duration of toast
        fail = Toast.makeText(context, text_fail, duration); //initialize toast
    }
    //Create onClick for login
    //  compare input strings from database
    //  if same, proceed to another activity. else try again

    //Create onClick for sign up
    //  go to sign up activity
    //      fill details
    //      save to database
    //          create buttons.
    //      return to login layout

    public void logIn(View v){
        user_stored = login_user.getText().toString(); // get user input
        pass_stored = login_pass.getText().toString(); // get pass input
        if (user_stored.compareTo("asd") == 0 && pass_stored.compareTo("123") == 0){ //compare string values
            to_home_customer = new Intent(this, home_customer.class); //initialize new activity
            startActivity(to_home_customer); //transfer activity
        }
        else {
            fail.show(); //show toast
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_page, menu);
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
