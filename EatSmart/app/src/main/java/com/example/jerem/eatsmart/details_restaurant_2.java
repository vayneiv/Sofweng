package com.example.jerem.eatsmart;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class details_restaurant_2 extends AppCompatActivity {

    RadioGroup radioGroupBudget, radioGroupLocation;
    RadioButton radioBudget, radioLocation;
    int checkedIdBudget, checkedIdLocation;
    CheckBox Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining,price1,price2,price3,price4,loc11,loc12,loc13,loc14,loc15,loc16;
    RadioGroup pricerg,locrg;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Bundle extras;
    EditText desc;
    String userName,password,description,resto_name,contact,img;
    byte[] logo,menupictures;
    Uri imgUri;
    int loc,price;
    int newmenu=0;
    ImageButton signUp,sel_Image;
    Intent back_home;
    Bitmap yourSelectedpic;
    private static int RESULT_LOAD_IMAGE = 1;
    Uri menupics=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_restaurant_2);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        if (savedInstanceState == null)
        {
            //fetching extra data passed with booleanents in a Bundle type variable
            extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
                password=null;
                resto_name=null;
                contact=null;
                img=null;
                Log.i("Extras is","null");
            }
            else
            {
                userName= extras.getString("Username");
                password=extras.getString("Password");
                resto_name=extras.getString("restau");
                contact=extras.getString("contact");
                img=extras.getString("imageUri");

                Log.i("Extras is","not null");
            }
        }

        back_home = new Intent(this, loginPage.class);
        back_home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Log.i("img is",img);
        if(img.equalsIgnoreCase("1")){
            Bitmap profilelogo= BitmapFactory.decodeResource(getResources(), R.drawable.profile_logo);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            profilelogo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            logo = stream.toByteArray();
        }else{
            imgUri=Uri.parse(img);
            Cursor cursor = getContentResolver().query(imgUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap yourSelectedImage = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            logo = stream.toByteArray();
        }
        desc=(EditText)findViewById(R.id.editText12);
        Log.i("logo is",logo.toString());
        TextView txt = (TextView) findViewById(R.id.textView24);
        Typeface font = Typeface.createFromAsset(getAssets(), "Kenzo Regular.otf");
        txt.setTypeface(font);
        TextView txt1 = (TextView) findViewById(R.id.textView19);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt1.setTypeface(font1);
        TextView txt2 = (TextView) findViewById(R.id.textView42);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt2.setTypeface(font2);
        TextView txt3 = (TextView) findViewById(R.id.textView43);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt3.setTypeface(font3);
        TextView txt4 = (TextView) findViewById(R.id.textView65);
        Typeface font4 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt4.setTypeface(font4);
        sel_Image=(ImageButton)findViewById(R.id.imageButton11);
        sel_Image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newmenu=1;
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        Cafe=(CheckBox)findViewById(R.id.checkBox28);
        Buffet=(CheckBox)findViewById(R.id.checkBox34);
        Dessert=(CheckBox)findViewById(R.id.checkBox29);
        Bar=(CheckBox)findViewById(R.id.checkBox30);
        Grill=(CheckBox)findViewById(R.id.checkBox31);
        Lutong_Bahay=(CheckBox)findViewById(R.id.checkBox35);
        Fast_food=(CheckBox)findViewById(R.id.checkBox36);
        Veg=(CheckBox)findViewById(R.id.checkBox33);
        Fine_dining=(CheckBox)findViewById(R.id.checkBox32);
        checkedIdBudget = -1;
        checkedIdLocation = -1;
        radioGroupBudget = (RadioGroup)findViewById(R.id.radioGroup2);
        radioGroupLocation = (RadioGroup)findViewById(R.id.radioGroup);
        radioBudget = (RadioButton)radioGroupBudget.findViewById(radioGroupBudget.getCheckedRadioButtonId());
        radioLocation = (RadioButton)radioGroupLocation.findViewById(radioGroupLocation.getCheckedRadioButtonId());


        signUp=(ImageButton)findViewById(R.id.imageButton10);
        signUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean Cafeis = Cafe.isChecked();
                boolean Buffetis = Buffet.isChecked();
                boolean Dessertis = Dessert.isChecked();
                boolean Baris = Bar.isChecked();
                boolean Grillis = Grill.isChecked();
                boolean Lutong_Bahayis = Lutong_Bahay.isChecked();
                boolean Fast_foodis = Fast_food.isChecked();
                boolean Vegis = Veg.isChecked();
                boolean Fine_diningis = Fine_dining.isChecked();
                if(newmenu==0){
                    Bitmap menupic= BitmapFactory.decodeResource(getResources(), R.drawable.menu_pics);
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    menupic.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                    menupictures = stream2.toByteArray();
                }else{
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    yourSelectedpic.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                    menupictures= stream2.toByteArray();
                }
                description=desc.getText().toString();
                price();
                location();
                loginDataBaseAdapter.insertEntryrestau(userName,
                        password,
                        resto_name,
                        description,
                        Cafeis,
                        Buffetis,
                        Dessertis,
                        Baris,
                        Grillis,
                        Lutong_Bahayis,
                        Fast_foodis,
                        Vegis,
                        Fine_diningis,
                        loc,
                        price,
                        1,
                        contact,
                        logo,
                        menupictures);
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                startActivity(back_home);
            }

        });
    }
    public void price()
    {
        checkedIdBudget = radioGroupBudget.getCheckedRadioButtonId();
        if (checkedIdBudget == -1)
        {
            price = 0;
        }
        else
        {
            radioBudget = (RadioButton)radioGroupBudget.findViewById(checkedIdBudget);
            price = radioGroupBudget.indexOfChild(radioBudget) + 1;
            Log.i("Price is", String.valueOf(price));
        }
    }
    public void location()
    {
        checkedIdLocation = radioGroupLocation.getCheckedRadioButtonId();
        if (checkedIdLocation == -1)
        {
            loc = 0;
        }
        else
        {
            radioLocation = (RadioButton)radioGroupLocation.findViewById(checkedIdLocation);
            loc = radioGroupLocation.indexOfChild(radioLocation) + 1;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            menupics = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(menupics, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            yourSelectedpic = BitmapFactory.decodeFile(picturePath);
        }
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }
}
