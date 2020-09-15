package com.example.mattespill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.example.mattespill.GameActivity.COUNTER;
import static com.example.mattespill.GameActivity.RESULT;
import static com.example.mattespill.GameActivity.SHARED_GAME_PREFS;

public class StatisticsActivity extends AppCompatActivity {
    String result;
    int count;
    ListView listView;
    ArrayList<Results> resultList = new ArrayList<>();
    ArrayList<String> names= new ArrayList<>();
    ArrayList<String> resultArray = new ArrayList<>();
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

        // TODO: fylle arraylist av object med resultat fra sharedPrefs = result

        makeList();
        // listView = findViewById(R.id.listView);

        /* denne må bruke arraylist av object samme inne i klassen bytte ut begge arrayer med arraylist av object
        CustomListAdapter adapter = new CustomListAdapter(this, resultList);
        listView.setAdapter(adapter);
        */
        RecyclerView resView = (RecyclerView) findViewById(R.id.listView);
        resView.setLayoutManager(new LinearLayoutManager(this));
        ResultsAdapter resAdapter = new ResultsAdapter(this.getLayoutInflater(), resultList);
        resView.setAdapter(resAdapter);


        Button btnBack = findViewById(R.id.btnBackStats);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void makeList(){
        if (count > 0){
            for (int i = 0; i < count; i++){
                names.add("Spill " + count);
                resultArray.add(result);
                Results results = new Results(names.get(i), resultArray.get(i));
                System.out.println(results);
                resultList.add(results);
            }
        } if (count == 0){
            Results dummy = new Results("DummyGame", "Dummyscore");
            resultList.add(dummy);
            System.out.println(resultList);
        }
        count++;
    }
    /*public void makeList() {
        try {
            Log.d("TAG", "makeList start: ");
            // Create and populate a List of planet names.
            System.out.println("her kommer resultatet fra Gson: " + resultArray);
           /* resultArray.add(result);
            if (!(resultArray.size() == 0)) {
                for (int i = 0; i < resultArray.size(); i++) {
                    names.add("Spill " + (names.size() + i));
                    Results results = new Results(names.get(i), resultArray.get(i));
                    System.out.println(results);
                    resultList.add(results);
                }
                for (Results results : resultList) {
                    System.out.println(results.name + " \n " + results.score);
                    System.out.println("--------------");
                }
            } else {
                // set dummy
                Results dummy = new Results("DummyGame", "Dummyscore");
                resultList.add(dummy);
                System.out.println(resultList);
            }

            /*for (int i = 0; i < gameName.length; i++){
                String nameGame = "Spill " + (gameName.length + i);
                i++;
                gameName[i];
            }
            
            Results resultat1 = new Results("spill1", "1/5");
            resultList.add(1, resultat1);
            resultList.remove(resultat1);
            String name = "Spill " + (resultList.size() + 1);
            resultList.add(new Results(name, result));
        } catch (Exception e){
            Log.d("TAG", "makeList: " + e);
            System.out.println("makeList: " + e);
        }
    }*/


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
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
        result = sharedPreferences.getString(RESULT,"resultat");
        count = sharedPreferences.getInt(COUNTER, 0);
        // TODO: prøve å fikse denne, tror grunnen er at den ikke lagrer hva som er i arraylist
        // result = sharedPreferences.getString(RESULTS,"");
        System.out.println("her kommer resultat:" + result + ":---- \n" + " counter = " + count);
        Gson gson = new Gson();
        String response = sharedPreferences.getString(RESULT, null);
        Type type = new TypeToken<ArrayList<Results>>() {}.getType();
        // resultList = gson.fromJson(response,new TypeToken<List<Results>>(){}.getType());
        resultList = gson.fromJson(response, type);
        System.out.println("resultlist består av: " + resultList);

        if (resultList == null){
            Log.d("TAG", "loadData: returned null");
            Results dummy = new Results("DummyGame", "Dummyscore");
            resultList.add(dummy);
            System.out.println(resultList);
        }

        // fra preferences
        SharedPreferences sharedprefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        lang = sharedprefs.getBoolean("langSwitch", false);
        System.out.println("----:" + lang + ":----");
        // TODO: tror ikke gson formatet funker

    }

    public void saveData(){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(resultList);
        editor.remove(RESULT).apply();
        editor.putString(RESULT, json);
        editor.apply();
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