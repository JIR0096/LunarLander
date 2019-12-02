package com.example.lunarlander;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Pause {
    private Bitmap bitmap;
    private int width;

    public Pause(Context context, int width) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pause);
        this.width = width;
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmap, width-bitmap.getWidth(),20,paint);
    }

}
