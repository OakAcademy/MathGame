package com.company.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resulta extends AppCompatActivity {

    TextView result;
    Button playAgain;
    Button exit;
    int scor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulta);

        result = findViewById(R.id.textViewResult);
        playAgain = findViewById(R.id.buttonAgain);
        exit = findViewById(R.id.buttonExit);

        Intent intent = getIntent();
        scor = intent.getIntExtra("scor", 0);
        String userScor = String.valueOf(scor);
        result.setText("Your Scor: " + userScor);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resulta.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
