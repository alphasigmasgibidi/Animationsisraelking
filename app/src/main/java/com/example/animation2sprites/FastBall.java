package com.example.animation2sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

public class FastBall  extends Sprite{
    private float r;
    private float canvasHeight;
    private Paint p;
    public FastBall(float x, float y, float dx, float dy, float r) {
        super(x, y, dx, dy);
        this.r = r;
        this.p=new Paint();
    }

    @Override
    public void move() {
        y = y+dy;
    }

    @Override
    public void draw(Canvas canvas) {
        canvasHeight = canvas.getHeight();
        canvas.drawCircle(x,y,r,p);
    }

}
