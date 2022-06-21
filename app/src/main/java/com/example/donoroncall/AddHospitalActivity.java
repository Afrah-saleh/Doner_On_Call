package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.Statement;

public class AddHospitalActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    Button butSave, btn;
    EditText texHospitalName,texCity,texTele,texAddress,texPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        ((AppCompatActivity) AddHospitalActivity.this).getSupportActionBar().setTitle("New Hospital");
        connectionClass = new ConnectionClass();
        texHospitalName = (EditText) findViewById(R.id.hosName);
        texCity = (EditText) findViewById(R.id.texCity);
        texTele = (EditText) findViewById(R.id.texTele);
        texAddress = (EditText) findViewById(R.id.texAddress);
        texPassword = (EditText) findViewById(R.id.texPassword);

        butSave = (Button) findViewById(R.id.butAdd);
        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHospital_Api addHospital = new addHospital_Api();
                addHospital.execute();
            }
        });
    }

    private class addHospital_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "INSERT INTO `hospital` (`h_name`, `h_city`, `h_phone`, `h_email`,`h_password`) VALUES ('"+texHospitalName.getText()+"','"+texCity.getText()+"', '"+texTele.getText()+"', '"+texAddress.getText()+"','"+texPassword.getText()+"') ";
                    Log.d(TAG, "doInBackground: "+query);
                    Statement stnt = con.createStatement();
                    stnt.executeUpdate(query);

                }
                else
                {
                    Log.d(TAG, "onPreExecute: Error 1");
                    return "error";

                }
            } catch (Exception e)
            {
                Log.d(TAG, "onPreExecute: Error 2");
                return "error";
            }
            return null;
        }

        @Override
        protected void onPreExecute()
        {
            Toast.makeText(AddHospitalActivity.this, "please wait ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result==null)
            {
                Toast.makeText(AddHospitalActivity.this, "hospital is added", Toast.LENGTH_SHORT).show();
                AddHospitalActivity.this.finish();
            }
            else
            {
                Toast.makeText(AddHospitalActivity.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }

        }
    }
}