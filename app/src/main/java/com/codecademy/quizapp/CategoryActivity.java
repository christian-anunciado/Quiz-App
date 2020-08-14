package com.codecademy.quizapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bugtong,food,history,geography,play,random;
    private int category = 0;
    private String key = "CATEGORY";

    private MusicService mServ;
    private boolean MUSIC;
    private boolean SOUND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bugtong = findViewById(R.id.btn_category_bugtong);
        food = findViewById(R.id.btn_category_food);
        history = findViewById(R.id.btn_category_history);
        geography = findViewById(R.id.btn_category_geography);
        random = findViewById(R.id.btn_category_random);
        play = findViewById(R.id.btn_category_play);

        loadData();
        if(MUSIC)
        doBindService();

        bugtong.setOnClickListener(this);
        food.setOnClickListener(this);
        history.setOnClickListener(this);
        geography.setOnClickListener(this);
        random.setOnClickListener(this);
        play.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_category_bugtong :
                selectedOption(bugtong);
                category = 1;
                break;
            case R.id.btn_category_food :
                selectedOption(food);
                category = 2;
                break;
            case R.id.btn_category_history :
                selectedOption(history);
                category = 3;
                break;
            case R.id.btn_category_geography :
                selectedOption(geography);
                category = 4;
                break;
            case R.id.btn_category_random :
                selectedOption(random);
                category = 5;
                break;
            case R.id.btn_category_play :
                if (category == 0) {
                    Toast.makeText(CategoryActivity.this, "Select Category", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(CategoryActivity.this, QuestionsActivity.class);
                    intent.putExtra(key, category);
                    startActivity(intent);
                    finish();
                }
                break;
        }

    }
    @Override
    public void onBackPressed() {
        if(MUSIC)
        doUnbindService();
        Intent i = new Intent(CategoryActivity.this, Menu.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mServ != null) {

            mServ.resumeMusic();
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
    void defaultOption() {
        ArrayList<Button> options = new ArrayList<Button>();
        options.add(0, bugtong);
        options.add(1, food);
        options.add(2, history);
        options.add(3, geography);
        options.add(4,random);

        for (Button btn : options) {
            btn.setBackground(ContextCompat.getDrawable(this, R.drawable.button_default_bg));
        }
    }

    void selectedOption(Button btn) {
        defaultOption();
        btn.setBackground(ContextCompat.getDrawable(this, R.drawable.button_selected_bg));
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

    private boolean mIsBound = false;

    void doBindService() {
        bindService(new Intent(this,
                        MusicService.class),
                Scon,
                Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",
                MODE_PRIVATE);

        MUSIC = sharedPreferences.getBoolean("music",
                true);
        SOUND = sharedPreferences.getBoolean("sound",
                true);
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }
}