package com.example.jarvis.dbtest;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static int Version = 1;
    private static String DB_NAME="TEST.db";
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
    }
    public MySQLiteOpenHelper(Context context,String name,int version){this(context,name,null,version);}
    public MySQLiteOpenHelper(Context context,int version){
        this(context,DB_NAME,null,version);
    }
    public MySQLiteOpenHelper(Context context)
    {
        this(context, DB_NAME,null, Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(id int primary key,name varchar(200))";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
