package com.sande.supermarketdb.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sande.supermarketdb.Database.Database;
import com.sande.supermarketdb.DatabaseClasses.EmployeeDB;
import com.sande.supermarketdb.R;
import com.sande.supermarketdb.Utils.UtilsClass;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ArrayMap<Integer,String> userPass=new ArrayMap<>();
    private EditText mUser;
    private EditText mPass;
    private ArrayList<EmployeeDB> empList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Database mDB=new Database(this);
        empList=mDB.getAllEmployee();
        for(EmployeeDB x:empList){
            userPass.put(x.EID,x.Password);
        }
        mUser=(EditText)findViewById(R.id.user_et_login);
        mPass=(EditText)findViewById(R.id.pass_et_login);
    }

    public void loginInit(View view) {
        if(mUser.getText().length()==0||mPass.getText().length()==0){
            Toast.makeText(LoginActivity.this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();
        }else{
            int givenUsername=Integer.parseInt(mUser.getText().toString());
            String givenPass=mPass.getText().toString();
            if(userPass.containsKey(givenUsername)){
                if(givenPass.equals(userPass.get(givenUsername))){
                    EmployeeDB loggedIn=empList.get(userPass.indexOfKey(givenUsername));
                    String isLoggedinBy=loggedIn.Ename+loggedIn.EID;
                    UtilsClass.setIsLoggedInBy(getApplicationContext(),isLoggedinBy);
                    UtilsClass.setIsLoggedIn(getApplicationContext(),true);
                    Intent inte=new Intent(this,MainActivity.class);
                    inte.putExtra("extra_data",isLoggedinBy);
                    startActivity(inte);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(LoginActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
