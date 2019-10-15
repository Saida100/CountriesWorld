package com.saida_aliyeva.countriesworld.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.saida_aliyeva.countriesworld.R;
import com.saida_aliyeva.countriesworld.Utils;
import com.squareup.picasso.Picasso;

public class CountriesDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_data);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String countryName = bundle.getString("countryName");
        String capital = bundle.getString("capital");
        String area = bundle.getString("area");
        String callingCode = bundle.getString("callingCode");
        String region = bundle.getString("region");
        String subregion = bundle.getString("subregion");
        String domain = bundle.getString("domain");
        String lat = bundle.getString("lat");
        String lng = bundle.getString("lng");
        String currency = bundle.getString("currency");
        String currencySymbol = bundle.getString("symbol");
        String langName = bundle.getString("langName");
        String langNativeName = bundle.getString("langNativeName");
        String flag = bundle.getString("flag");
        String alpha2Code = bundle.getString("alpha2Code");
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(countryName);
        toolbar.setSubtitle(capital);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountriesDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        TextView countryTextView, capitalTextView, areaTextView, area1, regionTextView, subRegiontextView,
                domainTextView, callingCodeTextView, currencyTextView, latlngTextView, languageTextView;
        ImageView flagImageView;
        flagImageView = findViewById(R.id.flagImage);
        countryTextView = findViewById(R.id.countryName);
        capitalTextView = findViewById(R.id.capitalName);
        regionTextView = findViewById(R.id.regionName);
        subRegiontextView = findViewById(R.id.subRegionName);
        areaTextView = findViewById(R.id.areaName);
        area1 = findViewById(R.id.area);
        domainTextView = findViewById(R.id.domainName);
        callingCodeTextView = findViewById(R.id.callingCodeName);
        currencyTextView = findViewById(R.id.currencyName);
        latlngTextView = findViewById(R.id.latlngName);
        languageTextView = findViewById(R.id.languageName);
        countryTextView.setText(countryName);
        capitalTextView.setText(capital);
        regionTextView.setText(region);
        subRegiontextView.setText(subregion);
        areaTextView.setText(area);
        languageTextView.setText(langName);
        domainTextView.setText(domain);
        callingCodeTextView.setText("+" + callingCode);
        currencyTextView.setText(currency + ", " + currencySymbol);
        latlngTextView.setText(lat + ",  " + lng);
        area1.setText("Area" + " (km\u00B2)");
        if (alpha2Code != "AX" && alpha2Code != "AN") {
            Picasso.get().load("http://www.geognos.com/api/en/countries/flag/" + alpha2Code + ".png").into(flagImageView);
        }
        if (alpha2Code.equals("AX")) {
            Utils.loadImage("https://www.crwflags.com/fotw/images/a/", "ax", ".gif", flagImageView);
        }
        if (alpha2Code.equals("AN")) {
            Utils.loadImage("https://www.crwflags.com/fotw/images/a/", "an", ".gif", flagImageView);
        }


    }
}
