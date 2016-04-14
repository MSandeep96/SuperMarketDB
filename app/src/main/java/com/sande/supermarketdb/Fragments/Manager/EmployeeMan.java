package com.sande.supermarketdb.Fragments.Manager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sande.supermarketdb.DatabaseClasses.EmployeeDB;
import com.sande.supermarketdb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeMan extends Fragment {


    private Context mContext;

    public EmployeeMan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=getContext();
        View mView= inflater.inflate(R.layout.fragment_employee_man, container, false);
        final EditText empid=(EditText)mView.findViewById(R.id.empId_et_fem);
        final EditText empName=(EditText)mView.findViewById(R.id.empName_et_fem);
        final EditText sex=(EditText)mView.findViewById(R.id.sex_et_fem);
        final EditText salary=(EditText)mView.findViewById(R.id.salary_et_fem);
        final EditText job_type=(EditText)mView.findViewById(R.id.job_type_et_fem);
        Button sumbit=(Button)mView.findViewById(R.id.submit_btn_fsm);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int empId=Integer.getInteger(empid.getText().toString());
                String empname=empName.getText().toString();
                String sx=sex.getText().toString();
                double salry=Double.parseDouble(salary.getText().toString());
                String job_typ=job_type.getText().toString();
                EmployeeDB empitem=new EmployeeDB(empId,empname,)
            }
        });
    }

}
