package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountsListActivity extends AppCompatActivity {
    ListView simpleList;
    ConnectionClass connectionClass;
    List<String> itemList = new ArrayList<String>();
    String countryList[] = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectionClass = new ConnectionClass();
        setContentView(R.layout.activity_accounts_list);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.account_listview_item, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
        accountsList_Api accountsList = new accountsList_Api();
        accountsList.execute();
    }

    private class accountsList_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT d_name FROM `doner`";
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                    // to print the resultset on console
                    if (rs.next()) {
                        do {
                            String resString = rs.getString(1);
                            Log.d(TAG, "doInBackground resString : "+resString);
                            itemList.add(resString);
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
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AccountsListActivity.this, R.layout.account_listview_item, R.id.textView, itemList);
                simpleList.setAdapter(arrayAdapter);

            }

            else
            {
                //  Toast.makeText(HospitalsList.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}