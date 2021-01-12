package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.braintrainer.R.id.timerTextView;

public class MainActivity extends AppCompatActivity {
    TextView sumTextView,resultTextView,scoreTextView,timerTextView;
    Button goButton;
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    Button button0,button1,button2,button3,playAgainButton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
    }
    public void playAgain(View view) {
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(5100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000+" s"));
                playAgainButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done !!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();

    }
    public void newQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b =rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);

            }
            else{
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);

            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        goButton=(Button)findViewById(R.id.goButton);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playAgain(findViewById(R.id.timerTextView));





    }



}