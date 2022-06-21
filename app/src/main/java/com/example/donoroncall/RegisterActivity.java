package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Pattern;
public class RegisterActivity<user_type> extends AppCompatActivity {
    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    ".{6,}" +        // at least 6 characters
                    "$");
    ConnectionClass connectionClass;
    Button butRegister;
    EditText texEmail, texPassword, textname, textphone, date;
    Button selectDate;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    Spinner txtblood, address;
    String doneremail=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        connectionClass = new ConnectionClass();
        textname = findViewById(R.id.dName);
        texEmail = findViewById(R.id.Email);
        texPassword = findViewById(R.id.pass);
        textphone = findViewById(R.id.phone);
        txtblood = findViewById(R.id.blood);
        address = findViewById(R.id.address);
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
                datePickerDialog = new DatePickerDialog(RegisterActivity.this,
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



        texEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {

                try {

                    Connection con = connectionClass.CONN();
                    if (con != null) {
                        String donernameq = "SELECT * FROM doner where d_email ='" + texEmail.getText() + "'";
                        Statement stnt22 = con.createStatement();
                        ResultSet rs22 = stnt22.executeQuery((donernameq));
                        while (rs22.next()) {
                            doneremail=rs22.getString("d_email");

                        }
                        if ( doneremail!=null)
                        {

                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this);
                            dlgAlert.setMessage("Email already Exist press OK to go to login page");
                            dlgAlert.setTitle("Doner On Call");
                            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();



                        }

                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });



        butRegister = (Button) findViewById(R.id.button4);
        butRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Connection con = connectionClass.CONN();
                    if (con != null) {
                        if (validation.validatename(textname) &&  emailValidator(texEmail) == true &&validatePassword(texPassword) == true&& validation.validatePhone(textphone) && validation.validateDate(date)  ) {
                        //if (validatename(textname) == true && emailValidator(texEmail) == true && validatePassword(texPassword) == true &&  validatePhone(textphone) == true && validateDate(date) == true) {
                            String query = "INSERT INTO doner (d_birthdate, d_blodtype,d_city,d_email,d_mobile,d_name,d_password)  VALUES ('" + date.getText() + "','" + txtblood.getSelectedItem().toString() + "','" + address.getSelectedItem().toString() + "','" + texEmail.getText() + "','" + textphone.getText() + "','" + textname.getText() + "','" + texPassword.getText() + "')";
                            //if (validation.validateDate(date) && validation.validatePhone(textphone) && validation.validatename(textname) && validation.validateEmail(texEmail)) {
                            Statement stnt = con.createStatement();
                            stnt.executeUpdate(query);

                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this);
                            dlgAlert.setMessage("Register Done");
                            dlgAlert.setTitle("Doner On Call");
                            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();


                        } else {
                            Toast.makeText(RegisterActivity.this, "Data don't match criteria ", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Not Connected ", Toast.LENGTH_SHORT).show();
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }

            public boolean emailValidator(TextView texEmail) {
                String emailToText = texEmail.getText().toString();
                if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
                    texEmail.setError(null);
                    return true;
                } else {
                    //  Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                    texEmail.setError("Enter valid Email address!");
                    return false;
                }
            }

            public boolean validatePassword(TextView texPassword) {
                String passwordInput = texPassword.getText().toString().trim();
                // if password field is empty
                // it will display error message "Field can not be empty"
                if (passwordInput.isEmpty()) {
                    texPassword.setError("Field can not be empty");
                    return false;
                }
                // if password does not matches to the pattern
                // it will display an error message "Password is too weak"
                else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                    texPassword.setError("Password is too weak make sure to use upper case letter,1 digit and 6 characters");
                    return false;
                } else {
                    texPassword.setError(null); //if no error
                    return true;
                }
            }





        });

    }
}











