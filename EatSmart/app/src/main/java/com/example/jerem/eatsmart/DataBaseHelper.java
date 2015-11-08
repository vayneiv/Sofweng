package com.example.jerem.eatsmart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
    public DataBaseHelper(Context context, String name,CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_1);
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_2);

    }
    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        _db.execSQL("DROP TABLE IF EXISTS " + "LOGIN");
        _db.execSQL("DROP TABLE IF EXISTS " + "restau");
        _db.execSQL("DROP TABLE IF EXISTS " + "Rate_Comment");

        // create new tables
        onCreate(_db);

    }

}