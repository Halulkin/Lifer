package com.halulkin.lifer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.halulkin.lifer.TargetsFragment.TargetsModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LiferDB";

    private static final String TABLE_NAME = "Targets";

    private static final String KEY_TARGET_ID = "targetId";
    private static final String KEY_TARGET_NAME = "targetName";
    private static final String KEY_TARGET_DATE = "targetDate";
    private static final String KEY_TARGET_STATUS = "targetStatus";

    private static final String[] COLUMNS = {KEY_TARGET_ID, KEY_TARGET_NAME, KEY_TARGET_DATE,
            KEY_TARGET_STATUS};


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Targets ( "
                + "targetId INTEGER PRIMARY KEY AUTOINCREMENT, " + "targetName TEXT, "
                + "targetDate TEXT, " + "targetStatus INTEGER )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteTarget(TargetsModel target) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(target.getTargetId())});
        db.close();
    }

    public TargetsModel getTarget(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        TargetsModel target = new TargetsModel();
        target.setTargetId(Integer.parseInt(cursor.getString(0)));
        target.setTargetName(cursor.getString(1));
        target.setTargetDate(cursor.getString(2));
        target.setTargetStatus(Integer.parseInt(cursor.getString(3)));

        return target;
    }

    public List<TargetsModel> getAllTargets() {

        List<TargetsModel> targets = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        TargetsModel target = null;

        if (cursor.moveToFirst()) {
            do {
                target = new TargetsModel();
                target.setTargetId(Integer.parseInt(cursor.getString(0)));
                target.setTargetName(cursor.getString(1));
                target.setTargetDate(cursor.getString(2));
                target.setTargetStatus(Integer.parseInt(cursor.getString(3)));

                targets.add(target);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return targets;
    }

    public void addTarget(TargetsModel target) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TARGET_NAME, target.getTargetName());
        values.put(KEY_TARGET_DATE, target.getTargetDate());
        values.put(KEY_TARGET_STATUS, target.getTargetStatus());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void read() {
        Cursor cursor = getWritableDatabase().query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_TARGET_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_TARGET_NAME);
            int dateIndex = cursor.getColumnIndex(DBHelper.KEY_TARGET_DATE);
            int statusIndex = cursor.getColumnIndex(DBHelper.KEY_TARGET_STATUS);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", email = " + cursor.getString(dateIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog", "0 rows");

        cursor.close();
    }

    public void updateTarget(TargetsModel target) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TARGET_NAME, target.getTargetName());
        values.put(KEY_TARGET_DATE, target.getTargetDate());
        values.put(KEY_TARGET_STATUS, target.getTargetStatus());

        // updating row
        db.update(TABLE_NAME, values, KEY_TARGET_ID + " = ?",
                new String[]{String.valueOf(target.getTargetId())});
    }

}