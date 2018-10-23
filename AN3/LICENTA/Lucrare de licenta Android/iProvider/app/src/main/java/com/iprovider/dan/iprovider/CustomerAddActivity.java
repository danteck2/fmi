package com.iprovider.dan.iprovider;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class CustomerAddActivity extends AppCompatActivity {
    TextView errorText;
    EditText idText, idMeterText, nameText, accountNumberText, emailText, phoneText, addressText, lastReadDateText, lastReadText, balanceText, nationalIdNumberText;
    int length_idMeter, length_name, length_accountNumber, length_email, length_phone, length_address, length_lastReadDate, length_lastRead, length_balance, length_nationalIdNumber;
    Button btn_add_customer, btn_scan_id_meter_customer, btn_back;
    public static final int CAMERA_SCAN_METER = 15;

    private DatabaseReference mCustomerDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add);
        setTitle("Add new customer");

        mCustomerDatabaseReference = FirebaseDatabase.getInstance().getReference().child("customers");

        idText = (EditText) findViewById(R.id.txt_id_customer_edit);
        idMeterText = (EditText) findViewById(R.id.txt_id_meter_customer_edit);
        nameText = (EditText) findViewById(R.id.txt_name_customer_edit);
        accountNumberText = (EditText) findViewById(R.id.txt_account_number_edit);
        emailText = (EditText) findViewById(R.id.txt_email_customer_edit);
        phoneText = (EditText) findViewById(R.id.txt_phone_customer_edit);
        addressText = (EditText) findViewById(R.id.txt_address_customer_edit);
        lastReadDateText = (EditText) findViewById(R.id.txt_last_read_date_customer_edit);
        lastReadText = (EditText) findViewById(R.id.txt_last_read_customer_edit);
        balanceText = (EditText) findViewById(R.id.txt_balance_customer_edit);
        nationalIdNumberText = (EditText) findViewById(R.id.txt_national_id_number_customer_edit);
        errorText = (TextView) findViewById(R.id.txt_error_customer);
        Date currentTime = Calendar.getInstance().getTime();
        lastReadDateText.setText(new SimpleDateFormat("dd/MM/yyyy").format(currentTime));
        length_lastReadDate = lastReadDateText.getText().toString().length();
        lastReadText.setText("0");
        length_lastRead = 1;
        balanceText.setText("0");
        length_balance = 1;

        Random rn = new Random();
        int i = rn.nextInt(899);
        int rnum =  100 + i;
        Random rn2 = new Random();
        int i2 = rn2.nextInt(899);
        int rnum2 =  132 + i2;
        idText.setText(Integer.toString(rnum) + new SimpleDateFormat("ddMM").format(currentTime) + Integer.toString(rnum2));

        Random rn3 = new Random();
        int i3 = rn3.nextInt(899);
        int rnum3 =  155 + i3;
        accountNumberText.setText(Integer.toString(rnum3) + new SimpleDateFormat("ddMM").format(currentTime));
        length_accountNumber = accountNumberText.getText().toString().length();

        btn_add_customer = (Button) findViewById(R.id.btn_add_customer);
        btn_back = (Button) findViewById(R.id.btn_back_customer);
        btn_add_customer.setEnabled(false);
        btn_scan_id_meter_customer = (Button) findViewById(R.id.btn_scan_id_meter_customer);

        //set lengths
        idMeterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_idMeter = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_name = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        accountNumberText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_accountNumber = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(isValidEmailAddress(charSequence.toString()) == false){
                    errorText.setText("* Email is not valid.");
                    btn_add_customer.setEnabled(false);
                    length_email = 0;
                } else {
                    length_email = charSequence.length();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_phone = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        addressText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_address = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        lastReadDateText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_lastReadDate = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        lastReadText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_lastRead = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        balanceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_balance = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });
        nationalIdNumberText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length_nationalIdNumber = charSequence.length();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_add_customer.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_add_customer.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_add_customer.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_add_customer.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_add_customer.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_add_customer.setEnabled(true);
                }
            }
        });


        btn_scan_id_meter_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCameraScanIdMeterActivity = new Intent (CustomerAddActivity.this, CameraScanIdMeterActivity.class);
                startActivityForResult(newCameraScanIdMeterActivity, CAMERA_SCAN_METER);
            }
        });

        btn_add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                String id = idText.getText().toString();
                String idMeter = idMeterText.getText().toString();
                String name = nameText.getText().toString();
                String accountNumber = accountNumberText.getText().toString();
                String email = emailText.getText().toString();
                String phone = phoneText.getText().toString();
                String address = addressText.getText().toString();
                String uid = "";
                String lastReadDate = lastReadDateText.getText().toString();
                String lastRead = lastReadText.getText().toString();
                String balance = balanceText.getText().toString();
                String nationalIdNumber = nationalIdNumberText.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    uid = user.getUid();
                }
                Customer curentCustomerData = new Customer(id, idMeter, name, accountNumber, email, phone, address, uid, lastReadDate, lastRead, balance, nationalIdNumber);
                mCustomerDatabaseReference.push().setValue(curentCustomerData);

                toastMessage( "Customer data added!");

                setResult(RESULT_OK, intent);

                //sendFragmentSource();

                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                sendFragmentSource();

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendFragmentSource();
    }

    private void sendFragmentSource(){
        Intent goToMainActivity = new Intent(CustomerAddActivity.this, MainActivity.class);
        goToMainActivity.putExtra("referer","customer");
        startActivity(goToMainActivity);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_SCAN_METER) {
            if (resultCode == RESULT_OK) {
                String idMeter = data.getStringExtra("idMeter");
                idMeterText.setText(idMeter);
                length_idMeter = idMeter.length();
            }
        }
    }
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
