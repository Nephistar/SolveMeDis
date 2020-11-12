package com.example.solvemedis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.solvemedis.QuizItem;

public class Math_Easy extends AppCompatActivity {
    private Button back_button;
    private Button answer_button_1;
    private Button answer_button_2;
    private Button answer_button_3;
    private Button answer_button_4;
    private TextView questionBox;
    private QuizItem quizItem = new QuizItem("easy"); // generate first question
    // this has to be global so the button event can check for the answer
    private int score = 0;
    private int wrong = 0;
    private TextView score_screen;
    private TextView wrong_screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__easy);
        score_screen = (TextView) findViewById(R.id.score_easy);
        wrong_screen = (TextView) findViewById(R.id.wrong_easy);
        // below all Button events are made and populated
        back_button = (Button) findViewById(R.id.back_from_easy);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();

            }
        });
        answer_button_1 = (Button) findViewById(R.id.easy_answer_button_1);
        answer_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.easy_answer_button_1);

            }
        });
        answer_button_2 = (Button) findViewById(R.id.easy_answer_button_2);
        answer_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.easy_answer_button_2);

            }
        });
        answer_button_3 = (Button) findViewById(R.id.easy_answer_button_3);
        answer_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.easy_answer_button_3);

            }
        });
        answer_button_4 = (Button) findViewById(R.id.easy_answer_button_4);
        answer_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.easy_answer_button_4);

            }
        });
        questionBox = (TextView) findViewById(R.id.question_box_easy);
        fillQuestion(questionBox);
        answer_button_1.setText(quizItem.getOpt(0));
        answer_button_2.setText(quizItem.getOpt(1));
        answer_button_3.setText(quizItem.getOpt(2));
        answer_button_4.setText(quizItem.getOpt(3));
    }

    public void openHome(){ // change back to home/difficulty select screen
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
        quizItem = new QuizItem("easy");
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