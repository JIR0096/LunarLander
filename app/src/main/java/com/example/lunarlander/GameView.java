package com.example.lunarlander;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    //boolean variable to track if the game is playing or not
    volatile boolean playing;

    //the game thread
    private Thread gameThread = null;

    private Player player;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Ground ground;
    //private Pause pause;
    private Stars stars;
    private Background background;
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

        player = new Player(context, x, y);
        ground = new Ground(x, y);
        background = new Background();
        stars = new Stars(context, activity);
        //pause = new Pause(context, y);
        surfaceHolder = getHolder();
        paint = new Paint();
        gameThread = new Thread();
        gameThread.start();
    }

    @Override
    public void run() {
        while (playing) {

            int status = ground.landed(player.getLanderX(), player.getLanderY());
            switch (status) {
                case 1:
                    //Log.w("Landed =>", String.valueOf(status));
                    GameActivity.stat = 1;
                    stop();
                    Player.score = stars.gotStars() * 1000 + 1000;
                    activity.endGame();
                    break;
                case -1:
                    //Log.w("Crashes =>", String.valueOf(status));
                    GameActivity.stat = -1;
                    stop();
                    activity.endGame();
                    break;
                default:
                    //Log.d("Not landed =>", String.valueOf(status));
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
        background.update((int) player.getXPosition());
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            background.draw(canvas);
            ground.draw(canvas);
            stars.draw(paint, canvas);
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
            //pause.draw(canvas,paint);
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

    public void stop() {
        playing = false;
        try {
            //stopping the thread
            gameThread.interrupt();
        } catch (Exception e) {
        }
    }

    public void pause() {
        //when the game is paused
        //setting the variable to false
        playing = false;
        try {
            //stopping the thread
            gameThread.join();
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

    public void landed() {

    }

    public void moveRight() {
        player.moveLeft(false);
        player.moveRight(true);
    }

    public void moveLeft() {
        player.moveRight(false);
        player.moveLeft(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        int totalPointerCount = motionEvent.getPointerCount();

        for (int i = 0; i < totalPointerCount; i++) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    //stopping the boosting when screen is released
                    player.stopBoosting();
                    if (!Settings.controlWithGyro) {
                        player.moveRight(false);
                        player.moveLeft(false);
                    }
                    //player.stopRotate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //boosting the space jet when screen is pressed
                    xDown = motionEvent.getX(i);
                    yDown = motionEvent.getY(i);
                    int rotate = 0;
                    if (!Settings.controlWithGyro && yDown <= 1500) {
                        if (xDown > 500)
                            player.moveLeft(true);
                        else
                            player.moveRight(true);
                    } else
                        player.setBoosting();
                    //player.setRotate(rotate);
                    break;
            }
        }
        return true;
    }
}