package com.iprovider.dan.iprovider;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BillFragment extends Fragment {

    Button btn_add_bill;

    private ListView mBillListView;
    private BillAdapter mBillAdapter;
    //Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mBillDatabaseReference;
    private ChildEventListener mChildEventListener;
    public static final int BILL_BACK = 8;
    Spinner spinnerStatusBill;
    String[] spinnerValue = {"New", "Paid", "Unpaid"};
    ArrayAdapter<String> adapter;
    String StatusInt = "0";

    public BillFragment() {
        //empty constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spinnerValue);
        spinnerStatusBill =(Spinner) view.findViewById(R.id.spinner_status_bill_filter);
        spinnerStatusBill.setAdapter(adapter);
        spinnerStatusBill.setSelection(adapter.getPosition("New"));


        btn_add_bill = (Button) view.findViewById(R.id.btn_add_bill);

        btn_add_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivityBillAdd = new Intent (getActivity(), BillAddActivity.class);
                startActivityForResult(newActivityBillAdd, BILL_BACK);
            }
        });

        //Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mBillDatabaseReference = mFirebaseDatabase.getReference().child("bills");

        mBillListView = (ListView) view.findViewById(R.id.billListView);
        List<Bill> billList = new ArrayList<>();
        mBillAdapter = new BillAdapter(getActivity(), R.layout.item_bill, billList);
        mBillListView.setAdapter(mBillAdapter);

//        mChildEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Bill bill = dataSnapshot.getValue(Bill.class);
//                if(bill!=null) {
//                    if (bill.getStatus().equals(StatusInt)) {
//                        mBillAdapter.add(bill);
//                    }
//                }
//
//            }
//
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
//            public void onChildRemoved(DataSnapshot dataSnapshot) {}
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
//            public void onCancelled(DatabaseError databaseError) {}
//        };
//        mBillDatabaseReference.addChildEventListener(mChildEventListener);


        spinnerStatusBill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mBillAdapter.clear();
                if(i==0){
                    StatusInt = "0";
                    mChildEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Bill bill = dataSnapshot.getValue(Bill.class);
                            if(bill!=null) {
                                if (bill.getStatus().equals(StatusInt)) {
                                    mBillAdapter.add(bill);
                                }
                            }

                        }

                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                        public void onChildRemoved(DataSnapshot dataSnapshot) {}
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    mBillDatabaseReference.addChildEventListener(mChildEventListener);
                }else if(i==1){
                    StatusInt = "1";
                    mChildEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Bill bill = dataSnapshot.getValue(Bill.class);
                            if(bill!=null) {
                                if (bill.getStatus().equals(StatusInt)) {
                                    mBillAdapter.add(bill);
                                }
                            }

                        }

                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                        public void onChildRemoved(DataSnapshot dataSnapshot) {}
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    mBillDatabaseReference.addChildEventListener(mChildEventListener);
                }else if(i==2){
                    StatusInt = "2";
                    mChildEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Bill bill = dataSnapshot.getValue(Bill.class);
                            if(bill!=null) {
                                if (bill.getStatus().equals(StatusInt)) {
                                    mBillAdapter.add(bill);
                                }
                            }

                        }

                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                        public void onChildRemoved(DataSnapshot dataSnapshot) {}
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    mBillDatabaseReference.addChildEventListener(mChildEventListener);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BILL_BACK) {
            if (resultCode == Activity.RESULT_OK) {
                //toastMessage("On activity result: Bill back");
                mBillAdapter.clear();
                StatusInt = "0";
                mChildEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Bill bill = dataSnapshot.getValue(Bill.class);
                        if(bill!=null) {
                            if (bill.getStatus().equals(StatusInt)) {
                                mBillAdapter.add(bill);
                            }
                        }
                    }
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                    public void onChildRemoved(DataSnapshot dataSnapshot) {}
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                    public void onCancelled(DatabaseError databaseError) {}
                };
                mBillDatabaseReference.addChildEventListener(mChildEventListener);
            }
        }
    }
    private void toastMessage (String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
