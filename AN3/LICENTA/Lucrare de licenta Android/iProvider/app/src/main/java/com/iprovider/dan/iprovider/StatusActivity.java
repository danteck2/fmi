package com.iprovider.dan.iprovider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class StatusActivity extends AppCompatActivity {


    EditText phoneText, addressText, dateText;
    TextView txt_status;
    TextView errorText;
    Button btn_add;
    Spinner spinnerTypeUser;
    String[] spinnerValue = {"Administrator", "Accountant", "Customer"};
    private DatabaseReference mUserDatabaseReference;
    int length_dateText, length_phoneText, length_addressText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setTitle("Additional user information");



        mUserDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        dateText = (EditText) findViewById(R.id.txt_input_date);
        phoneText = (EditText) findViewById(R.id.txt_input_phone);
        addressText = (EditText) findViewById(R.id.txt_input_address);
        txt_status = (TextView) findViewById(R.id.txt_status);
        errorText = (TextView) findViewById(R.id.txt_error_status);
        spinnerTypeUser =(Spinner)findViewById(R.id.spinner_type_user);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(StatusActivity.this, android.R.layout.simple_list_item_1, spinnerValue);
        spinnerTypeUser.setAdapter(adapter);


        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setEnabled(false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            txt_status.setText(new StringBuffer("Status: ").append(String.valueOf(user.isEmailVerified())));
        }

//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn_send.setEnabled(false);
//
////                FirebaseAuth.getInstance().getCurrentUser()
////                        .sendEmailVerification()
////                        .addOnCompleteListener(new OnCompleteListener<Void>() {
////                            @Override
////                            public void onComplete(@NonNull Task<Void> task) {
////                                btn_send.setEnabled(true);
////                                if(task.isSuccessful()) {
////                                    Toast.makeText(Status.this, "Verification email send to: " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
////                                } else
////                                    Toast.makeText(Status.this, "Failed to send verification email", Toast.LENGTH_SHORT ).show();
////
////
////                            }
////                        });
//            }
//        });

        FirebaseAuth.getInstance().getCurrentUser()
        .sendEmailVerification()
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(StatusActivity.this, "Verification email send to: " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(StatusActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT ).show();


            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String uid = "";
                String name = "";
                String email ="";
                String date = dateText.getText().toString();
                String phone = phoneText.getText().toString();
                String address = addressText.getText().toString();
                String typeUser = spinnerTypeUser.getSelectedItem().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                boolean verified = false;
                if (user != null) {
                    verified = user.isEmailVerified();
                    uid = user.getUid();
                    name = user.getDisplayName();
                    email = user.getEmail();
                }
                User curentUserData = new User(uid, name, email, phone, address, date, typeUser, verified);
                mUserDatabaseReference.push().setValue(curentUserData);

                toastMessage( "Aditional data added!");

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //set lengths
        dateText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_dateText = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_addressText < 5){
                    errorText.setText("* Address is incomplete.");
                    btn_add.setEnabled(false);
                }else if(length_dateText  != 10){
                    errorText.setText("* Birth Date is incorect.");
                    btn_add.setEnabled(false);
                }else if(length_phoneText < 7){
                    errorText.setText("* Phone number is too short.");
                    btn_add.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add.setEnabled(true);
                }
            }
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_phoneText = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_addressText < 5){
                    errorText.setText("* Address is incomplete.");
                    btn_add.setEnabled(false);
                }else if(length_dateText  != 10){
                    errorText.setText("* Birth Date is incorect.");
                    btn_add.setEnabled(false);
                }else if(length_phoneText < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add.setEnabled(true);
                }
            }
        });
        addressText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_addressText = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_addressText < 5){
                    errorText.setText("* Address is incomplete.");
                    btn_add.setEnabled(false);
                }else if(length_dateText  != 10){
                    errorText.setText("* Birth Date is incorect.");
                    btn_add.setEnabled(false);
                }else if(length_phoneText < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add.setEnabled(true);
                }
            }
        });

    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
