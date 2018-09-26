package com.example.bigul.bluetoothattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("ViewActivity" , "Initialized");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        InputStream inputStream = getResources().openRawResource(R.raw.student_data);
        CSVFileReader csvFile = new CSVFileReader(inputStream);
        ArrayList<Student> studentList = csvFile.read();

        Log.v("ViewActivity" , "main" + studentList.get(1).name);

        StudentAdapter adapter = new StudentAdapter(this, studentList);

        ListView listView = (ListView) findViewById(R.id.student_item);
        listView.setAdapter(adapter);
        // Log.v("ViewActivity" , studentList.get(1).toString());
        //Log.v("ViewActivity" , "Size - " + studentList.get());
    }


}
