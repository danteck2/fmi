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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class CustomerUpdateActivity extends AppCompatActivity {
    private static final String TAG = "CustomerUpdateActivity";
    TextView errorText;
    EditText idMeterText, nameText, accountNumberText, emailText, phoneText, addressText, lastReadDateText, lastReadText, balanceText, nationalIdNumberText;
    int length_idMeter, length_name, length_accountNumber, length_email, length_phone, length_address, length_lastReadDate, length_lastRead, length_balance, length_nationalIdNumber;
    Button btn_update_customer_update, btn_back_update_customer_update;
    String idMeter, name, accountNumber, email, phone, address, lastReadDate, lastRead, balance, nationalIdNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update);


        idMeterText = (EditText) findViewById(R.id.txt_id_meter_customer_edit_update);
        nameText = (EditText) findViewById(R.id.txt_name_customer_edit_update);
        accountNumberText = (EditText) findViewById(R.id.txt_account_number_customer_edit_update);
        emailText = (EditText) findViewById(R.id.txt_email_customer_edit_update);
        phoneText = (EditText) findViewById(R.id.txt_phone_customer_edit_update);
        addressText = (EditText) findViewById(R.id.txt_address_customer_edit_update);
        lastReadDateText = (EditText) findViewById(R.id.txt_last_read_date_customer_edit_update);
        lastReadText = (EditText) findViewById(R.id.txt_last_read_customer_edit_update);
        balanceText = (EditText) findViewById(R.id.txt_balance_customer_edit_update);
        nationalIdNumberText = (EditText) findViewById(R.id.txt_national_id_number_customer_edit_update);
        errorText = (TextView) findViewById(R.id.txt_error_customer_edit_update);
        btn_back_update_customer_update = (Button) findViewById(R.id.btn_back_customer_edit_update);
        btn_update_customer_update = (Button) findViewById(R.id.btn_update_customer_update);
        btn_update_customer_update.setEnabled(false);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            setTitle("Customer: " + bundle.getString("name"));
            idMeterText.setText(bundle.getString("idMeter"));
            nameText.setText(bundle.getString("name"));
            accountNumberText.setText(bundle.getString("accountNumber"));
            emailText.setText(bundle.getString("email"));
            phoneText.setText(bundle.getString("phone"));
            addressText.setText(bundle.getString("address"));
            lastReadDateText.setText(bundle.getString("lastReadDate"));
            lastReadText.setText(bundle.getString("lastRead"));
            balanceText.setText(bundle.getString("balance"));
            nationalIdNumberText.setText(bundle.getString("nationalIdNumber"));

            idMeter = bundle.getString("idMeter");
            name = bundle.getString("name");
            accountNumber = bundle.getString("accountNumber");
            email = bundle.getString("email");
            phone = bundle.getString("phone");
            address = bundle.getString("address");
            lastReadDate = bundle.getString("lastReadDate");
            lastRead = bundle.getString("lastRead");
            balance = bundle.getString("balance");
            nationalIdNumber = bundle.getString("nationalIdNumber");

            length_idMeter = idMeter.length();
            length_name = name.length();
            length_accountNumber = accountNumber.length();
            length_email = email.length();
            length_phone = phone.length();
            length_address = address.length();
            length_lastReadDate = lastReadDate.length();
            length_lastRead = lastRead.length();
            length_balance = balance.length();
            length_nationalIdNumber = nationalIdNumber.length();
        }

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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                    length_email = 0;
                } else {
                    length_email = charSequence.length();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(length_idMeter < 3){
                    errorText.setText("* ID meter is too short.");
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
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
                    btn_update_customer_update.setEnabled(false);
                } else if(length_name < 3){
                    errorText.setText("* Name is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_accountNumber < 3){
                    errorText.setText("* Account number is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_email < 3){
                    errorText.setText("* Email is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_phone < 7){
                    errorText.setText("* Phone number is not valid.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_address < 8){
                    errorText.setText("* Address is incomplete.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastReadDate != 10){
                    errorText.setText("* Last read date is incorect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_lastRead < 1){
                    errorText.setText("* Last read is incorrect.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_balance < 1){
                    errorText.setText("* Balance is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber < 13){
                    errorText.setText("* National ID number is too short.");
                    btn_update_customer_update.setEnabled(false);
                }else if(length_nationalIdNumber > 13){
                    errorText.setText("* National ID number is too long.");
                    btn_update_customer_update.setEnabled(false);
                }else {
                    errorText.setText("");
                    btn_update_customer_update.setEnabled(true);
                }
            }
        });

        btn_update_customer_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                final String idMeterFinal = idMeterText.getText().toString();
                final String nameFinal = nameText.getText().toString();
                final String accountNumberFinal = accountNumberText.getText().toString();
                final String emailFinal = emailText.getText().toString();
                final String phoneFinal = phoneText.getText().toString();
                final String addressFinal = addressText.getText().toString();
                final String lastReadDateFinal = lastReadDateText.getText().toString();
                final String lastReadFinal = lastReadText.getText().toString();
                final String balanceFinal = balanceText.getText().toString();
                final String nationalIdNumberFinal = nationalIdNumberText.getText().toString();

                toastMessage("idMeter: " + idMeterFinal);
                final Customer[] myCustomer = new Customer[1];
                Query uidQuery =  FirebaseDatabase.getInstance().getReference("customers").orderByChild("idMeter").equalTo(idMeter);
                uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            singleSnapshot.getRef().child("idMeter").setValue(idMeterFinal);
                            singleSnapshot.getRef().child("name").setValue(nameFinal);
                            singleSnapshot.getRef().child("accountNumber").setValue(accountNumberFinal);
                            singleSnapshot.getRef().child("email").setValue(emailFinal);
                            singleSnapshot.getRef().child("phone").setValue(phoneFinal);
                            singleSnapshot.getRef().child("address").setValue(addressFinal);
                            singleSnapshot.getRef().child("lastReadDate").setValue(lastReadDateFinal);
                            singleSnapshot.getRef().child("lastRead").setValue(lastReadFinal);
                            singleSnapshot.getRef().child("balance").setValue(balanceFinal);
                            singleSnapshot.getRef().child("nationalIdNumber").setValue(nationalIdNumberFinal);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn_back_update_customer_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
