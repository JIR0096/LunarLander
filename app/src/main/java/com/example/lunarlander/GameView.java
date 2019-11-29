package com.example.lunarlander;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.content.Context.SENSOR_SERVICE;

public class GameView extends SurfaceView implements Runnable {

    //boolean variable to track if the game is playing or not
    volatile boolean playing;

    //the game thread
    private Thread gameThread = null;

    //adding the player to this class
    private Player player;

    //These objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Ground ground;
    private SensorManager gyroscopeSensor;
    private GameActivity activity = (GameActivity) getContext();

    float xDown;
    float yDown;

    //Class constructor
    public GameView(Context context) {
        super(context);

        //initializing player object
        player = new Player(context);


        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

    }

    public GameView(Context context, int x, int y) {
        super(context);

        player = new Player(context, x,y);
        ground = new Ground(x,y);
        surfaceHolder = getHolder();
        paint = new Paint();
        gameThread = new Thread();
        gameThread.start();
    }

    @Override
    public void run() {
        while (playing) {

            int status = ground.landed(player.getLanderX(), player.getLanderY());
            switch (status)
            {
                case 1:
                    Log.w("Landed =>", String.valueOf(status));
                    pause();
                    break;
                case -1:
                    Log.w("Crashes =>", String.valueOf(status));
                    GameActivity.stat = -1;
                    pause();

                    activity.endGame();
                    break;
                default:
                    Log.d("Not landed =>", String.valueOf(status));
                    break;
            }
            //to update the frame
            update();

            //to draw the frame
            draw();

            //to control
            control();
        }
    }


    private void update() {
        player.update();
    }

    private void draw() {
        if(surfaceHolder.getSurface().isValid())
        {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
            ground.draw(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        //when the game is paused
        //setting the variable to false
        playing = false;
        try {
            //stopping the thread
            gameThread.interrupted();
        } catch (Exception e) {
        }
    }

    public void resume() {
        //when the game is resumed
        //starting the thread again
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void landed(){

    }

    public void moveRight(){
        player.moveLeft(false);
        player.moveRight(true);
    }
    public void moveLeft(){
        player.moveRight(false);
        player.moveLeft(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                //stopping the boosting when screen is released
                player.stopBoosting();
                if(!Settings.controlWithGyro) {
                    player.moveRight(false);
                    player.moveLeft(false);
                }
                //player.stopRotate();
                break;
            case MotionEvent.ACTION_DOWN:
                //boosting the space jet when screen is pressed
                xDown = motionEvent.getX();
                yDown = motionEvent.getY();
                int rotate = 0;
                if(!Settings.controlWithGyro && yDown <= 750 ){
                    if(xDown > 500)
                        player.moveRight(true);
                    else
                        player.moveLeft(true);
                }
                else
                    player.setBoosting();
                //player.setRotate(rotate);
                break;
        }
        return true;
    }
}