package com.example.surya.insignia2k16.events_main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.surya.insignia2k16.Constants;
import com.example.surya.insignia2k16.R;

/**
 * Created by surya on 23-08-2016.
 */

public class Events_Adapter extends RecyclerView.Adapter<Events_Adapter.MyViewHolder> {


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mList_item_text;
        public TextView mList_item_venue,Co_ods,event_time;
        public ImageView mList_image_view,alphaImage;
        public MyViewHolder(View view) {
            super(view);
            mList_item_text = (TextView)view.findViewById(R.id.event_name_textview);
            mList_image_view = (ImageView)view.findViewById(R.id.event_image_view);
            mList_item_venue = (TextView)view.findViewById(R.id.venue_name);
            Co_ods = (TextView)view.findViewById(R.id.co_od);
            event_time = (TextView)view.findViewById(R.id.event_time);
            alphaImage  = (ImageView)view.findViewById(R.id.AplhaImage);
        }
    }

    public Events_Adapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_recyclerview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position<Constants.eventsModels.size()){

            holder.mList_item_text.setText(Constants.eventsModels.get(position).eventName);
            holder.mList_image_view.setImageResource(Constants.eventsModels.get(position).posterId);
            holder.mList_item_venue.setText(Constants.eventsModels.get(position).venue);
            holder.Co_ods.setText(Constants.eventsModels.get(position).co_od);
            holder.event_time.setText(Constants.eventsModels.get(position).event_time);
            holder.alphaImage.setVisibility(View.INVISIBLE);
        }else {
            holder.alphaImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }
}