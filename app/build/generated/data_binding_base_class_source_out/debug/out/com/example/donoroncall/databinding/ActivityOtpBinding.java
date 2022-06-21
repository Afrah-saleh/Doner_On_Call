// Generated by view binder compiler. Do not edit!
package com.example.donoroncall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.example.donoroncall.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOtpBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextView otpDescriptionText;

  @NonNull
  public final PinView pinView;

  private ActivityOtpBinding(@NonNull ScrollView rootView, @NonNull TextView otpDescriptionText,
      @NonNull PinView pinView) {
    this.rootView = rootView;
    this.otpDescriptionText = otpDescriptionText;
    this.pinView = pinView;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOtpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_otp, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOtpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.otp_description_text;
      TextView otpDescriptionText = ViewBindings.findChildViewById(rootView, id);
      if (otpDescriptionText == null) {
        break missingId;
      }

      id = R.id.pin_view;
      PinView pinView = ViewBindings.findChildViewById(rootView, id);
      if (pinView == null) {
        break missingId;
      }

      return new ActivityOtpBinding((ScrollView) rootView, otpDescriptionText, pinView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
