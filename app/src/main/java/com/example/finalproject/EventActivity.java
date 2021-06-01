package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class EventActivity extends SingleFragmentActivity { //Single Fragment's extended here - take note

    private static final String EXTRA_event_ID = "com.bignerdranch.android.criminalintent.event_id";

    public static Intent newIntent (Context packageContext, UUID eventId){ //creating intent
        Intent intent = new Intent(packageContext, EventActivity.class);
        intent.putExtra(EXTRA_event_ID, eventId);
        return intent;
    }

    @Override
    protected Fragment createFragment() { //creating fragment and sending it
        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_event_ID);
        return EventFragment.newInstance(eventId);
    }

}

