package com.sande.supermarketdb.DatabaseClasses;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class StockDB {
    public int ProductId;
    public String Product_Name;
    public String Product_Size;
    public int Quantity;
    public double Sale_Price;
    public double Cost_Price;
    public String Category;
    public int Supplied_by;

    public StockDB(){};

    public StockDB(int productId, String product_Name, String product_Size, int quantity, double sale_Price, double cost_Price, String category, int supplied_by) {
        ProductId = productId;
        Product_Name = product_Name;
        Product_Size = product_Size;
        Quantity = quantity;
        Sale_Price = sale_Price;
        Cost_Price = cost_Price;
        Category = category;
        Supplied_by = supplied_by;
    }
}
