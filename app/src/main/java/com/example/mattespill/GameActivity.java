package com.example.mattespill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView input;
    String[] questions;
    String[] answers;
    TextView txtQuestion;
    TextView txtQuestionNum;
    int number = 5;
    int counter = 0;

    public void Clear(View v){
        input.setText("");
        Log.d("Clear", "Clear button clicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Theme
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.LightTheme);
        }

        // load arrays
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);

        // velge random spørsmål
        int randomIndex = new Random().nextInt(number);
        String randomTask = questions[randomIndex];

        setContentView(R.layout.activity_game);

        // Views and onClick
        input = findViewById(R.id.txtAnswer);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtQuestionNum.setText(String.format("%d / %d", counter, number));
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestion.setText(randomTask);

        Button btnExit = findViewById(R.id.btnCancel);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelGame();
            }
        });

        Button btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneClicked();
            }
        });

        Button btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoClicked();
            }
        });

        Button btnThree = findViewById(R.id.btnThree);
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threeClicked();
            }
        });

        Button btnFour = findViewById(R.id.btnFour);
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourClicked();
            }
        });

        Button btnFive = findViewById(R.id.btnFive);
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiveClicked();
            }
        });

        Button btnSix = findViewById(R.id.btnSix);
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sixClicked();
            }
        });

        Button btnSeven = findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sevenClicked();
            }
        });

        Button btnEight = findViewById(R.id.btnEight);
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eightClicked();
            }
        });

        Button btnNine = findViewById(R.id.btnNine);
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nineClicked();
            }
        });

        Button btnZero = findViewById(R.id.btnZero);
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeroClicked();
            }
        });

        Button btnConfirm = findViewById(R.id.btnNext);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (input.getText().toString().equals("")) {
                        Toast.makeText(GameActivity.this, "Tast ditt svar", Toast.LENGTH_SHORT).show();
                    } else {
                        confirmClicked();
                    }
                } catch (Exception e){
                    Log.e("error", "Feil i btn confirm" + e);
                }
            }
        });
    }

    public void oneClicked(){
        // code here
        String prevText = input.getText().toString();
        String one = "1";
        if (!prevText.equals("")){
            String newText = prevText + one;
            input.setText(newText);
        } else{
            input.setText(one);
        }
        Log.i("knapp", "1 trykket"); // bort før levering
    }

    public void twoClicked(){
        // code here
        String prevText = input.getText().toString();
        String two = "2";
        if (!prevText.equals("")){
            String newText = prevText + two;
            input.setText(newText);
        } else{
            input.setText(two);
        }
        Log.i("knapp", "2 trykket"); // bort før levering
    }

    public void threeClicked(){
        // code here
        String prevText = input.getText().toString();
        String three = "3";
        if (!prevText.equals("")){
            String newText = prevText + three;
            input.setText(newText);
        } else{
            input.setText(three);
        }
        Log.i("knapp", "3 trykket"); // bort før levering
    }

    public void fourClicked(){
        // code here
        String prevText = input.getText().toString();
        String four = "4";
        if (!prevText.equals("")){
            String newText = prevText + four;
            input.setText(newText);
        } else{
            input.setText(four);
        }
        Log.i("knapp", "4 trykket"); // bort før levering
    }

    public void fiveClicked(){
        // code here
        String prevText = input.getText().toString();
        String five = "5";
        if (!prevText.equals("")){
            String newText = prevText + five;
            input.setText(newText);
        } else{
            input.setText(five);
        }
        Log.i("knapp", "5 trykket"); // bort før levering
    }

    public void sixClicked(){
        // code here
        String prevText = input.getText().toString();
        String six = "6";
        if (!prevText.equals("")){
            String newText = prevText + six;
            input.setText(newText);
        } else{
            input.setText(six);
        }
        Log.i("knapp", "6 trykket"); // bort før levering
    }

    public void sevenClicked(){
        // code here
        String prevText = input.getText().toString();
        String seven = "7";
        if (!prevText.equals("")){
            String newText = prevText + seven;
            input.setText(newText);
        } else{
            input.setText(seven);
        }
        Log.i("knapp", "7 trykket"); // bort før levering
    }

    public void eightClicked(){
        // code here
        String prevText = input.getText().toString();
        String eight = "8";
        if (!prevText.equals("")){
            String newText = prevText + eight;
            input.setText(newText);
        } else{
            input.setText(eight);
        }
        Log.i("knapp", "8 trykket"); // bort før levering
    }

    public void nineClicked(){
        // code here
        String prevText = input.getText().toString();
        String nine = "9";
        if (!prevText.equals("")){
            String newText = prevText + nine;
            input.setText(newText);
        } else{
            input.setText(nine);
        }
        Log.i("knapp", "9 trykket"); // bort før levering
    }

    public void zeroClicked() {
        // code here
        String prevText = input.getText().toString();
        String zero = "0";
        if (!prevText.equals("")){
            String newText = prevText + zero;
            input.setText(newText);
        } else{
            input.setText(zero);
        }
        Log.i("knapp", "0 trykket"); // bort før levering
    }

    public void confirmClicked() {
        // TODO: make this work
        // TODO: add method to check if answer is checked
        // variabler
        String inputVal = input.getText().toString();
        System.out.println("Svaret som er skrevet er = " + inputVal);

        int randomIndex = new Random().nextInt(number);
        String randomTask = questions[randomIndex];
        System.out.println("RandomInt = " + randomIndex);
        int i;
        int f;


        System.out.println("her kommer lengden av arrayet: " + questions.length);
        for (i = 0; i<number; i++) {
            counter++;
            System.out.println(counter);
            String quest = questions[i];
            txtQuestionNum.setText(counter + " / " + number);


        }
            if (counter >= number) {
                txtQuestion.setText(randomTask);
                input.setText("");
                System.out.println("her kommer quest: " + i);
            }
            else{
                txtQuestion.setText("Du er ferdig");
            }
        Log.i("knapp", "Neste trykket"); // bort før levering
    }

    public void cancelGame(){
        String txtDialog = getResources().getString(R.string.dialog);
        String txtPositive = getResources().getString(R.string.positive);
        String txtNegative = getResources().getString(R.string.negative);

        final Intent intent = new Intent(this, MainActivity.class);

        DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        // yes trykket
                        startActivity(intent);
                        Log.i("knapp", "ja trykket"); // bort før levering
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        // no trykket
                        Log.i("knapp", "nei trykket"); // bort før levering

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(txtDialog).setPositiveButton(txtPositive, dialog)
                .setNegativeButton(txtNegative,dialog).show();

    }
}