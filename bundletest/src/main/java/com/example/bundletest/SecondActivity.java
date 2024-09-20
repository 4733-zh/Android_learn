package com.example.bundletest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        TextView textView = findViewById(R.id.text);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Person person = (Person)bundle.getSerializable("person");
        String name = person.getName();
        int age = person.getAge();
        textView.setText("age = "+age+", name = "+name);
    }
}