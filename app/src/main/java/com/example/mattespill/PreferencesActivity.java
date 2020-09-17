package com.example.mattespill;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;


public class PreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefsFragment()).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {
        public Locale newLang;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

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

        private void restartActivity(){
            Intent i = new Intent(getActivity(), PreferencesActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }
}

