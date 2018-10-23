package com.iprovider.dan.iprovider;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ToolFragment extends Fragment {
    Button btn_print_bills, btn_print_bookkeeping;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool, container, false);

        btn_print_bills = (Button) view.findViewById(R.id.btn_print_bills);
        btn_print_bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPrintBillActivity = new Intent (getActivity(), PrintBillActivity.class);
                startActivity(newPrintBillActivity);
            }
        });

        btn_print_bills = (Button) view.findViewById(R.id.btn_print_bookkeeping);
        btn_print_bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPrintBookkeepingActivity = new Intent (getActivity(), PrintBookkeepingActivity.class);
                startActivity(newPrintBookkeepingActivity);
            }
        });



        return view;
    }
}
