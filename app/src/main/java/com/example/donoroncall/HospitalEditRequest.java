package com.example.donoroncall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class HospitalEditRequest extends AppCompatActivity {
    ConnectionClass connectionClass;
    Button btnupdate,btnback;
    int donerid=0,hospitalid=0;
    BottomNavigationView bottomNavigationView;
    TextView requestid,requestdate,annid,requesttype,dname,donationdate,requestresult,daddress,dmobile;
    String donerphone, doneradderss;
    Spinner requeststatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_edit_request);
        connectionClass = new ConnectionClass();


btnback=findViewById(R.id.back);

        btnupdate=findViewById(R.id.btnupdate);
        annid=findViewById(R.id.annid);
        requestdate= findViewById(R.id.requestdate);
        dname=findViewById(R.id.dname);
        requesttype=findViewById(R.id.requesttype);
        donationdate=findViewById(R.id.donationdate);
        requestid=findViewById(R.id.requestid);
        requeststatus=findViewById(R.id.requeststatus);
        requestresult=findViewById(R.id.requestresult);
        daddress=findViewById(R.id.dadress);
        dmobile=findViewById(R.id.dphone);


        Intent intent= getIntent();
        requestid.setText(String.valueOf(intent.getIntExtra("requestid",-1)));
        requestdate.setText(intent.getStringExtra("requestdate"));
        annid.setText(String.valueOf(intent.getIntExtra("announcementid",-1)));
        requesttype.setText(intent.getStringExtra("requesttype"));
        dname.setText(intent.getStringExtra("donername"));
        donationdate.setText(intent.getStringExtra("donationdate"));
        requestresult.setText(intent.getStringExtra("requeststatus"));

        //find user type

        try {
            Connection con = connectionClass.CONN();
            if (con != null) {

                String doneridquery = "SELECT donerid FROM doner where d_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt11 = con.createStatement();
                ResultSet rs11 = stnt11.executeQuery((doneridquery));
                while (rs11.next()) {
                    donerid = rs11.getInt("donerid");

               }
                String hospitalidquery = "SELECT h_id FROM hospital where h_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt22 = con.createStatement();
                ResultSet rs22 = stnt22.executeQuery((hospitalidquery));
                while (rs22.next()) {
                    hospitalid = rs22.getInt("h_id");
                }


                String requestownerquery = "select d_city , d_mobile from doner where donerid=(SELECT donerid FROM request where requestid ='" + requestid.getText() + "')";
                Statement stnt33 = con.createStatement();
                ResultSet rs33 = stnt33.executeQuery((requestownerquery));
                while (rs33.next()) {

                    donerphone=rs33.getString("d_mobile");
                    doneradderss=rs33.getString("d_city");
                }

                daddress.setText(doneradderss);
                dmobile.setText(donerphone);


                if(donerid!=0)
                {
                    requestresult.setEnabled(false);
                    requestresult.setEnabled(false);
                    btnupdate.setVisibility(View.INVISIBLE);
                    bottomNavigationView=findViewById(R.id.bottomNavigation);
                    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item1:
                                    Intent intent=new Intent(HospitalEditRequest.this,DoonerProfileActivity.class);
                                    startActivity(intent);
                                    break;
                                case R.id.item2:
                                    intent=new Intent(HospitalEditRequest.this,DoonerMainActivity.class);
                                    startActivity(intent);
                                    break;
                                case R.id.item3:
                                    intent=new Intent(HospitalEditRequest.this,DoonerAnnouncementlist.class);
                                    startActivity(intent);
                                    break;

                                case R.id.item4:
                                    AlertDialog.Builder alert = new AlertDialog.Builder(HospitalEditRequest.this);
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
                }

             if(hospitalid!=0) {
                 requeststatus.setVisibility(View.VISIBLE);
                 bottomNavigationView=findViewById(R.id.bottomNavigation);
                 bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                     @Override
                     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                         switch (item.getItemId()){
                             case R.id.item1:
                                 Intent intent=new Intent(HospitalEditRequest.this,HospitalProfile.class);
                                 startActivity(intent);
                                 break;
                             case R.id.item2:
                                 intent=new Intent(HospitalEditRequest.this,HospitalMain.class);
                                 startActivity(intent);
                                 break;
                             case R.id.item3:
                                 intent=new Intent(HospitalEditRequest.this,HospitalAddEditAnnouncement.class);
                                 startActivity(intent);
                                 break;

                         }

                         return false;
                     }
                 });


             }

            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(donerid!=0)
        {
            Intent intent = new Intent(HospitalEditRequest.this,DoonerMainActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(HospitalEditRequest.this,HospitalMain.class);
            startActivity(intent);
        }


    }
});

btnupdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        try {
            Connection con = connectionClass.CONN();
            if (con != null) {
                if (hospitalid != 0) {
                    String updatequery = "update request set requeststatus ='" + requeststatus.getSelectedItem().toString() + "' where requestid ='" + requestid.getText() + "'";
                    Statement stnt1 = con.createStatement();
                    stnt1.executeUpdate((updatequery));
                    Toast.makeText(HospitalEditRequest.this, "Request updated Successfuly ", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (hospitalid != 0) {
            Intent intent = new Intent(HospitalEditRequest.this, HospitalRequests.class);
            startActivity(intent);
        }
    }
});

    }
}