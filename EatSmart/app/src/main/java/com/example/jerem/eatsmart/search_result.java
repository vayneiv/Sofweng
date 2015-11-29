package com.example.jerem.eatsmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class search_result extends AppCompatActivity {
    TextView description,location,cuisine,price;
    String category=" ";
    String resto_price=" ";
    String resto_loc=" ";
    Bundle extras;
    String userName, restauName;
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView  display_restau;
    Intent to_rate_and_comment;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        display_restau = (TextView) findViewById(R.id.textView67);
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
            else if(restauName == null)
            {
                userName= extras.getString("Username");
                restauName = "No Restaurant Found";
            }
            else
            {
                userName= extras.getString("Username");
                restauName= extras.getString("Restaurant Name");
            }
        }

        display_restau.setText("Restaurant Name: " + "\n" + restauName);
        image=(ImageView)findViewById(R.id.imageView3);
        image.invalidate();
        image.setImageBitmap(loginDataBaseAdapter.getimage(restauName));
        description=new TextView(this);
        description=(TextView)findViewById(R.id.textView63);
        description.setText(loginDataBaseAdapter.getrestaudesc(restauName));
        cuisine=new TextView(this);
        cuisine=(TextView)findViewById(R.id.textView64);
        if (loginDataBaseAdapter.getBar(restauName)==1){
            category=category+"Bar ";
        } if (loginDataBaseAdapter.getBuffet(restauName)==1){
            category=category+"Buffet ";
        } if (loginDataBaseAdapter.getCafe(restauName)==1){
            category=category+"Cafe ";
        } if (loginDataBaseAdapter.getDessert(restauName)==1){
            category=category+"Dessert ";
        } if (loginDataBaseAdapter.getfast_food(restauName)==1){
            category=category+"Fast Food ";
        } if (loginDataBaseAdapter.getFine_dining(restauName)==1){
            category=category+"Fine Dining ";
        } if (loginDataBaseAdapter.getGrill(restauName)==1){
            category=category+"Grill ";
        } if (loginDataBaseAdapter.getLutong_bahay(restauName)==1){
            category=category+"Lutong Bahay ";
        } if (loginDataBaseAdapter.getveg(restauName)==1){
            category=category+"Veg ";
        }
        cuisine.setText(category);
        price=new TextView(this);
        price=(TextView)findViewById(R.id.textView65);
        if(loginDataBaseAdapter.getrestauprice(restauName)==1){
            resto_price="P0-P100";
        }else if(loginDataBaseAdapter.getrestauprice(restauName)==2){
            resto_price="P101-P500";
        }else if(loginDataBaseAdapter.getrestauprice(restauName)==3){
            resto_price="P501-P1000";
        }else if(loginDataBaseAdapter.getrestauprice(restauName)==4){
            resto_price="P1000 above";
        }else{
            resto_price=" ";
        }
        price.setText(resto_price);
        location=new TextView(this);
        location=(TextView)findViewById(R.id.textView66);
        if(loginDataBaseAdapter.getrestauloc(restauName)==1){
            resto_loc="Manila";
        }else if(loginDataBaseAdapter.getrestauloc(restauName)==2){
            resto_loc="Pasig";
        }else if(loginDataBaseAdapter.getrestauloc(restauName)==3){
            resto_loc="Makati";
        }else if(loginDataBaseAdapter.getrestauloc(restauName)==4){
            resto_loc="San Juan";
        }else if(loginDataBaseAdapter.getrestauloc(restauName)==5){
            resto_loc="Pasay";
        }else if(loginDataBaseAdapter.getrestauloc(restauName)==6){
            resto_loc="Taguig";
        }else{
            resto_loc=" ";
        }
    }
    public void to_rate_and_comment(View v)
    {
        to_rate_and_comment = new Intent(this, rate_and_comment.class);
        to_rate_and_comment.putExtra("Username", userName);
        to_rate_and_comment.putExtra("Restaurant Name", restauName);
        startActivity(to_rate_and_comment);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
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
