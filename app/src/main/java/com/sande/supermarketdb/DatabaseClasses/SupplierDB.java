package com.sande.supermarketdb.DatabaseClasses;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class SupplierDB {
    public int SID;
    public String Sname;
    public long Scontact;

    public SupplierDB(){

    }
    public SupplierDB(int SID, String sname, long scontact) {
        this.SID = SID;
        Sname = sname;
        Scontact = scontact;
    }
}
