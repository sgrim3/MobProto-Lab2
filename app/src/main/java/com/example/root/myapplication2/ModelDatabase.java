package com.example.root.myapplication2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;

/**
 * Created by Haley on 9/15/14
 */
public class ModelDatabase extends SQLiteOpenHelper {
    //Table Name
    public static final String TABLE_NAME = "chats";
    //Table Fields
    public static final String MESSAGE_ID = "messageid";
    public static final String CHAT_VISIBLE = "visible";
    public static final String USERNAME = "username";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";
    // ModelDatabase creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "("
            + MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CHAT_VISIBLE + " TEXT NOT NULL, "
            + USERNAME + " TEXT NOT NULL, "
            + MESSAGE + " TEXT NOT NULL, "
            + TIMESTAMP + " TEXT NOT NULL)";
    //Database Info
    private static final String DATABASE_NAME = "ChatAppDatabase";
    private static final int DATABASE_VERSION = 1;

    //Default Constructor
    public ModelDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
//OnCreate Method - creates the ModelDatabase
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
//OnUpgrade Method - upgrades ModelDatabase if applicable
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(ModelDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}