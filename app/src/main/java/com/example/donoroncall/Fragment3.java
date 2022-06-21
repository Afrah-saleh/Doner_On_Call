package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    ConnectionClass connectionClass;
    List<String> itemList = new ArrayList<String>();
    List<announcementlModel> list= new ArrayList<>();
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Announcements list");
        view = inflater.inflate(R.layout.fragment1, container, false);

        ListView simpleList = (ListView) view.findViewById(R.id.test);


        myAdapter adapter = new myAdapter(view.getContext(), list);
        simpleList.setAdapter(adapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> ViewHolder, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),AnnouncementActivity.class);
                intent.putExtra("id",adapter.getItem(i).getName5() );
                //based on item add info to intent
                startActivity(intent);
            }
        });

        connectionClass = new ConnectionClass();
        Fragment3.announcementsList_Api announcements_Api = new Fragment3.announcementsList_Api();
        announcements_Api.execute();
        return view;
    }

    private class announcementsList_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT bloodannouncement.*,hospital.h_name FROM `bloodannouncement`,`hospital` WHERE bloodannouncement.hospitalid=hospital.h_id ORDER BY `announcementdate` ASC";
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                    // to print the resultset on console
                    if (rs.next()) {
                        do {
                            String resString = rs.getString(1);
                            Log.d(TAG, "doInBackground resString : "+resString);
                            itemList.add(resString);
                            list.add(new announcementlModel(rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(7),rs.getString(1)));
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
                ListView listView = view.findViewById(R.id.test);
                myAdapter adapter = new myAdapter(view.getContext(), list);
                listView.setAdapter(adapter);

            }

            else
            {
                //  Toast.makeText(HospitalsList.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
