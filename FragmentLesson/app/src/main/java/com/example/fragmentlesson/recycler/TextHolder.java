package com.example.fragmentlesson.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentlesson.R;

public class TextHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextHolder(@NonNull View itemView,RecyclerItemCallback callback) {
        super(itemView);
        textView = itemView.findViewById(R.id.textV);
        itemView.setOnClickListener(v->{
            callback.onRecyclerItemClicked();
        });
    }
}
