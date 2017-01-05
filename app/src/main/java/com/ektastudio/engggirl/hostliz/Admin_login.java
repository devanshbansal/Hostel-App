package com.ektastudio.engggirl.hostliz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_login extends AppCompatActivity implements View.OnClickListener{

    public SQLiteDatabase db;
    private TextInputLayout textuser, textpass;
    private EditText editUser, editPass;
    private Button bLogin, bRegister,bAdminTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDataBase();
        setContentView(R.layout.activity_admin_login);


     /*  getSupportActionBar().setHomeButtonEnabled(true);// to set back arrow and also add meta beta in manifest otherwise back will not work
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        textuser = (TextInputLayout) findViewById(R.id.TextInput1);
        textpass = (TextInputLayout) findViewById(R.id.TextInput2);
        editUser = (EditText) findViewById(R.id.EditText1);
        editPass = (EditText) findViewById(R.id.EditText2);
        bLogin = (Button) findViewById(R.id.ButtonSub);
        bRegister = (Button) findViewById(R.id.btngisternew);
        bAdminTop=(Button)findViewById(R.id.buttonAdminTop);

        bLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);
        bAdminTop.setOnClickListener(this);

    }
    public void createDataBase()
    {
        db=openOrCreateDatabase("HostelInfo",MODE_PRIVATE,null);

        db.execSQL("CREATE TABLE IF NOT EXISTS WardanInfo(Name VARCHAR,Password VARCHAR NOT NULL,ConfrmPass VARCHAR NOT NULL,Email VARCHAR NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS StudentInfo(Name VARCHAR, Roll_no INTEGER PRIMARY KEY NOT NULL,Hostel_name VARCHAR, Floor_name VARCHAR, Room_no INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS StudentAttendence(Roll_no INTEGER PRIMARY KEY NOT NULL,Attendence VARCHAR);");

    }
    public void AdminLogin( ) {
        String username = editUser.getText().toString().trim();
        String password = editPass.getText().toString().trim();
          if(username.equals("admin")&& password.equals("123"))
        {
            Intent i=new Intent(Admin_login.this,Room_Allocation.class);
            i.putExtra("Username",username);
            this.finish();
            startActivity(i);

        }
        else
            Toast.makeText(getApplicationContext(),"Invalid User please make your account",Toast.LENGTH_SHORT).show();
    }

    public boolean ValidateName()
    {
        if(editUser.getText().toString().trim().isEmpty())
        {
            textuser.setError("This field is mandatory");
            requestFocus(textuser);
            return false;
        }
        else
            textuser.setErrorEnabled(false);
        return true;
    }
    public boolean ValidatePassword()
    {
        if(editPass.getText().toString().trim().isEmpty())
        {
            textpass.setError("This field is mandatory");
            requestFocus(textpass);
            return false;
        }
        else
            textpass.setErrorEnabled(false);
        return true;
    }
    public void requestFocus(View v)
    {
        if(v.requestFocus())
        {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }



    public void onClick(View v)
    {
        if (v == bLogin)
        {
            if(!ValidateName()&&!ValidatePassword())
            {
                return;
            }
            else
            AdminLogin();
        }
        if(v==bRegister)
        {  if(!ValidateName()&&!ValidatePassword())
        {
            return;
        }
            Intent wardenreg=new Intent(getBaseContext(),Register_Warden.class);
            this.finish();
            startActivity(wardenreg);
        }
        if(v==bAdminTop)
        {
            Intent adminback=new Intent(this,LoginOptionButton.class);
            this.finish();
            startActivity(adminback);
        }

    }



    }
