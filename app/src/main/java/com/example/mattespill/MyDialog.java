package com.example.mattespill;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {
    private DialogClickListener callback;

    public interface DialogClickListener {
        public void onStatsClick();
        public void onAvbrytClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback=(DialogClickListener)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Kallende klasse må implemente interfacet!");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.stats).setPositiveButton(R.string.stats,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                callback.onStatsClick();
                            }
                        }
                )
                .setNegativeButton(R.string.btnCancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                callback.onAvbrytClick();
                            }
                        }
                )
                .create();
    }
}
