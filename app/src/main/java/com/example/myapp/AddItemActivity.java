package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
    }

    public void turnBack(View view){
        Intent goBackToDB = new Intent(this, DataBaseActivity.class);
        goBackToDB.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(goBackToDB);
    }

    public void confirmItem(View view){
        //добавление записи

    }
}