package com.example.callevonanka.assignment_5;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Calle Von Anka on 2015-11-02.
 */
public class QuoteAdapter extends ArrayAdapter<String> {

    List<String> mQuoteList;
    LayoutInflater mLayoutInflater;
    Context context;
    private SparseBooleanArray mSelectedItems;

    public QuoteAdapter(Context context, int resource, List<String> mQuoteList){
        super(context, resource, mQuoteList);
        mSelectedItems = new SparseBooleanArray();
        this.context = context;
        this.mQuoteList = mQuoteList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void remove(String object) {
        mQuoteList.remove(object);
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItems.get(position));
    }

    public void removeSelection() {
        mSelectedItems = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItems.put(position, value);
        else
            mSelectedItems.delete(position);
        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItems;
    }

    @Override
    public int getCount() {
        return mQuoteList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("QUOTEADAPTER", "In getView");
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.quote_item,parent,false);
        }

        String quote= mQuoteList.get(position);

        TextView quotes = (TextView) convertView.findViewById(R.id.quote_item_TV);
        quotes.setText(quote);
        return convertView;
    }
}
