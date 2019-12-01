package com.example.lunarlander;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Stars {
    private int width;
    private int height;
    private Random r;
    private List<Star> list;

    public Stars(Context context) {
        width = Settings.width;
        height = Settings.heightOfScreen;
        r = new Random();
        list = new ArrayList<Star>();
        int x = r.nextInt(width);

        for (int i = 0; i < 3; i++) {
            int y = r.nextInt(height);
            list.add(new Star(context, x, y));
            x += width / 3;
            if (x > width)
                x = x - width;
        }
    }

    public void draw(Paint paint, Canvas canvas) {

        int halfWidth =  Settings.widthOfScreen/2;
        int x = Map.x;
        int y = Player.getY();
        int rightBorder = x + halfWidth;
        int x0;

        for (Star s : list) {

            if(!s.collected) {
                x0 = rightBorder - s.getX();
                canvas.drawBitmap(s.getBitmap(), x0, s.getY(), paint);
                if (x >= s.getX() && x <= s.getX() + s.getBitmap().getWidth() && y >= s.getY() && y <= s.getY() + s.getBitmap().getHeight())
                    s.collected = true;
            }
        }
    }

    public int gotStars(){
        int count = 0;
        for (Star s : list) {
            if(s.collected)
                count++;
        }
        return count;
    }

    private class Star implements Serializable {

        private int x;
        private int y;

        public boolean collected;

        private Bitmap bitmap;

        public Star(Context context, int x, int y) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.star);
            this.x = x;
            this.y = y;
            collected = false;
        }

        public Bitmap getBitmap(){
            return bitmap;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return x;
        }
    }
}
