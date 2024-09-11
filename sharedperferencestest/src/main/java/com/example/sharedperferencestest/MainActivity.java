package com.example.sharedperferencestest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(view -> {
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("name","Tom");
            editor.putInt("age", 28);
            editor.putBoolean("married",false);
            editor.apply();
        });

        Button restoreData  = (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(view -> {
            SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
            String name = prefs.getString("name", "");
            int age = prefs.getInt("age", 0);
            boolean married = prefs.getBoolean("married", false);
            Log.d("MainActivity","name is "+ name);
            Log.d("MainActivity","age is "+ age);
            Log.d("MainActivity","married is "+ married);
        });
    }
}