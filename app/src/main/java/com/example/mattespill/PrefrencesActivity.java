package com.example.mattespill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;


public class PrefrencesActivity extends AppCompatActivity {
    public Locale lang = Locale.getDefault();
    public Locale newLang = Locale.forLanguageTag("");
    public int Game = 0;
    RadioGroup rdoGroup;

    private RadioButton rdo5Q;
    private RadioButton rdo10Q;
    private RadioButton rdo25Q;


    // shared preferences
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LANG = "lang";
    public static final String GAME_MODE = "gameMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tema sjekk
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.LightTheme);
        }

        // språk sjekk
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

        rdo5Q = findViewById(R.id.btn5Questions);
        rdo5Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioBtnClicked();
            }
        });

        rdo10Q = findViewById(R.id.btn10Questions);
        rdo10Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioBtnClicked();
            }
        });

        rdo25Q = findViewById(R.id.btn25Questions);
        rdo25Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioBtnClicked();
            }
        });

        onRadioBtnClicked();
    }

    public void onRadioBtnClicked(){

        Log.d("TAG", "onRadioBtnClicked: ");
        Game = 5;

        if (rdo5Q.isChecked()){
            Game = 5;
            System.out.println("btn5 clicked, result is: " + Game);
        }
        if ( rdo10Q.isChecked()){
            Game = 10;
            System.out.println("btn10 clicked, result is: " + Game);
        }
        if (rdo25Q.isChecked()){
            Game = 25;
            System.out.println("btn25 clicked, result is: " + Game);
        }
        System.out.println("-------" + Game + "-------");
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
        // TODO: enten legge inn egen lagre knapp, eller bare gjøre det her
        // saveData();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.i("knapp", "Back pressed"); // bort før levering
    }

    // save data to Sharedprefrences
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(LANG, String.valueOf(lang));
        editor.putInt(GAME_MODE, Game);
        editor.apply();;
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    // Load data from shared prefrences
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Game = sharedPreferences.getInt(GAME_MODE, 5);
        // last også lagret språk her
    }

    // metode for å oppdatere views
    public void updateViews(){
        // TODO: legg inn passende kode her
    }

    // TODO: denne må fikses så instance av radioknapper kan lagres
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("gameMode", Game);
    }

    // TODO: denne må fikses så instance av radioknapper kan hentes
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getInt("gameMode");
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }

}