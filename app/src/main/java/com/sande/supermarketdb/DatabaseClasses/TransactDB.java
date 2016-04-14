package com.sande.supermarketdb.DatabaseClasses;

/**
 * Created by Sandeep on 15-Apr-16.
 */
public class TransactDB {
    int BID;
    int PID;
    int Pquan;

    public TransactDB(int BID, int PID, int pquan) {
        this.BID = BID;
        this.PID = PID;
        Pquan = pquan;
    }

    public TransactDB() {
    }


    public String getBID() {
        return String.valueOf(BID);
    }

    public String getPID() {
        return String.valueOf(PID);
    }

    public String getPquan() {
        return String.valueOf(Pquan);
    }
}
