package com.sande.supermarketdb.Fragments.Manager;


import android.content.Context;
import android.database.SQLException;
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
import com.sande.supermarketdb.DatabaseClasses.EmployeeDB;
import com.sande.supermarketdb.ManagerCallBack;
import com.sande.supermarketdb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeMan extends Fragment {


    private Context mContext;
    private int EmpId;
    private Database mDB;

    public EmployeeMan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mDB = new Database(mContext);
        EmpId = mDB.getLatestEmployee() + 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_employee_man, container, false);
        final TextView empid = (TextView) mView.findViewById(R.id.empId_et_fem);
        String employeID="Employee ID:    "+EmpId;
        empid.setText(employeID);
        final EditText empName = (EditText) mView.findViewById(R.id.empName_et_fem);
        final EditText sex = (EditText) mView.findViewById(R.id.sex_et_fem);
        final EditText pass = (EditText) mView.findViewById(R.id.pass_et_fem);
        final EditText salary = (EditText) mView.findViewById(R.id.salary_et_fem);
        final EditText job_type = (EditText) mView.findViewById(R.id.job_type_et_fem);
        Button sumbit = (Button) mView.findViewById(R.id.submit_btn_fem);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emidnew="Employee ID:    "+EmpId;
                empid.setText(EmpId);
                String empname = empName.getText().toString();
                String sx = sex.getText().toString();
                double salry = Double.parseDouble(salary.getText().toString());
                String job_typ = job_type.getText().toString();
                String pas = pass.getText().toString();
                EmployeeDB empitem = new EmployeeDB(EmpId, empname, pas, sx, salry, job_typ);
                try {
                    mDB.insertIntoEmployee(empitem);
                } catch (Exception e) {
                    Toast.makeText(mContext, "Invalid entries", Toast.LENGTH_SHORT).show();
                }
                ((ManagerCallBack)mContext).resetFrag(new EmployeeMan());
            }
        });
        return mView;
    }
}
