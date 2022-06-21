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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddRequest extends AppCompatActivity {
ConnectionClass connectionClass;
Button btnsubmit,btndate,btnremove ,btnback;
int id ,q;
String announdate,hospitalname,hospitallocation,btype;
String requeststatus = "approved";
Spinner requesttype,announcementstatus;
int donerid=0,hospitalid=0;
String usertype="";
int result=0;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    TextView annid, anndate,hname,hloacation , blodtype,quantity,date,donationdate,title,annstatus, requestl, requestdatel;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        btnsubmit = findViewById(R.id.btnsubmit);
        annid = findViewById(R.id.anid);
        anndate = findViewById(R.id.andate);
        hname = findViewById(R.id.hname);
        hloacation = findViewById(R.id.hlocation);
        blodtype = findViewById(R.id.blodtype);
        quantity = findViewById(R.id.quantity);
        requesttype = findViewById(R.id.requesttype);
        connectionClass = new ConnectionClass();
        btndate = findViewById(R.id.btnDate);
        donationdate = findViewById(R.id.donationhdate);
        title = findViewById(R.id.requesttitel);
        btnremove = findViewById(R.id.btnremove);
        announcementstatus = findViewById(R.id.announcementstaus);
        date = findViewById(R.id.requestdate);
        annstatus = findViewById(R.id.annstatus);
        btnback=findViewById(R.id.back);
requestl=findViewById(R.id.reqestl);
requestdatel=findViewById(R.id.requestdatel);
        String currentdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        date.setText(currentdate);
        donationdate.setText(currentdate);

        Intent intent = getIntent();

        id = intent.getIntExtra("annid", -1);
        q = intent.getIntExtra("quantity", -1);
        hospitalname = intent.getStringExtra("hname");
        hospitallocation = intent.getStringExtra("hlocation");
        announdate = intent.getStringExtra("adate");
        btype = intent.getStringExtra("blodtype");


        annstatus.setText(intent.getStringExtra("statusofannouncement"));
        annid.setText(String.valueOf(id));
        anndate.setText(announdate);
        hname.setText(hospitalname);
        hloacation.setText(hospitallocation);
        quantity.setText(String.valueOf(q));
        blodtype.setText(btype);


        try {
            Connection con = connectionClass.CONN();
            if (con != null) {

//find donerid
                String doneridquery = "SELECT donerid FROM doner where d_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt1 = con.createStatement();
                ResultSet rs = stnt1.executeQuery((doneridquery));
                while (rs.next()) {
                    donerid = rs.getInt("donerid");
                }
//doner or  hospital
                String hospitalidquery = "SELECT h_id FROM hospital where h_email ='" + CurrentUser.getInstance().getCurrentUser() + "'";
                Statement stnt11 = con.createStatement();
                ResultSet rs22 = stnt11.executeQuery((hospitalidquery));
                while (rs22.next()) {
                    hospitalid = rs22.getInt("h_id");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (donerid != 0){
            usertype = "doner";
            donationdate.setVisibility(View.VISIBLE);
            date.setVisibility(View.VISIBLE);
            btndate.setVisibility(View.VISIBLE);
            requesttype.setVisibility(View.VISIBLE);
            requestl.setVisibility(View.VISIBLE);
            requestdatel.setVisibility(View.VISIBLE);
            bottomNavigationView=findViewById(R.id.bottomNavigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.item1:
                            Intent intent=new Intent(AddRequest.this,DoonerProfileActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.item2:
                            intent=new Intent(AddRequest.this,DoonerMainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.item3:
                            intent=new Intent(AddRequest.this,DoonerAnnouncementlist.class);
                            startActivity(intent);
                            break;

                        case R.id.item4:
                            AlertDialog.Builder alert = new AlertDialog.Builder(AddRequest.this);
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




    }
          if(hospitalid!=0) {
              usertype = "hospital";
                title.setText("Edit Announcement");
                btnsubmit.setText("Apply");
                btnremove.setVisibility(View.VISIBLE);
                announcementstatus.setVisibility(View.VISIBLE);
                quantity.setEnabled(true);
              bottomNavigationView=findViewById(R.id.bottomNavigation);
              bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                  @Override
                  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                      switch (item.getItemId()){
                          case R.id.item1:
                              Intent intent=new Intent(AddRequest.this,HospitalProfile.class);
                              startActivity(intent);
                              break;
                          case R.id.item2:
                              intent=new Intent(AddRequest.this,HospitalMain.class);
                              startActivity(intent);
                              break;
                          case R.id.item3:
                              intent=new Intent(AddRequest.this,HospitalAddEditAnnouncement.class);
                              startActivity(intent);
                              break;

                          case R.id.item4:
                              AlertDialog.Builder alert = new AlertDialog.Builder(AddRequest.this);
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





          }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(donerid!=0)
                {
                    Intent intent = new Intent(AddRequest.this,DoonerMainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(AddRequest.this,HospitalMain.class);
                    startActivity(intent);
                }


            }
        });


   requesttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       switch (position)
       {
           case 0:
               requeststatus = "approved";
              case 1:
               requeststatus = "pending";

       }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                // Inside Android OnClick method is where you will define Android datepicker.
                //Now you need to change Android DatePickerDialog to be able to use the current date values.
                datePickerDialog = new DatePickerDialog(AddRequest.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        donationdate.setText(day + "/" + (month + 1) + "/" + year); //Here you want to show the date values separated by (/) inside Android TextView.
                        //the month index value is (0-11). So if you choose the month February you will get the month value as January, if you choose the month December you will get the month value as November.
                    }
                }, year, month, dayOfMonth);
                //One thing left before you can see Android DatePickerDialog is to call the following method.
                datePickerDialog.show();


            }
        });



btnsubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        //choose request type

        try {
            Connection con = connectionClass.CONN();
            if (con != null) {


                if (donerid != 0) {
                    String checkrequest = "SELECT donerid FROM request where request.donerid ='" + donerid + "' and announcementid='" + id + "'";
                    Statement stnt2 = con.createStatement();
                    ResultSet rs2 = stnt2.executeQuery((checkrequest));
                    while (rs2.next()) {
                        result = rs2.getInt("donerid");
                                }

                    if (result == 0) {

                        String query = "INSERT INTO request (requestdate, requeststatus,requesttype,donerid,announcementid,expectdonationdate)  VALUES ('" + date.getText() + "','" + requeststatus + "','" + requesttype.getSelectedItem().toString() + "','" + donerid + "','" + id + "','" + donationdate.getText() + "')";
                        Statement stnt = con.createStatement();
                        stnt.executeUpdate(query);
                        Toast.makeText(AddRequest.this, "Request Submitted Successfuly ", Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(AddRequest.this, DoonerAnnouncementlist.class);
                        startActivity(intent);


                    } else {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(AddRequest.this);
                        dlgAlert.setMessage("You Already Have a request for this annoucement");
                        dlgAlert.setTitle("Doner On Call");
                        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                } else
                //hospital
                {

                    String query = "update bloodannouncement set announcement_status='" + announcementstatus.getSelectedItem().toString() + "' where announcementid ='" + id + "'";
                    Statement stnt = con.createStatement();
                    stnt.executeUpdate(query);


                    String query2 = "update bloodannouncement set quantity='" + quantity.getText()+ "' where announcementid ='" + id + "'";
                    Statement stnt2 = con.createStatement();
                    stnt.executeUpdate(query2);


                    Toast.makeText(AddRequest.this, "Announcement updated Successfuly ", Toast.LENGTH_SHORT).show();




                    Intent intent = new Intent(AddRequest.this, HospitalMain.class);
                    startActivity(intent);









                }
            }
            } catch(SQLException throwables)
            {
                throwables.printStackTrace();
            }

    }
});



btnremove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Connection conn = connectionClass.CONN();
        if ( conn!=null) {



            AlertDialog.Builder alert = new AlertDialog.Builder(AddRequest.this);
            alert.setTitle("Delete Announcement");
            alert.setMessage("Are you sure you want to remove the Announcement?");
            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    String query = "delete from bloodannouncement where announcementid ='" + id + "'";
                    try {
                        Statement stnt = conn.createStatement();
                        stnt.executeUpdate(query);
                        Toast.makeText(AddRequest.this, "Announcement Deleted Successfuly...", Toast.LENGTH_SHORT).show();

                        // close application




                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
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


    }

});

    }
}