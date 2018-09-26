package com.example.bigul.bluetoothattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.input_email);
        String _user = username.getText().toString() ;
        EditText password = (EditText) findViewById(R.id.input_password);
        String _password = password.getText().toString();
        Log.v("MainActivity" , "triggered");
        Log.v("MainActivity" , "hey - " + _user);
        Log.v("MainActivity" , "" + _user.length());
        if( _user.equalsIgnoreCase("rkpattnaik780@gmail.com") && _password.equals("rama1234")){
            Log.v("MainActivity" , "inside if");
            Intent intent = new Intent(MainActivity.this, PanelActivity.class);
            startActivity(intent);
        }
    }
}
