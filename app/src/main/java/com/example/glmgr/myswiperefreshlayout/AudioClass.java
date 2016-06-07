package com.example.glmgr.myswiperefreshlayout;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgr on 2016/6/6.
 */
public class AudioClass extends Activity implements View.OnClickListener {
    private MediaPlayer mMediaPlayer = null;
    @BindView(R.id.btnPlayRes)
    Button btnPlayRes;
    @BindView(R.id.btnStopRes)
    Button btnStopRes;
    @BindView(R.id.btnPlaySdCard)
    Button btnPlaySdCard;
    @BindView(R.id.btnStopSdCard)
    Button btnStopSdCard;
    @BindView(R.id.btnPlayWeb)
    Button btnPlayWeb;
    @BindView(R.id.btnStopWeb)
    Button btnStopWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        ButterKnife.bind(this);

        btnPlayRes.setOnClickListener(this);
        btnStopRes.setOnClickListener(this);
        btnPlaySdCard.setOnClickListener(this);
        btnStopSdCard.setOnClickListener(this);
        btnPlayWeb.setOnClickListener(this);
        btnStopWeb.setOnClickListener(this);
    }

    public void playAudioFromRes(View view) {
        if(btnPlayRes.getText().toString().equals("Play")){
            if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(this, R.raw.ring);
                mMediaPlayer.setLooping(true);
            }
            mMediaPlayer.start();
            btnPlayRes.setText("Pause");
        }else if(btnPlayRes.getText().toString().equals("Pause")) {
            mMediaPlayer.pause();
            btnPlayRes.setText("Play");
        }
    }

    public void stopAudio(View view) {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();//释放资源
            mMediaPlayer = null;
        }
    }

    public void playAudioFromSdCard(View view) {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDataSource("/mnt/sdcard/ring.mp3");
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.start();
    }


    public void playAudioFromWeb(View view) {
        String url = "http://172.19.0.73:8003/ring.mp3";
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(url);
        } catch (IllegalArgumentException e) {
            Log.d("playAudioFromWeb", "IllegalArgumentException");
            return;
        } catch (IllegalStateException e) {
            Log.d("playAudioFromWeb", "IllegalArgumentException");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.prepareAsync();
        mMediaPlayer.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlayRes:
                playAudioFromRes(v);
                break;
            case R.id.btnStopRes:
                stopAudio(v);
                break;
            case R.id.btnPlaySdCard:
                playAudioFromSdCard(v);
                break;
            case R.id.btnStopSdCard:
                stopAudio(v);
                break;
            case R.id.btnPlayWeb:
                playAudioFromWeb(v);
                break;
            case R.id.btnStopWeb:
                stopAudio(v);
                break;
        }
    }
}
