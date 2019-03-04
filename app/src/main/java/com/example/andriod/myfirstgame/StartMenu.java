package com.example.andriod.myfirstgame;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }
    public void OnClick(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
    public void OnBack(View view){
        finish();
    }
    public void onSet(View view){
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }
}
