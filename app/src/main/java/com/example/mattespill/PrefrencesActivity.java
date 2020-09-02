package com.example.mattespill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Locale;


public class PrefrencesActivity extends AppCompatActivity {
    public Locale lang = Locale.getDefault();
    public Locale newLang = Locale.forLanguageTag("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.LightTheme);
        }

        if (lang != Locale.forLanguageTag("nb")){
            Locale.setDefault(Locale.forLanguageTag("de"));
        }

        setContentView(R.layout.activity_prefrences);

        // changing theme
        SwitchCompat themeSwitch = findViewById(R.id.themeSwitch);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            themeSwitch.setChecked(true);
        }
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // add code here
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                finish();
                startActivity(new Intent(PrefrencesActivity.this,
                        PrefrencesActivity.this.getClass()));
            }
        });

        // TODO: make button/switch change language
        final Button btnLang = findViewById(R.id.btnLang);
        // add if here

        btnLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale currentLocale = getResources().getConfiguration().locale;
                System.out.println("Her kommer standard språk: " + currentLocale);
                if (lang.equals(Locale.forLanguageTag("nb"))){
                    changeGerman();
                } else{
                    changeNorwegian();
                }
            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void changeGerman(){
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale("de");
        ((Resources) res).updateConfiguration(conf, dm);
        setContentView(R.layout.activity_main);
        System.out.println("Her kommer språk: " + conf.locale);
        newLang = Locale.forLanguageTag("de");
        Locale.setDefault(newLang);
        System.out.println("Ny default er: " + Locale.getDefault());
        recreate();
    }

    public void changeNorwegian(){
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale("nb");
        ((Resources) res).updateConfiguration(conf, dm);
        setContentView(R.layout.activity_main);
        System.out.println("Her kommer språk: " + conf.locale);
        newLang = Locale.forLanguageTag("nb");
        Locale.setDefault(newLang);
        System.out.println("Ny default er: " + Locale.getDefault());
        recreate();
    }

    public void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.i("knapp", "Back pressed"); // bort før levering
    }
}