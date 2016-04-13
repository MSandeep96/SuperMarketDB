package com.sande.supermarketdb.DatabaseClasses;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class BillsDB {
    public int BID;
    public int CID;
    public int EID;
    public double bill_amount;
    public double total_profit;
    public String bTime;
    public String modeofp;

    public String getBID() {
        return "Bill ID:\t"+BID;
    }

    public String getCID() {
        return "Customer ID:\t"+CID;
    }

    public String getEID() {
        return "Employee ID:\t"+EID;
    }

    public String getBill_amount() {
        return "Bill amount:\t"+bill_amount;
    }

    public String getTotal_profit() {
        return "Total Profit:\t"+total_profit;
    }

    public String getbTime() {
        return "Bill time:\t"+bTime;
    }

    public String getModeofp() {
        return "Mode of Payment:\t"+modeofp;
    }

    public BillsDB(){}

    public BillsDB(int BID, int CID, int EID, int bill_amount, int total_profit, String bTime, String modeofp) {
        this.BID = BID;
        this.CID = CID;
        this.EID = EID;
        this.bill_amount = bill_amount;
        this.total_profit = total_profit;
        this.bTime = bTime;
        this.modeofp = modeofp;
    }
}
