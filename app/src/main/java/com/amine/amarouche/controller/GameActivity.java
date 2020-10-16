/**
 * Created by <AMAROUCHE> </> on <09/10/2020>.
 **/
package com.amine.amarouche.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amine.amarouche.R;
import com.amine.amarouche.model.Question;
import com.amine.amarouche.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mGameText;
    private Button mReponse1;
    private Button mReponse2;
    private Button mReponse3;
    private Button mReponse4;
    
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate()");

        mQuestionBank = this.generateQuestions();

        mScore = 0;
        mNumberOfQuestions = 5;

        mEnableTouchEvents = true;

        // On récupère les éléments de la vus dans des variables !
        mGameText = (TextView) findViewById(R.id.activity_main_game_question);
        mReponse1 = (Button) findViewById(R.id.activity_main_reponse1_btn);
        mReponse2 = (Button) findViewById(R.id.activity_main_reponse2_btn);
        mReponse3 = (Button) findViewById(R.id.activity_main_reponse3_btn);
        mReponse4 = (Button) findViewById(R.id.activity_main_reponse4_btn);

        // On donne un Tag aux éléments pour savoir le quel à était séléctionner :D
        mReponse1.setTag(0);
        mReponse2.setTag(1);
        mReponse3.setTag(2);
        mReponse4.setTag(3);

        mReponse1.setOnClickListener(this);
        mReponse2.setOnClickListener(this);
        mReponse3.setOnClickListener(this);
        mReponse4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex  == mCurrentQuestion.getAnswserIndex()) {
            //Bonne réponse
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else {
            //Mauvaise réponse
            Toast.makeText(this,"Faux",Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

            if (--mNumberOfQuestions == 0) {
                //Fin du jeu !
                endGame();
            }
            else {
                mCurrentQuestion = mQuestionBank.getQuestion();
                displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bien jouer petit Scaraber")
                .setMessage("Voici ton score " + mScore + "/5" )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void displayQuestion(final Question question){
        mGameText.setText(question.getQuestion());
        mReponse1.setText(question.getChoiceList().get(0));
        mReponse2.setText(question.getChoiceList().get(1));
        mReponse3.setText(question.getChoiceList().get(2));
        mReponse4.setText(question.getChoiceList().get(3));
    }
    private QuestionBank generateQuestions() {
        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0);

        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1969"),
                3);

        Question question3 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "742"),
                3);

    return new QuestionBank(Arrays.asList(
                    question1,
                    question2,
                    question3));
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("GameActivity::onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("GameActivity::onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("GameActivity::onDestroy()");
    }
}