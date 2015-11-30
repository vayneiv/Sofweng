package com.example.jerem.eatsmart;

import android.content.ContentResolver;
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
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class details_restaurant extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    Bundle extras;
    EditText desc,res_name,contact;
    ImageButton next;
    Button sel_Image;
    String userName,password;
    byte[] img;
    ImageView imageView;
    Intent to_details_restaurant2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_restaurant);
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

        TextView txt = (TextView) findViewById(R.id.textView53);
        Typeface font = Typeface.createFromAsset(getAssets(), "Kenzo Regular.otf");
        txt.setTypeface(font);
        TextView txt1 = (TextView) findViewById(R.id.textView52);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "basictitlefont.otf");
        txt1.setTypeface(font1);
        TextView txt2 = (TextView) findViewById(R.id.textView25);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "basictitlefont.otf");
        txt2.setTypeface(font2);
        TextView txt3= (TextView) findViewById(R.id.textView7);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "basictitlefont.otf");
        txt3.setTypeface(font3);
        to_details_restaurant2 = new Intent(this, details_restaurant.class);
        res_name=(EditText)findViewById(R.id.editText4);
        desc=(EditText)findViewById(R.id.editText6);
        contact=(EditText)findViewById(R.id.editText10);
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
                String contactno=contact.getText().toString();
                Bitmap yourSelectedImage = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                img= stream.toByteArray();
                if(resto_name.equals(""))//add other textfields and/or checkboxes
                {
                    Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                }
                else{
                    to_details_restaurant2.putExtra("Username", userName);
                    to_details_restaurant2.putExtra("Password", password);
                    to_details_restaurant2.putExtra("desc", description);
                    to_details_restaurant2.putExtra("restau", resto_name);
                    to_details_restaurant2.putExtra("contact", contactno);
                    to_details_restaurant2.putExtra("Logo", img);
                    startActivity(to_details_restaurant2);
                    finish();
                }
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


