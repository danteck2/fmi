package com.iprovider.dan.iprovider;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomerFragment extends Fragment {

    private ListView mCustomerListView;
    private CustomerAdapter mCustomerAdapter;
    //Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCustomerDatabaseReference;
    private ChildEventListener mChildEventListener;

    Button btn_add_customer;
    public static final int CUSTOMER_ADD = 3;

    public CustomerFragment() {
        //empty constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);

        btn_add_customer = (Button) view.findViewById(R.id.btn_add_customer_fragment);
        btn_add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivityCustommerAdd = new Intent (getActivity(), CustomerAddActivity.class);
                startActivityForResult(newActivityCustommerAdd, CUSTOMER_ADD);
            }
        });
        //Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCustomerDatabaseReference = mFirebaseDatabase.getReference().child("customers");

        mCustomerListView = (ListView) view.findViewById(R.id.customerListView);

        List<Customer> customerList = new ArrayList<>();
        mCustomerAdapter = new CustomerAdapter(getActivity(), R.layout.item_customer, customerList);
        mCustomerListView.setAdapter(mCustomerAdapter);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                mCustomerAdapter.add(customer);
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                mCustomerAdapter.notifyDataSetChanged();
            }
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                mCustomerAdapter.notifyDataSetChanged();
            }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            public void onCancelled(DatabaseError databaseError) {}
        };
        mCustomerDatabaseReference.addChildEventListener(mChildEventListener);



        return view;
    }


    private void toastMessage (String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
