package com.sande.supermarketdb.DialogFrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sande.supermarketdb.CallBack;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.Pojo.New_bill_item;
import com.sande.supermarketdb.R;
import com.sande.supermarketdb.Utils.ProjectCons;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

/**
 * Created by Sandeep on 13-Apr-16.
 */
public class AddStockItem extends DialogFragment {
    ArrayList<StockDB> items=new ArrayList<>();
    ArrayList<String> itemNames=new ArrayList<>();
    private Context mContext;
    int selected;
    CallBack callBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database mDB=new Database(mContext);
        items=mDB.getAllStock();
        for(StockDB x:items){
            itemNames.add(x.Product_Name);
        }
        callBack=(CallBack)mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.add_stock_item,container,false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        final Spinner mSpinner=(Spinner) mView.findViewById(R.id.spinner_asi);
        final TextView mAval=(TextView)mView.findViewById(R.id.avlquan_tv_asi);
        final EditText mQuan=(EditText)mView.findViewById(R.id.quan_et_asi);
        final Button mBtn=(Button)mView.findViewById(R.id.accept_btn_asi);
        final TextView mPrice=(TextView)mView.findViewById(R.id.dynamic_price_asi);
        ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,itemNames);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAval.setText(items.get(position).getQuantity());
                selected=position;
                mBtn.setClickable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBtn.setClickable(false);
            }
        });
        mQuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==0){
                    mPrice.setText("Price");
                }else {
                    mPrice.setText(String.valueOf(Integer.parseInt(s.toString()) * items.get(selected).Sale_Price));
                }
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuan.getText().length()==0){
                    Toast.makeText(mContext, "Valid Quantity Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                int Quan=Integer.parseInt(mQuan.getText().toString());
                if(Quan>items.get(selected).Quantity){
                    Toast.makeText(mContext,"Can't be greater than available quan!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ProjectCons.mProd.contains(selected)){
                    Toast.makeText(mContext, "Item already added", Toast.LENGTH_SHORT).show();
                }else {
                    int prodId = items.get(selected).ProductId;
                    int leftQuan = items.get(selected).Quantity - Quan;
                    double item_price = items.get(selected).Sale_Price * Quan;
                    double prof_this = items.get(selected).Sale_Price * Quan - items.get(selected).Cost_Price * Quan;
                    String prod_name = items.get(selected).Product_Name;
                    New_bill_item nbi = new New_bill_item(prodId, Quan, item_price, prof_this, prod_name, leftQuan);
                    callBack.onAddItem(nbi);
                    ProjectCons.mProd.add(selected);
                    dismiss();
                }
            }
        });
        return mView;
    }
}
