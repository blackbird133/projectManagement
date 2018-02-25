package com.apps.management.project.deny.projectmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Yedi on 11/29/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {

//    private static final String TAG = SQLiteHandler.class.getSimpleName();
//    private static final int DATABASE_VERSION = 1;
//
//    //Database name
//    private static final String DATABASE_NAME = "project_management";
//
//    //Database Table
//    private static final String TABLE_REGISTER =  "register";
//
//    //Table Column
//    private static final String KEY_ID = "id";
//    private static final String KEY_EMPID = "employee_id";
//    private static final String KEY_UID = "uid";
//    private static final String KEY_USERNAME = "username";
//    private static final String KEY_EMAIL = "email";
//
//    public SQLiteHandler(Context context) {
//        super(context, DATABASE_NAME, null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String CREATE_REGISTER_TABLE = "CREATE TABLE " + TABLE_REGISTER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMPID
//                                        + " TEXT," + KEY_UID + " TEXT," + KEY_USERNAME + " TEXT," + KEY_EMAIL + " TEXT UNIQUE" + ")";
//        sqLiteDatabase.execSQL(CREATE_REGISTER_TABLE);
//
//        Log.d(TAG, "onCreate: Database table created");
//        }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
//
//        onCreate(sqLiteDatabase);
//    }
//
//    public void addUser(String empid, String uid, String username, String email) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_EMPID, empid);
//        values.put(KEY_UID, uid);
//        values.put(KEY_USERNAME, username);
//        values.put(KEY_EMAIL, email);
//
//        long id = sqLiteDatabase.insert(TABLE_REGISTER, null, values);
//        sqLiteDatabase.close();
//
//        Log.d(TAG, "addUser: New user inserted into sqlite: " + id);
//    }
//
//    public HashMap<String, String> getUserDetails() {
//        HashMap<String, String> user =  new HashMap<String, String>();
//        String selectQuery = "SELECT * FROM " + TABLE_REGISTER;
//
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        if (cursor.getCount() > 0) {
//            user.put("empid", cursor.getString(1));
//            user.put("uid", cursor.getString(2));
//            user.put("username", cursor.getString(3));
//            user.put("email", cursor.getString(4));
//        }
//        cursor.close();
//        sqLiteDatabase.close();
//
//        Log.d(TAG, "getUserDetails: Fetching user from sqlite " + user.toString());
//
//        return user;
//    }
//
//    public void deleteUser() {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        sqLiteDatabase.delete(TABLE_REGISTER, null, null);
//        sqLiteDatabase.close();
//
//        Log.d(TAG, "deleteUser: Deleted all user info from sqlite");
//    }


    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String name, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Email
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
