package com.example.donoroncall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    Button butviewHos,butAddHos,butAccounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        butviewHos = (Button) findViewById(R.id.button8);
        butviewHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent=new Intent(AdminActivity.this,HospitalsList.class);
                startActivity(intent);

            }
        });

        butAddHos = (Button) findViewById(R.id.button9);
        butAddHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent=new Intent(AdminActivity.this,AddHospitalActivity.class);
                startActivity(intent);

            }
        });

        butAccounts = (Button) findViewById(R.id.button5);
        butAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent=new Intent(AdminActivity.this,AccountsListActivity.class);
                startActivity(intent);

            }
        });
    }

    public void viewHos()
    {

    }
}