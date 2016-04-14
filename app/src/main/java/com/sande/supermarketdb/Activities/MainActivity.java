package com.sande.supermarketdb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sande.supermarketdb.CallBack;
import com.sande.supermarketdb.DialogFrag.AddCustomerItem;
import com.sande.supermarketdb.DialogFrag.AddStockItem;
import com.sande.supermarketdb.Fragments.*;
import com.sande.supermarketdb.Pojo.New_bill_item;
import com.sande.supermarketdb.R;
import com.sande.supermarketdb.Utils.ProjectCons;
import com.sande.supermarketdb.Utils.UtilsClass;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,CallBack{

    private static final String SAVE_FRAG = "save_frag";
    private Fragment mFrag;
    private int OnFrag;
    public static String empNmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frag=getSupportFragmentManager().findFragmentByTag("visible_frag");
                if(frag instanceof New_Bill)
                {
                    AddStockItem addStIt = new AddStockItem();
                    addStIt.show(getSupportFragmentManager(), "Add_Item");
                }else if(frag instanceof Customer){
                    AddCustomerItem addCuIt=new AddCustomerItem();
                    addCuIt.show(getSupportFragmentManager(),"Add_Customer");
                }else{
                    Snackbar.make(view,"Call manager to add one",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        if(!UtilsClass.getIsLoggedIn(getApplicationContext())){
            Intent mInt=new Intent(this,LoginActivity.class);
            startActivity(mInt);
            finish();
            return;
        }
        if(UtilsClass.getIsLoggedInBy(this).equals("Nobody")){

        }else{
            changeFrag(new New_Bill());
        }
        Intent inte=getIntent();
        if(inte!=null){
            empNmId=inte.getStringExtra("extra_data");
            changeFrag(new New_Bill());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id==R.id.action_logout){
            UtilsClass.setIsLoggedIn(this,false);
            Intent inte=new Intent(this,LoginActivity.class);
            startActivity(inte);
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_bill) {
            mFrag=new New_Bill();
        } else if (id == R.id.nav_stock) {
            mFrag=new Stock();
        } else if (id == R.id.nav_employee) {
            mFrag=new Employee();
        } else if (id == R.id.nav_customer) {
            mFrag=new Customer();
        } else if (id == R.id.nav_supplier) {
            mFrag=new Supplier();
        } else if (id == R.id.nav_bills) {
            mFrag = new Bills();
        }
        changeFrag(mFrag);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFrag(Fragment frag){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_cm,frag,"visible_frag").commit();
    }

    @Override
    public void onAddItem(New_bill_item nbi) {
        mFrag=getSupportFragmentManager().findFragmentByTag("visible_frag");
        if(mFrag instanceof New_Bill){
            ((New_Bill)mFrag).gotItem(nbi);
        }
    }

    @Override
    public void resetFrag() {
        changeFrag(new New_Bill());
    }

    @Override
    public void resetCust() {
        changeFrag(new Customer());
    }

    @Override
    public void callTransactivity(int billid) {
        Intent inte=new Intent(this,TransactsActivity.class);
        inte.putExtra("billid",billid);
        startActivityForResult(inte,124);
    }
}
