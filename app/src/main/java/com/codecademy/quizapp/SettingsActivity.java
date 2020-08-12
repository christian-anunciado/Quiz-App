package com.codecademy.quizapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class SettingsActivity extends AppCompatActivity {

    Button editName,createNew;
    Switch music,sound;


    private String NAME;
    private boolean MUSIC,SOUND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editName = findViewById(R.id.settings_editName);
        createNew = findViewById(R.id.settings_createNew);
        music = findViewById(R.id.settings_switch_music);
        sound = findViewById(R.id.settings_switch_sound);
        loadData();

        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingsActivity.this,"Music On",Toast.LENGTH_SHORT).show();
                    MUSIC = true;
                }
                else {
                    Toast.makeText(SettingsActivity.this,"Music Off",Toast.LENGTH_SHORT).show();
                    MUSIC = false;
                }
                saveData();

            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingsActivity.this,"Sound On",Toast.LENGTH_SHORT).show();
                    SOUND = true;

                }
                else {
                    Toast.makeText(SettingsActivity.this,"Sound Off",Toast.LENGTH_SHORT).show();
                    SOUND = false;
                }
                saveData();
            }
        });

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditNameName();
            }
        });

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCreateNew();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),Menu.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",NAME);
        editor.putBoolean("music",MUSIC);
        editor.putBoolean("sound",SOUND);
        editor.commit();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData", MODE_PRIVATE);
//        String user = sharedPreferences.getString("name", "");
        MUSIC = sharedPreferences.getBoolean("music",true);
        SOUND = sharedPreferences.getBoolean("sound",true);

        music.setChecked(MUSIC);
        sound.setChecked(SOUND);

    }

    public void clearData() {
        SharedPreferences sharedPreferences = getSharedPreferences("mData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void setEditNameName(){
        setTheme(R.style.NoActionBarTheme);
        View view = getLayoutInflater().inflate(R.layout.dialog_edit_name,null);

        final EditText editText = (EditText) view.findViewById(R.id.dialog_et);
        final TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.dialog_tf);

        final MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(SettingsActivity.this);
        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Change", null);

        dialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                            int which) {
                        dialog.dismiss();
                    }
                });

        final AlertDialog mAlertDialog = dialog.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positive = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString().isEmpty()){
                            textInputLayout.setError("Field is empty!");
                            textInputLayout.clearFocus();
                            editText.clearFocus();
                        }else {
                            NAME = editText.getText().toString().trim();
                            mAlertDialog.dismiss();
                            saveData();
                            Toast.makeText(SettingsActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mAlertDialog.show();
        editText.requestFocus();
        mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


    }

    public void setCreateNew(){
        setTheme(R.style.NoActionBarTheme);
        View view = getLayoutInflater().inflate(R.layout.dialog_edit_name,null);

        final TextView textView = (TextView) view.findViewById(R.id.dialog_tv);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_et);
        final TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.dialog_tf);
        textView.setText("Enter new user");

        final MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(SettingsActivity.this);
        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Create", null);

        dialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                            int which) {
                        dialog.dismiss();
                    }
                });

        final AlertDialog mAlertDialog = dialog.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positive = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString().isEmpty()){
                            textInputLayout.setError("Field is empty!");
                            textInputLayout.clearFocus();
                            editText.clearFocus();
                        }else {
                            NAME = editText.getText().toString().trim();

                            final MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(SettingsActivity.this);
                            materialAlertDialogBuilder.setCancelable(false);
                            materialAlertDialogBuilder.setTitle("Proceed?");
                            materialAlertDialogBuilder.setMessage("It will delete your entire progress.");

                            materialAlertDialogBuilder.setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                int which) {
                                            NAME = editText.getText().toString().trim();
                                            mAlertDialog.dismiss();
                                            clearData();
                                            Toast.makeText(SettingsActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                            saveData();
                                        }
                                    });

                            materialAlertDialogBuilder.setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            materialAlertDialogBuilder.show();
                        }
                    }
                });
            }
        });


        mAlertDialog.show();
        editText.requestFocus();
        mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,
                Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }
}