package com.sande.supermarketdb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sande.supermarketdb.Activities.MainActivity;
import com.sande.supermarketdb.Adapter.New_BillAdapter;
import com.sande.supermarketdb.CallBack;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.BillsDB;
import com.sande.supermarketdb.DatabaseClasses.CustomerDB;
import com.sande.supermarketdb.Pojo.New_bill_item;
import com.sande.supermarketdb.R;
import com.sande.supermarketdb.Utils.ProjectCons;
import com.sande.supermarketdb.Utils.UtilsClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class New_Bill extends Fragment {


    private int billId;
    private Context mContext;
    private int eid=0;
    private double tot_prof;
    private String LoginDetails;
    private ArrayList<CustomerDB> customers;
    private ArrayList<String> cnames = new ArrayList<>();
    //private ArrayList<New_bill_item> nbis=new ArrayList<>();
    private TextView billAmount;
    private double billTot;
    private New_BillAdapter mAdapter;
    private Spinner cSpanner;
    private int cusPosi;
    private int modeOfPay;
    private String[] modeOfPa;
    private Database db;

    public New_Bill() {
        // Required empty public constructor
    }

    public void gotItem(New_bill_item nbi) {
        billTot += nbi.this_price;
        tot_prof += nbi.prof_this;
        String showBill = "Bill Amount:   " + billTot;
        billAmount.setText(showBill);
        //nbis.add(nbi);
        mAdapter.add_item(nbi);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        db = new Database(mContext);
        billId = db.getLatestBillId() + 1;
        if (MainActivity.empNmId == null) {
            LoginDetails = UtilsClass.getIsLoggedInBy(getContext());
        } else {
            LoginDetails = MainActivity.empNmId;
        }
        if(!LoginDetails.equals("Nobody")) {
            eid = Integer.parseInt(LoginDetails.replaceAll("[^0-9]", ""));
        }
        customers = db.getAllCustomers();
        for (CustomerDB x : customers) {
            cnames.add(x.Cname);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_new__bill, container, false);
        TextView empTv = (TextView) mView.findViewById(R.id.empId_tv_fnb);
        TextView billIdTv = (TextView) mView.findViewById(R.id.billId_tv_fnb);
        billIdTv.setText(String.valueOf(billId));
        empTv.setText(String.valueOf(eid));
        cSpanner = (Spinner) mView.findViewById(R.id.customer_spinner_fnb);
        Spinner modopSpanner = (Spinner) mView.findViewById(R.id.modeop_spinner_fnb);
        ArrayAdapter<String> cAdapter=new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, cnames);
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cSpanner.setAdapter(cAdapter);
        cSpanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cusPosi = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        modeOfPa=getResources().getStringArray(R.array.modeOp);
        ArrayAdapter<String> modeAdapter=new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, modeOfPa);
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modopSpanner.setAdapter(modeAdapter);
        modopSpanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modeOfPay = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        billAmount = (TextView) mView.findViewById(R.id.billTotal_tv_fnb);
        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv_fnb);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new New_BillAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.new_bill_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_new_bill) {
            long time = System.currentTimeMillis();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date mDate=new Date(time);
            String ti=sdf.format(mDate);
            BillsDB new_bill=new BillsDB(billId,eid,customers.get(cusPosi).CID,billTot,tot_prof,ti,modeOfPa[modeOfPay]);
            ArrayList<New_bill_item> bill_items=mAdapter.get_items();
            db.insertIntoBills(new_bill);
            db.updateStock(bill_items);
            db.insertIntoTranscats(billId,bill_items);
            ((CallBack)mContext).resetFrag();
            ProjectCons.mProd=new ArrayList<>();
        }
        return true;
    }
}
