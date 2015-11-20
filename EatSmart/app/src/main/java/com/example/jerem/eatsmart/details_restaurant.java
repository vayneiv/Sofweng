package com.example.jerem.eatsmart;

import android.content.ContentResolver;
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

public class details_restaurant extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    Bundle extras;
    EditText desc,res_name;
    CheckBox Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining,price1,price2,price3,price4,loc11,loc12,loc13,loc14,loc15,loc16;
    ImageButton next;
    Button sel_Image;
    String userName,password;
    byte[] img;
    RadioGroup pricerg,locrg;
    int price;
    int loc;
    ImageView imageView;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_restaurant);
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
        res_name=(EditText)findViewById(R.id.editText4);
        desc=(EditText)findViewById(R.id.editText6);
        Cafe=(CheckBox)findViewById(R.id.radioButton5);
        Buffet=(CheckBox)findViewById(R.id.radioButton26);
        Dessert=(CheckBox)findViewById(R.id.radioButton6);
        Bar=(CheckBox)findViewById(R.id.radioButton16);
        Grill=(CheckBox)findViewById(R.id.radioButton23);
        Lutong_Bahay=(CheckBox)findViewById(R.id.radioButton27);
        Fast_food=(CheckBox)findViewById(R.id.radioButton28);
        Veg=(CheckBox)findViewById(R.id.radioButton25);
        Fine_dining=(CheckBox)findViewById(R.id.radioButton24);
        price1=(CheckBox)findViewById(R.id.checkBox10);
        price1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=3;
                price1.setChecked(true);
                price2.setChecked(false);
                price3.setChecked(false);
                price4.setChecked(false);
            }
        });
        price2=(CheckBox)findViewById(R.id.checkBox11);
        price2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=2;
                price1.setChecked(false);
                price2.setChecked(true);
                price3.setChecked(false);
                price4.setChecked(false);
            }
        });
        price3=(CheckBox)findViewById(R.id.checkBox12);
        price3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=1;
                price1.setChecked(false);
                price2.setChecked(false);
                price3.setChecked(true);
                price4.setChecked(false);
            }
        });
        price4=(CheckBox)findViewById(R.id.checkBox13);
        price4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                price=4;
                price1.setChecked(false);
                price2.setChecked(false);
                price3.setChecked(false);
                price4.setChecked(true);
            }
        });
        loc11=(CheckBox)findViewById(R.id.radioButton13);
        loc11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=1;
                loc11.setChecked(true);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc12=(CheckBox)findViewById(R.id.radioButton14);
        loc12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=2;
                loc11.setChecked(false);
                loc12.setChecked(true);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc13=(CheckBox)findViewById(R.id.radioButton15);
        loc13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=3;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(true);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc14=(CheckBox)findViewById(R.id.radioButton17);
        loc14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=4;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(true);
                loc15.setChecked(false);
                loc16.setChecked(false);
            }
        });
        loc15=(CheckBox)findViewById(R.id.radioButton49);
        loc15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=5;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(true);
                loc16.setChecked(false);
            }
        });
        loc16=(CheckBox)findViewById(R.id.radioButton50);
        loc16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loc=6;
                loc11.setChecked(false);
                loc12.setChecked(false);
                loc13.setChecked(false);
                loc14.setChecked(false);
                loc15.setChecked(false);
                loc16.setChecked(true);
            }
        });

        imageView= (ImageView) findViewById(R.id.imageView5);
        sel_Image=(Button)findViewById(R.id.button6);
        sel_Image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        next=(ImageButton)findViewById(R.id.imageButton2);
        next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String description = desc.getText().toString();
                String resto_name = res_name.getText().toString();
                boolean Cafeis = Cafe.isChecked();
                boolean Buffetis = Buffet.isChecked();
                boolean Dessertis = Dessert.isChecked();
                boolean Baris = Bar.isChecked();
                boolean Grillis = Grill.isChecked();
                boolean Lutong_Bahayis = Lutong_Bahay.isChecked();
                boolean Fast_foodis = Fast_food.isChecked();
                boolean Vegis = Veg.isChecked();
                boolean Fine_diningis = Fine_dining.isChecked();
                Bitmap yourSelectedImage = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                img= stream.toByteArray();

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
                                                        img);
                finish();
                }

        });



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

    }


