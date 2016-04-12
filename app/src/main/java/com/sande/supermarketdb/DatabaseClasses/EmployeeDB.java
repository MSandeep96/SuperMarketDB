package com.sande.supermarketdb.DatabaseClasses;


/**
 * Created by Sandeep on 12-Apr-16.
 */
public class EmployeeDB {
    public int EID;
    public String Ename;
    public String Password;
    public String Sex;
    public double Salary;
    public String JobType;

    public EmployeeDB(){}

    public EmployeeDB(int EID, String ename, String password, String sex, double salary, String jobType) {
        this.EID = EID;
        Ename = ename;
        Password = password;
        Sex = sex;
        Salary = salary;
        JobType = jobType;
    }
}
