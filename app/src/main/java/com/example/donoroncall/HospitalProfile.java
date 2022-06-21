package com.example.donoroncall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HospitalProfile extends AppCompatActivity {
    ConnectionClass connectionClass= new ConnectionClass();
    EditText Name,Phone ,Citytext;
    TextView Email;
    Button btnsave,password,btnlogout,btnback;
    Spinner city;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_profile);
        Email=findViewById(R.id.Email);
        Name=findViewById(R.id.Name);
        Phone=findViewById(R.id.Phone);
        city=findViewById(R.id.city);
        btnsave=findViewById(R.id.btnsave);
        Citytext=findViewById(R.id.cityedit);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(HospitalProfile.this,HospitalProfile.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(HospitalProfile.this,HospitalMain.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(HospitalProfile.this,HospitalAddEditAnnouncement.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(HospitalProfile.this);
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
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalProfile.this,HospitalMain.class);
                startActivity(intent);
            }
        });

        password=findViewById(R.id.password);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HospitalProfile.this,HospitalResetPassword.class);
                startActivity(intent);

            }
        });
        Email.setText(CurrentUser.getInstance().getCurrentUser());

        Connection con = connectionClass.CONN();
        if ( con!=null) {
            String query = "SELECT * FROM hospital where h_email ='"+CurrentUser.getInstance().getCurrentUser()+"'" ;

            try {
                Statement stnt = con.createStatement();
                ResultSet rs = stnt.executeQuery(query);

                while (rs.next()) {
                    Name.setText(rs.getString("h_name"));
                    Phone.setText(rs.getString("h_phone"));
                    Citytext.setText(rs.getString("h_city"));

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else
        {
            System.out.println("Connection failed");
        }


    btnsave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Connection conn = connectionClass.CONN();
            if ( conn!=null)
            {
                String query ="Update hospital set h_name ='" + Name.getText() + "' where h_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;
                String query2 ="Update hospital set h_phone ='" + Phone.getText() + "' where h_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;
                String query5 ="Update hospital set h_city ='" + city.getSelectedItem().toString() + "' where h_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;

                try
                {

                    if(validation.validatePhone(Phone)&&validation.validatename(Name)) {
                        Statement stnt = conn.createStatement();
                        stnt.executeUpdate(query);
                        stnt.executeUpdate(query2);
                        stnt.executeUpdate(query5);
                        Toast.makeText(HospitalProfile.this, "Updated Successfuly...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(HospitalProfile.this, "new data doesn't match criteria", Toast.LENGTH_SHORT).show();

                    }

                }
                catch(SQLException throwables)
                {
                    throwables.printStackTrace();
                }


            }


            Intent intent = new Intent(HospitalProfile.this,HospitalMain.class);
            startActivity(intent);

        }




    });
}
}