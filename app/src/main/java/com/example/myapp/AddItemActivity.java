package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameBox;
    private EditText ageBox;
    private EditText cityBox;
    Button deleteButton;

    private DatabaseAdapter adapter;
    private long userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameBox = findViewById(R.id.nameEdit);
        ageBox = findViewById(R.id.ageEdit);
        cityBox = findViewById(R.id.cityEdit);
        deleteButton = findViewById(R.id.button6);
        adapter = new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            User user = adapter.getUser(userId);
            nameBox.setText(user.getName());
            ageBox.setText(String.valueOf(user.getAge()));
            cityBox.setText(user.getCity());
            adapter.close();
        } else {
            // скрываем кнопку удаления
            deleteButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){
        String name = nameBox.getText().toString();
        int age = Integer.parseInt(ageBox.getText().toString());
        String city = cityBox.getText().toString();
        User user = new User(userId, name, age, city);

        adapter.open();
        if (userId > 0) {
            adapter.update(user);
        } else {
            adapter.insert(user);
        }
        adapter.close();
    }

    public void delete(View view){
        adapter.open();
        adapter.delete(userId);
        adapter.close();
    }


    public void turnBack(View view){
        Intent goBackToDB = new Intent(this, DataBaseActivity.class);
        goBackToDB.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //super.onDestroy();
        startActivity(goBackToDB);

    }
}