package com.example.finalproject;

import android.location.Location;
import android.util.Log;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Event { //Core Event Class
    private UUID mId; //unique id
    private String mTitle; //name of event
    private Date mDate; //date
    private Time mTime; //time
    private boolean mCompleted; //completed - kind of irrelevant but also important
    private Location mLocation; //obsolete - tried to use it as a feature but decided that reminders could be given in the context of time
    private String temperature; // temperature - to determine if fever exists or not
    private boolean needsVaccination; // element of event requirement (date)
    private boolean needsMask; // element of event requirement (mask)
    public boolean accessed = false; // in a directory with preset or a custom-created dir

    public Event() { //general constructor
        mId = UUID.randomUUID();
        mDate = new Date(); //current date
        mTime = new Time(mDate.getTime()); //current time (long)
        this.mTitle = "Event";
        this.needsMask = false;
        this.needsVaccination = false;
        this.mCompleted = false;
    }

    public void setVaxBox(boolean bool){
        this.needsVaccination = bool;
    } //setting vaccination req

    public void setMaskBox(boolean bool){
        this.needsMask = bool;
    } //setting mask req

    public boolean getMaskBox(){
        return this.needsMask;
    } //getting ifNecessary (mask)

    public boolean getVaxBox(){
        return this.needsVaccination;
    } //getting if Necessary (vax)

    public void setTime(int hours, int mins, int seconds){ //setting time - not sure whether this is used
        this.mTime = new Time(hours, mins, seconds);
    }

    public Location getLocation(){
        return mLocation;
    } //receiving location

    public Time getTime(){
        return mTime;
    }
    public void setTime(long time){
        this.mTime.setTime(time);
        this.mDate.setTime(time);
    }

    public void setTemperature(String str){
        this.temperature = str;
    }

    public String getTemperature(){
        return this.temperature;
    }

    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) { //setting title
        mTitle = title; //generic command
        if (this.getTitle().equals("The Overlake School")){ //for all accessed commands, we set the event characteristics then so that the completed vs not completed doesn't cause a delay...
            this.setVaxBox(false);
            this.setMaskBox(true);
            this.accessed = true;
        }
        else if (this.getTitle().equals("CenturyLink Stadium")){
            this.setVaxBox(true);
            this.setMaskBox(false);
            this.accessed = true;
        }
        else if (this.getTitle().equals("T-Mobile Park")){
            this.setVaxBox(true);
            this.setMaskBox(false);
            this.accessed = true;
        }
        else if (this.getTitle().equals("Fred Meyer")){
            this.setVaxBox(false);
            this.setMaskBox(true);
            this.accessed = true;
        }
        else if (this.getTitle().equals("UW")){
            this.setVaxBox(true);
            this.setMaskBox(true);
            this.accessed = true;
        }
        else if (this.getTitle().equals("Starbucks")){
            this.setVaxBox(false);
            this.setMaskBox(true);
            this.accessed = true;
        }
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isCompleted() {
        return mCompleted;
    }
    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public boolean inLine(Person person){ //checking if person is compatible with event requirements
        if (person.mGenericSafe == false){ // if basic requirements are not met (which all events will need)
            this.setCompleted(false);
            return false;
        }
        int i = 0; //4 values below for comparative analysis
        int j = 0;
        int k = 0;
        int l = 0;
        if (person.getMasked() == true){ //adding element to person
            i++;
        }
        if (this.getMaskBox() == true){ //adding req to event
            j++;
        }
        if (person.getVaccinated() == true){ //adding element to person
            k++;
        }
        if (this.getVaxBox() == true){ //adding req to event
            l++;
        }
        if (i >= j && k >= l){ //checking of person trumps event in all categories
            this.setCompleted(true);
            return true;
        }
        else{
            this.setCompleted(false);
            return false;
        }

    }
}
