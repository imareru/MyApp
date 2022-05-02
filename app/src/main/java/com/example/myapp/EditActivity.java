package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void turnBack(View view){
        Intent goBackToDB = new Intent(this, DataBaseActivity.class);
        goBackToDB.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(goBackToDB);
    }

    public void confirmEdit(View view){
        //подтверждаем изменения
    }
}