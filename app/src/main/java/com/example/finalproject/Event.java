package com.example.finalproject;

import android.location.Location;
import android.util.Log;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Event {
    //figured out how to clone, commit and push
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Time mTime;
    private boolean mCompleted;
    private Location mLocation;
    private String temperature;
    private boolean needsVaccination;
    private boolean needsMask;
    public boolean accessed = false;

    public Event() {
        mId = UUID.randomUUID();
        mDate = new Date();
        mTime = new Time(mDate.getTime());
        this.mTitle = "Event";
        this.needsMask = false;
        this.needsVaccination = false;
        this.mCompleted = false;
    }

    public void setVaxBox(boolean bool){
        this.needsVaccination = bool;
    }

    public void setMaskBox(boolean bool){
        this.needsMask = bool;
    }

    public boolean getMaskBox(){
        return this.needsMask;
    }

    public boolean getVaxBox(){
        return this.needsVaccination;
    }

    public void setTime(int hours, int mins, int seconds){
        this.mTime = new Time(hours, mins, seconds);
    }

    public Location getLocation(){
        return mLocation;
    }

    /*public Location setLocation(){
        //
    }*/

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
    public void setTitle(String title) {
        mTitle = title;
        if (this.getTitle().equals("The Overlake School")){
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
            //this.get
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

    public boolean inLine(Person person){
        if (person.mGenericSafe == false){
            this.setCompleted(false);
            return false;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        if (person.getMasked() == true){
            i++;
        }
        if (this.getMaskBox() == true){
            j++;
        }
        if (person.getVaccinated() == true){
            k++;
        }
        if (this.getVaxBox() == true){
            l++;
        }
        if (i >= j && k >= l){
            this.setCompleted(true);
            return true;
        }
        else{
            this.setCompleted(false);
            return false;
        }
        /*else{
            j++;
        }
        if ()*/
        /*if (event.getMaskBox() != getMasked()){
            if (event.getMaskBox() == true){
                Log.d("WE GOT A MAJOR ISSUE", "THIS ONE");
            }
            return false;
        }
        if (event.getVaxBox() != getVaccinated()){
            return false;
        }
        else{
            return true;
        }*/
        /*else{
            if (event.getMaskBox() != getMasked()){
                return false;
            }
            if (event.getVaxBox() != getVaccinated()){
                return false;
            }
            else{
                return true;
            }
        }*/
    }
}
