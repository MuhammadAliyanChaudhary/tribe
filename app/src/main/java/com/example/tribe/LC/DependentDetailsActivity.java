package com.example.tribe.LC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tribe.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DependentDetailsActivity extends AppCompatActivity {



    private CircleImageView dependentProfileImage;
    private TextView dependentNameTitle, dependentDob, dependentAgeYears;
    private TextView editTextPentSize, editTextShoeSize, editTextTopSize, editTextLikes, editTextDislikes, editTextDiet, editTextAllergy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependent_details);


        dependentNameTitle = findViewById(R.id.child_name_txt_show);
        dependentAgeYears = findViewById(R.id.years_old_child_show);
        dependentDob = findViewById(R.id.date_birth_child_show);
        dependentProfileImage = findViewById(R.id.dependent_profile_item);
        editTextPentSize = findViewById(R.id.pent_size_edit_text_show);
        editTextShoeSize = findViewById(R.id.shoe_size_edit_text_show);
        editTextTopSize = findViewById(R.id.top_size_edit_text_show);
        editTextLikes = findViewById(R.id.likes_edit_text_show);
        editTextDislikes = findViewById(R.id.dislikes_edit_text_show);
        editTextDiet = findViewById(R.id.diet_edit_text);
        editTextAllergy = findViewById(R.id.allergy_edit_text);


        String imgLink = getIntent().getStringExtra("image");

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


        Glide.with(getApplicationContext()).load(imgLink)
                .apply(options)
                .into(dependentProfileImage);


        dependentNameTitle.setText(getIntent().getStringExtra("name"));
        dependentAgeYears.setText(getIntent().getStringExtra("years"));
        dependentDob.setText(getIntent().getStringExtra("dob"));
        editTextPentSize.setText(getIntent().getStringExtra("pent"));
        editTextShoeSize.setText(getIntent().getStringExtra("shoe"));
        editTextTopSize.setText(getIntent().getStringExtra("top"));
        editTextDiet.setText(getIntent().getStringExtra("diet"));
        editTextAllergy.setText(getIntent().getStringExtra("allergy"));
        editTextLikes.setText(getIntent().getStringExtra("like"));
        editTextDislikes.setText(getIntent().getStringExtra("dislike"));









    }
}