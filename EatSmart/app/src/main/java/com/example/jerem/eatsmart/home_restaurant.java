package com.example.jerem.eatsmart;

import android.content.Intent;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;
import android.widget.ToggleButton;

public class home_restaurant extends AppCompatActivity {
    TextView welcMSG,description,location,cuisine,price;
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
    ImageView image;
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
                pack=1;
            }
            else
            {
                userName= extras.getString("Username");
                password=extras.getString("Password");
                pack=extras.getInt("package");
            }
        }
        image=(ImageView)findViewById(R.id.imageView6);
        image.invalidate();
        image.setImageBitmap(loginDataBaseAdapter.getimage(userName));
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        description=new TextView(this);
        description=(TextView)findViewById(R.id.textView13);
        description.setText(loginDataBaseAdapter.getrestaudesc(userName));
        cuisine=new TextView(this);
        cuisine=(TextView)findViewById(R.id.textView15);
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
        price=new TextView(this);
        price=(TextView)findViewById(R.id.textView17);
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
        location=new TextView(this);
        location=(TextView)findViewById(R.id.textView18);
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
        Save = (Button) findViewById(R.id.button7);
        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                image.setImageBitmap(loginDataBaseAdapter.getimage(userName));
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

