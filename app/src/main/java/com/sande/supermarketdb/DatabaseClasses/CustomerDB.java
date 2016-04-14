package com.sande.supermarketdb.DatabaseClasses;

/**
 * Created by Sandeep on 12-Apr-16.
 */
public class CustomerDB {
    public int CID;
    public String Cname;
    public long Contact;
    public String Email;

    public CustomerDB(){

    }

    public CustomerDB(int CID, String cname, String contact, String email) {
        this.CID = CID;
        Cname = cname;
        Contact = Long.parseLong(contact);
        Email = email;
    }

    public String getCID() {
        return String.valueOf(CID);
    }

    public String getCname() {
        return Cname;
    }

    public String getContact() {
        return String.valueOf(Contact);
    }

    public String getEmail() {
        return Email;
    }

}
