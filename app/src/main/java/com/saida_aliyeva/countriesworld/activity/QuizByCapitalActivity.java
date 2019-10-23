package com.saida_aliyeva.countriesworld.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saida_aliyeva.countriesworld.Utils;
import com.saida_aliyeva.countriesworld.fragment.QuizResultFragment;
import com.saida_aliyeva.countriesworld.model_class.Countries;
import com.saida_aliyeva.countriesworld.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizByCapitalActivity extends AppCompatActivity {

    List<Countries> countries;
    Button aButton, bButton, cButton, dButton, nextQuestionButton;
    TextView timeTextView, questionCountTextView, questionContentTextView;
    ImageView flagImageView;
    Random random;
    List<Countries> newList = new ArrayList<>();
    Boolean checkNextQuestion = true;
    String checkCorrectAnswer = "";
    String selectedAnswer = "";
    int questionCount;
    int count = 1;
    long startTime = 0;
    int getRandomNumber;
    int countCorrectAnswers = 0;
    Toolbar toolbar;
    LinearLayout fragmentLayout, questionLayout;
    QuizResultFragment quizResultFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_by_capital);
        initViews();
        quizResultFragment = new QuizResultFragment();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quiz by capital");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        questionCount = Integer.parseInt(bundle.getString("radioButton"));

        nextQuestionButton.setText("Verify");
        questionCountTextView.setText("Question\n" + count + "/" + questionCount);
        getTasksFromSharedPrefs(this);
        for (int i = 0; i < countries.size(); i++) {
            if (!countries.get(i).getCapital().equals("")) {
                newList.add(countries.get(i));
            }
        }
        clickNextButton(nextQuestionButton);
        random = new Random();
        setQuestionAndCorrectAnswer();

        final Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                //   timeTextView.setText(String.format("%d:%02d", minutes, seconds));
                timeTextView.setText("Time\n" + minutes + " m " + seconds + " sec");
                timerHandler.postDelayed(this, 500);
            }
        };
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelectedButtonColor(aButton, bButton, cButton, dButton);
                selectedAnswer = "a";
            }
        });
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelectedButtonColor(bButton, aButton, cButton, dButton);
                selectedAnswer = "b";

            }
        });
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelectedButtonColor(cButton, bButton, aButton, dButton);
                selectedAnswer = "c";

            }
        });
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelectedButtonColor(dButton, bButton, cButton, aButton);
                selectedAnswer = "d";

            }
        });
    }

    public List<Countries> getTasksFromSharedPrefs(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("currentTasks", "");
        countries = gson.fromJson(json, new TypeToken<ArrayList<Countries>>() {
        }.getType());
        return countries;
    }

    public void setQuestionAndCorrectAnswer() {
        getRandomNumber = random.nextInt(newList.size());
        questionContentTextView.setText("What is the capital of " + newList.get(getRandomNumber).getName() + "?");
        getSupportActionBar().setSubtitle(newList.get(getRandomNumber).getName());
        String alpha2Code = newList.get(getRandomNumber).getAlpha2Code();
        if (alpha2Code != "AX" && alpha2Code != "AN") {
            Picasso.get().load("http://www.geognos.com/api/en/countries/flag/" + alpha2Code.toUpperCase() + ".png").into(flagImageView);
        }
        if (alpha2Code.equals("AX")) {
            Utils.loadImage("https://www.crwflags.com/fotw/images/a/", "ax", ".gif", flagImageView);
        }
        if (alpha2Code.equals("AN")) {
            Utils.loadImage("https://www.crwflags.com/fotw/images/a/", "an", ".gif", flagImageView);
        }
        String correctAnswer = newList.get(getRandomNumber).getCapital();
        newList.remove(getRandomNumber);
        int numberAnswerButton = random.nextInt(4);
        switch (numberAnswerButton) {
            case 0:
                aButton.setText(correctAnswer);
                setVariants(bButton);
                setVariants(cButton);
                setVariants(dButton);
                checkCorrectAnswer = "a";
                break;
            case 1:
                bButton.setText(correctAnswer);
                setVariants(aButton);
                setVariants(cButton);
                setVariants(dButton);
                checkCorrectAnswer = "b";
                break;
            case 2:
                cButton.setText(correctAnswer);
                setVariants(bButton);
                setVariants(aButton);
                setVariants(dButton);
                checkCorrectAnswer = "c";
                break;
            case 3:
                dButton.setText(correctAnswer);
                setVariants(bButton);
                setVariants(cButton);
                setVariants(aButton);
                checkCorrectAnswer = "d";
                break;
        }
    }

    public void setVariants(Button button1) {
        int rand = random.nextInt(newList.size());
        button1.setText(newList.get(rand).getCapital());
    }


    public void changeSelectedButtonColor(Button selectedButton, Button bButton, Button cButton, Button dButton) {
        selectedButton.setBackgroundColor(Color.BLUE);
        bButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        cButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        dButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
    }

    public void doNotchangeSelectedButtonColor(Button selectedButton, Button bButton, Button cButton, Button dButton) {
        selectedButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        bButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        cButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        dButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
    }

    public void showCorrectAnswer() {
        switch (checkCorrectAnswer) {
            case "a":
                aButton.setBackgroundColor(Color.GREEN);
                break;
            case "b":
                bButton.setBackgroundColor(Color.GREEN);
                break;
            case "c":
                cButton.setBackgroundColor(Color.GREEN);
                break;
            case "d":
                dButton.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }

    public void checkCorrectAnswer() {
        if (!selectedAnswer.equals(checkCorrectAnswer)) {
            switch (selectedAnswer) {
                case "a":
                    aButton.setBackgroundColor(Color.RED);
                    break;
                case "b":
                    bButton.setBackgroundColor(Color.RED);
                    break;
                case "c":
                    cButton.setBackgroundColor(Color.RED);
                    break;
                case "d":
                    dButton.setBackgroundColor(Color.RED);
                    break;
                default:
                    break;
            }

        } else {
            countCorrectAnswers++;
            Log.e("countCorrectAnswers", String.valueOf(countCorrectAnswers));
        }
    }

    public void clickNextButton(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if (!checkNextQuestion) {
                    count++;
                    button.setText("Verify");
                    if (count > questionCount) {
                        //  finish();
                        fragmentLayout.setVisibility(View.VISIBLE);
                        questionLayout.setVisibility(View.GONE);
                        toolbar.setTitle("Your result");
                        toolbar.setSubtitle("");
                        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
                        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                        Bundle bundle = new Bundle();
                        bundle.putString("time", timeTextView.getText().toString().substring(4));
                        bundle.putString("correctAnswers", String.valueOf(countCorrectAnswers));
                        bundle.putString("uncorrectAnswers", String.valueOf(questionCount - countCorrectAnswers));
                        quizResultFragment.setArguments(bundle);
                        setFragment();
                    } else {
                        questionCountTextView.setText("Question\n" + count + "/" + questionCount);
                        setQuestionAndCorrectAnswer();
                        doNotchangeSelectedButtonColor(aButton, bButton, cButton, dButton);
                    }

                    Log.e("count=", String.valueOf(count));
                    checkNextQuestion = true;

                } else {
                    showCorrectAnswer();
                    checkCorrectAnswer();
                    button.setText("Next");
                    checkNextQuestion = false;
                    selectedAnswer = "";
                }

            }


        });

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            showDialog();
        } else {
            finish();
        }
    }

    public void showDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("About continue quiz")
                .setMessage("Are you sure to stop quiz?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .show();
    }


    public void initViews() {
        fragmentLayout = findViewById(R.id.container);
        questionLayout = findViewById(R.id.questions);
        toolbar = findViewById(R.id.toolbar);
        nextQuestionButton = findViewById(R.id.nextQuestion);
        timeTextView = findViewById(R.id.time);
        questionCountTextView = findViewById(R.id.questionCount);
        questionContentTextView = findViewById(R.id.question);
        flagImageView = findViewById(R.id.flag);
        aButton = findViewById(R.id.a);
        bButton = findViewById(R.id.b);
        cButton = findViewById(R.id.c);
        dButton = findViewById(R.id.d);
    }

    public void setFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, quizResultFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
