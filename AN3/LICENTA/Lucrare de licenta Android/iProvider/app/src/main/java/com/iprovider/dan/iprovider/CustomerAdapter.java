package com.iprovider.dan.iprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer>{
    private Context context;
    public static final int CUSTOMER_BACK = 9;

    public CustomerAdapter(Context context, int resource, List<Customer> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_customer, parent, false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.item_id_customer);
        TextView idMeter = (TextView) convertView.findViewById(R.id.item_id_meter_customer);
        TextView name = (TextView) convertView.findViewById(R.id.item_name_customer);
        TextView lastReadDate = (TextView) convertView.findViewById(R.id.item_last_read_date_customer);
        TextView lastRead = (TextView) convertView.findViewById(R.id.item_last_read_customer);
        TextView balance = (TextView) convertView.findViewById(R.id.item_balance_customer);
        final Customer customer = getItem(position);
        if(customer != null){
            id.setText(customer.getId());
            idMeter.setText(customer.getIdMeter());
            name.setText(customer.getName());
            lastReadDate.setText(customer.getLastReadDate());
            lastRead.setText(customer.getLastRead());
            balance.setText(customer.getBalance());
        }

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(customer != null){

                    Intent newCustomerItemActivity = new Intent (context, CustomerItemActivity.class);
                    newCustomerItemActivity.putExtra("id", customer.getId());
                    newCustomerItemActivity.putExtra("idMeter", customer.getIdMeter());
                    newCustomerItemActivity.putExtra("name", customer.getName());
                    newCustomerItemActivity.putExtra("accountNumber", customer.getAccountNumber());
                    newCustomerItemActivity.putExtra("email", customer.getEmail());
                    newCustomerItemActivity.putExtra("phone", customer.getPhone());
                    newCustomerItemActivity.putExtra("address", customer.getAddress());
                    newCustomerItemActivity.putExtra("lastReadDate", customer.getLastReadDate());
                    newCustomerItemActivity.putExtra("lastRead", customer.getLastRead());
                    newCustomerItemActivity.putExtra("balance", customer.getBalance());
                    newCustomerItemActivity.putExtra("nationalIdNumber", customer.getNationalIdNumber());

                    ((Activity) context).startActivityForResult(newCustomerItemActivity, CUSTOMER_BACK);
                }
            }
        });

        return convertView;
    }
    private void toastMessage (String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
