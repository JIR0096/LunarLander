package com.example.lunarlander;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;


public class GameActivity extends Activity {

    //declaring gameview
    private GameView gameView;

    public static int stat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Getting display object
        Display display = getWindowManager().getDefaultDisplay();

        //Getting the screen resolution into point object
        Point size = new Point();
        display.getSize(size);

        //Initializing game view object
        //this time we are also passing the screen size to the GameView constructor
        gameView = new GameView(this, size.x, size.y);

        if(Settings.controlWithGyro) {
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            Sensor gyroscopeSensor;
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    Log.w("SensorEvent", String.valueOf(sensorEvent.values[2]));
                    if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                        gameView.moveLeft();
                    } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                        gameView.moveRight();
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            };
            sensorManager.registerListener(gyroscopeSensorListener,gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //adding it to contentview
        setContentView(gameView);

    }

    protected void endGame(){
        switch (stat){
            case 1:
                startActivity(new Intent(this, LandedActivity.class));
                break;
            case -1:
                /*AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
                alertDialog.setTitle("Lose");
                alertDialog.setMessage("You crashed, therefore you lose");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                alertDialog.show();*/
                startActivity(new Intent(this, CrashedActivity.class));

            default:
               break;

        }
    }


    //pausing the game when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        endGame();
        gameView.pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}