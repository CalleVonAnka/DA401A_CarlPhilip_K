package com.example.callevonanka.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.Main_Activity_ID, new MovieListFrag())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MAIN","Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MAIN", "Resumed");
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MAIN", "Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MAIN", "Stopped");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("MAIN", "Destroyed");
    }
}
