package com.codecademy.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bugtong,food,history,geography,play,random;
    private int category = 0;
    private String key = "CATEGORY";

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
        setTheme(R.style.NoActionBarTheme);
        MaterialAlertDialogBuilder dialogs= new MaterialAlertDialogBuilder(CategoryActivity.this);
        dialogs.setCancelable(true);
        dialogs.setTitle("Return to Menu?");

        dialogs.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogs.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(),Menu.class);
                startActivity(i);
                finish();
            }
        });
        dialogs.create().show();
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
}