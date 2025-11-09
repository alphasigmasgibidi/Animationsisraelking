package com.example.animation2sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball extends Sprite{
    private float r;    // radius size
    private Paint p;
    private float canvasWidth;
    private float canvasHeight;


    public Ball(float x, float y, float r, int color) {
        super(x,y,r/2,15);
        this.r = r;
        this.p = new Paint();
        p.setColor(color);
    }

    public void move()
    {
        if(x<r || x>canvasWidth-r)
            dx = -dx;
        if(y<r || y>canvasHeight-r)
            dy = -dy;

        x = x + dx;
        y = y + dy;
    }

    public void draw(Canvas canvas)
    {
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawCircle(x,y,r,p);
    }

    public boolean isCollision(float x, float y) {
        if(this.x > x && this.x<x+250 && this.y > y && this.y < y+250)
            return true;
        return  false;
    }
}
