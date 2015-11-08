package com.example.jerem.eatsmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class rate_and_comment extends AppCompatActivity {
    Bundle extras;
    String userName, restauName, comment;
    EditText editComment;
    RatingBar ratingBar;
    TextView display_rate_and_comment;
    LoginDataBaseAdapter loginDataBaseAdapter;
    float rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_and_comment);
        rate = 0;
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        if (savedInstanceState == null)
        {
            //fetching extra data passed with intents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                restauName = null;
            }
            else
            {
                userName= extras.getString("Username");
                restauName= extras.getString("Restaurant Name");
            }
        }
        editComment = (EditText)findViewById(R.id.editText4);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        display_rate_and_comment = (TextView)findViewById(R.id.textView59);
    }
    public void send(View v)
    {
        comment = editComment.getText().toString();
        rate = ratingBar.getRating();
        display_rate_and_comment.setText("Rate: " + rate + "\n" + "Comment: " + comment);
        loginDataBaseAdapter.newRateComment(restauName,comment,rate);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rate_and_comment, menu);
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