package com.example.animation2sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Gun extends Sprite{

    Bitmap bitmap2;
    private float canvasWidth;
    private float canvasHeight;
    public Gun(float x, float y , Bitmap bitmap2) {
        super(x, y, 0,0);
        this.bitmap2 = bitmap2;

    }

    @Override
    public void move() {

    }
    public Float getBitX(){
        return x+150;
    }
    public Float getBitY(){
        return y+150;
    }

    @Override
    public void draw(Canvas canvas) {

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawBitmap(bitmap2,x,y,null);
    }

    public void setNewLocation(float x) {
        this.x = x - bitmap2.getWidth()/2;
    }
}
