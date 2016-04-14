package com.sande.supermarketdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sande.supermarketdb.DatabaseClasses.TransactDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 14-Apr-16.
 */
public class TransactsAdapter extends RecyclerView.Adapter<TransactsAdapter.TransactsObj> {

    private final LayoutInflater mLayoutInflater;
    private final ArrayList<TransactDB> items;

    public TransactsAdapter(Context context, ArrayList<TransactDB> objs) {
        mLayoutInflater= LayoutInflater.from(context);
        items=objs;
    }

    @Override
    public TransactsObj onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.transact_item,parent,false);
        return new TransactsObj(mView);
    }

    @Override
    public void onBindViewHolder(TransactsObj holder, int position) {
        holder.pid.setText(items.get(position).getPID());
        holder.pquan.setText(items.get(position).getPquan());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class TransactsObj extends RecyclerView.ViewHolder{
        TextView pid;
        TextView pquan;
        public TransactsObj(View itemView) {
            super(itemView);
            pid=(TextView)itemView.findViewById(R.id.pid_transItem);
            pquan=(TextView)itemView.findViewById(R.id.pquan_transItem);
        }
    }
}
