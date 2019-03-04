package com.halulkin.lifer.CreatorsActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.halulkin.lifer.R;

import java.util.Calendar;

public class NewTargetTemplate extends AppCompatActivity {

    TextView etTargetDate, etTargetDateReminder;
    private int mYear, mMonth, mDay;
    String month, day;
    String targetDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_target_template);

        etTargetDate = findViewById(R.id.etTargetDate);
        etTargetDateReminder = findViewById(R.id.etTargetDateReminder);
    }

    public void onClickEditTextTargetDate(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        if ((monthOfYear + 1) < 10) {
                            month = "0" + (monthOfYear + 1);
                        } else {
                            month = String.valueOf(monthOfYear + 1);
                        }
                        if (dayOfMonth < 10) {
                            day = "0" + dayOfMonth;
                        } else {
                            day = String.valueOf(dayOfMonth);
                        }

                        targetDate = day + " - " + (month) + " - " + year;
                        etTargetDate.setText(targetDate);

                        calculateDifference(dayOfMonth, monthOfYear, year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }


    public void calculateDifference(int endDay, int endMonth, int endYear) {
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH, endDay);
        thatDay.set(Calendar.MONTH, endMonth); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, endYear);

        Calendar today = Calendar.getInstance();
        long diff = thatDay.getTimeInMillis() - today.getTimeInMillis(); //result in millis
        long days = diff / (24 * 60 * 60 * 1000);
        int leftDays = (int) days;

        if (leftDays > 1) {
            etTargetDateReminder.setText(getString(R.string.date_picker_dialog_message_1, leftDays));

        } else if (leftDays == 1) {
            etTargetDateReminder.setText(getString(R.string.date_picker_dialog_message_2));
        } else {
            etTargetDateReminder.setText(getString(R.string.date_picker_dialog_message_3));
        }
    }
}
