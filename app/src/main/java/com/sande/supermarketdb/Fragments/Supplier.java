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

import com.sande.supermarketdb.Adapter.SupplierAdapter;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.SupplierDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Supplier extends Fragment {


    private Context mContext;
    private ArrayList<SupplierDB> items;

    public Supplier() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database mDB=new Database(mContext);
        items=mDB.getAllSupplier();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_supplier, container, false);
        RecyclerView mRecy=(RecyclerView)mView.findViewById(R.id.rv_fsupplier);
        mRecy.setLayoutManager(new LinearLayoutManager(mContext));
        SupplierAdapter mAdapter=new SupplierAdapter(mContext,items);
        mRecy.setAdapter(mAdapter);
        return mView;
    }

}
