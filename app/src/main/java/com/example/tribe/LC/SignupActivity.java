package com.example.tribe.LC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tribe.MainActivity;
import com.example.tribe.Model.User;
import com.example.tribe.R;
import com.example.tribe.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText edit_username_signup, edit_email_signup, edit_pass_signup, edit_confirm_pass_signup;
    private Button button_create_account_signup;
    private TextView textview_terms_conditions, textview_privacy_policy;
    private Utils utils;
    private boolean isExist;
    ProgressDialog mDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        edit_username_signup = findViewById(R.id.edit_username_signup);
        edit_email_signup = findViewById(R.id.edit_email_signup);
        edit_pass_signup = findViewById(R.id.edit_pass_signup);
        button_create_account_signup = findViewById(R.id.button_create_account_signup);
        textview_privacy_policy = findViewById(R.id.textview_privacy_policy);
        textview_terms_conditions = findViewById(R.id.textview_terms_conditions);


        edit_confirm_pass_signup = findViewById(R.id.edit_confirm_pass_signup);


        utils = new Utils(this);
        mAuth = FirebaseAuth.getInstance();
        isExist = false;

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);


        // On click listeners on buttons started
        button_create_account_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

            }
        });

    }

    // Validite method to check the if user have left fields empty

    private void validate() {

        dialog.show();

        String name = edit_username_signup.getText().toString().trim();
        String email = edit_email_signup.getText().toString().trim();
        String pass = edit_pass_signup.getText().toString().trim();

        if (name.isEmpty()) {
            edit_username_signup.setError("Username must contain value");
            edit_username_signup.requestFocus();
            dialog.dismiss();
        } else if (email.isEmpty()) {
            edit_email_signup.setError("Not a valid email");
            edit_email_signup.requestFocus();
            dialog.dismiss();
        } else if (pass.isEmpty()) {
            edit_pass_signup.setError("Password must contain a value");
            edit_pass_signup.requestFocus();
            dialog.dismiss();
        } else if (pass.length() < 6) {
            edit_pass_signup.setError("Password must be greater then 6 digit");
            edit_pass_signup.requestFocus();
            dialog.dismiss();
        }

        if (edit_confirm_pass_signup.getText().toString().isEmpty()) {
            edit_confirm_pass_signup.setError("Enter password again");
            edit_confirm_pass_signup.requestFocus();
            dialog.dismiss();
        } else if (!edit_confirm_pass_signup.getText().toString().equals(edit_pass_signup.getText().toString())) {
            edit_confirm_pass_signup.setError("Password do not match");
            edit_confirm_pass_signup.requestFocus();
            dialog.dismiss();
        } else {
            createUser(email, pass);

        }

    }

    private void createUser(String email, String pass) {


        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    user = mAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();

                    dataUpload(email, uid);


                } else {

                    dialog.dismiss();

                    Toast.makeText(SignupActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                dialog.dismiss();

                Toast.makeText(SignupActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void dataUpload(String email, String uid) {



        String Username = edit_username_signup.getText().toString();



        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", email);
        userMap.put("username", Username);
        userMap.put("imageURL", "default");
        userMap.put("status", "offline");
        userMap.put("id", uid);
        userMap.put("search", Username.toLowerCase());
        userMap.put("bio", Username.toLowerCase());
        userMap.put("trybe", "notadded");
        SharedPreferences.Editor editor = getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
        editor.putString("profileid", uid);
        editor.apply();


        FirebaseDatabase.getInstance().getReference("Users").child(uid).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    utils.putToken(uid);
                    dialog.dismiss();
                    Intent intent = new Intent(SignupActivity.this, EmailVerificationActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    dialog.dismiss();

                    Toast.makeText(SignupActivity.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(SignupActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
 /*   @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
            finish();
        }


    }*/

    }


}