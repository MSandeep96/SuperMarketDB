package com.sande.supermarketdb.Fragments.Manager;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.ManagerCallBack;
import com.sande.supermarketdb.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class StockMan extends Fragment {


    private Context mContext;
    private int prodId;

    public StockMan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database mDB=new Database(mContext);
        prodId=mDB.getLatestProductID()+1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext=getContext();
        View mView=inflater.inflate(R.layout.fragment_stock_man,container,false);
        TextView prodid=(TextView) mView.findViewById(R.id.prodid_fsm);
        String productId="Product ID:    "+prodId;
        prodid.setText(productId);
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
                String prodnam=prodName.getText().toString();
                int qan=Integer.parseInt(quan.getText().toString());
                double sale_pe=Double.parseDouble(sale_price.getText().toString());
                double cost_pe=Double.parseDouble(cost_price.getText().toString());
                String cate=categ.getText().toString();
                int supp=Integer.parseInt(supp_by.getText().toString());
                StockDB mStockItem=new StockDB(prodId,prodnam,qan,sale_pe,cost_pe,cate,supp);
                Database mDB=new Database(mContext);
                try {
                    mDB.insertIntoStock(mStockItem);
                    Toast.makeText(mContext, "Successful", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                ((ManagerCallBack)mContext).resetFrag(new StockMan());
            }
        });
        return mView;
    }

}
