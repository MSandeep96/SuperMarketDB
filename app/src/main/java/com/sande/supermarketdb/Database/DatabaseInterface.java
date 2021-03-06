package com.sande.supermarketdb.Database;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public interface DatabaseInterface  {
    int DATABASE_VERSION=1;
    String DATABASE_NAME="SUPERMARKET";
    String TABLE_BILLS="BILLS";
    String TABLE_CUSTOMER="CUSTOMER";
    String TABLE_EMPLOYEE="EMPLOYEE";
    String TABLE_STOCK="STOCK";
    String TABLE_SUPPLIER="SUPPLIER";
    String TABLE_TRANSACTS="TRANSACTS";
    String TABLE_MANAGER="MANAGER";
    String SUPPLIER_SID="SID";
    String SUPPLIER_SNAME="SNAME";
    String SUPPLIER_SCONTACT="SCONTACT";
    String STOCK_PRODUCTID="PRODUCTID";
    String STOCK_PRODUCT_NAME="PRODUCT_NAME";
    String STOCK_PRODUCT_SIZE="PRODUCT_SIZE";
    String STOCK_QUANTITY="QUANTITY";
    String STOCK_SALE_PRICE="SALE_PRICE";
    String STOCK_COST_PRICE="COST_PRICE";
    String STOCK_CATEGORY="CATEGORY";
    String STOCK_SUPPLIED_BY="SUPPLIED_BY";
    String EMPLOYEE_EID="EID";
    String EMPLOYEE_ENAME="ENAME";
    String EMPLOYEE_PASSWORD="PASSWORD";
    String EMPLOYEE_SEX="SEX";
    String EMPLOYEE_SALARY="SALARY";
    String EMPLOYEE_JOB_TYPE="JOB_TYPE";
    String CUSTOMER_CID="CID";
    String CUSTOMER_CNAME="CNAME";
    String CUSTOMER_CONTACT="CONTACT";
    String CUSTOMER_EMAIL="EMAIL";
    String BILL_BID="BID";
    String BILL_EID="EID";
    String BILL_CID="CID";
    String BILL_BILL_AMOUNT="BILL_AMOUNT";
    String BILL_TOTAL_PROFIT="TOTAL_PROFIT";
    String BILL_BTIME="BTIME";
    String BILL_MODEOP="MODEOP";
    String TRANSACTS_BID="BID";
    String TRANSACTS_PID="PID";
    String TRANSACTS_PQUAN="PQUAN";
    String MANAGER_MID="MID";
    String MANAGER_EID="EID";
}
