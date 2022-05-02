package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.open_dataExchange:
                Intent secondActivity = new Intent(this, SecondActivity.class);
                startActivity(secondActivity);
                return true;
            case R.id.open_flags:
                Intent flagActivity = new Intent(this, CountriesActivity.class);
                startActivity(flagActivity);
                return true;
            case R.id.open_outMemory:
                Intent sdActivity = new Intent(this, SDActivity.class);
                startActivity(sdActivity);
                return true;
            case R.id.open_innerMemory:
                Intent filesActivity = new Intent(this, FilesActivity.class);
                startActivity(filesActivity);
                return true;
            case R.id.open_database:
                Intent DBActivity = new Intent(this, DataBaseActivity.class);
                startActivity(DBActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}