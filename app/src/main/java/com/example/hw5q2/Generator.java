package com.example.hw5q2;

import java.util.Random;

public class Generator {

    // Number 1 and number 2 will be randomly generated as doubles
    private double num1;
    private double num2;
    // The correct answer based on the type of operation
    private double correctAnswer;
    // The type of operation, randomly generated
    private int operator;

    // Create a Random class object
    private Random random = new Random();

    public void generate()
    {
        // The operator will range between 1 and 4, both inclusive
        operator = random.nextInt(4) + 1;

        // Generate num1 and num2 as doubles, between 1 and 100
        num1 = random.nextInt(100) + 1;  // Generates a number between 1 and 100
        num2 = random.nextInt(100) + 1;  // Generates a number between 1 and 100

        // Ensure num2 is not 0 for division
        if (operator == 4 && num2 == 0)
        {
            num2 = 1; // Prevent division by zero
        }
    }

    // Method returns the correct answer
    public double getCorrectAnswer()
    {
        // Calculate the correct answer based on the operator
        switch (operator) {
            case 1:  // Addition
                correctAnswer = num1 + num2;
                break;
            case 2:  // Subtraction
                correctAnswer = num1 - num2;
                break;
            case 3:  // Multiplication
                correctAnswer = num1 * num2;
                break;
            case 4:  // Division
                correctAnswer = num1 / num2;
                break;
            default:
                correctAnswer = 0;
                break;
        }

        // Round the result to 1 decimal place
        return Math.round(correctAnswer * 10.0) / 10.0;
    }

    // Get number 1
    public double getNum1() {
        return num1;
    }

    // Get number 2
    public double getNum2() {
        return num2;
    }

    // Get operator symbol as a string
    public String getOperator() {
        switch (operator) {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "*";
            case 4:
                return "/";
            default:
                return "";
        }
    }

    public int getOperatorNumber()
    {
        return operator;
    }
}
