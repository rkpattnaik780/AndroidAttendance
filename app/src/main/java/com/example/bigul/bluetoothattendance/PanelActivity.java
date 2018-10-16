package com.example.bigul.bluetoothattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void goToView(View view){

        Intent intent = new Intent(PanelActivity.this, ViewActivity.class);
        startActivity(intent);
    }

    public void takeAttendance(View view){

        Intent intent = new Intent(PanelActivity.this, AttendanceActivity.class);
        startActivity(intent);

    }

    public void addMember(View view){

        Intent intent = new Intent(PanelActivity.this , AddActivity.class);
        startActivity(intent);
    }


}
