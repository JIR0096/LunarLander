package com.example.lunarlander;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Ground {

    private int height;
    private int width;
    private int heightOfScreen;
    private int podX0 = 400;
    private int podX1 = 600;
    private int podY = 1500;

    private int areaL;
    private int areaR;

    private Map map;

    public Ground(int height, int width) {
        this.height = height;
        this.width = width;
        this.heightOfScreen = Settings.heightOfScreen;
        Settings.widthOfScreen = width;
        map = new Map();
        areaL = map.getArea1();
        areaR = map.getArea2();


    }

    private void drawLandArea(Canvas canvas) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStrokeWidth(10);
        canvas.drawLine(podX0, podY, podX1, podY, p);
    }

    public void draw(Canvas canvas) {
        Paint p1 = new Paint();
        p1.setAntiAlias(true);
        p1.setColor(Color.GRAY);
        p1.setStrokeWidth(10);

        int halfWidth = width / 2;
        int x = map.x;
        int rightBorder = x + halfWidth;
        int leftBorder = x - halfWidth;
        boolean landAreaRightL = rightBorder - areaL > 0;
        boolean landAreaRightR = rightBorder - areaR > 0;
        boolean landAreaLeftL = leftBorder - areaL < 0;
        boolean landAreaLeftR = leftBorder - areaR < 0;

        Log.d("x => ", String.valueOf(x));
        Log.d("LandArea => ", areaL+"|"+areaR);


       /* if (landAreaRightL && !landAreaRightR) {
            podX0 = rightBorder-areaL;
            podX1 = width;
            drawLandArea(canvas);
            canvas.drawLine(0, podY, podX0, podY, p1);
        } else if (!landAreaLeftL && landAreaLeftR) {
            podX0 = 0;
            podX1 = -(areaL - leftBorder);
            drawLandArea(canvas);
            canvas.drawLine(podX1, podY, width, podY, p1);
        } else if (landAreaLeftL && landAreaRightR) {*/
            podX0 = rightBorder - areaR;
            podX1 = podX0+Settings.sizeOfLandArea ;
            canvas.drawRect(0,podY,width,heightOfScreen,p1);
            drawLandArea(canvas);
            /*canvas.drawLine(0, podY, podX0, podY, p1);
            canvas.drawLine(podX1, podY, width, podY, p1);*/
        /*} else {
            podX0 = 0;
            podX1 = 0;
            canvas.drawLine(0, podY, width, podY, p1);
        }*/

        //canvas.drawLine(podX1, podY, width, podY, p1);
    }

    public int landed(int x, int y) {
        if (x >= podX0 && x <= podX1 && y >= podY-5 && y <= podY + 5)
            return 1;
        else if (y >= podY - 5 && y <= podY + 5)
            return -1;
        else
            return 0;

    }
}
