package com.example.lunarlander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ChoseDifActivity extends Activity {

    Difficulty dif;
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_dif);

        Intent intent  = this.getIntent();
        str = intent.getExtras().getString("page");
    }

    public void setDifEz(View view){
        Settings.difficulty = Difficulty.EASY;
        startActivity();
    }
    public void setDifMed(View view){
        Settings.difficulty = Difficulty.MEDIUM;
        startActivity();
    }
    public void setDifHard(View view){
        Settings.difficulty = Difficulty.HARD;
        startActivity();
    }

    public void startActivity(){
        Intent intent = new Intent();
        switch (str) {
            case "game":
                intent.setClass(this, GameActivity.class);
                break;
            case "score":
                intent.setClass(this, HighScoreActivity.class);
                break;
        }

        startActivity(intent);
    }

}
