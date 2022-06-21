// Generated by view binder compiler. Do not edit!
package com.example.donoroncall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.donoroncall.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHospitalRequestsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button back;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final RecyclerView lvRequests;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView9;

  private ActivityHospitalRequestsBinding(@NonNull RelativeLayout rootView, @NonNull Button back,
      @NonNull BottomNavigationView bottomNavigation, @NonNull ImageView imageView2,
      @NonNull RecyclerView lvRequests, @NonNull TextView textView, @NonNull TextView textView11,
      @NonNull TextView textView7, @NonNull TextView textView9) {
    this.rootView = rootView;
    this.back = back;
    this.bottomNavigation = bottomNavigation;
    this.imageView2 = imageView2;
    this.lvRequests = lvRequests;
    this.textView = textView;
    this.textView11 = textView11;
    this.textView7 = textView7;
    this.textView9 = textView9;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHospitalRequestsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHospitalRequestsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_hospital_requests, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHospitalRequestsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back;
      Button back = ViewBindings.findChildViewById(rootView, id);
      if (back == null) {
        break missingId;
      }

      id = R.id.bottomNavigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.lv_requests;
      RecyclerView lvRequests = ViewBindings.findChildViewById(rootView, id);
      if (lvRequests == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      return new ActivityHospitalRequestsBinding((RelativeLayout) rootView, back, bottomNavigation,
          imageView2, lvRequests, textView, textView11, textView7, textView9);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
