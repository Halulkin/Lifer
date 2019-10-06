package com.halulkin.lifer.CreatorsActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.halulkin.lifer.R;

import java.util.Calendar;
import java.util.Objects;

public class NewTargetTemplate extends AppCompatActivity {

    TextView etTargetDate, etTargetDateReminder;
    String month, day;
    String targetDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_target_template);

        Toolbar toolbar = findViewById(R.id.newTargetToolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        etTargetDate = findViewById(R.id.etTargetDate);
        etTargetDateReminder = findViewById(R.id.etTargetDateReminder);
    }

    public void onClickEditTextTargetDate(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view1, year, monthOfYear, dayOfMonth) -> {

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
