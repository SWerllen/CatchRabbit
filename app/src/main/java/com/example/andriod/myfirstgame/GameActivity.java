package com.example.andriod.myfirstgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    @SuppressLint("WrongViewCast")
    Plane plane;
    int winwidth,winheight;
    int sleeptime;
    Handler handler;
    boolean threadrun=false;
    @SuppressLint({"WrongViewCast", "ResourceAsColor"})
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        plane=new Plane(this);
        setContentView(plane);
        sleeptime=Config.getSleeptime();
        WindowManager windowManager=getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        display.getMetrics(metrics);
        winwidth=metrics.widthPixels;
        winheight=metrics.heightPixels;
        plane.winwidth=winwidth;
        plane.winheight=winheight;

        plane.setBackgroundResource(R.drawable.green);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(!plane.randomAdd()) {   //结束了
                    Log.i("TIMER","GameOver");
                    //创建一个结束页面
                    threadrun=false;
                    onOver();
                    //gameover
                }
                Log.i("TIMER","Add one");
                sleeptime--;
                plane.invalidate();
                if(plane.score>20){
                    Log.i("TIMER","Win!!");
                    onWin();
                }
            }
        };
        plane.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for(int i=0;i<plane.buglist.size();i++) {
                    int xcha = (int) event.getX() - plane.buglist.get(i).x;
                    int ycha = (int) event.getY() - plane.buglist.get(i).y;
                    if (xcha > -50 && xcha < 100 && ycha > -40 && ycha < 80){
                        Log.i("TAPKILL","Kill one！");
                        plane.buglist.remove(i);
                        plane.invalidate();
                        plane.score++;
                        return true;
                    }
                }
                return true;
            }
        });
        threadrun=true;
        new MyThread().start();

    }
    class MyThread extends Thread{
        @Override
        public void run() {
            while(threadrun){
                handler.sendEmptyMessage(0);
                try {
                    Thread.sleep(sleeptime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    protected void onPause() {
        super.onPause();
        threadrun=false;    //关闭线程
    }
    protected  void onOver(){
        Intent intent=new Intent(this,GameOverActivity.class);
        startActivity(intent);
        finish();
    }

    protected void onWin(){
        Intent intent=new Intent(this,GameWinActivity.class);
        startActivity(intent);
        finish();
    }
}
