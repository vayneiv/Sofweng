package com.example.jerem.eatsmart;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoginDataBaseAdapter
{
    public static int nos_calls=0;
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE =
            "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"
                    +"USERNAME  text,PASSWORD text); ";
    static final String DATABASE_CREATE_1 =
            "create table "+"restau"+
            "( " +"ID"+" integer primary key autoincrement,"+
                    "USERNAME  text," +
                    "PASSWORD text," +
                    "RESTAUNAME text," +
                    "DESCRIPTION text," +
                    "Cafe INTEGER DEFAULT 0," +
                    "Buffet INTEGER DEFAULT 0," +
                    "Dessert INTEGER DEFAULT 0," +
                    "Bar INTEGER DEFAULT 0," +
                    "Grill INTEGER DEFAULT 0," +
                    "Lutong_bahay INTEGER DEFAULT 0," +
                    "fast_food INTEGER DEFAULT 0," +
                    "Veg INTEGER DEFAULT 0," +
                    "Fine_dining INTEGER DEFAULT 0," +
                    "LOCATION integer,"+
                    "price interger"+
                    "image BLOB); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntryrestau(String userName,
                                  String password,
                                  String RestauName,
                                  String Description,
                                  boolean Cafe,
                                  boolean Buffet,
                                  boolean Dessert,
                                  boolean Bar,
                                  boolean Grill,
                                  boolean Lutong_bahay,
                                  boolean fast_food,
                                  boolean Veg,
                                  boolean Fine_dining,
                                  int LOCATION,
                                  int price,
                                  byte []image)
    {
        Log.i(userName,"insertEntryrestau");
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        newValues.put("RESTAUNAME", RestauName);
        newValues.put("DESCRIPTION",Description);
        newValues.put("Cafe",Cafe);
        newValues.put("Buffet",Buffet);
        newValues.put("Dessert",Dessert);
        newValues.put("Bar",Bar);
        newValues.put("Grill",Grill);
        newValues.put("Lutong_bahay",Lutong_bahay);
        newValues.put("fast_food",fast_food);
        newValues.put("Veg",Veg);
        newValues.put("Fine_dining",Fine_dining);
        newValues.put("LOCATION",LOCATION);
        newValues.put("price",price);
        newValues.put("image",image);

        // Insert the row into your table
        db.insert("restau", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public void insertEntrycusto(String userName,String password)
    {
        nos_calls += 1;
        Log.i(userName, "insertEntrycusto");
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String UserName,String table)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete(table, where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSingleEntryrestau(String userName)
    {
        String returnVar;
        Cursor cursor=db.query("restau", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            returnVar="NOT EXIST";
            return returnVar;
        }
        cursor.moveToFirst();
        returnVar= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return returnVar;
    }
    public String getrestauname(String userName)
    {
    String returnVar;
    Cursor cursor=db.query("restau", null, " USERNAME=?", new String[]{userName}, null, null, null);
    if(cursor.getCount()<1) // UserName Not Exist
    {
        cursor.close();
        returnVar="NOT EXIST";
        return returnVar;
    }
    cursor.moveToFirst();
    returnVar= cursor.getString(cursor.getColumnIndex("RESTAUNAME"));
    cursor.close();
    return returnVar;
    }
    public String getrestauloc(String userName)
    {
        String returnVar;
        Cursor cursor=db.query("restau", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            returnVar="NOT EXIST";
            return returnVar;
        }
        cursor.moveToFirst();
        returnVar= cursor.getString(cursor.getColumnIndex("LOCATION"));
        cursor.close();
        return returnVar;
    }
    public String getrestaudesc(String userName)
    {
        String returnVar;
        Cursor cursor=db.query("restau", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            returnVar="NOT EXIST";
            return returnVar;
        }
        cursor.moveToFirst();
        returnVar= cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
        cursor.close();
        return returnVar;
    }
    public String getSingleEntrycusto(String userName)
    {
        String returnVar;
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            returnVar="NOT EXIST";
            return returnVar;
        }
        cursor.moveToFirst();
        returnVar= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return returnVar;
    }
    public void  updaterestaudesc(String userName,String description)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", getSingleEntryrestau(userName));
        updatedValues.put("RESTAUNAME", getrestauname(userName));
        updatedValues.put("DESCRIPTION",description);
        updatedValues.put("LOCATION", getrestauloc(userName));
        String where="USERNAME = ?";
        db.update("restau",updatedValues, where, new String[]{userName});
    }
    public void clearDatabase() {
        close();
        context.deleteDatabase(DATABASE_NAME);
    }
}