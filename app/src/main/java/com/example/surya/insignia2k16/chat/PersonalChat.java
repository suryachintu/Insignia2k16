package com.example.surya.insignia2k16.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surya.insignia2k16.Constants;
import com.example.surya.insignia2k16.R;
import com.example.surya.insignia2k16.chat.model.Messages;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonalChat extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    RecyclerView mRecyclerView;
    FirebaseUser mFirebaseUser;
    FirebaseRecyclerAdapter<Messages,UserViewHolder> mFirebaseAdapter;
    LinearLayoutManager layoutManager;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();

        if (mFirebaseUser != null) {
            userName = mFirebaseUser.getDisplayName();
        }

        if (userName != null && (userName.equals(Constants.insignia_heads[0]) || userName.equals(Constants.insignia_heads[1]) ||
                userName.equals(Constants.insignia_heads[2]) || userName.equals(Constants.insignia_heads[3]))) {

            arrangeOwnerAdapter();

        }else {
            arrangeUserAdapter();
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void arrangeUserAdapter() {



    }

    private void arrangeOwnerAdapter() {
        mReference = FirebaseDatabase.getInstance().getReference().child(Constants.CHATROOMS).child("Surya Parsa").child("INBOX");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(PersonalChat.this, dataSnapshot.getChildrenCount() + "hi", Toast.LENGTH_SHORT).show();

                Constants.users.clear();
                int i = 0;
                for (DataSnapshot post : dataSnapshot.getChildren()) {

                    Messages m = post.getValue(Messages.class);

                    Toast.makeText(PersonalChat.this, m.getMessage() +  post.getKey(), Toast.LENGTH_SHORT).show();

                    Constants.users.add(i,post.getKey());
                    i++;
                }
                
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
        mRecyclerView = (RecyclerView)findViewById(R.id.chat_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Messages, UserViewHolder>(Messages.class,R.layout.chat_list,UserViewHolder.class,mReference.child("DUMMY")) {
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, Messages model, int position) {

                viewHolder.mTextView.setText(model.getMessage());
                Toast.makeText(PersonalChat.this, "enter", Toast.LENGTH_SHORT).show();
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    mRecyclerView.scrollToPosition(positionStart);
                }
            }
        });




        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);*/



    }

    private static class UserViewHolder  extends  RecyclerView.ViewHolder{

        public TextView mTextView;

        public UserViewHolder(View itemView) {
            super(itemView);

//            mTextView = (TextView)itemView.findViewById(R.id.chat_text);
        }
    }
}
