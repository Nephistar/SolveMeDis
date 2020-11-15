package com.example.solvemedis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Math_Normal extends AppCompatActivity {
    // below are global variables to access them in different spots
    private Button back_button;  // button for back (back button of phone also works)
    private Button answer_button_1; // the 4 answer buttons
    private Button answer_button_2;
    private Button answer_button_3;
    private Button answer_button_4;
    private TextView questionBox;
    private QuizItem quizItem = new QuizItem("normal"); // generate first question
    // this has to be global so the button event can check for the answer
    private int score = 0;  // correct answers
    private int wrong = 0;  // false answers
    private TextView score_screen;  // to display correct answers
    private TextView wrong_screen;  // to display false answers

    // -----------------------------------------------------------------------------
    // FOR DETAILED INFO ON ALL BELOW CHECK NORMAL WHICH HAS NEAR IDENTICAL METHODS!
    // -----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__normal);
        score_screen = (TextView) findViewById(R.id.score_normal);
        wrong_screen = (TextView) findViewById(R.id.wrong_normal);
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
                checkAnswer(R.id.normal_answer_button_1);

            }
        });
        answer_button_2 = (Button) findViewById(R.id.normal_answer_button_2);
        answer_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.normal_answer_button_2);

            }
        });
        answer_button_3 = (Button) findViewById(R.id.normal_answer_button_3);
        answer_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.normal_answer_button_3);

            }
        });
        answer_button_4 = (Button) findViewById(R.id.normal_answer_button_4);
        answer_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.normal_answer_button_4);

            }
        });
        questionBox = (TextView) findViewById(R.id.question_box_normal);
        fillQuestion(questionBox);
        answer_button_1.setText(quizItem.getOpt(0));
        answer_button_2.setText(quizItem.getOpt(1));
        answer_button_3.setText(quizItem.getOpt(2));
        answer_button_4.setText(quizItem.getOpt(3));
    }

    public void openHome(){
        Intent intent_home = new Intent(this, MainActivity.class);
        startActivity(intent_home);
    }

    public void checkAnswer(int button){
        String answer = quizItem.getAns();
        Button clicked_button = (Button) findViewById(button);
        if(answer.equals((String)clicked_button.getText())){
            // correct answer clicked;
            score_up();
        }
        else{
            // wrong answer clicked
            wrong_up();
        }
        quizItem = new QuizItem("normal");
        fillQuestion(questionBox);
        fillButtons();
    }

    public void fillQuestion(TextView quest_field){
        quest_field.setText(quizItem.getQuestion());
    }

    public void fillButtons(){
        answer_button_1.setText(quizItem.getOpt(0));
        answer_button_2.setText(quizItem.getOpt(1));
        answer_button_3.setText(quizItem.getOpt(2));
        answer_button_4.setText(quizItem.getOpt(3));
    }

    public void score_up(){
        score++;
        String text_score = "Right: "+ score;
        score_screen.setText(text_score);
    }

    public void wrong_up(){
        wrong++;
        String text_score = "Wrong: "+ wrong;
        wrong_screen.setText(text_score);
    }
}