package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AnnouncementActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    String announcement_id,hospital_name,announcement_date,blood_quantity,blood_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        ((AppCompatActivity) AnnouncementActivity.this).getSupportActionBar().setTitle("Announcement");

        connectionClass = new ConnectionClass();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("id");
            announcement_id = value;
            TextView myAwesomeTextView = (TextView)findViewById(R.id.textView7);
            myAwesomeTextView.setText(value);
            //The key argument here must match that used in the other activity
            AnnouncementActivity.announcement_Api announcement = new AnnouncementActivity.announcement_Api();
            announcement.execute();
        }

        Button deleteh = (Button) findViewById(R.id.button7);
        deleteh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnouncementActivity.deleteannouncement_Api deleteAnnou = new AnnouncementActivity.deleteannouncement_Api();
                deleteAnnou.execute();
            }
        });

    }


    private class announcement_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT bloodannouncement.*,hospital.h_name FROM `bloodannouncement`,`hospital` WHERE bloodannouncement.hospitalid=hospital.h_id and bloodannouncement.announcementid="+announcement_id+" ORDER BY `announcementdate` DESC , `announcement_status` ASC";
                    Log.d(TAG, query);
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                    //to print the resultset on console
                    if (rs.next()) {
                        do {
                            announcement_id =  rs.getString(1);
                            hospital_name =  rs.getString(7);
                            announcement_date =  rs.getString(2);
                            blood_quantity =  rs.getString(3);
                            blood_type =  rs.getString(4);
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
            Toast.makeText(AnnouncementActivity.this, "Loading ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result!=null) {
                TextView hospital_idTextView = (TextView)findViewById(R.id.textView7);
                hospital_idTextView.setText(announcement_id);

                TextView hospital_nameTextView = (TextView)findViewById(R.id.textView13);
                hospital_nameTextView.setText(blood_type);

                TextView hospital_telTextView = (TextView)findViewById(R.id.textView18);
                hospital_telTextView.setText(hospital_name);

                TextView hospital_addTextView = (TextView)findViewById(R.id.textView21);
                hospital_addTextView.setText(blood_quantity);

                TextView hospital_citTextView = (TextView)findViewById(R.id.textView19);
                hospital_citTextView.setText(announcement_date);

                ((AppCompatActivity) AnnouncementActivity.this).getSupportActionBar().setTitle(hospital_name);
            }
            else
            {
                Toast.makeText(AnnouncementActivity.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private class deleteannouncement_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "DELETE FROM bloodannouncement where announcementid="+announcement_id;
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
            Toast.makeText(AnnouncementActivity.this, "Loading ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            AnnouncementActivity.this.finish();
        }

    }
}