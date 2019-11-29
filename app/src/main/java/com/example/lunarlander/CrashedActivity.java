package com.example.lunarlander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CrashedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crashed);
    }

    public void again(View view){
        finish();
    }
    public void menu(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
