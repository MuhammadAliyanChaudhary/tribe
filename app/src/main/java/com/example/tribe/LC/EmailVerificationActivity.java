package com.example.tribe.LC;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tribe.BottomNavigationActivity;
import com.example.tribe.R;
import com.example.tribe.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerificationActivity extends AppCompatActivity {

    private static final String TAG = EmailVerificationActivity.class.getSimpleName();

    private Button sendVerifyLink, verifyEmail, logoutBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);


        sendVerifyLink = findViewById(R.id.send_verification_link);
        verifyEmail = findViewById(R.id.verify_btn);
        logoutBtn = findViewById(R.id.logout_btn_email_verification);

        mAuth = FirebaseAuth.getInstance();

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);


        if (mAuth.getCurrentUser().isEmailVerified()) {

            mAuth.getCurrentUser().reload();
            new Utils(this).SetShowOnboard(false);
            startActivity(new Intent(EmailVerificationActivity.this, BottomNavigationActivity.class));
            finish();

        }


        sendVerifyLink.setOnClickListener(view -> {


            dialog.show();
            if (!mAuth.getCurrentUser().isEmailVerified()) {
                mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(unused -> {
                    Log.d(TAG, "onSuccess: email sent");

                    dialog.dismiss();
                    Toast.makeText(EmailVerificationActivity.this, "We have send the link to your registered email address please check it", Toast.LENGTH_SHORT).show();

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(EmailVerificationActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }


        });


        verifyEmail.setOnClickListener(view -> {

            dialog.show();
            mAuth = FirebaseAuth.getInstance();
            mAuth.getCurrentUser().reload();

            if (!mAuth.getCurrentUser().isEmailVerified()) {
                mAuth.getCurrentUser().reload();

                if (mAuth.getCurrentUser().isEmailVerified()) {
                    new Utils(EmailVerificationActivity.this).SetShowOnboard(false);

                    Toast.makeText(EmailVerificationActivity.this, "Your Email is verified now", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmailVerificationActivity.this, BottomNavigationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                    dialog.dismiss();
                    finish();

                } else {
                    dialog.dismiss();
                    Toast.makeText(EmailVerificationActivity.this, "Email not Verified", Toast.LENGTH_SHORT).show();
                }

            } else {
                dialog.dismiss();
                Toast.makeText(EmailVerificationActivity.this, "Your Email is verified now", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EmailVerificationActivity.this, BottomNavigationActivity.class));

                finish();
            }


        });


        logoutBtn.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(EmailVerificationActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EmailVerificationActivity.this, LoginActivity.class));
            finish();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mAuth.getCurrentUser().reload();

        if (mAuth.getCurrentUser().isEmailVerified()) {

            startActivity(new Intent(EmailVerificationActivity.this, BottomNavigationActivity.class));
            finish();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        mAuth.getCurrentUser().reload();
        if (mAuth.getCurrentUser().isEmailVerified()) {

            startActivity(new Intent(EmailVerificationActivity.this, BottomNavigationActivity.class));
            finish();

        }
    }
}