package com.example.mattespill;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageHelper {

    public static void changeLocale(Resources res, String locale){
        Configuration config;
        config = new Configuration(res.getConfiguration());
        switch (locale){
            case "no":
                config.locale = new Locale("no");
                break;
            case "de":
                config.locale = Locale.GERMAN;
                break;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
