package com.example.tribe.ui.calendar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tribe.Adapters.EventsAdapter;
import com.example.tribe.Adapters.MyFotoAdapter;
import com.example.tribe.Model.EventMOdel;
import com.example.tribe.R;
import com.example.tribe.databinding.FragmentCalendarBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class CalendarFragment extends Fragment {
    ImageButton addEvents;
    String trybeid="notadd";
    RecyclerView events_rec;


    ProgressDialog progressDialog;
    EventsAdapter eventsAdapter;
    List<EventMOdel> eventMOdelList;
    HorizontalCalendar horizontalCalendar;
    private FragmentCalendarBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressDialog= new ProgressDialog(getActivity(),R.style.DialogStyle);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        addEvents=root.findViewById(R.id.addEvents);
        events_rec=root.findViewById(R.id.events_rec);
        eventMOdelList =new ArrayList<>();
        eventsAdapter=new EventsAdapter(getContext(),eventMOdelList);
        events_rec.setLayoutManager(new LinearLayoutManager(getContext()));
        events_rec.setAdapter(eventsAdapter);

        addEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_calendar_to_createEvent);

            }
        });
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, 0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
         horizontalCalendar = new HorizontalCalendar.Builder(root, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();;


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                String s=String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", date));
                getEvents(s);
            }
        });
        getEvents();
         return root;
    }



    void getEvents(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null)
        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("trybe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trybeid=snapshot.getValue(String.class);
                FirebaseDatabase.getInstance().getReference("Events").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        eventMOdelList.clear();
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            EventMOdel eventMOdel= dataSnapshot.getValue(EventMOdel.class);
                            if(eventMOdel!=null)
                            if(eventMOdel.getTrybeid().equals(trybeid)&eventMOdel.getPublisher().equals(firebaseUser.getUid()))
                                eventMOdelList.add(eventMOdel);
                        }
                        eventsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void getEvents(String date){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null)
        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("trybe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trybeid=snapshot.getValue(String.class);
                FirebaseDatabase.getInstance().getReference("Events").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        eventMOdelList.clear();
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            EventMOdel eventMOdel= dataSnapshot.getValue(EventMOdel.class);
                            if(eventMOdel!=null)
                            if(eventMOdel.getTrybeid().equals(trybeid)&eventMOdel.getPublisher().equals(firebaseUser.getUid()))
                                if(eventMOdel.getDate().equals(date))
                                eventMOdelList.add(eventMOdel);
                        }
                        eventsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}