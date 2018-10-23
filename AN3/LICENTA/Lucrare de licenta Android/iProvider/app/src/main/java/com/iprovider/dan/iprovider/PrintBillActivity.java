package com.iprovider.dan.iprovider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//QR code generator
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class PrintBillActivity extends AppCompatActivity {
    EditText companyName, address, phone, cif, vat, unitPrice, currency, lastDatePay, iban;
    Button btn_bill_generate_pdf, btn_bill_back_generate_pdf;
    private static final String TAG = "PrintBillActivity";
    ArrayList <Bill> bills = new ArrayList<Bill>();
    ArrayList <Customer> customers = new ArrayList<Customer>();
    public final static int QRcodeWidth = 150 ;
    Bitmap bitmap ;
    private DatabaseReference mCustomerDatabaseReference;

    String customer_address;
    String customer_name;
    String customer_email;
    String customer_phone;
    String customer_account;
    String customer_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_bill);
        setTitle("Print bills tool");
        mCustomerDatabaseReference = FirebaseDatabase.getInstance().getReference().child("customers");

        companyName = (EditText) findViewById(R.id.txt_company_name);
        address = (EditText) findViewById(R.id.txt_company_address);
        phone = (EditText) findViewById(R.id.txt_company_phone);
        cif = (EditText) findViewById(R.id.txt_company_cif);
        vat = (EditText) findViewById(R.id.txt_bill_vat);
        unitPrice = (EditText) findViewById(R.id.txt_bill_unit_price);
        currency = (EditText) findViewById(R.id.txt_bill_currency);
        lastDatePay = (EditText) findViewById(R.id.txt_bill_last_date_pay);
        iban = (EditText) findViewById(R.id.txt_company_iban);
        btn_bill_generate_pdf = (Button) findViewById(R.id.btn_bill_generate_pdf);
        btn_bill_back_generate_pdf = (Button) findViewById(R.id.btn_bill_back_generate_pdf);


        companyName.setText("Water Nova S.A.");
        address.setText("Str. Dr. Staicovici, nr. 75, Forum 230, Et. 2, Sector 5, București");
        phone.setText("+40314004400");
        cif.setText("RO5888716");
        iban.setText("RO51 INGB 0001 0000 0001 8827");
        vat.setText("19");
        unitPrice.setText("2.68");
        currency.setText("RON");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Today date
        c.add(Calendar.DATE, 30); // Adding 30 days
        Date currentTime = c.getTime();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);
        lastDatePay.setText(date);

        final Bill[] myBill = new Bill[100];
        Query uidQuery =  FirebaseDatabase.getInstance().getReference("bills").orderByChild("status").equalTo("0");
        uidQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                    myBill[0] = singleSnapshot.getValue(Bill.class);
                    if (myBill[0] != null){
                        bills.add(myBill[0]);
                        setCustomers(myBill[0].getIdMeter());
                    }else {
                        toastMessage("No bills for pay found!");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled", databaseError.toException());
            }
        });


        btn_bill_generate_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPDF();
                //sendFragmentSource();
                finish();
            }
        });
        btn_bill_back_generate_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        Intent goToMainActivity = new Intent(PrintBillActivity.this, MainActivity.class);
        goToMainActivity.putExtra("referer","tool");
        startActivity(goToMainActivity);
    }
    private void createPDF(){
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(600, 350, 1).create();
        //page 1
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        Date currentTime_1 = Calendar.getInstance().getTime();
        String date_1 = new SimpleDateFormat("dd/MM/yyyy").format(currentTime_1);
        canvas.drawText("Bills generate by " + date_1,150,30, new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.drawText(companyName.getText().toString(),30,75, new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.drawText(address.getText().toString(),30,100, new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.drawText(phone.getText().toString(),30,125, new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.drawText(cif.getText().toString(),30,150, new Paint(Paint.ANTI_ALIAS_FLAG));


        document.finishPage(page);

        int page_counter = 2;



        for(Bill i : bills){

            //page 2
            pageInfo = new PdfDocument.PageInfo.Builder(600, 350, page_counter).create();
            page = document.startPage(pageInfo);

            canvas = page.getCanvas();

            try {
                bitmap = TextToImageEncode(i.getId());
                canvas.drawBitmap(bitmap, 440, 120, new Paint(Paint.ANTI_ALIAS_FLAG));
            } catch (WriterException e) {
                e.printStackTrace();
            }

            canvas.drawText(companyName.getText().toString(),30,30, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("Address: " +address.getText().toString(),30,50, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("IBAN: " +iban.getText().toString(),30,70, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("Support phone: " + phone.getText().toString(),30,90, new Paint(Paint.ANTI_ALIAS_FLAG));

            canvas.drawText("C.I.F.: " +cif.getText().toString(),300,70, new Paint(Paint.ANTI_ALIAS_FLAG));

            canvas.drawText("Id bill: " + i.getId(),30,210, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("Id meter: " + i.getIdMeter(),30,230, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("Period: " + i.getPeriod(),30,250, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("Last day for pay: " + lastDatePay.getText().toString(),30,270, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("Unit price: " + unitPrice.getText().toString(),30,290, new Paint(Paint.ANTI_ALIAS_FLAG));
            canvas.drawText("VAT: " + vat.getText().toString() + "%",30,310, new Paint(Paint.ANTI_ALIAS_FLAG));


            canvas.drawText("Prior read: " + i.getPriorRead() + " m3",250,210, new Paint(Paint.ANTI_ALIAS_FLAG));

            canvas.drawText("Current read: " + i.getCurrentRead() + " m3",250,230, new Paint(Paint.ANTI_ALIAS_FLAG));

            float price_debt = Float.valueOf(unitPrice.getText().toString().trim()).floatValue();
            float debt_float = Integer.parseInt(i.getDebt()) * price_debt;
            canvas.drawText("Debt: " + String.valueOf(debt_float) + " " + currency.getText().toString(),250,250, new Paint(Paint.ANTI_ALIAS_FLAG));


            float value = (Float.valueOf(i.getCurrentRead().trim()).floatValue() - Float.valueOf(i.getPriorRead().trim()).floatValue()) * price_debt;
            canvas.drawText("Value without VAT: " + String.valueOf(value) + " " + currency.getText().toString(),250,270, new Paint(Paint.ANTI_ALIAS_FLAG));

            float value_vat = ((value + Float.valueOf(i.getDebt().trim()).floatValue()) * Float.valueOf(vat.getText().toString().trim()).floatValue()) / 100 ;
            BigDecimal value_vat_big = new BigDecimal(value_vat).setScale(2, BigDecimal.ROUND_HALF_UP);
            canvas.drawText("VAT of Bill: " + String.valueOf( value_vat_big.floatValue()) + " " + currency.getText().toString(),250,290, new Paint(Paint.ANTI_ALIAS_FLAG));

            float value_total_for_pay = value + value_vat + debt_float;
            BigDecimal value_total_for_pay_big = new BigDecimal(value_total_for_pay).setScale(2, BigDecimal.ROUND_HALF_UP);
            canvas.drawText("AMOUNT DUE: " + String.valueOf(value_total_for_pay_big.floatValue()) + " " + currency.getText().toString(),250,310, new Paint(Paint.ANTI_ALIAS_FLAG));



            for(Customer j : customers){

                if(j.getIdMeter().equals(i.getIdMeter())){
                    canvas.drawText("Name: " + j.getName(),30,110, new Paint(Paint.ANTI_ALIAS_FLAG));
                    canvas.drawText("Customer Address: " + j.getAddress(), 30, 130, new Paint(Paint.ANTI_ALIAS_FLAG));
                    canvas.drawText("Email: " + j.getEmail(),30,150, new Paint(Paint.ANTI_ALIAS_FLAG));
                    canvas.drawText("Phone: " + j.getPhone(),30,170, new Paint(Paint.ANTI_ALIAS_FLAG));
                    canvas.drawText("Account: " + j.getAccountNumber(),30,190, new Paint(Paint.ANTI_ALIAS_FLAG));
                    canvas.drawText("Balance: " + j.getBalance(),250,190, new Paint(Paint.ANTI_ALIAS_FLAG));
                }
            }




            document.finishPage(page);
            page_counter++;
        }



        // write the document content
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(currentTime);
        String targetPdf = "storage/emulated/0/Bills/bill_" + date + ".pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();

    }
    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 150, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
    private void setCustomerInfo(String add, String name, String email, String phone, String account, String balance) {
        this.customer_address = add;
        this.customer_name = name;
        this.customer_email = email;
        this.customer_phone = phone;
        this.customer_account = account;
        this.customer_balance = balance;
    }
    private void setCustomers(String idMeter2){


            final Customer[] myCustomer = new Customer[1];
            Query uidQuery2 = mCustomerDatabaseReference.orderByChild("idMeter").equalTo(idMeter2);
            uidQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                        myCustomer[0] = singleSnapshot.getValue(Customer.class);

                    }
                    if (myCustomer[0] != null){
                        customers.add( myCustomer[0]);

                        //setCustomerInfo(myCustomer[0].getAddress(),  myCustomer[0].getName(), myCustomer[0].getEmail(),  myCustomer[0].getPhone(), myCustomer[0].getAccountNumber(),  myCustomer[0].getBalance());

                    }else {
                        toastMessage("No Customer found with this idMeter");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d(TAG, "onCancelled", databaseError.toException());
                }
            });

    }
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
