package com.example.tribe.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tribe.LC.CommentsActivity;
import com.example.tribe.Model.Post;
import com.example.tribe.Model.User;
import com.example.tribe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendraanggrian.appcompat.widget.SocialTextView;

import java.util.HashMap;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public Context mContext;
    public List<Post> mPost;

    private FirebaseUser firebaseUser;

    public PostAdapter(Context mContext, List<Post> mPost) {
        this.mContext = mContext;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.post_item , parent , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final Post post = mPost.get(position);

        if (post.getDescription().equals("")){
            holder.description.setVisibility(View.GONE);
        } else {
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(post.getDescription());
        }

        publisherInfo(holder.image_profile , holder.username , holder.publisher , post.getPublisher());
        isLikes(post.getPostid() , holder.like);
        dislike(post.getPostid(),holder.dislike);
        noLikes(holder.likes , post.getPostid());
        getComments(post.getPostid() , holder.comments);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.like.getTag().equals("like")){
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(post.getPostid())
                            .child(firebaseUser.getUid()).setValue(true);
                    FirebaseDatabase.getInstance().getReference().child("disLikes")
                            .child(post.getPostid())
                            .child(firebaseUser.getUid()).removeValue();
                    addNotifications(post.getPublisher() , post.getPostid());
                } else {
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(post.getPostid())
                            .child(firebaseUser.getUid()).removeValue();
                }
            }
        });
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.dislike.getTag().equals("dislike")){
                    FirebaseDatabase.getInstance().getReference().child("disLikes")
                            .child(post.getPostid())
                            .child(firebaseUser.getUid()).setValue(true);
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(post.getPostid())
                            .child(firebaseUser.getUid()).removeValue();

                    adddislikeNotifications(post.getPublisher() , post.getPostid());
                } else {
                    FirebaseDatabase.getInstance().getReference().child("disLikes")
                            .child(post.getPostid())
                            .child(firebaseUser.getUid()).removeValue();
                }
            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , CommentsActivity.class);
                intent.putExtra("postid" , post.getPostid());
                intent.putExtra("publisherid" , post.getPublisher());
                mContext.startActivity(intent);
            }
        });

        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , CommentsActivity.class);
                intent.putExtra("postid" , post.getPostid());
                intent.putExtra("publisherid" , post.getPublisher());
                mContext.startActivity(intent);
            }
        });


        if (!post.getPublisher().equals(firebaseUser.getUid())){
            holder.more.setVisibility(View.GONE);
        }
        holder.more.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(mContext , v);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.edit :
                        editPost(post.getPostid());
                        return true;

                    case R.id.delete :
                        FirebaseDatabase.getInstance().getReference("Posts")
                                .child(post.getPostid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(mContext, "Deleted!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        return true;
                    default:
                        return false;
                }
            });
            popupMenu.inflate(R.menu.post_menu);
            if (!post.getPublisher().equals(firebaseUser.getUid())){
                popupMenu.getMenu().findItem(R.id.edit).setVisible(false);
                popupMenu.getMenu().findItem(R.id.delete).setVisible(false);
            }
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image_profile;
        public ImageView like;
        public ImageView dislike;
        public ImageView comment;
        public ImageView more;


        public TextView username;
        public TextView likes;
        public TextView publisher;
        public TextView description;
        public TextView comments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_profile = itemView.findViewById(R.id.image_profile);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            more = itemView.findViewById(R.id.more);
            dislike=itemView.findViewById(R.id.dis_like);

            username = itemView.findViewById(R.id.username);
            likes = itemView.findViewById(R.id.likes);
            publisher = itemView.findViewById(R.id.publisher);
            description = itemView.findViewById(R.id.description);
            comments = itemView.findViewById(R.id.comments);
        }
    }

    private void getComments(String postid , final TextView comments){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments").child(postid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comments.setText("View All " + dataSnapshot.getChildrenCount() + " Comments");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void isLikes (String postid , final ImageView imageView) {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(firebaseUser.getUid()).exists()){
                    imageView.setImageResource(R.drawable.ic_liked);
                    imageView.setTag("liked");
                } else {
                    imageView.setImageResource(R.drawable.ic_like);
                    imageView.setTag("like");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void dislike (String postid , final ImageView imageView) {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("disLikes").child(postid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(firebaseUser.getUid()).exists()){
                    imageView.setImageResource(R.drawable.ic_disliked);
                    imageView.setTag("disliked");
                } else {
                    imageView.setImageResource(R.drawable.ic_like);
                    imageView.setTag("dislike");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    private void addNotifications(String userid , String postid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(userid);

        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("userid" , firebaseUser.getUid());
        hashMap.put("text" , "liked your post");
        hashMap.put("postid" , postid);
        hashMap.put("ispost" , true);

        reference.push().setValue(hashMap);
    }

    private void adddislikeNotifications(String userid , String postid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(userid);

        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("userid" , firebaseUser.getUid());
        hashMap.put("text" , "dis-liked your post");
        hashMap.put("postid" , postid);
        hashMap.put("ispost" , true);

        reference.push().setValue(hashMap);
    }

    private void noLikes (final TextView likes , String postid) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Likes").child(postid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                likes.setText(dataSnapshot.getChildrenCount() + " likes");
                FirebaseDatabase.getInstance().getReference().child("Posts").child(postid).child("likesCount").setValue(String.valueOf(dataSnapshot.getChildrenCount()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void publisherInfo (final ImageView image_profile , final TextView username , final TextView publisher , String userid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

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
                Glide.with(mContext).load(user.getImageURL())
                        .apply(options)
                        .into(image_profile);

                username.setText(user.getUsername());
                publisher.setText(user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void editPost (final String postid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Edit Post");

        final EditText editText = new EditText(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT ,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editText.setLayoutParams(lp);
        alertDialog.setView(editText);

        getText(postid , editText);

        alertDialog.setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        HashMap<String , Object> hashMap = new HashMap<>();
                        hashMap.put("description" , editText.getText().toString());

                        FirebaseDatabase.getInstance().getReference("Posts")
                                .child(postid).updateChildren(hashMap);

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

    private void getText(String postid , final EditText editText) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts")
                .child(postid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editText.setText(dataSnapshot.getValue(Post.class).getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
