package com.example.finalproject;

import org.w3c.dom.Text;

//base question code
public class Question {

    private String str;

    public Question(String str){ //constructor
        this.str = str;
    }

    public String getName(){
        return this.str;
    } //i have no reason why i just didn't make it public lol

    public void setName(String str){
        this.str = str;
    }

}
