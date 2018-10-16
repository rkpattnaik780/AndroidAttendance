package com.example.bigul.bluetoothattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void addStudent(View view){
        String name,branch,roll,address ;
        EditText inputName = (EditText) findViewById(R.id.input_name);
        name = inputName.getText().toString();
        EditText inputBranch = (EditText) findViewById(R.id.input_branch);
        branch = inputBranch.getText().toString();
        EditText inputRoll = (EditText) findViewById(R.id.input_roll);
        roll = inputRoll.getText().toString();
        EditText inputAddress = (EditText) findViewById(R.id.input_addr);
        address = inputAddress.getText().toString();

        String addedLine = name + "," + branch + "," + roll + "," + address ;

        Log.v("AddActivity" , addedLine);
    }
}
