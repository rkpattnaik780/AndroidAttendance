package com.example.bigul.bluetoothattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        InputStream inputStream = getResources().openRawResource(R.raw.student_data);
        CSVFileReader csvFile = new CSVFileReader(inputStream);
        ArrayList<Student> studentList = csvFile.read();
        Log.v("AttendanceActivity",studentList.toString());

        AttendanceAdapter adapter = new AttendanceAdapter(this, studentList);

        ListView listView = (ListView) findViewById(R.id.attendance_item);
        listView.setAdapter(adapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(AttendanceActivity.this, PanelActivity.class);
                startActivity(intent);
            }
        });*/
    }

}
