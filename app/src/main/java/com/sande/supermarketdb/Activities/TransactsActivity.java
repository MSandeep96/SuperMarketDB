package com.sande.supermarketdb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sande.supermarketdb.Adapter.TransactsAdapter;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.TransactDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

public class TransactsActivity extends AppCompatActivity {
    ArrayList<TransactDB> transacts;
    int bill_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacts);
        Intent inte=getIntent();
        bill_id=inte.getIntExtra("billid",0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Database db=new Database(this);
        transacts=db.getTheseTransacts(bill_id);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_transacts);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TransactsAdapter transAdap=new TransactsAdapter(this,transacts);
        recyclerView.setAdapter(transAdap);
    }

}
