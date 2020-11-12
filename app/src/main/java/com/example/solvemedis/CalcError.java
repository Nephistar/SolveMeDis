package com.example.solvemedis;

import java.util.ArrayList;
import java.util.LinkedList;

public class CalcError {
    int oneNum;
    int otherNum;
    int ans;
    private int oneUnitsDigit;
    private int otherUnitsDigit;
    private int ansUnitsDigit;
    private int oneTensDigit;
    private int otherTensDigit;
    private int ansTensDigit;
    private int errorBaseSum;
    private LinkedList<Integer> trivial;
    private ArrayList<Integer> results;
    int RESULT_COUNT = 3; // for to have four options in the quiz

    public CalcError(int oneNumber, int otherNumber, int answer, String operation) {
        this.oneNum = oneNumber;
        this.otherNum = otherNumber;
        this.ans = answer;
        this.oneUnitsDigit = this.oneNum % 10;
        this.otherUnitsDigit = this.otherNum % 10;
        this.ansUnitsDigit = this.ans % 10;
        this.oneTensDigit = (this.oneNum % 100) / 10;
        this.otherTensDigit = (this.otherNum % 100) / 10;
        this.ansTensDigit = (this.ans % 100) / 10;
        this.trivial = new LinkedList<>();
        this.trivial.offer(this.ans - 1);
        this.results = new ArrayList<>();
        if (operation.equals("+")) {
            this.errorBaseSum = this.ans - this.ansUnitsDigit;
            this.fillResultsSum100();
        }
        else if (operation.equals("-")) {
            this.fillResultsDif100();
        }
    }

    // all error calculating methods should return 0 if not successful

    private int calcSumFlap100() {
        // error while adding the units digit, especially when adding with carry
        int err;
        int oneFlap = 10 - this.oneUnitsDigit;
        int otherFlap = 10 - this.otherUnitsDigit;
        if (otherFlap > oneFlap)
            err = this.errorBaseSum + otherFlap;
        else
            err = this.errorBaseSum + oneFlap;
        return err;
    }

    private int calcSumCarryLost100() {
        // basic error with forgetting the carry for sums < 100
        int err = 0;
        // check if there is a carry
        if (this.oneUnitsDigit + this.otherUnitsDigit > 9)
            err = this.ans - 10;
        return err;
    }

    private int calcDifCarryLost100() {
        // basic error with forgetting the carry for differences < 100
        int err = 0;
        // check if there is a carry
        if (this.oneUnitsDigit - this.otherUnitsDigit < 0)
            err = this.ans + 10;
        return err;
    }

    private int calcDifCarryAdd100() {
        // error with adding the carry for differences < 100
        int err = 0;
        // check if there is a carry
        if (this.oneUnitsDigit - this.otherUnitsDigit < 0)
            err = this.ans + 20;
        return err;
    }

    private int calcSumDirection100() {
        // error with subtracting the units digit instead of adding
        int err;
        if (this.otherUnitsDigit < this.oneUnitsDigit)
            err = this.ans - 2 * this.otherUnitsDigit;
        else
            err = this.ans - 2 * this.oneUnitsDigit;
        return err;
    }

    private int calcDifDirection100() {
        // error with adding the units digit instead of subtracting
        int err;
        err = this.ans + 2 * this.otherUnitsDigit;
        return err;
    }

    private int calcSumSwap100() {
        // error with swapping digits before adding
        int err = 0;
        int swapped = 0;
        int oneGap = Math.abs(this.oneTensDigit - this.oneUnitsDigit);
        int otherGap = Math.abs(this.otherTensDigit - this.otherUnitsDigit);
        if (oneGap < otherGap && oneGap != 0) {
            swapped = this.oneUnitsDigit * 10 + this.oneTensDigit;
            err = swapped + this.otherNum;
        } else if (otherGap != 0) {
            swapped = this.otherUnitsDigit * 10 + this.otherTensDigit;
            err = swapped + this.oneNum;
        }
        return err;
    }

