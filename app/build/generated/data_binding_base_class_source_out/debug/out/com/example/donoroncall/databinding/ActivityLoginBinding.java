// Generated by view binder compiler. Do not edit!
package com.example.donoroncall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.donoroncall.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button button3;

  @NonNull
  public final Button fpass;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final EditText texEmail;

  @NonNull
  public final EditText texPassword;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final Spinner userType;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button button3,
      @NonNull Button fpass, @NonNull ImageView imageView2, @NonNull EditText texEmail,
      @NonNull EditText texPassword, @NonNull TextView textView3, @NonNull Spinner userType) {
    this.rootView = rootView;
    this.button3 = button3;
    this.fpass = fpass;
    this.imageView2 = imageView2;
    this.texEmail = texEmail;
    this.texPassword = texPassword;
    this.textView3 = textView3;
    this.userType = userType;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button3;
      Button button3 = ViewBindings.findChildViewById(rootView, id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.fpass;
      Button fpass = ViewBindings.findChildViewById(rootView, id);
      if (fpass == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.texEmail;
      EditText texEmail = ViewBindings.findChildViewById(rootView, id);
      if (texEmail == null) {
        break missingId;
      }

      id = R.id.texPassword;
      EditText texPassword = ViewBindings.findChildViewById(rootView, id);
      if (texPassword == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.user_type;
      Spinner userType = ViewBindings.findChildViewById(rootView, id);
      if (userType == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, button3, fpass, imageView2,
          texEmail, texPassword, textView3, userType);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
