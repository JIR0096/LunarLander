package com.example.lunarlander;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if(Settings.controlWithGyro)
            setActivityBackgroundColor(Color.GREEN);
        else
            setActivityBackgroundColor(Color.YELLOW);
    }
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    public void controlByGyro(View view)
    {
        PackageManager packageManager = getPackageManager();
        boolean gyroExists = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE);
        if(gyroExists)
        {
            Settings.controlWithGyro = true;
            setActivityBackgroundColor(Color.GREEN);
            save();
        }
    }

    public void controlByTouch(View view)
    {
        Settings.controlWithGyro = false;
        setActivityBackgroundColor(Color.YELLOW);
        save();
    }

    public void save()
    {
        SharedPreferences sharedPref= getSharedPreferences("settings", 0);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putBoolean("control", Settings.controlWithGyro);
        editor.commit();
    }
}
