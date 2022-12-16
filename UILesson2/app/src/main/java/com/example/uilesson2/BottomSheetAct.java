package com.example.uilesson2;

import static android.widget.Toast.LENGTH_SHORT;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.uilesson2.databinding.ActivityBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class BottomSheetAct extends AppCompatActivity {

    ActivityBottomSheetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomSheetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.alertDialogBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog().show();
            }
        });

        binding.datePickerDialogBt.setOnClickListener(v -> {
            createDateDialog();
        });

        binding.timePickerDialogBt.setOnClickListener(v -> {
            createShowTimeDialog();
        });

        binding.customDialogBt.setOnClickListener(v->{
            createCustomDialog();
        });

        binding.classCustomDialogBt.setOnClickListener(v->{
            CustomDialog customDialog = new CustomDialog(this);
            customDialog.setCancelable(false);
            customDialog.show();
        });


        BottomSheetBehavior btShBehavior = BottomSheetBehavior.from(binding.bottomSheet);

        btShBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == STATE_HIDDEN) {
                    binding.bottomSheet.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btShBehavior.setState(STATE_COLLAPSED);
                        }
                    }, 1500);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    private void createCustomDialog() {

        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.custom_dialog);


        builder.show();
    }

    private void createShowTimeDialog() {

        Calendar calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                listener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);

        timePickerDialog.show();
    }

    private void createDateDialog() {

        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        };

        DatePickerDialog dialog = new DatePickerDialog(this,
                listener,
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose some thing");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BottomSheetAct.this, "ok", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BottomSheetAct.this, "cancel", Toast.LENGTH_LONG).show();
            }
        });
        return builder.create();
    }
}