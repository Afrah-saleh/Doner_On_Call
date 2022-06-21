package com.example.donoroncall;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class Forget2 extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    ".{6,}" +        // at least 6 characters
                    "$");
    Button update2;
    TextView textpass;
    TextView textEmail;
    String Password, Email;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget2);
        connectionClass = new ConnectionClass();

        textpass = (TextView) findViewById(R.id.newPass);
        textEmail = (TextView) findViewById(R.id.Email);
        update2 = (Button) findViewById(R.id.update2);

        update2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                         if(validation.validatePassword(textpass)) {

                                             try {
                                                 Connection con = connectionClass.CONN();
                                                 if (con != null) {
                                                     String query = "UPDATE doner SET d_password='"+textpass.getText()+"' where d_email='"+textEmail.getText()+"'";
                                                     Statement stnt = con.createStatement();
                                                     stnt.executeUpdate(query);
                                                     Toast.makeText(Forget2.this, "Successfully updated", Toast.LENGTH_SHORT).show();

                                                 }
                                             } catch (SQLException throwables) {
                                                 throwables.printStackTrace();
                                             }

                                         }
                                         else {
                                             Toast.makeText(Forget2.this, "something went wrong! try again... ", Toast.LENGTH_SHORT).show();
                                         }


                                       }
                                   });

    }

}