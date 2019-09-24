package com.sjsu.studentcenter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String ID = "ID";
    public static final String FIRST_NAME = "FIRSTNAME";
    public static final String MIDDLE_NAME = "MIDDLENAME";
    public static final String LAST_NAME = "LASTNAME";
    public static final String MOBILE = "MOBILE";
    public static final String ADDRESS = "ADDRESS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,MIDDLENAME TEXT,LASTNAME TEXT,MOBILE TEXT, ADDRESS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int id,String firstName, String middleName, String lastName, String mobile, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(FIRST_NAME,firstName);
        contentValues.put(MIDDLE_NAME,middleName);
        contentValues.put(LAST_NAME,lastName);
        contentValues.put(MOBILE,mobile);
        contentValues.put(ADDRESS,address);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getStudentDetails(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+ID+"=?",new String[] {studentId});
        return res;
    }

    public boolean updateData(String id,String firstName, String middleName, String lastName, String mobile, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(ID,id);
        row.put(FIRST_NAME,firstName);
        row.put(MIDDLE_NAME,middleName);
        row.put(LAST_NAME,lastName);
        row.put(MOBILE,mobile);
        row.put(ADDRESS,address);
        db.update(TABLE_NAME, row, ID+" = ?",new String[] { id });
        return true;
    }

}