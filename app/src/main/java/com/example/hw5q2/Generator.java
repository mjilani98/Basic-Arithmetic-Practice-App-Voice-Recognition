package com.example.kidsmathpracticeapp;

import java.util.Random;

public class Generator {

    //number 1 will be randomly generated
    private int num1;
    //number 2 will be randomly generated
    private int num2;
    //The correct answer based on the type of operation
    private int correctAnswer;
    //The number that will be inputted from the user
    private int answerInput;
    //The type of operation, randomly will be generated
    private int operator;
    //The total number of questions been answered
    private int totalQuestions  = 0;
    //The total number of the right answers
    private int totalCorrectAnswers = 0 ;


    //create a Random class object
    private Random random = new Random();

    public void generate()
    {
        //the operator will range between 1 and 4 , both inclusive
        operator = random.nextInt(4) +1 ;

        if(operator == 1)
        {
            num1 = random.nextInt(999)+1;
            num2 = random.nextInt(999)+1;
        }
        else if(operator == 2)
        {
            num1 = random.nextInt(999)+1;
            num2 = random.nextInt(999)+1;
        }
        else if(operator == 3)
        {
            num1 = random.nextInt(99)+1;
            num2 = random.nextInt(99)+1;
        }
        else if(operator == 3)
        {
            num1 = random.nextInt(999)+1;
            num2 = random.nextInt(99)+1;
        }

    }

    public int getCorrectAnswer()
    {
        //if Addition
        if(operator == 1 )
            correctAnswer = num1 + num2;
        //if Subtraction
        if(operator == 2)
            correctAnswer = num1 - num2;
        if(operator == 3 )
            correctAnswer = num1 * num2;
        if(operator == 4 )
            correctAnswer = num1 / num2;

        return correctAnswer;
    }



    //get number 1
    public int getNum1()
    {
        return num1;
    }

    //get number 2
    public int getNum2()
    {
        return num2;
    }

    //get the correct answer


    //get total questions
    public int getTotalQuestions()
    {
        return totalQuestions;
    }

    //get the total of correct answers
    public int getTotalCorrectAnswers()
    {
        return totalCorrectAnswers;
    }

    public void incrementCorrectAnswers()
    {
        totalCorrectAnswers +=1;
    }

    public void incrementTotalQuestions()
    {
        totalQuestions += 1;
    }

    public int getOperator()
    {
        return operator;
    }



}
