package com.example.tribe.ui.learning;

import android.os.Bundle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tribe.Adapters.BlogAdapter;
import com.example.tribe.Model.Blog;
import com.example.tribe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Learning extends Fragment {

    ImageButton backButton;
    RecyclerView blogRecyclerView;
    List<Blog> blogList;
    private FirebaseFirestore db;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning, container, false);


        backButton=view.findViewById(R.id.imageButton);
        blogRecyclerView = view.findViewById(R.id.blog_recycler_view);

        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);

        db = FirebaseFirestore.getInstance();



       blogRecyclerView.setHasFixedSize(true);
        blogRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        blogList = new ArrayList<>();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                blogList.clear();

                fetchBlogs();
                swipeRefreshLayout.setRefreshing(false);

            }
        });


        fetchBlogs();











        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null)
                    getActivity().onBackPressed();
            }
        });



        return view;
    }


   private void fetchBlogs(){


       swipeRefreshLayout.setRefreshing(true);


       db.collection("LearningBlog").orderBy("CreatedDate", Query.Direction.DESCENDING).get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {

                       if (task.isSuccessful()) {

                           for (QueryDocumentSnapshot document : task.getResult()) {

//                                Toast.makeText(getContext(), document.getString("profile"), Toast.LENGTH_SHORT).show();

                               Blog blog = new Blog(

                                       document.getString("Title"),
                                       document.getString("Image"),
                                       document.getString("Description"),
                                       document.getString("Date"),
                                       document.getString("ReadTime")
                               );


                               blogList.add(blog);


                           }

                           BlogAdapter blogAdapter = new BlogAdapter(getActivity().getApplicationContext(), blogList);
                           blogRecyclerView.setAdapter(blogAdapter);
                           blogAdapter.notifyDataSetChanged();
                           swipeRefreshLayout.setRefreshing(false);
//


                       } else {
                           swipeRefreshLayout.setRefreshing(false);
//

                           Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                       }
                   }
               });








}



}
