package com.example.jerem.eatsmart;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class resto_edit extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;

    private static int RESULT_LOAD_IMAGE_2 = 2;
    Bundle extras;
    EditText desc,contact;
    CheckBox Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining,price1,price2,price3,price4,loc11,loc12,loc13,loc14,loc15,loc16;
    Button sel_Image,next;
    ImageButton menupics;
    String userName,password;
    RadioGroup radioGroupBudget, radioGroupLocation;
    RadioButton radioBudget, radioLocation;
    byte[] img,menu_pic;
    int checkedIdBudget, checkedIdLocation;
    int price;
    int loc,pack;
    ImageView imageView,menupicture;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Bitmap logo;
    String description;
    String contactno;
    boolean Cafeis;
    boolean Buffetis;
    boolean Dessertis ;
    boolean Baris;
    boolean Grillis;
    boolean Lutong_Bahayis;
    boolean Fast_foodis ;
    boolean Vegis ;
    boolean Fine_diningis ;
    Bitmap yourSelectedPic;
    Intent finished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_edit);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        finished=new Intent(this,home_restaurant.class);
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
        Cafe=(CheckBox)findViewById(R.id.checkBox14);
        Buffet=(CheckBox)findViewById(R.id.checkBox15);
        Dessert=(CheckBox)findViewById(R.id.checkBox16);
        Bar=(CheckBox)findViewById(R.id.checkBox17);
        Grill=(CheckBox)findViewById(R.id.checkBox18);
        Lutong_Bahay=(CheckBox)findViewById(R.id.checkBox19);
        Fast_food=(CheckBox)findViewById(R.id.checkBox20);
        Veg=(CheckBox)findViewById(R.id.checkBox21);
        Fine_dining=(CheckBox)findViewById(R.id.checkBox22);
        radioGroupBudget = (RadioGroup)findViewById(R.id.radioGroup9);
        radioGroupLocation = (RadioGroup)findViewById(R.id.radioGroup10);


        desc=(EditText)findViewById(R.id.editText3);
        contact=(EditText)findViewById(R.id.editText11);
        radioBudget = (RadioButton) radioGroupBudget.findViewById(radioGroupBudget.getCheckedRadioButtonId());
        radioLocation = (RadioButton)radioGroupLocation.findViewById(radioGroupLocation.getCheckedRadioButtonId());
        imageView= (ImageView) findViewById(R.id.imageView8);
        sel_Image=(Button)findViewById(R.id.button12);

        imageView.setImageBitmap(loginDataBaseAdapter.getimage(userName));
        menupicture=(ImageView)findViewById(R.id.imageView20);
        menupicture.setImageBitmap(loginDataBaseAdapter.getmenupic(userName));
        next=(Button)findViewById(R.id.button8);
        sel_Image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        pack=loginDataBaseAdapter.getrestapack(userName);

        menupics=(ImageButton)findViewById(R.id.button5);
        menupics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE_2);
            }
        });
        TextView txt1 = (TextView) findViewById(R.id.textView22);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt1.setTypeface(font1);
        TextView txt2 = (TextView) findViewById(R.id.textView26);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt2.setTypeface(font2);
        TextView txt3= (TextView) findViewById(R.id.textView31);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "basictitlefont.ttf");
        txt3.setTypeface(font3);
        TextView txt4 = (TextView) findViewById(R.id.textView30);
        txt4.setTypeface(font2);
        TextView txt5= (TextView) findViewById(R.id.textView21);
        txt5.setTypeface(font3);
        next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (desc.getText().toString() == "") {
                    description = loginDataBaseAdapter.getrestaudesc(userName);
                } else {
                    description = desc.getText().toString();
                }
                if (contact.getText().toString() == "") {
                    contactno = loginDataBaseAdapter.getrestaucontact(userName);
                } else {
                    contactno = contact.getText().toString();
                }
                if(!Cafe.isChecked()&&!Buffet.isChecked()&&!Dessert.isChecked()&&!Bar.isChecked()&&!Grill.isChecked()&&!Lutong_Bahay.isChecked()&&!Fast_food.isChecked()&&!Veg.isChecked()&&!Fine_dining.isChecked()){

                    if (loginDataBaseAdapter.getCafe(userName) == 1) {
                        Cafeis = true;
                    } else {
                        Cafeis = false;
                    }
                    if (loginDataBaseAdapter.getBuffet(userName) == 1) {
                        Buffetis = true;
                    } else {
                        Buffetis = false;
                    }
                    if (loginDataBaseAdapter.getDessert(userName) == 1) {
                        Dessertis = true;
                    } else {
                        Dessertis = false;
                    }
                    if (loginDataBaseAdapter.getBar(userName) == 1) {
                        Baris = true;
                    } else {
                        Baris = false;
                    }
                    if (loginDataBaseAdapter.getGrill(userName) == 1) {
                        Grillis = true;
                    } else {
                        Grillis = false;
                    }
                    if (loginDataBaseAdapter.getLutong_bahay(userName) == 1) {
                        Lutong_Bahayis = true;
                    } else {
                        Lutong_Bahayis = false;
                    }
                    if (loginDataBaseAdapter.getfast_food(userName) == 1) {
                        Fast_foodis = true;
                    } else {
                        Fast_foodis = false;
                    }
                    if (loginDataBaseAdapter.getveg(userName) == 1) {
                        Vegis = true;
                    } else {
                        Vegis = false;
                    }
                    if (loginDataBaseAdapter.getFine_dining(userName) == 1) {
                        Fine_diningis = true;
                    } else {
                        Fine_diningis = false;
                    }
                }
                else
                {
                 Cafeis = Cafe.isChecked();
                 Buffetis = Buffet.isChecked();
                 Dessertis = Dessert.isChecked();
                 Baris = Bar.isChecked();
                 Grillis = Grill.isChecked();
                 Lutong_Bahayis = Lutong_Bahay.isChecked();
                 Fast_foodis = Fast_food.isChecked();
                 Vegis = Veg.isChecked();
                 Fine_diningis = Fine_dining.isChecked();
                }
                Bitmap yourSelectedImage = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                img= stream.toByteArray();
                Bitmap yourSelectedPic = ((BitmapDrawable)menupicture.getDrawable()).getBitmap();
                ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                yourSelectedPic.compress(Bitmap.CompressFormat.PNG, 100, stream1);
                menu_pic= stream1.toByteArray();
                price();
                location();
                Log.i("Description contains",contact.getText().toString());
                loginDataBaseAdapter.updaterestau(userName,
                        password,
                        loginDataBaseAdapter.getrestauname(userName),
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
                        pack,
                        contactno,
                        img,
                        menu_pic);
                Log.i("username is",loginDataBaseAdapter.getSingleEntryrestau(userName));
                finished.putExtra("Username",userName);
                finished.putExtra("Password",password);
                startActivity(finished);
                finish();
            }

        });



    }
    public void price()
    {
        checkedIdBudget = radioGroupBudget.getCheckedRadioButtonId();
        if (checkedIdBudget == -1)
        {
            price=loginDataBaseAdapter.getrestauprice(userName);
        }
        else
        {
            radioBudget = (RadioButton)radioGroupBudget.findViewById(checkedIdBudget);
            price = radioGroupBudget.indexOfChild(radioBudget) + 1;
        }
    }
    public void location()
    {
        checkedIdLocation = radioGroupLocation.getCheckedRadioButtonId();
        if (checkedIdLocation == -1)
        {
            loc=loginDataBaseAdapter.getrestauloc(userName);
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
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap yourSelectedImage = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(yourSelectedImage);
        } else if(requestCode == RESULT_LOAD_IMAGE_2 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            yourSelectedPic = BitmapFactory.decodeFile(picturePath);
            menupicture.setImageBitmap(yourSelectedPic);
        }
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resto_edit.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}