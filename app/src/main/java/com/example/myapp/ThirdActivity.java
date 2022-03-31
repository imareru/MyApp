package com.example.myapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private final static String TAG = "Third Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle arguments = getIntent().getExtras();

        TextView setUserInformation = findViewById(R.id.aboutUser);

        if(arguments!=null){
            String name = arguments.get("user_name").toString();
            String city = arguments.getString("user_city");
            String age = arguments.getString("user_age");
            setUserInformation.setText(name + ", " + age + ", из города " + city);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ошибка")
                    .setMessage("Вернитесь назад, вы заполнили не все поля")
                    .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Закрываем окно
                            dialog.cancel();
                        }
                    });
            builder.create();
            Intent backToSecond = new Intent(this, SecondActivity.class);
            backToSecond.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(backToSecond);
        }
    }

    public void goBack(View view) {
        Intent backToMain = new Intent(this, MainActivity.class);
        backToMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(backToMain);
    }

    public void close(View view){
        //System.exit(1);
        //finish();
        //Log.d(TAG, "App is closed");
    }
}