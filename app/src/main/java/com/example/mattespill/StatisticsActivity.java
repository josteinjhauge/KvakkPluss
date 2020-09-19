package com.example.mattespill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
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
import android.widget.ListView;
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

        setContentView(R.layout.activity_statistics);
        buildRecyclerView();
        setButtons();
    }

    public void buildRecyclerView(){
        try {
            RecyclerView resView = (RecyclerView) findViewById(R.id.listView);
            resView.setLayoutManager(new LinearLayoutManager(this));
            resAdapter = new ResultsAdapter(this.getLayoutInflater(), resultList);
            resView.setAdapter(resAdapter);
        } catch (Exception e) {
            Log.d("buildRecyclerView", "buildRecyclerView: " + e);
        }
    }

    public void setButtons(){
        Button btnBack = findViewById(R.id.btnBackStats);
        btnBack.setOnClickListener(v -> back());

        Button btnDelete = findViewById(R.id.btnDelete);

        if (resultList.size() == 0){
            btnDelete.setEnabled(false);
        } else {
            btnDelete.setOnClickListener(v -> sureDelete());
        }
    }

    public void sureDelete(){
        String txtDialog = getResources().getString(R.string.dialog);
        String txtPositive = getResources().getString(R.string.positive);
        String txtNegative = getResources().getString(R.string.negative);

        DialogInterface.OnClickListener dialog = (dialog1, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    resultList.clear();
                    buildRecyclerView();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(txtDialog).setPositiveButton(txtPositive, dialog)
                .setNegativeButton(txtNegative,dialog).show();
    }

    public void back(){
        saveData();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void loadData(){
        // fra gameAct
        try {
            SharedPreferences gameprefs = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
            Gson gson = new Gson();
            String response = gameprefs.getString(RESULT, null);
            Type type = new TypeToken<ArrayList<Results>>() {}.getType();
            resultList = gson.fromJson(response, type);
            if (resultList == null){
                resultList = new ArrayList<>();
            }
        } catch (Exception e) {
            Log.d("loadData", "loadArrayList: " + e);
            resultList = new ArrayList<>();
        }

        // fra preferences
        SharedPreferences sharedprefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        lang = sharedprefs.getString("lang_pref", "no");
    }

    public void saveData(){
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(resultList);
            editor.putString(RESULT, json);
            editor.apply();
        } catch (Exception e) {
            Log.d("saveData", "saveData: " + e);
        }
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