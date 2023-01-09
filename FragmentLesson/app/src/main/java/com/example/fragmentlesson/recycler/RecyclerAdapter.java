package com.example.fragmentlesson.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentlesson.R;

public class RecyclerAdapter extends RecyclerView.Adapter<TextHolder> {

    RecyclerItemCallback callback;
    public RecyclerAdapter(RecyclerItemCallback callback){
        this.callback = callback;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_recycler_layout,parent,false);
        return new TextHolder(view,callback);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, int position) {
        holder.textView.setText("Some text "+position);
    }

    @Override
    public int getItemCount() {
        return 9;
    }
}
