package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// first original event which discovers "permanent" facets of individual which are not susceptible to change.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button nextButton; //defining next and previous buttons
    Button previousButton;
    EditText eText;
    int index = 0; //index for question management
    static Person person; //making it static such that it can be accessed cross-activities

    private Question[] mQuestionBank = new Question[]{ //Questions Created
            new Question("What is your name?"),
            new Question("What is your date of birth? (MM/DD/YY)"),
            new Question("Are you vaccinated?")
    };

    // [Question], [Attribute]

    @Override
    protected void onCreate(Bundle savedInstanceState) { //init()
        super.onCreate(savedInstanceState);
        person = new Person(); //constructing one and only but "new" person
        setContentView(R.layout.activity_main);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.prev_button);
        eText = findViewById(R.id.textview);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        eText.setHint(mQuestionBank[index].getName());
    }

    @Override
    public void onClick(View v) { //change questions accordingly
        if (v.getId() == R.id.next_button){
            nextQuestion();
        }
        else if (v.getId() == R.id.prev_button){
            prevQuestion();
        }
        else if (v.getId() == R.id.textview){
            nextQuestion();
        }
    }

    public void nextQuestion(){
        if (index == 0){
            person.setName(eText.getText().toString());
            Toast.makeText(this, "Name Saved as " + person.getName(), Toast.LENGTH_LONG).show();
        }
        else if (index == 1){
            person.setmDateOfBirth(eText.getText().toString());
            Toast.makeText(this, "DOB Saved as " + person.getDateOfBirth(), Toast.LENGTH_LONG).show(); //Kind of found this interesting that it could be represented string-wise.
        }
        else if (index == 2){ // ending
            if (eText.getText().toString().toLowerCase().equals("yes")){
                person.setVaccinated(true); //yes/no answers
            }
            else if (eText.getText().toString().toLowerCase().equals("no")){
                person.setVaccinated(false); //yes-no answers
            }

        }
        if (index < mQuestionBank.length - 1){
            index++;
            eText.setText("");
            eText.setHint(mQuestionBank[index].getName());
        }
        else{
            nextButton.setEnabled(false); //stopping any possibility of changing buttons
            nextButton.setVisibility(View.GONE); //making all disappeear
            previousButton.setEnabled(false);
            previousButton.setVisibility(View.GONE);
            eText.setEnabled(false);
            eText.setVisibility(View.GONE);
            Intent intent = new Intent(this, EventListActivity.class); //transferring activity to the "Criminal Intent" aspect of the application.
            startActivity(intent);
        }
    }

    public void prevQuestion(){
        Toast.makeText(this, "Info Saved", Toast.LENGTH_LONG).show();
        index--;
        eText.setText("");
        eText.setHint(mQuestionBank[index].getName());
    }
}