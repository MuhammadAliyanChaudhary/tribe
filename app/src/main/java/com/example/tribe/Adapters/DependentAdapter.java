package com.example.tribe.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tribe.LC.BlogDetailActivity;
import com.example.tribe.LC.DependentDetailsActivity;
import com.example.tribe.Model.DependentModel;
import com.example.tribe.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DependentAdapter extends RecyclerView.Adapter<DependentAdapter.ViewHolder> {

    Context context;
    List<DependentModel> dependentList;

    public DependentAdapter(Context context, List<DependentModel> dependentList) {
        this.context = context;
        this.dependentList = dependentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dependent, parent, false);

        return new DependentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DependentModel dependentModel = dependentList.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        String imageUrl = dependentModel.getProfileImage();


        Glide.with(context).load(imageUrl)
                .apply(options)
                .into(holder.titleImage);

        holder.titleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(context, DependentDetailsActivity.class);
                intent.putExtra("image",dependentModel.getProfileImage());
                intent.putExtra("name",dependentModel.getName());
                intent.putExtra("dob",dependentModel.getDob());
                intent.putExtra("years",dependentModel.getAge());
                intent.putExtra("diet",dependentModel.getDiet());
                intent.putExtra("shoe",dependentModel.getShoe());
                intent.putExtra("pent",dependentModel.getPent());
                intent.putExtra("top",dependentModel.getTop());
                intent.putExtra("like",dependentModel.getLikes());
                intent.putExtra("dislike",dependentModel.getDislikes());
                intent.putExtra("allergy",dependentModel.getAllergy());


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dependentList.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView titleImage;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleImage = itemView.findViewById(R.id.dependent_profile_item);
        }
    }

}
