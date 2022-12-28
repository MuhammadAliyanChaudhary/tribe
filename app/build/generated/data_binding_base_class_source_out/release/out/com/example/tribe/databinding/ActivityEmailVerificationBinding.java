// Generated by view binder compiler. Do not edit!
package com.example.tribe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tribe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEmailVerificationBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button logoutBtnEmailVerification;

  @NonNull
  public final Button sendVerificationLink;

  @NonNull
  public final Button verifyBtn;

  private ActivityEmailVerificationBinding(@NonNull LinearLayout rootView,
      @NonNull Button logoutBtnEmailVerification, @NonNull Button sendVerificationLink,
      @NonNull Button verifyBtn) {
    this.rootView = rootView;
    this.logoutBtnEmailVerification = logoutBtnEmailVerification;
    this.sendVerificationLink = sendVerificationLink;
    this.verifyBtn = verifyBtn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEmailVerificationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEmailVerificationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_email_verification, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEmailVerificationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.logout_btn_email_verification;
      Button logoutBtnEmailVerification = ViewBindings.findChildViewById(rootView, id);
      if (logoutBtnEmailVerification == null) {
        break missingId;
      }

      id = R.id.send_verification_link;
      Button sendVerificationLink = ViewBindings.findChildViewById(rootView, id);
      if (sendVerificationLink == null) {
        break missingId;
      }

      id = R.id.verify_btn;
      Button verifyBtn = ViewBindings.findChildViewById(rootView, id);
      if (verifyBtn == null) {
        break missingId;
      }

      return new ActivityEmailVerificationBinding((LinearLayout) rootView,
          logoutBtnEmailVerification, sendVerificationLink, verifyBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}