package com.example.tribe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tribe.LC.BlogDetailActivity;
import com.example.tribe.Model.Blog;
import com.example.tribe.R;
import com.example.tribe.ui.blog.BlogDetailFragment;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    Context context;
    private List<Blog> blogs;

    public BlogAdapter(Context context, List<Blog> blogs) {
        this.context = context;
        this.blogs = blogs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.blog_item, parent, false);



        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Blog blog = blogs.get(position);



        Glide.with(context)
                .load(blog.getBlogImage()) // image url
                .placeholder(R.drawable.ic_image_24) // any placeholder to load at start
                .error(R.drawable.ic_launcher_foreground)  // any image in case of error
                .override(100, 100) // resizing
                .centerCrop()
                .into(holder.blogImage);

        holder.blogTitle.setText(blog.getBlogTitle());
        holder.blogDescription.setText(blog.getBlogDescription());
        holder.blogReadTime.setText(blog.getBlogReadTime());
        holder.blogDate.setText(blog.getBlogDate());


        holder.blogList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(context, BlogDetailActivity.class);
                intent.putExtra("image",blog.getBlogImage());
                intent.putExtra("Title",blog.getBlogTitle());
                intent.putExtra("description",blog.getBlogDescription());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);



            }
        });




    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView blogTitle, blogDescription, blogDate, blogReadTime;
        public ImageView blogImage;
        public LinearLayout blogList;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            blogTitle = itemView.findViewById(R.id.blog_title);
            blogDescription = itemView.findViewById(R.id.blog_description);
            blogDate = itemView.findViewById(R.id.blog_upload_date);
            blogReadTime = itemView.findViewById(R.id.blog_read_time);
            blogImage = itemView.findViewById(R.id.blog_thumbnail);
            blogList = itemView.findViewById(R.id.blog_list_item);
        }
    }


}
