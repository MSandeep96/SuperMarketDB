package com.sande.supermarketdb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.CustomerDB;
import com.sande.supermarketdb.R;
import com.sande.supermarketdb.Utils.UtilsClass;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class New_Bill extends Fragment {


    private int billId;
    private Context mContext;
    private String eid;
    private ArrayList<CustomerDB> customers;
    private ArrayList<String> cnames=new ArrayList<>();

    public New_Bill() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database db=new Database(mContext);
        billId=db.getLatestBillId()+1;
        eid= UtilsClass.getIsLoggedInBy(getContext());
        customers=db.getAllCustomers();
        for(CustomerDB x:customers){
            cnames.add(x.Cname);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_new__bill, container, false);
        TextView empTv=(TextView)mView.findViewById(R.id.empId_tv_fnb);
        TextView billIdTv=(TextView)mView.findViewById(R.id.billId_tv_fnb);
        final AutoCompleteTextView atoCom=(AutoCompleteTextView)mView.findViewById(R.id.actv_fnb);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_expandable_list_item_1,cnames);
        atoCom.setAdapter(adapter);
        atoCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atoCom.showDropDown();
            }
        });
        billIdTv.setText(String.valueOf(billId));
        empTv.setText(String.valueOf(eid));
        return mView;
    }

}
