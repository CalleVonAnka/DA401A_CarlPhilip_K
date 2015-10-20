package com.example.callevonanka.assignment_4.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.callevonanka.assignment_4.R;


/**
 * Created by Calle Von Anka on 2015-10-20.
 */
public class Dialog2 extends DialogFragment implements Dialog.OnClickListener {

    public Dialog2() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog2 = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title2)
                .setItems(R.array.item2, this);
        return dialog2.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast toast;
        switch (which) {
            case 0:
                toast = Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case 1:
                toast = Toast.makeText(getContext(), "Correct!", Toast.LENGTH_LONG);
                toast.show();
                break;
            case 2:
                toast = Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}
