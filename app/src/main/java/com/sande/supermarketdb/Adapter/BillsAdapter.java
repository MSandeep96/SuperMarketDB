package com.sande.supermarketdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sande.supermarketdb.CallBack;
import com.sande.supermarketdb.DatabaseClasses.BillsDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.BillsViewHolder>{

    Context mContext;
    ArrayList<BillsDB> items;
    LayoutInflater mInflater;

    public BillsAdapter(Context mContext, ArrayList<BillsDB> items){
        this.mContext=mContext;
        this.items=items;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public BillsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mInflater.inflate(R.layout.bills_item,parent,false);
        return new BillsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(BillsViewHolder holder, final int position) {
        holder.BID.setText(String.valueOf(items.get(position).getBID()));
        holder.EID.setText(String.valueOf(items.get(position).getEID()));
        holder.CID.setText(String.valueOf(items.get(position).getCID()));
        holder.total_prof.setText(String.valueOf(items.get(position).getTotal_profit()));
        holder.billAmount.setText(String.valueOf(items.get(position).getBill_amount()));
        holder.btime.setText(String.valueOf(items.get(position).getbTime()));
        holder.modeop.setText(items.get(position).getModeofp());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CallBack)mContext).callTransactivity(items.get(position).BID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class BillsViewHolder extends RecyclerView.ViewHolder{
        TextView BID;
        TextView CID;
        TextView EID;
        TextView billAmount;
        TextView total_prof;
        TextView btime;
        TextView modeop;
        public BillsViewHolder(View itemView) {
            super(itemView);
            BID=(TextView)itemView.findViewById(R.id.bills_bid_tv);
            CID=(TextView)itemView.findViewById(R.id.bills_cid_tv);
            EID=(TextView)itemView.findViewById(R.id.bills_eid_tv);
            billAmount=(TextView)itemView.findViewById(R.id.bills_billam_tv);
            btime=(TextView)itemView.findViewById(R.id.bills_time_tv);
            total_prof=(TextView)itemView.findViewById(R.id.bills_totalpf_tv);
            modeop=(TextView)itemView.findViewById(R.id.bills_modep_tv);
        }
    }
}
