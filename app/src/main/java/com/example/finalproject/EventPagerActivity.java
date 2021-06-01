package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class EventPagerActivity extends
        AppCompatActivity {

    private static final String EXTRA_event_ID =
            "com.bignerdranch.android.criminalintent.event_id";


    public static ViewPager mViewPager;
    private List<Event> mEvents;

    public static Intent newIntent(Context packageContext, UUID eventId) { //creating intent
        Intent intent = new Intent(packageContext,
                EventPagerActivity.class);
        intent.putExtra(EXTRA_event_ID, eventId);
        return intent;
    }

    UUID eventId = (UUID) getIntent() //defining id
            .getSerializableExtra(EXTRA_event_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pager);
        mViewPager = (ViewPager)
                findViewById(R.id.event_view_pager);
        mEvents = EventLab.get(this).getEvents(); //using this very activity as context
        FragmentManager fragmentManager =
                getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Event event = mEvents.get(position);
                return
                        EventFragment.newInstance(event.getId());
            }
            @Override
            public int getCount() {
                return mEvents.size();
            }
        });
        for (int i = 0; i < mEvents.size(); i++) { //view specific item and work with scroll in order to make view in line.
            if
            (mEvents.get(i).getId().equals(eventId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static void clear(){
        mViewPager.removeAllViews();
    } //clearing all views.
}
