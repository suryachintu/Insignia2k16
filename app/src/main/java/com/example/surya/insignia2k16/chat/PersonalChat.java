package com.example.surya.insignia2k16.chat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.surya.insignia2k16.Constants;
import com.example.surya.insignia2k16.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalChat extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    RecyclerView mRecyclerView;
    FirebaseUser mFirebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference().child(Constants.PERSONAL_CHAT_USERS);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        for (int i = 0; i < 4; i++) {

            if (mFirebaseUser.getDisplayName() == Constants.insignia_heads[i]){
                //show heads layout

            }else{
                //show users layout
            }

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
