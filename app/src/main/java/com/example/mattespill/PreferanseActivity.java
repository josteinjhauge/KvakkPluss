package com.example.mattespill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.Arrays;
import java.util.Locale;


public class PreferanseActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.LightTheme);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefsFragment()).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {
        public Locale newLang;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            Preference pref = findPreference("themeSwitch");
            final SwitchPreference switchPreference = (SwitchPreference) pref;

            pref.setOnPreferenceChangeListener((preference, o) -> {
                boolean state = Boolean.valueOf(o.toString());
                String summaryValue;
                if (state){
                    summaryValue = switchPreference.getSwitchTextOn().toString();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartActivity();
                } else{
                    summaryValue = switchPreference.getSwitchTextOff().toString();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartActivity();
                }
                Toast.makeText(getContext(), "Theme " + summaryValue,
                        Toast.LENGTH_SHORT).show();
                return true;
            });

            Preference langPref = findPreference("langSwitch");
            final SwitchPreference langSwitch = (SwitchPreference) langPref;
            langPref.setOnPreferenceChangeListener((preference, i) -> {
                boolean state = Boolean.valueOf(i.toString());
                String summaryValue2;
                if (state){
                    summaryValue2 = langSwitch.getSwitchTextOn().toString();
                    // kall bytt språk
                    tysk();
                    restartActivity();
                } else {
                    summaryValue2 = langSwitch.getSwitchTextOff().toString();
                    norsk();
                    restartActivity();
                }
                Toast.makeText(getContext(), "Språk " + summaryValue2,
                        Toast.LENGTH_SHORT).show();
                return true;
            });

            // TODO: denne funker ikke
            /*Preference gamePref = findPreference("gameMode");
            final ListPreference listPref = (ListPreference) gamePref;
            gamePref.setOnPreferenceChangeListener((preference, p) -> {
                boolean state = Boolean.valueOf(p.toString());
                String summary = ""; // denne er den som vi ønsker å sette
                if (state){
                    summary = ((ListPreference) gamePref).getEntry().toString();
                    gamePref.setSummary(summary);
                    restartActivity();
                }
                Toast.makeText(getContext(), "GameMode " + summary,
                        Toast.LENGTH_SHORT).show();

                return true;
            });*/
        }
        // TODO: saveInstanceState ta fra preferences

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

        private void restartActivity(){
            Intent i = new Intent(getActivity(), PreferanseActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }
}

