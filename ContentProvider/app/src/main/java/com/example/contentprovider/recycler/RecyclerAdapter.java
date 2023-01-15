package com.example.contentprovider.recycler;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.contentprovider.R;

public class RecyclerAdapter extends RecyclerView.Adapter<ContactsHolder> {

    Cursor cursor;

    public RecyclerAdapter(Cursor cursor){
        this.cursor = cursor;
    }

    @Override
    public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsHolder holder, int position) {
        holder.bind(cursor.getColumnName(position),cursor.getString(position));
    }

    @Override
    public int getItemCount() {
        return cursor.getColumnCount();
    }
}
