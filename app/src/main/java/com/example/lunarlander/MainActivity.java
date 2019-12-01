package com.example.lunarlander;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener{

    //image button
    private ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences sharedPref= getSharedPreferences("settings", 0);
        Settings.controlWithGyro = sharedPref.getBoolean("control", false);

        //getting the button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);

        //adding a click listener
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //starting game activity
        Intent intent = new Intent(this, ChoseDifActivity.class);
        intent.putExtra("page","game");
        startActivity(intent);
    }

    public void setSettings(View v){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
    public void showHighScore(View v){
        //startActivity(new Intent(this, HighScoreActivity.class) );

        Intent intent = new Intent(this, ChoseDifActivity.class);
        intent.putExtra("page","score");
        startActivity(intent);
    }
}