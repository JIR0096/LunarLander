package com.example.lunarlander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    protected void controlByGyro(View view)
    {
        Settings.controlWithGyro = true;
    }

    protected void controlByTouch(View view)
    {
        Settings.controlWithGyro = false;
    }
}
