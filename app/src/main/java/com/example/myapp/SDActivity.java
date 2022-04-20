package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDActivity extends AppCompatActivity {

    private final static String FILE_NAME = "document.txt";

    private File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdactivity);
    }

    public void checkStorage(View view){
        TextView storageState = findViewById(R.id.isStorageAvailable);
        TextView freeSpace = findViewById(R.id.space);
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            storageState.setText("Только для чтения");
        } else if (Environment.MEDIA_MOUNTED.equals(state)){
            //File myFile = new File("/storage/sdcard0/Android/data/com.example.myapp/files/document.txt");
            long getMemory = getExternalPath().getFreeSpace();
            storageState.setText("Доступно для записи");
            freeSpace.setText(String.valueOf(getMemory) + " бит");
            //freeSpace.setText(myFile.getFreeSpace());
        } else {
            storageState.setText("Неизвестная ошибка");
        }

    }

    public void goBack(View view) {
        Intent backToMain = new Intent(this, MainActivity.class);
        backToMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(backToMain);
    }

    public void saveText(View view){
        try(FileOutputStream fos = new FileOutputStream(getExternalPath())) {
            EditText textBox = findViewById(R.id.inputTextSD);
            String text = textBox.getText().toString();
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // открытие файла
    public void openText(View view){
        TextView textView = findViewById(R.id.outputTextSD);
        File file = getExternalPath();
        // если файл не существует, выход из метода
        if(!file.exists()) return;
        try(FileInputStream fin =  new FileInputStream(file)) {
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    getFreeSpace()
//    getTotalSpace()
}