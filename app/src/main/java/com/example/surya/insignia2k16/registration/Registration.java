package com.example.surya.insignia2k16.registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surya.insignia2k16.Constants;
import com.example.surya.insignia2k16.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    TextView responseText,eventName;
    Button registerBtn;
    EditText groupHead,member2,member1,member3,email,phoneNumber;
    DatabaseReference databaseReference;
    int position,count;
    FirebaseRecyclerAdapter<Model,ModelViewHolder> recyclerAdapter;
    String pushId,head,mem1,mem2,mem3,emailStr,number;
    ArrayList<String> duplicates;
    Model mode;
    LinearLayout regLayout,successLayout;
    WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        responseText = (TextView)findViewById(R.id.edit_response);
        eventName = (TextView)findViewById(R.id.eventName);
        registerBtn = (Button)findViewById(R.id.register_button);
        groupHead = (EditText)findViewById(R.id.groupHead);
        member1 = (EditText)findViewById(R.id.member_one);
        member2 = (EditText)findViewById(R.id.member_two);
        member3 = (EditText)findViewById(R.id.member_three);
        email = (EditText)findViewById(R.id.email);
        phoneNumber = (EditText)findViewById(R.id.ph_number);
        regLayout = (LinearLayout)findViewById(R.id.registration);
        mWebView = (WebView)findViewById(R.id.webview);
        successLayout = (LinearLayout)findViewById(R.id.reg_success);
        mProgressBar = (ProgressBar)findViewById(R.id.pg_bar);


        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);

        position = getIntent().getIntExtra("p",0);
        count = Constants.eventsModels.get(position).count;

        eventName.setText(Constants.eventsModels.get(position).eventName);

        Toast.makeText(Registration.this, "hello " + position, Toast.LENGTH_SHORT).show();

        if (Constants.eventsModels.get(position).eventName.equals("NITD MUN")){
            mWebView.setVisibility(View.VISIBLE);
            successLayout.setVisibility(View.GONE);
            regLayout.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            mWebView.setWebChromeClient( new MyBrowser());

            mWebView.getSettings().setLoadsImagesAutomatically(true);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(Constants.MUN_URL);

        }
        else{
            loadLayout();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadLayout() {
        if (count == 1){
            Toast.makeText(Registration.this, "hello " + count, Toast.LENGTH_SHORT).show();
        }else if (count == 2){
            Toast.makeText(Registration.this, "hello " + count, Toast.LENGTH_SHORT).show();
            member1.setVisibility(View.VISIBLE);
        }else if (count==3){
            Toast.makeText(Registration.this, "hello " + count, Toast.LENGTH_SHORT).show();
            member1.setVisibility(View.VISIBLE);
            member2.setVisibility(View.VISIBLE);
        }else if (count==4){
            Toast.makeText(Registration.this, "hello " + count, Toast.LENGTH_SHORT).show();
            member1.setVisibility(View.VISIBLE);
            member2.setVisibility(View.VISIBLE);
            member3.setVisibility(View.VISIBLE);
        }

        duplicates = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();


        responseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupHead.setText(mode.getGpHead());
                email.setText(mode.getEmail());
                phoneNumber.setText(mode.getNumber());
                member1.setText(mode.getMem1());
                member2.setText(mode.getMem2());
                member3.setText(mode.getMem3());
                regLayout.setVisibility(View.VISIBLE);
                successLayout.setVisibility(View.GONE);

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                head = groupHead.getText().toString();
                mem1 = member1.getText().toString();
                mem2 = member2.getText().toString();
                mem3 = member3.getText().toString();
                emailStr = email.getText().toString();
                number = phoneNumber.getText().toString();
//                databaseReference.push().setValue("a");
                Toast.makeText(Registration.this, "hi click", Toast.LENGTH_SHORT).show();

                if (checkEmail(emailStr) && checkNumber(number)&& checkHead(head)){

                    mode = new Model(head,mem1,mem2,mem3,emailStr,number);
                    databaseReference.child(eventName.getText().toString()).push().setValue(mode);
                    regLayout.setVisibility(View.GONE);
                    successLayout.setVisibility(View.VISIBLE);

                }
//                else {
//                    if (mem1.equals(""))
//                    member1.setError("Please fill the details");
//                    if (mem2.equals(""))
//                    member2.setError("Please fill the details");
//                    if (mem3.equals(""))
//                    member3.setError("Please fill the details");
//                }


            }
        });


    }

    private boolean checkHead(String head) {

        if (head.equals("")){
            groupHead.setError("Please fill the details");
            return false;
        }else
            return true;

    }

    private boolean checkNumber(String number) {

        if (number.trim().length()!=10) {
            phoneNumber.setError("Check Phone Number");
            return false;
        }
        else
            return true;

    }

    private boolean checkEmail(String mail) {

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            return true;
        } else {
            email.setError("Check Email");
            return false;
        }
    }

    private class ModelViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ModelViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView)itemView.findViewById(R.id.name);
        }
    }

    private class MyBrowser extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Registration.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

    private void setValue(int newProgress) {
        mProgressBar.setProgress(newProgress);
    }
}
