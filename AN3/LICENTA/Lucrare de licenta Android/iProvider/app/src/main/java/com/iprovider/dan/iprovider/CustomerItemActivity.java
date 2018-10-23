package com.iprovider.dan.iprovider;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CustomerItemActivity extends AppCompatActivity {
    private static final String TAG = "CustomerItemActivity";
    TextView idText, idMeterText, nameText, accountNumberText, emailText, phoneText, addressText, lastReadDateText, lastReadText, balanceText, nationalIdNumberText;
    Button btn_update_customer, btn_delete_customer, btn_back_item_customer;
    String id, idMeter, name, accountNumber, email, phone, address, lastReadDate, lastRead, balance, nationalIdNumber;
    public static final int CUSTOMER_UPDATE = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_item);

        idText = (TextView) findViewById(R.id.txt_id_customer_item);
        idMeterText = (TextView) findViewById(R.id.txt_id_meter_customer_item);
        nameText = (TextView) findViewById(R.id.txt_name_customer_item);
        accountNumberText = (TextView) findViewById(R.id.txt_account_number_customer_item);
        emailText = (TextView) findViewById(R.id.txt_email_customer_item);
        phoneText = (TextView) findViewById(R.id.txt_phone_customer_item);
        addressText = (TextView) findViewById(R.id.txt_address_customer_item);
        lastReadDateText = (TextView) findViewById(R.id.txt_last_read_date_customer_item);
        lastReadText = (TextView) findViewById(R.id.txt_last_read_customer_item);
        balanceText = (TextView) findViewById(R.id.txt_balance_customer_item);
        nationalIdNumberText = (TextView) findViewById(R.id.txt_national_id_number_customer_item);
        btn_update_customer = (Button) findViewById(R.id.btn_update_customer);
        btn_delete_customer = (Button) findViewById(R.id.btn_delete_customer);
        btn_back_item_customer = (Button) findViewById(R.id.btn_back_item_customer);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            setTitle("Customer: " + bundle.getString("name"));
            idText.setText(bundle.getString("id"));
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

            id = bundle.getString("id");
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
        }

        btn_update_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCustomerUpdateActivity = new Intent (CustomerItemActivity.this, CustomerUpdateActivity.class);
                newCustomerUpdateActivity.putExtra("id", id);
                newCustomerUpdateActivity.putExtra("idMeter", idMeter);
                newCustomerUpdateActivity.putExtra("name", name);
                newCustomerUpdateActivity.putExtra("accountNumber", accountNumber);
                newCustomerUpdateActivity.putExtra("email", email);
                newCustomerUpdateActivity.putExtra("phone", phone);
                newCustomerUpdateActivity.putExtra("address", address);
                newCustomerUpdateActivity.putExtra("lastReadDate", lastReadDate);
                newCustomerUpdateActivity.putExtra("lastRead", lastRead);
                newCustomerUpdateActivity.putExtra("balance", balance);
                newCustomerUpdateActivity.putExtra("nationalIdNumber", nationalIdNumber);

                startActivityForResult(newCustomerUpdateActivity, CUSTOMER_UPDATE);
            }
        });
        btn_delete_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CustomerItemActivity.this);
                alertDialog.setTitle("Confirm delete customer...");
                alertDialog.setMessage("Are you sure you want to delete?");
                //alertDialog.setIcon(R.drawable.delete);

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        final Customer[] myCustomer = new Customer[1];
                        Query uidQuery =  FirebaseDatabase.getInstance().getReference("customers").orderByChild("idMeter").equalTo(idMeter);
                        uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                                    singleSnapshot.getRef().child("id").removeValue();
                                    singleSnapshot.getRef().child("idMeter").removeValue();
                                    singleSnapshot.getRef().child("name").removeValue();
                                    singleSnapshot.getRef().child("accountNumber").removeValue();
                                    singleSnapshot.getRef().child("email").removeValue();
                                    singleSnapshot.getRef().child("phone").removeValue();
                                    singleSnapshot.getRef().child("address").removeValue();
                                    singleSnapshot.getRef().child("lastReadDate").removeValue();
                                    singleSnapshot.getRef().child("lastRead").removeValue();
                                    singleSnapshot.getRef().child("uidUser").removeValue();
                                    singleSnapshot.getRef().child("balance").removeValue();
                                    singleSnapshot.getRef().child("nationalIdNumber").removeValue();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.d(TAG, "onCancelled", databaseError.toException());
                            }
                        });

                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);

                        //sendFragmentSource();

                        finish();

                        Toast.makeText(getApplicationContext(), "Customer was deleted!", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // показываем Alert
                alertDialog.show();



            }
        });

        btn_back_item_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                sendFragmentSource();

                finish();
            }
        });
    }



    private void sendFragmentSource(){
        Intent goToMainActivity = new Intent(CustomerItemActivity.this, MainActivity.class);
        goToMainActivity.putExtra("referer","customer");
        startActivity(goToMainActivity);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CUSTOMER_UPDATE) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                //sendFragmentSource();
                finish();


            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Update customer canceled, problem", Toast.LENGTH_SHORT).show();
                sendFragmentSource();
                finish();
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
        sendFragmentSource();
    }
}
