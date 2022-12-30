package com.example.musicx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer Player;//MUSIC PLAY KE LIYE
    AudioManager audioManager;// FOR CONTROLLING THE AUDIO LEVELS
    //FOR PLAYING THE MUSIC
    public void play(View view){
        Player.start();
    }
    //FOR PAUSING THE MUSIC
    public void pause(View view){
        Player.pause();
    }
    //FOR STOPPING THE MUSIC
    public void stop(View view){
        Player.stop();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Player=MediaPlayer.create(this,R.raw.music);//MUSIC YEH WALA PLAY HOGA TUMHARE PLAYER MEIN
        audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);//TYPECASTING TO PARTICULAR DATA AND THIS LINE USED FOR GETTING AUDIO SERVICES PROVIDED BY ADROID STUDIO
        int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//MAX AND CURRENT VOLUMES INITALISED AND GIVEN VALUES USING THE FUNCTIONS PRESENT IN ANDROID STUDIO
        SeekBar seekVol=findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);//MAXIMUM VOLUME KA SETTING
        seekVol.setProgress(curVol);//FOR SETTING THE PINTER TO THE CURRENT VOLUME LEVEL CURRENT VOLUME LEVEL
        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);//SYSTEM WILL STORE NEW SOUND AFTER CHANGING TO THE NEW VOICE LEVEL
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar seekProg=findViewById(R.id.seekProg);//FOR STORING THE PROGRESS OF THE SEEKBAR IN HERE
        seekProg.setMax(Player.getDuration());//PROGRESS TO GET THE CURRET SITUATION AND MEDIAPLAYER'S ONE IS USED..(MAXIMUM SET KRDIYO TUM)
        new Timer().scheduleAtFixedRate(new TimerTask() {
                                            @Override
                                            public void run() {
                                                seekProg.setProgress(Player.getCurrentPosition());
                                            }
                                        }, 0,900);//PERIOD MATLAB KITNE SECONDS BAAD PHIR REPEAT HONE LAGEGA
                seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Player.seekTo(i);   //PROGRESS TAKES IT TO THAT
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });


    }
}