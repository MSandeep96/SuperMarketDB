package com.sande.supermarketdb.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sande.supermarketdb.DatabaseClasses.BillsDB;
import com.sande.supermarketdb.DatabaseClasses.CustomerDB;
import com.sande.supermarketdb.DatabaseClasses.EmployeeDB;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.DatabaseClasses.SupplierDB;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class Database extends SQLiteOpenHelper implements DatabaseInterface{

    private static final String CREATE_SUPPLIER="create table "+TABLE_SUPPLIER+"(" +
            "SID integer primary key," +
            "Sname text not null," +
            "Scontact number," +
            "check (Scontact>7000000000)" +
            ")";

    private static final String CREATE_STOCK="create table stock(" +
            "ProductId integer," +
            "Product_Name text not null," +
            "Product_Size text, " +
            "Quantity integer," +
            "Sale_Price REAL not null," +
            "Cost_Price real not null," +
            "Category text not null," +
            "Supplied_by integer not null," +
            "primary key (ProductId)," +
            "foreign key (Supplied_by) references supplier(sid) on delete cascade on update cascade,"+
            "unique (Product_Name,Product_Size)," +
            "check (Product_Size in ('Small','Medium','Large','XLarge'))," +
            "check (Category in ('Household','Edible','Electronics'))" +
            ")";

    private static final String CREATE_EMPLOYEE="create table employee(" +
            "EID integer primary key," +
            "Ename text not null," +
            "Password text not null," +
            "Sex text not null," +
            "Salary REAL not null," +
            "JobType text not null" +
            ")";

    private static final String  CREATE_CUSTOMER="create table customer(" +
            "CID integer primary key," +
            "Cname text not null," +
            "Contact number," +
            "Email text," +
            "total_spent REAL," +
            "check (total_spent>0)," +
            "check (contact>7000000000)" +
            ")";

    private static final String CREATE_BILL="create table bills(" +
            "BID integer primary key," +
            "CID integer not null," +
            "EID integer not null," +
            "billAmount real not null," +
            "total_Profit real not null," +
            "btime text not null," +
            "modeop text not null," +
            "foreign key (CID) references customer(CID) on delete cascade on update cascade," +
            "foreign key (EID) references employee(EID) on delete cascade on update cascade," +
            "check (billAmount>0)," +
            "check (total_Profit>0)," +
            "check (modeop in ('Cash','Card'))" +
            ")";

    private static final String CREATE_TRANSACTS ="create table transacts(" +
            "BID integer not null," +
            "PID integer not null," +
            "Pquan integer not null," +
            "foreign key (BID) references bill(BID) on delete cascade on update cascade," +
            "foreign key (PID) references stock(ProductId) on delete cascade on update cascade," +
            "primary key(BID,PID)" +
            ")";

    private static final String CREATE_MANAGER="create table manager(" +
            "MID integer primary key," +
            "EID integer," +
            "foreign key (EID) references employee (EID) on delete cascade on update cascade" +
            ")";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SUPPLIER);
        db.execSQL(CREATE_STOCK);
        db.execSQL(CREATE_EMPLOYEE);
        db.execSQL(CREATE_CUSTOMER);
        db.execSQL(CREATE_BILL);
        db.execSQL(CREATE_TRANSACTS);
        db.execSQL(CREATE_MANAGER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_SUPPLIER);
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_BILLS);
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_MANAGER);
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_STOCK);
        db.execSQL("DROP TABLE IF EXIST"+ TABLE_TRANSACTS);
        onCreate(db);
    }

    public ArrayList<BillsDB> getAllBills(){
        ArrayList<BillsDB> allBills=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_BILLS;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                BillsDB bill=new BillsDB();
                bill.BID=Integer.parseInt(cursor.getString(0));
                bill.CID=Integer.parseInt(cursor.getString(1));
                bill.EID=Integer.parseInt(cursor.getString(2));
                bill.bill_amount=Double.parseDouble(cursor.getString(3));
                bill.total_profit=Double.parseDouble(cursor.getString(4));
                bill.bTime=cursor.getString(5);
                bill.modeofp=cursor.getString(6);
                allBills.add(bill);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  allBills;
    }

    public ArrayList<CustomerDB> getAllCustomers(){
        ArrayList<CustomerDB> allCustomers=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_CUSTOMER;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                CustomerDB customerDB=new CustomerDB();
                customerDB.CID=Integer.parseInt(cursor.getString(0));
                customerDB.Cname=cursor.getString(1);
                customerDB.Contact=Long.parseLong(cursor.getString(2));
                customerDB.Email=cursor.getString(3);
                customerDB.total_spent=Double.parseDouble(cursor.getString(4));
                allCustomers.add(customerDB);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return allCustomers;
    }

    public ArrayList<EmployeeDB> getAllEmployee(){
        ArrayList<EmployeeDB> allEmployees=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_EMPLOYEE;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                EmployeeDB employeeDB=new EmployeeDB();
                employeeDB.EID=Integer.parseInt(cursor.getString(0));
                employeeDB.Ename=cursor.getString(1);
                employeeDB.Password=cursor.getString(2);
                employeeDB.Sex=cursor.getString(3);
                employeeDB.Salary=Double.parseDouble(cursor.getString(4));
                employeeDB.JobType=cursor.getString(5);
                allEmployees.add(employeeDB);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return allEmployees;
    }

    public ArrayList<StockDB> getAllStock(){
        ArrayList<StockDB> allStock=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_STOCK;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                StockDB stockDB = new StockDB();
                stockDB.ProductId = Integer.parseInt(cursor.getString(0));
                stockDB.Product_Name = cursor.getString(1);
                stockDB.Product_Size = cursor.getString(2);
                stockDB.Quantity = Integer.parseInt(cursor.getString(3));
                stockDB.Sale_Price = Double.parseDouble(cursor.getString(4));
                stockDB.Cost_Price = Double.parseDouble(cursor.getString(5));
                stockDB.Category = cursor.getString(6);
                stockDB.Supplied_by = Integer.parseInt(cursor.getString(7));
                allStock.add(stockDB);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allStock;
    }

    public ArrayList<SupplierDB> getAllSupplier(){
        ArrayList<SupplierDB> allSuppliers=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_SUPPLIER;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                SupplierDB supplierDB = new SupplierDB();
                supplierDB.SID = Integer.parseInt(cursor.getString(0));
                supplierDB.Sname = cursor.getString(1);
                supplierDB.Scontact = Long.parseLong(cursor.getString(2));
                allSuppliers.add(supplierDB);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return allSuppliers;
    }

    public int getLatestBillId(){
        int billId=0;
        String query="SELECT * FROM "+TABLE_BILLS+" ORDER BY "+BILL_BID+" DESC";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            billId=Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        return billId;
    }

    public ArrayList<String> getAllCustomerIds(){
        ArrayList<String> customerIds=new ArrayList<>();
        String query="SELECT "+CUSTOMER_CID+" FROM "+TABLE_CUSTOMER;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                customerIds.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return customerIds;
    }

}