package com.codecademy.quizapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service implements MediaPlayer.OnErrorListener {

    private final IBinder mBinder = new ServiceBinder();
    MediaPlayer mPlayer;
    private int length = 0;


    public MusicService() {

    }

    public class ServiceBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mPlayer = MediaPlayer.create(MusicService.this,
                R.raw.bg_song);
        mPlayer.setOnErrorListener(this);

        if (mPlayer != null) {
            mPlayer.setLooping(true);
            mPlayer.setVolume(50,
                    50);
        }


        if (mPlayer != null) {
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                public boolean onError(MediaPlayer mp,
                        int what,
                        int
                                extra) {

                    onError(mPlayer,
                            what,
                            extra);
                    return true;
                }
            });
        }
    }

    @Override
    public int onStartCommand(Intent intent,
            int flags,
            int startId) {
        if (mPlayer != null) {
            mPlayer.start();
        }
        return START_NOT_STICKY;
    }

    public void pauseMusic() {
        if (mPlayer != null) {

            mPlayer.pause();
            length = mPlayer.getCurrentPosition();
            stopMusic();

        }
    }

    public void resumeMusic() {

        if (mPlayer != null) {
            if (!mPlayer.isPlaying()) {
                mPlayer.seekTo(length);
                mPlayer.start();
            }
        } else {
            onCreate();
            resumeMusic();
        }
        }

        public void startMusic () {
            mPlayer = MediaPlayer.create(MusicService.this,
                    R.raw.bg_song);
            mPlayer.setOnErrorListener(this);

            if (mPlayer != null) {
                mPlayer.setLooping(true);
                mPlayer.start();
            }

        }

        public void stopMusic () {
            if (mPlayer != null) {
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
            }
        }

        @Override
        public void onDestroy () {
            super.onDestroy();
            if (mPlayer != null) {
                try {
                    stopMusic();
                } finally {
                    mPlayer = null;
                }
            }
        }

        @Nullable
        @Override
        public IBinder onBind (Intent intent){
            return mBinder;
        }

        public boolean onError (MediaPlayer mp,int what, int extra){

            Toast.makeText(this,
                    "Music player failed",
                    Toast.LENGTH_SHORT).show();
            if (mPlayer != null) {
                try {
                    mPlayer.stop();
                    mPlayer.release();
                } finally {
                    mPlayer = null;
                }
            }
            return false;
        }
    }
