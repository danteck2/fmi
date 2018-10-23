package com.iprovider.dan.iprovider;

import android.content.Intent;
import android.support.annotation.NonNull;
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

public class BillItemActivity extends AppCompatActivity {
    private static final String TAG = "BillItemActivity";
    TextView idText, idMeterText, periodText, priorReadText, currentReadText, statusText, debtText;
    Button btn_paid_bill, btn_back_bill;
    String id, idMeter, period, priorRead, currentRead, status, debt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_item);

        idText = (TextView) findViewById(R.id.txt_id_bill_item);
        idMeterText = (TextView) findViewById(R.id.txt_id_meter_bill_item);
        periodText = (TextView) findViewById(R.id.txt_period_bill_item);
        priorReadText = (TextView) findViewById(R.id.txt_prior_read_bill_item);
        currentReadText = (TextView) findViewById(R.id.txt_current_read_bill_item);
        statusText = (TextView) findViewById(R.id.txt_status_bill_item);
        debtText = (TextView) findViewById(R.id.txt_debt_bill_item);
        btn_paid_bill = (Button) findViewById(R.id.btn_update_bill);
        btn_back_bill = (Button) findViewById(R.id.btn_back_bill);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            setTitle("Bill: " + bundle.getString("id"));
            idText.setText(bundle.getString("id"));
            idMeterText.setText(bundle.getString("idMeter"));
            periodText.setText(bundle.getString("period"));
            priorReadText.setText(bundle.getString("priorRead"));
            currentReadText.setText(bundle.getString("currentRead"));
            int status_int =  Integer.parseInt(bundle.getString("status"));
            if(status_int == 0) {
                statusText.setText("New");
                btn_paid_bill.setEnabled(true);
            }else if(status_int == 1) {
                statusText.setText("Paid");
                btn_paid_bill.setEnabled(false);
            }else if(status_int == 2) {
                statusText.setText("Unpaid");
                btn_paid_bill.setEnabled(false);
            }

            debtText.setText(bundle.getString("debt"));

            id = bundle.getString("id");
            idMeter = bundle.getString("idMeter");
            period = bundle.getString("period");
            priorRead = bundle.getString("priorRead");
            currentRead = bundle.getString("currentRead");
            status = bundle.getString("status");
            debt = bundle.getString("debt");


        }

        btn_paid_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage("Bill paid");


                // set status 1 for current bill
                final Customer[] myCustomer = new Customer[1];
                Query uidQuery =  FirebaseDatabase.getInstance().getReference("bills").orderByChild("id").equalTo(id);
                uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            singleSnapshot.getRef().child("status").setValue("1");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });
                //update balance
                Query uidQuery2 =  FirebaseDatabase.getInstance().getReference("customers").orderByChild("idMeter").equalTo(idMeter);
                uidQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            myCustomer[0] = singleSnapshot.getValue(Customer.class);
                            int balance_int =  Integer.parseInt(myCustomer[0].getBalance());
                            int bill_value = Integer.parseInt(currentRead) - Integer.parseInt(priorRead);
                            singleSnapshot.getRef().child("balance").setValue(Integer.toString(balance_int + bill_value + Integer.parseInt(debt)));
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });

                statusText.setText("Paid");
                btn_paid_bill.setEnabled(false);

            }
        });

        btn_back_bill.setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
        sendFragmentSource();
    }

    private void sendFragmentSource(){
        Intent goToMainActivity = new Intent(BillItemActivity.this, MainActivity.class);
        goToMainActivity.putExtra("referer","bill");
        startActivity(goToMainActivity);
    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
