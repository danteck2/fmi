package com.iprovider.dan.iprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BillAdapter extends ArrayAdapter<Bill> {
    private Context context;
    public static final int BILL_BACK = 8;

    public BillAdapter(Context context, int resource, List<Bill> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_bill, parent, false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.item_id_bill);
        TextView idMeter = (TextView) convertView.findViewById(R.id.item_id_meter_bill);
        TextView period = (TextView) convertView.findViewById(R.id.item_period_bill);
        TextView priorRead = (TextView) convertView.findViewById(R.id.item_prior_read_bill);
        TextView currentRead = (TextView) convertView.findViewById(R.id.item_current_read_bill);
        TextView status = (TextView) convertView.findViewById(R.id.item_status_bill);
        TextView debt = (TextView) convertView.findViewById(R.id.item_debt_bill);

        final Bill bill = getItem(position);
        if(bill != null){
            id.setText(bill.getId());
            idMeter.setText(bill.getIdMeter());
            period.setText(bill.getPeriod());
            priorRead.setText(bill.getPriorRead());
            currentRead.setText(bill.getCurrentRead());
            int status_int = Integer.parseInt(bill.getStatus());
            if(status_int == 0){
                status.setText("New");
            }else if(status_int == 1){
                status.setText("Paid");
            }else if(status_int == 2){
                status.setText("Unpaid");
            }
            debt.setText(bill.getDebt());
        }

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(bill != null){

                    Intent newBillItemActivity = new Intent (context, BillItemActivity.class);
                    newBillItemActivity.putExtra("id", bill.getId());
                    newBillItemActivity.putExtra("idMeter", bill.getIdMeter());
                    newBillItemActivity.putExtra("period", bill.getPeriod());
                    newBillItemActivity.putExtra("priorRead", bill.getPriorRead());
                    newBillItemActivity.putExtra("currentRead", bill.getCurrentRead());
                    newBillItemActivity.putExtra("status", bill.getStatus());
                    newBillItemActivity.putExtra("debt", bill.getDebt());

                    ((Activity) context).startActivityForResult(newBillItemActivity, BILL_BACK);
                }
            }
        });

        return convertView;
    }

}
