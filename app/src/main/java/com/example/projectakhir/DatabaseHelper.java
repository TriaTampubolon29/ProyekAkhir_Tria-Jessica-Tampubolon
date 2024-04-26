package com.example.projectakhir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "test.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(email TEXT PRIMARY KEY, password TEXT)");
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS motivation(id INTEGER PRIMARY KEY AUTOINCREMENT, topik TEXT NOT NULL, motivation TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists motivation");
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM motivation";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY,null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("topik", cursor.getString(1));
                map.put("motivation", cursor.getString(2));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String topik, String motivation){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO motivation (topik, motivation) VALUES('"+topik+"', '"+motivation+"')";
        database.execSQL(QUERY);
    }

    public void update(int id, String topik, String motivation){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("topik", topik);
        values.put("motivation", motivation);

        // Persiapkan klausa WHERE dengan ID yang sesuai
        String whereClause = "id=?";
        String[] whereArgs = { String.valueOf(id) };

        // Lakukan pembaruan data di dalam tabel 'motivation'
        database.update("motivation", values, whereClause, whereArgs);
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM motivation WHERE id = "+id;
        database.execSQL(QUERY);
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}