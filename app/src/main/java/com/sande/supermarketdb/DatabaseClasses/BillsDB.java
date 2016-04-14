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
        return String.valueOf(BID);
    }

    public String getCID() {
        return String.valueOf(CID);
    }

    public String getEID() {
        return String.valueOf(EID);
    }

    public String getBill_amount() {
        return String.valueOf(bill_amount);
    }

    public String getTotal_profit() {
        return String.valueOf(total_profit);
    }

    public String getbTime() {
        return bTime;
    }

    public String getModeofp() {
        return modeofp;
    }

    public BillsDB(){}

    public BillsDB(int BID, int CID, int EID, double bill_amount, double total_profit, String bTime, String modeofp) {
        this.BID = BID;
        this.CID = CID;
        this.EID = EID;
        this.bill_amount = bill_amount;
        this.total_profit = total_profit;
        this.bTime = bTime;
        this.modeofp = modeofp;
    }
}
