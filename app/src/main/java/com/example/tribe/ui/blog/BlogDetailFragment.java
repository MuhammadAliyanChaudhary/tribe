package com.example.tribe.ui.blog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tribe.R;


public class BlogDetailFragment extends Fragment {

    ImageButton backButton;
    TextView blogDetailTitle, blogDetailDescription;
    ImageView blogDetailImage;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blog_detail, container, false);


        backButton=view.findViewById(R.id.imageButton);
        blogDetailImage=view.findViewById(R.id.blog_detail_image);
        blogDetailTitle=view.findViewById(R.id.blog_detail_title);
        blogDetailDescription=view.findViewById(R.id.blog_detail_description);





        String imageLink = getActivity().getIntent().getStringExtra("image");
        blogDetailTitle.setText(getActivity().getIntent().getStringExtra("Title"));
        blogDetailDescription.setText(Html.fromHtml(getActivity().getIntent().getStringExtra("Description")));



        Glide.with(getContext())
                .load(imageLink) // image url
                .placeholder(R.drawable.ic_image_24) // any placeholder to load at start
                .error(R.drawable.ic_image_24)  // any image in case of error
                .override(250, 250) // resizing
                .centerCrop()
                .into(blogDetailImage);









        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null)
                    getActivity().onBackPressed();
            }
        });






        return view;
    }
}