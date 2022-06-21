package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    ".{6,}" +        // at least 6 characters
                    "$");
    Button update2;
    TextView textpass, CnewPass;
    TextView textEmail;
    String Password, Email;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        connectionClass = new ConnectionClass();
        textpass = (TextView) findViewById(R.id.newPass);
        CnewPass = (TextView) findViewById(R.id.CnewPass);
        textEmail = (TextView) findViewById(R.id.Email);
        update2 = (Button) findViewById(R.id.update2);

        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePassword(textpass) == true) {
                    ForgetPassword.forget_Api reset = new forget_Api();
                    reset.execute();
                }
            }

            private boolean validatePassword(TextView textpass) {
                String passwordInput = textpass.getText().toString().trim();
                String conPass = CnewPass.getText().toString().trim();
                // if password field is empty
                // it will display error message "Field can not be empty"
                if (passwordInput.isEmpty()) {
                    textpass.setError("Field can not be empty");
                    return false;
                } else if (conPass.isEmpty()) {
                    CnewPass.setError("Field can not be empty");
                    return false;
                }
                // if password does not matches to the pattern
                // it will display an error message "Password is too weak"
                else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                    textpass.setError("Password is too weak make sure to use upper case letter,1 digit and 6 characters");
                    return false;
                } else if (!passwordInput.equals(conPass)) {

                    CnewPass.setError("Password not match");
                    return false;
                } else {
                    textpass.setError(null); //if no error
                    CnewPass.setError(null);
                    return true;
                }
            }

        });
    }

    private class forget_Api extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                Connection con = connectionClass.CONN();
                if (con != null) {

                    String query = "UPDATE `doner` SET `d_password`='" + textpass.getText() + "' where `d_email`='" + textEmail.getText() + "'";

                    Statement stnt = con.createStatement();
                    stnt.executeUpdate(query);

                } else {
                    Log.d(TAG, "onPreExecute: Error 1");
                    return "error";

                }
            } catch (Exception e) {
                Log.d(TAG, "onPreExecute: Error 2: " + e);
                return "error";
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(ForgetPassword.this, "please wait ... ", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: " + result);
            if (result == null) {
                Toast.makeText(ForgetPassword.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(ForgetPassword.this, "something went wrong! try again... ", Toast.LENGTH_SHORT).show();
            }

        }
    }

}