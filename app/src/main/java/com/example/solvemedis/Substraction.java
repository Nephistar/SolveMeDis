package com.example.solvemedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Substraction {
    private Integer bound;
    private Integer difference;
    private List<Integer> taskNumbers;
    String question;
    String answer;
    List<String> error;
    public Random rand;

    Substraction(Integer bound) {
        this.rand = new Random();
        this.bound = bound;
        this.taskNumbers = new ArrayList<>(5);
        this.taskNumbers.add(genMinuend());
        this.genSubtrahentDifference();
        this.error = genErr();
        this.question = toQuestion();
        this.answer = toAnswer();
    }

    public int randNextIntLeftGauss() {
        // generate a random int with a distribution like some left half of Gauss
        double rand;
        // set limits: -2.0 <= rand <= 0.0
        do {
            rand = this.rand.nextGaussian();
            // cut off outliers (double standard deviation)
        } while (Math.abs(rand) > 2);
        // mirror right half of Gauss to left half
        if (rand > 0.0)
            rand = -rand;
        // limits set; function values therefore in limits:
        // 0 <= rand <= bound
        rand = this.bound * rand / 2 + this.bound;
        // add small offset
        rand += 10;
        return (int) rand;
    }

    private Integer genMinuend() {
        return this.randNextIntLeftGauss();
    }

    private void genSubtrahentDifference() {
        //Subtrahent: random number smaller than sum
        int subtrahent = this.rand.nextInt(this.taskNumbers.get(0) - 1);
        this.taskNumbers.add(subtrahent);
        // difference: calculate
        this.difference = this.taskNumbers.get(0) - subtrahent;
    }

    private ArrayList<String> genErr() {
        CalcError calculator = new CalcError(this.taskNumbers.get(0), this.taskNumbers.get(1), this.difference, "-");
        ArrayList<Integer> results = calculator.getResults();
        ArrayList<String> errorStrings = new ArrayList<>();
        for (int idx = 0; idx < results.size(); idx++) {
            String str = results.get(idx).toString();
            errorStrings.add(str);
        }
        return errorStrings;
    }

    private String toQuestion() {
        String q = "Find the value of the difference!\n";
        q += this.taskNumbers.get(0);
        q += " - ";
        q += this.taskNumbers.get(1);
        q += "\n";
        return q;
    }

    private String toAnswer() {
        return "" + this.difference;
    }
}
