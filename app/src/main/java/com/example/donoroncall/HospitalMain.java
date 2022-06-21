package com.example.donoroncall;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;

public class HospitalMain extends AppCompatActivity {
   // public android.widget.Button Button;
   public Button button,account,MAnage,btnacceptdonation,btndonationrecord,btnlogout;
    ConnectionClass connectionClass= new ConnectionClass();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_main);
         button = (Button) findViewById(R.id.button2);
        //btnlogout=findViewById(R.id.btnlogout);
        MAnage = findViewById(R.id.button3);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(HospitalMain.this,HospitalProfile.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(HospitalMain.this,HospitalMain.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(HospitalMain.this,HospitalAddEditAnnouncement.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(HospitalMain.this);
                        alert.setTitle("Logout");
                        alert.setMessage("Are you sure you want to Logout?");
                        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);

                                // close application
                            }

                        });
                        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // close dialog
                                dialog.cancel();
                            }
                        });
                        alert.show();

                }

                return false;
            }
        });
        btnacceptdonation=findViewById(R.id.btnacceptdonation);
        btndonationrecord=findViewById(R.id.btndonationrecord);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(HospitalMain.this, HospitalAddEditAnnouncement.class);
            startActivity(intent);
        });


MAnage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HospitalMain.this, HospitalRequests.class);
        startActivity(intent);
    }
});


        account = (Button) findViewById(R.id.account);
        account.setOnClickListener(view -> {
            Intent intent = new Intent(HospitalMain.this, HospitalProfile.class);
            startActivity(intent);


        });
    btnacceptdonation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent(HospitalMain.this, AcceptNewDonation.class);
            startActivity(intent);



        }
    });
       /* btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection conn = connectionClass.CONN();
                if ( conn!=null) {



                    AlertDialog.Builder alert = new AlertDialog.Builder(HospitalMain.this);
                    alert.setTitle("Logout");
                    alert.setMessage("Are you sure you want to Logout?");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            System.exit(0);

                            // close application
                        }

                    });
                    alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // close dialog
                            dialog.cancel();
                        }
                    });
                    alert.show();

                }   }
        });*/
    btndonationrecord.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HospitalMain.this, DonationRecord.class);
            startActivity(intent);

        }
    });

    }

}
