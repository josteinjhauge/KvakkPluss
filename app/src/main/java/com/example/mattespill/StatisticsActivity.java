package com.example.mattespill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
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

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.example.mattespill.GameActivity.RESULT;
import static com.example.mattespill.GameActivity.SHARED_GAME_PREFS;

public class StatisticsActivity extends AppCompatActivity {
    private ResultsAdapter resAdapter;
    private RecyclerView resView;
    ListView listView;
    ArrayList<Results> resultList = new ArrayList<>();

    public Locale newLang;

    // shared preferences
    public static final String SHARED_STATS_PREFS = "sharedStatsPrefs";
    public static final String RESULTS = "results";
    public static final String LIST_STATE = "list_state";
    public static final String KEY_RECYCLER_STATE = "recycler_state";

    String actland;
    String lang;

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

        setContentView(R.layout.activity_statistics);
        buildRecyclerView();
        setButtons();
        // listView = findViewById(R.id.listView);
    }

    public void buildRecyclerView(){
        RecyclerView resView = (RecyclerView) findViewById(R.id.listView);
        resView.setLayoutManager(new LinearLayoutManager(this));
        resAdapter = new ResultsAdapter(this.getLayoutInflater(), resultList);
        resView.setAdapter(resAdapter);

    }

    public void setButtons(){
        Button btnBack = findViewById(R.id.btnBackStats);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureDelete();
            }
        });
    }

    public void sureDelete(){
        String txtDialog = getResources().getString(R.string.dialog);
        String txtPositive = getResources().getString(R.string.positive);
        String txtNegative = getResources().getString(R.string.negative);

        DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        // yes trykket
                        resultList.clear();
                        System.out.println("Resultview etter clear() " + resultList);
                        buildRecyclerView();
                        Log.i("knapp", "ja trykket"); // TODO: bort før levering
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        // no trykket
                        Log.i("knapp", "nei trykket"); // TODO: bort før levering
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(txtDialog).setPositiveButton(txtPositive, dialog)
                .setNegativeButton(txtNegative,dialog).show();
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
        lang = sharedprefs.getString("lang_pref", "no");
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

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }
}