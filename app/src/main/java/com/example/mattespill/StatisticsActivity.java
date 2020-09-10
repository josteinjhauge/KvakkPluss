package com.example.mattespill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.mattespill.GameActivity.RESULT;
import static com.example.mattespill.GameActivity.SHARED_GAME_PREFS;

public class StatisticsActivity extends AppCompatActivity {
    String result;
    ListView listView;
    String [] results = new String[] {
            "1/5", "5/5", "9/10"
    };
    
    ArrayList<String> resultsList = new ArrayList<>();

    // shared preferences
    public static final String SHARED_STATS_PREFS = "sharedStatsPrefs";
    public static final String RESULTS = "results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.LightTheme);
        }

        loadData();
        setContentView(R.layout.activity_statistics);

        // TODO: burde lage metode for å fylle arraylist og bare fylle adapter i oncreate
        makeList(); // denne må fylle arraylist med resultater
        System.out.println("Arraylist inneholder: " + results);

        listView = findViewById(R.id.listView);
        ArrayAdapter<String> resultAdapter = new ArrayAdapter<String>(
                this,R.layout.list_activity, results);
        listView.setAdapter(resultAdapter);

        Button btnBack = findViewById(R.id.btnBackStats);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }
    public void back(){
        // TODO: enten legge inn egen lagre knapp, eller bare gjøre det her
        saveData();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.i("knapp", "Back pressed"); // bort før levering
    }

    public void makeList(){
        Collections.addAll(resultsList, results);
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
        result = sharedPreferences.getString(RESULT,"resultat");
        // TODO: prøve å fikse denne, tror grunnen er at den ikke lagrer hva som er i arraylist
        // results = sharedPreferences.getString(RESULTS,);
        System.out.println("----:" + result + ":----");
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_STATS_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RESULTS, String.valueOf(results));
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
}