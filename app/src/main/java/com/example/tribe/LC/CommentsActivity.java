package com.example.tribe.LC;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tribe.Adapters.CommentAdapter;
import com.example.tribe.Model.Comment;
import com.example.tribe.Model.User;
import com.example.tribe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private CommentAdapter commentAdapter;
    private List<Comment> commentList;

    private EditText addcomment;
    private ImageView image_profile;

    private String postid;
    private String publisherid;

    FirebaseUser firebaseUser;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        context=CommentsActivity.this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);}
        toolbar.setNavigationOnClickListener(v -> finish());

        Intent intent = getIntent();
        postid = intent.getStringExtra("postid");
        publisherid = intent.getStringExtra("publisherid");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(this , commentList , postid);
        recyclerView.setAdapter(commentAdapter);
        
        addcomment = findViewById(R.id.add_comment);
        image_profile = findViewById(R.id.image_profile);
        TextView post = findViewById(R.id.post);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        
        post.setOnClickListener(v -> {
            if (TextUtils.isEmpty(addcomment.getText().toString())){
                if(context!=null)
                Toast.makeText(context, "No comment added!", Toast.LENGTH_SHORT).show();
            } else {
                addComment();
            }
        });

        getImage();
        readComments();
        
    }

    private void addComment() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Comments").child(postid);

        String commentid = reference.push().getKey();

        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("comment" , addcomment.getText().toString());
        hashMap.put("publisher" , firebaseUser.getUid());
        hashMap.put("commentid" , commentid);
        if(commentid!=null)
        reference.child(commentid).setValue(hashMap);
        addNotifications();
        addcomment.setText("");

    }

    private void addNotifications() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications").child(publisherid);

        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("userid" , firebaseUser.getUid());
        hashMap.put("text" , "commented: " + addcomment.getText().toString());
        hashMap.put("postid" , postid);
        hashMap.put("ispost" , true);

        reference.push().setValue(hashMap);
    }

    private void getImage() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

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

                if(user!=null&context!=null)
                Glide.with(context).load(user.getImageURL())
                        .apply(options)
                        .into(image_profile);            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void readComments () {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Comments").child(postid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Comment comment = snapshot.getValue(Comment.class);
                    commentList.add(comment);
                }
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        context=null;
        super.onDestroy();
    }
}
