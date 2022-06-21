package com.example.donoroncall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DonationRecord extends AppCompatActivity {
    ConnectionClass connectionClass= new ConnectionClass();
    Connection con = connectionClass.CONN();
    RecyclerView recyclerView;
    Button btnback;
    RecyclerView.Adapter madapter;
    RecyclerView.LayoutManager layoutManager;
    List<DonationRecordeModel> donationrecordlist=new ArrayList<>();
    int donerid=0;
    String donername="";
    int hospitalid=0;
    String hospitalname="";
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_record);
btnback=findViewById(R.id.back);
        recyclerView = findViewById(R.id.lv_donationrecord);
        layoutManager= new LinearLayoutManager(DonationRecord.this);
        recyclerView.setLayoutManager(layoutManager);
        madapter= new DonationRecordRecycleViewAdapter(donationrecordlist,DonationRecord.this);
        recyclerView.setAdapter(madapter);



        //find user type

        try {

            if (con != null) {

                String doneridquery = "SELECT donerid FROM doner where d_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt11 = con.createStatement();
                ResultSet rs11 = stnt11.executeQuery((doneridquery));
                while (rs11.next()) {
                    donerid = rs11.getInt("donerid");
                  //  donername=rs11.getString("d_name");
                    bottomNavigationView=findViewById(R.id.bottomNavigation);
                    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item1:
                                    Intent intent=new Intent(DonationRecord.this,DoonerProfileActivity.class);
                                    startActivity(intent);
                                    break;
                                case R.id.item2:
                                    intent=new Intent(DonationRecord.this,DoonerMainActivity.class);
                                    startActivity(intent);
                                    break;
                                case R.id.item3:
                                    intent=new Intent(DonationRecord.this,DoonerAnnouncementlist.class);
                                    startActivity(intent);
                                    break;
                                case R.id.item4:
                                    AlertDialog.Builder alert = new AlertDialog.Builder(DonationRecord.this);
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
                String hospitalidquery = "SELECT h_id FROM hospital where h_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt22 = con.createStatement();
                ResultSet rs22 = stnt22.executeQuery((hospitalidquery));
                while (rs22.next()) {
                    hospitalid = rs22.getInt("h_id");
                 //   hospitalname=rs22.getString("h_name");
                    bottomNavigationView=findViewById(R.id.bottomNavigation);
                    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item1:
                                    Intent intent=new Intent(DonationRecord.this,HospitalProfile.class);
                                    startActivity(intent);
                                    break;
                                case R.id.item2:
                                    intent=new Intent(DonationRecord.this,HospitalMain.class);
                                    startActivity(intent);
                                    break;
                                case R.id.item3:
                                    intent=new Intent(DonationRecord.this,HospitalAddEditAnnouncement.class);
                                    startActivity(intent);
                                    break;

                                case R.id.item4:
                                    AlertDialog.Builder alert = new AlertDialog.Builder(DonationRecord.this);
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

           }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }


        filldonationrecordlist();

btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (donerid != 0) {
            Intent intent = new Intent(DonationRecord.this, DoonerMainActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(DonationRecord.this, HospitalMain.class);
            startActivity(intent);
        }
    }
});


    }

    private void filldonationrecordlist() {
        String query;

        if ( con!=null) {
            if(donerid!=0)
                 query = "SELECT * FROM donationrecord, hospital ,doner where h_id=hospitalid and doner.donerid=donationrecord.donerid and donationrecord.donerid='" + donerid + "'";
         else
                query = "SELECT * FROM donationrecord, hospital ,doner where h_id=hospitalid and doner.donerid=donationrecord.donerid and donationrecord.hospitalid='" + hospitalid + "'";




            try {
                Statement stnt = con.createStatement();
                ResultSet rs = stnt.executeQuery(query);

                while (rs.next()) {

                    int id = rs.getInt("recordid");
                    int q = rs.getInt("quantity");
                    String date = rs.getString("donationdate");
                    String hname=rs.getString("h_name");
                    String dname=rs.getString("d_name");
                    DonationRecordeModel drec = new DonationRecordeModel(id,q,date,hname,dname);
                    donationrecordlist.add(drec);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else
        {
            System.out.println("Connection failed");
        }






    }



}