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

    public String getProductId() {
        return "ProductID:\t"+ProductId;
    }

    public String getProduct_Name() {
        return "ProductName:\t"+Product_Name;
    }

    public String getProduct_Size() {
        return "ProductSize:\t"+Product_Size;
    }

    public String getQuantity() {
        return "Quantity:\t"+Quantity;
    }

    public String getSale_Price() {
        return "Sale Price:\t"+Sale_Price;
    }

    public String getCost_Price() {
        return "Cost Price:\t"+Cost_Price;
    }

    public String getCategory() {
        return "Category:\t"+Category;
    }

    public String getSupplied_by() {
        return "Supplied By:\t"+Supplied_by;
    }
}
