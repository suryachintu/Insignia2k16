package com.example.surya.insignia2k16.about;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.surya.insignia2k16.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class f1 extends Fragment {

    static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Object> mContentItems = new ArrayList<>();
    Context context;

    public static f1 newInstance() {
        return new f1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new TestRecyclerViewAdapter(mContentItems);

        //mAdapter = new RecyclerViewMaterialAdapter();
        mRecyclerView.setAdapter(mAdapter);
        if(mContentItems!=null) mContentItems.clear();
        {
            /*for (int i = 0; i < ITEM_COUNT; ++i) {
                mContentItems.add(new Object());
            }*/
            mContentItems.add(new Object("Rajiv Tripathi","7835014007","Chairman","rajivtripathi@nitdelhi.ac.in",R.drawable.rajiv));
            mContentItems.add(new Object("Aditya Chamarthi","9910663180","Convener","chamarthi.aditya@nitdelhi.ac.in",R.drawable.aditya));
            mContentItems.add(new Object("Yoga Sasidhar Reddy.B","8750267966","Co-Convener","141200014@nitdelhi.ac.in",R.drawable.yoga));
            mContentItems.add(new Object("Tejesh","9971574459","Hospitality","tejesh36p@gmail.com",R.drawable.tejesh));
            mContentItems.add(new Object("Sanyogitha Singh","9560494624","Sponsorship","sanyogitha.khatkar@nitdelhi.ac.in",R.drawable.sanyogita));
            mContentItems.add(new Object("Sanyogitha Singh","9560494624","Sponsorship","sanyogitha.khatkar@nitdelhi.ac.in",R.drawable.sanyogita));
            mContentItems.add(new Object("Sai Venkata Manohar","8130129479","Print and Design","saimanohar.venkata@gmail.com",R.drawable.manohar));
            mContentItems.add(new Object("Himanshu Sharma","9953151638","Publicity","223himanshu@gmail.com",R.drawable.himanshu));
            mContentItems.add(new Object("Sravan Kumar","8527268276","Infrastructure and Logistics","sra2kumar42003210@gmail.com",R.drawable.sravan));
            mContentItems.add(new Object("Anurag Giri","8527268276","Website","anuragmgiri@gmail.com",R.drawable.anurag));
            mContentItems.add(new Object("Surya Teja","7065118750","App Developer","suryaparsa@gmail.com",R.drawable.surya));
            mContentItems.add(new Object("Vengal Rao","7532954739","App Developer","vengalraoguttha@gmail.com",R.drawable.vengal));
            mContentItems.add(new Object("N.Havish","9560468502","Discipline","havihavish@gmail.com",R.drawable.havish));
            mAdapter.notifyDataSetChanged();
        }
    }
}


