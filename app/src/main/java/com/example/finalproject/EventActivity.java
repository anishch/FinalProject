package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class EventActivity extends SingleFragmentActivity {

    private static final String EXTRA_event_ID = "com.bignerdranch.android.criminalintent.event_id";

    public static Intent newIntent (Context packageContext, UUID eventId){
        Intent intent = new Intent(packageContext, EventActivity.class);
        intent.putExtra(EXTRA_event_ID, eventId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_event_ID);
        return EventFragment.newInstance(eventId);
    }

}

/*
package com.example.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class eventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm =
                getSupportFragmentManager();
        Fragment fragment =
                fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new eventFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,
                            fragment)
                    .commit();
        }
    }
}
*/
