package com.example.donoroncall;
import static android.content.ContentValues.TAG;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginActivity extends AppCompatActivity {
    Button butLogin,fpass;
    TextView texEmail,texPassword;
    ConnectionClass connectionClass;
    Spinner user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectionClass = new ConnectionClass();

        texEmail = (TextView) findViewById(R.id.texEmail);
        texPassword = (TextView) findViewById(R.id.texPassword);
        butLogin = (Button) findViewById(R.id.button3);
        user_type = (Spinner) findViewById(R.id.user_type);

        fpass = (Button) findViewById(R.id.fpass);
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPass.class);
                startActivity(intent);
            }
        });


  /*      Connection con = connectionClass.CONN();
       if (con != null) {
            Toast.makeText(LoginActivity.this, "Connected Successfully ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Not Connected ", Toast.LENGTH_SHORT).show();
        }*/

        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_Api login = new login_Api();
                login.execute();
            }
        });
    };

    private class login_Api extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String userType = user_type.getSelectedItem().toString();
                try {
                    Connection con = connectionClass.CONN();
                    if (con != null) {


                        CurrentUser.getInstance().setGlobalVar1(texEmail.getText().toString());

                        if (userType.equals("Hospital")) {
                            String query = "SELECT * FROM `hospital` WHERE h_email = '" + texEmail.getText() + "' and h_password = '" + texPassword.getText() + "'";
                            Statement stnt = con.createStatement();
                            ResultSet rs = stnt.executeQuery(query);

                            while (rs.next()) {
                            return rs.getString(1);
                            }

                        }
                        else if (userType.equals("Donor")) {
                            String query = "SELECT * FROM `doner` WHERE d_email = '" + texEmail.getText() + "' and d_password = '" + texPassword.getText() + "'";
                            Statement stnt = con.createStatement();
                            ResultSet rs = stnt.executeQuery((query));
                            while (rs.next()) {
                                return rs.getString(1);
                            }
                         }
                        else if (userType.equals("Admin")) {
                            String query = "SELECT * FROM `admin` WHERE a_email = '" + texEmail.getText() + "' and a_password = '" + texPassword.getText() + "'";
                            Statement stnt = con.createStatement();
                            ResultSet rs = stnt.executeQuery((query));
                            while (rs.next()) {
                                return rs.getString(1);
                            }
                        }
                    } else
                        Log.d(TAG, "onPreExecute: Error 1");
                } catch (Exception e) {
                    Log.d(TAG, "onPreExecute: Error 2");
                    return "error";
                }
                return null;
            }

        @Override
        protected void onPreExecute()
        {
            Toast.makeText(LoginActivity.this, "logging-in ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result!=null) {
              //  Toast.makeText(LoginActivity.this, "Welcome... ", Toast.LENGTH_SHORT).show();
                if(user_type.getSelectedItem().toString().equals("Hospital")){
                    Intent intent=new Intent(LoginActivity.this,HospitalMain.class);
                    startActivity(intent);
                }
                if(user_type.getSelectedItem().toString().equals("Donor")){
                    Intent intent=new Intent(LoginActivity.this,DoonerMainActivity.class);
                    startActivity(intent);
                }
                if(user_type.getSelectedItem().toString().equals("Admin")){
                    Intent intent=new Intent(LoginActivity.this,AdminDashboardActivity.class);
                    startActivity(intent);
                }


            }
            else
            {
                Toast.makeText(LoginActivity.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }

        }

    }





}