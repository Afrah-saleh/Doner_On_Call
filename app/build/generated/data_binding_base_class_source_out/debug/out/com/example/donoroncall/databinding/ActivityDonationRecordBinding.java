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

public final class ActivityDonationRecordBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button back;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final RecyclerView lvDonationrecord;

  @NonNull
  public final TextView textView;

  private ActivityDonationRecordBinding(@NonNull RelativeLayout rootView, @NonNull Button back,
      @NonNull BottomNavigationView bottomNavigation, @NonNull ImageView imageView2,
      @NonNull RecyclerView lvDonationrecord, @NonNull TextView textView) {
    this.rootView = rootView;
    this.back = back;
    this.bottomNavigation = bottomNavigation;
    this.imageView2 = imageView2;
    this.lvDonationrecord = lvDonationrecord;
    this.textView = textView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDonationRecordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDonationRecordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_donation_record, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDonationRecordBinding bind(@NonNull View rootView) {
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

      id = R.id.lv_donationrecord;
      RecyclerView lvDonationrecord = ViewBindings.findChildViewById(rootView, id);
      if (lvDonationrecord == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityDonationRecordBinding((RelativeLayout) rootView, back, bottomNavigation,
          imageView2, lvDonationrecord, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
