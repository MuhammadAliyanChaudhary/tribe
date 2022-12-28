package com.example.tribe.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tribe.Model.EventMOdel;
import com.example.tribe.Model.Post;
import com.example.tribe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    Context context;
    List<EventMOdel> eventList;
    private ViewHolder holder;
    private int position;

    public EventsAdapter(Context context, List<EventMOdel> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new EventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {



        holder.date.setText(eventList.get(position).getDate());
        holder.time.setText(eventList.get(position).getTime());
        holder.description.setText(eventList.get(position).getDescription());
        holder.title.setText(eventList.get(position).getTitle());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context , view);
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()){
                        case R.id.accept :
                            addNotifications(eventList.get(position).getPublisher(),eventList.get(position).getEventid(),"Accepted your event "+eventList.get(position).getTitle());
                            return true;

                        case R.id.deny :
                            addNotifications(eventList.get(position).getPublisher(),eventList.get(position).getEventid(),"Rejected your event "+eventList.get(position).getTitle());
                            return true;
                        case R.id.deleteEvent :
                            FirebaseDatabase.getInstance().getReference().child("Events").child(eventList.get(position).getEventid()).removeValue();
                            return true;
                        case R.id.editEvent :
                            editPost(eventList.get(position));
                            return true;
                        default:
                            return false;
                    }
                });
                popupMenu.inflate(R.menu.delete_menu);
                if (!eventList.get(position).getPublisher().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    popupMenu.getMenu().findItem(R.id.deleteEvent).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.editEvent).setVisible(false);
                }
                if(eventList.get(position).getPublisher().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    popupMenu.getMenu().findItem(R.id.accept).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.deny).setVisible(false);

                }
                popupMenu.show();
            }
        });

    }


    private void addNotifications(String userid , String postid, String text) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(userid);

        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("userid" , FirebaseAuth.getInstance().getCurrentUser().getUid());
        hashMap.put("text" , text);
        hashMap.put("postid" , postid);
        hashMap.put("ispost" , false);

        reference.push().setValue(hashMap);
    }


    private void editPost (EventMOdel eventMOdel) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Edit Event Description");

        final EditText desc = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT ,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        desc.setLayoutParams(lp);
        alertDialog.setView(desc);

        getText(eventMOdel.getEventid() , desc,"description");

        alertDialog.setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         FirebaseDatabase.getInstance().getReference("Events").child(eventMOdel.getEventid()).child("description").setValue(desc.getText().toString().trim());

                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }


    private void getText(String eventid , final EditText editText, String valuePath) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events")
                .child(eventid).child(valuePath);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editText.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,time,description,title;
        ImageView delete;
        CardView cardViewEvent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            delete=itemView.findViewById(R.id.imageView2);
            description=itemView.findViewById(R.id.description);
            title=itemView.findViewById(R.id.title);
            cardViewEvent = itemView.findViewById(R.id.cardViewEvent);
        }
    }
}
