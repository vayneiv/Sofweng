package com.example.jerem.eatsmart;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class resto_edit extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    Bundle extras;
    EditText desc,contact;
    CheckBox Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining,price1,price2,price3,price4,loc11,loc12,loc13,loc14,loc15,loc16;
    ImageButton next;
    Button sel_Image;
    String userName,password;
    RadioGroup radioGroupBudget, radioGroupLocation;
    RadioButton radioBudget, radioLocation;
    byte[] img;
    int checkedIdBudget, checkedIdLocation;
    int price;
    int loc,pack;
    ImageView imageView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto_edit);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
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
        contact=(EditText)findViewById(R.id.editText11);
        desc=(EditText)findViewById(R.id.editText3);
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


        radioBudget = (RadioButton)radioGroupBudget.findViewById(radioGroupBudget.getCheckedRadioButtonId());
        radioLocation = (RadioButton)radioGroupLocation.findViewById(radioGroupLocation.getCheckedRadioButtonId());
        imageView= (ImageView) findViewById(R.id.imageView8);
        sel_Image=(Button)findViewById(R.id.button12);

        next=(ImageButton)findViewById(R.id.imageButton6);
        sel_Image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        description = loginDataBaseAdapter.getrestaudesc(userName);
        contactno=loginDataBaseAdapter.getrestaucontact(userName);
        if (loginDataBaseAdapter.getCafe(userName)==1) {
            Cafeis = true;
        }
        else{
            Cafeis=false;
        }
        if (loginDataBaseAdapter.getBuffet(userName)==1) {
            Buffetis = true;
        }
        else{
            Buffetis=false;
        }
        if (loginDataBaseAdapter.getDessert(userName)==1) {
            Dessertis = true;
        }
        else{
            Dessertis=false;
        }
        if (loginDataBaseAdapter.getBar(userName)==1) {
            Baris = true;
        }
        else{
            Baris=false;
        }
        if (loginDataBaseAdapter.getGrill(userName)==1) {
            Grillis = true;
        }
        else{
            Grillis=false;
        }
        if (loginDataBaseAdapter.getLutong_bahay(userName)==1) {
            Lutong_Bahayis = true;
        }
        else{
            Lutong_Bahayis=false;
        }
        if (loginDataBaseAdapter.getfast_food(userName)==1) {
            Fast_foodis = true;
        }
        else{
            Fast_foodis=false;
        }
        if (loginDataBaseAdapter.getveg(userName)==1) {
            Vegis = true;
        }
        else{
            Vegis=false;
        }
        if (loginDataBaseAdapter.getFine_dining(userName)==1) {
            Fine_diningis = true;
        }
        else{
            Fine_diningis=false;
        }
        loc=loginDataBaseAdapter.getrestauloc(userName);
        price=loginDataBaseAdapter.getrestauprice(userName);
        pack=loginDataBaseAdapter.getrestapack(userName);
        logo=loginDataBaseAdapter.getimage(userName);
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        logo.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        img= stream1.toByteArray();
        next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                description = desc.getText().toString();
                contactno=contact.getText().toString();
                 Cafeis = Cafe.isChecked();
                 Buffetis = Buffet.isChecked();
                 Dessertis = Dessert.isChecked();
                 Baris = Bar.isChecked();
                 Grillis = Grill.isChecked();
                 Lutong_Bahayis = Lutong_Bahay.isChecked();
                 Fast_foodis = Fast_food.isChecked();
                 Vegis = Veg.isChecked();
                 Fine_diningis = Fine_dining.isChecked();
                Bitmap yourSelectedImage = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                img= stream.toByteArray();
                price();
                location();
                loginDataBaseAdapter.updaterestau(userName,
                        password,
                        null,
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
                        img);
                finish();
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
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap yourSelectedImage = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(yourSelectedImage);
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