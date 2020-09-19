package com.example.mattespill;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Locale;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class PreferencesActivity extends PreferenceActivity {

    String actland;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getDefaultSharedPreferences(this);
        String lang = pref.getString("lang_pref", "no");
        setLang(lang);
        actland = lang;

        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener =
                (sharedPreferences, key) -> {
                    try {
                        if (key.equals("lang_pref")) {
                            String lang1 = pref.getString("lang_pref", "no");
                            setLang(lang1);
                            getFragmentManager().beginTransaction().replace(android.R.id.content,
                                    new PrefsFragment()).commit();
                        }
                    } catch (Exception e){
                        Log.d("onCreate", "onCreate: " + e);
                    }
                };
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefsFragment()).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    public void setLang(String landskode){
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