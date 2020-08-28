package com.example.mattespill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // TODO: dette er en git-test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove navbar from android
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_main);

        // knapper for å skifte activity
        Button btnGame = findViewById(R.id.btnStartGame);
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });
        Button btnStats = findViewById(R.id.btnStats);
        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsScreen();
            }
        });
        Button btnPrefrences = findViewById(R.id.btnPrefrences);
        btnPrefrences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPrefrencesScreen();
            }
        });
    }

    public void openGameScreen(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        Log.i("knapp", "StartGame pressed"); // bort før levering
    }

    public void openStatsScreen(){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
        Log.i("knapp", "Statistics pressed"); // bort før levering
    }

    public void openPrefrencesScreen(){
        Intent intent = new Intent(this, PrefrencesActivity.class);
        startActivity(intent);
        Log.i("knapp", "Prefrences pressed"); // bort før levering
    }

   /* public void showButtons(){
        Button btnFive = findViewById(R.id.btn5Questions);
        Button btnFithteen = findViewById(R.id.btn15Questions);
        Button btnTwentyFive = findViewById(R.id.btn25Questions);

        // gjør knapper synlig
        btnFive.setVisibility(View.VISIBLE);
        btnFithteen.setVisibility(View.VISIBLE);
        btnTwentyFive.setVisibility(View.VISIBLE);


    }*/
}