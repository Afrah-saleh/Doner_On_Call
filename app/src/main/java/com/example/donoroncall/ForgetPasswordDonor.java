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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class ForgetPasswordDonor extends AppCompatActivity {
    Button update,selectDate;
    TextView date,textEmail, mobile;
    ConnectionClass connectionClass;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    Spinner blood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_donor);
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
                datePickerDialog = new DatePickerDialog(ForgetPasswordDonor.this,
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
        blood=(Spinner)findViewById(R.id.blood);
        mobile=(TextView)findViewById(R.id.mobile);


        update.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          if (emailValidator(textEmail)==true&& validatePhone(mobile) == true && validateDate(date) == true){
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
                    String query = "SELECT * FROM `doner` WHERE d_email = '"+textEmail.getText()+"' and d_mobile = '"+mobile.getText()+"' and d_birthdate = '"+date.getText()+"' and d_blodtype = '"+blood.getSelectedItem().toString()+"'";
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
            Toast.makeText(ForgetPasswordDonor.this, "verifying ... ", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            if(result!=null)
            {
                Toast.makeText(ForgetPasswordDonor.this, "verified... ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ForgetPasswordDonor.this, ForgetPassword.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(ForgetPasswordDonor.this, "Wrong input... ", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public boolean emailValidator(TextView texEmail) {
        String emailToText = texEmail.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            texEmail.setError(null); //if no error
            return true;
        } else {
            texEmail.setError("Enter valid Email address!");
            return false;
        }
    }
    private boolean validatePhone(TextView mobile) {
        String phoneInput = mobile.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (phoneInput.isEmpty()) {
            mobile.setError("phone number can not be empty");
            return false;
        } else {
            mobile.setError(null); //if no error
            return true;
        }
    }

    private boolean validateDate(TextView date) {
        String dateInput = date.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (dateInput.isEmpty()) {
            date.setError("Birthdate can not be empty");
            return false;
        } else {
            date.setError(null); //if no error
            return true;
        }
    }
}


