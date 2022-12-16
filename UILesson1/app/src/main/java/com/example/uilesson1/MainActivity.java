package com.example.uilesson1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(v -> {
            toastCall();
        });


        TextView textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(v -> {
            snackBarCall(v);
        });

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setOnClickListener(v->{
            Intent intent = new Intent(this,CollapsingToolBar.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void snackBarCall(View v){
        Snackbar snackbar = Snackbar.make(v,"Our SnackBar",Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.lime_green));
        View snackBarView = snackbar.getView();
        TextView snBrText = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        snBrText.setTextColor(getResources().getColor(R.color.lime_green));

        View.OnClickListener listener  = v1 -> snackbar.dismiss();

        snackbar.setAction("Hide",listener);
        snackbar.show();
    }

    private void toastCall(){
        Toast toast = Toast.makeText(this,"Custom toast",Toast.LENGTH_LONG);

        View customToast = toast.getView();

        TextView textCT = customToast.findViewById(android.R.id.message);
        textCT.setTextSize(16);
        textCT.setTextColor(getResources().getColor(R.color.lime_green));
        textCT.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_foreground,0,0,0);
        toast.setGravity(Gravity.CENTER|Gravity.BOTTOM,0,0);
        customToast.getBackground().setTint(getResources().getColor(R.color.gray));
        toast.show();
    }
}