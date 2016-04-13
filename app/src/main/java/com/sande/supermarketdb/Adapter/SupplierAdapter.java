package com.sande.supermarketdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sande.supermarketdb.DatabaseClasses.SupplierDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {
    private final Context mContext;
    private final ArrayList<SupplierDB> items;
    LayoutInflater mLayoutInflater;

    public SupplierAdapter(Context context, ArrayList<SupplierDB> items) {
        mContext=context;
        this.items=items;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public SupplierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.supplier_item,parent,false);
        return new SupplierViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(SupplierViewHolder holder, int position) {
        holder.SID.setText(String.valueOf(items.get(position).getSID()));
        holder.Sname.setText(items.get(position).getSname());
        holder.Scontact.setText(String.valueOf(items.get(position).getScontact()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SupplierViewHolder extends RecyclerView.ViewHolder{
        TextView SID;
        TextView Sname;
        TextView Scontact;
        public SupplierViewHolder(View itemView) {
            super(itemView);
            SID=(TextView)itemView.findViewById(R.id.supp_sid_tv);
            Sname=(TextView)itemView.findViewById(R.id.supp_sname_tv);
            Scontact=(TextView)itemView.findViewById(R.id.supp_sconta_tv);
        }
    }
}
