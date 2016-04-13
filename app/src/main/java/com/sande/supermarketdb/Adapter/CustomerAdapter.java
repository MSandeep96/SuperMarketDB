package com.sande.supermarketdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sande.supermarketdb.DatabaseClasses.CustomerDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerView>{


    private final Context mContext;
    private final ArrayList<CustomerDB> items;
    private final LayoutInflater mLayoutInflater;

    public CustomerAdapter(Context context, ArrayList<CustomerDB> items) {
        mContext=context;
        this.items=items;
        mLayoutInflater= LayoutInflater.from(mContext);
    }

    @Override

    public CustomerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.customer_item,parent,false);
        return new CustomerView(mView);
    }

    @Override
    public void onBindViewHolder(CustomerView holder, int position) {
        holder.cid.setText(String.valueOf(items.get(position).getCID()));
        holder.cname.setText(items.get(position).getCname());
        holder.contact.setText(String.valueOf(items.get(position).getContact()));
        holder.cemail.setText(items.get(position).getEmail());
        holder.totalSpent.setText(String.valueOf(items.get(position).getTotal_spent()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CustomerView extends RecyclerView.ViewHolder{
        TextView cid;
        TextView cname;
        TextView contact;
        TextView cemail;
        TextView totalSpent;
        public CustomerView(View itemView) {
            super(itemView);
            cid=(TextView)itemView.findViewById(R.id.cust_cid_tv);
            cname=(TextView)itemView.findViewById(R.id.cust_cname_tv);
            contact=(TextView)itemView.findViewById(R.id.cust_contact_tv);
            cemail=(TextView)itemView.findViewById(R.id.cust_email_tv);
            totalSpent=(TextView)itemView.findViewById(R.id.cust_total_tv);
        }
    }
}
