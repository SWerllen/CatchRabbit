package com.example.andriod.myfirstgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GameWinActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_win);
    }

    public void OnBack(View view){
        finish();
    }
}
