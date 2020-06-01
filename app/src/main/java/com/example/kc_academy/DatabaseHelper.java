package com.example.kc_academy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DatabaseHelper extends SQLiteOpenHelper {
    static final String Row_ID ="id";
    static final  String Name = "text";
    //Initialize database
    private static final String DATABASE_NAME = "database_name";
    private static final String TABLE_NAME = "table_name";


    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Table
        String createTable = "create table "+ TABLE_NAME+
                "(id, INTEGER PRIMERY_KEY , txt TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public boolean addText(String text){
        //getwriteble database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //create values into database
        ContentValues contentValues = new ContentValues();
        contentValues.put("txt",text);
        //add values into database
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return true;
    }

    public ArrayList getAllText(){
        //get readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        //create cursor to select all values
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME
        ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("txt")));
            cursor.moveToNext();
        }
        return arrayList;
    }


    public void deleteName(String text) {
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "DELETE FROM " + DATABASE_NAME + "WHERE" + text + "";
        db.execSQL(query);

    }

}
