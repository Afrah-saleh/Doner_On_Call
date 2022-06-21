package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import android.content.Context;
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
import java.util.HashMap;
import java.util.List;

public class Fragment2 extends Fragment {

    List<String> itemList = new ArrayList<String>();
    ArrayList<ArrayList<String>> graph = new ArrayList<>();
    List<String> itemListCity = new ArrayList<String>();
    List<String> itemListid = new ArrayList<String>();
    HashMap<String, String> capitalCities = new HashMap<String, String>();
    ConnectionClass connectionClass;
    private View view;
    ArrayAdapter<String> arrayAdapter;
    List<hospitalModel> list= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Hospitals list");
        view = inflater.inflate(R.layout.fragment2, container, false);

        ListView simpleList = (ListView) view.findViewById(R.id.simpleListView);


        hospitalAdapter adapter = new hospitalAdapter(view.getContext(), list);
        simpleList.setAdapter(adapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> ViewHolder, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),HospitalActivity.class);
                intent.putExtra("id",adapter.getItem(i).getName() );
                //based on item add info to intent
                startActivity(intent);
            }
        });

        Button butAdd = (Button)view.findViewById(R.id.butAdd);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddHospitalActivity.class);
                startActivity(intent);
            }
        });



        connectionClass = new ConnectionClass();
        Fragment2.hospitalsList_Api hospitalsList = new Fragment2.hospitalsList_Api();
        hospitalsList.execute();
        return view;

    }

    private class hospitalsList_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT h_id,h_name,h_city FROM `hospital`";
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                    // to print the resultset on console
                    if (rs.next()) {
                        do {

                            list.add(new hospitalModel(rs.getString(1), rs.getString(2),rs.getString(3)));
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
                ListView simpleList = (ListView) view.findViewById(R.id.simpleListView);
                hospitalAdapter adapter = new hospitalAdapter(view.getContext(), list);
                simpleList.setAdapter(adapter);
            }
            else
            {
                //  Toast.makeText(HospitalsList.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
