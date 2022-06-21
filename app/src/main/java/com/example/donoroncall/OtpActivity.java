package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;

public class OtpActivity extends AppCompatActivity {

    ConnectionClass connectionClass;
    String phoneNumber=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectionClass = new ConnectionClass();
        setContentView(R.layout.activity_otp);




    }

    public void callNextScreenFromOTP(View view) {
        PinView pin_view = (PinView) findViewById(R.id.pin_view);
        Bundle bundle = getIntent().getExtras();
        phoneNumber = bundle.getString("phoneNumber");
        System.out.println(bundle.getString("phoneNumber"));
        if(bundle.getString("otp").matches(pin_view.getText().toString()))
        {
            System.out.println("True");


            OtpActivity.otp_Api otp_Api = new OtpActivity.otp_Api();
            otp_Api.execute();
        }
    }

    private class otp_Api extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {


            try {
                Connection con = connectionClass.CONN();
                if (con != null) {

                    String query = "UPDATE `users` SET `verified` = '1' WHERE `users`.`phone` ='"+phoneNumber+"'";

                    Statement stnt = con.createStatement();
                    stnt.executeUpdate(query);

                } else {
                    Log.d(TAG, "onPreExecute: Error 1");
                    return "error";

                }
            } catch (Exception e) {
                Log.d(TAG, "onPreExecute: Error 2: " + e);
                return "error";
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(OtpActivity.this, "please wait ... ", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: " + result);
            if (result == null) {


                Toast.makeText(OtpActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(OtpActivity.this,LoginActivity.class);
                startActivity(i);




            } else {
                Toast.makeText(OtpActivity.this, "Wrong OTP... ", Toast.LENGTH_SHORT).show();
            }

        }
    }
}