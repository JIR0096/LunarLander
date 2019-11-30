package com.example.lunarlander;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.util.Log;

public class Player {
    //Bitmap to get character from image
    private Bitmap bitmap;

    //if is trusters on
    private boolean boosting;

    private final int GRAVITY  = -10;

    private int maxY;
    private int minY;
    private int maxX;
    private int minX;


    private double xPosition;
    private double yPosition;

    private boolean moveR;
    private boolean moveL;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    //coordinates
    private int x;
    private int y;

    private float originAngle = 0;

    //motion speed of the character
    private int speed = 0;
    private int rotate = 0;
    private Context context;

    //constructor
    public Player(Context context) {
        x = 75;
        y = 50;
        speed = 1;

        //Getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lander0);
        Settings.widthOfImage = bitmap.getWidth();
        boosting = false;
    }

    public Player(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lander0);

        x = (screenX-bitmap.getWidth())/2;
        y = 50;
        speed = 1;
        this.context = context;

        Settings.widthOfImage = bitmap.getWidth();
        Settings.heightOfScreen = screenY;

        maxX = screenX - bitmap.getWidth();
        minX = 0;

        maxY = screenY - bitmap.getHeight();
        minY = 0;
        boosting = false;

        Settings.difficulty = Difficulty.EASY;

    }

    public void setRotate(int angle){
        this.rotate = angle;    //0 = nic, -1 = vlevo, 1 = vpravo
    }
    public void stopRotate(){
        this.rotate = 0;
    }

    public void moveSide(int x)
    {
        xPosition += x;
    }

    public void rotate(float angle){
        try {
            Matrix mat = new Matrix();
            originAngle=(originAngle+angle)%360;
            double radians = Math.toRadians(angle);
            double sin = Math.abs(Math.sin(radians));
            double cos = Math.abs(Math.cos(radians));
            int newWidth = (int)(bitmap.getWidth() * cos + bitmap.getHeight() * sin);
            int newHeight = (int)(bitmap.getWidth() * sin + bitmap.getHeight() * cos);
            Log.d("x+width => ", "x+width="+0+"+"+newWidth+"<="+ bitmap.getWidth());
            Log.d("y+width => ", "y+height="+0+"+"+newHeight+"<="+ bitmap.getHeight());
            mat.reset();
            mat.setRotate(originAngle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0,newWidth, newHeight, mat, true);
        }
        catch (Exception e)
        {
            Log.e("Exception", e.getMessage());
        }
    }
    public void setBoosting() {
        this.boosting = true;
    }
    public void stopBoosting() {
        this.boosting = false;
    }
    public void moveRight(boolean move) {this.moveR = move;}
    public void moveLeft(boolean move) {this.moveL = move;}

    public void landed(){
        speed = 0;
        xPosition = 0;

    }

    //Method to update coordinate of character
    public void update() {
        //if the ship is boosting

        /*if(rotate != 0)
        {
            rotate(rotate);
        }*/

        if (boosting) {
            //speeding up the ship
            speed += 2;
        } else {
            //slowing down if not boosting
            speed -= 5;
        }

        if(moveR && xPosition < Settings.widthOfImage/2)
            xPosition++;
        if(moveL && xPosition > -Settings.widthOfImage/2)
            xPosition--;

        //controlling the top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        Map.move((int)xPosition);

        if(xPosition > 0 && !moveR)
            xPosition--;
        else if(xPosition < 0 && !moveL)
            xPosition++;

        //moving the ship down
        y -= speed + GRAVITY;

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
    }

    /*
     * These are getters you can generate it autmaticallyl
     * right click on editor -> generate -> getters
     * */
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getLanderX(){ return x+bitmap.getWidth();}
    public int getLanderY(){ return y+bitmap.getHeight();}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}