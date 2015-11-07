package com.example.jerem.eatsmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class im_feeling_lucky extends AppCompatActivity {
    Bundle extras;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String userName,cat_1,cat_2,cat_3,cat_4,cat_5,cat_6,cat_7,cat_8,cat_9, budget, location;
    TextView user, cate_1,cate_2,cate_3,cate_4,cate_5,cate_6,cate_7,cate_8,cate_9, text_budget,text_location;
    int budget_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_feeling_lucky);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        budget_db = 0;
        if (savedInstanceState == null)
        {
            //fetching extra data passed with intents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                cat_1 = null;
                cat_2 = null;
                cat_3 = null;
                cat_4 = null;
                cat_5 = null;
                cat_6 = null;
                cat_7 = null;
                cat_8 = null;
                cat_9 = null;
                budget = null;
                location = null;
            }
            else
            {
                userName= extras.getString("Username");
                cat_1 = extras.getString("Category1");
                cat_2 = extras.getString("Category2");
                cat_3 = extras.getString("Category3");
                cat_4 = extras.getString("Category4");
                cat_5 = extras.getString("Category5");
                cat_6 = extras.getString("Category6");
                cat_7 = extras.getString("Category7");
                cat_8 = extras.getString("Category8");
                cat_9 = extras.getString("Category9");
                budget = extras.getString("Budget");
                location = extras.getString("Location");
            }
        }
        user = (TextView) findViewById(R.id.textView34);
        cate_1 = (TextView) findViewById(R.id.textView38);
        cate_2 = (TextView) findViewById(R.id.textView39);
        cate_3 = (TextView) findViewById(R.id.textView40);
        cate_4 = (TextView) findViewById(R.id.textView41);
        cate_5 = (TextView) findViewById(R.id.textView42);
        cate_6 = (TextView) findViewById(R.id.textView43);
        cate_7 = (TextView) findViewById(R.id.textView44);
        cate_8 = (TextView) findViewById(R.id.textView45);
        cate_9 = (TextView) findViewById(R.id.textView46);
        text_budget = (TextView)findViewById(R.id.textView48);
        text_location = (TextView)findViewById(R.id.textView51);
        user.setText("User: " + userName);
        cate_1.setText("" + cat_1);
        cate_2.setText("" + cat_2);
        cate_3.setText("" + cat_3);
        cate_4.setText("" + cat_4);
        cate_5.setText("" + cat_5);
        cate_6.setText("" + cat_6);
        cate_7.setText("" + cat_7);
        cate_8.setText("" + cat_8);
        cate_9.setText("" + cat_9);
        getBudgetIndex();
        text_budget.setText("" + budget + " budget_db: " + budget_db);
        text_location.setText("" + location);
    }
    public void getBudgetIndex()
    {
        if (budget == null)
        {
            budget_db = 0;
        }
        else
        {
            if (budget.equals("0-100"))
            {
                budget_db = 1;
            }
            else if (budget.equals("101-500"))
            {
                budget_db = 2;
            }
            else if (budget.equals("501-1000"))
            {
                budget_db = 3;
            }
            else if (budget.equals("1000+"))
            {
                budget_db = 4;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_im_feeling_lucky, menu);
        return true;
    }
    public String IFL(){
        ArrayList<String> choice;
        String result;
        Random r = new Random();
        choice=loginDataBaseAdapter.getList();
        result=choice.get(r.nextInt(choice.size()+1));
        return result;
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
