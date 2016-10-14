package com.example.surya.insignia2k16.events_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surya.insignia2k16.Constants;
import com.example.surya.insignia2k16.R;
import com.example.surya.insignia2k16.about.AboutInsignia;
import com.example.surya.insignia2k16.chat.GlobalChat;
import com.example.surya.insignia2k16.chat.PersonalChat;
import com.example.surya.insignia2k16.chat.auth.Login;
import com.example.surya.insignia2k16.instafeed.Instafeed;
import com.example.surya.insignia2k16.locate.Locate;
import com.example.surya.insignia2k16.welcomeScreens.PrefManager;
import com.example.surya.insignia2k16.welcomeScreens.WelcomeActivity;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,GoogleApiClient.OnConnectionFailedListener {

    public static final int REQUEST_INVITE=0;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mReference;
    FirebaseUser mFirebaseUser;
    FirebaseRemoteConfig mRemoteConfig;
    RecyclerView mRecyclerView;
    GoogleApiClient mGoogleApiClient;
    ImageView mImageView;
    private TextView mTextView;
    private Events_Adapter myAdapter;
    private PrefManager prefManager;
//    ArrayList<EventsModel> eventsModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (!prefManager.islogged()) {
                    startActivity(new Intent(MainActivity.this, PersonalChat.class));
                }else {
                    Toast.makeText(MainActivity.this, "Requires google sign in", Toast.LENGTH_SHORT).show();
                }
            }
                
        });

        // Initialize Firebase Remote Config.
        mRemoteConfig = FirebaseRemoteConfig.getInstance();

        // Define Firebase Remote Config Settings.
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettings =
                            new FirebaseRemoteConfigSettings.Builder()
                                .setDeveloperModeEnabled(true)
                                .build();

        // Define default config values. Defaults are used when fetched config values are not
        // available. Eg: if an error occurred fetching values from the server.
        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put("event_venue", "To be declared");
        for (int i = 0; i < Constants.mEvents_names.length; i++) {
            defaultConfigMap.put("event" + i, "To be declared");
        }
        // Apply config settings and default values.
        mRemoteConfig.setConfigSettings(firebaseRemoteConfigSettings);
        mRemoteConfig.setDefaults(defaultConfigMap);

        // Fetch remote config.
        fetchConfig();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this, this)
                .build();

        // Check for App Invite invitations and launch deep-link activity if possible.
        // Requires that an Activity is registered in AndroidManifest.xml to handle
        // deep-link URLs.
        boolean autoLaunchDeepLink = true;
        appInvite(autoLaunchDeepLink);


        //recycler view
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);

        myAdapter = new Events_Adapter();

        mRecyclerView.setAdapter(myAdapter);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();

        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        if (position<Constants.eventsModels.size()) {
                            Intent intent = new Intent(MainActivity.this, detail_events.class);
                            intent.putExtra("p", position);
                            startActivity(intent);
                        }
                    }
                })
        );


        /*end of newly added*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        mTextView = (TextView)header.findViewById(R.id.email_main);
        mImageView = (ImageView) header.findViewById(R.id.profile_image_main);
    }

    //App Invites
    private void appInvite(boolean autoLaunchDeepLink) {
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(
                        new ResultCallback<AppInviteInvitationResult>() {
                            @Override
                            public void onResult(AppInviteInvitationResult result) {
                                Log.d("vengal", "getInvitation:onResult:" + result.getStatus());
                                if (result.getStatus().isSuccess()) {
                                    // Extract information from the intent
                                    Intent intent = result.getInvitationIntent();
                                    String deepLink = AppInviteReferral.getDeepLink(intent);
                                    String invitationId = AppInviteReferral.getInvitationId(intent);

                                    // Because autoLaunchDeepLink = true we don't have to do anything
                                    // here, but we could set that to false and manually choose
                                    // an Activity to launch to handle the deep link here.
                                    // ...
                                }
                            }
                        });
    }

    //Remote config
    private void fetchConfig() {

             // Fetch the config to determine the allowed length of messages.
            long cacheExpiration = 3600; // 1 hour in seconds
            // If developer mode is enabled reduce cacheExpiration to 0 so that
            // each fetch goes to the server. This should not be used in release
            // builds.
            if (mRemoteConfig.getInfo().getConfigSettings()
                    .isDeveloperModeEnabled()) {
                cacheExpiration = 0;
            }
            mRemoteConfig.fetch(cacheExpiration)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Make the fetched config available via
                            // FirebaseRemoteConfig get<type> calls.
                            mRemoteConfig.activateFetched();
                            applyRetrievedLengthLimit();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // There has been an error fetching the config
                            Log.w("hi", "Error fetching config: " +
                                    e.getMessage());
                            Toast.makeText(MainActivity.this, "Error in fetching updates", Toast.LENGTH_SHORT).show();
                            applyRetrievedLengthLimit();
                        }
                    });

    }

    private void applyRetrievedLengthLimit() {


         Constants.event_name = mRemoteConfig.getString("event_venue");
         String name = "event";
         int j=0;
        for (int i = 0; i < 12; i++) {
            Constants.eventsModels.clear();
            Constants.FLAGARRAY[i] = mRemoteConfig.getBoolean(name + i);
               j = 0;
        }

        for (int i = 0; i < 12; i++) {
            if (Constants.FLAGARRAY[i]){
              Constants.eventsModels.add(j,new EventsModel(Constants.mEvents_names[i],Constants.mVenues[i],Constants.mEvent_cordinators[i],Constants.mEvent_time[i],
                                            Constants.mEvents_description[i],Constants.mEvents_posters[i],Constants.requiredMembers[i]));
                j++;
            }
        }

        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            if (prefManager.islogged())
                prefManager.setUserLoggedIn(false);
            else
                mFirebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();
            return true;
        }
        if (id == R.id.action_refresh) {
            fetchConfig();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_chat) {

            if (prefManager.islogged()){
                Toast.makeText(MainActivity.this, "Requires Google Sign In", Toast.LENGTH_LONG).show();
            }else {
                startActivity(new Intent(MainActivity.this, GlobalChat.class));
            }

        } else if (id == R.id.nav_Instafeed) {

            startActivity(new Intent(MainActivity.this, Instafeed.class));

        } else if (id == R.id.nav_about) {

            startActivity(new Intent(MainActivity.this, AboutInsignia.class));

        } else if (id == R.id.nav_locate) {

            startActivity(new Intent(MainActivity.this, Locate.class));

        } else if (id == R.id.nav_share) {
                onInviteClicked();
        } else if (id == R.id.nav_send) {

            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "insignia.alphaz@gmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Team," + "");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
            return true;

        }else if (id == R.id.nav_informal){

            startActivity(new Intent(MainActivity.this,Informal.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("vengal", "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d("vengal", "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }

    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                //getString(R.string.invitation_custom_image)))
                .setEmailHtmlContent("<html>\n" +
                        "<body style=\"text-align:center;\">\n" +
                        "<img src=\"https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/12118848_1530913257200726_4774124256511356201_n.jpg?oh=70750b04f3ce8df15d2128fba80f883a&oe=5871A6AC\"/>\n" +
                        "\n" +
                        "<h1>Insignia 2K16</h1>\n" +
                        "<p> \n" +
                        "        This app is a complete guide to all the formal and informal events of the fest.<br>\n" +
                        "\tYou can keep an eye on your favourite events, get notified.\n" +
                        "        and and get connected to the organizers of the fest.<br>\n" +
                        "        Download the app and SignUp to become a part of the sophomore edition of Insignia.\n" +
                        "\n" +
                        "\n" +
                        "<a href=\"%%APPINVITE_LINK_PLACEHOLDER%%\">Install the app and have great fun.</a>\n" +
                        "</p>\n" +
                        "</body>\n" +
                        "</html>")
                .setEmailSubject(getString(R.string.invitation_subject))
                //.setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent,REQUEST_INVITE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        prefManager = new PrefManager(this);

//        Toast.makeText(MainActivity.this, "hi" + prefManager.islogged(), Toast.LENGTH_SHORT).show();

        if (prefManager.isFirstTimeLaunch()) {
//            launchHomeScreen();
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            finish();
            return;
        }
        if (!prefManager.islogged() && mFirebaseUser == null){
//            Toast.makeText(MainActivity.this, "goto login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();
            return;
        }else {

            if (prefManager.islogged()){
                mTextView.setText("Guest");
          }
            else {
                if (mFirebaseUser == null) {
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                    return;
                }
                else{
//                    mReference.child("users").
                    mTextView.setText(mFirebaseUser.getDisplayName());
                    mTextView.setText(mFirebaseUser.getEmail());
                    if (mFirebaseUser.getPhotoUrl() != null)
                        Picasso.with(this).load(mFirebaseUser.getPhotoUrl()).into(mImageView);
                }

            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
