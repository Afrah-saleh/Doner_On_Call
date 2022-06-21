package com.example.donoroncall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AcceptNewDonation extends AppCompatActivity {
    ConnectionClass connectionClass;
    TextView date, donername;
    EditText quantity , donermobile;
    Button btnsave,btnback;
    int hospitalid=0;
    int donerid;
    String btype;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_new_donation);
btnback=findViewById(R.id.back);
        connectionClass = new ConnectionClass();
        date = findViewById(R.id.dateofdonation);
        donername=findViewById(R.id.donernamev);
        donermobile=findViewById(R.id.doner);
        quantity=findViewById(R.id.quantity);
        btnsave=findViewById(R.id.save);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(AcceptNewDonation.this,HospitalProfile.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(AcceptNewDonation.this,HospitalMain.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(AcceptNewDonation.this,HospitalAddEditAnnouncement.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(AcceptNewDonation.this);
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
        String currentdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        date.setText(currentdate);


        // find hospital
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


        donermobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {

                    Connection con = connectionClass.CONN();
                    if (con != null) {
                        String donernameq = "SELECT * FROM doner where d_mobile ='" + donermobile.getText() + "'";
                        Statement stnt22 = con.createStatement();
                        ResultSet rs22 = stnt22.executeQuery((donernameq));
                        while (rs22.next()) {
                            donername.setText(rs22.getString("d_name"));
                            donerid=rs22.getInt("donerid");
                            btype=rs22.getString("d_blodtype");
                                   }


                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }



                    }
        });
btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(AcceptNewDonation.this,HospitalMain.class);
        startActivity(intent);
    }
});
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    Connection con = connectionClass.CONN();
                    if (con != null) {
                        String newdonation = "INSERT INTO donationrecord (donationdate, quantity,donerid ,hospitalid)  VALUES ('" + date.getText() + "','" + quantity.getText() + "','" + donerid+ "','" + hospitalid + "')";
                        Statement stnt22 = con.createStatement();
                        stnt22.executeUpdate((newdonation));
                        Toast.makeText(AcceptNewDonation.this, "Donation Done ", Toast.LENGTH_SHORT).show();


                        String query4 ="Update bloodannouncement set quantity = quantity-'" + quantity.getText() + "' where bloodtype ='"+ btype+"'"+"and hospitalid='"+ hospitalid+"'" ;
                        Statement stnt = con.createStatement();
                        stnt.executeUpdate(query4);






                        Intent intent = new Intent(AcceptNewDonation.this, HospitalMain.class);
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