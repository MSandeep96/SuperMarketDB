package com.sande.supermarketdb.DatabaseClasses;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class CustomerDB {
    public int CID;
    public String Cname;
    public long Contact;
    public String Email;
    public double total_spent;

    public CustomerDB(){

    }

    public CustomerDB(int CID, String cname, long contact, String email, double total_spent) {
        this.CID = CID;
        Cname = cname;
        Contact = contact;
        Email = email;
        this.total_spent = total_spent;
    }
}
