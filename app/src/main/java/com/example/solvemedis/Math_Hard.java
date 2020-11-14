package com.example.solvemedis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Math_Hard extends AppCompatActivity {
    private Button back_button;
    private Button answer_button_1;
    private TextView questionBox;
    private EditText number_input;
    private String input_number;
    private QuizItem quizItem = new QuizItem("hard"); // generate first question
    // this has to be global so the button event can check for the answer
    private int score = 0;
    private int wrong = 0;
    private TextView score_screen;
    private TextView wrong_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__hard);
        score_screen = (TextView) findViewById(R.id.score_hard);
        wrong_screen = (TextView) findViewById(R.id.wrong_hard);
        number_input = (EditText) findViewById(R.id.number_input_hard);
        back_button = (Button) findViewById(R.id.back_from_hard);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();

            }
        });
        answer_button_1 = (Button) findViewById(R.id.hard_answer_button_1);
        answer_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.hard_answer_button_1);
                number_input.setText("");
            }
        });
        questionBox = (TextView) findViewById(R.id.question_box_hard);
        fillQuestion(questionBox);
    }

    public void openHome(){
        Intent intent_home = new Intent(this, MainActivity.class);
        startActivity(intent_home);
    }

    public void checkAnswer(int button){
        String given_answer = number_input.getText().toString();
        if(tryParse(quizItem.getAns(), 1) == (tryParse(given_answer, 0))){
            // correct answer clicked;
            score_up();
        }
        else{
            // wrong answer clicked
            wrong_up();
        }
        quizItem = new QuizItem("hard");
        fillQuestion(questionBox);
    }

    public void fillQuestion(TextView quest_field){
        quest_field.setText(quizItem.getQuestion());
    }

    public int tryParse(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public void score_up(){
        score++;
        String text_score = "Points "+ score;
        score_screen.setText(text_score);
    }

    public void wrong_up(){
        wrong++;
        String text_score = "Wrong: "+ wrong;
        wrong_screen.setText(text_score);
    }
}