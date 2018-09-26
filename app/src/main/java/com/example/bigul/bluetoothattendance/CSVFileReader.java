package com.example.bigul.bluetoothattendance;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFileReader {
    InputStream inputStream;

    public CSVFileReader(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public ArrayList<Student> read(){
        ArrayList<Student> students = new ArrayList<Student>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            reader.readLine();
            while ((csvLine = reader.readLine()) != null) {

                Student s = new Student(csvLine.split(",")[0] , csvLine.split(",")[1] , csvLine.split(",")[2] , csvLine.split(",")[3]);
                Log.v("ViewActivity" , "Reader - " + s.name) ;
                students.add(s);
                Log.v("ViewActivity" , "ObjectReader - " + students.get(0).name) ;
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        Log.v("ViewActivity" , students.toString());
        return students;
    }
}
