package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import android.text.format.DateFormat;

public class EventListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mEventRecyclerView;
    private EventAdapter mAdapter;
    private boolean mSubtitleVisible;
    private Button button;
    int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mSubtitleVisible =
                    savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        View view =
                inflater.inflate(R.layout.fragment_event_list,
                        container, false);
        mEventRecyclerView = (RecyclerView) view
                .findViewById(R.id.event_recycler_view);
        mEventRecyclerView.setLayoutManager(new
                LinearLayoutManager(getActivity()));

        updateUI();
        return view;

    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE,
                mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,
                                    MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_event_list,
                menu);

        MenuItem subtitleItem =
                menu.findItem(R.id.update_details);
        subtitleItem.setTitle("Clear All Events");

        MenuItem subtitleItem2 =
                menu.findItem(R.id.change_vax_status);
        subtitleItem2.setTitle("Alternate vaccination status");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_event:
                Event event = new Event();
                EventLab.get(getActivity()).addEvent(event);
                Intent intent = EventActivity.newIntent(getActivity(), event.getId());
                startActivity(intent);
                updateUI();
                return true;
            case R.id.update_details:
                EventLab.get(getActivity()).clearList();
                Fragment fragmentOfScoreList = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                FragmentTransaction betweenFragmentTransaction = getFragmentManager().beginTransaction();
                betweenFragmentTransaction.detach(fragmentOfScoreList);
                betweenFragmentTransaction.attach(fragmentOfScoreList);
                betweenFragmentTransaction.commit();
            case R.id.change_vax_status:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                MainActivity.person.setVaccinated(!MainActivity.person.getVaccinated());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        EventLab eventLab = EventLab.get(getActivity());
        int EventCount = EventLab.getEvents().size();
        String subtitle =
                getString(R.string.subtitle_format, EventCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity)
                getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private ImageView mStopImageView;

        private Event mEvent;

        public EventHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_event, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.event_title);
            mDateTextView =  itemView.findViewById(R.id.event_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mStopImageView = itemView.findViewById(R.id.imageView2);
        }

        public void bind(Event event){
            mEvent = event;
            mTitleTextView.setText(event.getTitle());
            mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy, hh:mm", event.getDate()));
            mEvent.setCompleted(mEvent.inLine(MainActivity.person));
            if (mEvent.getDate().getDate() !=  new Date(System.currentTimeMillis()).getDate()){
                mSolvedImageView.setVisibility(View.GONE);
                mStopImageView.setVisibility(View.GONE);
                mEvent.setCompleted(false);
                if (mEvent.getTitle().equals(EventLab.getEvents().get(EventLab.getEvents().size() - 1).getTitle())){
                    if (mEvent.accessed){
                        Toast.makeText (getActivity(), R.string.event_found, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText (getActivity(), R.string.event_not_found, Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else{
                mSolvedImageView.setVisibility(mEvent.isCompleted() ? View.VISIBLE : View.GONE);
                mStopImageView.setVisibility(!mEvent.isCompleted() ? View.VISIBLE : View.GONE);
                if (mEvent.getTitle().equals(EventLab.getEvents().get(EventLab.getEvents().size() - 1).getTitle())){
                    if (mEvent.accessed){
                        Toast.makeText (getActivity(), R.string.event_found, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText (getActivity(), R.string.event_not_found, Toast.LENGTH_SHORT).show();
                    }
                }
                if (mEvent.getDate().getTime() <= System.currentTimeMillis() + (15*60*1000)){
                    Toast.makeText (getActivity(), R.string.event_soon, Toast.LENGTH_SHORT).show();
                }
            }

        }

        @Override
        public void onClick(View view){
            Intent intent = EventActivity.newIntent(getActivity(), mEvent.getId());
            startActivity(intent);
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder> {
        private List<Event> mEvents;
        public EventAdapter(List<Event> events) {
            mEvents = events;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new EventHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(EventHolder holder, int position) {
            Event event = mEvents.get(position);
            holder.bind(event);
        }
        @Override
        public int getItemCount() {
            return mEvents.size();
        }

    }

    private void updateUI() {
        EventLab eventLab = EventLab.get(getActivity());
        List<Event> Events = EventLab.getEvents();
        if(mAdapter == null) {
            mAdapter = new EventAdapter(Events);
            mEventRecyclerView.setAdapter(mAdapter);
        }
        else{ mAdapter.notifyDataSetChanged(); }
        updateSubtitle();
    }

}