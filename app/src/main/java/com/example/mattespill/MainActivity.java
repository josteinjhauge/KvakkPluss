package com.example.mattespill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity {
    public Locale newLang;

    // Fra shared prefs
    String lang;
    String actland;


    // TODO: dette er en git-test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();

        // sjekke språk mot det som kommer fra sharedprefs
        setLang(lang);
        actland = lang;
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        try {
                            if (key.equals("lang_pref"))
                            {
                                setLang(lang);
                                getFragmentManager().beginTransaction().replace(android.R.id.content,
                                        new PreferencesActivity.PrefsFragment()).commit();
                            }
                        } catch (Exception e){
                            System.out.println("Feilmelding kommer her " + e);
                        }
                    }
                };

        SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);

        setContentView(R.layout.activity_main);

        // knapper for å skifte activity
        Button btnGame = findViewById(R.id.btnStartGame);
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });
        Button btnStats = findViewById(R.id.btnStats);
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsScreen();
            }
        });
        Button btnPrefrences = findViewById(R.id.btnPrefrences);
        btnPrefrences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrefrencesScreen();
            }
        });
    }

    public void loadData() {
        Log.d("TAG", "loadData MainAct: ");
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        lang = sharedPreferences.getString("lang_pref", "no");
        System.out.println("----:" + lang + ":----");
    }

    public void openGameScreen(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
        Log.i("knapp", "StartGame pressed"); // bort før levering
    }

    public void openStatsScreen(){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
        finish();
        Log.i("knapp", "Statistics pressed"); // bort før levering
    }

    public void openPrefrencesScreen(){
        // TODO: endre Intent linje for å bytte mellom de to løsningene
        // Ny løsning med bruk av fragment
        Intent intent = new Intent(this, PreferencesActivity.class);
        // gammel løsning
        // Intent intent = new Intent(this, PrefrencesActivity.class);
        startActivity(intent);
        Log.i("knapp", "Prefrences pressed"); // bort før levering
    }

    public void tysk(){
        setLang("de");
    }

    public void norsk(){
        setLang("nb");
    }

    public void setLang(String landskode){
        Log.d("TAG","settland med landskode: " + landskode + " kjører nå");

        newLang = Locale.forLanguageTag(landskode);
        Log.d("TAG", "newLang er satt til: " + newLang);

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        Locale locale = new Locale(landskode);
        cf.locale = locale;
        res.updateConfiguration(cf,dm);
    }

}