package com.example.rajnish.mygmail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE = "database.db";
    public static String TABLE = "mytable";
    public static String PERSON_ID = "person_id";
    public static String FROM_NAME = "from_person";
    public static String SUBJECT = "subject";
    public static String MESSAGE = "messaged";
    public static String PROFILE_DP = "profile_pic";
    public static String DATE = "date";


    String tableString;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableString = "CREATE TABLE " + TABLE + "(" + PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FROM_NAME + " VARCHAR(200), " +
                SUBJECT + " VARCHAR(200)," +
                MESSAGE + " VARCHAR, " +
                DATE + " DATE, " +
                PROFILE_DP + " VARCHAR(200)" + ");";

        db.execSQL(tableString);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE + " ;");
    }

    public void insertData(MyGmail myGmail) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FROM_NAME, myGmail.getFrom());
        contentValues.put(SUBJECT, myGmail.getSubject());
        contentValues.put(MESSAGE, myGmail.getMessage());
        contentValues.put(PROFILE_DP, myGmail.getImage_thumbnail());
        contentValues.put(DATE, date());
        db.insert(TABLE, null, contentValues);

    }

    public ArrayList<MyGmail> getData() {
        ArrayList<MyGmail> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE + " ;", null);
        while (cursor.moveToNext()) {
            MyGmail myGmail = new MyGmail();
            String from_name = cursor.getString(cursor.getColumnIndexOrThrow(FROM_NAME));
            String subject = cursor.getString(cursor.getColumnIndexOrThrow(SUBJECT));
            int id_num = cursor.getInt(cursor.getColumnIndexOrThrow(PERSON_ID));
            String message = cursor.getString(cursor.getColumnIndexOrThrow(MESSAGE));
            int image = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(PROFILE_DP)));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
            myGmail.setFrom(from_name);
            myGmail.setSubject(subject);
            myGmail.setMessage(message);
            myGmail.setId_num(id_num);
            myGmail.setImage_thumbnail(image);
            myGmail.setDate(date);
            data.add(myGmail);

        }
        return data;
    }


    String date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }


}

