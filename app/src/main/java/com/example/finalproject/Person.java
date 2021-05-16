package com.example.finalproject;

import android.location.Location;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Person {

    private UUID mId;
    private String mName;
    private Date mDateOfBirth;
    //private Time mTime;
    private Location location;
    private boolean mSafe;
    private boolean mVaccinated;
    private boolean mMasked;


    public Person() {
        mId = UUID.randomUUID();
        mVaccinated = false;
        mMasked = false;
        mSafe = (mVaccinated || mMasked);
    }

    public void setLocation(Location l){
        this.location = l;
    }

    public void setName(String n){
        this.mName = n;
    }

    public void setmDateOfBirth(String n){
        // 10/25/30
        int year;
        if (Integer.parseInt(n.substring(7,5)) <= 21){
            year = 2000 + Integer.parseInt(n.substring(7,9));
        }
        else{
            year = 1900 + Integer.parseInt(n.substring(7,9));
        }
        int month = Integer.parseInt(n.substring(0,2));
        int date = Integer.parseInt(n.substring(3, 5));
        this.mDateOfBirth = new Date(year, month, date);
    }

    public boolean getSafe(){
        return mSafe;
    }

    public boolean getMasked(){
        return mMasked;
    }

    public boolean getVaccinated(){
        return mVaccinated;
    }

    public Location getLocation(){
        return location;
    }

    public void setMasked(boolean solved) {
        mMasked = solved;
    }

    public void setVaccinated(boolean solved) {
        mVaccinated = solved;
    }
}
