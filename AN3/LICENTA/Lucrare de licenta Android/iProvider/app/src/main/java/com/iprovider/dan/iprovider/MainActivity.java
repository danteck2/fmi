package com.iprovider.dan.iprovider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    public static final String ANONYMOUS = "anonymous";

    public static final int RC_SIGN_IN = 1;
    public static final int ADDITIONAL_INFO = 2;
    public static final int BILL_BACK = 8;
    public static final int CUSTOMER_BACK = 9;
    public static final int READ_BACK = 29; //to do

    List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(), new AuthUI.IdpConfig.GoogleBuilder().build());

    private String mUsername;
    private DrawerLayout drawer;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView emailNavHeader, nameNavHeader;
    View headerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = ANONYMOUS;
        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUserDatabaseReference = mFirebaseDatabase.getReference().child("users");
        //Navigation Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent newActivityCustommerAdd = new Intent (MainActivity.this, CustomerAddActivity.class);
//                startActivityForResult(newActivityCustommerAdd, CUSTOMER_ADD);
//            }
//        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerLayout = navigationView.getHeaderView(0);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_customer);
            setTitle("Customers");
        }

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    onSignedInInitialize(user.getDisplayName(), user.getEmail());

                    setFragmentSource();

                    final User[] myUser = new User[1];
                    Query uidQuery = mUserDatabaseReference.orderByChild("uid").equalTo(user.getUid());
                    uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                                myUser[0] = singleSnapshot.getValue(User.class);
                            }
                            if (myUser[0] != null){
                                //toastMessage("Loghin user uid from database:  "+myUser[0].getUid());
                            }else {
                                runAditional();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d(TAG, "onCancelled", databaseError.toException());
                        }
                    });

                } else {
                    // User is signed out
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .setTheme(R.style.LoginTheme)
                                    .setLogo(R.drawable.logo)
                                    .setTosUrl("http://freespirit.md/terms-of-service.html")
                                    .setPrivacyPolicyUrl("http://freespirit.md/privacy-policy.html")
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();


            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled, network problem", Toast.LENGTH_SHORT).show();
                finish();
            }

        }else if (requestCode == ADDITIONAL_INFO) {
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "Aditional data succesiful updated!", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == BILL_BACK) {
            if (resultCode == RESULT_OK){
                //toastMessage("bill back");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BillFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_bill);
                setTitle("Bills");
            }
        }else if (requestCode == CUSTOMER_BACK) {
            if (resultCode == RESULT_OK){
                //toastMessage("Customer back");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_customer);
                setTitle("Customers");
            }
        }else if (requestCode == READ_BACK) {
            if (resultCode == RESULT_OK){
                //toastMessage("Read back");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReadFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_read);
                setTitle("Reads");
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
        //mCustomerAdapter.clear();
        //detachDatabaseReadListener();
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            case R.id.action_settings:
                Intent newSettingsActivity = new Intent (MainActivity.this, SettingsActivity.class);
                startActivity(newSettingsActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_customer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerFragment()).commit();
                setTitle("Customers");
                break;
            case R.id.nav_bill:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BillFragment()).commit();
                setTitle("Bills");
                break;
            case R.id.nav_read:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReadFragment()).commit();
                setTitle("Reads");
                break;
            case R.id.nav_tool:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToolFragment()).commit();
                setTitle("Tools");
                break;
            case R.id.nav_print:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpFragment()).commit();
                setTitle("Help");
                break;
            case R.id.nav_send:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                setTitle("About");
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void onSignedInInitialize(String username, String email) {
        mUsername = username;
       // attachDatabaseReadListener();
        emailNavHeader = (TextView) headerLayout.findViewById(R.id.txt_email_for_nav_header);
        nameNavHeader = (TextView) headerLayout.findViewById(R.id.txt_name_for_nav_header);
        emailNavHeader.setText(username);
        nameNavHeader.setText(email);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_customer);
        setTitle("Customers");
    }

    private void setFragmentSource(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            final String referer = bundle.getString("referer");

            if(referer!=null ){
                switch (referer){
                    case "bill":
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BillFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_bill);
                        setTitle("Bills");
                        break;
                    case "customer":
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_customer);
                        setTitle("Customers");
                        break;
                    case "read":
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReadFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_read);
                        setTitle("Reads");
                        break;
                    case "tool":
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToolFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_tool);
                        setTitle("Tools");
                        break;
                }
            }
        }
    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
        //mCustomerAdapter.clear();
        //detachDatabaseReadListener();
    }
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void runAditional(){
        toastMessage("runAditional");
        Intent newActivityStatus = new Intent (MainActivity.this, StatusActivity.class);
        startActivityForResult(newActivityStatus, ADDITIONAL_INFO);
    }
}
