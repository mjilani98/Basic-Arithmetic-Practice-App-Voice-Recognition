package com.example.hw5q2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //create a question generator
    private Generator generator;

    //code used for speech recognition activity
    private final int ACTIVITY_TALK = 1;

    private double number1;           //number one in the question
    private double number2;           //number two in the question
    private double answer;            //correct answer to the question

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //display the screen
        setContentView(R.layout.activity_main);

        generator = new Generator();

        //check speech recognition is available
        if (!checkSpeechRecognition())
        {
            Toast.makeText(this, "No speech recognition", Toast.LENGTH_LONG).show();
        }
        else
        {
            //create a question
            createQuestion();
            //display the question
            displayQuestion();
        }

    }

    //Method checks whether speech recognition is available
    private boolean checkSpeechRecognition()
    {
        //create package manager
        PackageManager manager = getPackageManager();

        //find available speech recognition activities
        List<ResolveInfo> list = manager.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

        //check there is at least one activity
        return list.size() > 0;
    }

    //Method creates a question
    private void createQuestion()
    {
        generator.generate();


        //getting the number from generator
        number1 = generator.getNum1();
        number2 = generator.getNum2();

        //correct answer
        answer = generator.getCorrectAnswer();
    }

    //method display the question on the screen
    private void displayQuestion()
    {

        //display number 1
        TextView num1View = findViewById(R.id.Num1);
        num1View.setText((int)number1+"");

        //display number 2
        TextView num2View = findViewById(R.id.Num2);
        num2View.setText((int)number2+"");

        //display operator
        TextView operator = findViewById(R.id.Operator);
        operator.setText(generator.getOperator());

        //reset the result view
        TextView result = findViewById(R.id.result);
        result.setText("");
    }

    //event handler of the next button
    public void next(View v)
    {
        createQuestion();
        displayQuestion();
    }

    //event handler of the answer button
    public void answer(View view)
    {
        listen();
    }

    //method prompts the user to speak and recognizes user's speech
    private void listen()
    {
        //create speech recognition activity
        Intent listenIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        //set speech recognition model
        listenIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        //set a prompt
        listenIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your answer");

        //set maximum number of results
        listenIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

        //start speech recognition
        startActivityForResult(listenIntent, ACTIVITY_TALK);
    }

    //Method displays the results of user's speech
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //if the results are from speech recognition request
        if (requestCode == ACTIVITY_TALK)
        {
            //find the list of words that were recognized
            ArrayList<String> words = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            //display whether user answered correctly or not
            displayResult(words);
        }
    }

    //Method displays whether the user answered question correctly
    private void displayResult(ArrayList<String> words)
    {
        //text view for output
        TextView resultTextView = findViewById(R.id.result);


        if(generator.getOperatorNumber() != 4) // if the operation is not division , the answer is integer
        {
            //if list of recognized words contains correct the answer, say correct
            if (words.contains((int)answer+""))
            {
                resultTextView.setTextColor(Color.parseColor("#00FF00")); //setting the text color to green
                resultTextView.setText("Correct");
                System.out.println("the answer : "+generator.getCorrectAnswer());
            }
            //otherwise say incorrect
            else
            {
                resultTextView.setTextColor(Color.parseColor("#FF0000")); //setting the text color to red
                resultTextView.setText("Incorrect");
                System.out.println("the answer : "+generator.getCorrectAnswer());
            }
        }
        else //else the answer is double
        {
            //if list of recognized words contains correct the answer, say correct
            if (words.contains(answer+""))
            {
                resultTextView.setTextColor(Color.parseColor("#00FF00")); //setting the text color to green
                resultTextView.setText("Correct");
            }
            //otherwise say incorrect
            else
            {
                resultTextView.setTextColor(Color.parseColor("#FF0000")); //setting the text color to red
                resultTextView.setText("Incorrect");
            }
        }

    }
}