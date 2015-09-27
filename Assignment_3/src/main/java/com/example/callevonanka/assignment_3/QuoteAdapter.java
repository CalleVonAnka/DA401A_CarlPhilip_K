package com.example.callevonanka.assignment_3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Calle Von Anka on 2015-09-15.
 */
public class QuoteAdapter extends BaseAdapter{

    List<String> mQuoteList;
    LayoutInflater mLayoutInflater;

    public QuoteAdapter(List<String> mQuoteList, LayoutInflater mLayoutInflater) {
        this.mQuoteList = mQuoteList;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public int getCount() {
        return mQuoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mQuoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("QUOTEADAPTER", "In getView");
        convertView = mLayoutInflater.inflate(R.layout.quote_item,parent,false);

        String quote= mQuoteList.get(position);

        TextView quotes = (TextView) convertView.findViewById(R.id.quote_item_TV);
        quotes.setText(quote);
        return convertView;
    }
}
