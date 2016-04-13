package com.sande.supermarketdb.Pojo;

/**
 * Created by Sandeep on 13-Apr-16.
 */
public class New_bill_item {
    int prod_id;
    int bought_quantity;
    double item_price;
    double prof_this;

    public New_bill_item(int prod_id, int bought_quantity, double item_cross_quan, double prof_this) {
        this.prod_id = prod_id;
        this.bought_quantity = bought_quantity;
        this.item_price = item_cross_quan;
        this.prof_this = prof_this;
    }
}
