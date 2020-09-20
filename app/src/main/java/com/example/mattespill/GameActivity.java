package com.example.mattespill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class GameActivity extends AppCompatActivity {
    TextView input;
    String[] questions;
    String[] answers;
    TextView txtQuestion;
    TextView txtQuestionNum;
    TextView txtWrong;
    TextView txtCorrect;

    int questionCount = 1;
    int countCorrect = 0;
    int countWrong = 0;
    boolean gameOver = false;

    ArrayList<Integer> fetchedQuestions = new ArrayList<>();
    ArrayList<QandA> gameQuestions = new ArrayList<>();
    ArrayList<QandA> allQuestions = new ArrayList<>();
    ArrayList<Results> resultList = new ArrayList<>();

    public Locale newLang;
    public Results result;

    // shared preferences
    public static final String SHARED_GAME_PREFS = "sharedGamePrefs";
    public static final String RESULT = "result";

    // Fra shared prefs
    int game = 5;
    String lang;
    String actland;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load data from shared prefrences
        loadData();

        // sjekke språk mot det som kommer fra sharedprefs
        try {
            setLang(lang);
        } catch (Exception e){
            Log.d("onCreate", "onCreate lang: " + e);
        }
        actland = lang;
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener =
                (sharedPreferences, key) -> {
                    try {
                        if (key.equals("lang_pref"))
                        {
                            setLang(lang);
                            getFragmentManager().beginTransaction().replace(android.R.id.content,
                                    new PreferencesActivity.PrefsFragment()).commit();
                        }
                    } catch (Exception e){
                        Log.d("onCreate", "onSharedPreferenceChanged: " + e);
                    }
                };
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);

        loadQandA();
        setContentView(R.layout.activity_game);
        getGameQuestions(game);

        // Views and onClick
        input = findViewById(R.id.txtAnswer);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtQuestionNum.setText(String.format("%d / %d", questionCount, game));
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestion.setText(gameQuestions.get(0).getQuestion());

        setButtons();
    }

    public void setButtons(){
        Button btnExit = findViewById(R.id.btnCancel);
        btnExit.setOnClickListener(v -> cancelGame());

        final Button btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String one = "1";
            if (!prevText.equals("")){
                String newText = prevText + one;
                input.setText(newText);
            } else {
                input.setText(one);
            }
        });

        final Button btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String two = "2";
            if (!prevText.equals("")){
                String newText = prevText + two;
                input.setText(newText);
            } else{
                input.setText(two);
            }
        });

        final Button btnThree = findViewById(R.id.btnThree);
        btnThree.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String three = "3";
            if (!prevText.equals("")){
                String newText = prevText + three;
                input.setText(newText);
            } else{
                input.setText(three);
            }
        });

        final Button btnFour = findViewById(R.id.btnFour);
        btnFour.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String four = "4";
            if (!prevText.equals("")){
                String newText = prevText + four;
                input.setText(newText);
            } else{
                input.setText(four);
            }
        });

        final Button btnFive = findViewById(R.id.btnFive);
        btnFive.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String five = "5";
            if (!prevText.equals("")){
                String newText = prevText + five;
                input.setText(newText);
            } else{
                input.setText(five);
            }
        });

        final Button btnSix = findViewById(R.id.btnSix);
        btnSix.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String six = "6";
            if (!prevText.equals("")){
                String newText = prevText + six;
                input.setText(newText);
            } else{
                input.setText(six);
            }
        });

        final Button btnSeven = findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String seven = "7";
            if (!prevText.equals("")){
                String newText = prevText + seven;
                input.setText(newText);
            } else{
                input.setText(seven);
            }
        });

        final Button btnEight = findViewById(R.id.btnEight);
        btnEight.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String eight = "8";
            if (!prevText.equals("")){
                String newText = prevText + eight;
                input.setText(newText);
            } else{
                input.setText(eight);
            }
        });

        final Button btnNine = findViewById(R.id.btnNine);
        btnNine.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String nine = "9";
            if (!prevText.equals("")){
                String newText = prevText + nine;
                input.setText(newText);
            } else{
                input.setText(nine);
            }
        });

        final Button btnZero = findViewById(R.id.btnZero);
        btnZero.setOnClickListener(v -> {
            String prevText = input.getText().toString();
            String zero = "0";
            if (!prevText.equals("")){
                String newText = prevText + zero;
                input.setText(newText);
            } else{
                input.setText(zero);
            }
        });

        final Button btnConfirm = findViewById(R.id.btnNext);
        btnConfirm.setOnClickListener(v -> {
            String inputVal = input.getText().toString();

            if (inputVal.equals("")) {
                Toast.makeText(GameActivity.this, "Tast ditt svar", Toast.LENGTH_SHORT).show();
            } else {
                if (questionCount < game) {
                    checkAnswer(inputVal);
                    txtQuestion.setText(gameQuestions.get(questionCount).getQuestion());
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
                    txtQuestion.setText(R.string.done);

                    checkAnswer(inputVal);
                    getResult();
                    saveResult();
                    doneDialog();
                    gameOver = true;
                }
            }
        });
    }

    public void getResult(){
        String score = String.format("%d / %d", (questionCount - countWrong), game);
        String gameName = getResources().getString(R.string.game);
        String name = gameName + (resultList.size()+1);
        result = new Results(name, score);
        resultList.add(result);
    }

    public void saveResult(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(resultList);
        editor.putString(RESULT, json);
        editor.apply();
    }

    public void cancelGame(){
        String txtDialog = getResources().getString(R.string.dialog);
        String txtPositive = getResources().getString(R.string.positive);
        String txtNegative = getResources().getString(R.string.negative);

        final Intent intent = new Intent(this, MainActivity.class);

        DialogInterface.OnClickListener dialog = (dialog1, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    startActivity(intent);
                    finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
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
    }

    public void getGameQuestions(int game){
        // velge random spørsmål
        int count = 0;
        boolean check;
        do {
            int randomIndex = (int) (Math.random()*25);
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
            Log.d("updateWrong", "updateWrong feilet " + e);
        }
    }

    public void Clear(View v){
        input.setText("");
    }

    public void doneDialog(){
        final Intent main = new Intent(this, MainActivity.class);
        final Intent stats = new Intent(this, StatisticsActivity.class);
        final Intent restart = new Intent(this, GameActivity.class);
        String txtPositive = getResources().getString(R.string.btnCancel);
        String txtNegative = getResources().getString(R.string.stats);
        String txtRestart = getResources().getString(R.string.restart);
        String txtTitle = getResources().getString(R.string.results);

        DialogInterface.OnClickListener dialog = (dialog1, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    // avslutt trykket
                    startActivity(main);
                    finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    // stats trykket
                    startActivity(stats);
                    finish();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    // omstart trykket
                    startActivity(restart);
                    finish();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String ut = "";
        for (int i = 0; i< gameQuestions.size(); i++){
            ut += gameQuestions.get(i).question + " = " + gameQuestions.get(i).answer + "\n";
        }
        builder.setMessage(ut);
        builder.setPositiveButton(txtPositive, dialog)
                .setNegativeButton(txtNegative,dialog)
                .setNeutralButton(txtRestart, dialog);
        builder.setTitle(txtTitle);
        builder.show();
    }

    public void setLang(String landskode){
        newLang = Locale.forLanguageTag(landskode);

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

    public void loadData(){
        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(this);
            game = Integer.parseInt(sharedPreferences.getString("gameMode", "5"));
            lang = sharedPreferences.getString("lang_pref", "no");

            loadArrayList();
        } catch (Exception e) {
            Log.d("loadData", "loadData: " + e);
        }
    }

    public void loadArrayList(){
        try {
            SharedPreferences gameprefs = getSharedPreferences(SHARED_GAME_PREFS, MODE_PRIVATE);
            Gson gson = new Gson();
            String response = gameprefs.getString(RESULT, null);
            Type type = new TypeToken<ArrayList<Results>>() {}.getType();
            resultList = gson.fromJson(response, type);
            if (resultList == null){
                resultList = new ArrayList<>();
            }
        } catch (Exception e) {
            Log.d("loadArrayList", "loadArrayList: " + e);
            resultList = new ArrayList<>();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtWrong = findViewById(R.id.txtWrong);
        input = findViewById(R.id.txtAnswer);

        String question = txtQuestion.getText().toString();
        String questionNum = txtQuestionNum.getText().toString();
        String correct = txtCorrect.getText().toString();
        String wrong = txtWrong.getText().toString();
        String answer = input.getText().toString();

        outState.putString("Question", question);
        outState.putString("QuestionNum", questionNum);
        outState.putString("Correct", correct);
        outState.putString("Wrong", wrong);
        outState.putString("Answer", answer);
        outState.putInt("QuestionCount", questionCount);
        outState.putInt("CountCorrect", countCorrect);
        outState.putInt("CountWrong", countWrong);
        outState.putParcelableArrayList("GameQuestions", gameQuestions);
        outState.putBoolean("GameOver", gameOver);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtWrong = findViewById(R.id.txtWrong);
        input = findViewById(R.id.txtAnswer);

        txtQuestion.setText(savedInstanceState.getString("Question"));
        txtQuestionNum.setText(savedInstanceState.getString("QuestionNum"));
        txtCorrect.setText(savedInstanceState.getString("Correct"));
        txtWrong.setText(savedInstanceState.getString("Wrong"));
        input.setText(savedInstanceState.getString("Answer"));
        questionCount = savedInstanceState.getInt("QuestionCount");
        countCorrect = savedInstanceState.getInt("CountCorrect");
        countWrong = savedInstanceState.getInt("CountWrong");
        gameQuestions = savedInstanceState.getParcelableArrayList("GameQuestions");
        gameOver = savedInstanceState.getBoolean("GameOver");
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (gameOver){
            doneDialog();
        }
    }
}