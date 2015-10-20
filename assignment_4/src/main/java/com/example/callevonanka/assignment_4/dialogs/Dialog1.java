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
public class Dialog1 extends DialogFragment implements Dialog.OnClickListener {

    public Dialog1() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title1)
                .setItems(R.array.item1, this);
        return dialog1.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast toast;
        switch (which) {
            case 0:
                toast = Toast.makeText(getContext(), "You guessed right!", Toast.LENGTH_LONG);
                toast.show();
                break;
            case 1:
                toast = Toast.makeText(getContext(), "Impossible..", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case 2:
                toast = Toast.makeText(getContext(), "incorrect", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}
