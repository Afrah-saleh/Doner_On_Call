package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    String acc_id,mobile,city,bllodtype,birthday,name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ((AppCompatActivity) AccountActivity.this).getSupportActionBar().setTitle("Account");
        connectionClass = new ConnectionClass();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("id");
            acc_id = value;
            Log.d(TAG, "onCreate: "+value);



            AccountActivity.account_Api hospital = new AccountActivity.account_Api();
            hospital.execute();
        }

    }

    private class account_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT * FROM `doner` where donerid="+acc_id;
                    Log.d(TAG, query);
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                    //to print the resultset on console
                    if (rs.next()) {
                        do {
                            mobile =  rs.getString(8);
                            city =  rs.getString(5);
                            bllodtype =  rs.getString(7);
                            birthday =  rs.getString(6);
                            name =  rs.getString(3);
                            email =  rs.getString(2);
                        } while (rs.next());
                    } else {
                        System.out.println("Record Not Found...");
                    }


                } else
                    Log.d(TAG, "onPreExecute: Error 1");
            } catch (Exception e) {
                Log.d(TAG, "onPreExecute: Error 2");
                return "error";
            }
            return "test";
        }
        @Override
        protected void onPreExecute()
        {
            Toast.makeText(AccountActivity.this, "Loading ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result!=null) {

                TextView acc_id2 = (TextView)findViewById(R.id.textView7);
                acc_id2.setText(acc_id);

                TextView account_nameTextView = (TextView)findViewById(R.id.textView13);
                account_nameTextView.setText(name);

                TextView mobile_telTextView = (TextView)findViewById(R.id.textView21);
                mobile_telTextView.setText(mobile);

                TextView blood_addTextView = (TextView)findViewById(R.id.textView22);
                blood_addTextView.setText(bllodtype);

                TextView cityTextView = (TextView)findViewById(R.id.textView28);
                cityTextView.setText(city);

                TextView birthdayTextView = (TextView)findViewById(R.id.textView19);
                birthdayTextView.setText(birthday);

                TextView emailTextView = (TextView)findViewById(R.id.textView18);
                emailTextView.setText(email);

                ((AppCompatActivity) AccountActivity.this).getSupportActionBar().setTitle(acc_id);
            }
            else
            {
                Toast.makeText(AccountActivity.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}