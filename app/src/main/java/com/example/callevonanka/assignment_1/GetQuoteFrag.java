package com.example.callevonanka.assignment_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GetQuoteFrag extends Fragment implements View.OnClickListener {

    public GetQuoteFrag(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("GET QUOTE FRAG", "Created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("GET QUOTE FRAG", "Create view");
        View v = inflater.inflate(R.layout.activity_get_quote_frag, container, false);
        View button = v.findViewById(R.id.buttonID);
        button.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Main_Act, new ShowQuoteFrag())
                .addToBackStack(null)
                .commit();
    }
}
