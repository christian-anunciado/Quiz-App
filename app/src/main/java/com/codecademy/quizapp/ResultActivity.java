package com.codecademy.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    int totalScore;
    int totalQuestions;
    TextView user, score;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            totalScore = extras.getInt("TOTAL_SCORE");
            totalQuestions = extras.getInt("TOTAL_QUESTIONS");
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        user = findViewById(R.id.tv_user);
        score = findViewById(R.id.tv_score);
        finish = findViewById(R.id.btn_finish);

        loadData();

        finish.setBackgroundColor(Color.parseColor("#1e1917"));
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, Menu.class);
                startActivity(i);
                saveData();
                finish();
            }
        });


    }
    public  void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score",totalScore);
        editor.apply();
    }

    public  void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",MODE_PRIVATE);
        String users = sharedPreferences.getString("name","");
        int scores = sharedPreferences.getInt("score",0);

        if (!(users.isEmpty() && scores == 0)) {
            user.setText(users);
            score.setText("You got a score of " + totalScore + " out of " + totalQuestions);
        }
    }
}