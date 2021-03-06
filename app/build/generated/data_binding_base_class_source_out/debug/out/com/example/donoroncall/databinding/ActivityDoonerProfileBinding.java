// Generated by view binder compiler. Do not edit!
package com.example.donoroncall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.donoroncall.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDoonerProfileBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText Email;

  @NonNull
  public final EditText Name;

  @NonNull
  public final EditText Phone;

  @NonNull
  public final Button back;

  @NonNull
  public final EditText birthdate;

  @NonNull
  public final Spinner blood;

  @NonNull
  public final EditText bloodtype;

  @NonNull
  public final TextView bloodview;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final Button btnDate;

  @NonNull
  public final Button btnsave;

  @NonNull
  public final Spinner city;

  @NonNull
  public final EditText cityedit;

  @NonNull
  public final TextView cityview;

  @NonNull
  public final TextView emailview;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextView nameview;

  @NonNull
  public final TextView phoneview;

  @NonNull
  public final TextView textView;

  private ActivityDoonerProfileBinding(@NonNull RelativeLayout rootView, @NonNull EditText Email,
      @NonNull EditText Name, @NonNull EditText Phone, @NonNull Button back,
      @NonNull EditText birthdate, @NonNull Spinner blood, @NonNull EditText bloodtype,
      @NonNull TextView bloodview, @NonNull BottomNavigationView bottomNavigation,
      @NonNull Button btnDate, @NonNull Button btnsave, @NonNull Spinner city,
      @NonNull EditText cityedit, @NonNull TextView cityview, @NonNull TextView emailview,
      @NonNull ImageView imageView2, @NonNull TextView nameview, @NonNull TextView phoneview,
      @NonNull TextView textView) {
    this.rootView = rootView;
    this.Email = Email;
    this.Name = Name;
    this.Phone = Phone;
    this.back = back;
    this.birthdate = birthdate;
    this.blood = blood;
    this.bloodtype = bloodtype;
    this.bloodview = bloodview;
    this.bottomNavigation = bottomNavigation;
    this.btnDate = btnDate;
    this.btnsave = btnsave;
    this.city = city;
    this.cityedit = cityedit;
    this.cityview = cityview;
    this.emailview = emailview;
    this.imageView2 = imageView2;
    this.nameview = nameview;
    this.phoneview = phoneview;
    this.textView = textView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDoonerProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDoonerProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dooner_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDoonerProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Email;
      EditText Email = ViewBindings.findChildViewById(rootView, id);
      if (Email == null) {
        break missingId;
      }

      id = R.id.Name;
      EditText Name = ViewBindings.findChildViewById(rootView, id);
      if (Name == null) {
        break missingId;
      }

      id = R.id.Phone;
      EditText Phone = ViewBindings.findChildViewById(rootView, id);
      if (Phone == null) {
        break missingId;
      }

      id = R.id.back;
      Button back = ViewBindings.findChildViewById(rootView, id);
      if (back == null) {
        break missingId;
      }

      id = R.id.birthdate;
      EditText birthdate = ViewBindings.findChildViewById(rootView, id);
      if (birthdate == null) {
        break missingId;
      }

      id = R.id.blood;
      Spinner blood = ViewBindings.findChildViewById(rootView, id);
      if (blood == null) {
        break missingId;
      }

      id = R.id.bloodtype;
      EditText bloodtype = ViewBindings.findChildViewById(rootView, id);
      if (bloodtype == null) {
        break missingId;
      }

      id = R.id.bloodview;
      TextView bloodview = ViewBindings.findChildViewById(rootView, id);
      if (bloodview == null) {
        break missingId;
      }

      id = R.id.bottomNavigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      id = R.id.btnDate;
      Button btnDate = ViewBindings.findChildViewById(rootView, id);
      if (btnDate == null) {
        break missingId;
      }

      id = R.id.btnsave;
      Button btnsave = ViewBindings.findChildViewById(rootView, id);
      if (btnsave == null) {
        break missingId;
      }

      id = R.id.city;
      Spinner city = ViewBindings.findChildViewById(rootView, id);
      if (city == null) {
        break missingId;
      }

      id = R.id.cityedit;
      EditText cityedit = ViewBindings.findChildViewById(rootView, id);
      if (cityedit == null) {
        break missingId;
      }

      id = R.id.cityview;
      TextView cityview = ViewBindings.findChildViewById(rootView, id);
      if (cityview == null) {
        break missingId;
      }

      id = R.id.emailview;
      TextView emailview = ViewBindings.findChildViewById(rootView, id);
      if (emailview == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.nameview;
      TextView nameview = ViewBindings.findChildViewById(rootView, id);
      if (nameview == null) {
        break missingId;
      }

      id = R.id.phoneview;
      TextView phoneview = ViewBindings.findChildViewById(rootView, id);
      if (phoneview == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityDoonerProfileBinding((RelativeLayout) rootView, Email, Name, Phone, back,
          birthdate, blood, bloodtype, bloodview, bottomNavigation, btnDate, btnsave, city,
          cityedit, cityview, emailview, imageView2, nameview, phoneview, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
