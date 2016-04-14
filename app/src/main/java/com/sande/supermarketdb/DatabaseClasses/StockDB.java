package com.sande.supermarketdb.DatabaseClasses;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class StockDB {
    public int ProductId;
    public String Product_Name;
    public int Quantity;
    public double Sale_Price;
    public double Cost_Price;
    public String Category;
    public int Supplied_by;

    public StockDB(){};

    public StockDB(int productId, String product_Name,int quantity, double sale_Price, double cost_Price, String category, int supplied_by) {
        ProductId = productId;
        Product_Name = product_Name;
        Quantity = quantity;
        Sale_Price = sale_Price;
        Cost_Price = cost_Price;
        Category = category;
        Supplied_by = supplied_by;
    }

    public String getProductId() {
        return String.valueOf(ProductId);
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public String getQuantity() {
        return String.valueOf(Quantity);
    }

    public String getSale_Price() {
        return String.valueOf(Sale_Price);
    }

    public String getCost_Price() {
        return String.valueOf(Cost_Price);
    }

    public String getCategory() {
        return Category;
    }

    public String getSupplied_by() {
        return String.valueOf(Supplied_by);
    }
}
