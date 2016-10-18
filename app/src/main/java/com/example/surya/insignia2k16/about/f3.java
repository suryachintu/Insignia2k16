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
import android.widget.Adapter;
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

    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<com.example.surya.insignia2k16.about.Object> mContentItems;
    RecyclerView.Adapter mRecyclerAdapter;

    public static f3 newInstance() {
        return new f3();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mContentItems = new ArrayList<>();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();


        layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setHasFixedSize(true);
//
//
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Rajiv Tripathi","7835014007","Chairman","rajivtripathi@nitdelhi.ac.in",R.drawable.rajiv));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Aditya Chamarthi","9910663180","Convener","chamarthi.aditya@nitdelhi.ac.in",R.drawable.aditya));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Yoga Sasidhar Reddy.B","8750267966","Co-Convener","141200014@nitdelhi.ac.in",R.drawable.yoga));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Tejesh","9971574459","Hospitality","tejesh36p@gmail.com",R.drawable.tejesh));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Sanyogitha Singh","9560494624","Sponsorship","sanyogitha.khatkar@nitdelhi.ac.in",R.drawable.sanyogita));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Sai Venkata Manohar","8130129479","Print and Design","saimanohar.venkata@gmail.com",R.drawable.manohar));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Himanshu Sharma","9953151638","Publicity","223himanshu@gmail.com",R.drawable.himanshu));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Sravan Kumar","8527268276","Infrastructure and Logistics","sra2kumar42003210@gmail.com",R.drawable.sravan));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Anurag Giri","8527268276","Website","anuragmgiri@gmail.com",R.drawable.anurag));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Surya Teja","7065118750","App Developer","suryaparsa@gmail.com",R.drawable.surya));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("Vengal Rao","7532954739","App Developer","vengalraoguttha@gmail.com",R.drawable.vengal));
//        mContentItems.add(new com.example.surya.insignia2k16.about.Object("N.Havish","9560468502","Discipline","havihavish@gmail.com",R.drawable.havish));
//        mRecyclerAdapter.notifyDataSetChanged();
//
//        mRecyclerView.setAdapter(mRecyclerAdapter);
        return rootView;
    }

}
