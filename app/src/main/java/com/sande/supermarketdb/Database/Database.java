package com.sande.supermarketdb.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.util.ArrayMap;
import android.widget.Toast;

import com.sande.supermarketdb.DatabaseClasses.BillsDB;
import com.sande.supermarketdb.DatabaseClasses.CustomerDB;
import com.sande.supermarketdb.DatabaseClasses.EmployeeDB;
import com.sande.supermarketdb.DatabaseClasses.StockDB;
import com.sande.supermarketdb.DatabaseClasses.SupplierDB;
import com.sande.supermarketdb.DatabaseClasses.TransactDB;
import com.sande.supermarketdb.Pojo.New_bill_item;

import java.util.ArrayList;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class Database extends SQLiteOpenHelper implements DatabaseInterface {

    private static final String CREATE_SUPPLIER = "create table " + TABLE_SUPPLIER + "(" +
            "SID integer primary key," +
            "Sname text not null," +
            "Scontact number," +
            "check (Scontact>7000000000)" +
            ")";

    private static final String CREATE_STOCK = "create table stock(" +
            "ProductId integer," +
            "Product_Name text not null," +
            "Quantity integer," +
            "Sale_Price REAL not null," +
            "Cost_Price real not null," +
            "Category text not null," +
            "Supplied_by integer not null," +
            "primary key (ProductId)," +
            "foreign key (Supplied_by) references supplier(sid) on delete cascade on update cascade," +
            "check (Category in ('Household','Edible','Electronics'))" +
            ")";

    private static final String CREATE_EMPLOYEE = "create table employee(" +
            "EID integer primary key," +
            "Ename text not null," +
            "Password text not null," +
            "Sex text not null," +
            "Salary REAL not null," +
            "JobType text not null," +
            "check (JobType in ('FullTime','PartTime'))," +
            "check (Sex in ('M','F'))" +
            ")";

    private static final String CREATE_CUSTOMER = "create table customer(" +
            "CID integer primary key," +
            "Cname text not null," +
            "Contact number," +
            "Email text," +
            "check (contact>7000000000)" +
            ")";

    private static final String CREATE_BILL = "create table bills(" +
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
            "check (modeop in ('Cash','Credit','Debit','Voucher'))" +
            ")";

    private static final String CREATE_TRANSACTS = "create table transacts(" +
            "BID integer not null," +
            "PID integer not null," +
            "Pquan integer not null," +
            "foreign key (BID) references bill(BID) on delete cascade on update cascade," +
            "foreign key (PID) references stock(ProductId) on update cascade," +
            "primary key(BID,PID)" +
            ")";

    private static final String CREATE_MANAGER = "create table manager(" +
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
        db.execSQL("DROP TABLE IF EXIST" + TABLE_SUPPLIER);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_BILLS);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_MANAGER);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_STOCK);
        db.execSQL("DROP TABLE IF EXIST" + TABLE_TRANSACTS);
        onCreate(db);
    }

    public ArrayList<BillsDB> getAllBills() {
        ArrayList<BillsDB> allBills = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BILLS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                BillsDB bill = new BillsDB();
                bill.BID = Integer.parseInt(cursor.getString(0));
                bill.CID = Integer.parseInt(cursor.getString(1));
                bill.EID = Integer.parseInt(cursor.getString(2));
                bill.bill_amount = Double.parseDouble(cursor.getString(3));
                bill.total_profit = Double.parseDouble(cursor.getString(4));
                bill.bTime = cursor.getString(5);
                bill.modeofp = cursor.getString(6);
                allBills.add(bill);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allBills;
    }

    public ArrayList<CustomerDB> getAllCustomers() {
        ArrayList<CustomerDB> allCustomers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CUSTOMER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                CustomerDB customerDB = new CustomerDB();
                customerDB.CID = Integer.parseInt(cursor.getString(0));
                customerDB.Cname = cursor.getString(1);
                customerDB.Contact = Long.parseLong(cursor.getString(2));
                customerDB.Email = cursor.getString(3);
                allCustomers.add(customerDB);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allCustomers;
    }

    public ArrayList<EmployeeDB> getAllEmployee() {
        ArrayList<EmployeeDB> allEmployees = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                EmployeeDB employeeDB = new EmployeeDB();
                employeeDB.EID = Integer.parseInt(cursor.getString(0));
                employeeDB.Ename = cursor.getString(1);
                employeeDB.Password = cursor.getString(2);
                employeeDB.Sex = cursor.getString(3);
                employeeDB.Salary = Double.parseDouble(cursor.getString(4));
                employeeDB.JobType = cursor.getString(5);
                allEmployees.add(employeeDB);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allEmployees;
    }

    public ArrayList<StockDB> getAllStock() {
        ArrayList<StockDB> allStock = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STOCK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                StockDB stockDB = new StockDB();
                stockDB.ProductId = Integer.parseInt(cursor.getString(0));
                stockDB.Product_Name = cursor.getString(1);
                stockDB.Quantity = Integer.parseInt(cursor.getString(2));
                stockDB.Sale_Price = Double.parseDouble(cursor.getString(3));
                stockDB.Cost_Price = Double.parseDouble(cursor.getString(4));
                stockDB.Category = cursor.getString(5);
                stockDB.Supplied_by = Integer.parseInt(cursor.getString(6));
                allStock.add(stockDB);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allStock;
    }

    public ArrayList<SupplierDB> getAllSupplier() {
        ArrayList<SupplierDB> allSuppliers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_SUPPLIER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                SupplierDB supplierDB = new SupplierDB();
                supplierDB.SID = Integer.parseInt(cursor.getString(0));
                supplierDB.Sname = cursor.getString(1);
                supplierDB.Scontact = Long.parseLong(cursor.getString(2));
                allSuppliers.add(supplierDB);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allSuppliers;
    }

    public int getLatestBillId() {
        int billId = 0;
        String query = "SELECT * FROM " + TABLE_BILLS + " ORDER BY " + BILL_BID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            billId = Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        return billId;
    }

    public int getLatestCustomerId() {
        int customerId = 0;
        String query = "SELECT * FROM " + TABLE_CUSTOMER + " ORDER BY " + CUSTOMER_CID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            customerId = Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        return customerId;
    }

    public ArrayList<String> getAllCustomerIds() {
        ArrayList<String> customerIds = new ArrayList<>();
        String query = "SELECT " + CUSTOMER_CID + " FROM " + TABLE_CUSTOMER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                customerIds.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return customerIds;
    }

    public ArrayList<TransactDB> getTheseTransacts(int BillId) {
        ArrayList<TransactDB> transacts = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TRANSACTS + " WHERE " + TRANSACTS_BID + " = " + String.valueOf(BillId);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int pid = Integer.parseInt(cursor.getString(1));
                int pquan = Integer.parseInt(cursor.getString(2));
                transacts.add(new TransactDB(BillId, pid, pquan));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return transacts;
    }

    public void insertIntoStock(StockDB stock_item) {
        String sql = "INSERT INTO " + TABLE_STOCK + " VALUES (?,?,?,?,?,?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql, new Object[]{stock_item.ProductId, stock_item.Product_Name, stock_item.Quantity, stock_item.Sale_Price, stock_item.Cost_Price, stock_item.Category, stock_item.Supplied_by});
    }

    public void insertIntoCustomer(CustomerDB customer_item) {
        String sql = "INSERT INTO " + TABLE_CUSTOMER + " VALUES (?,?,?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql, new Object[]{customer_item.CID, customer_item.Cname, customer_item.Contact, customer_item.Email});
    }

    public void insertIntoBills(BillsDB bill_item) {
        String sql = "INSERT INTO " + TABLE_BILLS + " VALUES (?,?,?,?,?,?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql, new Object[]{bill_item.BID, bill_item.CID, bill_item.EID, bill_item.bill_amount, bill_item.total_profit, bill_item.bTime, bill_item.modeofp});
    }

    public void insertIntoTranscats(int billId, ArrayList<New_bill_item> nbis) {
        String sql = "INSERT INTO " + TABLE_TRANSACTS + " VALUES (?,?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        for (New_bill_item x : nbis) {
            db.execSQL(sql, new Object[]{billId, x.prod_id, x.bought_quantity});
        }
    }

    public void updateStock(ArrayList<New_bill_item> nbis) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (New_bill_item x : nbis) {
            String sql = "UPDATE " + TABLE_STOCK + " SET " + STOCK_QUANTITY + " = " + String.valueOf(x.left_quantity) + " WHERE " + STOCK_PRODUCTID +
                    " = " + String.valueOf(x.prod_id);
            db.execSQL(sql);
        }
    }

    public void executeQuery(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public int getLatestEmployee() {
        int employeeID = 0;
        String query = "SELECT * FROM " + TABLE_EMPLOYEE + " ORDER BY " + EMPLOYEE_EID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            employeeID = Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        return employeeID;
    }

    public void insertIntoEmployee(EmployeeDB empitem) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_EMPLOYEE + " VALUES (?,?,?,?,?,?)";
        db.execSQL(sql, new Object[]{empitem.EID, empitem.Ename, empitem.Password, empitem.Sex, empitem.Salary, empitem.JobType});
    }

    public int getLatestSupplierID() {
        int supplierID = 0;
        String query = "SELECT * FROM " + TABLE_SUPPLIER + " ORDER BY " + SUPPLIER_SID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            supplierID = Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        return supplierID;
    }

    public void insertIntoSupplier(SupplierDB mSupp) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_SUPPLIER + " VALUES (?,?,?)";
        db.execSQL(sql, new Object[]{mSupp.SID, mSupp.Sname, mSupp.Scontact});
    }

    public ArrayMap<Integer, String> getManagerAccounts() {
        ArrayMap<Integer, String> maps = new ArrayMap<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_MANAGER;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String pass = cursor.getString(1);
                maps.put(id, pass);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return maps;
    }

    public int getLatestProductID() {
        int supplierID = 0;
        String query = "SELECT * FROM " + TABLE_STOCK + " ORDER BY " + STOCK_PRODUCTID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            supplierID = Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        return supplierID;
    }

    public ArrayList<EmployeeDB> getAllManagers() {
        ArrayList<EmployeeDB> allManagers=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_EMPLOYEE+" NATURAL JOIN "+ TABLE_MANAGER;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                EmployeeDB employeeDB = new EmployeeDB();
                employeeDB.EID = Integer.parseInt(cursor.getString(0));
                employeeDB.Ename = cursor.getString(1);
                employeeDB.Password = cursor.getString(2);
                employeeDB.Sex = cursor.getString(3);
                employeeDB.Salary = Double.parseDouble(cursor.getString(4));
                employeeDB.JobType = cursor.getString(5);
                allManagers.add(employeeDB);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allManagers;
    }

    public String getStringResult(String sql) {
        StringBuilder mStr=new StringBuilder();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            String tableNames="";
            for(int k=0;k<cursor.getColumnCount();k++){
                tableNames+=cursor.getColumnName(k)+" || ";
            }
            tableNames+="\n\n";
            mStr.append(tableNames);
            do {
                String row="**";
                for(int i=0;i<cursor.getColumnCount();i++){
                    row+=cursor.getString(i)+" || ";
                }
                row+="**\n";
                mStr.append(row);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mStr.toString();
    }

    public String getProductName(String pid) {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT "+STOCK_PRODUCT_NAME+" FROM "+TABLE_STOCK+" WHERE "+STOCK_PRODUCTID+" = "+pid;
        String prodName=null;
        Cursor mCursor=db.rawQuery(query,null);
        if(mCursor.moveToFirst()){
            prodName=mCursor.getString(0);
        }
        mCursor.close();
        return prodName;
    }
}
