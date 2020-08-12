package com.codecademy.quizapp;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private Animation fade;
    private ImageView title;
    private TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_acitvity);

        fade = AnimationUtils.loadAnimation(this,R.anim.animation);

        title = findViewById(R.id.iv_sc_image);
        logo = findViewById(R.id.tv_sc_logo);
        slogan = findViewById(R.id.tv_sc_slogan);

        title.setAnimation(fade);
        logo.setAnimation(fade);
        slogan.setAnimation(fade);

    }
}