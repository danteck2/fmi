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

import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class ReadFragment extends Fragment {

    private static final String TAG = "ReadFragment";
    Button btn_add_read;
    public static final int READ_ADD = 4;

    private ListView mReadListView;
    private ReadAdapter mReadAdapter;
    //Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReadDatabaseReference;
    private ChildEventListener mChildEventListener;
    Spinner spinnerIdMeterRead;
    String[] all = {"All"};
    List<String> spinnerValueList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String[] spinnerValue;

    public ReadFragment() {
        //empty constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        spinnerIdMeterRead =(Spinner) view.findViewById(R.id.spinner_id_meter_read_filter);
        //get idMeters for spinner
        final Read[] myRead = new Read[10];
        Query uidQuery2 =  FirebaseDatabase.getInstance().getReference("reads").orderByChild("idMeter");
        uidQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                    myRead[0] = singleSnapshot.getValue(Read.class);
                    if (myRead[0] != null){
                        spinnerValueList.add(myRead[0].getIdMeter());
                    }else {
                        toastMessage("No reads found!");
                    }
                }
                setSpinnerAdapter();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled", databaseError.toException());
            }
        });

        btn_add_read = (Button) view.findViewById(R.id.btn_add_read);

        btn_add_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivityReadAdd = new Intent (getActivity(), ReadAddActivity.class);
                startActivityForResult(newActivityReadAdd, READ_ADD);
            }
        });

        //Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReadDatabaseReference = mFirebaseDatabase.getReference().child("reads");

        mReadListView = (ListView) view.findViewById(R.id.readListView);
        List<Read> readList = new ArrayList<>();
        mReadAdapter = new ReadAdapter(getActivity(), R.layout.item_read, readList);
        mReadListView.setAdapter(mReadAdapter);

//        mChildEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Read read = dataSnapshot.getValue(Read.class);
//                mReadAdapter.add(read);
//            }
//
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
//            public void onChildRemoved(DataSnapshot dataSnapshot) {}
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
//            public void onCancelled(DatabaseError databaseError) {}
//        };
//        mReadDatabaseReference.addChildEventListener(mChildEventListener);

        spinnerIdMeterRead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mReadAdapter.clear();

                if(i==0){
                    mChildEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Read read = dataSnapshot.getValue(Read.class);
                            mReadAdapter.add(read);
                        }
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                        public void onChildRemoved(DataSnapshot dataSnapshot) {}
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    mReadDatabaseReference.addChildEventListener(mChildEventListener);
                }else{
                    final AdapterView<?> adapterViewFinal = adapterView;
                    final int i_final = i;
                    mChildEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Read read = dataSnapshot.getValue(Read.class);
                            if(read!=null) {
                                if (read.getIdMeter().equals(adapterViewFinal.getItemAtPosition(i_final).toString())) {
                                    mReadAdapter.add(read);
                                }
                            }
                        }
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                        public void onChildRemoved(DataSnapshot dataSnapshot) {}
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                        public void onCancelled(DatabaseError databaseError) {}
                    };
                    mReadDatabaseReference.addChildEventListener(mChildEventListener);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        return view;
    }

    private void setSpinnerAdapter() {

        List<String> spinnerValueListDistinct = new ArrayList<String>(new HashSet<String>(spinnerValueList));
        spinnerValue = joinArrayGeneric(all, spinnerValueListDistinct.toArray(new String[spinnerValueListDistinct.size()]));
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spinnerValue);
        spinnerIdMeterRead.setAdapter(adapter);
        spinnerIdMeterRead.setSelection(adapter.getPosition("All"));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_ADD) {
            if (resultCode == Activity.RESULT_OK) {
                String idMeter = data.getStringExtra("idMeter");
                final String currentRead = data.getStringExtra("currentRead");
                final String priorRead = data.getStringExtra("priorRead");
                final String balance = data.getStringExtra("balance");

                Date currentTime = Calendar.getInstance().getTime();
                final String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);
                //toastMessage("On activity result read_add_-Date: " + date + " CURRENTREAD " + currentRead);
                // set new balance, last read
                final Customer[] myCustomer = new Customer[1];
                Query uidQuery =  FirebaseDatabase.getInstance().getReference("customers").orderByChild("idMeter").equalTo(idMeter);
                uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            myCustomer[0] = singleSnapshot.getValue(Customer.class);

                            int balance_int =  Integer.parseInt(myCustomer[0].getBalance());
                            int bill_value = Integer.parseInt(currentRead) - Integer.parseInt(myCustomer[0].getLastRead());
                            int balance_new = balance_int - bill_value;
                            //toastMessage("old balance: " + Integer.toString(balance_int));
                            //toastMessage("bill_value: " + Integer.toString(bill_value));
                            //toastMessage("new balance: " + Integer.toString(balance_new));

                            singleSnapshot.getRef().child("balance").setValue(Integer.toString(balance_int - bill_value));
                            singleSnapshot.getRef().child("lastReadDate").setValue(date);
                            singleSnapshot.getRef().child("lastRead").setValue(currentRead);


                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        }
    }
    private void toastMessage (String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
    static <T> T[] joinArrayGeneric(T[]... arrays) {
        int length = 0;
        for (T[] array : arrays) {
            length += array.length;
        }
        //T[] result = new T[length];
        final T[] result = (T[]) Array.newInstance(arrays[0].getClass().getComponentType(), length);

        int offset = 0;
        for (T[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
