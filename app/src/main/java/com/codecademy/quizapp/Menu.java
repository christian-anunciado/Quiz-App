package com.codecademy.quizapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Menu extends AppCompatActivity {

    private Button play, howToPlay, stats, settings;
    private TextView name;
    private MediaPlayer player;

    private String User_Name;
    private boolean MUSIC;
    private boolean SOUND;

    private HomeWatcher mHomeWatcher = new HomeWatcher(this);
    private boolean mIsBound = false;
    private MusicService mServ;


    private long mTIME;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Pinoy Quiz");


        name = findViewById(R.id.tv_menu_name);
        play = findViewById(R.id.btn_menu_play);
        howToPlay = findViewById(R.id.btn_menu_how_to_play);
        stats = findViewById(R.id.btn_menu_stats);
        settings = findViewById(R.id.btn_menu_settings);
        loadData();


        if (MUSIC) {
            Intent i = new Intent(Menu.this,
                    MusicService.class);
            startService(i);
            doBindService();

            mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
                @Override
                public void onHomePressed() {
                    if (mServ != null) {
                        mServ.pauseMusic();
                    }
                }

                @Override
                public void onHomeLongPressed() {
                    if (mServ != null) {
                        mServ.pauseMusic();
                    }
                }
            });
            mHomeWatcher.startWatch();
        }


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,
                        CategoryActivity.class);
                startActivity(i);
                finish();
                doUnbindService();

            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, ScoreActivity.class);
                startActivity(i);
                finish();
                doUnbindService();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,
                        SettingsActivity.class);
                startActivity(i);
                finish();
                doUnbindService();
            }
        });

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",
                MODE_PRIVATE);
        String user = sharedPreferences.getString("name",
                "");
        MUSIC = sharedPreferences.getBoolean("music",
                true);
        SOUND = sharedPreferences.getBoolean("sound",
                true);

        if (!user.isEmpty()) {
            name.setText("Hi \n" + user + "!");
            User_Name = user;
        }
    }

    @Override
    protected void onPause() {

        super.onPause();
        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isInteractive();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mServ != null) {
            mServ.resumeMusic();
        }

    }


    @Override
    public void onBackPressed() {

        if (mTIME + 2000 > System.currentTimeMillis()) {
            toast.cancel();
            Intent music = new Intent();
            music.setClass(this,
                    MusicService.class);
            stopService(music);
            mHomeWatcher.stopWatch();
            doUnbindService();
            super.onBackPressed();
        } else {
            toast = Toast.makeText(Menu.this,
                    "Press again to exit",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        mTIME = System.currentTimeMillis();


    }

    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name,
                IBinder
                        binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this,
                        MusicService.class),
                Scon,
                Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

}

