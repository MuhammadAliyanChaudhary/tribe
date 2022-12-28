// Generated by view binder compiler. Do not edit!
package com.example.tribe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tribe.R;
import com.google.android.material.appbar.AppBarLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddDependentsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button addProfileDependent;

  @NonNull
  public final EditText allergyEditText;

  @NonNull
  public final AppBarLayout bar;

  @NonNull
  public final EditText childNameTxt;

  @NonNull
  public final EditText dateBirthChild;

  @NonNull
  public final CircleImageView dependentProfileItem;

  @NonNull
  public final EditText dietEditText;

  @NonNull
  public final EditText dislikesEditText;

  @NonNull
  public final ImageButton imageButton;

  @NonNull
  public final EditText likesEditText;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final TextView measurementTxt;

  @NonNull
  public final EditText pentSizeEditText;

  @NonNull
  public final TextView personalInformationTxt;

  @NonNull
  public final EditText shoeSizeEditText;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final EditText topSizeEditText;

  @NonNull
  public final TextView uploadTxt;

  @NonNull
  public final TextView username;

  @NonNull
  public final EditText yearsOldChild;

  private ActivityAddDependentsBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button addProfileDependent, @NonNull EditText allergyEditText,
      @NonNull AppBarLayout bar, @NonNull EditText childNameTxt, @NonNull EditText dateBirthChild,
      @NonNull CircleImageView dependentProfileItem, @NonNull EditText dietEditText,
      @NonNull EditText dislikesEditText, @NonNull ImageButton imageButton,
      @NonNull EditText likesEditText, @NonNull LinearLayout linearLayout2,
      @NonNull LinearLayout linearLayout3, @NonNull LinearLayout linearLayout4,
      @NonNull TextView measurementTxt, @NonNull EditText pentSizeEditText,
      @NonNull TextView personalInformationTxt, @NonNull EditText shoeSizeEditText,
      @NonNull Toolbar toolbar, @NonNull EditText topSizeEditText, @NonNull TextView uploadTxt,
      @NonNull TextView username, @NonNull EditText yearsOldChild) {
    this.rootView = rootView;
    this.addProfileDependent = addProfileDependent;
    this.allergyEditText = allergyEditText;
    this.bar = bar;
    this.childNameTxt = childNameTxt;
    this.dateBirthChild = dateBirthChild;
    this.dependentProfileItem = dependentProfileItem;
    this.dietEditText = dietEditText;
    this.dislikesEditText = dislikesEditText;
    this.imageButton = imageButton;
    this.likesEditText = likesEditText;
    this.linearLayout2 = linearLayout2;
    this.linearLayout3 = linearLayout3;
    this.linearLayout4 = linearLayout4;
    this.measurementTxt = measurementTxt;
    this.pentSizeEditText = pentSizeEditText;
    this.personalInformationTxt = personalInformationTxt;
    this.shoeSizeEditText = shoeSizeEditText;
    this.toolbar = toolbar;
    this.topSizeEditText = topSizeEditText;
    this.uploadTxt = uploadTxt;
    this.username = username;
    this.yearsOldChild = yearsOldChild;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddDependentsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddDependentsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_dependents, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddDependentsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_profile_dependent;
      Button addProfileDependent = ViewBindings.findChildViewById(rootView, id);
      if (addProfileDependent == null) {
        break missingId;
      }

      id = R.id.allergy_edit_text;
      EditText allergyEditText = ViewBindings.findChildViewById(rootView, id);
      if (allergyEditText == null) {
        break missingId;
      }

      id = R.id.bar;
      AppBarLayout bar = ViewBindings.findChildViewById(rootView, id);
      if (bar == null) {
        break missingId;
      }

      id = R.id.child_name_txt;
      EditText childNameTxt = ViewBindings.findChildViewById(rootView, id);
      if (childNameTxt == null) {
        break missingId;
      }

      id = R.id.date_birth_child;
      EditText dateBirthChild = ViewBindings.findChildViewById(rootView, id);
      if (dateBirthChild == null) {
        break missingId;
      }

      id = R.id.dependent_profile_item;
      CircleImageView dependentProfileItem = ViewBindings.findChildViewById(rootView, id);
      if (dependentProfileItem == null) {
        break missingId;
      }

      id = R.id.diet_edit_text;
      EditText dietEditText = ViewBindings.findChildViewById(rootView, id);
      if (dietEditText == null) {
        break missingId;
      }

      id = R.id.dislikes_edit_text;
      EditText dislikesEditText = ViewBindings.findChildViewById(rootView, id);
      if (dislikesEditText == null) {
        break missingId;
      }

      id = R.id.imageButton;
      ImageButton imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      id = R.id.likes_edit_text;
      EditText likesEditText = ViewBindings.findChildViewById(rootView, id);
      if (likesEditText == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      id = R.id.measurement_txt;
      TextView measurementTxt = ViewBindings.findChildViewById(rootView, id);
      if (measurementTxt == null) {
        break missingId;
      }

      id = R.id.pent_size_edit_text;
      EditText pentSizeEditText = ViewBindings.findChildViewById(rootView, id);
      if (pentSizeEditText == null) {
        break missingId;
      }

      id = R.id.personal_information_txt;
      TextView personalInformationTxt = ViewBindings.findChildViewById(rootView, id);
      if (personalInformationTxt == null) {
        break missingId;
      }

      id = R.id.shoe_size_edit_text;
      EditText shoeSizeEditText = ViewBindings.findChildViewById(rootView, id);
      if (shoeSizeEditText == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.top_size_edit_text;
      EditText topSizeEditText = ViewBindings.findChildViewById(rootView, id);
      if (topSizeEditText == null) {
        break missingId;
      }

      id = R.id.upload_txt;
      TextView uploadTxt = ViewBindings.findChildViewById(rootView, id);
      if (uploadTxt == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      id = R.id.years_old_child;
      EditText yearsOldChild = ViewBindings.findChildViewById(rootView, id);
      if (yearsOldChild == null) {
        break missingId;
      }

      return new ActivityAddDependentsBinding((ConstraintLayout) rootView, addProfileDependent,
          allergyEditText, bar, childNameTxt, dateBirthChild, dependentProfileItem, dietEditText,
          dislikesEditText, imageButton, likesEditText, linearLayout2, linearLayout3, linearLayout4,
          measurementTxt, pentSizeEditText, personalInformationTxt, shoeSizeEditText, toolbar,
          topSizeEditText, uploadTxt, username, yearsOldChild);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}