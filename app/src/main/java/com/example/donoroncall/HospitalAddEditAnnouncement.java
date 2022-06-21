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
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HospitalAddEditAnnouncement extends AppCompatActivity {
    ConnectionClass connectionClass= new ConnectionClass();
    Connection con = connectionClass.CONN();
    RecyclerView recyclerView;
    RecyclerView.Adapter madapter;
    RecyclerView.LayoutManager layoutManager;
    int hospitalid=0;
    List<announcmentModel> announcementlist=new ArrayList<>();
    Button btnadd,btnback;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_add_edit_announcement);
btnback=findViewById(R.id.back);
        btnadd=findViewById(R.id.btnadd);
        recyclerView = findViewById(R.id.lv_announcement);
        layoutManager= new LinearLayoutManager(HospitalAddEditAnnouncement.this);
        recyclerView.setLayoutManager(layoutManager);
        madapter= new RecycleViewAdapter(announcementlist,HospitalAddEditAnnouncement.this);
        recyclerView.setAdapter(madapter);

        //find hospital
        try {


            String hospitalidquery = "SELECT h_id FROM hospital where h_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
            Statement stnt22 = con.createStatement();
            ResultSet rs22 = stnt22.executeQuery((hospitalidquery));
            while (rs22.next()) {
                hospitalid = rs22.getInt("h_id");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        fillannouncementlist();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(HospitalAddEditAnnouncement.this,add_announcment.class);
                startActivity(intent);


            }
        });
btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(HospitalAddEditAnnouncement.this,HospitalMain.class);
        startActivity(intent);
    }
});
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(HospitalAddEditAnnouncement.this,HospitalProfile.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(HospitalAddEditAnnouncement.this,HospitalMain.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(HospitalAddEditAnnouncement.this,HospitalAddEditAnnouncement.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(HospitalAddEditAnnouncement.this);
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
    private void fillannouncementlist() {

        if ( con!=null) {

            String query = "SELECT * FROM bloodannouncement, hospital where h_id=hospitalid and h_id='"+hospitalid+"'ORDER BY `announcementdate` DESC, `announcement_status` ASC" ;
            try {
                Statement stnt = con.createStatement();
                ResultSet rs = stnt.executeQuery(query);

                while (rs.next()) {

                    int id = rs.getInt("announcementid");
                    int q = rs.getInt("quantity");
                    String date = rs.getString("announcementdate");
                    String hname=rs.getString("h_name");
                    String hlocation=rs.getString("h_city");
                    String blodtype= rs.getString("bloodtype");
                    String announcementstatus=rs.getString("announcement_status");
                    announcmentModel ann = new announcmentModel(id,date,q,blodtype,hname,hlocation,announcementstatus);
                    announcementlist.add(ann);


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



