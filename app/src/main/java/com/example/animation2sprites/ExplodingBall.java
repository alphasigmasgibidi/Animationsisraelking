package com.example.animation2sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class ExplodingBall extends Sprite{
    Bitmap bitmap;
    private float canvasWidth;
    private float canvasHeight;

    public ExplodingBall(float x, float y, Bitmap bitmap) {
        super(x,y,15,15);
        this.bitmap = bitmap;
    }

    public void move()
    {
        if(x>0 && x<canvasWidth-bitmap.getWidth() &&
                y>0 && y<canvasHeight-bitmap.getHeight())
        {
            x = x + dx;
            y = y + dy;
        }
    }

    public void draw(Canvas canvas)
    {
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawBitmap(bitmap,x,y,null);
    }

    public void setNewLocation(float x, float y) {
        this.x = x - bitmap.getWidth()/2;
        this.y = y - bitmap.getHeight()/2;
    }


}
