package com.example.finalproject;

import androidx.fragment.app.Fragment;

public class EventListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EventListFragment();
    } //creating fragment from activity to list.
}