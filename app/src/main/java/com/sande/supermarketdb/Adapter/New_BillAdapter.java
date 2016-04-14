package com.sande.supermarketdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sande.supermarketdb.Pojo.New_bill_item;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class New_BillAdapter extends RecyclerView.Adapter<New_BillAdapter.New_billHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    ArrayList<New_bill_item> items=new ArrayList<>();

    public New_BillAdapter(Context context){
        mContext=context;
        mLayoutInflater= LayoutInflater.from(context);
    }

    public void add_item(New_bill_item nbi){
        items.add(nbi);
        notifyItemInserted(items.size());
    }

    public ArrayList<New_bill_item> get_items(){
        return items;
    }

    @Override
    public New_billHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.new_bill_tem,parent,false);
        return new New_billHolder(mView);
    }

    @Override
    public void onBindViewHolder(New_billHolder holder, int position) {
        holder.mPID.setText(items.get(position).getProd_id());
        holder.mPname.setText(items.get(position).getProd_name());
        holder.mQuan.setText(items.get(position).getBought_quantity());
        holder.mTotBill.setText(items.get(position).getThis_price());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class New_billHolder extends RecyclerView.ViewHolder{
        TextView mPID;
        TextView mPname;
        TextView mQuan;
        TextView mTotBill;
        public New_billHolder(View itemView) {
            super(itemView);
            mPID=(TextView)itemView.findViewById(R.id.prodId_tv_nbi);
            mPname=(TextView)itemView.findViewById(R.id.prodName_tv_nbi);
            mQuan=(TextView)itemView.findViewById(R.id.quan_tv_nbi);
            mTotBill=(TextView)itemView.findViewById(R.id.totBill_tv_nbi);
        }
    }
}
