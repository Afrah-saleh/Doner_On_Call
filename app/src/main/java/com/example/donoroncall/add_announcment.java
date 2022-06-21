package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class add_announcment extends AppCompatActivity {
    ConnectionClass connectionClass;
    TextView date,quantity;
    Button save;
    int hospitalid=0;
String announcementstatus ="Active";
    Spinner txtblood ;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcment);
        connectionClass = new ConnectionClass();
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(add_announcment.this,HospitalProfile.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(add_announcment.this,HospitalMain.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(add_announcment.this,HospitalAddEditAnnouncement.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(add_announcment.this);
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
        txtblood = (Spinner) findViewById(R.id.blood_Type);
        quantity =  findViewById(R.id.quantity);
        date=findViewById(R.id.tvSelectedDate);

        String currentdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        date.setText(currentdate);


        //database

        try {

            Connection con = connectionClass.CONN();
            if (con != null) {
                String hospitalidquery = "SELECT h_id FROM hospital where h_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt22 = con.createStatement();
                ResultSet rs22 = stnt22.executeQuery((hospitalidquery));
                while (rs22.next()) {
                    hospitalid = rs22.getInt("h_id");
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    try {

                        Connection con = connectionClass.CONN();
                        if (con != null) {
                            String query = "INSERT INTO bloodannouncement (announcementdate,quantity,bloodtype,announcement_status,hospitalid)  VALUES ('"+date.getText()+"','"+quantity.getText()+"','"+txtblood.getSelectedItem().toString()+"','"+announcementstatus+"','"+hospitalid+"')";
                                Statement stnt = con.createStatement();
                                stnt.executeUpdate(query);
                            Toast.makeText(add_announcment.this, "Announcement Added Successfuly", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(add_announcment.this, HospitalMain.class);
                            startActivity(intent);

                        }
                    }
                    catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
    });
}

}

