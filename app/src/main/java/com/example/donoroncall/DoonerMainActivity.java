package com.example.donoroncall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DoonerMainActivity extends AppCompatActivity {


Button btneditprofile,btnresetpassword,btnviewannouncement,btndeleteaccount,btnlogout,btnrequest,btndonatonhistory;
    ConnectionClass connectionClass= new ConnectionClass();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dooner_main);

      btneditprofile=findViewById(R.id.button5);
      btnresetpassword=findViewById(R.id.button6);
      btnviewannouncement=findViewById(R.id.button2);
      btndeleteaccount=findViewById(R.id.btndeleteaccount);

      btnrequest=findViewById(R.id.btnrequest);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(DoonerMainActivity.this,DoonerProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(DoonerMainActivity.this,DoonerMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(DoonerMainActivity.this,DoonerAnnouncementlist.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(DoonerMainActivity.this);
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
      btndonatonhistory=findViewById(R.id.btndonationrecord);
      btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoonerMainActivity.this,DoonerProfileActivity.class);
                startActivity(intent);


            }
        });


        btnresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoonerMainActivity.this,DoonerResetPassword.class);
                startActivity(intent);

            }
        });

        btnviewannouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoonerMainActivity.this,DoonerAnnouncementlist.class);
                startActivity(intent);

            }
        });

btndeleteaccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Connection conn = connectionClass.CONN();
        if ( conn!=null) {



            AlertDialog.Builder alert = new AlertDialog.Builder(DoonerMainActivity.this);
            alert.setTitle("Delete Account");
            alert.setMessage("Are you sure you want to delete?");
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    String query = "delete from doner where d_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                    try {
                        Statement stnt = conn.createStatement();
                        stnt.executeUpdate(query);
                        Toast.makeText(DoonerMainActivity.this, "Acount Deleted Successfuly...", Toast.LENGTH_SHORT).show();

                        // close application

                        finishAffinity();
                        System.exit(0);


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
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


        }

});


/*btnlogout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Connection conn = connectionClass.CONN();
        if ( conn!=null) {



            AlertDialog.Builder alert = new AlertDialog.Builder(DoonerMainActivity.this);
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

btnrequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(DoonerMainActivity.this,DonerRequests.class);
        startActivity(intent);
    }
});
btndonatonhistory.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(DoonerMainActivity.this,DonationRecord.class);
        startActivity(intent); }
});

    }
}