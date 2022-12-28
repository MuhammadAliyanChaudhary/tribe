package com.example.tribe.ui.memories;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tribe.Adapters.MyFotoAdapter;
import com.example.tribe.LC.PostActivity;
import com.example.tribe.Model.Post;
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
import java.util.Collections;
import java.util.List;


public class Memories extends Fragment {
    private ImageView image_profile;

    private TextView fullname;
    private TextView bio;
    private TextView username;
    ImageButton backButton;
    ImageButton addImages;
    private RecyclerView recyclerView;
    private MyFotoAdapter myFotoAdapter;
    private List<Post> postList;

    private List<String> mySaves;
    private RecyclerView recyclerView_saves;
    private MyFotoAdapter myFotoAdapter_saves;
    private List<Post> postList_saves;
    ProgressDialog progressDialog ;
    private FirebaseUser firebaseUser;
    String profileid;



    public Memories() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_memories, container, false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences prefs = getContext().getSharedPreferences("PREFS" , Context.MODE_PRIVATE);
        profileid = prefs.getString("profileid" , "none");

        progressDialog= new ProgressDialog(getActivity(),R.style.DialogStyle);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

        image_profile = view.findViewById(R.id.image_profile);
        fullname = view.findViewById(R.id.fullname);
        bio = view.findViewById(R.id.bio);
        username = view.findViewById(R.id.username);
        backButton=view.findViewById(R.id.imageButton);
        addImages=view.findViewById(R.id.addPhotos);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext() , 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        postList = new ArrayList<>();
        myFotoAdapter = new MyFotoAdapter(getContext() , postList);
        recyclerView.setAdapter(myFotoAdapter);

        recyclerView_saves = view.findViewById(R.id.recycler_view_save);
        recyclerView_saves.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new GridLayoutManager(getContext() , 3);
        recyclerView_saves.setLayoutManager(linearLayoutManager1);
        postList_saves = new ArrayList<>();
        myFotoAdapter_saves = new MyFotoAdapter(getContext() , postList_saves);
        recyclerView_saves.setAdapter(myFotoAdapter_saves);

        recyclerView.setVisibility(View.VISIBLE);
        recyclerView_saves.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null)
                getActivity().onBackPressed();
            }
        });
        addImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity() , PostActivity.class));
            }
        });

        userInfo();
        myFotos();


        return view;
    }

    private void userInfo() {
        progressDialog.show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(profileid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getContext() == null){
                    return;
                }

                if(progressDialog!=null) progressDialog.dismiss();
                User user = dataSnapshot.getValue(User.class);
                Log.d("TAG", "onDataChange: "+dataSnapshot.toString());
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.default_image)
                        .error(R.drawable.default_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH);

                Glide.with(getActivity()).load(user.getImageURL())
                        .apply(options)
                        .into(image_profile);
                username.setText(user.getUsername());
                fullname.setText(user.getUsername());
                bio.setText(user.getBio());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if(progressDialog!=null) progressDialog.dismiss();

            }
        });
    }

    private void myFotos() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("userPosts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Post post = snapshot.getValue(Post.class);
                        postList.add(post);

                }

                Collections.reverse(postList);
                myFotoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}