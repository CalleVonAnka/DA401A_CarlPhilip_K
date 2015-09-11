package com.example.callevonanka.assignment_1;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowQuoteFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ShowQuoteFragment", "In onCreateView");

        View v = inflater.inflate(R.layout.activity_show_quote_frag, container, false);


        TextView tv = (TextView) v.findViewById(R.id.quoteTV);

        String currentDate = Helpers.getDate();
        String [] quoteArray = getResources().getStringArray(R.array.array);
        String randomQuote = Helpers.getQuote(quoteArray);

        tv.setText(randomQuote + "\n\n Printed: " + currentDate);
        return v;
    }


}
