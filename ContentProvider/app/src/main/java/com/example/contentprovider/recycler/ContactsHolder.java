package com.example.contentprovider.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.contentprovider.R;

public class ContactsHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView number;

    public ContactsHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textView);
        number = itemView.findViewById(R.id.textView2);
    }

    public void bind(String columnName, String string){
        name.setText(columnName);
        number.setText(string);
    }
}
