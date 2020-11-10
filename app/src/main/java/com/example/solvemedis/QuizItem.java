package com.example.solvemedis;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class QuizItem {
    private String question;
    private String answer;
    private List<String> error;
    private List<Integer> options;
    private int errorCount;

    public QuizItem(String diff) {
        this.error = new ArrayList<>(6);
        genQ(diff);
        this.errorCount = 3;
        this.options = new ArrayList<>();
        this.giveOptions();
    }

    private void genQ(String diff) {
        if (diff == "easy") {
            int sumBound = 100;
            Sum sum = new Sum(sumBound);
            this.question = sum.question;
            this.answer = sum.answer;
            this.error = sum.error;
        }
    }

    private void giveOptions() {
        this.options.add(-1);
        for (int i = 0; i < this.errorCount; i++)
            this.options.add(i);
        Collections.shuffle(this.options);
    }

    void printConsole() {
        String out = "";
        out += this.question;
        for (int i: this.options) {
            out += "[ ] ";
            if (i == -1)
                out += this.answer;
            else
                out += this.error.get(i);
            out += "\n";
        }
        System.out.println(out);
    }

    public String getQuestion() {
        return this.question;
    }

    public String getOpt(int index) {
        int optCode = this.options.get(index);
        if (optCode == -1)
            return this.answer;
        else
            return this.error.get(optCode);
    }

    public Boolean isRightAns(int index) {
        int optCode = this.options.get(index);
        return (optCode == -1);
    }

    public String getAns() {
        return this.answer;
    }

    //code for testing
    public static void main(String[] args) {
        QuizItem item = new QuizItem("easy");
        item.printConsole();

        System.out.println("done");

    }
}