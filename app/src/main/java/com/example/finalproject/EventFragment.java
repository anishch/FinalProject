package com.example.finalproject;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.finalproject.Event;
import com.example.finalproject.EventLab;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

import static android.widget.CompoundButton.*;


public class EventFragment extends Fragment { //creating Event

    private static final String ARG_Event_ID = "Event_id";
    private static final String DIALOG_DATE_AND_TIME ="DialogDateandTime";

    private static final int REQUEST_DATE_AND_TIME = 0;

    private boolean bool = true;

    private Date date;


    private EditText mTitleField;
    private Event mEvent;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mVaxBox;
    private CheckBox mMaskBox;
    private EditText mCOVIDViolator;

    private EditText tempQuestion; //EditText
    private EditText soonQuestion;
    private EditText symptomQuestion;
    private EditText maskQuestion;
    private CheckBox mSolvedCheckBox;

    private boolean bool2 = false;
    private String str;

    public static EventFragment newInstance(UUID EventId){ //crafting new instance
        Bundle args = new Bundle();
        args.putSerializable(ARG_Event_ID, EventId);

        EventFragment fragment = new EventFragment(); //new fragment -> allows for recursion
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID EventId = (UUID) getArguments().getSerializable(ARG_Event_ID);
        mEvent = EventLab.get(getActivity()).getEvent(EventId); //invoking eventLab
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_event, container,
                false);
        mCOVIDViolator = (EditText) v.findViewById(R.id.event_title); //title

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
                if (mEvent.getTitle().equals("The Overlake School")){ //setting boolean to be true
                    bool2 = true;
                    str = s.toString();
                }
                if (mEvent.getTitle().equals("CenturyLink Stadium")){
                    bool2 = true;
                    str = s.toString();
                }
                else if (mEvent.getTitle().equals("T-Mobile Park")){
                    bool2 = true;
                    str = s.toString();
                }
                else if (mEvent.getTitle().equals("Fred Meyer")){
                    bool2 = true;
                    str = s.toString();

                }
                else if (mEvent.getTitle().equals("UW")){
                    bool2 = true;
                    str = s.toString();
                }
                else if (mEvent.getTitle().equals("Starbucks")){
                    bool2 = true;
                    str = s.toString();
                }
                else{

                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        mVaxBox = (CheckBox) v.findViewById(R.id.vax_needed);
        mVaxBox.setChecked(mEvent.getVaxBox());

        if (mEvent.getTitle().equals("The Overlake School")){
            mEvent.setVaxBox(false); //setting vaccination false
            mVaxBox.setChecked(mEvent.getVaxBox());
            mVaxBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("CenturyLink Stadium")){ //same pattern
            mEvent.setVaxBox(true);
            mVaxBox.setChecked(mEvent.getVaxBox());
            mVaxBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("T-Mobile Park")){
            mEvent.setVaxBox(true);
            mVaxBox.setChecked(mEvent.getVaxBox());
            mVaxBox.setEnabled(false);

        }
        else if (mEvent.getTitle().equals("Fred Meyer")){
           mEvent.setVaxBox(false);
           mVaxBox.setChecked(mEvent.getVaxBox());
           mVaxBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("UW")){
            mEvent.setVaxBox(true);
            mVaxBox.setChecked(mEvent.getVaxBox());
            mVaxBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("Starbucks")){
            mEvent.setVaxBox(false);
            mVaxBox.setChecked(mEvent.getVaxBox());
            mVaxBox.setEnabled(false);
        }
        else if (!bool2){ //if creating a new dir
            Log.d("Hey", "Prob not working"); //log function
            mVaxBox.setChecked(mEvent.getVaxBox());
            mVaxBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    mEvent.setVaxBox(isChecked); //can be customized

                }
            });
        }

        mMaskBox = (CheckBox) v.findViewById(R.id.mask_needed);
        mMaskBox.setChecked(mEvent.getMaskBox());

        if (mEvent.getTitle().equals("The Overlake School")){ //same logic but for Mask Reqs (look down)
            Log.d("BOOL ACTIVATED", "RECOGNIZED");
            mEvent.setMaskBox(true);
            mMaskBox.setChecked(mEvent.getMaskBox());
            mMaskBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("CenturyLink Stadium")){
            mEvent.setMaskBox(false);
            mMaskBox.setChecked(mEvent.getMaskBox());
            mMaskBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("T-Mobile Park")){
            mEvent.setMaskBox(false);
            mMaskBox.setChecked(mEvent.getMaskBox());
            mMaskBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("Fred Meyer")){
             mEvent.setMaskBox(true);
             mMaskBox.setChecked(mEvent.getMaskBox());
        }
        else if (mEvent.getTitle().equals("UW")){
            mEvent.setMaskBox(true);
            mMaskBox.setChecked(mEvent.getMaskBox());
            mMaskBox.setEnabled(false);
        }
        else if (mEvent.getTitle().equals("Starbucks")){
             mEvent.setMaskBox(true);
             mMaskBox.setChecked(mEvent.getMaskBox());
             mMaskBox.setEnabled(false);
        }
        else if (!bool2){ //if creating something outside of dir
            mMaskBox.setChecked(mEvent.getMaskBox());
            mMaskBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    mEvent.setMaskBox(isChecked);
                }
            });
        }

        mDateButton = (Button)
                v.findViewById(R.id.event_date_and_time);
        mDateButton.setText(mEvent.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                CombinedFragment dialog = CombinedFragment.newInstance(mEvent.getDate(), mEvent.getTime());
                dialog.setTargetFragment(EventFragment.this, REQUEST_DATE_AND_TIME);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.show(manager, DIALOG_DATE_AND_TIME);
            }
        });

        //checking temperature
        tempQuestion = (EditText) v.findViewById(R.id.question_temperature);
        tempQuestion.setText(mEvent.getTemperature());
        tempQuestion.addTextChangedListener(new TextWatcher() {
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
                if (Integer.parseInt(s.toString()) > 99){
                    bool = false;
                    MainActivity.person.setGenericSafe(false);
                }
                else{
                    MainActivity.person.setGenericSafe(bool);
                }
            }
        });

        //checking proximity (copy-paste)
        soonQuestion = (EditText) v.findViewById(R.id.question_proximity);
        soonQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int
                    before, int count) {
                if (soonQuestion.getText().toString().toLowerCase().equals("yes")){
                    bool = false;
                    MainActivity.person.setGenericSafe(false);
                }
                else{
                    MainActivity.person.setGenericSafe(bool);
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
        //checking recent symptoms
        symptomQuestion = (EditText) v.findViewById(R.id.question_symptoms);
        symptomQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int
                    before, int count) {
                if (symptomQuestion.getText().toString().toLowerCase().equals("yes")){
                    bool = false;
                    MainActivity.person.setGenericSafe(false);
                }
                else{
                    MainActivity.person.setGenericSafe(bool);
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        //making sure mask is worn
        maskQuestion = (EditText) v.findViewById(R.id.question_mask);
        maskQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count,
                    int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int
                    before, int count) {
                if (maskQuestion.getText().toString().toLowerCase().equals("no")){
                    bool = false;
                    MainActivity.person.setMasked(false);
                    MainActivity.person.update();
                }
                else if (maskQuestion.getText().toString().toLowerCase().equals("yes")){
                    MainActivity.person.setMasked(true);
                    MainActivity.person.update();
                }
            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        //to suggest completion of event so that it can either be locked or confirmed.
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.event_solved);
        mEvent.setCompleted(false);
        mSolvedCheckBox.setChecked(mEvent.isCompleted());
        mSolvedCheckBox.setEnabled(false);
        if (MainActivity.person.inLine(mEvent)){
            mSolvedCheckBox.setEnabled(true);
            mEvent.setCompleted(true);
            mSolvedCheckBox.setChecked(mEvent.isCompleted());
        }
        else{
            mSolvedCheckBox.setEnabled(false);
            mEvent.setCompleted(false);
            mSolvedCheckBox.setChecked(mEvent.isCompleted());
            Log.d("ISSUE", "INLINE IS SCREWED UP");
        }

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

    //Activity Result
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
            date.setHours(time.getHours()); //set the date
            date.setMinutes(time.getMinutes());
            date.setSeconds(time.getSeconds());
            mEvent.setDate(date); //ensure that the event has the attribute before display.
            mDateButton.setText(mEvent.getDate().toString());
        }

    }


}