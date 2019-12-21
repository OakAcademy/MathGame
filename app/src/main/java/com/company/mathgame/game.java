package com.company.mathgame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class game extends AppCompatActivity {

    TextView scor;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button nextQuestion;

    Random random = new Random();
    int number1;
    int number2;

    int userAnswer;
    int realAnswer;
    int userScor = 0;
    int userLife = 3;

    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scor = findViewById(R.id.textViewscor);
        life = findViewById(R.id.textViewlife);
        time = findViewById(R.id.textViewTime);

        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);

        ok = findViewById(R.id.buttonOk);
        nextQuestion = findViewById(R.id.buttonNext);

        gameContinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               userAnswer = Integer.valueOf(answer.getText().toString());
               pauseTimer();
               if(userAnswer == realAnswer)
               {
                   userScor = userScor + 10;
                   question.setText("Congratulations. Your Answer is True.");
                   scor.setText("" + userScor);

               }
               else
               {
                   userLife = userLife - 1;
                   question.setText("Sorry! Your Answer is Wrong!");
                   life.setText("" + userLife);

               }


            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer.setText("");

                resetTimer();

                if(userLife <= 0)
                {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(game.this, resulta.class);
                    intent.putExtra("scor", userScor);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    gameContinue();
                }

            }
        });
    }

    public void gameContinue()
    {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        realAnswer = number1 + number2;

        question.setText(number1 + " + " + number2);
        startTimer();

    }

    public void startTimer()
    {
        timer = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {

                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife - 1;
                question.setText("Sorry! Time is Up!");

            }
        }.start();

        timer_running = true;
    }

    public  void updateText()
    {
        int second = (int)(time_left_in_milis /1000)%60;
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time.setText(time_left);
    }

    public  void pauseTimer()
    {
        timer.cancel();
        timer_running = false;

    }
    public void resetTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }
}
