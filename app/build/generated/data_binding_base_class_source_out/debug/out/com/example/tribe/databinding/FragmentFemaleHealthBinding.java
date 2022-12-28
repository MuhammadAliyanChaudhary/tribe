// Generated by view binder compiler. Do not edit!
package com.example.tribe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.example.tribe.R;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFemaleHealthBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LottieAnimationView animationView;

  @NonNull
  public final CardView babiesToddlersWeightBtn;

  @NonNull
  public final AppBarLayout bar;

  @NonNull
  public final CardView childrenWeightBtn;

  @NonNull
  public final TextView childrenWeightBtntxt;

  @NonNull
  public final ImageButton imageButton;

  @NonNull
  public final CardView teenageWeightBtn;

  @NonNull
  public final TextView teenageWeightBtntxt;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final CardView toddlersWeightBtn;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView username;

  private FragmentFemaleHealthBinding(@NonNull ConstraintLayout rootView,
      @NonNull LottieAnimationView animationView, @NonNull CardView babiesToddlersWeightBtn,
      @NonNull AppBarLayout bar, @NonNull CardView childrenWeightBtn,
      @NonNull TextView childrenWeightBtntxt, @NonNull ImageButton imageButton,
      @NonNull CardView teenageWeightBtn, @NonNull TextView teenageWeightBtntxt,
      @NonNull TextView textView4, @NonNull TextView textView6, @NonNull CardView toddlersWeightBtn,
      @NonNull Toolbar toolbar, @NonNull TextView username) {
    this.rootView = rootView;
    this.animationView = animationView;
    this.babiesToddlersWeightBtn = babiesToddlersWeightBtn;
    this.bar = bar;
    this.childrenWeightBtn = childrenWeightBtn;
    this.childrenWeightBtntxt = childrenWeightBtntxt;
    this.imageButton = imageButton;
    this.teenageWeightBtn = teenageWeightBtn;
    this.teenageWeightBtntxt = teenageWeightBtntxt;
    this.textView4 = textView4;
    this.textView6 = textView6;
    this.toddlersWeightBtn = toddlersWeightBtn;
    this.toolbar = toolbar;
    this.username = username;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFemaleHealthBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFemaleHealthBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_female_health, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFemaleHealthBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.animationView;
      LottieAnimationView animationView = ViewBindings.findChildViewById(rootView, id);
      if (animationView == null) {
        break missingId;
      }

      id = R.id.babies_toddlers_weight_btn;
      CardView babiesToddlersWeightBtn = ViewBindings.findChildViewById(rootView, id);
      if (babiesToddlersWeightBtn == null) {
        break missingId;
      }

      id = R.id.bar;
      AppBarLayout bar = ViewBindings.findChildViewById(rootView, id);
      if (bar == null) {
        break missingId;
      }

      id = R.id.children_weight_btn;
      CardView childrenWeightBtn = ViewBindings.findChildViewById(rootView, id);
      if (childrenWeightBtn == null) {
        break missingId;
      }

      id = R.id.children_weight_btntxt;
      TextView childrenWeightBtntxt = ViewBindings.findChildViewById(rootView, id);
      if (childrenWeightBtntxt == null) {
        break missingId;
      }

      id = R.id.imageButton;
      ImageButton imageButton = ViewBindings.findChildViewById(rootView, id);
      if (imageButton == null) {
        break missingId;
      }

      id = R.id.teenage_weight_btn;
      CardView teenageWeightBtn = ViewBindings.findChildViewById(rootView, id);
      if (teenageWeightBtn == null) {
        break missingId;
      }

      id = R.id.teenage_weight_btntxt;
      TextView teenageWeightBtntxt = ViewBindings.findChildViewById(rootView, id);
      if (teenageWeightBtntxt == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.toddlers_weight_btn;
      CardView toddlersWeightBtn = ViewBindings.findChildViewById(rootView, id);
      if (toddlersWeightBtn == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new FragmentFemaleHealthBinding((ConstraintLayout) rootView, animationView,
          babiesToddlersWeightBtn, bar, childrenWeightBtn, childrenWeightBtntxt, imageButton,
          teenageWeightBtn, teenageWeightBtntxt, textView4, textView6, toddlersWeightBtn, toolbar,
          username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
