package com.example.finalproject;

import android.location.Location;
import android.util.Log;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

//base Person class
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
    public boolean mGenericSafe;


    public Person() { //basic Person constructor
        mId = UUID.randomUUID();
        mVaccinated = false; //false by default
        mMasked = false;
        mSafe = (mVaccinated || mMasked); //defining Safety as confluence of two
        mDoubleSafe = (mVaccinated && mMasked); //different metric
        mGenericSafe = false; //setting default of false for event fill-ins
    }

    public void setLocation(Location l){
        this.location = l;
    } //setting location

    public void setName(String n){
        this.mName = n;
    } //setting name

    public void setmDateOfBirth(String n){ //settin DOB - kind of useless but pushes garb of formality
        int year;
        if (Integer.parseInt(n.substring(6,8)) <= 21){ // if num is less than 21 - assume it's from the 21st century
            year = 2000 + Integer.parseInt(n.substring(6,8)) - 1900;
        }
        else{ //assume it's from the 1900s
            year = 1900 + Integer.parseInt(n.substring(7,9)) - 1900;
        }
        int month = Integer.parseInt(n.substring(0,2));
        int date = Integer.parseInt(n.substring(3, 5));
        this.mDateOfBirth = new Date(year, (month - 1) % 12, date);
    }

    public boolean getSafe(){
        return mSafe;
    } //check if safe

    public void setGenericSafe(boolean bool){
        this.mGenericSafe = bool;
    }

    public boolean inLine(Event event){ //metric of individual person inline with event
        // - refer to event to see corresponding comments - exact converse.
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
    }

    public void update(){ //updating such that this occurs immediately
        mSafe = (mVaccinated || mMasked);
        mDoubleSafe = (mVaccinated && mMasked);
    }

    public boolean getMasked(){
        return mMasked;
    } //check if masked

    public boolean getVaccinated(){
        return mVaccinated;
    } //check if vaxxed

    public Location getLocation(){
        return location;
    } //return location

    public void setMasked(boolean solved) {
        mMasked = solved;
    } //setmasked

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
