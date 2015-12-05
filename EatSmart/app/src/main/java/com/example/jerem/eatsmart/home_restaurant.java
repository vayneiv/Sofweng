package com.example.jerem.eatsmart;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class home_restaurant extends AppCompatActivity {
    TextView welcMSG,description,location,cuisine,price,contact;
    Bundle extras;
    String userName,password;
    String category=" ";
    String resto_price=" ";
    String resto_loc=" ";
    int pack;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Button Save;
    KeyListener editdesc;
    Intent edit;
    ImageView image,menupics;
    LinearLayout Rate;
    TextView header;
    ArrayList<Integer> Ratings;
    ArrayList<String> Comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_restaurant);
        edit = new Intent(this,resto_edit.class);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        Log.i("test","test");
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
            }
        }
        Rate=(LinearLayout)findViewById(R.id.linearlayout1);

        TextView txt = (TextView) findViewById(R.id.textView9);
        Typeface font = Typeface.createFromAsset(getAssets(), "Kenzo Regular.otf");
        TextView txt1 = (TextView) findViewById(R.id.textView14);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt.setTypeface(font);
        txt1.setTypeface(font);
        TextView txt2 = (TextView) findViewById(R.id.textView15);
        txt2.setTypeface(font);
        TextView txt3 = (TextView) findViewById(R.id.textView17);
        txt3.setTypeface(font);
        TextView txt4 = (TextView) findViewById(R.id.textView40);
        txt4.setTypeface(font);
        welcMSG=(TextView)findViewById(R.id.textView54);
        welcMSG.setText(loginDataBaseAdapter.getrestauname(userName));
        welcMSG.setTypeface(font1);
        image=(ImageView)findViewById(R.id.imageView6);
        image.invalidate();
        image.setImageBitmap(loginDataBaseAdapter.getimage(userName));

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        description=new TextView(this);
        description=(TextView)findViewById(R.id.textView18);
        description.setText(loginDataBaseAdapter.getrestaudesc(userName));
        description.setTypeface(font1);
        cuisine=new TextView(this);
        cuisine=(TextView)findViewById(R.id.textView35);
        if (loginDataBaseAdapter.getBar(userName)==1){
            category=category+"Bar ";
        } if (loginDataBaseAdapter.getBuffet(userName)==1){
            category=category+"Buffet ";
        } if (loginDataBaseAdapter.getCafe(userName)==1){
            category=category+"Cafe ";
        } if (loginDataBaseAdapter.getDessert(userName)==1){
            category=category+"Dessert ";
        } if (loginDataBaseAdapter.getfast_food(userName)==1){
            category=category+"Fast Food ";
        } if (loginDataBaseAdapter.getFine_dining(userName)==1){
            category=category+"Fine Dining ";
        } if (loginDataBaseAdapter.getGrill(userName)==1){
            category=category+"Grill ";
        } if (loginDataBaseAdapter.getLutong_bahay(userName)==1){
            category=category+"Lutong Bahay ";
        } if (loginDataBaseAdapter.getveg(userName)==1){
            category=category+"Veg ";
        }
            cuisine.setText(category);

        cuisine.setTypeface(font1);
        price=new TextView(this);
        price=(TextView)findViewById(R.id.textView38);
        if(loginDataBaseAdapter.getrestauprice(userName)==1){
            resto_price="P0-P100";
        }else if(loginDataBaseAdapter.getrestauprice(userName)==2){
            resto_price="P101-P500";
        }else if(loginDataBaseAdapter.getrestauprice(userName)==3){
            resto_price="P501-P1000";
        }else if(loginDataBaseAdapter.getrestauprice(userName)==4){
            resto_price="P1000 above";
        }else{
            resto_price=" ";
        }
        price.setText(resto_price);

        price.setTypeface(font1);
        location=new TextView(this);
        location=(TextView)findViewById(R.id.textView2);
        if(loginDataBaseAdapter.getrestauloc(userName)==1){
            resto_loc="Manila";
        }else if(loginDataBaseAdapter.getrestauloc(userName)==2){
            resto_loc="Pasig";
        }else if(loginDataBaseAdapter.getrestauloc(userName)==3){
            resto_loc="Makati";
        }else if(loginDataBaseAdapter.getrestauloc(userName)==4){
            resto_loc="San Juan";
        }else if(loginDataBaseAdapter.getrestauloc(userName)==5){
            resto_loc="Pasay";
        }else if(loginDataBaseAdapter.getrestauloc(userName)==6){
            resto_loc="Taguig";
        }else{
            resto_loc=" ";
        }
        location.setText(resto_loc);

        location.setTypeface(font1);
        contact=new TextView(this);
        contact=(TextView)findViewById(R.id.textView39);
        contact.setText(loginDataBaseAdapter.getrestaucontact(userName));

        menupics=(ImageView)findViewById(R.id.imageView18);
        menupics.invalidate();
        menupics.setImageBitmap(loginDataBaseAdapter.getmenupic(userName));

        contact.setTypeface(font1);
        Ratings=loginDataBaseAdapter.Retrieve_Rate(userName);
        Comments=loginDataBaseAdapter.Retrieve_Comments(userName);
        header=(TextView)findViewById(R.id.textView41);
        header.setTypeface(font);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) LinearLayout.LayoutParams.WRAP_CONTENT,
                (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                (int) LinearLayout.LayoutParams.WRAP_CONTENT,
                (int) LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.topMargin=30;
        if (loginDataBaseAdapter.Retrieve_Comments(userName).size()==0){
            header.setVisibility(View.GONE);
        }else {
            header.setVisibility(View.VISIBLE);
            final int no_of_custo = loginDataBaseAdapter.Retrieve_Comments(userName).size();
            for (int i = 0; i <no_of_custo; i += 1) {
                Log.i("Customer no.", "1");
                final TextView Header = new TextView(this);
                Header.setLayoutParams(params1);
                Header.setText("Customer " + (i + 1));
                Header.setTypeface(font);
                Header.setTextSize(20);
                Header.setTextColor(Color.RED);
                Rate.addView(Header);
                final RatingBar Rating = new RatingBar(this);
                Rating.setLayoutParams(params);
                Rating.setNumStars(5);
                Rating.setStepSize((float) 0.5);
                Rating.setMax(5);
                Rating.setRating(Ratings.get(i));
                Rate.addView(Rating);
                final TextView Comment = new TextView(this);
                final TextView Comment1 = new TextView(this);
                Comment.setText("Comments");
                Comment.setTypeface(font);
                Comment.setTextSize(20);
                Comment1.setText("" + Comments.get(i));
                Comment1.setTypeface(font1);
                Comment1.setTextSize(20);
                Comment.setTextColor(Color.RED);
                Rate.addView(Comment);
                Rate.addView(Comment1);
            }
        }
        Save = (Button) findViewById(R.id.button7);
        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit.putExtra("Username", userName);
                edit.putExtra("Password", password);
                startActivity(edit); //transfer activity
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_restaurant, menu);
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

