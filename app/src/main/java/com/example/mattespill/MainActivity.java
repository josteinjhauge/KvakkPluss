package com.example.mattespill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // knapp for å skifte activity
        Button btnGame = findViewById(R.id.btnStartGame);
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });
        /*
        Button btnFive = findViewById(R.id.btn5Questions);
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });
        Button btnFifteen = findViewById(R.id.btn15Questions);
        btnFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });
        Button btnTwentyFive = findViewById(R.id.btn25Questions);
        btnTwentyFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameScreen();
            }
        });*/
    }

    public void openGameScreen(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        Log.i("knapp", "StartGame pressed");
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