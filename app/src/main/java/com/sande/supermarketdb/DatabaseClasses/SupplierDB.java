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

    public String getSID() {
        return "Supplier ID:\t"+SID;
    }

    public String getSname() {
        return "Supplier Name:\t"+Sname;
    }

    public String getScontact() {
        return "Supplier Contact:\t"+Scontact;
    }
}
