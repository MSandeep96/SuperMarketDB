package com.sande.supermarketdb.Fragments.Manager;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AndroidException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.EmployeeDB;
import com.sande.supermarketdb.DatabaseClasses.SupplierDB;
import com.sande.supermarketdb.ManagerCallBack;
import com.sande.supermarketdb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierMan extends Fragment {


    private Context mContext;
    private int suppID;
    private Database mDB;

    public SupplierMan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        mDB=new Database(mContext);
        suppID=mDB.getLatestSupplierID()+1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView= inflater.inflate(R.layout.fragment_supplier_man, container, false);
        TextView mText=(TextView)mView.findViewById(R.id.supplier_id_fsuppm);
        String supID="Supplier ID:    "+suppID;
        mText.setText(supID);
        final EditText suppName=(EditText)mView.findViewById(R.id.supplier_name_et_fsuppm);
        final EditText suppContact=(EditText)mView.findViewById(R.id.supplier_contact_et_fsuppm);
        Button mSubButt=(Button)mView.findViewById(R.id.sub_but);
        mSubButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmpNa=suppName.getText().toString();
                long mECont= Long.parseLong(suppContact.getText().toString());
                SupplierDB mSupp=new SupplierDB(suppID,mEmpNa,mECont);
                try {
                    mDB.insertIntoSupplier(mSupp);
                    Toast.makeText(mContext,"Successfully Added",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(mContext, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                ((ManagerCallBack)mContext).resetFrag(new SupplierMan());
            }
        });
        return mView;
    }

}
