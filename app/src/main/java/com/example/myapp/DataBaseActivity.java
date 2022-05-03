package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBaseActivity extends AppCompatActivity {

    private ListView usersList;
    ArrayAdapter<User> arrayAdapter;

    ConstraintLayout main_layout;
    private GestureDetectorCompat lSwipeDetector;
    int i;

    private static final int SWIPE_MIN_DISTANCE = 130;
    private static final int SWIPE_MAX_DISTANCE = 300;
    private static final int SWIPE_MIN_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        i = 1;
        lSwipeDetector = new GestureDetectorCompat(this, new MyGestureListener());
        main_layout = (ConstraintLayout) findViewById(R.id.userLayout);
        main_layout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return lSwipeDetector.onTouchEvent(motionEvent);
            }
        });


        usersList = findViewById(R.id.usersList);
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = arrayAdapter.getItem(position);
                if(user!=null) {
                    Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                    intent.putExtra("id", user.getId());
                    startActivity(intent);
                }
            }
        });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e){
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_DISTANCE)
                return false;
            if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_MIN_VELOCITY){
                AddItemActivity del = new AddItemActivity();
                del.delete(usersList.getSelectedView());
            }
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<User> users = adapter.getUsers();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        usersList.setAdapter(arrayAdapter);
        adapter.close();
    }

    public void goBack(View view) {
        Intent backToMain = new Intent(this, MainActivity.class);
        backToMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(backToMain);
    }

    public void addNewItem(View view){
        Intent addItem = new Intent(this, AddItemActivity.class);
        startActivity(addItem);
    }

}
