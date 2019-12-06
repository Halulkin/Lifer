package com.halulkin.lifer.CreatorsActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.halulkin.lifer.DBHelper;
import com.halulkin.lifer.R;
import com.halulkin.lifer.TargetsFragment.TargetsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTargetTemplate extends AppCompatActivity {

    public DBHelper db;
    String month, day;
    String targetDate;

    @BindView(R.id.fb_save_target)
    FloatingActionButton fbSaveTarget;

    @BindView(R.id.etTargetTitle)
    EditText etTargetTitle;
    @BindView(R.id.tvChooseTargetDate)
    TextView tvChooseTargetDate;
    @BindView(R.id.tvTargetDateReminder)
    TextView tvTargetDateReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_target_template);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.newTargetToolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        db = new DBHelper(this);

    }

    @OnClick(R.id.fb_save_target)
    public void setFbSaveTarget() {
        TargetsModel newTargetsModel = new TargetsModel();
        newTargetsModel.setTargetDate(tvChooseTargetDate.getText().toString());
        newTargetsModel.setTargetName(etTargetTitle.getText().toString());

        db.addTarget(newTargetsModel);
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

                    targetDate = day + "/" + (month) + "/" + year;
//                    tvChooseTargetDate.setText(targetDate);

                    try {
                        formatDate(targetDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    calculateDifference(dayOfMonth, monthOfYear, year);

                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    public void formatDate(String targetDate) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(targetDate);

        dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        String dateTime = dateFormat.format(date);
        tvChooseTargetDate.setText(dateTime);
    }

//    public void formatDate(String targetDate) throws ParseException {
//
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = format.parse(targetDate);
//
//
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
//        date = new Date();
//        String dateTime = dateFormat.format(date);
//        tvChooseTargetDate.setText(dateTime);
//    }

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
            tvTargetDateReminder.setText(getString(R.string.date_picker_dialog_message_1, leftDays));

        } else if (leftDays == 1) {
            tvTargetDateReminder.setText(getString(R.string.date_picker_dialog_message_2));
        } else {
            tvTargetDateReminder.setText(getString(R.string.date_picker_dialog_message_3));
        }
    }


}
