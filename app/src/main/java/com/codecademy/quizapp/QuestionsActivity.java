package com.codecademy.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class QuestionsActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 20_500;
    private List<Questions> questions = new ArrayList<>();
    private ArrayList<Questions> questionsAnswered = new ArrayList<>();

    private TextView question, optionOne, optionTwo, optionThree, optionFour, progress, toolbarTitle, timer;
    private ImageView image;
    private Button submit;
    private ProgressBar progressBar, timerProgressBar;
    private Toolbar toolbar;
    private ScrollView scrollView;
    private ConstraintLayout questions_timer;

    private int category;
    private int mode;
    private int progressNumber;
    private int currentIndex = 0;
    private int totalCorrect;
    private int playerAnswer = -1;
    private final int maxQuestion = 10;

    private CountDownTimer mCountDownTimer;
    private Vector<AlertDialog> dialogs = new Vector<>();
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            category = extras.getInt("CATEGORY");
//            mode = extras.getInt("MODE",1);
        }

        mode = 1;

        questions = new QuestionList().getQuestions(category);

        progressBar = findViewById(R.id.pb_progressBar);
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_text);
        progress = findViewById(R.id.tv_progress);
        questions_timer = (ConstraintLayout) findViewById(R.id.activity_questions_timer);

        question = findViewById(R.id.tv_questions);
        optionOne = findViewById(R.id.tv_optionOne);
        optionTwo = findViewById(R.id.tv_optionTwo);
        optionThree = findViewById(R.id.tv_optionThree);
        optionFour = findViewById(R.id.tv_optionFour);
        image = findViewById(R.id.iv_image);
        submit = findViewById(R.id.btn_submit);

        scrollView = findViewById(R.id.scrollview);
        timer = findViewById(R.id.timer_text);
        timerProgressBar = findViewById(R.id.timer_progress);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newGame();

        optionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption(optionOne, 1);
                submit.setEnabled(true);

            }
        });

        optionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption(optionTwo, 2);
                submit.setEnabled(true);

            }
        });

        optionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption(optionThree, 3);
                submit.setEnabled(true);

            }
        });

        optionFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption(optionFour, 4);
                submit.setEnabled(true);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerAnswer == -1) {
                    submit.setEnabled(false);
                    progressNumber++;
                    if (progressNumber <= maxQuestion) {
                        onSubmit();
                    } else {
                        Intent i = new Intent(QuestionsActivity.this, ResultActivity.class);
                        i.putExtra("TOTAL_SCORE", totalCorrect);
                        i.putExtra("TOTAL_QUESTIONS", maxQuestion);
                        i.putParcelableArrayListExtra("QUESTIONS_ANSWERED", (ArrayList<? extends Parcelable>) questionsAnswered);
                        startActivity(i);
                        finish();
                    }
                } else {
                    if(mode ==1){
                    pauseTimer();}

                    showCorrectAnswer();
                    if (progressNumber >= maxQuestion) {
                        submit.setText("FINISH");
                    } else {
                        submit.setText("GO TO NEXT QUESTION");
                    }
                }
                playerAnswer = -1;
            }
        });

    }

    void newGame() {
        progressNumber = 1;
        totalCorrect = 0;
        currentIndex = 0;

        Collections.shuffle(questions);
        Questions firstQuestion = newQuestion();
        displayQuestion(firstQuestion);
        progress.setText("0 / " + maxQuestion);
        progressBar.setProgress(progressNumber);
        progressBar.setMax(maxQuestion);
        progress.setText(progressNumber + "/" + maxQuestion);

    }

    void onSubmit() {
        if (progressNumber > maxQuestion) {
            submit.setText("FINISH");
        } else {
            submit.setText("SUBMIT");
        }
        currentIndex++;
        questionsAnswered.add(getCurrentQuestion());
        newQuestion();
        if (mode == 1) {
            resetTimer();
        }
        displayQuestion(getCurrentQuestion());
        progressBar.setProgress(progressNumber);
        progress.setText(progressNumber + "/" + maxQuestion);

    }

    void displayQuestion(Questions questions) {
        setToolbarTitle();
        defaultOption();
        getGameMode();
        scrollView.setScrollY(0);
        if (questions.getOptionThree().isEmpty() && questions.getOptionFour().isEmpty() && questions.getImage() == 0) {
            displayTrueOrFalseQuestion(questions);
        } else if (questions.getImage() == 0) {
            displayTextQuestion(questions);
        } else {
            displayWithImageQuestion(questions);
        }


    }

    void getGameMode() {

        if (mode == 0) {
            questions_timer.setVisibility(View.GONE);
        } else {
            questions_timer.setVisibility(View.VISIBLE);
            startTimer();
        }

    }

    void setToolbarTitle() {
        int categorys = getCurrentQuestion().getCategory();

        if (categorys == 1)
            toolbarTitle.setText("Bugtong");
        if (categorys == 2)
            toolbarTitle.setText("Food");
        if (categorys == 3)
            toolbarTitle.setText("History");
        if (categorys == 4)
            toolbarTitle.setText("Geography");
        if (categorys == 5)
            toolbarTitle.setText("Random");
    }

    Questions newQuestion() {
        return questions.get(currentIndex);
    }

    Questions getCurrentQuestion() {
        Questions currentQuestion = questions.get(currentIndex);
        return currentQuestion;
    }

    void defaultOption() {
        ArrayList<TextView> options = new ArrayList<TextView>();
        options.add(0, optionOne);
        options.add(1, optionTwo);
        options.add(2, optionThree);
        options.add(3, optionFour);

        for (TextView text : options) {
            text.setTextColor(Color.parseColor("#7A8089"));
            text.setTypeface(Typeface.DEFAULT);
            text.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
            text.setClickable(true);
        }
        submit.setEnabled(false);
    }

    void selectedOption(TextView tv, int playerAnswer) {
        this.playerAnswer = playerAnswer;
        defaultOption();
        tv.setTextColor(Color.parseColor("#363A43"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(this, R.drawable.on_touch_option_border_bg));
    }

    void correctOption(int options, int drawableView) {
        if (options == 1) {
            optionOne.setBackground(ContextCompat.getDrawable(this, drawableView));
            optionOne.setTypeface(optionOne.getTypeface(), Typeface.BOLD);
        }
        if (options == 2) {
            optionTwo.setBackground(ContextCompat.getDrawable(this, drawableView));
            optionTwo.setTypeface(optionTwo.getTypeface(), Typeface.BOLD);
        }
        if (options == 3) {
            optionThree.setBackground(ContextCompat.getDrawable(this, drawableView));
            optionThree.setTypeface(optionThree.getTypeface(), Typeface.BOLD);
        }
        if (options == 4) {
            optionFour.setBackground(ContextCompat.getDrawable(this, drawableView));
            optionThree.setTypeface(optionThree.getTypeface(), Typeface.BOLD);
        }
    }

    void showCorrectAnswer() {
        List<TextView> options = new ArrayList<>();
        options.add(0, optionOne);
        options.add(1, optionTwo);
        options.add(2, optionThree);
        options.add(3, optionFour);

        for (TextView i : options) {
            i.setClickable(false);
        }

        if (playerAnswer != getCurrentQuestion().getCorrectAnswer()) {
            correctOption(playerAnswer, R.drawable.wrong_option_border_bg);
        } else {
            totalCorrect += 1;
        }

        if (playerAnswer == 0) {
            for (int i = 1; i <= 4; i++) {
                correctOption(i, R.drawable.wrong_option_border_bg);
            }
        }

        correctOption(getCurrentQuestion().getCorrectAnswer(), R.drawable.correct_option_border_bg);

    }

    void displayWithImageQuestion(Questions questions) {
        question.setText(questions.getQuestion());
        image.setImageResource(questions.getImage());
        optionOne.setText(questions.getOptionOne());
        optionTwo.setText(questions.getOptionTwo());
        optionThree.setText(questions.getOptionThree());
        optionFour.setText(questions.getOptionFour());
        optionThree.setVisibility(View.VISIBLE);
        optionFour.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);
    }

    void displayTrueOrFalseQuestion(Questions questions) {
        question.setText(questions.getQuestion());
        optionOne.setText(questions.getOptionOne());
        optionTwo.setText(questions.getOptionTwo());
        optionThree.setVisibility(View.GONE);
        optionFour.setVisibility(View.GONE);
        image.setVisibility(View.GONE);
    }

    void displayTextQuestion(Questions questions) {
        image.setVisibility(View.GONE);
        optionThree.setVisibility(View.VISIBLE);
        optionFour.setVisibility(View.VISIBLE);
        question.setText(questions.getQuestion());
        optionOne.setText(questions.getOptionOne());
        optionTwo.setText(questions.getOptionTwo());
        optionThree.setText(questions.getOptionThree());
        optionFour.setText(questions.getOptionFour());
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(QuestionsActivity.this);
        dialog.setTitle("Confirm Exit?");
        if (mode == 1) {
            pauseTimer();
        }
        dialog.setMessage("Closing the app will end your current quiz.");
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startTimer();
            }
        });

        dialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        displayQuestion(getCurrentQuestion());
                    }
                });
        dialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog show = dialog.create();
        dialogs.add(show);
        show.show();
    }

    @Override
    protected void onPause() {
        if (mode == 1) {
            pauseTimer();
        }
        for (AlertDialog x : dialogs) {
            if (x.isShowing()) x.dismiss();
        }

        super.onPause();
    }

    @Override
    protected void onRestart() {
        startTimer();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(QuestionsActivity.this);
        if (id == android.R.id.home) {
            if (mode == 1) {
                pauseTimer();
            }
            dialog.setTitle("Return to Menu?");
            dialog.setMessage("This will end your current quiz.");
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    startTimer();
                }
            });

            dialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            displayQuestion(getCurrentQuestion());
                        }
                    });
            dialog.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(QuestionsActivity.this, com.codecademy.quizapp.Menu.class);
                    startActivity(i);
                    finish();
                }
            });
