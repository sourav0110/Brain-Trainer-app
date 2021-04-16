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

public class MainActivity extends AppCompatActivity {
   Button startButton;
   ArrayList<Integer> answers=new ArrayList<Integer>();
   int locationOfCorrectAnswer;
   int score=0;
   int numberOfQuestion=0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    Boolean end=false;
    TextView sumTextView;
   TextView resultTextView;
   TextView pointsTextView;
   TextView timerTextView;
   TextView remarksTextView;
   public void playAgain(View view){
       score=0;
       end=false;
       numberOfQuestion=0;
       timerTextView.setText("30s");
       playAgainButton.setVisibility(View.INVISIBLE);
       resultTextView.setText("");
       remarksTextView.setText("");
       pointsTextView.setText("00/00");
       generateQuestion();
       new CountDownTimer(30100,1000){

           @Override
           public void onTick(long millisUntilFinished) {
               timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
           }

           @Override
           public void onFinish() {
               end=true;
               timerTextView.setText("0s");
               resultTextView.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
               if((numberOfQuestion-score)<=3)
                   remarksTextView.setText("Excellent! ");
               else if((numberOfQuestion-score)<=6)
                   remarksTextView.setText("Good!");
               else if((numberOfQuestion-score)<=12)
                   remarksTextView.setText("Average!");
               else
                   remarksTextView.setText("Poor!");
               playAgainButton.setVisibility(View.VISIBLE);


           }
       }.start();


   }
   public void generateQuestion()
   {
       Random rand=new Random();
       int a=rand.nextInt(50);
       int b=rand.nextInt(50);
       sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
       locationOfCorrectAnswer=rand.nextInt(4);
       int incorrectAnswer;
       answers.clear();
       for(int i=0;i<=4;i++){
           if(i==locationOfCorrectAnswer){
               answers.add(a+b);
           }
           else{
               incorrectAnswer=rand.nextInt(110);
               while(incorrectAnswer==a+b){
                   incorrectAnswer=rand.nextInt(110);
               }
               answers.add(incorrectAnswer);
           }
       }
       button1.setText(Integer.toString(answers.get(0)));
       button2.setText(Integer.toString(answers.get(1)));
       button3.setText(Integer.toString(answers.get(2)));
       button4.setText(Integer.toString(answers.get(3)));
   }
    public void chooseAnswer(View view) {
       if(end==false) {
           Log.i("tag:", (String) view.getTag());
           if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
               score++;

               resultTextView.setText("Correct!");
               Log.i("Correct!", (String) view.getTag());
           } else {

               resultTextView.setText("Wrong!");
           }
           numberOfQuestion++;
           pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

           generateQuestion();
       }
    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        remarksTextView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startButton);
         sumTextView=(TextView)findViewById(R.id.sumTextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        pointsTextView=(TextView)findViewById(R.id.pointsTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        remarksTextView=(TextView)findViewById(R.id.remarkTextView);
        playAgainButton=(Button) findViewById(R.id.playAgainButton);

         button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
         button3=(Button)findViewById(R.id.button3);
       button4=(Button)findViewById(R.id.button4);

       playAgain(findViewById(R.id.playAgainButton));

    }


}