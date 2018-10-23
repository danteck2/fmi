package com.iprovider.dan.iprovider;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
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

public class ReadAddActivity extends AppCompatActivity {

    private static final String TAG = "ReadAddActivity";
    EditText idMeterText, currentReadText;
    TextView errorText;
    Button btn_add, btn_scan, btn_scan_meter, btn_back_read_add;
    public static final int CAMERA_SCAN = 5;
    public static final int CAMERA_SCAN_METER = 16;
    String lastReadDate;
    String priorRead;
    String balance;

    private DatabaseReference mReadDatabaseReference;
    private DatabaseReference mCustomerDatabaseReference;
    private DatabaseReference mCustomerDatabaseReference2;
    private DatabaseReference mBillDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_add);
        setTitle("Add new read");

        mReadDatabaseReference = FirebaseDatabase.getInstance().getReference().child("reads");
        mCustomerDatabaseReference = FirebaseDatabase.getInstance().getReference().child("customers");
        mCustomerDatabaseReference2 = FirebaseDatabase.getInstance().getReference().child("customers");
        mBillDatabaseReference = FirebaseDatabase.getInstance().getReference().child("bills");

        idMeterText = (EditText) findViewById(R.id.txt_id_meter_add_read_edit);
        currentReadText = (EditText) findViewById(R.id.txt_current_read_add_read_edit);
        btn_add = (Button) findViewById(R.id.btn_add_read_activity);
        btn_scan = (Button) findViewById(R.id.btn_scan_read_activity);
        btn_scan_meter = (Button) findViewById(R.id.btn_scan_meter_add_read_activity);
        btn_back_read_add = (Button) findViewById(R.id.btn_back_read_add_activity);
        errorText = (TextView) findViewById(R.id.txt_error_read_add);

        btn_add.setEnabled(false);

        idMeterText.setEnabled(false);
        idMeterText.setInputType(InputType.TYPE_NULL);
        currentReadText.setEnabled(false);
        //currentReadText.setInputType(InputType.TYPE_NULL);





        btn_scan_meter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCameraScanIdMeterActivity = new Intent (ReadAddActivity.this, CameraScanIdMeterActivity.class);
                startActivityForResult(newCameraScanIdMeterActivity, CAMERA_SCAN_METER);
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCameraScanActivity = new Intent (ReadAddActivity.this, CameraScanActivity.class);
                startActivityForResult(newCameraScanActivity, CAMERA_SCAN);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                String currentRead = currentReadText.getText().toString();
                String idMeter = idMeterText.getText().toString();
                String uid = "";
                String name = "";
                Date currentTime = Calendar.getInstance().getTime();
                String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    uid = user.getUid();
                    name = user.getDisplayName();
                }
                Read curentReadData = new Read(idMeter, name, uid, date, currentRead);
                mReadDatabaseReference.push().setValue(curentReadData);

                toastMessage( "Read data added!");

                Random rn = new Random();
                int i = rn.nextInt(899);
                int rnum =  100 + i;
                String idBill = Integer.toString(rnum) + new SimpleDateFormat("ddMMyyyy").format(currentTime);

                String period = lastReadDate + " - " + new SimpleDateFormat("dd/MM/yyyy").format(currentTime);
                String status = "0";
                String debt = "0";
                int balance_int = 0;
                balance_int = Integer.parseInt(balance);
                if(balance_int >= 0){
                    debt = "0";
                }else{
                    int debt_int =  Integer.parseInt(balance) * (-1);
                    debt = Integer.toString(debt_int);
                }


               // toastMessage("lastReadDate: " + lastReadDate + "priorRead: " + priorRead);
                Bill currentBillData = new Bill(idBill, idMeter, period, priorRead, currentRead, status, debt);
                mBillDatabaseReference.push().setValue(currentBillData);

                //set unpaid for other bills
                final Bill[] myBill = new Bill[1];
                final String id_bill_final = idBill;
                Query uidQuery3 =  FirebaseDatabase.getInstance().getReference("bills").orderByChild("idMeter").equalTo(idMeter);
                uidQuery3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            myBill[0] = singleSnapshot.getValue(Bill.class);
                            String status_b =  myBill[0].getStatus();
                            String bill_id = myBill[0].getId();
                            //toastMessage("Status: " + status_b + " ID bill: " + bill_id);
                            if((status_b.equals("0") == true)  && (bill_id.equals( id_bill_final) == false)) {
                                singleSnapshot.getRef().child("status").setValue("2");
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });


                intent.putExtra("idMeter", idMeter);
                intent.putExtra("currentRead", currentRead);
                intent.putExtra("priorRead", priorRead);
                intent.putExtra("balance", balance);

                setResult(RESULT_OK, intent);
                //sendFragmentSource();
                finish();
            }
        });
        btn_back_read_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
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
        Intent goToMainActivity = new Intent(ReadAddActivity.this, MainActivity.class);
        goToMainActivity.putExtra("referer","read");
        startActivity(goToMainActivity);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_SCAN) {
            if (resultCode == RESULT_OK) {
                String idMeter = data.getStringExtra("idMeter");
                String currentRead = data.getStringExtra("currentRead");
                idMeterText.setText(idMeter);
                currentReadText.setText(currentRead);

                final Customer[] myCustomer2 = new Customer[1];
                Query uidQuery2 = mCustomerDatabaseReference2.orderByChild("idMeter").equalTo(idMeter);
                uidQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            myCustomer2[0] = singleSnapshot.getValue(Customer.class);
                        }
                        if (myCustomer2[0] != null){
                            //toastMessage("!!!!!!!LastReadDate:  "+myCustomer[0].getLastReadDate());
                            setLast(myCustomer2[0].getLastReadDate(), myCustomer2[0].getLastRead(), myCustomer2[0].getBalance());
                        }else {
                            toastMessage("No Customer found with this idMeter");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });
                btn_add.setEnabled(true);
                currentReadText.setEnabled(true);

