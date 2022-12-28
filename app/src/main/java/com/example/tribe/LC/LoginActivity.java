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

import com.example.tribe.CustomDialog;
import com.example.tribe.MainActivity;
import com.example.tribe.R;
import com.example.tribe.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

public class LoginActivity extends AppCompatActivity {

    private EditText edit_text_email_or_username, edit_text_password;

    private TextView forgot_your_password,text_view_create_account;
    private Button button_login;
    private Utils utils;
    FirebaseDatabase db;
    private boolean isIdExist;
    CustomDialog customDialog;
    Dialog
            dialog;


    private DocumentReference documentReference;
    private boolean isExist;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        utils = new Utils(this);
        isIdExist = true;

        edit_text_email_or_username= findViewById(R.id.edit_text_email_or_username);
        edit_text_password= findViewById(R.id.edit_text_password);
        forgot_your_password= findViewById(R.id.forgot_your_password);
        text_view_create_account= findViewById(R.id.text_view_create_account);
        button_login= findViewById(R.id.button_login);


        db = FirebaseDatabase.getInstance();
        utils = new Utils(this);
        mAuth = FirebaseAuth.getInstance();
        isExist = false;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);



        forgot_your_password.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class)));

        text_view_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));


            }
        });


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                validate();


            }
        });






    }

    private void validate() {

        dialog.show();
        if(edit_text_email_or_username.getText().toString().isEmpty()){

            edit_text_email_or_username.setError("Username is Empty");
            edit_text_email_or_username.requestFocus();
            dialog.dismiss();

        }
        else if(edit_text_password.getText().toString().isEmpty()){
            edit_text_password.setError("Password is Empty");
            edit_text_password.requestFocus();
            dialog.dismiss();

        }

        else {

            authentication(edit_text_email_or_username.getText().toString(),edit_text_password.getText().toString());

        }
    }

    private void authentication(String email, String pass) {


        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {

            if(task.isSuccessful()){

                user = mAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                utils.putToken(uid);
                dialog.dismiss();
                SharedPreferences.Editor editor = getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
                editor.putString("profileid", user.getUid());
                editor.apply();



                startActivity(new Intent(LoginActivity.this, EmailVerificationActivity.class));
                finish();


            }else{
                dialog.dismiss();

                Toast.makeText(LoginActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();


            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });






     /*   LottieDialog lottieDialog = new LottieDialog(this);
        lottieDialog.show();



        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if (documentSnapshot.getString("Username").equals(str_name)) {


                                    if (documentSnapshot.getString("Password").equals(str_password)) {

                                        String document_id_user = documentSnapshot.getString("Document ID");
                                        utils.putToken(document_id_user);

                                        lottieDialog.dismiss();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        edit_text_password.setError("Invalid password");
                                        lottieDialog.dismiss();
                                    }
                                    isIdExist=true;

                                }
                                else{

                                    isIdExist=false;

                                }


                            }
                            if(!isIdExist){ // false
                                edit_text_email_or_username.setError("Invalid username");
                                lottieDialog.dismiss();
                            }

                        }
                        *//*else {
                            edit_text_email_or_username.setError("Invalid username");
                        }*//*
                    }

                });*/

    }

}