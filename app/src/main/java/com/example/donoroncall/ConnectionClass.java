package com.example.donoroncall;


import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    String classes ="com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://172.20.10.2:3306/doneroncall";
    String un = "jawza";
    String password = "1234";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {
            Class.forName(classes);
            conn = DriverManager.getConnection(url,un,password);


        } catch (SQLException se)
        {
            Log.e("error",se.getMessage());
        } catch (ClassNotFoundException e)
        {
            Log.e("error",e.getMessage());
        } catch (Exception e)
        {
            Log.e("error",e.getMessage());
        }
        return conn;
    }
}
