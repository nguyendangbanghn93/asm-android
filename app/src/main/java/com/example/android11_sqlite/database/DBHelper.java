package com.example.android11_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "EXPENSE";
    public static final int DB_VERSION = 1;

    public static String TABLE_NAME = "TBL_USER";
    public static String ID = "id";
    public static String NAME = "name";
    public static String GENDER = "gender";
    public static String DES = "des";

    public static String TABLE_NAME_3 = "TBL_EXPENSES";
    public static String EXPENSE_ID = "expense_id";
    public static String EXPENSE_NAME = "expense_name";
    public static String EXPENSE_DES = "expense_des";
    public static String EXPENSE_CONTENT = "expense_content";
    public static String EXPENSE_TOTAL = "expense_total";
    public static String EXPENSE_DATE = "expense_date";
    public static String EXPENSE_CATE_ID = "cate_id";

    public static String TABLE_NAME_2 = "TBL_CATE";
    public static String CATE_ID = "cate_id";
    public static String CATE_NAME = "cate_name";

    private static final String CATE_VALUE_1 = "Shopping";
    private static final String CATE_VALUE_2 = "Food";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                GENDER + " TEXT, " +
                DES + " TEXT)";

        String sql5 = "CREATE TABLE " + TABLE_NAME_3 + " ( " +
                EXPENSE_ID + " INTEGER PRIMARY KEY, " +
                EXPENSE_NAME + " TEXT, " +
                EXPENSE_DES + " TEXT, " +
                EXPENSE_CONTENT + " TEXT, " +
                EXPENSE_TOTAL + " TEXT, " +
                EXPENSE_CATE_ID + " INT, " +
                EXPENSE_DATE + " TEXT)";

        String sql2 = "CREATE TABLE " + TABLE_NAME_2 + " ( " +
                CATE_ID + " INTEGER PRIMARY KEY, " +
                CATE_NAME + " TEXT)";

        String sql3 = "INSERT INTO " + TABLE_NAME_2 + " (" + CATE_NAME + ") " + "VALUES " + "(\"" + CATE_VALUE_1 + "\")";
        String sql4 = "INSERT INTO " + TABLE_NAME_2 + " (" + CATE_NAME + ") " + "VALUES " + "(\"" + CATE_VALUE_2 + "\")";

        db.execSQL(sql5);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        String sql3 = "DROP TABLE IF EXISTS " + TABLE_NAME_3;
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME_2;
        db.execSQL(sql3);
        db.execSQL(sql2);

        db.execSQL(sql);
        onCreate(db);
    }

    public String addUser(String user, String gender, String des) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,user);
        contentValues.put(GENDER,gender);
        contentValues.put(DES,des);
        long isAdd = db.insert(TABLE_NAME_3, null, contentValues);
        if (isAdd == -1) {
            return "Add Fail";
        }
        db.close();
        return "Add success";
    }

    public String updateUser(int id, String user, String gender, String des) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,user);
        contentValues.put(GENDER,gender);
        contentValues.put(DES,des);
        int isUpdate = db.update(TABLE_NAME, contentValues, ID+ " = ? " , new String[] {id+""});
        if (isUpdate > 0) {
            return "Update success";
        }
        db.close();
        return "Update Fail";
    }

    public String deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int isDelete = db.delete(TABLE_NAME, ID+ " = ? ", new String[]{id+""});
        if (isDelete > 0) {
            return "Delete success";
        }
        db.close();
        return "Delete Fail";
    }

    public Cursor getAllUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT rowid _id,* FROM " +TABLE_NAME_3;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public boolean addExpense(String name, @Nullable String des, @Nullable String content, String total, String date, @Nullable int cateId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_NAME, name);
        contentValues.put(EXPENSE_DES, des);
        contentValues.put(EXPENSE_CONTENT, content);
        contentValues.put(EXPENSE_TOTAL, total);
        contentValues.put(EXPENSE_DATE, date);
        contentValues.put(EXPENSE_CATE_ID, cateId);

        long isAdd = db.insert(TABLE_NAME_3, null, contentValues);
        if (isAdd == -1) {
            return false;
        }
        db.close();
        return true;
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT rowid _id,* FROM " +TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
