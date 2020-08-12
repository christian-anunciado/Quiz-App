package com.codecademy.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int SPLASH_SCREEN = 2000;
    private Animation fade;
    private ImageView title;
    private TextView logo, slogan;
    private String USER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        fade = AnimationUtils.loadAnimation(this, R.anim.animation);

        title = findViewById(R.id.iv_sc_image);
        logo = findViewById(R.id.tv_sc_logo);
        slogan = findViewById(R.id.tv_sc_slogan);

        loadData();

    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",MODE_PRIVATE);
        String user = sharedPreferences.getString("name","");
        if (user.isEmpty()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, DashBoardAcitvity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_SCREEN);
        }   else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_SCREEN);
        }
    }
}