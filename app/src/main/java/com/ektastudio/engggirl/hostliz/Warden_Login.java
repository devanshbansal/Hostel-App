package com.ektastudio.engggirl.hostliz;

import android.content.Intent;
import android.database.Cursor;
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

public class Warden_Login extends AppCompatActivity implements View.OnClickListener {
    public SQLiteDatabase db;
    private TextInputLayout textuser, textpass;
    private EditText editUser, editPass;
    private Button bLogin, bManage,bWardenTop;
    TextView forgetPassword;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createDataBase();
        setContentView(R.layout.activity_warden__login);


        textuser = (TextInputLayout) findViewById(R.id.TextInput1);
        textpass = (TextInputLayout) findViewById(R.id.TextInput2);
        editUser = (EditText) findViewById(R.id.EditText1);
        editPass = (EditText) findViewById(R.id.EditText2);
        bLogin = (Button) findViewById(R.id.ButtonSub);
        bManage=(Button)findViewById(R.id.btnCancel1);
        forgetPassword=(TextView)findViewById(R.id.TextForget);

        bWardenTop=(Button)findViewById(R.id.buttonWardenLoginTop);
        bLogin.setOnClickListener(this);
        bManage.setOnClickListener(this);
        bWardenTop.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);



    }
    public void createDataBase()
    {
        db=openOrCreateDatabase("HostelInfo",MODE_PRIVATE,null);
    }


    public boolean ValidateName()
    {
        if(editUser.getText().toString().trim().isEmpty())
        {
            textuser.setError("fill name field");
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
            textpass.setError("Fill password field");
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
    public void WardenLogin()
    {
        try{
        String wardenName=editUser.getText().toString().trim();
        String wardenPass=editPass.getText().toString().trim();
        String query="select * from WardanInfo where Email='"+wardenName+"' and Password='"+wardenPass+"'";
        Cursor c1=db.rawQuery(query,null);
        if(c1.moveToNext()){
         Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
            Intent takeAttendence=new Intent(this,Warden_TakeAttendence.class);
            this.finish();
            startActivity(takeAttendence);
        }else

                Toast.makeText(getApplicationContext(),"Check Username",Toast.LENGTH_SHORT).show();
        } catch(Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }






    }
    public void onClick(View v)     
    {
        if (v == bLogin)
        {
           if (!ValidateName()&& !ValidatePassword())
            {
                return;
            }
           else
           {
               WardenLogin();

           }
        }


        if(v==bWardenTop)
        {
            Intent backwarden=new Intent(this,LoginOptionButton.class);
            this.finish();
            startActivity(backwarden);
        }
        if(v==forgetPassword)
        {
           // Toast.makeText(this,"here",Toast.LENGTH_LONG).show();
            Intent forgetpassword=new Intent(this,ForgetPassword.class);
            this.finish();
            startActivity(forgetpassword);

        }
        if(v==bManage)
        {Intent cacel=new Intent(this,View_Students_Details.class);
        startActivity(cacel);}
    }
}





