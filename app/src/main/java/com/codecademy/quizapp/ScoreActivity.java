package com.codecademy.quizapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;


public class ScoreActivity extends AppCompatActivity {
    DonutProgressView donutProgressView;

    private float bugtongAverage,foodAverage,historyAverage,geographyAverage,totalAverage;

    private TextView tv_bugtongAverage,tv_foodAverage,tv_historyAverage,tv_geographyAverage,tv_totalAverage;
    private ProgressBar pb_bugtongAverage,pb_foodAverage,pb_historyAverage,pb_geographyAverage;

    private ArrayList<ObjectAnimator> progressAnimator = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        donutProgressView = findViewById(R.id.activity_score_donut);
        tv_totalAverage = findViewById(R.id.activity_score_textView_total_average_score);
        tv_bugtongAverage = findViewById(R.id.activity_score_textView_average_score1);
        tv_foodAverage = findViewById(R.id.activity_score_textView_average_score2);
        tv_historyAverage = findViewById(R.id.activity_score_textView_average_score3);
        tv_geographyAverage = findViewById(R.id.activity_score_textView_average_score4);
        pb_bugtongAverage = findViewById(R.id.activity_score_progressBar1);
        pb_foodAverage = findViewById(R.id.activity_score_progressBar2);
        pb_historyAverage = findViewById(R.id.activity_score_progressBar3);
        pb_geographyAverage = findViewById(R.id.activity_score_progressBar4);


        setAverageScores();
        addDonut();


    }

    private void setAverageScores() {

        bugtongAverage = (float) 8.6;
        foodAverage = (float) 6.5;
        historyAverage = (float) 4.1;
        geographyAverage = (float) 7.4;
        totalAverage = bugtongAverage + foodAverage + historyAverage + geographyAverage;

        tv_totalAverage.setText(String.valueOf(Math.round(totalAverage)));
        tv_bugtongAverage.setText(String.valueOf(Math.round(bugtongAverage)));
        tv_foodAverage.setText(String.valueOf(Math.round(foodAverage)));
        tv_historyAverage.setText(String.valueOf(Math.round(historyAverage)));
        tv_geographyAverage.setText(String.valueOf(Math.round(geographyAverage)));

        pb_bugtongAverage.setMax(Math.round(totalAverage) / 2);
        pb_foodAverage.setMax(Math.round(totalAverage) / 2);
        pb_historyAverage.setMax(Math.round(totalAverage) / 2);
        pb_geographyAverage.setMax(Math.round(totalAverage) / 2);

        progressAnimator.add(ObjectAnimator.ofInt(pb_bugtongAverage,"progress",0,Math.round(bugtongAverage)));
        progressAnimator.add(ObjectAnimator.ofInt(pb_foodAverage,"progress",0,Math.round(foodAverage)));
        progressAnimator.add(ObjectAnimator.ofInt(pb_historyAverage,"progress",0,Math.round(historyAverage)));
        progressAnimator.add(ObjectAnimator.ofInt(pb_geographyAverage,"progress",0,Math.round(geographyAverage)));

        for (ObjectAnimator x : progressAnimator) {
            x.setDuration(1000);
            x.start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ScoreActivity.this,Menu.class);
        startActivity(i);
        finish();
    }

    private void addDonut() {
        List<DonutSection> sections = new ArrayList<>();
        sections.add(new DonutSection("Bugtong",
                getResources().getColor(R.color.colorPrimaryDark),
                Math.round(bugtongAverage)));
        sections.add(new DonutSection("Food",
                getResources().getColor(R.color.colorAccent),
                Math.round(foodAverage)));
        sections.add(new DonutSection("History",
                getResources().getColor(R.color.colorPrimary),
                Math.round(historyAverage)));
        sections.add(new DonutSection("Geography",
                getResources().getColor(R.color.colorAccentDark),
                Math.round(geographyAverage)));
        donutProgressView.setCap(Math.round(totalAverage));
        donutProgressView.submitData(sections);
    }
}