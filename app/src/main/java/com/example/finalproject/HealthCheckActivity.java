package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HealthCheckActivity extends AppCompatActivity implements View.OnClickListener {

        private EditText tempQuestion;
        private EditText soonQuestion;
        private EditText symptomQuestion;
        private EditText maskQuestion;
        private CheckBox isCompleted;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_health_check);
            tempQuestion = findViewById(R.id.question_temperature);
            soonQuestion = findViewById(R.id.question_proximity);
            symptomQuestion = findViewById(R.id.question_symptoms);
            maskQuestion = findViewById(R.id.question_mask);
            isCompleted = findViewById(R.id.event_solved);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.event_solved) {
                save();
            }
        }

        public void save(){
            boolean bool = false;
            if (Integer.parseInt(tempQuestion.getText().toString()) >= 99){
                bool = true;
            }
            if (soonQuestion.getText().toString().toLowerCase().equals("yes")){
                bool = true;
            }
            if (symptomQuestion.getText().toString().toLowerCase().equals("yes")){
                bool = true;
            }
            if (bool == false){
               
            }


        }


}
