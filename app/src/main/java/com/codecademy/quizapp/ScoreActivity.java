package com.codecademy.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;


public class ScoreActivity extends AppCompatActivity {
    DonutProgressView donutProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        donutProgressView = findViewById(R.id.activity_score_donut);
        addDonut();


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
        sections.add(new DonutSection("section1",
                getResources().getColor(R.color.colorPrimaryDark),
                .25f));
        sections.add(new DonutSection("section2",
                getResources().getColor(R.color.colorAccent),
                .25f));
        sections.add(new DonutSection("section3",
                getResources().getColor(R.color.colorPrimary),
                .25f));
        sections.add(new DonutSection("section4",
                getResources().getColor(R.color.colorAccentDark),
                .25f));
        donutProgressView.setCap(1f);
        donutProgressView.submitData(sections);
    }
}