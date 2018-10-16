package com.example.bigul.bluetoothattendance;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AttendanceActivity extends AppCompatActivity {

    BluetoothAdapter mBlueToothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter();

        InputStream inputStream = getResources().openRawResource(R.raw.student_data);
        CSVFileReader csvFile = new CSVFileReader(inputStream);
        ArrayList<Student> studentList = csvFile.read();
        Log.v("AttendanceActivity",studentList.toString());

        AttendanceAdapter adapter = new AttendanceAdapter(this, studentList);

        ListView listView = (ListView) findViewById(R.id.attendance_item);
        listView.setAdapter(adapter);


    }

    public void discoverDevices(View view){

        if(mBlueToothAdapter.isDiscovering()){

            mBlueToothAdapter.cancelDiscovery();

            checkBTPermission();

            mBlueToothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver2, discoverDevicesIntent);
        }
        if(!mBlueToothAdapter.isDiscovering()){

            checkBTPermission();

            mBlueToothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver2, discoverDevicesIntent);
        }
    }

    private BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d("MainActivity", "onReceive: ACTION FOUND.");

            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                Log.e("AttendanceActivity" , device.getAddress().toString());
                String currentAddress =  device.getAddress().toString() ;

                ListView studentList = (ListView) findViewById(R.id.attendance_item);

                for (int i = 0; i < studentList.getCount(); i++) {
                    View student ;
                    student = studentList.getChildAt(i);
                    TextView student_address = student.findViewById(R.id.tvMAC);
                    String stu_address = (String) student_address.getText();

                    Log.v("AttendanceActivity" , stu_address);

                    if(stu_address.equalsIgnoreCase(currentAddress)){
                        CheckBox attendance = (CheckBox) findViewById(R.id.checkbox);
                        attendance.setChecked(true);
                    }

                }

            }
        }
    };

    ///////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// TO SUBMIT ATTENDANCE //////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    public void submitAttendance(View view){

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));

        String stu_list = "" ;

        Log.v("AttendanceActivity" , "in submit attendance");

        String message = "The following students were present on " + dateFormat.format(date) + "\n\n" ;

        ListView studentList = (ListView) findViewById(R.id.attendance_item);

        for (int i = 0; i < studentList.getCount(); i++) {
            View student ;
            student = studentList.getChildAt(i);
            TextView student_name = student.findViewById(R.id.tvName);
            String stu_name = (String) student_name.getText();
            CheckBox present = (CheckBox) student.findViewById(R.id.checkbox);
            if(present.isChecked()){
                stu_list += stu_name + "\n" ;
            }

        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Attendance by Bluetooth Attendance");
        intent.putExtra(Intent.EXTRA_TEXT, message + stu_list );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkBTPermission(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
            }
        }else{
            Log.d("MainActivity", "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

}
