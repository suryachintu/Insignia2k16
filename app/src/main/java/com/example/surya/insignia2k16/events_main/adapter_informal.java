package com.example.surya.insignia2k16.events_main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.surya.insignia2k16.Constants;
import com.example.surya.insignia2k16.R;

/**
 * Created by Surya on 13-09-2016.
 */
public class Adapter_informal extends RecyclerView.Adapter<Adapter_informal.MyViewHolder> {

    public Adapter_informal() {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_informal, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mTextView.setText(Constants.mEvents_names[position]);
        holder.mImageView.setImageResource(Constants.mEvents_posters[position]);

    }

    @Override
    public int getItemCount() {
        return Constants.mEvents_names.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView)itemView.findViewById(R.id.informal_text);
            mImageView = (ImageView) itemView.findViewById(R.id.informal_imageView);

        }
    }
}
