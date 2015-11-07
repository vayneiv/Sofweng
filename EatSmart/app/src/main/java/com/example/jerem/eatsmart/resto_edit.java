package com.example.jerem.eatsmart;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    EditText desc;
    RadioButton Cafe,Buffet,Bar,Grill,Lutong_Bahay,Dessert,Fast_food,Veg,Fine_dining;
    ImageButton next;
    Button sel_Image;
    String userName,password;
    Uri selectedImage;
    int pack;
    RadioGroup pricerg,locrg;
    int price;
    int loc;
    LoginDataBaseAdapter loginDataBaseAdapter;
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
                pack=1;
            }
            else
            {
                userName= extras.getString("Username");
                password=extras.getString("Password");
                pack=extras.getInt("package");
            }
        }
        desc=(EditText)findViewById(R.id.editText3);
        Cafe=(RadioButton)findViewById(R.id.radioButton7);
        Buffet=(RadioButton)findViewById(R.id.radioButton8);
        Dessert=(RadioButton)findViewById(R.id.radioButton9);
        Bar=(RadioButton)findViewById(R.id.radioButton10);
        Grill=(RadioButton)findViewById(R.id.radioButton11);
        Lutong_Bahay=(RadioButton)findViewById(R.id.radioButton12);
        Fast_food=(RadioButton)findViewById(R.id.radioButton20);
        Veg=(RadioButton)findViewById(R.id.radioButton21);
        Fine_dining=(RadioButton)findViewById(R.id.radioButton22);
        pricerg=(RadioGroup)findViewById(R.id.radioGroup2);
        locrg=(RadioGroup)findViewById(R.id.radioGroup4);

        sel_Image=(Button)findViewById(R.id.button12);
        sel_Image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        next=(ImageButton)findViewById(R.id.imageButton7);
        next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String description = desc.getText().toString();
                boolean Cafeis = Cafe.isChecked();
                boolean Buffetis = Buffet.isChecked();
                boolean Dessertis = Dessert.isChecked();
                boolean Baris = Bar.isChecked();
                boolean Grillis = Grill.isChecked();
                boolean Lutong_Bahayis = Lutong_Bahay.isChecked();
                boolean Fast_foodis = Fast_food.isChecked();
                boolean Vegis = Veg.isChecked();
                boolean Fine_diningis = Fine_dining.isChecked();
                byte[] image=convertImageToByte(selectedImage);
                if(pricerg.getCheckedRadioButtonId()==R.id.radioButton29){
                    price=1;
                } else if(pricerg.getCheckedRadioButtonId()==R.id.radioButton30){
                    price=2;
                } else if(pricerg.getCheckedRadioButtonId()==R.id.radioButton31){
                    price=3;
                } else if(pricerg.getCheckedRadioButtonId()==R.id.radioButton32){
                    price=4;
                } else{
                    price=0;
                }
                if(locrg.getCheckedRadioButtonId()==R.id.radioButton33){
                    loc=1;
                } else if(locrg.getCheckedRadioButtonId()==R.id.radioButton34){
                    loc=2;
                } else if(locrg.getCheckedRadioButtonId()==R.id.radioButton35){
                    loc=3;
                } else if(locrg.getCheckedRadioButtonId()==R.id.radioButton36){
                    loc=4;
                } else if(locrg.getCheckedRadioButtonId()==R.id.radioButton37){
                    loc=5;
                } else if(locrg.getCheckedRadioButtonId()==R.id.radioButton38) {
                    loc = 6;
                } else{
                    loc=0;
                }
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
                                                        image);
                finish();
            }
        });



    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imageView5);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
    public byte[] convertImageToByte(Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }
}