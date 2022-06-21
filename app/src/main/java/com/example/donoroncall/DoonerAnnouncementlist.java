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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DoonerAnnouncementlist extends AppCompatActivity {

 ConnectionClass connectionClass= new ConnectionClass();
 RecyclerView recyclerView;
 RecyclerView.Adapter madapter;
 RecyclerView.LayoutManager layoutManager;
 String Active ="Active";
 List<announcmentModel> announcementlist=new ArrayList<>();
int donerid=0;
String btype;
Button btnback;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dooner_announcementlist);
        recyclerView = findViewById(R.id.lv_announcement);
        layoutManager= new LinearLayoutManager(DoonerAnnouncementlist.this);
        recyclerView.setLayoutManager(layoutManager);
        madapter= new RecycleViewAdapter(announcementlist,DoonerAnnouncementlist.this);
        recyclerView.setAdapter(madapter);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(DoonerAnnouncementlist.this,DoonerProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(DoonerAnnouncementlist.this,DoonerMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(DoonerAnnouncementlist.this,DoonerAnnouncementlist.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(DoonerAnnouncementlist.this);
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

        try {
            Connection con = connectionClass.CONN();
            if (con != null) {

//find donerid
                String doneridquery = "SELECT donerid,d_blodtype FROM doner where d_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt1 = con.createStatement();
                ResultSet rs = stnt1.executeQuery((doneridquery));
                while (rs.next()) {
                    donerid = rs.getInt("donerid");
                    btype=rs.getString("d_blodtype");
                }





            }
        }
                 catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }






                fillannouncementlist();
    }
    private void fillannouncementlist() {

        String query;
        Connection con = connectionClass.CONN();
        if ( con!=null) {


            if(donerid==0)
                query   = "SELECT * FROM bloodannouncement, hospital where h_id=hospitalid and announcement_status='" + Active + "'";
             else
                 query = "SELECT * FROM bloodannouncement, hospital where h_id=hospitalid and bloodtype='"+btype+"' and announcement_status='" + Active + "'ORDER BY `announcementdate` DESC,`announcement_status` ASC";

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
btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(DoonerAnnouncementlist.this,DoonerMainActivity.class);
        startActivity(intent);
    }
});

    }
}