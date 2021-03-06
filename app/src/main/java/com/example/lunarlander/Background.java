package com.example.lunarlander;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Background {

    private int width;
    private int widthOfScreen;
    private int height;
    private int position;
    private List<Star> stars;
    private int x;
    private int moveX;

    public Background() {
        widthOfScreen = Settings.widthOfScreen;
        width = Settings.widthOfScreen;
        height = Settings.heightOfScreen;

        x = width / 2;
        moveX = Map.x;

        Random r = new Random();
        stars = new ArrayList();

        for (int i = 0; i < 1000; i++) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            stars.add(new Star(x, y));
        }
    }

    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);

        for (Star star : stars) {
            canvas.drawRect(star.x, star.y, star.x + 5, star.y + 5, p);
        }
    }

    public void update(int move) {
        position = move;

        for (Star star : stars) {
            if (position > 0) {
                if(star.x+position > widthOfScreen)
                    star.x = widthOfScreen-star.x;
                else
                    star.x+=position;
            } else if (position < 0) {
                if (star.x < position)
                    star.x = widthOfScreen - star.x;
                else
                    star.x += position;
            }
        }
    }


    private class Star implements Serializable {
        private int x;
        private int y;

        private Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
