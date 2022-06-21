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

public class HospitalRequests extends AppCompatActivity {

int hospital;
Button btnback;
    BottomNavigationView bottomNavigationView;
    ConnectionClass connectionClass= new ConnectionClass();
    RecyclerView recyclerView;
    RecyclerView.Adapter madapter;
    RecyclerView.LayoutManager layoutManager;
    List<RequestModel> requestlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_requests);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(HospitalRequests.this,HospitalProfile.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(HospitalRequests.this,HospitalMain.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(HospitalRequests.this,HospitalAddEditAnnouncement.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(HospitalRequests.this);
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
btnback=findViewById(R.id.back);
      recyclerView = findViewById(R.id.lv_requests);
        layoutManager= new LinearLayoutManager(HospitalRequests.this);
        recyclerView.setLayoutManager(layoutManager);
        madapter= new RequestRecycleViewAdapter(requestlist,HospitalRequests.this);
        recyclerView.setAdapter(madapter);
        fillrequestlist();


btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(HospitalRequests.this,HospitalMain.class);
        startActivity(intent);
    }
});

    }



    private void fillrequestlist() {


        Connection con = connectionClass.CONN();
        if (con != null) {


//find hospital
            String hospitalquery = "SELECT h_id FROM hospital where h_email ='"+CurrentUser.getInstance().getCurrentUser()+"'" ;
            try {
                Statement stnt1 = con.createStatement();
                ResultSet rs1 = stnt1.executeQuery((hospitalquery));
                while (rs1.next()) {
                    hospital = rs1.getInt("h_id");
                }
             } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


            String query = "SELECT * FROM request, bloodannouncement,hospital,doner where request.announcementid=bloodannouncement.announcementid and bloodannouncement.hospitalid=hospital.h_id and request.donerid=doner.donerid and bloodannouncement.hospitalid='"+hospital+"'";
      try{
                Statement stnt = con.createStatement();
                ResultSet rs = stnt.executeQuery(query);

                while (rs.next()) {

                    int id = rs.getInt("requestid");
                    String date = rs.getString("requestdate");
                    int annid = rs.getInt("announcementid");
                    String dname = rs.getString("d_name");
                    String reqstatus = rs.getString("requeststatus");
                    String reqtype = rs.getString("requesttype");
                    String doantiondate=rs.getString("expectdonationdate");
                    RequestModel req = new RequestModel(id, dname, annid, reqstatus, date, reqtype,doantiondate);
                    requestlist.add(req);


                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Connection failed");
        }


    }


    }