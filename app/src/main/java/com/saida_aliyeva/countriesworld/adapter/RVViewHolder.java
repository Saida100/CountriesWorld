package com.saida_aliyeva.countriesworld.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.github.megatronking.svg.support.SVGDrawable;
//import com.github.megatronking.svg.support.extend.SVGImageView;
import com.saida_aliyeva.countriesworld.model_class.Countries;
import com.saida_aliyeva.countriesworld.Listener;
import com.saida_aliyeva.countriesworld.R;
import com.saida_aliyeva.countriesworld.Utils;

import okhttp3.OkHttpClient;


public class RVViewHolder extends RecyclerView.ViewHolder {
    TextView countryNameTextView, capitalNameTextView;
    ImageView flagImageView;
    private static OkHttpClient httpClient;


    public RVViewHolder(@NonNull View itemView) {
        super(itemView);
        flagImageView = itemView.findViewById(R.id.imageFlag);
        countryNameTextView = itemView.findViewById(R.id.countryName);
        capitalNameTextView = itemView.findViewById(R.id.capitalName);

    }

    public void bind(final Countries countries, final Listener listener) {
        try {

            String alpha2Code = countries.getAlpha2Code();
            capitalNameTextView.setText(countries.getCapital());
            countryNameTextView.setText(countries.getName());
            if (!alpha2Code.equals("SS") && !alpha2Code.equals("AX") && !alpha2Code.equals("AN") &&
                    !alpha2Code.equals("CW") && !alpha2Code.equals("GF") &&
                    !alpha2Code.equals("MQ") && !alpha2Code.equals("RE") &&
                    !alpha2Code.equals("SX") && !alpha2Code.equals("GS") &&
                    !countries.getName().contains("Bonaire") &&
                    !countries.getName().contains("Minor") &&
                    !countries.getName().equals("Republic of Kosovo") &&
                    !countries.getName().equals("Guadeloupe")

            ) {
                Utils.loadImage("http://www.geognos.com/api/en/countries/flag/",
                        alpha2Code, ".png", flagImageView);

            } else {
                if (alpha2Code.equals("AX")) {
                    Utils.loadImage("https://www.crwflags.com/fotw/images/a/", "ax", ".gif", flagImageView);
                }
                if (alpha2Code.equals("AN")) {
                    Utils.loadImage("https://www.crwflags.com/fotw/images/a/", "an", ".gif", flagImageView);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.inItemClick(countries);
            }
        });


    }
}
