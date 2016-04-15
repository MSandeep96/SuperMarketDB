package com.sande.supermarketdb.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sande.supermarketdb.Fragments.Manager.EmployeeMan;
import com.sande.supermarketdb.Fragments.Manager.QueryMan;
import com.sande.supermarketdb.Fragments.Manager.StockMan;
import com.sande.supermarketdb.Fragments.Manager.SupplierMan;
import com.sande.supermarketdb.ManagerCallBack;
import com.sande.supermarketdb.R;

public class ManagerActivity extends AppCompatActivity implements ManagerCallBack{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onStockPressed(View view) {
        changeFrag(new StockMan());
    }

    public void onEmployeePressed(View view) {
        changeFrag(new EmployeeMan());
    }

    public void onSupplierPressed(View view) {
        changeFrag(new SupplierMan());
    }

    public void onQueryPressed(View view) {
        changeFrag(new QueryMan());
    }

    public void changeFrag(Fragment mFrag){
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_contentManager,mFrag,"visible_manager").commit();
    }

    @Override
    public void resetFrag(Fragment fragment) {

    }
}
