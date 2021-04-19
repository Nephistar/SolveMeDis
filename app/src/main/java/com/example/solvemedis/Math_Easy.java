package com.example.solvemedis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.solvemedis.QuizItem;
import com.example.solvemedis.AsyncRequester;

public class Math_Easy extends AppCompatActivity {
    // below are global variables to access them in different spots
    private Button back_button;  // button for back (back button of phone also works)
    private Button answer_button_1; // the 4 answer buttons
    private Button answer_button_2;
    private Button answer_button_3;
    private Button answer_button_4;
    private TextView questionBox;  // to display the question
    private QuizItem quizItem = new QuizItem("easy"); // generate first question
    // this has to be global so the button event can check for the answer
    private int score = 0;  // correct answers
    private int wrong = 0;  // false answers
    private TextView score_screen;  // to display correct answers
    private TextView wrong_screen;  // to display false answers
    private Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {  // on opening the new activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__easy);  // set how the activity looks
        score_screen = (TextView) findViewById(R.id.score_easy);
        wrong_screen = (TextView) findViewById(R.id.wrong_easy);  // init the scores
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
                AsyncRequester requester = new AsyncRequester(mContext); // Todo: REMOVE TEST HERE
                String response = requester.doInBackground("get", "highscore"); // Todo: REMOVE TEST HERE

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
        Button clicked_button = (Button) findViewById(button);  // which button was clicked
        if(answer.equals((String)clicked_button.getText())){  // check answer
            // correct answer clicked;
            score_up();
        }
        else{
            // wrong answer clicked
            wrong_up();
        }
        quizItem = new QuizItem("easy");  // override the old question
        fillQuestion(questionBox);  // fill question box again
        fillButtons();  // fill the new answers to the buttons
    }

    public void fillQuestion(TextView quest_field){  // sets the new question
        quest_field.setText(quizItem.getQuestion());
    }  // this is an extra method in case the needs to be done more later

    public void fillButtons(){  // set the new answers to the buttons
        answer_button_1.setText(quizItem.getOpt(0));
        answer_button_2.setText(quizItem.getOpt(1));
        answer_button_3.setText(quizItem.getOpt(2));
        answer_button_4.setText(quizItem.getOpt(3));
    }  // this is an extra method in case the needs to be done more later

    public void score_up(){  // increase score and change the displayed score
        score++;
        String text_score = "Right: "+ score;
        score_screen.setText(text_score);
    }

    public void wrong_up(){  // increase negative score and change the displayed score
        wrong++;
        String text_score = "Wrong: "+ wrong;
        wrong_screen.setText(text_score);
    }
}