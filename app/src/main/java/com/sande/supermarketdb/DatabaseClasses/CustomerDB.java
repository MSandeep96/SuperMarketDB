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

    public String getCID() {
        return "Customer ID:\t"+CID;
    }

    public String getCname() {
        return "Customer Name:\t"+Cname;
    }

    public String getContact() {
        return "Customer Phone:\t"+Contact;
    }

    public String getEmail() {
        return "EmailID:\t"+Email;
    }

    public String getTotal_spent() {
        return "Total Spent:\t"+total_spent;
    }
}
