package com.example.callevonanka.assignment_4;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Calle Von Anka on 2015-10-03.
 */
public class MarkerClickDialog extends DialogFragment implements Dialog.OnClickListener {

    public MarkerClickDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Welcome to treasure hunt!")
                .setMessage("Walk near this place to get your first question, and continue the walk on this road to find the other questions.")
                .setPositiveButton("OK", this);
        return dialog.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                break;
        }
    }
}
