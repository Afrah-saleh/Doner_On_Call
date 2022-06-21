package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HospitalsList extends AppCompatActivity {
    ListView simpleList;
    List<String> itemList = new ArrayList<String>();
    List<String> itemListid = new ArrayList<String>();
    HashMap<String, String> capitalCities = new HashMap<String, String>();
    ConnectionClass connectionClass;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_list);
        connectionClass = new ConnectionClass();
        simpleList = (ListView)findViewById(R.id.simpleListView);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HospitalsList.this,HospitalActivity.class);
                intent.putExtra("id", itemListid.get(i));
                //based on item add info to intent
                startActivity(intent);
            }
        });

        hospitalsList_Api login = new hospitalsList_Api();
        login.execute();
    }
    private class hospitalsList_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT h_id,h_name FROM `hospital`";
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                    // to print the resultset on console
                    if (rs.next()) {
                        do {
                            String resString = rs.getString(2);
                            itemList.add(resString);
                            itemListid.add(rs.getString(1));
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
           // Toast.makeText(HospitalsList.this, "logging-in ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result!=null) {
                //Toast.makeText(HospitalsList.this, "Welcome... ", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "test: "+itemList);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HospitalsList.this, R.layout.hospitals_list_item, R.id.textView, itemList);
                simpleList.setAdapter(arrayAdapter);
            }

            else
            {
              //  Toast.makeText(HospitalsList.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}