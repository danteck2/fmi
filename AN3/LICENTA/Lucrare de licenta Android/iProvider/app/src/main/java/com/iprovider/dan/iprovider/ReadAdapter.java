package com.iprovider.dan.iprovider;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ReadAdapter extends ArrayAdapter<Read>{

    public ReadAdapter(Context context, int resource, List<Read> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_read, parent, false);
        }

        TextView idMeter = (TextView) convertView.findViewById(R.id.item_id_meter_read);
        TextView firstLastName = (TextView) convertView.findViewById(R.id.item_first_last_name_read);
        TextView uid = (TextView) convertView.findViewById(R.id.item_uid_read);
        TextView date = (TextView) convertView.findViewById(R.id.item_date_read);
        TextView currentRead = (TextView) convertView.findViewById(R.id.item_current_read_read);

        Read read = getItem(position);
        if(read != null){
            idMeter.setText(read.getIdMeter());
            firstLastName.setText(read.getFirstLastName());
            uid.setText(read.getUid());
            date.setText(read.getDate());
            currentRead.setText(read.getCurrentRead());
        }

        return convertView;
    }
}
