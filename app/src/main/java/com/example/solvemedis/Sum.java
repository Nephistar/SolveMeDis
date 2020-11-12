package com.example.solvemedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sum {
    private Integer bound;
    private Integer sum;
    private List<Integer> summand;
    String question;
    String answer;
    List<String> error;
    public Random rand;

    Sum(Integer bound) {
        this.rand = new Random();
        this.bound = bound;
        this.sum = genSum();
        this.summand = new ArrayList<>(5);
        this.genSummands();
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

    private Integer genSum() {
        return this.randNextIntLeftGauss();
    }

    private void genSummands() {
        //first summand: random number smaller than sum
        int genSummand = this.rand.nextInt(this.sum - 1);
        this.summand.add(genSummand);
        // second summand: calculate
        genSummand = this.sum - genSummand;
        this.summand.add(genSummand);
    }

    private ArrayList<String> genErr() {
        CalcError calculator = new CalcError(this.summand.get(0), this.summand.get(1), this.sum, "+");
        ArrayList<Integer> results = calculator.getResults();
        ArrayList<String> errorStrings = new ArrayList<>();
        for (int idx = 0; idx < results.size(); idx++) {
            String str = results.get(idx).toString();
            errorStrings.add(str);
        }
        return errorStrings;
    }

    private String toQuestion() {
        String q = "Find the value of the sum!\n";
        q += this.summand.get(0);
        q += " + ";
        q += this.summand.get(1);
        q += "\n";
        return q;
    }

    private String toAnswer() {
        return "" + this.sum;
    }
}
