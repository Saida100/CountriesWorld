package com.saida_aliyeva.countriesworld.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.saida_aliyeva.countriesworld.Utils;
import com.saida_aliyeva.countriesworld.activity.QuizByCapitalActivity;
import com.saida_aliyeva.countriesworld.R;


public class QuizFragment extends Fragment {

    String getTextRadioButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button capitalButton = view.findViewById(R.id.byCapitalName);
        Button flagButton = view.findViewById(R.id.byFlagName);
        capitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();


            }
        });
        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void showDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.items_dialog_window, null);
        initAndClickRadioButton(view);
        dialog.setTitle("Selection question number")
                .setMessage("Please select question number")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Bundle bundle = new Bundle();
                        bundle.putString("radioButton", getTextRadioButton);
                        Intent intent = new Intent(getActivity(), QuizByCapitalActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                })
                .show();
    }


    public void initAndClickRadioButton(View view) {
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        final RadioButton firstRadioButton = view.findViewById(R.id.first_answer);
        final RadioButton secondRadioButton = view.findViewById(R.id.second_answer);
        final RadioButton thirdRadioButton = view.findViewById(R.id.third_answer);
        final RadioButton fourthRadioButton = view.findViewById(R.id.fourth_answer);
        final RadioButton fifthRadioButton = view.findViewById(R.id.fifth_answer);
        final RadioButton sixthRadioButton = view.findViewById(R.id.sixth_answer);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.first_answer:
                        getTextRadioButton = firstRadioButton.getText().toString();
                        Log.e("log", getTextRadioButton);
                        break;
                    case R.id.second_answer:
                        getTextRadioButton = secondRadioButton.getText().toString();
                        Log.e("log", getTextRadioButton);
                        break;
                    case R.id.third_answer:
                        getTextRadioButton = thirdRadioButton.getText().toString();
                        Log.e("log", getTextRadioButton);
                        break;
                    case R.id.fourth_answer:
                        getTextRadioButton = fourthRadioButton.getText().toString();
                        Log.e("log", getTextRadioButton);
                        break;
                    case R.id.fifth_answer:
                        getTextRadioButton = fifthRadioButton.getText().toString();
                        Log.e("log", getTextRadioButton);
                        break;
                    case R.id.sixth_answer:
                        getTextRadioButton = sixthRadioButton.getText().toString();
                        Log.e("log", getTextRadioButton);
                        break;
                }
            }
        });

    }

}
