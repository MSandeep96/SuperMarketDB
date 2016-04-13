package com.sande.supermarketdb.DialogFrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.Pojo.New_bill_item;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 13-Apr-16.
 */
public class AddStockItem extends DialogFragment {
    ArrayList<StockDB> items=new ArrayList<>();
    ArrayList<String> itemNames=new ArrayList<>();
    private Context mContext;
    int selected;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database mDB=new Database(mContext);
        items=mDB.getAllStock();
        for(StockDB x:items){
            itemNames.add(x.Product_Name);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.add_stock_item,container,false);
        final Spinner mSpinner=(Spinner)mView.findViewById(R.id.spinner_asi);
        final TextView mAval=(TextView)mView.findViewById(R.id.avlquan_tv_asi);
        final EditText mQuan=(EditText)mView.findViewById(R.id.quan_et_asi);
        Button mBtn=(Button)mView.findViewById(R.id.accept_btn_asi);
        ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,itemNames);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAval.setText(items.get(position).getQuantity());
                selected=position;
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Quan=Integer.parseInt(mQuan.getText().toString());
                int prodId=items.get(selected).ProductId;
                double item_price=items.get(selected).Sale_Price*Quan;
                double prof_this=items.get(selected).Sale_Price*Quan-items.get(selected).Cost_Price*Quan;
                New_bill_item nbi=new New_bill_item(prodId,Quan,item_price,prof_this);

            }
        });
        return mView;
    }
}
