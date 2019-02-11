package com.notebook.mirambeaucare.notebook.ui.timeDate;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Time picker Dialog fragment, shows time dialog, callback is in MainActivity.
 */
public class TimePickerFragment extends DialogFragment {

    Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(),
                (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute,
                true);
    }
}
