package com.example.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CombinedFragment extends DialogFragment {

    public static final String EXTRA_TIME = "com.bignerdranch.android.criminalintent.time";
    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    private static final String ARG_DATE = "date";
    private static final String ARG_TIME = "time";

    private TimePicker mTimePicker;
    private DatePicker mDatePicker;

    public int minute;
    public int hour;
    public int second = 0;

    private Date date;
    private Time time;
    //start

    public static CombinedFragment newInstance(Date date, Time time) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        args.putSerializable(ARG_TIME, time);
        CombinedFragment fragment = new CombinedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Time[] time = {(Time)
                getArguments().getSerializable(ARG_TIME)};

        //time.getTime();

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date_time_picker, null);

        mTimePicker = (TimePicker)
                v.findViewById(R.id.dialog_time_picker);
        mTimePicker.setEnabled(true);
        mTimePicker.setIs24HourView(false);

        final Date[] date = {(Date)
                getArguments().getSerializable(ARG_DATE)};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date[0]);
        final int[] year = {calendar.get(Calendar.YEAR)};
        final int[] month = {calendar.get(Calendar.MONTH)};
        final int[] day = {calendar.get(Calendar.DAY_OF_MONTH)};

        //Fragment fragment = new Fragment();


        mDatePicker = (DatePicker)
                v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year[0], month[0], day[0], null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Time of Event")
                .setPositiveButton(android.R.string.ok,
                        null)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void
                            onClick(DialogInterface dialog, int which) {
                                int year =
                                        mDatePicker.getYear();
                                int month =
                                        mDatePicker.getMonth();
                                int day =
                                        mDatePicker.getDayOfMonth();
                                date[0] = new GregorianCalendar(year, month, day).getTime();
                                minute =
                                        mTimePicker.getCurrentMinute();
                                hour =
                                        mTimePicker.getCurrentHour();
                                Clock clock = new Clock() {
                                    @Override
                                    public ZoneId getZone() {
                                        return null;
                                    }

                                    @Override
                                    public Clock withZone(ZoneId zone) {
                                        return null;
                                    }

                                    @Override
                                    public Instant instant() {
                                        return null;
                                    }
                                }; sendResult(Activity.RESULT_OK, new Time(hour, minute, second), date[0]);
                            }
                        })
                .create();

    }



    //end


    private void sendResult(int resultCode, Time time, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        try{
            intent.putExtra(EXTRA_DATE, date);
            intent.putExtra(EXTRA_TIME, time);
        } catch (Exception e){
            Log.d("intent_error", "Issue!!!!!");
        }
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
