package com.example.finalproject;

import android.content.Context;

import com.example.finalproject.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EventLab {

    private static EventLab sEventLab;

    //private List<Event> mEvents;
    private static Map<UUID, Event> mEvents;

    public static EventLab get(Context context) {
        if (sEventLab == null) {
            sEventLab = new EventLab(context);
        }
        return sEventLab;
    }

    private EventLab(Context context) {
        mEvents = new LinkedHashMap<>();
    }

    public void addEvent(Event c) {
        mEvents.put(c.getId(), c);
    }

    public static List<Event> getEvents() {
        return new ArrayList<>(mEvents.values());
    }


    public Event getEvent(UUID id) {
        if (mEvents.containsKey(id)){
            return mEvents.get(id);
        }
        else {
            return null;
        }
    }

}