package com.example.callevonanka.assignment_1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MAIN ACTIVITY", "Created");
        FragmentManager fragman = getSupportFragmentManager();
        FragmentTransaction fragtranny = fragman.beginTransaction();
        fragtranny.add(R.id.Main_Act, new GetQuoteFrag());
        fragtranny.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MAIN ACTIVITY", "Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MAIN ACTIVITY", "Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MAIN ACTIVITY", "Paused");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MAIN ACTIVITY", "Destroyed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MAIN ACTIVITY", "Stopped");
    }
}
