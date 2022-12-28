package com.example.tribe.ui.Community;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tribe.Adapters.PostAdapter;
import com.example.tribe.Model.Post;
import com.example.tribe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Community extends Fragment {


    CardView postButton;
    EditText postdetails;
    ImageButton backButton;

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_community, container, false);
        postButton=view.findViewById(R.id.postButton);
        postdetails=view.findViewById(R.id.postText);
        backButton=view.findViewById(R.id.imageButton);
        recyclerView = view.findViewById(R.id.all_posts);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(getContext() , postList);
        recyclerView.setAdapter(postAdapter);
        progressDialog= new ProgressDialog(getActivity(),R.style.DialogStyle);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null)
                getActivity().onBackPressed();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(postdetails.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Type something to post", Toast.LENGTH_SHORT).show();
                }
                else{
                    postdetails.clearFocus();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");

                    String postid = reference.push().getKey();

                    HashMap<String , Object> hashMap = new HashMap<>();
                    hashMap.put("postid" , postid);
                    hashMap.put("description" , postdetails.getText().toString().trim());
                    hashMap.put("likesCount" , "0");
                    hashMap.put("publisher" , FirebaseAuth.getInstance().getCurrentUser().getUid());
                    reference.child(postid).setValue(hashMap);
                    postdetails.setText("");
                }
            }
        });


        readPosts();



        return view;
    }


    private void readPosts () {
        if(progressDialog!=null) progressDialog.show();

         FirebaseDatabase.getInstance().getReference("Posts").orderByChild("likesCount").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                if(progressDialog!=null) progressDialog.dismiss();


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = snapshot.getValue(Post.class);

                            postList.add(post);
                }

                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if(progressDialog!=null) progressDialog.dismiss();

            }
        });
    }

}