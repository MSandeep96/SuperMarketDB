package com.sande.supermarketdb.DialogFrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sande.supermarketdb.CallBack;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.CustomerDB;
import com.sande.supermarketdb.R;

/**
 * Created by Sandeep on 14-Apr-16.
 */
public class AddCustomerItem extends DialogFragment {

    private Context mContext;
    private int latestCid;
    private Database db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        db=new Database(mContext);
        latestCid=db.getLatestCustomerId()+1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.add_customer_dialog,container,false);
        TextView mCustomeId=(TextView)mView.findViewById(R.id.customer_id_acd);
        mCustomeId.setText(String.valueOf(latestCid));
        final EditText mEditT1=(EditText)mView.findViewById(R.id.customerName_et_acd);
        final EditText mEditT2=(EditText)mView.findViewById(R.id.customerNumber_et_acd);
        final EditText mEditT3=(EditText)mView.findViewById(R.id.emailid_et_acd);
        Button mBtn=(Button)mView.findViewById(R.id.add_btn_acd);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditT1.getText().length()==0||mEditT2.getText().length()==0){
                    Toast.makeText(mContext, "Field can't be empty", Toast.LENGTH_SHORT).show();
                }else {
                    CustomerDB cust=new CustomerDB(latestCid,mEditT1.getText().toString(),mEditT2.getText().toString(),mEditT3.getText().toString());
                    ((CallBack)mContext).resetCust();
                    db.insertIntoCustomer(cust);
                    dismiss();
                }
            }
        });
        return mView;
    }
}
