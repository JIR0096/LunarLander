package com.example.lunarlander;

import java.util.Random;

public class Map {
    public static int x;
    public static int maxX;
    private int area1;
    private int area2;
    private int originalX;
    private int originalY;

    public Map() {
        maxX = Settings.widthOfScreen*5;
        Settings.width = maxX;
        x = maxX / 2;
        Random r = new Random();
        area1 = r.nextInt(maxX);
        //area1 = maxX/2;
        area2 = area1+Settings.widthOfImage;
        Settings.sizeOfLandArea =  area1;
       /* this.originalX = originalX;
        this.originalY = originalX;*/
    }

    public int getArea1() {
        return area1;
    }

    public int getArea2() {
        return area2;
    }

    public int getX() {
        return x;
    }

    public int getOriginalX() {
        return originalX;
    }

    public int getOriginalY() {
        return originalY;
    }

    public static void move(int moveX) {
        if (x + moveX > maxX ) {
            x = x + moveX - maxX;
        }
        else if (x + moveX < 0)
            x = x + moveX + maxX;
        else
            x += moveX;
    }

}
