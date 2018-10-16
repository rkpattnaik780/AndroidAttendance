package com.example.bigul.bluetoothattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    ListView listView;

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

        listView = (ListView) findViewById(R.id.student_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = ((TextView) view.findViewById(R.id.tvName)).getText().toString();
                Log.v("ViewActivity" , selected);
            }
        });

    }


    //ListView listView = (ListView) findViewById(R.id.student_item);



}
