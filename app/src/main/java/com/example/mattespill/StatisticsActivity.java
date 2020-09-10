package com.example.mattespill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.mattespill.GameActivity.RESULT;
import static com.example.mattespill.GameActivity.SHARED_GAME_PREFS;
import static com.example.mattespill.PrefrencesActivity.GAME_MODE;
import static com.example.mattespill.PrefrencesActivity.SHARED_PREFS;

public class StatisticsActivity extends AppCompatActivity {
    String result;

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
        makeList();
        setContentView(R.layout.activity_statistics);

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
        // saveData();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.i("knapp", "Back pressed"); // bort før levering
    }

    public void makeList(){

    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
        result = sharedPreferences.getString(RESULT,"resultat");
        System.out.println("----:" + result + ":----");
    }
}