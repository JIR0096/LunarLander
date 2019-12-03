package com.example.lunarlander;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Fuel {
    private int amountOfFuel;
    private static int baseFuel;
    Rect r0;
    Paint paint;
    Paint paint1;
    int width;

    public Fuel(){
        baseFuel = 10000;
        amountOfFuel = baseFuel;
        width = Settings.widthOfScreen/3;
        r0 = new Rect();
        r0.left = 0;
        r0.top = 0;
        r0.right = width;
        r0.bottom = 100;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.GRAY);

    }

    public void update(int amount){
        amountOfFuel -= amount;
    }

    public int getAmountOfFuel(){
        return amountOfFuel;
    }

    public void draw(Canvas canvas){
        canvas.drawRect(r0, paint);

        int right = (amountOfFuel*100)/baseFuel;

        Rect r1 = new Rect();
        r1.left = 10;
        r1.top = 10;
        r1.right = (right*width/100)-10;
        r1.bottom = 90;

        canvas.drawRect(r1, paint1);
    }
}
