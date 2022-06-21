// Generated by view binder compiler. Do not edit!
package com.example.donoroncall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class ActivityAddHospitalBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button butAdd;

  @NonNull
  public final EditText hosName;

  @NonNull
  public final EditText texAddress;

  @NonNull
  public final EditText texCity;

  @NonNull
  public final EditText texPassword;

  @NonNull
  public final EditText texTele;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  private ActivityAddHospitalBinding(@NonNull ConstraintLayout rootView, @NonNull Button butAdd,
      @NonNull EditText hosName, @NonNull EditText texAddress, @NonNull EditText texCity,
      @NonNull EditText texPassword, @NonNull EditText texTele, @NonNull TextView textView4,
      @NonNull TextView textView5) {
    this.rootView = rootView;
    this.butAdd = butAdd;
    this.hosName = hosName;
    this.texAddress = texAddress;
    this.texCity = texCity;
    this.texPassword = texPassword;
    this.texTele = texTele;
    this.textView4 = textView4;
    this.textView5 = textView5;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddHospitalBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddHospitalBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_hospital, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddHospitalBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.butAdd;
      Button butAdd = ViewBindings.findChildViewById(rootView, id);
      if (butAdd == null) {
        break missingId;
      }

      id = R.id.hosName;
      EditText hosName = ViewBindings.findChildViewById(rootView, id);
      if (hosName == null) {
        break missingId;
      }

      id = R.id.texAddress;
      EditText texAddress = ViewBindings.findChildViewById(rootView, id);
      if (texAddress == null) {
        break missingId;
      }

      id = R.id.texCity;
      EditText texCity = ViewBindings.findChildViewById(rootView, id);
      if (texCity == null) {
        break missingId;
      }

      id = R.id.texPassword;
      EditText texPassword = ViewBindings.findChildViewById(rootView, id);
      if (texPassword == null) {
        break missingId;
      }

      id = R.id.texTele;
      EditText texTele = ViewBindings.findChildViewById(rootView, id);
      if (texTele == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      return new ActivityAddHospitalBinding((ConstraintLayout) rootView, butAdd, hosName,
          texAddress, texCity, texPassword, texTele, textView4, textView5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}