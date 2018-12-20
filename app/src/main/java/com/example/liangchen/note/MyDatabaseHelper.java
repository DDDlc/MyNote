package com.example.liangchen.note;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_NOTE="create table Note ("
            +"id integer primary key autoincrement,"
            +"title text,"
            +"content text,"
            +"date text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }
}
