package com.example.finalproject;

import android.location.Location;
import android.util.Log;

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
    private boolean mDoubleSafe;
    private boolean mVaccinated;
    private boolean mMasked;
    private boolean mGenericSafe;


    public Person() {
        mId = UUID.randomUUID();
        mVaccinated = false;
        mMasked = false;
        mSafe = (mVaccinated || mMasked);
        mDoubleSafe = (mVaccinated && mMasked);
        mGenericSafe = false;
    }

    public void setLocation(Location l){
        this.location = l;
    }

    public void setName(String n){
        this.mName = n;
    }

    public void setmDateOfBirth(String n){
        //mDateOfBirth = new Date();
        // 10/25/30
        int year;
        if (Integer.parseInt(n.substring(6,8)) <= 21){
            year = 2000 + Integer.parseInt(n.substring(6,8)) - 1900;
        }
        else{
            year = 1900 + Integer.parseInt(n.substring(7,9)) - 1900;
        }
        int month = Integer.parseInt(n.substring(0,2));
        int date = Integer.parseInt(n.substring(3, 5));
        this.mDateOfBirth = new Date(year, (month - 1) % 12, date);
    }

    public boolean getSafe(){
        return mSafe;
    }

    public void setGenericSafe(boolean bool){
        this.mGenericSafe = bool;
    }

    public boolean inLine(Event event){
        if (this.mGenericSafe == false){
            return false;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        if (getMasked() == true){
            i++;
        }
        if (event.getMaskBox() == true){
            j++;
        }
        if (getVaccinated() == true){
            k++;
        }
        if (event.getVaxBox() == true){
            l++;
        }
        if (i >= j && k >= l){
            return true;
        }
        else{
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

    public void update(){
        mSafe = (mVaccinated || mMasked);
        mDoubleSafe = (mVaccinated && mMasked);
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

    public String getDateOfBirth() {
        return mDateOfBirth.toString();
    }

    public String getName() {
        return mName;
    }
}
