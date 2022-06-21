package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class ForgetPass extends AppCompatActivity {
    Button update,selectDate;
    TextView date,textEmail;
    ConnectionClass connectionClass;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        connectionClass = new ConnectionClass();

        textEmail = (TextView) findViewById(R.id.Email);
        // select date
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here you need to define Android Calendar, using this Calendar you will be able to get current year, month and day values.
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                // Inside Android OnClick method is where you will define Android datepicker.
                //Now you need to change Android DatePickerDialog to be able to use the current date values.
                datePickerDialog = new DatePickerDialog(ForgetPass.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year); //Here you want to show the date values separated by (/) inside Android TextView.
                                //the month index value is (0-11). So if you choose the month February you will get the month value as January, if you choose the month December you will get the month value as November.
                            }
                        }, year, month, dayOfMonth);
                //One thing left before you can see Android DatePickerDialog is to call the following method.
                datePickerDialog.show();
            }
        });


        update = (Button) findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (emailValidator(textEmail)==true){
                                            Forget_Api forget = new Forget_Api();
                                            forget.execute();
                                        }}
                                  }
        );

    }
    private class Forget_Api extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT * FROM `doner` WHERE d_email = '"+textEmail.getText()+"' and d_birthdate = '"+date.getText()+"'";
                    Statement stnt = con.createStatement();
                    ResultSet rs = stnt.executeQuery(query);

                    while (rs.next())
                    {
                        return rs.getString(1);
                    }

                }
                else
                    Log.d(TAG, "onPreExecute: Error 1");
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
            Toast.makeText(ForgetPass.this, "verifying ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result!=null)
            {
                Toast.makeText(ForgetPass.this, "verified... ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ForgetPass.this, Forget2.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(ForgetPass.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public boolean emailValidator(TextView texEmail) {
        String emailToText = texEmail.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
