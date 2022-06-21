package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HospitalActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    String hospital_id,hospital_name,hospital_tel,hospital_add,hospital_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) HospitalActivity.this).getSupportActionBar().setTitle("Hospital");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        connectionClass = new ConnectionClass();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("id");
            hospital_id = value;
            TextView myAwesomeTextView = (TextView)findViewById(R.id.textView7);
            myAwesomeTextView.setText(value);
            //The key argument here must match that used in the other activity
            hospital_Api hospital = new hospital_Api();
            hospital.execute();
        }

        Button deleteh = (Button) findViewById(R.id.button7);
        deleteh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletehospital_Api deleteHospital = new deletehospital_Api();
                deleteHospital.execute();
            }
        });
    }
    private class hospital_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT * FROM `hospital` where h_id="+hospital_id;
                    Log.d(TAG, query);
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                     //to print the resultset on console
                    if (rs.next()) {
                        do {
                            hospital_name =  rs.getString(3);
                            hospital_city =  rs.getString(5);
                            hospital_tel =  rs.getString(6);
                            hospital_add =  rs.getString(2);
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
            Toast.makeText(HospitalActivity.this, "Loading ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result!=null) {
                TextView hospital_idTextView = (TextView)findViewById(R.id.textView7);
                hospital_idTextView.setText(hospital_id);

                TextView hospital_nameTextView = (TextView)findViewById(R.id.textView13);
                hospital_nameTextView.setText(hospital_name);

                TextView hospital_telTextView = (TextView)findViewById(R.id.textView18);
                hospital_telTextView.setText(hospital_tel);

                TextView hospital_addTextView = (TextView)findViewById(R.id.textView21);
                hospital_addTextView.setText(hospital_add);

                TextView hospital_citTextView = (TextView)findViewById(R.id.textView19);
                hospital_citTextView.setText(hospital_city);

                ((AppCompatActivity) HospitalActivity.this).getSupportActionBar().setTitle(hospital_name);
            }
            else
            {
                Toast.makeText(HospitalActivity.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private class deletehospital_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "DELETE FROM hospital where h_id="+hospital_id;
                    Log.d(TAG, query);
                    Statement stnt = con.createStatement();
                    stnt.executeUpdate(query);

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
            Toast.makeText(HospitalActivity.this, "Loading ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            HospitalActivity.this.finish();
        }

    }
}