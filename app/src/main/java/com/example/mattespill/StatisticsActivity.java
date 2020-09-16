package com.example.mattespill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

import static com.example.mattespill.GameActivity.RESULT;
import static com.example.mattespill.GameActivity.SHARED_GAME_PREFS;

public class StatisticsActivity extends AppCompatActivity {
    private ResultsAdapter resAdapter;
    ListView listView;
    ArrayList<Results> resultList = new ArrayList<>();

    public Locale newLang;

    // shared preferences
    public static final String SHARED_STATS_PREFS = "sharedStatsPrefs";
    public static final String RESULTS = "results";
    boolean lang;

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

        // sjekke språk mot det som kommer fra sharedprefs
        if (lang){
            tysk();
        } else {
            norsk();
        }

        setContentView(R.layout.activity_statistics);

        buildRecyclerView();
        setButtons();
        // listView = findViewById(R.id.listView);
    }

    public void buildRecyclerView(){
        /* TODO: denne under bør funke hvis vi må bruke listView
        ikke fjern CustomListAdpter
         */
/*
        // denne må bruke arraylist av object samme inne i klassen bytte ut begge arrayer med arraylist av object
        CustomListAdapter adapter = new CustomListAdapter(this, resultList);
        listView.setAdapter(adapter);
*/
        /*RecyclerView resView = (RecyclerView) findViewById(R.id.listView);
        resView.setLayoutManager(new LinearLayoutManager(this));
        ResultsAdapter resAdapter = new ResultsAdapter(this.getLayoutInflater(), resultList);
        resView.setAdapter(resAdapter);*/
        RecyclerView resView = (RecyclerView) findViewById(R.id.listView);
        resView.setLayoutManager(new LinearLayoutManager(this));
        resAdapter = new ResultsAdapter(this.getLayoutInflater(), resultList);
        resView.setAdapter(resAdapter);

        resAdapter.setOnItemClickListener(new ResultsAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteResults(position);
            }
        });
    }

    public void setButtons(){
        Button btnBack = findViewById(R.id.btnBackStats);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        ImageView btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultList.clear();
                System.out.println("Resultview etter clear() " + resultList);
                buildRecyclerView();
            }
        });
    }

    public void deleteResults(int position) {
        // TODO: FIX DENNE
        resultList.remove(position);
        resAdapter.notifyItemRemoved(position);
        for (Results result : resultList) {
            System.out.println(result.getName() + " sin score: " + result.getScore());
            System.out.println("---------------");
        }
        System.out.println(resultList.size());
    }

    public void back(){
        // TODO: enten legge inn egen lagre knapp, eller bare gjøre det her
        saveData();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.i("knapp", "Back pressed"); // bort før levering
    }


    public void loadData(){
        // fra gameAct
        try {
            SharedPreferences gameprefs = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
            Gson gson = new Gson();
            String response = gameprefs.getString(RESULT, null);
            System.out.println("response: " + response);
            Type type = new TypeToken<ArrayList<Results>>() {}.getType();
            resultList = gson.fromJson(response, type);
            System.out.println("ResultList: " + resultList + " Response: " + response + "\n"
                    + "Type: " + type);
            if (resultList == null){
                resultList = new ArrayList<>();
            }
        } catch (Exception e) {
            Log.d("ERROR", "loadArrayList: " + e);
            resultList = new ArrayList<>();
        }

        System.out.println("resultlist består av: ");
        for (Results result : resultList) {
            System.out.println(result.getName() + " sin score: " + result.getScore());
            System.out.println("---------------");
        }

        // fra preferences
        SharedPreferences sharedprefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        lang = sharedprefs.getBoolean("langSwitch", false);
        System.out.println("----:" + lang + ":----");
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(resultList);
        editor.putString(RESULT, json);
        editor.apply();

        System.out.println("resultat: " + resultList);
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
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
        cf.setLocale(newLang);
        res.updateConfiguration(cf,dm);
    }
}