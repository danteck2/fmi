package com.iprovider.dan.iprovider;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {

    EditText phoneText, addressText, dateText;
    TextView txt_status, txt_type;
    TextView errorText;
    Button btn_resend, btn_update, btn_back;
    Spinner spinnerTypeUser;
    String[] spinnerValue = {"Administrator", "Accountant", "Customer"};
    private static final String TAG = "SettingsActivity";
    String date, phone, address, typeUser;
    ArrayAdapter<String> adapter;
    int length_dateText, length_phoneText, length_addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        adapter = new ArrayAdapter<>(SettingsActivity.this, android.R.layout.simple_list_item_1, spinnerValue);

        dateText = (EditText) findViewById(R.id.txt_input_date_settings);
        phoneText = (EditText) findViewById(R.id.txt_input_phone_settings);
        addressText = (EditText) findViewById(R.id.txt_input_address_settings);
        txt_status = (TextView) findViewById(R.id.txt_status_settings);
        txt_type = (TextView) findViewById(R.id.txt_current_type_user_settings);
        errorText = (TextView) findViewById(R.id.txt_error_settings);
        spinnerTypeUser =(Spinner)findViewById(R.id.spinner_type_user_settings);
        spinnerTypeUser.setAdapter(adapter);

        btn_resend = (Button) findViewById(R.id.btn_resend_email);
        btn_update = (Button) findViewById(R.id.btn_update_settings);
        btn_back = (Button) findViewById(R.id.btn_back_settings);
        btn_update.setEnabled(false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            txt_status.setText(new StringBuffer("Email verified status: ").append(String.valueOf(user.isEmailVerified())));
            if(String.valueOf(user.isEmailVerified()).equals("true")){
                btn_resend.setEnabled(false);
            }else{
                txt_status.setVisibility(View.INVISIBLE);
            }
        }

        final User[] myUser = new User[1];
        final String uid = user.getUid();
        Query uidQuery =  FirebaseDatabase.getInstance().getReference("users").orderByChild("uid").equalTo(user.getUid());
        uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                    myUser[0] = singleSnapshot.getValue(User.class);
                    if (myUser[0] != null){
                        pupulate(myUser[0].getBirthday(), myUser[0].getPhone(), myUser[0].getAddress(), myUser[0].getTypeUser());
                    }else {
                        toastMessage("No user additional information found!");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled", databaseError.toException());
            }
        });

        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(SettingsActivity.this, "Verification email send to: " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(SettingsActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT ).show();


                            }
                        });
                finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = dateText.getText().toString();
                phone = phoneText.getText().toString();
                address = addressText.getText().toString();
                typeUser = spinnerTypeUser.getSelectedItem().toString();

                final User[] myUser = new User[1];
                Query uidQuery2 =  FirebaseDatabase.getInstance().getReference("users").orderByChild("uid").equalTo(uid);
                uidQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            singleSnapshot.getRef().child("birthday").setValue(date);
                            singleSnapshot.getRef().child("phone").setValue(phone);
                            singleSnapshot.getRef().child("address").setValue(address);
                            singleSnapshot.getRef().child("typeUser").setValue(typeUser);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });

                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    btn_update.setEnabled(false);
                }else if(length_dateText  != 10){
                    errorText.setText("* Birth Date is incorect.");
                    btn_update.setEnabled(false);
                }else if(length_phoneText < 7){
                    errorText.setText("* Phone number is too short.");
                    btn_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update.setEnabled(true);
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
                    btn_update.setEnabled(false);
                }else if(length_dateText  != 10){
                    errorText.setText("* Birth Date is incorect.");
                    btn_update.setEnabled(false);
                }else if(length_phoneText < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update.setEnabled(true);
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
                    btn_update.setEnabled(false);
                }else if(length_dateText  != 10){
                    errorText.setText("* Birth Date is incorect.");
                    btn_update.setEnabled(false);
                }else if(length_phoneText < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update.setEnabled(true);
                }
            }
        });


    }

    private void pupulate(String bd, String p, String a, String t) {

        dateText.setText(bd);
        phoneText.setText(p);
        addressText.setText(a);
        txt_type.setText(t);
        spinnerTypeUser.setSelection(adapter.getPosition(t));
        length_dateText = bd.length();
        length_phoneText = p.length();
        length_addressText = a.length();
    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
