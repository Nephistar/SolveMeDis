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

    public Sum(Integer bound) {
        this.rand = new Random();
        this.bound = bound;
        this.sum = genSum();
        this.summand = new ArrayList<>(5);
        this.genSummands();
        this.error = new ArrayList<>(5);
        this.genErr();
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
        // limits set; associated function values therefore in limits:
        // 0 <= rand <= bound
        rand = this.bound * rand / 2 + this.bound;
        return (int) rand;
    }

    private Integer genSum() {
        return this.randNextIntLeftGauss();
    }

    private void genSummands() {
        int genSummand = this.rand.nextInt(this.sum);
        this.summand.add(genSummand);
        genSummand = this.sum - genSummand;
        this.summand.add(genSummand);
    }

    private void genErr() {
        genErr100();
    }

    private void genErr100() {
        int oneSummand = this.summand.get(0);
        int otherSummand = this.summand.get(1);
        int unitsDigit = oneSummand % 10;
        int otherUnitsDigit = otherSummand % 10;
        int sumUnitsDigit = this.sum % 10;
        int errorBase = this.sum - sumUnitsDigit;

        int flapError;
        // error while adding the units digit, especially when adding with carry
        int flap = 10 - unitsDigit;
        int otherflap = 10 - otherUnitsDigit;
        if (otherflap > flap)
            flapError = errorBase + otherflap;
        else
            flapError = errorBase + flap;
        // check: can't be same as right answer!
        if (flapError == this.sum)
            flapError++;
        this.error.add("" + flapError);

        if (unitsDigit + otherUnitsDigit > 9) {
            int carryLostError;
            // error with forgetting the carry
            carryLostError = this.sum - 10;
            this.error.add("" + carryLostError);
        }
        else {
            int directionError;
            // error with subtracting the units digit instead of adding
            if (otherUnitsDigit < unitsDigit)
                directionError = this.sum - 2 * otherUnitsDigit;
            else
                directionError = this.sum - 2 * unitsDigit;
            // check: can't be same as right answer!
            if (directionError == this.sum)
                directionError--;
            this.error.add("" + directionError);
        }

        int swapError;
        // error with swapping the last two digits, especially when they are similar
        int tensDigit = (oneSummand % 100) / 10;
        int otherTensDigit = (otherSummand % 100) / 10;
        int sumTensDigit = (this.sum % 100) / 10;
        int swapProb = -Math.abs(tensDigit - unitsDigit);
        int otherSwapProb = -Math.abs(otherTensDigit - otherUnitsDigit);
        int sumSwapProb = -Math.abs(sumTensDigit - sumUnitsDigit);
        if (sumSwapProb > swapProb && sumSwapProb > otherSwapProb && sumSwapProb != 0)
            swapError = sumUnitsDigit * 10 + sumTensDigit;
        else if (otherSwapProb > swapProb && otherSwapProb != 0)
            swapError = oneSummand + otherUnitsDigit * 10 + otherTensDigit;
        else if (swapProb != 0)
            swapError = otherSummand + unitsDigit * 10 + tensDigit;
        else
            swapError = this.sum - 1;
        this.error.add("" + swapError);
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
