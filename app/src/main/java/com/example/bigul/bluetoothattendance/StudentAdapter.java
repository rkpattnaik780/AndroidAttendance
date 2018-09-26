package com.example.bigul.bluetoothattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, ArrayList<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvBranch = (TextView) convertView.findViewById(R.id.tvBranch);
        TextView tvRoll = (TextView) convertView.findViewById(R.id.tvRoll);
        // Populate the data into the template view using the data object
        tvName.setText(student.name);
        tvBranch.setText(student.branch);
        tvRoll.setText(student.roll);
        // Return the completed view to render on screen
        return convertView;
    }
}
