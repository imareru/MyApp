package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
 
public class DatabaseAdapter {
 
    private DatabaseHelper dbHelper;
    private SQLiteDatabase users;
 
    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }
 
    public DatabaseAdapter open(){
        users = dbHelper.getWritableDatabase();
        return this;
    }
 
    public void close(){
        dbHelper.close();
    }
 
    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_AGE, DatabaseHelper.COLUMN_CITY};
        return  users.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }
 
    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));
            String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CITY));
            users.add(new User(id, name, year, city));
        }
        cursor.close();
        return  users;
    }
 
    public long getCount(){
        return DatabaseUtils.queryNumEntries(users, DatabaseHelper.TABLE);
    }
 
    public User getUser(long id){
        User user = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = users.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));
            String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CITY));
            user = new User(id, name, year, city);
        }
        cursor.close();
        return  user;
    }
 
    public long insert(User user){
 
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_AGE, user.getAge());
        cv.put(DatabaseHelper.COLUMN_CITY, user.getCity());
 
        return  users.insert(DatabaseHelper.TABLE, null, cv);
    }
 
    public long delete(long userId){
 
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return users.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }
 
    public long update(User user){
 
        String whereClause = DatabaseHelper.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(DatabaseHelper.COLUMN_AGE, user.getAge());
        cv.put(DatabaseHelper.COLUMN_CITY, user.getCity());

        return users.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}