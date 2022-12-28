package com.example.tribe.ui;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tribe.Model.User;
import com.example.tribe.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreateEvent extends Fragment {


    EditText title,description;
    CardView post;
    DatePicker datePicker;
    TimePicker timePicker;
    String trybeid ="notadded";
    ProgressDialog progressDialog;
    private DatabaseReference dbRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create_event, container, false);
        title=view.findViewById(R.id.Title);
        progressDialog= new ProgressDialog(getActivity(),R.style.DialogStyle);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        description=view.findViewById(R.id.Description);
        post=view.findViewById(R.id.post);
        datePicker=view.findViewById(R.id.simpleDatePicker);
        timePicker=view.findViewById(R.id.datePicker1);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().toString().trim().isEmpty()){
                    title.setError("Tittle cannot be empty");
                    return;
                }
                else if(description.getText().toString().trim().isEmpty()){
                    description.setError("Description cannot be empty");
                    return;

                }

                postEvent(getDate(),getTime(),title.getText().toString().trim(),description.getText().toString().trim());
            }

        });

        return view;
    }


    public void postEvent(String date, String time, String title, String description){


        progressDialog.show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");

        String eventid = reference.push().getKey();

        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("eventid" , eventid);
        hashMap.put("description" , description);
        hashMap.put("title" , title);
        hashMap.put("date" , date);
        hashMap.put("time" , time);
        hashMap.put("trybeid","ppp");
        hashMap.put("excepted","false");
        hashMap.put("publisher" , FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.child(eventid).setValue(hashMap).addOnSuccessListener(unused -> getTrybeMenmbers(eventid,description));

    }

    String getDate(){
        String s;
        if(datePicker.getMonth() <10&&datePicker.getDayOfMonth()<10){
            s=datePicker.getYear()+"-"+"0"+datePicker.getMonth()+"-"+"0"+datePicker.getDayOfMonth();

        }
        else if(datePicker.getMonth() <10){
            s=datePicker.getYear()+"-"+"0"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth();

        }
        else {
            s=datePicker.getYear()+"-"+datePicker.getMonth()+"-"+"0"+datePicker.getDayOfMonth();

        }
        return s;
    }


    void getTrybeMenmbers(String eventid,String description) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.orderByChild("trybe").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    if (user.getTrybe().equals("notadded")) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("trybe");

                        trybeid = reference.push().getKey();

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("trybeid", trybeid);
                        reference.child(trybeid).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("trybe").setValue(trybeid);

                            }
                        });

                    }
                    else{
                        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("trybe").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                trybeid=snapshot.getValue(String.class);
                                FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            User user = snapshot.getValue(User.class);
                                            if(user.getTrybe().equals(trybeid)){
                                                addNotifications(user.getId(),eventid,description);
                                            }
                                        }
                                        FirebaseDatabase.getInstance().getReference("Events").child(eventid).child("trybeid").setValue(trybeid);
                                        progressDialog.dismiss();
                                        if(getActivity()!=null){
                                        getActivity().onBackPressed();
                                        Toast.makeText(getActivity(), "Event created", Toast.LENGTH_SHORT).show();}

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

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void addNotifications(String userid , String postid,String desc) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(userid);

        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("userid" , FirebaseAuth.getInstance().getCurrentUser().getUid());
        hashMap.put("text" , desc);
        hashMap.put("postid" , postid);
        hashMap.put("ispost" , true);

        reference.push().setValue(hashMap);
    }


    String getTime(){
        int hour, minute;
        String am_pm;
        if (Build.VERSION.SDK_INT >= 23 ){
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }
        else{
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }
        if(hour > 12) {
            am_pm = "PM";
            hour = hour - 12;
        }
        else
        {
            am_pm="AM";
        }
        return hour +":"+ minute+" "+am_pm;
    }

}