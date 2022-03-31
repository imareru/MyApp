package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void goTo3(View view) {

        EditText nameBox = findViewById(R.id.inputName);
        EditText ageBox = findViewById(R.id.inputAge);
        EditText cityBox = findViewById(R.id.inputCity);
        String userName = nameBox.getText().toString();
        String userAge = ageBox.getText().toString();
        String userCity = cityBox.getText().toString();

        Intent thirdActivity = new Intent(this, ThirdActivity.class);
        thirdActivity.putExtra("user_name", userName);
        thirdActivity.putExtra("user_age", userAge);
        thirdActivity.putExtra("user_city", userCity);
        startActivity(thirdActivity);
    }
}