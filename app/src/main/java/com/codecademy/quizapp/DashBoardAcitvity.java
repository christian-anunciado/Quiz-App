package com.codecademy.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class DashBoardAcitvity extends AppCompatActivity {

    private Button submit;
    private TextView name, appName;
    private ImageView image;
    TextInputLayout text;
    RelativeLayout rl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_acitvity);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        submit = findViewById(R.id.btn_start);
        name = findViewById(R.id.et_name);
        appName = findViewById(R.id.tv_db_appName);
        image = findViewById(R.id.iv_db_image);
        text = findViewById(R.id.tf_text);
        rl = findViewById(R.id.rl_db);

        KeyboardVisibilityEvent.setEventListener(DashBoardAcitvity.this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (!isOpen) {
                    text.clearFocus();
                    name.clearFocus();
                }
            }
        });

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.clearFocus();
                name.clearFocus();
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //text.setErrorIconDrawable(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text.setErrorIconDrawable(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //text.setErrorIconDrawable(null);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(DashBoardAcitvity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    text.setError("*Field is empty");
                    text.clearFocus();
                    name.clearFocus();
                } else {
                    Intent i = new Intent(DashBoardAcitvity.this, Menu.class);
                    startActivity(i);
                    saveData();
                    finish();
                }
            }
        });

    }

   public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name.getText().toString().trim());
        editor.commit();
   }
}