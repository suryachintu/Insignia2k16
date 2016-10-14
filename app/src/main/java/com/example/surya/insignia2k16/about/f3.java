package com.example.surya.insignia2k16.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surya.insignia2k16.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.*;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by havihavish on 04-07-2016.
 */
public class f3 extends Fragment {

    DatabaseReference reference;
    private FirebaseRecyclerAdapter<faqs, FaqsViewHolder> mRecyclerAdapter;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    public static f3 newInstance() {
        return new f3();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();


        layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();

        mRecyclerAdapter = new FirebaseRecyclerAdapter<faqs, FaqsViewHolder>(faqs.class,R.layout.card3,FaqsViewHolder.class,reference.child("f")) {
            @Override
            protected void populateViewHolder(FaqsViewHolder viewHolder, faqs model, int position) {

                viewHolder.answer.setText(model.getAnswer());
                viewHolder.ques.setText(model.getQuestion());
                Toast.makeText(getActivity(), "populate view holder", Toast.LENGTH_SHORT).show();
            }
        };

//        faqs f = new faqs("insignia","NITD");
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return rootView;
    }

    private class FaqsViewHolder extends RecyclerView.ViewHolder{
        public TextView ques;
        public TextView answer;

        public FaqsViewHolder(View itemView) {
            super(itemView);
            ques = (TextView)itemView.findViewById(R.id.ques);
            answer = (TextView)itemView.findViewById(R.id.answer);
        }

    }
}
