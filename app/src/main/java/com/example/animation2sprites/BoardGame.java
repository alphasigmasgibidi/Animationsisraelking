package com.example.animation2sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BoardGame extends View {

    Handler handler;
    ThreadGame threadGame;
    ArrayList<Sprite> arrayList;
    Gun gun;
    ArrayList<Sprite>arl2;
    int counter = 0;
    private boolean isRun = true;



    public BoardGame(Context context) {
        super(context);

        arrayList = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icons8_explosion96);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int)200, (int)200, true);

        arl2 = new ArrayList<>();
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.icons8_cannon96);
        bitmap2 = Bitmap.createScaledBitmap(bitmap2, (int)300, (int)300, true);

        gun = new Gun(640,2750,bitmap2);
        arl2.add(gun);

        ExplodingBall explodingBall = new ExplodingBall(200,200,bitmap);
        arrayList.add(explodingBall);

        Ball s1 = new Ball(100,100,50, Color.RED);
        arrayList.add(s1);
        Ball s2 = new Ball(300,100,40, Color.BLUE);
        arrayList.add(s2);

        threadGame = new ThreadGame();
        threadGame.start(); // runs as thread the run() method

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                counter++;
                if(counter%25 == 0)
                {
                    //Sprite s2 = new Sprite(300,100,40, Color.BLUE);
                    FastBall fb = new FastBall(gun.getBitX(),3000,0,-70,30);
                    arl2.add(fb);
                    Ball s2 = new Ball(300,100,40, 0xFF000000+counter*10000%0x1000000);
                    arrayList.add(s2);
                }
                for (int i=0; i<arrayList.size();i++)
                {
                    arrayList.get(i).move();
                    if(i>0)
                    {
                        if(((Ball)arrayList.get(i)).isCollision(arrayList.get(0).getX(),arrayList.get(0).getY()))
                        {
                            arrayList.remove(i);
                        }
                    }
                }
                for (int i = 0; i < arl2.size(); i++) {
                    arl2.get(i).move();
                    if(i!= 0 && arl2.get(i).getX()+30>250 && arl2.get(i).getX()-30<350 && arl2.get(i).getY()+30<650){
                        arl2.remove(i);
                    }
                }

                invalidate(); // clear the canvas and calls to onDraw()
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        canvas.drawCircle(300,600,50,p);

        for (int i=0; i<arrayList.size();i++)
        {
            arrayList.get(i).draw(canvas);
        }
        for (int i=0; i<arl2.size();i++)
        {
            arl2.get(i).draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            isRun = false;
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if(event.getX() > gun.getBitX()-150 && event.getX() < gun.getBitX()+150 && event.getY() > gun.getBitY()-150 && event.getY() < gun.getBitY()+150){
                ((Gun)arl2.get(0)).setNewLocation(event.getX());
            }
            else{
                ((ExplodingBall)arrayList.get(0)).setNewLocation(event.getX(),event.getY());
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
            isRun = true;

        invalidate(); // clears the canvas and call to onDraw()
        return true;
    }

    private class ThreadGame extends Thread{
        @Override
        public void run() {
            super.run();
            while (true)
            {
                try {
                    sleep(40);
                    if(isRun)
                        handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
