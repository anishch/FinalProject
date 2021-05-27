package com.example.finalproject;

import org.w3c.dom.Text;

public class Question {

    private String str;
    //private boolean mAnswerTrue;

    public Question(String str){
        this.str = str;
        //this.mAnswerTrue = AnswerTrue;
    }

    public String getName(){
        return this.str;
    }

    public void setName(String str){
        this.str = str;
    }

}
