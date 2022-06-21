package com.example.donoroncall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent=new Intent(LaunchActivity.this, LoginActivity.class);
            startActivity(intent);

        });
        button =(Button) findViewById(R.id.button2);
        button.setOnClickListener(view -> {
            Intent intent=new Intent(LaunchActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
    }

}