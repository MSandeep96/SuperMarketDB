package com.sande.supermarketdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.EmployeeDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{
    Context mContext;
    ArrayList<EmployeeDB> items;
    LayoutInflater mLayoutInflater;
    public EmployeeAdapter(Context context, ArrayList<EmployeeDB> items) {
        mContext=context;
        this.items=items;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.employee_item,parent,false);
        return new EmployeeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        holder.eid.setText(String.valueOf(items.get(position).getEID()));
        holder.ename.setText(items.get(position).getEname());
        holder.jobType.setText(items.get(position).getJobType());
        holder.salary.setText(String.valueOf(items.get(position).getSalary()));
        holder.sex.setText(items.get(position).getSex());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView eid;
        TextView ename;
        TextView jobType;
        TextView salary;
        TextView sex;
        public EmployeeViewHolder(View itemView) {
            super(itemView);
            eid=(TextView)itemView.findViewById(R.id.emp_eid_tv);
            ename=(TextView)itemView.findViewById(R.id.emp_ename_tv);
            jobType=(TextView)itemView.findViewById(R.id.emp_jobt_tv);
            salary=(TextView)itemView.findViewById(R.id.emp_sal_tv);
            sex=(TextView)itemView.findViewById(R.id.emp_sex_tv);
        }
    }
}
