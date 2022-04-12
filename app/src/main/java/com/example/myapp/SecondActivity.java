package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        int name = nameBox.getText().toString().trim().length();
        int age = ageBox.getText().toString().trim().length();
        int city = cityBox.getText().toString().trim().length();

        if (name==0 || age==0 || city==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ошибка")
                    .setMessage("Вернитесь назад, вы заполнили не все поля")
                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create();
            builder.show();
        } else {
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
}