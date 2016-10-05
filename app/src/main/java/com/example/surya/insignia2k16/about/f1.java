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

        //Use this now
      //  mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        mAdapter = new TestRecyclerViewAdapter(mContentItems);

        //mAdapter = new RecyclerViewMaterialAdapter();
        mRecyclerView.setAdapter(mAdapter);
        if(mContentItems!=null) mContentItems.clear();
        {
            /*for (int i = 0; i < ITEM_COUNT; ++i) {
                mContentItems.add(new Object());
            }*/
            mContentItems.add(new Object("Rajiv Tripathi","7835014007","Chairmen","rajivtripathi@nitdelhi.ac.in",R.drawable.image3));
            mContentItems.add(new Object("Aditya Chamarthi","9910663180","Convener","chamarthi.aditya@nitdelhi.ac.in",R.drawable.image3));
            mContentItems.add(new Object("Yoga Sasidhar Reddy.B","8750267966","Co-Convener","141200014@nitdelhi.ac.in",R.drawable.image3));
            mContentItems.add(new Object("Sanyogitha Singh","9560494624","Sponsorship","sanyogitha.khatkar@nitdelhi.ac.in",R.drawable.image3));
            mContentItems.add(new Object("Sai Venkata Manohar","8130129479","Print and Design","saimanohar.venkata@gmail.com",R.drawable.image3));
            mContentItems.add(new Object("Himanshu Sharma","9953151638","Publicity","223himanshu@gmail.com",R.drawable.image3));
            mContentItems.add(new Object("Sravan Kumar","8527268276","Infrastructure and Logistics","sra2kumar42003210@gmail.com",R.drawable.image3));
            mContentItems.add(new Object("Sravan Kumar","8527268276","Website","141100042@gmail.com",R.drawable.image3));
            mContentItems.add(new Object("Surya Teja","7065118750","App Developer","suryaparsa@gmail.com",R.drawable.image3));
            mContentItems.add(new Object("Vengal Rao","7532954739","App Developer","vengalraoguttha@gmail.com",R.drawable.image3));
            mAdapter.notifyDataSetChanged();
        }
    }
}


