package com.example.root.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Haley 9/15/14
 */
public class HandlerDatabase {
    //Database Model
    private ModelDatabase model;
    //Database
    private SQLiteDatabase database;
    //All Fields
    private String[] allColumns = {
            ModelDatabase.MESSAGE_ID,
            ModelDatabase.CHAT_VISIBLE,
            ModelDatabase.USERNAME,
            ModelDatabase.MESSAGE,
            ModelDatabase.TIMESTAMP
    };

    //Public Constructor - create connection to Database
    public HandlerDatabase(Context context) {
        model = new ModelDatabase(context);
    }

    /**
     * Add
     */
    public void addChatToDatabase(String visible, String username, String message, String timestamp) {
        ContentValues values = new ContentValues();
        values.put(ModelDatabase.CHAT_VISIBLE, visible);
        values.put(ModelDatabase.USERNAME, username);
        values.put(ModelDatabase.MESSAGE, message);
        values.put(ModelDatabase.TIMESTAMP, timestamp);
        database.insertWithOnConflict(ModelDatabase.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    /**
     * Get
     */
    public ArrayList<ChatObject> getAllChatObjects(){
        return sweepCursor(database.query(ModelDatabase.TABLE_NAME, allColumns, ModelDatabase.CHAT_VISIBLE + " like '%true%'", null, null, null, null));
    }
//    public ArrayList<ChatObject> getChatObjectsByCategory(String cat){
//        return sweepCursor(database.query(
//                ModelDatabase.TABLE_NAME,
//                allColumns,
//                ModelDatabase.KITTY_CATEGORY + " like '%" + cat + "%' AND " + ModelDatabase.KITTY_FAVORITE + " like '%false%' AND " + ModelDatabase.KITTY_VISIBLE + " like '%true%'",
//                null, null, null, null, null
//        ));
//    }
//    public ArrayList<ChatObject> getOwnedChatObjects(){
//        return sweepCursor(database.query(
//                ModelDatabase.TABLE_NAME,
//                allColumns,
//                ModelDatabase.KITTY_FAVORITE + " like '%true%' AND " + ModelDatabase.KITTY_VISIBLE + " like '%true%'",
//                null, null, null,
//                ModelDatabase.KITTY_CATEGORY));
//    }
//    public ChatObject getChatObjectById(String id){
//        return sweepCursor(database.query(
//                ModelDatabase.TABLE_NAME,
//                allColumns,
//                ModelDatabase.KITTY_URL + " like '%" + id + "%'",
//                null, null, null, null
//        )).get(0);
//    }
//    /**
//     * Delete
//     */
//    public void deleteKittiesByCategory(String cat){
//        database.delete(
//                ModelDatabase.TABLE_NAME,
//                ModelDatabase.KITTY_CATEGORY + " like '%" + cat + "%' AND " + ModelDatabase.KITTY_FAVORITE + " like '%false%'",
//                null
//        );
//    }
//    public void deleteKittyById(String id){
//        database.delete(
//                ModelDatabase.TABLE_NAME,
//                ModelDatabase.KITTY_URL + " like '%" + id + "%'",
//                null
//        );
//    }


    /**
     * Additional Helpers
     */
    //Sweep Through Cursor and return a List of Kitties
    private ArrayList<ChatObject> sweepCursor(Cursor cursor) {
        ArrayList<ChatObject> chatObjects = new ArrayList<ChatObject>();
        //Get to the beginning of the cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //Get the ChatObjects
            ChatObject co = new ChatObject(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                    );
            //Add the ChatObject
            chatObjects.add(co);
            //Go on to the next ChatObject
            cursor.moveToNext();
        }
       return chatObjects;
    }

    //Get Writable Database - open the database
    public void open() {
        database = model.getWritableDatabase();
    }
}


