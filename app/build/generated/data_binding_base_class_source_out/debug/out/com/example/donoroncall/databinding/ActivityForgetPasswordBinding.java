// Generated by view binder compiler. Do not edit!
package com.example.donoroncall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.donoroncall.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityForgetPasswordBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText CnewPass;

  @NonNull
  public final EditText Email;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final EditText newPass;

  @NonNull
  public final Button update2;

  private ActivityForgetPasswordBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText CnewPass, @NonNull EditText Email, @NonNull ImageView imageView4,
      @NonNull EditText newPass, @NonNull Button update2) {
    this.rootView = rootView;
    this.CnewPass = CnewPass;
    this.Email = Email;
    this.imageView4 = imageView4;
    this.newPass = newPass;
    this.update2 = update2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityForgetPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityForgetPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_forget_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityForgetPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.CnewPass;
      EditText CnewPass = ViewBindings.findChildViewById(rootView, id);
      if (CnewPass == null) {
        break missingId;
      }

      id = R.id.Email;
      EditText Email = ViewBindings.findChildViewById(rootView, id);
      if (Email == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.newPass;
      EditText newPass = ViewBindings.findChildViewById(rootView, id);
      if (newPass == null) {
        break missingId;
      }

      id = R.id.update2;
      Button update2 = ViewBindings.findChildViewById(rootView, id);
      if (update2 == null) {
        break missingId;
      }

      return new ActivityForgetPasswordBinding((ConstraintLayout) rootView, CnewPass, Email,
          imageView4, newPass, update2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
