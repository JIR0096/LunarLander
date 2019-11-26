package com.example.lunarlander;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ground {

    private int height;
    private int width;
    private int podX0 = 400;
    private int podX1 = 600;
    private int podY = 1500;

    public Ground(int height, int width){
        this.height = height;
        this.width = width;
    }
    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStrokeWidth(10);
        canvas.drawLine(podX0, podY, podX1, podY, p);

        Paint p1 = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
        p.setStrokeWidth(10);
        canvas.drawLine(0, podY, podX0, podY, p);
        canvas.drawLine(podX1, podY, width, podY, p);
    }
    public int landed(int x, int y)
    {
        if(x >= podX0 && x <= podX1 && y >= podY-5 && y <= podY+5)
            return 1;
        else if(y >= podY-5 && y <= podY+5)
            return  -1;
        else
            return 0;

    }
}
