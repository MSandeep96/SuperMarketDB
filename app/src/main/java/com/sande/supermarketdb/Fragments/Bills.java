package com.sande.supermarketdb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sande.supermarketdb.Adapter.BillsAdapter;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.BillsDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bills extends Fragment {

    ArrayList<BillsDB> items;
    private Context mContext;

    public Bills() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database mDB=new Database(mContext);
        items=mDB.getAllBills();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_bills, container, false);
        RecyclerView mRec=(RecyclerView)mView.findViewById(R.id.rv_fb);
        mRec.setLayoutManager(new LinearLayoutManager(mContext));
        BillsAdapter mAdapter=new BillsAdapter(mContext,items);
        mRec.setAdapter(mAdapter);
        return  mView;
    }

}
