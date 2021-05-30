package com.example.finalproject;

import android.location.Location;

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
}
