package com.sande.supermarketdb;

import com.sande.supermarketdb.Pojo.New_bill_item;

/**
 * Created by Sandeep on 13-Apr-16.
 */
public interface CallBack {
    void onAddItem(New_bill_item nbi);

    void resetFrag();

    void resetCust();

    void callTransactivity(int billid);
}
