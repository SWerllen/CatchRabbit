package com.example.andriod.myfirstgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plane extends View {
    int winwidth,winheight;
    Bitmap plane;
    List<Point> buglist;
    Random random = new Random();
    Point hit;
    long score=0;
    int max=50;

    @SuppressLint("ResourceAsColor")
    public Plane(Context context) {
        super(context);
        plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit);
        setFocusable(true);
        buglist=new ArrayList<Point>();
        hit=new Point(0,0);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        for(int i=0;i<buglist.size();i++) {
            canvas.drawBitmap(plane, buglist.get(i).x, buglist.get(i).y, p);
        }
        Paint textPaint = new Paint();
        textPaint.setStrokeWidth(3);
        textPaint.setTextSize(80);
        textPaint.setARGB(255,255,255,255);
        textPaint.setColor(R.color.colorText);
        textPaint.setTextAlign(Paint.Align.LEFT);
        Rect bounds = new Rect();
        String testString=String.format("Score: %d",score);
        textPaint.getTextBounds(testString, 0, testString.length(), bounds);
        canvas.drawText(testString, getMeasuredWidth()/2 - bounds.width()/2, getMeasuredHeight()/2 + bounds.height()/2, textPaint);
    }

    public boolean randomAdd(){
        if(buglist.size()>max) return false;
        buglist.add(new Point(150+random.nextInt(winwidth-300),100+random.nextInt(winheight-200)));
        return true;
    }
}
