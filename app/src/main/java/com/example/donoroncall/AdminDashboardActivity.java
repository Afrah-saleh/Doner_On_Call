package com.example.donoroncall;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminDashboardActivity extends AppCompatActivity {
    ConnectionClass connectionClass= new ConnectionClass();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new Fragment2()).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.item2); // change to whichever id should be default

        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.item1:
                        fragment= new Fragment1();
                        break;
                    case R.id.item2:
                        fragment= new Fragment2();
                        break;
                    case R.id.item3:
                        fragment= new Fragment3();
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(AdminDashboardActivity.this);
                        alert.setTitle("Logout");
                        alert.setMessage("Are you sure you want to Logout?");
                        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                                    public void onClick(DialogInterface dialog, int which) {
                                        finishAffinity();
                                        System.exit(0);
                                    }

                        });
                        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // close dialog
                                dialog.cancel();
                            }
                        });
                        alert.show();
                        return false;
                }
             //
           // }
       // });
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });




    }
}