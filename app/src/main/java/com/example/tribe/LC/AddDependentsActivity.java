package com.example.tribe.LC;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tribe.BottomNavigationActivity;
import com.example.tribe.Model.User;
import com.example.tribe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddDependentsActivity extends AppCompatActivity {

   private CircleImageView dependentProfileImage;
    private EditText dependentNameTitle, dependentDob, dependentAgeYears;
    private EditText editTextPentSize, editTextShoeSize, editTextTopSize, editTextLikes, editTextDislikes, editTextDiet, editTextAllergy;
    private Uri path;
    Button addProfileBtn;


    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;


    String tribeId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dependents);


        dependentProfileImage= findViewById(R.id.dependent_profile_item);
        dependentNameTitle = findViewById(R.id.child_name_txt);
        dependentDob = findViewById(R.id.date_birth_child);
        dependentAgeYears = findViewById(R.id.years_old_child);
        editTextPentSize = findViewById(R.id.pent_size_edit_text);
        editTextShoeSize = findViewById(R.id.shoe_size_edit_text);
        editTextTopSize = findViewById(R.id.top_size_edit_text);
        editTextLikes = findViewById(R.id.likes_edit_text);
        editTextDislikes = findViewById(R.id.dislikes_edit_text);
        editTextDiet = findViewById(R.id.diet_edit_text);
        editTextAllergy = findViewById(R.id.allergy_edit_text);
        addProfileBtn = findViewById(R.id.add_profile_dependent);


        storage = FirebaseStorage.getInstance();





        dependentProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // open image from gallery and select image
                launcher.launch("image/*");

            }
        });


        addProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });







    }

    // Function for validating empty fields and correct link
    private void validate() {




        if(dependentNameTitle.getText().toString().isEmpty()){

            Toast.makeText(AddDependentsActivity.this, "Name is empty", Toast.LENGTH_SHORT).show();

        }
        else if (dependentDob.getText().toString().isEmpty()) {

            Toast.makeText(AddDependentsActivity.this, "Date of birth is empty", Toast.LENGTH_SHORT).show();
            
        } else if (dependentAgeYears.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Age is empty", Toast.LENGTH_SHORT).show();
      


        } else if (editTextPentSize.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Pent size is empty", Toast.LENGTH_SHORT).show();
            
        }
        else if (editTextShoeSize.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Shoe size is empty", Toast.LENGTH_SHORT).show();

        }
        else if (editTextTopSize.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Top size is empty", Toast.LENGTH_SHORT).show();

        }
        else if (editTextDiet.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Diet is empty", Toast.LENGTH_SHORT).show();

        }
        else if (editTextAllergy.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Allergy is empty", Toast.LENGTH_SHORT).show();

        }
        else if (editTextLikes.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Likes is empty", Toast.LENGTH_SHORT).show();

        }
        else if (editTextDislikes.getText().toString().isEmpty()) {
            Toast.makeText(AddDependentsActivity.this, "Dislikes is empty", Toast.LENGTH_SHORT).show();

        }

        else {

            uploadImage();

        }


    }

    private void uploadImage() {

        if (path != null) {

            final StorageReference reference = storage.getReference().child("images/" + UUID.randomUUID().toString());


            reference.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {


                            path = uri;

                           addDependentsToFirebase(path.toString());







                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {



                            Toast.makeText(AddDependentsActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(AddDependentsActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        } else {
         
            Toast.makeText(AddDependentsActivity.this, "Please Upload Image", Toast.LENGTH_SHORT).show();
        }
    }

    private void addDependentsToFirebase(String path) {


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.orderByChild("trybe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    String tribeId = user.getTrybe();



                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("dependents").child(tribeId);


                  String  dependentId = reference.push().getKey();


                    HashMap<String, Object> hashMap = new HashMap<>();

                    hashMap.put("profileImage", path);
                    hashMap.put("dob", dependentDob.getText().toString());
                    hashMap.put("name", dependentNameTitle.getText().toString());
                    hashMap.put("age", dependentAgeYears.getText().toString());
                    hashMap.put("diet", editTextDiet.getText().toString());
                    hashMap.put("pent", editTextPentSize.getText().toString());
                    hashMap.put("shoe", editTextShoeSize.getText().toString());
                    hashMap.put("top", editTextDislikes.getText().toString());
                    hashMap.put("likes", editTextLikes.getText().toString());
                    hashMap.put("dislikes", editTextDislikes.getText().toString());
                    hashMap.put("allergy", editTextAllergy.getText().toString());

                    reference.child(dependentId).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(AddDependentsActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    });






                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


























    }


    ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {


                    if (uri != null) {
                        dependentProfileImage.setImageURI(uri);
                        path = uri;
                    }


                }
            });





    
}