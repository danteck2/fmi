package com.iprovider.dan.iprovider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.MultiDetector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class BillAddActivity extends AppCompatActivity {
    private static final String TAG = "CameraScan";

    private static final int RC_HANDLE_GMS = 9001;
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    final int RequestCameraPermissionID = 1001;

    SurfaceView cameraScan;
    TextView scanResult;
    Button btn_qr_ok, btn_confirm_paid_bill;
    CameraSource cameraSource = null;
    private String stringResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        setTitle("Scan QR code from Bills");

        cameraScan = (SurfaceView) findViewById(R.id.camera_scan_id_bill);
        scanResult = (TextView) findViewById(R.id.txt_scan_result_id_bill);
        btn_qr_ok = (Button) findViewById(R.id.btn_qr_ok_id_bill);
        btn_confirm_paid_bill = (Button) findViewById(R.id.btn_qr_confirm_paid_bill);
        btn_confirm_paid_bill.setEnabled(false);

        int rc = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
        } else {
            requestCameraPermission();
        }

        btn_confirm_paid_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage("Bill paid");


                // set status 1 for current bill

                final Bill[] myBill = new Bill[1];
                final String idMeter;
                Query uidQuery =  FirebaseDatabase.getInstance().getReference("bills").orderByChild("id").equalTo(stringResult);
                uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){

                            myBill[0] = singleSnapshot.getValue(Bill.class);

                            singleSnapshot.getRef().child("status").setValue("1");
                            updateBalance(myBill[0]);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled", databaseError.toException());
                    }
                });


                scanResult.setText("Bill: " + stringResult + " - PAID");
                btn_confirm_paid_bill.setEnabled(false);


            }
        });
        btn_qr_ok.setOnClickListener(new View.OnClickListener() {
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
        Intent goToMainActivity = new Intent(BillAddActivity.this, MainActivity.class);
        goToMainActivity.putExtra("referer","bill");
        startActivity(goToMainActivity);
    }

    private void updateBalance(Bill bill) {
        //update balance
        final Customer[] myCustomer = new Customer[1];
        final Bill billFinal = bill;
        Query uidQuery2 =  FirebaseDatabase.getInstance().getReference("customers").orderByChild("idMeter").equalTo(bill.getIdMeter());
        uidQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                    myCustomer[0] = singleSnapshot.getValue(Customer.class);
                    if(myCustomer[0]!=null){
                        toastMessage("myCustomer name: " + myCustomer[0].getName());
                        toastMessage("get balance int: " + Integer.parseInt(myCustomer[0].getBalance()));
                        toastMessage("Prior/last read: " + Integer.parseInt(billFinal.getPriorRead()));
                        toastMessage("Bill current read: " + Integer.parseInt(billFinal.getCurrentRead()));

                        int balance_int =  Integer.parseInt(myCustomer[0].getBalance());
                        int bill_value = Integer.parseInt(billFinal.getCurrentRead()) - Integer.parseInt(billFinal.getPriorRead());
                        //singleSnapshot.getRef().child("balance").setValue("0");
                        singleSnapshot.getRef().child("balance").setValue(Integer.toString(balance_int + bill_value + Integer.parseInt(billFinal.getDebt())));

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraScan.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d(TAG, "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission granted - initialize the camera source");
            // we have permission, so create the camerasource
            createCameraSource();
            return;
        }

        Log.e(TAG, "Permission not granted: results len = " + grantResults.length +
                " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Multitracker sample")
                .setMessage("No camera permission")
                .setPositiveButton("OK", listener)
                .show();
    }
    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{android.Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }

        final Activity thisActivity = this;
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(thisActivity, permissions,
                        RC_HANDLE_CAMERA_PERM);
            }
        };
    }

    private void createCameraSource() {
        Context context = getApplicationContext();

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.QR_CODE).build();
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if(qrcodes.size() != 0)
                {
                    scanResult.post(new Runnable() {
                        @Override
                        public void run() {
                            //Create vibrate
                            //Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            //vibrator.vibrate(100);
                            scanResult.setText("Bill: " + qrcodes.valueAt(0).displayValue);
                            stringResult = qrcodes.valueAt(0).displayValue;

                            // disable button if bill is paid

                            final Bill[] myBill2 = new Bill[1];
                            Query uidQuery3 =  FirebaseDatabase.getInstance().getReference("bills").orderByChild("id").equalTo(stringResult);
                            uidQuery3.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){

                                        myBill2[0] = singleSnapshot.getValue(Bill.class);
                                    }
                                    if(myBill2[0] != null){
                                        if(myBill2[0].getStatus().equals("0")){
                                            btn_confirm_paid_bill.setEnabled(true);
                                        } else if(myBill2[0].getStatus().equals("1")){
                                            scanResult.setText("ID Bill: " + stringResult + " - Bill was Paid");
                                            btn_confirm_paid_bill.setEnabled(false);
                                        } else {
                                            scanResult.setText("ID Bill: " + stringResult + " - UNPAID (bill is not for pay)");
                                            btn_confirm_paid_bill.setEnabled(false);
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.d(TAG, "onCancelled", databaseError.toException());
                                }
                            });




//                            Intent newConfirmPaidActivity = new Intent (BillAddActivity.this, ConfirmPaidActivity.class);
//                            newConfirmPaidActivity.putExtra("idBill", stringResult);
//                            startActivity(newConfirmPaidActivity);
                        }
                    });
                }
            }
        });



        MultiDetector multiDetector = new MultiDetector.Builder()
                .add(barcodeDetector)
                .build();

        if (!multiDetector.isOperational()) {
            toastMessage("Detector dependencies are not yet available");
        }
        cameraSource = new CameraSource.Builder(getApplicationContext(), multiDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(640, 480)
                .setRequestedFps(2.0f)
                .setAutoFocusEnabled(true)
                .build();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
    }

    private void startCameraSource() {

        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
            dlg.show();
        }
        if (cameraSource != null) {
            cameraScan.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //Request permission
                        ActivityCompat.requestPermissions(BillAddActivity.this,
                                new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
                        return;
                    }
                    try {
                        cameraSource.start(cameraScan.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                }
                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });
        }
    }
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
