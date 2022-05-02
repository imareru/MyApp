package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<State>();
    ListView usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        // начальная инициализация списка
        setInitialData();
        usersList = findViewById(R.id.usersList);
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.database_item, states);
        usersList.setAdapter(stateAdapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent openEdit = new Intent(DataBaseActivity.this, EditActivity.class);
                startActivity(openEdit);
            }
        };
        usersList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){
        //получаем данные из БД
    }

    public void goBack(View view) {
        Intent backToMain = new Intent(this, MainActivity.class);
        backToMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(backToMain);
    }

}