//            dialog.create().show();
            AlertDialog show = dialog.create();
            dialogs.add(show);
            show.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 250) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (Math.round((float)millisUntilFinished / 1000.0f) != mTimeLeftInMillis)
                {
                    mTimeLeftInMillis = Math.round((float)millisUntilFinished);
                    updateCountDownText();
                }
            }

            @Override
            public void onFinish() {
                int DELAY = 3000;
                Handler handler = new Handler();

                scrollView.setScrollY(scrollView.getBottom());
                timer.setText("0");
                playerAnswer = 0;
                progressNumber++;
                showCorrectAnswer();

                if (progressNumber <= maxQuestion) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onSubmit();
                        }
                    }, DELAY);
                } else {
                    pauseTimer();
                    submit.setText("FINISH");
                    submit.setEnabled(true);
                    handler.removeCallbacksAndMessages(null);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(QuestionsActivity.this, ResultActivity.class);
                            i.putExtra("TOTAL_SCORE", totalCorrect);
                            i.putExtra("TOTAL_QUESTIONS", maxQuestion);
                            i.putParcelableArrayListExtra("QUESTIONS_ANSWERED", (ArrayList<? extends Parcelable>) questionsAnswered);
                            startActivity(i);
                            finish();
                        }
                    });
                }
            }
        }.start();

    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    private void updateCountDownText() {
        Integer seconds = (int) (mTimeLeftInMillis / 1000.0f);
        if (seconds <= 5)
            timer.setTextColor(ContextCompat.getColor(QuestionsActivity.this, R.color.colorAccent));
        else
            timer.setTextColor(ContextCompat.getColor(QuestionsActivity.this, R.color.colorPrimaryDark));


        timer.setText(seconds.toString());
        timerProgressBar.setProgress(seconds);
    }
}