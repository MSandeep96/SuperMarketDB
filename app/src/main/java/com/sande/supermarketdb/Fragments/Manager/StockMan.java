package com.sande.supermarketdb.Fragments.Manager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StockMan extends Fragment {


    private Context mContext;

    public StockMan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext=getContext();
        View mView=inflater.inflate(R.layout.fragment_stock_man,container,false);
        final EditText prodId=(EditText)mView.findViewById(R.id.prodid_fsm);
        final EditText prodName=(EditText)mView.findViewById(R.id.prodname_fsm);
        final EditText quan=(EditText)mView.findViewById(R.id.prodquan_fsm);
        final EditText sale_price=(EditText)mView.findViewById(R.id.sale_price_fsm);
        final EditText cost_price=(EditText)mView.findViewById(R.id.cost_price_fsm);
        final EditText categ=(EditText)mView.findViewById(R.id.category_fsm);
        final EditText supp_by=(EditText)mView.findViewById(R.id.supplied_by_fsm);
        Button sumbit=(Button)mView.findViewById(R.id.submit_btn_fsm);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodid=Integer.parseInt(prodId.getText().toString());
                String prodnam=prodName.getText().toString();
                int qan=Integer.parseInt(quan.getText().toString());
                double sale_pe=Double.parseDouble(sale_price.getText().toString());
                double cost_pe=Double.parseDouble(cost_price.getText().toString());
                String cate=categ.getText().toString();
                int supp=Integer.parseInt(supp_by.getText().toString());
                StockDB mStockItem=new StockDB(prodid,prodnam,qan,sale_pe,cost_pe,cate,supp);
                Database mDB=new Database(mContext);
                try {
                    mDB.insertIntoStock(mStockItem);
                }catch (Exception e){
                    Toast.makeText(mContext,"Invalid entries",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mView;
    }

}