//                try
//                {
//                    if (Integer.parseInt(priorRead) <= Integer.parseInt(currentRead) ){
//                        btn_add.setEnabled(true);
//                        currentReadText.setEnabled(true);
//                    }else{
//                        toastMessage("Invalid Current Read Value");
//                        currentReadText.setText(currentRead + " - Invalid number");
//                        btn_add.setEnabled(false);
//                    }
//
//                }
//                catch (NumberFormatException nfe)
//                {
//                    btn_add.setEnabled(false);
//                    currentReadText.setText(currentRead + " - Invalid number");
//                    toastMessage("NumberFormatException: " + nfe.getMessage());
//                }

                currentReadText.setEnabled(true);

            }
        } else if (requestCode == CAMERA_SCAN_METER) {
            if (resultCode == RESULT_OK) {
                String idMeter = data.getStringExtra("idMeter");
                idMeterText.setText(idMeter);

                final Customer[] myCustomer = new Customer[1];
                Query uidQuery = mCustomerDatabaseReference.orderByChild("idMeter").equalTo(idMeter);
                uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            myCustomer[0] = singleSnapshot.getValue(Customer.class);
                        }
                        if (myCustomer[0] != null){
                            //toastMessage("!!!!!!!LastReadDate:  "+myCustomer[0].getLastReadDate());
                            setLast(myCustomer[0].getLastReadDate(), myCustomer[0].getLastRead(), myCustomer[0].getBalance());
                        }else {
                            toastMessage("No Customer found with this idMeter");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });

                currentReadText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(charSequence.length()!=0){
                            try
                            {
                                if(Integer.parseInt(priorRead) <= Integer.parseInt(charSequence.toString())){
                                    btn_add.setEnabled(true);
                                }else {
                                    // number ivalid
                                    btn_add.setEnabled(false);
                                }
                            }
                            catch (NumberFormatException nfe)
                            {
                                btn_add.setEnabled(false);
                                //currentReadText.setText(currentRead + " - Invalid number");
                                //toastMessage("NumberFormatException: " + nfe.getMessage());
                            }

                        }else {
                            btn_add.setEnabled(false);
                        }

                    }
                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                currentReadText.setEnabled(true);
            }
        }
    }

    private void setLast(String lrd, String lr, String bal) {
        this.lastReadDate = lrd;
        this.priorRead = lr;
        this.balance = bal;
    }
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
