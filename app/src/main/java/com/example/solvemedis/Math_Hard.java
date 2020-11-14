package com.example.solvemedis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Math_Hard extends AppCompatActivity {
    // below are global variables to access them in different spots
    private Button back_button;  // button for back (back button of phone also works)
    private Button answer_button_1; // the 4 answer buttons
    private TextView questionBox;  // the place where the question is displayed
    private EditText number_input;  // field for the input of numbers
    private String input_number;  // the inputted number
    private QuizItem quizItem = new QuizItem("hard"); // generate first question
    // this has to be global so the button event can check for the answer
    private int score = 0;  // correct answers
    private int wrong = 0;  // false answers
    private TextView score_screen;  // to display correct answers
    private TextView wrong_screen;  // to display false answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__hard);
        // set all vars to the correct objects
        score_screen = (TextView) findViewById(R.id.score_hard);
        wrong_screen = (TextView) findViewById(R.id.wrong_hard);
        number_input = (EditText) findViewById(R.id.number_input_hard);
        back_button = (Button) findViewById(R.id.back_from_hard);

        // init buttons
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

    public void openHome(){  // back button method to get ... back to home
        Intent intent_home = new Intent(this, MainActivity.class);
        startActivity(intent_home);
    }

    public void checkAnswer(int button){  // checks the given answer
        String given_answer = number_input.getText().toString();  // converts the TextEdit type
        if(tryParse(quizItem.getAns(), 1) == (tryParse(given_answer, 0))){
            // correct answer clicked;
            score_up();
        }
        else{
            // wrong answer clicked
            wrong_up();
        }
        quizItem = new QuizItem("hard");  // override the old question with a new one
        fillQuestion(questionBox);
        number_input.setText("");  // empty the number box after the question
    }

    public void fillQuestion(TextView quest_field){ // put new question in box
        quest_field.setText(quizItem.getQuestion());
    }  // this is an extra method in case the needs to be done more later

    public int tryParse(String value, int defaultVal) {  // help to avoid parsing errors
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public void score_up(){  // increase score and change the displayed score
        score++;
        String text_score = "Points "+ score;
        score_screen.setText(text_score);
    }

    public void wrong_up(){  // increase negative score and change the displayed score
        wrong++;
        String text_score = "Wrong: "+ wrong;
        wrong_screen.setText(text_score);
    }
}