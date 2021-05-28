package com.example.finalproject;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.finalproject.Event;
import com.example.finalproject.EventLab;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

import static android.widget.CompoundButton.*;

public class EventFragment extends Fragment {

    private static final String ARG_Event_ID = "Event_id";
    private static final String DIALOG_DATE_AND_TIME ="DialogDateandTime";
    //private static final String DIALOG_TIME = "DialogTime";

    private static final int REQUEST_DATE_AND_TIME = 0;

    private boolean bool = false;

    private Date date;


    private EditText mTitleField;
    private Event mEvent;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;
    private EditText mCOVIDViolator;

    public static EventFragment newInstance(UUID EventId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_Event_ID, EventId);

        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID EventId = (UUID) getArguments().getSerializable(ARG_Event_ID);
        mEvent = EventLab.get(getActivity()).getEvent(EventId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //mEvent = new Event();
        View v = inflater.inflate(R.layout.fragment_event, container,
                false);
        mCOVIDViolator = (EditText) v.findViewById(R.id.event_title);
        mCOVIDViolator.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int
                    before, int count) {
                mEvent.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        mTitleField = (EditText) v.findViewById(R.id.event_temperature);
        mTitleField.setText(mEvent.getTemperature());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int
                    before, int count) {
                mEvent.setTemperature(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        mDateButton = (Button)
                v.findViewById(R.id.event_date_and_time);
        mDateButton.setText(mEvent.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                CombinedFragment dialog = CombinedFragment.newInstance(mEvent.getDate(), mEvent.getTime());
                //Fragment dialog2  TimePickerFragment.newInstance(mEvent.getTime());
                dialog.setTargetFragment(EventFragment.this, REQUEST_DATE_AND_TIME);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.show(manager, DIALOG_DATE_AND_TIME);
                //dialog.setTarget
                //dialog.show(manager, DIALOG_TIME);
                //bool = true;

                //bool = false;
                /*if (!bool){
                    FragmentManager manager = getFragmentManager();
                    DatePickerFragment dialog = DatePickerFragment.newInstance(mEvent.getDate());
                    dialog.setTargetFragment(EventFragment.this, REQUEST_DATE);
                    dialog.show(manager, DIALOG_DATE);
                    bool = true;
                }
                else if (bool){
                    FragmentManager manager2 = getFragmentManager();
                    TimePickerFragment dialog2 = TimePickerFragment.newInstance(mEvent.getTime());
                    dialog2.setTargetFragment(EventFragment.this, REQUEST_TIME);
                    dialog2.show(manager2, DIALOG_TIME);
                    bool = false;
                }*/
            }
        });
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.event_solved);
        mSolvedCheckBox.setChecked(mEvent.isCompleted());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mEvent.setCompleted(isChecked);
            }
        });
        FragmentManager fm = getFragmentManager();
        Fragment fragment =
                fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new EventFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,
                            fragment)
                    .commit();
        }
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE_AND_TIME){
            date = (Date) data
                    .getSerializableExtra(CombinedFragment.EXTRA_DATE);
            Time time = (Time) data.getSerializableExtra(CombinedFragment.EXTRA_TIME);
            date.setHours(time.getHours());
            date.setMinutes(time.getMinutes());
            date.setSeconds(time.getSeconds());
            mEvent.setDate(date);
            mDateButton.setText(mEvent.getDate().toString());
        }

    }


}