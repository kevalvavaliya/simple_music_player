package com.example.music_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity implements Runnable {

    Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h =new Handler();
        h.postDelayed(this,3000);
    }

    @Override
    public void run() {
        Intent i=new Intent(this,player.class);
        startActivity(i);
        finish();
    }
}