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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class DoonerResetPassword extends AppCompatActivity {
    ConnectionClass connectionClass= new ConnectionClass();
    EditText oldpassword,newpassword,confirmpassword;
    Button btnreset,btnback;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dooner_reset_password);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(DoonerResetPassword.this,DoonerProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(DoonerResetPassword.this,DoonerMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(DoonerResetPassword.this,DoonerAnnouncementlist.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(DoonerResetPassword.this);
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

        oldpassword=findViewById(R.id.oldpassword);
        newpassword=findViewById(R.id.newpassword);
        confirmpassword=findViewById(R.id.confirmpassword);
        btnreset=findViewById(R.id.btnreset);
        btnback=findViewById(R.id.back);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Connection conn = connectionClass.CONN();
                if ( conn!=null) {
                    String query0 = "SELECT d_password FROM doner where d_email ='"+CurrentUser.getInstance().getCurrentUser()+"'" ;
                    String query1 = "Update doner set d_password ='" + newpassword.getText() + "' where d_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                    try {
                        Statement stnt0 = conn.createStatement();
                        ResultSet rs = stnt0.executeQuery(query0);
                        while (rs.next())
                        {
                            if(rs.getString("d_password").equals(oldpassword.getText().toString())) {
                                if (newpassword.getText().toString().equals(confirmpassword.getText().toString())) {


                                    if(validation.validatePassword(newpassword) && validation.validatePassword(confirmpassword))
                                    {
                                        Statement stnt = conn.createStatement();
                                        stnt.executeUpdate(query1);
                                        Toast.makeText(DoonerResetPassword.this, "Password Updated Successfuly...", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(DoonerResetPassword.this, "new password doesn't match criteria", Toast.LENGTH_SHORT).show();

                                    }

                                }
                                else
                                {
                                    Toast.makeText(DoonerResetPassword.this, "the new password don't match", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else
                            {
                                Toast.makeText(DoonerResetPassword.this, "incorrect old password .", Toast.LENGTH_SHORT).show();

                            }
                        }


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

                }
        });
btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(DoonerResetPassword.this,DoonerMainActivity.class);
        startActivity(intent);

    }
});



    }
}