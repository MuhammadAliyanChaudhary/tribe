package com.example.tribe.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tribe.Model.Notification;
import com.example.tribe.Model.Post;
import com.example.tribe.Model.User;
import com.example.tribe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    private Context mContext;
    private List<com.example.tribe.Model.Notification> mNotifications;

    public NotificationAdapter(Context mContext, List<com.example.tribe.Model.Notification> mNotifications) {
        this.mContext = mContext;
        this.mNotifications = mNotifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Notification notification = mNotifications.get(position);
        holder.text.setText(notification.getText());

        getUserInfo(holder.image_profile , holder.username , notification.getUserid());


    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image_profile;
        public ImageView post_image;
        public TextView username;
        public TextView text;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_profile = itemView.findViewById(R.id.image_profile);
            post_image = itemView.findViewById(R.id.post_image);
            username = itemView.findViewById(R.id.username);
            text = itemView.findViewById(R.id.comment);
        }
    }

    private void getUserInfo(final ImageView imageView , final TextView username , String publisherid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(publisherid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.default_image)
                        .error(R.drawable.default_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH);
                if(user!=null){
                if(user.getImageURL()!=null)
                Glide.with(mContext).load(user.getImageURL())
                        .apply(options)
                        .into(imageView);
                username.setText(user.getUsername());}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
