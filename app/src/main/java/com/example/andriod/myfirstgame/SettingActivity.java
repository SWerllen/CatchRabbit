package com.example.andriod.myfirstgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {
    RadioGroup my_rg;
    int sleeptime;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        my_rg=findViewById(R.id.rg_mode);
        sleeptime=Config.getSleeptime();
        switch(sleeptime){
            case 1000:
                my_rg.check(R.id.rd_simple);
                break;
            case 500:
                my_rg.check(R.id.rd_middle);
                break;
            case 200:
                my_rg.check(R.id.rd_hard);
                break;
        }
        my_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rd_simple:
                        sleeptime=1000;
                        Log.i("SETTING","更改为简单难度");
                        break;
                    case R.id.rd_middle:
                        sleeptime=500;
                        Log.i("SETTING","更改为中等难度");
                        break;
                    case R.id.rd_hard:
                        sleeptime=200;
                        Log.i("SETTING","更改为困难难度");
                        break;
                }
            }
        });
    }

    public void OnBack(View view){
        finish();
    }
    public void OnSave(View view){
        Config.setSleeptime(sleeptime);
        finish();
    }
}
