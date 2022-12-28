package com.example.tribe.LC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tribe.R;
import com.example.tribe.ui.learning.Learning;
import com.example.tribe.ui.menu.MenuFragment;

public class BlogDetailActivity extends AppCompatActivity {


    ImageButton backButton;
    TextView blogDetailTitle, blogDetailDescription;
    ImageView blogDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);



        backButton=findViewById(R.id.imageButton);
        blogDetailImage=findViewById(R.id.blog_detail_image);
        blogDetailTitle=findViewById(R.id.blog_detail_title);
        blogDetailDescription=findViewById(R.id.blog_detail_description);





        String imageLink = getIntent().getStringExtra("image");
        blogDetailTitle.setText(getIntent().getStringExtra("Title"));
        blogDetailDescription.setText(Html.fromHtml(getIntent().getStringExtra("description")));



        Glide.with(getApplicationContext())
                .load(imageLink) // image url
                .placeholder(R.drawable.ic_image_24) // any placeholder to load at start
                .error(R.drawable.ic_image_24)  // any image in case of error
                .override(250, 250) // resizing
                .centerCrop()
                .into(blogDetailImage);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });




    }







}