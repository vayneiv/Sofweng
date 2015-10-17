package com.example.jerem.eatsmart;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RestauDataBaseAdapter
{
    static final String DATABASE_NAME = "restau.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE_1 = "create table "+"restau"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db1;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  RestauDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  RestauDataBaseAdapter open() throws SQLException
    {
        db1 = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db1.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db1;
    }

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);

        // Insert the row into your table
        db1.insert("restau", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db1.delete("restau", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSingleEntry(String userName)
    {
        String returnVar;
        Cursor cursor=db1.query("restau", null, " USERNAME=?", null, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            returnVar=DATABASE_NAME;
            return returnVar;
        }
        cursor.moveToFirst();
        returnVar= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return returnVar;
    }
    public void  updateEntry(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db1.update("restau",updatedValues, where, new String[]{userName});
    }
    public void clearDatabase() {
        close();
        context.deleteDatabase(DATABASE_NAME);

    }
}