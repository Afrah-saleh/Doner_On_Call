package com.example.donoroncall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class DoonerProfileActivity extends AppCompatActivity {
ConnectionClass connectionClass= new ConnectionClass();
EditText Name,Phone,Birthdate ,Email,Citytext,blodtext;
Spinner blood,city;
Button btnsave,btndate,btnback;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dooner_profile);

              Email=findViewById(R.id.Email);
              Name=findViewById(R.id.Name);
              Phone=findViewById(R.id.Phone);
              city=findViewById(R.id.city);
              Birthdate=findViewById(R.id.birthdate);
              btnsave=findViewById(R.id.btnsave);
              btndate=findViewById(R.id.btnDate);
              blood=findViewById(R.id.blood);
              Citytext=findViewById(R.id.cityedit);
              blodtext=findViewById(R.id.bloodtype);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Intent intent=new Intent(DoonerProfileActivity.this,DoonerProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        intent=new Intent(DoonerProfileActivity.this,DoonerMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item3:
                        intent=new Intent(DoonerProfileActivity.this,DoonerAnnouncementlist.class);
                        startActivity(intent);
                        break;

                    case R.id.item4:
                        AlertDialog.Builder alert = new AlertDialog.Builder(DoonerProfileActivity.this);
                        alert.setTitle("Logout");
                        alert.setMessage("Are you sure you want to Logout?");
                        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);

                                // close application
                            }

                        });
                        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // close dialog
                                dialog.cancel();
                            }
                        });
                        alert.show();

                }

                return false;
            }
        });
              Email.setText(CurrentUser.getInstance().getCurrentUser());
              btnback=findViewById(R.id.back);

        Connection con = connectionClass.CONN();
        if ( con!=null) {
            String query = "SELECT * FROM doner where d_email ='"+CurrentUser.getInstance().getCurrentUser()+"'" ;

            try {
                Statement stnt = con.createStatement();
                ResultSet rs = stnt.executeQuery(query);

                while (rs.next()) {
                    Name.setText(rs.getString("d_name"));
                    Birthdate.setText(rs.getString("d_birthdate"));
                    Phone.setText(rs.getString("d_mobile"));
                    Citytext.setText(rs.getString("d_city"));
                    blodtext.setText(rs.getString("d_blodtype"));

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
              }
        }
        else
        {
            System.out.println("Connection failed");
        }







        btndate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // Inside Android OnClick method is where you will define Android datepicker.
        //Now you need to change Android DatePickerDialog to be able to use the current date values.
        datePickerDialog = new DatePickerDialog(DoonerProfileActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Birthdate.setText(day + "/" + (month + 1) + "/" + year); //Here you want to show the date values separated by (/) inside Android TextView.
                        //the month index value is (0-11). So if you choose the month February you will get the month value as January, if you choose the month December you will get the month value as November.
                    }
                }, year, month, dayOfMonth);
        //One thing left before you can see Android DatePickerDialog is to call the following method.
        datePickerDialog.show();


    }
});



btnsave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Connection conn = connectionClass.CONN();
        if ( conn!=null)
        {
            String query ="Update doner set d_name ='" + Name.getText() + "' where d_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;
            String query2 ="Update doner set d_mobile ='" + Phone.getText() + "' where d_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;
            String query3 ="Update doner set d_birthdate ='" + Birthdate.getText() + "' where d_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;
            String query4 ="Update doner set d_blodtype ='" + blood.getSelectedItem().toString() + "' where d_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;
            String query5 ="Update doner set d_city ='" + city.getSelectedItem().toString() + "' where d_email ='"+ CurrentUser.getInstance().getCurrentUser()+"'" ;

            try
                {

                if(validation.validateDate(Birthdate)&&validation.validatePhone(Phone)&&validation.validatename(Name)) {
                    Statement stnt = conn.createStatement();
                    stnt.executeUpdate(query);
                    stnt.executeUpdate(query2);
                    stnt.executeUpdate(query3);
                    stnt.executeUpdate(query4);
                    stnt.executeUpdate(query5);
                    Toast.makeText(DoonerProfileActivity.this, "Updated Successfuly...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(DoonerProfileActivity.this, "new data doesn't match criteria", Toast.LENGTH_SHORT).show();

                }

                }
            catch(SQLException throwables)
                {
                throwables.printStackTrace();
               }


        }




    }




});
btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DoonerProfileActivity.this,DoonerMainActivity.class);
        startActivity(intent);
    }
});

}
}