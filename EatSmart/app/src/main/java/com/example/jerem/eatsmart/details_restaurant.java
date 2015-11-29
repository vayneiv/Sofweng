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
    RadioGroup radioGroupBudget, radioGroupLocation;
    RadioButton radioBudget, radioLocation;
    int checkedIdBudget, checkedIdLocation;
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
        checkedIdBudget = -1;
        checkedIdLocation = -1;
        radioGroupBudget = (RadioGroup)findViewById(R.id.radioGroup7);
        radioGroupLocation = (RadioGroup)findViewById(R.id.radioGroup8);
        radioBudget = (RadioButton)radioGroupBudget.findViewById(radioGroupBudget.getCheckedRadioButtonId());
        radioLocation = (RadioButton)radioGroupLocation.findViewById(radioGroupLocation.getCheckedRadioButtonId());

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

}


