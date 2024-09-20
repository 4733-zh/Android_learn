package com.example.bundlefragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add=findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.add){
            addFragmentToView(new FirstFragment());
        }
    }

    private void addFragmentToView(FirstFragment firstFragment) {
        Bundle bundle = new Bundle();
        bundle.putString("message","恭喜你，通过bundle传递参数成功");
        firstFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null)
                .add(R.id.fragment_layout,firstFragment)
                .commit();
    }
}