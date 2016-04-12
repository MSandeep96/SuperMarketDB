package com.sande.supermarketdb.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sandeep on 13-Apr-16.
 */
public class UtilsClass {

    public static boolean getIsLoggedIn(Context mContext){
        SharedPreferences mShared=mContext.getSharedPreferences(ProjectCons.SHAREDPREFS,Context.MODE_PRIVATE);
        return mShared.getBoolean(ProjectCons.LOGGED_STATUS,false);
    }

    public static String getIsLoggedInBy(Context mContext){
        SharedPreferences mShared=mContext.getSharedPreferences(ProjectCons.SHAREDPREFS,Context.MODE_PRIVATE);
        return mShared.getString(ProjectCons.LOGGED_IN_BY,"Nobody");
    }

    public static void setIsLoggedIn(Context mContext,Boolean mIslogged){
        SharedPreferences mShared=mContext.getSharedPreferences(ProjectCons.SHAREDPREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mShared.edit();
        mEditor.putBoolean(ProjectCons.LOGGED_STATUS,mIslogged);
        mEditor.apply();
    }

    public static void setIsLoggedInBy(Context mContext,String mIsLoggedinBy){
        SharedPreferences mShared=mContext.getSharedPreferences(ProjectCons.SHAREDPREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mShared.edit();
        mEditor.putString(ProjectCons.LOGGED_IN_BY,mIsLoggedinBy);
        mEditor.apply();
    }


}