    private int calcAnsSwap100() {
        // just swap the digits
        int err = 0;
        if (this.ansTensDigit != this.ansUnitsDigit)
            err = this.ansUnitsDigit * 10 + this.ansTensDigit;
        return err;
    }

    private int calcNextTrivial() {
        if (this.trivial.peek() == null) {
            if (this.ans - 10 > 0) {
                this.trivial.offer(this.ans - 10);
            }
            this.trivial.offer(this.ans + 1);
            this.trivial.offer(this.ans + 10);
        }
        return this.trivial.poll();
    }

    private void fillResultsSum100() {
        this.results.add(calcSumFlap100());
        this.results.add(calcSumCarryLost100());
        this.results.add(calcSumDirection100());
        this.results.add(calcSumSwap100());
        this.results.add(calcAnsSwap100());
        // remove unwanted elements
        // ArrayList remove() returns true if this list contained the element
        // remove all zeros
        Integer zero = 0;
        Boolean contains;
        do contains = this.results.remove(zero);
        while (contains);
        // remove unintentionally correct answers
        Integer correct = this.ans;
        do contains = this.results.remove(correct);
        while (contains);
        // remove doubles
        for (int idx = 0; idx < this.results.size(); idx++) {
            // look for equal elements in the rest of the list
            Integer element = this.results.get(idx);
            do contains = this.results.subList(idx +1,this.results.size()).remove(element);
            while (contains);
        }
        // check size
        if (this.results.size() == RESULT_COUNT)
            return;
        while (this.results.size() < RESULT_COUNT) {
            this.results.add(calcNextTrivial());
        }
        // too many results
        // remove last entry: simple swap from calcAnsSwap100()
        if (this.results.size() > RESULT_COUNT)
            this.results.remove(this.results.size() - 1);
        // remove the element that has the greatest difference from the correct answer (maxGap)
        while (this.results.size() > RESULT_COUNT) {
            int idxMaxGap = 0;
            int maxGap = Math.abs(this.ans - this.results.get(idxMaxGap));
            for (int idx = 1; idx < this.results.size(); idx++) {
                int currGap = Math.abs(this.ans - this.results.get(idx));
                if (currGap > maxGap) {
                    maxGap = currGap;
                    idxMaxGap = idx;
                }
            }
            this.results.remove(idxMaxGap);
        }
    }

    private void fillResultsDif100() {
        this.results.add(calcDifCarryLost100());
        this.results.add(calcDifCarryAdd100());
        this.results.add(calcDifDirection100());
        this.results.add(calcAnsSwap100());
        // remove unwanted elements
        // ArrayList remove() returns true if this list contained the element
        // remove all zeros
        Integer zero = 0;
        Boolean contains;
        do contains = this.results.remove(zero);
        while (contains);
        // remove unintentionally correct answers
        Integer correct = this.ans;
        do contains = this.results.remove(correct);
        while (contains);
        // remove doubles
        for (int idx = 0; idx < this.results.size(); idx++) {
            // look for equal elements in the rest of the list
            Integer element = this.results.get(idx);
            do contains = this.results.subList(idx +1,this.results.size()).remove(element);
            while (contains);
        }
        // check size
        if (this.results.size() == RESULT_COUNT)
            return;
        while (this.results.size() < RESULT_COUNT) {
            this.results.add(calcNextTrivial());
        }
        // too many results
        // remove last entry: simple swap from calcAnsSwap100()
        if (this.results.size() > RESULT_COUNT)
            this.results.remove(this.results.size() - 1);
        // remove the element that has the greatest difference from the correct answer (maxGap)
        while (this.results.size() > RESULT_COUNT) {
            int idxMaxGap = 0;
            int maxGap = Math.abs(this.ans - this.results.get(idxMaxGap));
            for (int idx = 1; idx < this.results.size(); idx++) {
                int currGap = Math.abs(this.ans - this.results.get(idx));
                if (currGap > maxGap) {
                    maxGap = currGap;
                    idxMaxGap = idx;
                }
            }
            this.results.remove(idxMaxGap);
        }
    }

   public ArrayList<Integer> getResults() {
       return this.results;
   }
}