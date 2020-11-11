package com.example.solvemedis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Math_Normal extends AppCompatActivity {
    private Button back_button;
    private Button answer_button_1;
    private Button answer_button_2;
    private Button answer_button_3;
    private Button answer_button_4;
    private TextView questionBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__normal);
        back_button = (Button) findViewById(R.id.back_from_normal);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();

            }
        });
        answer_button_1 = (Button) findViewById(R.id.normal_answer_button_1);
        answer_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();

            }
        });
        answer_button_2 = (Button) findViewById(R.id.normal_answer_button_2);
        answer_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();

            }
        });
        answer_button_3 = (Button) findViewById(R.id.normal_answer_button_3);
        answer_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();

            }
        });
        answer_button_4 = (Button) findViewById(R.id.normal_answer_button_4);
        answer_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();

            }
        });
    }

    public void openHome(){
        Intent intent_home = new Intent(this, MainActivity.class);
        startActivity(intent_home);
    }

    public void checkAnswer(){

    }
}