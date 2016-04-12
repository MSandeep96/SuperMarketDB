package com.sande.supermarketdb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.R;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder>{
    private final Context mContext;
    private final ArrayList<StockDB> list;
    private LayoutInflater mLayoutInflater;

    public StockAdapter(Context context, ArrayList<StockDB> list) {
        mContext=context;
        this.list=list;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=mLayoutInflater.inflate(R.layout.stock_item,parent,false);
        return new StockViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.ProductId.setText(String.valueOf(list.get(position).ProductId));
        holder.Product_Name.setText(list.get(position).Product_Name);
        holder.Product_Size.setText(list.get(position).Product_Size);
        holder.Quantity.setText(String.valueOf(list.get(position).Quantity));
        holder.Sale_Price.setText(String.valueOf(list.get(position).Sale_Price));
        holder.Cost_Price.setText(String.valueOf(list.get(position).Cost_Price));
        holder.Category.setText(list.get(position).Category);
        holder.Supplied_by.setText(String.valueOf(list.get(position).Supplied_by));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder{
        TextView ProductId;
        TextView Product_Name;
        TextView Product_Size;
        TextView Quantity;
        TextView Sale_Price;
        TextView Cost_Price;
        TextView Category;
        TextView Supplied_by;
        public StockViewHolder(View itemView) {
            super(itemView);
            ProductId=(TextView)itemView.findViewById(R.id.stock_prodid_tv);
            Product_Name=(TextView)itemView.findViewById(R.id.stock_prodna_tv);
            Product_Size=(TextView)itemView.findViewById(R.id.stock_prosiz_tv);
            Quantity=(TextView)itemView.findViewById(R.id.stock_quan_tv);
            Sale_Price=(TextView)itemView.findViewById(R.id.stock_sale_tv);
            Cost_Price=(TextView)itemView.findViewById(R.id.stock_cost_tv);
            Category=(TextView)itemView.findViewById(R.id.stock_cate_tv);
            Supplied_by=(TextView)itemView.findViewById(R.id.stock_suppl_tv);

        }
    }
}
