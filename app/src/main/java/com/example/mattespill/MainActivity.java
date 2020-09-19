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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();

        // sjekke språk mot det som kommer fra sharedprefs
        setLang(lang);
        actland = lang;
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener =
                (sharedPreferences, key) -> {
                    try {
                        if (key.equals("lang_pref"))
                        {
                            setLang(lang);
                            getFragmentManager().beginTransaction().replace(android.R.id.content,
                                    new PreferencesActivity.PrefsFragment()).commit();
                        }
                    } catch (Exception e){
                        Log.d("onCreate", "onCreate: " + e);
                    }
                };

        SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);

        setContentView(R.layout.activity_main);

        setButtons();
    }

    public void setButtons() {
        Button btnGame = findViewById(R.id.btnStartGame);
        btnGame.setOnClickListener(v -> openGameScreen());

        Button btnStats = findViewById(R.id.btnStats);
        btnStats.setOnClickListener(v -> openStatsScreen());

        Button btnPrefrences = findViewById(R.id.btnPrefrences);
        btnPrefrences.setOnClickListener(v -> openPrefrencesScreen());
    }

    public void loadData() {
        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(this);
            lang = sharedPreferences.getString("lang_pref", "no");
        } catch (Exception e) {
            Log.d("loadData", "loadData: " + e);
        }
    }

    public void openGameScreen(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void openStatsScreen(){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
        finish();
    }

    public void openPrefrencesScreen(){
        Intent intent = new Intent(this, PreferencesActivity.class);
       // denne må IKKE kalle finish(), for da kan man ikke lukke fragment
        startActivity(intent);
    }

    public void setLang(String landskode){
        newLang = Locale.forLanguageTag(landskode);

        try {
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration cf = res.getConfiguration();
            Locale locale = new Locale(landskode);
            cf.locale = locale;
            res.updateConfiguration(cf,dm);
        } catch (Exception e) {
            Log.d("setLang", "setLang: " + e);
        }
    }
}