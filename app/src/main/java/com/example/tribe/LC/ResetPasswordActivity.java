package com.example.tribe.LC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tribe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPasswordActivity extends AppCompatActivity {

    private Button send_link_btn;
    private EditText edit_email_input;
    private ImageButton back_arrow_btn;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        back_arrow_btn = findViewById(R.id.back_arrow_btn);
        send_link_btn = findViewById(R.id.send_link_btn);
        edit_email_input = findViewById(R.id.edit_email_reset_pass);



        firebaseAuth = FirebaseAuth.getInstance();
        back_arrow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });

        send_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edit_email_input.getText().toString().trim();

                sendLink(email);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                        finish();
                    }
                }, 2000);





            }
        });




    }

    private void sendLink(String email){



        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(ResetPasswordActivity.this, "Reset Password link send to your email", Toast.LENGTH_SHORT).show();





                }else{

                    Toast.makeText(ResetPasswordActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ResetPasswordActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}