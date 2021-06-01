package com.example.finalproject;

import android.content.Context;
import android.util.Log;

import com.example.finalproject.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EventLab { //compilation and event management

    private static EventLab sEventLab; //recursive ability

    public static Map<UUID, Event> mEvents; //hashmap, which is superior to arraylist for management purposes

    public static EventLab get(Context context) { //such that the same events can be distributed without pointer arithmetic necessary
        if (sEventLab == null) {
            sEventLab = new EventLab(context);
        }
        return sEventLab;
    }

    private EventLab(Context context) {
        mEvents = new LinkedHashMap<>();
    } //constructor

    public void addEvent(Event c) {
        mEvents.put(c.getId(), c);
    } //adding event

    public static List<Event> getEvents() {
        return new ArrayList<>(mEvents.values());
    } //returning list of values (subjective though)


    public Event getEvent(UUID id) { //receiving event by id
        if (mEvents.containsKey(id)){
            return mEvents.get(id);
        }
        else {
            return null;
        }
    }

    public void clearList() {
        mEvents.clear();
    } //clearing list

    public void clearPastList() { //attempt to clear past list - this seemed to work but issues existed with displaying it immediately so idea was discarded.
        for (int i = 0; i < getEvents().size(); i++){
            if (getEvents().get(i).getDate().getTime() < System.currentTimeMillis()){
                mEvents.remove(getEvents().get(i).getId());
                i--;
                Log.d("Wassup", "Going through the if statement");
            }
            Log.d("Hello", "For Loop");
        }
    }
}