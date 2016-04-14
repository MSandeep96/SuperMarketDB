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
        View mView= inflater.inflate(R.layout.fragment_stock_man, container, false);
        final EditText mQuery=(EditText)mView.findViewById(R.id.query_et_fqm);
        Button mButton=(Button)mView.findViewById(R.id.submit_btn_fqm);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuery.getText().length()==0){
                    Toast.makeText(mContext, "Can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    Database mDb=new Database(mContext);
                    mDb.executeQuery(mQuery.getText().toString(),mContext);
                    mQuery.setText("");
                    Toast.makeText(mContext, "Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mView;
    }

}
