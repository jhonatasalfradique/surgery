package fluxoconsultoria.ufrj.br.surgerynote;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ericreis on 6/12/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    private EditText datePickerButton;

    public DatePickerFragment(View v)
    {
        this.datePickerButton = (EditText) v;
    }

    @NonNull
    @SuppressWarnings("deprecation")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        // Date is deprecated (after jdk 1.1 the year offset is 1900 A.C)
        Date date = new Date(year - Constants.YEAR_OFFSET, month, day);
        this.datePickerButton.setText(sdf.format(date));

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        // Date is deprecated (after jdk 1.1 the year offset is 1900 A.C)
        Date date = new Date(year - Constants.YEAR_OFFSET, monthOfYear, dayOfMonth);
        this.datePickerButton.setText(sdf.format(date));
    }
}
