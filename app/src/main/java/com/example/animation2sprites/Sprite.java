package com.example.animation2sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Sprite {
    protected float x,y;  // the center of the circle
    protected float dx, dy;

    public Sprite(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract void move();
    public abstract void draw(Canvas canvas);
}
