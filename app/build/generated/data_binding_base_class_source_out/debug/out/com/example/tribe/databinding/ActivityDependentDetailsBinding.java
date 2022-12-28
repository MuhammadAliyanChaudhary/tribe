// Generated by view binder compiler. Do not edit!
package com.example.tribe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class ActivityDependentDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView allergyEditText;

  @NonNull
  public final AppBarLayout bar;

  @NonNull
  public final TextView childNameTxtShow;

  @NonNull
  public final TextView dateBirthChildShow;

  @NonNull
  public final CircleImageView dependentProfileItem;

  @NonNull
  public final TextView dietEditText;

  @NonNull
  public final TextView dislikesEditTextShow;

  @NonNull
  public final ImageButton imageButton;

  @NonNull
  public final TextView likesEditTextShow;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final TextView measurementTxt;

  @NonNull
  public final TextView pentSizeEditTextShow;

  @NonNull
  public final TextView personalInformationTxt;

  @NonNull
  public final TextView shoeSizeEditTextShow;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView topSizeEditTextShow;

  @NonNull
  public final TextView username;

  @NonNull
  public final TextView yearsOldChildShow;

  private ActivityDependentDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView allergyEditText, @NonNull AppBarLayout bar,
      @NonNull TextView childNameTxtShow, @NonNull TextView dateBirthChildShow,
      @NonNull CircleImageView dependentProfileItem, @NonNull TextView dietEditText,
      @NonNull TextView dislikesEditTextShow, @NonNull ImageButton imageButton,
      @NonNull TextView likesEditTextShow, @NonNull LinearLayout linearLayout2,
      @NonNull LinearLayout linearLayout3, @NonNull LinearLayout linearLayout4,
      @NonNull TextView measurementTxt, @NonNull TextView pentSizeEditTextShow,
      @NonNull TextView personalInformationTxt, @NonNull TextView shoeSizeEditTextShow,
      @NonNull Toolbar toolbar, @NonNull TextView topSizeEditTextShow, @NonNull TextView username,
      @NonNull TextView yearsOldChildShow) {
    this.rootView = rootView;
    this.allergyEditText = allergyEditText;
    this.bar = bar;
    this.childNameTxtShow = childNameTxtShow;
    this.dateBirthChildShow = dateBirthChildShow;
    this.dependentProfileItem = dependentProfileItem;
    this.dietEditText = dietEditText;
    this.dislikesEditTextShow = dislikesEditTextShow;
    this.imageButton = imageButton;
    this.likesEditTextShow = likesEditTextShow;
    this.linearLayout2 = linearLayout2;
    this.linearLayout3 = linearLayout3;
    this.linearLayout4 = linearLayout4;
    this.measurementTxt = measurementTxt;
    this.pentSizeEditTextShow = pentSizeEditTextShow;
    this.personalInformationTxt = personalInformationTxt;
    this.shoeSizeEditTextShow = shoeSizeEditTextShow;
    this.toolbar = toolbar;
    this.topSizeEditTextShow = topSizeEditTextShow;
    this.username = username;
    this.yearsOldChildShow = yearsOldChildShow;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDependentDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDependentDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dependent_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDependentDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.allergy_edit_text;
      TextView allergyEditText = ViewBindings.findChildViewById(rootView, id);
      if (allergyEditText == null) {
        break missingId;
      }

      id = R.id.bar;
      AppBarLayout bar = ViewBindings.findChildViewById(rootView, id);
      if (bar == null) {
        break missingId;
      }

      id = R.id.child_name_txt_show;
      TextView childNameTxtShow = ViewBindings.findChildViewById(rootView, id);
      if (childNameTxtShow == null) {
        break missingId;
      }

      id = R.id.date_birth_child_show;
      TextView dateBirthChildShow = ViewBindings.findChildViewById(rootView, id);
      if (dateBirthChildShow == null) {
        break missingId;
      }

      id = R.id.dependent_profile_item;
      CircleImageView dependentProfileItem = ViewBindings.findChildViewById(rootView, id);
      if (dependentProfileItem == null) {
        break missingId;
      }

      id = R.id.diet_edit_text;
      TextView dietEditText = ViewBindings.findChildViewById(rootView, id);
      if (dietEditText == null) {
        break missingId;
      }

      id = R.id.dislikes_edit_text_show;
      TextView dislikesEditTextShow = ViewBindings.findChildViewById(rootView, id);
      if (dislikesEditTextShow == null) {
        break missingId;
      }

      id = R.id.imageButton;
      ImageButton imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      id = R.id.likes_edit_text_show;
      TextView likesEditTextShow = ViewBindings.findChildViewById(rootView, id);
      if (likesEditTextShow == null) {
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

      id = R.id.pent_size_edit_text_show;
      TextView pentSizeEditTextShow = ViewBindings.findChildViewById(rootView, id);
      if (pentSizeEditTextShow == null) {
        break missingId;
      }

      id = R.id.personal_information_txt;
      TextView personalInformationTxt = ViewBindings.findChildViewById(rootView, id);
      if (personalInformationTxt == null) {
        break missingId;
      }

      id = R.id.shoe_size_edit_text_show;
      TextView shoeSizeEditTextShow = ViewBindings.findChildViewById(rootView, id);
      if (shoeSizeEditTextShow == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.top_size_edit_text_show;
      TextView topSizeEditTextShow = ViewBindings.findChildViewById(rootView, id);
      if (topSizeEditTextShow == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      id = R.id.years_old_child_show;
      TextView yearsOldChildShow = ViewBindings.findChildViewById(rootView, id);
      if (yearsOldChildShow == null) {
        break missingId;
      }

      return new ActivityDependentDetailsBinding((ConstraintLayout) rootView, allergyEditText, bar,
          childNameTxtShow, dateBirthChildShow, dependentProfileItem, dietEditText,
          dislikesEditTextShow, imageButton, likesEditTextShow, linearLayout2, linearLayout3,
          linearLayout4, measurementTxt, pentSizeEditTextShow, personalInformationTxt,
          shoeSizeEditTextShow, toolbar, topSizeEditTextShow, username, yearsOldChildShow);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
