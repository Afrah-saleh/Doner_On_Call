

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
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    ConnectionClass connectionClass;
    List<String> itemList = new ArrayList<String>();
    List<String> itemListid = new ArrayList<String>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Accounts list");

        ListView simpleList = (ListView)view.findViewById(R.id.test);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),AccountActivity.class);
                intent.putExtra("id", itemListid.get(i));
                //based on item add info to intent
                startActivity(intent);
            }
        });

        connectionClass = new ConnectionClass();
        Fragment1.accountsList_Api accountsList = new Fragment1.accountsList_Api();
        accountsList.execute();



        return view;
    }



    public void goToAttract() {
//        Intent intent = new Intent(getActivity(), MainActivity2.class);
//        startActivity(intent);
    }

    private class accountsList_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT d_name,donerid FROM `doner`";
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);
                    // to print the resultset on console
                    if (rs.next()) {
                        do {
                            itemList.add(rs.getString(1));
                            itemListid.add(rs.getString(2));
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
                ListView simpleList = (ListView) view.findViewById(R.id.test);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.account_listview_item,R.id.textView,itemList);
                simpleList.setAdapter(arrayAdapter);

            }

            else
            {
                //  Toast.makeText(HospitalsList.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

