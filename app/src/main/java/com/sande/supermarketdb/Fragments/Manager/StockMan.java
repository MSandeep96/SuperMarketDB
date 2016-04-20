package com.sande.supermarketdb.Fragments.Manager;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.DatabaseClasses.SupplierDB;
import com.sande.supermarketdb.ManagerCallBack;
import com.sande.supermarketdb.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StockMan extends Fragment {


    private Context mContext;
    private int prodId;
    ArrayList<SupplierDB> mSuppliers;
    private int selectedSupplier;
    private Database mDB;

    public StockMan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        mDB=new Database(mContext);
        prodId=mDB.getLatestProductID()+1;
        mSuppliers=mDB.getAllSupplier();
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
        final Spinner supp_by=(Spinner) mView.findViewById(R.id.supplied_by_fsm);
        ArrayList<String> suppliers=new ArrayList<>();
        for(SupplierDB x:mSuppliers){
            suppliers.add(x.getSname());
        }
        ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,suppliers);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supp_by.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSupplier=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        supp_by.setAdapter(mAdapter);
        Button sumbit=(Button)mView.findViewById(R.id.submit_btn_fsm);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prodnam=prodName.getText().toString();
                int qan=Integer.parseInt(quan.getText().toString());
                double sale_pe=Double.parseDouble(sale_price.getText().toString());
                double cost_pe=Double.parseDouble(cost_price.getText().toString());
                String cate=categ.getText().toString();
                StockDB mStockItem=new StockDB(prodId,prodnam,qan,sale_pe,cost_pe,cate,mSuppliers.get(selectedSupplier).SID);
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
