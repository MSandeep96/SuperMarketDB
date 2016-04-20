package com.sande.supermarketdb.Fragments.Manager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.ManagerCallBack;
import com.sande.supermarketdb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryMan extends Fragment {


    private Context mContext;

    public QueryMan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext=getContext();
        View mView= inflater.inflate(R.layout.fragment_query_man, container, false);
        final EditText mQuery=(EditText)mView.findViewById(R.id.query_et_fqm);
        final Database mDb=new Database(mContext);
        Button mInse=(Button)mView.findViewById(R.id.submit_btn_fqm);

        final TextView mText=(TextView)mView.findViewById(R.id.query_result);
        mInse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuery.getText().length()==0){
                    Toast.makeText(mContext, "Can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        mDb.executeQuery(mQuery.getText().toString());
                        Toast.makeText(mContext, "Successful", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(mContext, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                ((ManagerCallBack)mContext).resetFrag(new QueryMan());
            }
        });
        Button mQuer=(Button)mView.findViewById(R.id.queryButton);
        mQuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuery.getText().length()==0) {
                    Toast.makeText(mContext, "Can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        String result = mDb.getStringResult(mQuery.getText().toString());
                        mText.setText(result);
                    }catch (Exception e){
                        Toast.makeText(mContext,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return mView;
    }

}
