package com.example.mattespill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    TextView input;
    String[] questions;
    String[] answers;
    TextView txtQuestion;
    TextView txtQuestionNum;
    int questionCountFive = 5;
    int questionCountTen = 10;
    int questionCountTwentyFive = 25;
    int questionCount = 1;
    int answerdCount = 1;
    int questionAmount = 5;
    ArrayList<Integer> fetchedQuestions = new ArrayList<>();
    ArrayList<QandA> gameQuestions = new ArrayList<>();
    ArrayList<QandA> allQuestions = new ArrayList<>();


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
        loadQandA();
        setContentView(R.layout.activity_game);
        getGameQuestions(questionAmount);

        // Views and onClick
        input = findViewById(R.id.txtAnswer);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtQuestionNum.setText(String.format("%d / %d", questionCount, questionAmount));
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestion.setText(gameQuestions.get(0).getQuestion());
        System.out.println("Arraylist: " + gameQuestions);


        Button btnExit = findViewById(R.id.btnCancel);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelGame();
            }
        });

        final Button btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneClicked();
            }
        });

        final Button btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoClicked();
            }
        });

        final Button btnThree = findViewById(R.id.btnThree);
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threeClicked();
            }
        });

        final Button btnFour = findViewById(R.id.btnFour);
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourClicked();
            }
        });

        final Button btnFive = findViewById(R.id.btnFive);
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiveClicked();
            }
        });

        final Button btnSix = findViewById(R.id.btnSix);
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sixClicked();
            }
        });

        final Button btnSeven = findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sevenClicked();
            }
        });

        final Button btnEight = findViewById(R.id.btnEight);
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eightClicked();
            }
        });

        final Button btnNine = findViewById(R.id.btnNine);
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nineClicked();
            }
        });

        final Button btnZero = findViewById(R.id.btnZero);
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
                    System.out.println(answerdCount + ".." + questionAmount);
                    if (!(answerdCount < questionAmount)) {
                        txtQuestion.setText(R.string.done);
                        input.setText("");
                        btnOne.setEnabled(false);
                        btnTwo.setEnabled(false);
                        btnThree.setEnabled(false);
                        btnFour.setEnabled(false);
                        btnFive.setEnabled(false);
                        btnSix.setEnabled(false);
                        btnSeven.setEnabled(false);
                        btnEight.setEnabled(false);
                        btnNine.setEnabled(false);
                        btnZero.setEnabled(false);
                    }
                    else{
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

        if (inputVal.equals("")) {
            Toast.makeText(GameActivity.this, "Tast ditt svar", Toast.LENGTH_SHORT).show();
        } else {
                //checkAnswer(inputVal); // TODO: Lag metode som endrer scoren basert på feil eller riktig svart
                txtQuestion.setText(gameQuestions.get(questionCount).getQuestion());
                answerdCount++;
                questionCount++;
                txtQuestionNum.setText(String.format("%d / %d", questionCount, questionAmount));
                input.setText(""); // setter input til tomt
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

    public void loadQandA () {
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);

        for (int i = 0; i < questions.length; i++ ) {
            QandA qanda = new QandA(questions[i], answers[i]);
            allQuestions.add(qanda);
        }

        for (QandA quandas : allQuestions) {
            System.out.println(quandas.question + " = " + quandas.answer);
            System.out.println("--------------");
        }

    }

    public void getGameQuestions(int questionAmount){
        // velge random spørsmål
        int count = 0;
        boolean check;
        // TODO: sammenkjør arrays her
        do {
            int randomIndex = (int) (Math.random()*questionAmount); // sjekk om stemmer senere
            check = fetchedQuestions.contains(randomIndex);
            if (!check){
                QandA randomTask = allQuestions.get(randomIndex);
                fetchedQuestions.add(randomIndex);
                gameQuestions.add(randomTask);
                count++;
            }
        } while (count < questionAmount);
    }


    public void checkAnswer(String inputVal){

    }
}