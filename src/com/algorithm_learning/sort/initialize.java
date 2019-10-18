package com.algorithm_learning.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;


public class initialize {
    private double[] data;
    private Random r = new Random();
    public double startTime;
    public double endTime;

    double tmp;

    public initialize(int n) {
        data = new double[n];
        for (int i = 0; i < n; i++) {
            tmp = r.nextInt(500000);
            tmp += r.nextDouble();
            tmp *= 1000;
            data[i] = (int)tmp / 1000.0;
        }
    }

    public double[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "use time is " + (endTime - startTime) + " ms\n\n" +
                Arrays.toString(data) + "\n\nuse time is " + (endTime - startTime) + " ms";
    }
}
