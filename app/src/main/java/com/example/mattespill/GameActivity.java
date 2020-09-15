package com.example.mattespill;
// TODO: gå over alle strenger
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    TextView input;
    String[] questions;
    String[] answers;
    TextView txtQuestion;
    TextView txtQuestionNum;
    TextView txtWrong;
    TextView txtCorrect;

    int questionCount = 1;
    int answerdCount = 1;
    int countCorrect = 0;
    int countWrong = 0;

    ArrayList<Integer> fetchedQuestions = new ArrayList<>();
    ArrayList<QandA> gameQuestions = new ArrayList<>();
    ArrayList<QandA> allQuestions = new ArrayList<>();
    ArrayList<Results> resultList = new ArrayList<>();

    public Locale newLang;
    public Results result;

    // shared preferences
    public static final String SHARED_GAME_PREFS = "sharedGamePrefs";
    public static final String RESULT = "result";
    public static  final String COUNTER = "counter";

    // Fra shared prefs
    int game = 5; // fra preferences, men her satt til default hvis spiller ikke er i preferences først
    boolean lang;

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

        // Load data from shared prefrences
        loadData();

        // sjekke språk mot det som kommer fra sharedprefs
        if (lang){
            tysk();
        } else {
            norsk();
        }

        loadQandA();
        // getGameMode();
        setContentView(R.layout.activity_game);
        getGameQuestions(game);

        // Views and onClick
        input = findViewById(R.id.txtAnswer);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtQuestionNum.setText(String.format("%d / %d", questionCount, game));
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

        final Button btnConfirm = findViewById(R.id.btnNext);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    System.out.println(answerdCount + ".." + game);
                    if (!(answerdCount < game)) {
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
                        btnConfirm.setEnabled(false);

                        confirmClicked(0);
                        getResult();
                        saveResult();
                        // TODO: Kalle på dialogFragment
                    }
                    else {
                        confirmClicked(1);
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

    public void confirmClicked(int status) {
        // TODO: make this work
        // TODO: add method to check if answer is checked
        // variabler
        String inputVal = input.getText().toString();

        System.out.println("Svaret som er skrevet er = " + inputVal);

        if (inputVal.equals("")) {
            Toast.makeText(GameActivity.this, "Tast ditt svar", Toast.LENGTH_SHORT).show();
        }
        if (status == 0) {
            checkAnswer(inputVal);
            txtQuestion.setText(R.string.done);
            input.setText("");
        } else {
            checkAnswer(inputVal);
            txtQuestion.setText(gameQuestions.get(questionCount).getQuestion());
            answerdCount++;
            questionCount++;
            txtQuestionNum.setText(String.format("%d / %d", questionCount, game));
            input.setText(""); // setter input til tomt
        }
        Log.i("knapp", "Neste trykket"); // bort før levering
    }

    public void getResult(){
        String score = String.format("%d / %d", (questionCount - countWrong), game);
        String name = "Spill " + (resultList.size()+1);
        result = new Results(name, score);
        resultList.add(result);

        System.out.println("game: " + game + " correct: " + countCorrect +
                "\nresult: " + result.getScore() + " name: " + result.getName());
    }

    public void saveResult(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(resultList);
        editor.putString(RESULT, json);
        editor.apply();

        System.out.println("resultat: " + result);
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
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
                        finish();
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

    public void getGameQuestions(int game){
        // velge random spørsmål
        int count = 0;
        boolean check;
        // TODO: sammenkjør arrays her
        do {
            int randomIndex = (int) (Math.random()*game); // sjekk om stemmer senere
            check = fetchedQuestions.contains(randomIndex);
            if (!check){
                QandA randomTask = allQuestions.get(randomIndex);
                fetchedQuestions.add(randomIndex);
                gameQuestions.add(randomTask);
                count++;
            }
        } while (count < game);
    }

    public void checkAnswer(String inputVal){
        String answer = gameQuestions.get(questionCount-1).getAnswer();
        System.out.println(inputVal + " skal være lik " + answer);
        System.out.println("questioncount er: " + questionCount);
        try {
            if (inputVal.equals(answer)){
                updateCorrect();
            } else {
                updateWrong();
            }
        } catch (Exception e){
            Log.d("catch checkAnswer", "checkAnswer: " + e);
        }
    }

    public void updateCorrect(){
        txtCorrect = findViewById(R.id.txtCorrect);
        try {
            countCorrect++;
            txtCorrect.setText(String.valueOf(countCorrect));
        } catch (Exception e){
            Log.d("updateCorrect", "updateCorrect feilet " + e);
        }
    }

    public void updateWrong(){
        txtWrong = findViewById(R.id.txtWrong);
        try {
            countWrong++;
            txtWrong.setText(String.valueOf(countWrong));
        } catch (Exception e){
            Log.d("updateCorrect", "updateCorrect feilet " + e);
        }
    }

    public void Clear(View v){
        input.setText("");
        Log.d("Clear", "Clear button clicked");
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

    public void loadData(){
        Log.d("TAG", "loadData: ");
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        game = Integer.parseInt(sharedPreferences.getString("gameMode", "5"));
        lang = sharedPreferences.getBoolean("langSwitch", false);
        System.out.println("----:" + game + ":----");
        System.out.println("----:" + lang + ":----");

        SharedPreferences gameprefs = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String response = gameprefs.getString(RESULT, null);
        System.out.println("response: " + response);
        Type type = new TypeToken<ArrayList<Results>>() {}.getType();
        resultList = gson.fromJson(response, type);

        System.out.println("resultlist består av: ");
        for (Results result : resultList) {
            System.out.println(result.getName() + " sin score: " + result.getScore());
            System.out.println("---------------");
        }

    }

    // TODO: mulig mer må saves til instance, men ikke funnet helt ut av det enda

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtWrong = findViewById(R.id.txtWrong);

        String question = txtQuestion.getText().toString();
        String questionNum = txtQuestionNum.getText().toString();
        String correct = txtCorrect.getText().toString();
        String wrong = txtWrong.getText().toString();

        // TODO: husk å lagre questioncount!!!
        outState.putString("Question", question);
        outState.putString("QuestionNum", questionNum);
        outState.putString("Correct", correct);
        outState.putString("Wrong", wrong);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtWrong = findViewById(R.id.txtWrong);

        txtQuestion.setText(savedInstanceState.getString("Question"));
        txtQuestionNum.setText(savedInstanceState.getString("QuestionNum"));
        txtCorrect.setText(savedInstanceState.getString("Correct"));
        txtWrong.setText(savedInstanceState.getString("Wrong"));
    }
}