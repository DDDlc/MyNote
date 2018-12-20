package com.example.liangchen.note;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataBaseOperate {
    private Context context;
    private MyDatabaseHelper dataBase;
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseOperate(Context context) {
        this.context = context;
        dataBase = new MyDatabaseHelper(context, "NOTE.db", null, 1);
        sqLiteDatabase = dataBase.getWritableDatabase();
    }

    public void insert(String content) {
        Page page = new Page();
        page = page.toPage(content);
        sqLiteDatabase.execSQL("INSERT INTO Note (title, date, content) VALUES(?, ?, ?)", new String[]{page.getTitle(), page.getDate(), page.getContent()});
    }

    public void update(String content, int id) {
        Page page = new Page();
        page = page.toPage(content);
        sqLiteDatabase.execSQL("UPDATE Note SET title = ?, date = ?, content = ? WHERE id = ?", new String[]{page.getTitle(), page.getDate(), page.getContent(), id + ""});
    }

    public void delete(int id) {
        sqLiteDatabase.execSQL("DELETE FROM Note WHERE id = ?", new String[]{id + ""});
    }

    public List<Page> selectAll() {
        List<Page> pageList = new ArrayList<>();
        Page page;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Note ORDER BY date DESC", null);
        if (cursor.moveToFirst()) {
            do {
                page = new Page();
                page.setId(cursor.getInt(cursor.getColumnIndex("id")));
                page.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                page.setDate(cursor.getString(cursor.getColumnIndex("date")));
                page.setContent(cursor.getString(cursor.getColumnIndex("content")));
                pageList.add(page);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pageList;
    }
    public List<Page> Search(String input) {
        List<Page> pageList = new ArrayList<>();
        Page page;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Note ORDER BY date DESC ",  null);
        if (cursor.moveToFirst()) {
            do {
                String t=cursor.getString(cursor.getColumnIndex("title"));
                page = new Page();
                if(t.contains(input)) {
                    page.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    page.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    page.setDate(cursor.getString(cursor.getColumnIndex("date")));
                    page.setContent(cursor.getString(cursor.getColumnIndex("content")));
                    pageList.add(page);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pageList;
    }
    public Page selectPage(int id) {
        Page page = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Note WHERE id = ?", new String[]{id + ""});
        if (cursor.moveToFirst()) {
            do {
                page = new Page();
                page.setId(cursor.getInt(cursor.getColumnIndex("id")));
                page.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                page.setDate(cursor.getString(cursor.getColumnIndex("date")));
                page.setContent(cursor.getString(cursor.getColumnIndex("content")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return page;
    }
}
