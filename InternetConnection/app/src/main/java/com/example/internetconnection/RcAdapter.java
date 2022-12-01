package com.example.internetconnection;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.MovieHolder> {

    ArrayList<MovieItem> localData;

    public RcAdapter(ArrayList<MovieItem> localData){
        this.localData = localData;

    }

    public class MovieHolder extends  RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public MovieHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.movie_name);
            image = itemView.findViewById(R.id.movie_image);
        }

        public void bind(MovieItem item){
            name.setText(item.name);
            Picasso.get()
                    .load(item.image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(image);
        }
    }

    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_layout,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bind(localData.get(position));
    }

    @Override
    public int getItemCount() {
        return localData.size();
    }


}
