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

import com.sande.supermarketdb.Adapter.StockAdapter;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Stock extends Fragment {


    private Context mContext;
    private ArrayList<StockDB> list;

    public Stock() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database mDB=new Database(mContext);
        list=mDB.getAllStock();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_stock, container, false);
        RecyclerView mRecy=(RecyclerView)mView.findViewById(R.id.rv_fstock);
        mRecy.setLayoutManager(new LinearLayoutManager(mContext));
        StockAdapter mAdapter=new StockAdapter(mContext,list);
        mRecy.setAdapter(mAdapter);
        return mView;
    }

}
