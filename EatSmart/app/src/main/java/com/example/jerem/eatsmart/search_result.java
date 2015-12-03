package com.example.jerem.eatsmart;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class search_result extends AppCompatActivity {
    TextView welcMSG,description,location,cuisine,price,contact;
    String category=" ";
    String resto_price=" ";
    String resto_loc=" ";
    Bundle extras;
    String userName, restauName;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Intent to_rate_and_comment;
    ImageView image;
    LinearLayout Rate;
    TextView header;
    ArrayList<Integer> Ratings;
    ArrayList<String> Comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
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
        Rate=(LinearLayout)findViewById(R.id.linearLayout2);

        Log.i("Username of restau is",restauName);
        TextView txt = (TextView) findViewById(R.id.textView44);
        Typeface font = Typeface.createFromAsset(getAssets(), "Kenzo Regular.otf");
        TextView txt1 = (TextView) findViewById(R.id.textView46);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt.setTypeface(font);
        txt1.setTypeface(font);
        TextView txt2 = (TextView) findViewById(R.id.textView48);
        txt2.setTypeface(font);
        TextView txt3 = (TextView) findViewById(R.id.textView50);
        txt3.setTypeface(font);
        TextView txt4 = (TextView) findViewById(R.id.textView60);
        txt4.setTypeface(font);
        welcMSG=(TextView)findViewById(R.id.textView23);
        welcMSG.setText(loginDataBaseAdapter.getrestauname(restauName));
        welcMSG.setTypeface(font1);
        image=(ImageView)findViewById(R.id.imageView3);
        image.invalidate();
        image.setImageBitmap(loginDataBaseAdapter.getimage(restauName));

        description=new TextView(this);
        description=(TextView)findViewById(R.id.textView45);
        description.setText(loginDataBaseAdapter.getrestaudesc(restauName));
        description.setTypeface(font1);
        cuisine=new TextView(this);
        cuisine=(TextView)findViewById(R.id.textView47);
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

        cuisine.setTypeface(font1);
        price=new TextView(this);
        price=(TextView)findViewById(R.id.textView49);
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

        price.setTypeface(font1);
        location=new TextView(this);
        location=(TextView)findViewById(R.id.textView51);
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
        location.setText(resto_loc);

        location.setTypeface(font1);
        contact=new TextView(this);
        contact=(TextView)findViewById(R.id.textView61);
        contact.setText(loginDataBaseAdapter.getrestaucontact(restauName));

        contact.setTypeface(font1);
        Ratings=loginDataBaseAdapter.Retrieve_Rate(restauName);
        Comments=loginDataBaseAdapter.Retrieve_Comments(restauName);
        header=(TextView)findViewById(R.id.textView62);
        if ((Ratings==null)||(Comments==null)){
            header.setVisibility(View.GONE);
        }else {
            header.setVisibility(View.VISIBLE);
            final int no_of_custo = loginDataBaseAdapter.Retrieve_Comments(restauName).size();
            for (int i = 0; i < no_of_custo; i += 3) {
                final TextView Header = new TextView(this);
                Header.setText("Customer" + (i + 1));
                Header.setTypeface(font);
                Rate.addView(Header);
                final RatingBar Rating = new RatingBar(this);
                Rating.setRating(Ratings.get(i));
                Rate.addView(Rating);
                final TextView Comment = new TextView(this);
                Comment.setText("Comments" + Comments.get(i));
                Comment.setTypeface(font1);
                Rate.addView(Comment);
            }
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
