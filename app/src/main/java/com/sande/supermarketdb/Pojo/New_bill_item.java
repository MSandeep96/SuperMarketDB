package com.sande.supermarketdb.Pojo;

/**
 * Created by Sandeep on 13-Apr-16.
 */
public class New_bill_item {
    public String getProd_id() {
        return "Product_ID:    "+prod_id;
    }

    public String getBought_quantity() {
        return "Quantity:    "+bought_quantity;
    }

    public String  getThis_price() {
        return "Total:    "+this_price;
    }

    public String getProd_name(){return "Product_Name:    "+prod_name;}

    public int prod_id;
    public int bought_quantity;
    public double this_price;
    public double prof_this;
    public String prod_name;
    public int left_quantity;

    public New_bill_item(int prod_id, int bought_quantity, double this_price, double prof_this, String prod_name,int lq) {
        this.prod_id = prod_id;
        this.bought_quantity = bought_quantity;
        this.this_price = this_price;
        this.prof_this = prof_this;
        this.prod_name = prod_name;
        left_quantity=lq;
    }
}
