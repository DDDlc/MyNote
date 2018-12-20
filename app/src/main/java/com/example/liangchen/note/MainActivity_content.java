package com.example.liangchen.note;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity_content extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText editText;
    private DataBaseOperate pageDao;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            ((ActionBar) actionbar).hide();
        }
        Button button_Success = (Button) findViewById(R.id.Success);
        Button button_delete =(Button) findViewById(R.id.Delete);
        final EditText editText=(EditText) findViewById(R.id.ET);
        pageDao = new DataBaseOperate(this);
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 0);
        Page page = pageDao.selectPage(id);
        editText.setText(page.getContent());

        dbHelper = new MyDatabaseHelper(this,"NOTE.db",null,1);
        button_Success.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pageDao.update(editText.getText().toString(), id);//数据入库
                startActivity(new Intent(MainActivity_content.this, MainActivity.class));
                Toast.makeText(MainActivity_content.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pageDao.delete(id);//删
                startActivity(new Intent(MainActivity_content.this, MainActivity.class));
                finish();
            }
        });
    }
}
