package com.saida_aliyeva.countriesworld.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saida_aliyeva.countriesworld.R;
import com.saida_aliyeva.countriesworld.activity.QuizByCapitalActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class QuizResultFragment extends Fragment {

    public QuizResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_result, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView timeTextView, scoreTextView;
        PieChartView pieChartView;
        pieChartView = view.findViewById(R.id.diagramYourResult);
        timeTextView = view.findViewById(R.id.yourResultTime);
        scoreTextView = view.findViewById(R.id.yourResultScore);
        Bundle bundle = getArguments();
        String correctAnswers = bundle.getString("correctAnswers");
        String uncorrectAnswers = bundle.getString("uncorrectAnswers");
        timeTextView.setText("You completed quiz time\n" + bundle.getString("time"));
        scoreTextView.setText("You scored " + correctAnswers);
        List<SliceValue> chartList = new ArrayList<>();
        float c = Float.parseFloat(correctAnswers);
        float u = Float.parseFloat(uncorrectAnswers);
        chartList.add(new SliceValue(c, Color.GREEN).setLabel(correctAnswers));
        chartList.add(new SliceValue(u, Color.RED).setLabel(uncorrectAnswers));
        PieChartData pieChartData = new PieChartData(chartList);
        pieChartData.setHasLabels(true).setValueLabelsTextColor(Color.BLACK);
        pieChartData.setValueLabelTextSize(20);
        pieChartData.setValueLabelBackgroundEnabled(false);
        pieChartView.setPieChartData(pieChartData);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
