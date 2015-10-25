package com.example.jerem.eatsmart;

import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
//CURRENT UPDATE: Switching Activities
//  for customer only
//  learn how to pass data to home_customer to indicate which customer from database (e.g. index of array)
public class loginPage extends AppCompatActivity {
    EditText login_user, login_pass;
    String user_stored, pass_stored, text_fail,text_deb;
    Intent to_home_customer,to_home_restaurant;
    Context context;
    int duration;
    Toast fail;
    Toast deb;
    LoginDataBaseAdapter loginDataBaseAdapter;
    RestauDataBaseAdapter restauDataBaseAdapter;
    Button Debug;
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
        deb=Toast.makeText(context,text_deb,duration);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

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
        String storedVar_restau=loginDataBaseAdapter.getSingleEntryrestau(user_stored);
        String storedVar_user=loginDataBaseAdapter.getSingleEntrycusto(user_stored);
        Log.i("Restau Name",storedVar_restau);
        // check if the Stored password matches with  Password entered by user
        if(pass_stored.equals(storedVar_restau))
        {
            to_home_restaurant = new Intent(this, home_restaurant.class);
            to_home_restaurant.putExtra("Username", user_stored);
            startActivity(to_home_restaurant); //transfer activity
        }
        else if(pass_stored.equals(storedVar_user))
        {
            to_home_customer = new Intent(this, home_customer.class); //initialize new activity
            to_home_customer.putExtra("Username", user_stored);
            startActivity(to_home_customer); //transfer activity
        }
        else {
            fail.show(); //show toast
        }

    }
    public void signUp(View v) {
        // TODO Auto-generated method stub

        /// Create Intent for SignUpActivity  abd Start The Activity
        Intent intentSignUP=new Intent(getApplicationContext(),chooseSignUP.class);
        startActivity(intentSignUP);
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
