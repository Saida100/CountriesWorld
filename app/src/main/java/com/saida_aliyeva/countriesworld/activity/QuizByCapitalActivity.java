package com.saida_aliyeva.countriesworld.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saida_aliyeva.countriesworld.model_class.Countries;
import com.saida_aliyeva.countriesworld.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizByCapitalActivity extends AppCompatActivity {

    List<Countries> countries;
    Button aButton, bButton, cButton, dButton, nextQuestionButton;
    TextView timeTextView, questionCountTextView, questionContentTextView;
    Random random;
    List<Countries> newList = new ArrayList<>();
    Boolean checkNextQuestion = true;
    String checkCorrectAnswer="";
    String selectedAnswer="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_by_capital);

        aButton = findViewById(R.id.a);
        bButton = findViewById(R.id.b);
        cButton = findViewById(R.id.c);
        dButton = findViewById(R.id.d);
        nextQuestionButton = findViewById(R.id.nextQuestion);
        nextQuestionButton.setText("Verify");
        timeTextView = findViewById(R.id.time);
        questionCountTextView = findViewById(R.id.questionCount);
        questionContentTextView = findViewById(R.id.question);
        getTasksFromSharedPrefs(this);
        random = new Random();
        setQuestionAndCorrectAnswer();

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkNextQuestion) {
                    nextQuestionButton.setText("Verify");
                    setQuestionAndCorrectAnswer();
                    doNotchangeSelectedButtonColor(aButton, bButton, cButton, dButton);
                    checkNextQuestion = true;
                } else {
                    showCorrectAnswer();
                    checkCorrectAnswer();
                    nextQuestionButton.setText("Next");
                    checkNextQuestion = false;
                    selectedAnswer="";


                }

            }
        });
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
        for (int i = 0; i < countries.size(); i++) {
            if (!countries.get(i).getCapital().equals("")) {
                newList.add(countries.get(i));
            }
        }
        //  newList.addAll(countries);

        int rd = random.nextInt(newList.size());
        questionContentTextView.setText("What is the capital of " + newList.get(rd).getName() + "?");
        String correctAnswer = newList.get(rd).getCapital();
        newList.remove(rd);
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
        newList.subList(0, newList.size() - 1);
        int rand = random.nextInt(newList.size());
        button1.setText(newList.get(rand).getCapital());
        newList.remove(rand);
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

        }
    }


}
