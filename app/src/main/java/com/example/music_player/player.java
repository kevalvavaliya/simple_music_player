package com.example.music_player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class player extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, Runnable {

    ImageView play,pause,back,next;
    SeekBar sk;
    TextView t_start,t_end;
    MediaPlayer mp;
    Handler h;
    int dur;
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        setup();
        dur=mp.getDuration();
        t_end.setText(time_set(dur));
        sk.setMax(dur);
        sk.setOnSeekBarChangeListener(this);
        h=new Handler();

    }
    void setup()
    {
        play=findViewById(R.id.btn_play);
        pause=findViewById(R.id.btn_pause);
        back=findViewById(R.id.btn_back);
        next=findViewById(R.id.btn_next);

        sk=findViewById(R.id.seek);

        t_start=findViewById(R.id.tx_start);
        t_end=findViewById(R.id.tx_end);

        mp=MediaPlayer.create(this,R.raw.song);
        enable(false);
    }
    void enable(boolean b)
    {
        pause.setEnabled(b);
        next.setEnabled(b);
        back.setEnabled(b);
    }
    String time_set(int t) {
        t = t / 1000;
        int m = t / 60;
        t = t % 60;
        String time = m +":"+ t;
        return time;
    }

    public void play(View view) {
        mp.start();
        play.setEnabled(false);
        enable(true);
        flag=true;
        h.postDelayed(this,2000);

    }
    public void pause(View view) {
        mp.pause();
        play.setEnabled(true);
        flag=false;

    }
    public void next(View view) {
        int cur=mp.getCurrentPosition();
        if(cur<dur)
        {
            cur =cur+10*1000;
            mp.seekTo(cur);
            t_start.setText(time_set(cur));
            sk.setProgress(cur);
        }
        else{
            mp.seekTo(dur);
            t_start.setText("00:00");
            sk.setProgress(0);
        }

    }
    public void prev(View view) {
        int cur =mp.getCurrentPosition();
        if(cur>=0)
        {
            cur=cur-10*1000;
            mp.seekTo(cur);
            t_start.setText(time_set(cur));
            sk.setProgress(cur);
        }
        else {
            mp.seekTo(0);
            t_start.setText(time_set(dur));
            sk.setProgress(dur);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mp.seekTo(i);
        t_start.setText(time_set(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void run() {

            int cur = mp.getCurrentPosition();

            sk.setProgress(cur);
            t_start.setText(time_set(cur));
            if(flag==true)
            {
                h.postDelayed(this,5000);
            }
    }
}