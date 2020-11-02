package com.example.solvemedis;

import java.util.ArrayList;
import java.util.List;

public class GenerateQ {
    public class QuizItem(String diff){
        String question = "";
        String answer = "";
        List<String> error = new ArrayList<>();
        question = gen_Q(diff);
        answer = gen_A(question);
        error = gen_ERR(question);
        QuizItem t = new QuizItem(question, answer, error);
        return t;
    }
}
