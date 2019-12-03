package com.example.lunarlander;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Stars {
    private int width;
    private int height;
    private Random r;
    private List<Star> list;
    private final MediaPlayer mp;
    private final Bitmap coll;

    public Stars(Context context, Activity ac) {
        width = Settings.width;
        height = Settings.heightOfScreen;
        r = new Random();

        list = new ArrayList<Star>();
        int x = r.nextInt(width);

        coll = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_collected);

        mp = MediaPlayer.create(ac, R.raw.collect);

        int maxY = height - Settings.heightOfScreen / 2;
        int minY = 100;


        for (int i = 0; i < 3; i++) {
            int y = r.nextInt((maxY - minY) + 1) + minY;
            list.add(new Star(context, x, y));
            x += width / 3;
            if (x > width)
                x = x - width;
        }
    }

    public void draw(Paint paint, Canvas canvas) {

        int halfWidth = Settings.widthOfScreen / 2;
        int x = Map.x;
        int rightBorder = x + halfWidth;
        int x0;

        for (Star s : list) {

            x0 = rightBorder - s.getX();
            canvas.drawBitmap(s.getBitmap(), x0, s.getY(), paint);
        }
    }

    public void update(Player player) {
        int halfWidth = Settings.widthOfScreen / 2;
        int x = Map.x;
        int rightBorder = x + halfWidth;
        int playerX = player.getX();
        int playerY = player.getY();
        int playerWidth = player.getBitmap().getWidth();
        int playerHeight = player.getBitmap().getHeight();
        int x0;


        for (Star s : list) {

            if (!s.collected) {
                x0 = rightBorder - s.getX();
                //if (((x > x0) && (x <= (x0 + s.getBitmap().getWidth())) && ((playerY + playerHeight) >= s.getY()) && (player.getY() <= (s.getY() + s.getBitmap().getHeight())))) {

                int starWidth = s.getWidth();

                if ((x0 >= playerX && x0 <= playerX + playerWidth) || (x0 + starWidth >= playerX && x0 + starWidth <= playerX + playerWidth)) {
                    if ((s.getY() >= playerY && s.getY() <= playerY + playerHeight) || (s.getY() + s.getHeight() >= playerY && s.getY() + s.getHeight() <= playerY + playerHeight)) {
                        s.collected = true;
                        s.setBitmap(coll);
                        Log.w("Collected", "true");
                        mp.start();
                    }

                }
            }
        }
    }


    public int gotStars() {
        int count = 0;
        for (Star s : list) {
            if (s.collected)
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

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public int getWidth() {
            return bitmap.getWidth();
        }

        public int getHeight() {
            return bitmap.getHeight();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
