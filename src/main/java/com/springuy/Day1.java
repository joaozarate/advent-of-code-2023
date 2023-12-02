package com.springuy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {

    public static void main(String[] args) {
        var input = Day1.class.getResourceAsStream("/input.txt");

        List<String> inputList = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))
                .lines()
                .toList();

        AtomicInteger calibrationSum = new AtomicInteger();

        inputList.forEach(line -> {
            var lineConverted = spellOutTheNumber(line);

            var currentSum = calibrationSum.get();

            var leftValue = -1;
            var rightValue = -1;
            var startPos = 0;
            var endPos = lineConverted.length() -1;

            while (currentSum == calibrationSum.get()) {
                var leftChar = lineConverted.charAt(startPos);
                var rightChar = lineConverted.charAt(endPos);

                if (Character.isDigit(leftChar)) leftValue = Character.getNumericValue(leftChar);
                if (Character.isDigit(rightChar)) rightValue = Character.getNumericValue(rightChar);

                if (leftValue > -1 && rightValue > -1) {
                    calibrationSum.addAndGet(Integer.parseInt(leftValue + "" + rightValue));
                }

                if (!Character.isDigit(leftChar)) startPos++;
                if (!Character.isDigit(rightChar)) endPos--;
            }

        });

        System.out.println("Calibration sum: " + calibrationSum.get());

    }

    private static String spellOutTheNumber(String line) {
        return line.replace("one", "on1e")
                .replace("two", "tw2o")
                .replace("three", "thre3e")
                .replace("four", "fou4r")
                .replace("five", "fiv5e")
                .replace("six", "si6x")
                .replace("seven", "seve7n")
                .replace("eight", "eigh8t")
                .replace("nine", "nin9e");
    }

}
