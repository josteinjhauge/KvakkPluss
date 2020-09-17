package com.example.mattespill;
// TODO: gå over alle strenger
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

    // til dialogboks
    ArrayList<QandA> failedQuestions = new ArrayList<>();

    ArrayList<Results> resultList = new ArrayList<>();

    public Locale newLang;
    public Results result;

    // shared preferences
    public static final String SHARED_GAME_PREFS = "sharedGamePrefs";
    public static final String RESULT = "result";

    // Fra shared prefs
    int game = 5; // fra preferences, men her satt til default hvis spiller ikke er i preferences først
    boolean lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load data from shared prefrences
        loadData();

        // sjekke språk mot det som kommer fra sharedprefs
        if (lang){
            tysk();
        } else {
            norsk();
        }

        loadQandA();
        setContentView(R.layout.activity_game);
        getGameQuestions(game);

        // Views and onClick
        input = findViewById(R.id.txtAnswer);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtQuestionNum.setText(String.format("%d / %d", questionCount, game));
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestion.setText(gameQuestions.get(0).getQuestion());
        System.out.println("Arraylist: " + gameQuestions);

        setButtons();
    }

    public void setButtons(){
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
                String prevText = input.getText().toString();
                String one = "1";
                if (!prevText.equals("")){
                    String newText = prevText + one;
                    input.setText(newText);
                } else {
                    input.setText(one);
                }
            }
        });

        final Button btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String two = "2";
                if (!prevText.equals("")){
                    String newText = prevText + two;
                    input.setText(newText);
                } else{
                    input.setText(two);
                }
            }
        });

        final Button btnThree = findViewById(R.id.btnThree);
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String three = "3";
                if (!prevText.equals("")){
                    String newText = prevText + three;
                    input.setText(newText);
                } else{
                    input.setText(three);
                }
            }
        });

        final Button btnFour = findViewById(R.id.btnFour);
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String four = "4";
                if (!prevText.equals("")){
                    String newText = prevText + four;
                    input.setText(newText);
                } else{
                    input.setText(four);
                }
            }
        });

        final Button btnFive = findViewById(R.id.btnFive);
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String five = "5";
                if (!prevText.equals("")){
                    String newText = prevText + five;
                    input.setText(newText);
                } else{
                    input.setText(five);
                }
            }
        });

        final Button btnSix = findViewById(R.id.btnSix);
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String six = "6";
                if (!prevText.equals("")){
                    String newText = prevText + six;
                    input.setText(newText);
                } else{
                    input.setText(six);
                }
            }
        });

        final Button btnSeven = findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String seven = "7";
                if (!prevText.equals("")){
                    String newText = prevText + seven;
                    input.setText(newText);
                } else{
                    input.setText(seven);
                }
            }
        });

        final Button btnEight = findViewById(R.id.btnEight);
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String eight = "8";
                if (!prevText.equals("")){
                    String newText = prevText + eight;
                    input.setText(newText);
                } else{
                    input.setText(eight);
                }
            }
        });

        final Button btnNine = findViewById(R.id.btnNine);
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String nine = "9";
                if (!prevText.equals("")){
                    String newText = prevText + nine;
                    input.setText(newText);
                } else{
                    input.setText(nine);
                }
            }
        });

        final Button btnZero = findViewById(R.id.btnZero);
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = input.getText().toString();
                String zero = "0";
                if (!prevText.equals("")){
                    String newText = prevText + zero;
                    input.setText(newText);
                } else{
                    input.setText(zero);
                }
            }
        });

        final Button btnConfirm = findViewById(R.id.btnNext);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputVal = input.getText().toString();

                if (inputVal.equals("")) {
                    // TODO: En bedre måte å gi tilbakemelding her eller??
                    Toast.makeText(GameActivity.this, "Tast ditt svar", Toast.LENGTH_SHORT).show();
                } else {
                    if (answerdCount < game) {
                        checkAnswer(inputVal);
                        txtQuestion.setText(gameQuestions.get(questionCount).getQuestion());
                        answerdCount++;
                        questionCount++;
                        txtQuestionNum.setText(String.format("%d / %d", questionCount, game));
                        input.setText("");
                    } else {
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
                        input.setText("");
                        txtQuestion.setText(R.string.done); // TODO: Lage dialogboks istede

                        checkAnswer(inputVal);
                        getResult();
                        saveResult();
                        doneDialog();
                    }
                }
            }
        });
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
                        Log.i("knapp", "ja trykket"); // TODO: bort før levering
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        // no trykket
                        Log.i("knapp", "nei trykket"); // TODO: bort før levering
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
        do {
            int randomIndex = (int) (Math.random()*25); // sjekk om stemmer senere
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
                // TODO: lage arraylist her av gamequestions.get(questioncount-1)
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

    public void doneDialog(){
        final Intent main = new Intent(this, MainActivity.class);
        final Intent stats = new Intent(this, StatisticsActivity.class);
        String txtPositive = getResources().getString(R.string.btnCancel);
        String txtNegative = getResources().getString(R.string.stats);
        String txtTitle = getResources().getString(R.string.results);

        ArrayAdapter<QandA> dataAdapter = new ArrayAdapter<>(this, R.layout.results_dialog, gameQuestions);

        DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        // yes trykket
                        startActivity(main);
                        finish();
                        Log.i("knapp", "ja trykket"); // TODO: bort før levering
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        // no trykket
                        startActivity(stats);
                        finish();
                        Log.i("knapp", "nei trykket"); // TODO: bort før levering
                        break;
                }
            }
        };



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String ut = "";
        for (int i = 0; i< gameQuestions.size(); i++){
            ut += gameQuestions.get(i).question + " = " + gameQuestions.get(i).answer + "\n";
        }
        builder.setMessage(ut);
        builder.setPositiveButton(txtPositive, dialog)
                .setNegativeButton(txtNegative,dialog);
        builder.setTitle(txtTitle);
        builder.show();
       /* DialogFragment dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(), "Avslutt");
       /*DialogFragment dialog = new Dialog();
       dialog.show(getSupportFragmentManager(),"Done");*/
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

        loadArrayList();
    }

    public void loadArrayList(){
        try {
            SharedPreferences gameprefs = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
            Gson gson = new Gson();
            String response = gameprefs.getString(RESULT, null);
            System.out.println("response: " + response);
            Type type = new TypeToken<ArrayList<Results>>() {}.getType();
            resultList = gson.fromJson(response, type);
            System.out.println("ResultList: " + resultList + " Response: " + response + "\n"
                    + "Type: " + type);
            if (resultList == null){
                resultList = new ArrayList<>();
            }
        } catch (Exception e) {
            Log.d("CATCH", "loadArrayList: " + e);
            resultList = new ArrayList<>();
        }

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
        outState.putInt("QuestionCount", questionCount);
        outState.putInt("AnswerdCount", answerdCount);
        outState.putInt("CountCorrect", countCorrect);
        outState.putInt("CountWrong", countWrong);
        outState.putParcelableArrayList("GameQuestions", gameQuestions);

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
        questionCount = savedInstanceState.getInt("QuestionCount");
        answerdCount = savedInstanceState.getInt("AnsweredCount");
        countCorrect = savedInstanceState.getInt("CountCorrect");
        countWrong = savedInstanceState.getInt("CountWrong");
        gameQuestions = savedInstanceState.getParcelableArrayList("GameQuestions");
    }
}