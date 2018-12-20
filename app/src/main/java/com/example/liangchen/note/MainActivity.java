package com.example.liangchen.note;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private MyDatabaseHelper dbHelper;
    private List<Page> pageList = new ArrayList<>();
    private Handler handler;
    private DataBaseOperate pageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            ((ActionBar) actionbar).hide();
        }

        pageDao = new DataBaseOperate(this);
        pageList = pageDao.selectAll();
        PageAdapter adapter = new PageAdapter(MainActivity.this, R.layout.acticity_listviewxml, pageList);
        ListView listView = (ListView) findViewById(R.id.Main_ET);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        Button button_Out = (Button) findViewById(R.id.Out);
        Button button_Edit = (Button) findViewById(R.id.Add);
        Button button_search = (Button) findViewById(R.id.button_search);
        EditText ET_search = (EditText) findViewById(R.id.edittext_search);
        dbHelper = new MyDatabaseHelper(this, "Note.db", null, 1);

        button_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pageDao.insert("");
                pageList = pageDao.selectAll();
               SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
              editor.putInt("id", pageList.get(0).getId());
                editor.apply();
                startActivity(new Intent(MainActivity.this, MainActivity_content.class));
            }
        });
        button_search.setOnClickListener(new View.OnClickListener() {
            EditText ET_search = (EditText) findViewById(R.id.edittext_search);

            @Override
            public void onClick(View v) {
                //显示出查询内容
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String inputText = ET_search.getText().toString();
                        pageList = pageDao.Search(inputText);
                        handler.post(runnable);
                    }
                }).start();*/
                String inputText = ET_search.getText().toString();
                Intent intent = new Intent(MainActivity.this,MainActivity_Search.class);
                intent.putExtra("extra_data",inputText);

                startActivity(intent);
                ET_search.getText().clear();
            }
        });
    }


 /*   Runnable runnable = new Runnable() {
        @Override
        public void run() {
            PageAdapter adapter = new PageAdapter(MainActivity.this, R.layout.acticity_listviewxml, pageList);
            ListView listView = (ListView) findViewById(R.id.Main_ET);
            listView.setAdapter(adapter);
        }
    };*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putInt("id", pageList.get(i).getId());
        editor.apply();
        startActivity(new Intent(MainActivity.this, MainActivity_content.class));
    }
}
