package com.example.uilesson2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.uilesson2.databinding.CustomDialogBinding;

public class CustomDialog extends Dialog {

    private CustomDialogBinding binding;
    private Context context;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CustomDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ok.setOnClickListener(v->{
            Toast.makeText(context,"Ok",Toast.LENGTH_LONG).show();
            dismiss();
        });

        binding.cancel.setOnClickListener(v->{
            Toast.makeText(context,"cancel",Toast.LENGTH_LONG).show();
            dismiss();
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
