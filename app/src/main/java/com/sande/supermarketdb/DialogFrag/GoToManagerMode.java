package com.sande.supermarketdb.DialogFrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sande.supermarketdb.CallBack;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.R;

/**
 * Created by Sandeep on 15-Apr-16.
 */
public class GoToManagerMode extends DialogFragment {
    ArrayMap<Integer,String> mappings;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        Database db=new Database(mContext);
        mappings=db.getManagerAccounts();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.gotomanagermode_layout,container,false);
        final EditText mID=(EditText)mView.findViewById(R.id.gotoman_manid);
        final EditText mPass=(EditText)mView.findViewById(R.id.gotoman_manPass);
        Button mBtn=(Button)mView.findViewById(R.id.gotoman_submit);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mID.getText().length()==0||mPass.getText().length()==0){
                    Toast.makeText(mContext, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    int id=Integer.parseInt(mID.getText().toString());
                    if(!mappings.containsKey(id)){
                        Toast.makeText(mContext, "Invalid ID", Toast.LENGTH_SHORT).show();
                    }else{
                        if(mappings.get(id).equals(mPass.getText().toString())){
                            ((CallBack)mContext).callManangerMode();
                            dismiss();
                        }else{
                            Toast.makeText(mContext, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        return mView;
    }
}
