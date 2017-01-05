package com.ektastudio.engggirl.hostliz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Warden extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    TextInputLayout tname,tpass,tconfirmPass,temail;
    EditText eName,ePass,eConfirmpass;
    AutoCompleteTextView email;
    Button bRegi,bCancel,bRegisterWardenBack;
    View focusView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDataBase();
        setContentView(R.layout.activity_register__warden);


        tname=(TextInputLayout)findViewById(R.id.TextRegUser);
        tpass=(TextInputLayout)findViewById(R.id.TextPassword);
        tconfirmPass=(TextInputLayout)findViewById(R.id.TextPasswordConfirm);
        temail=(TextInputLayout)findViewById(R.id.TextEmail);


        eName=(EditText)findViewById(R.id.EditRegText);
        ePass=(EditText)findViewById(R.id.EditPassword);
        eConfirmpass=(EditText)findViewById(R.id.EditPasswordConfrm);
        email=(AutoCompleteTextView)findViewById(R.id.email);




        bRegi=(Button)findViewById(R.id.ButtonRegister);
        bCancel=(Button)findViewById(R.id.btnCancel);
        bRegisterWardenBack=(Button)findViewById(R.id.buttonwardentop);

        bRegi.setOnClickListener(this);
        bCancel.setOnClickListener(this);
        bRegisterWardenBack.setOnClickListener(this);
    }

    public void createDataBase()
    {
        db=openOrCreateDatabase("HostelInfo",MODE_PRIVATE,null);
    }


    public void InsertWardenData()
    {
        String name1=eName.getText().toString().trim();
        String pass1=ePass.getText().toString().trim();
        String confrmPass=eConfirmpass.getText().toString().trim();
        String email1=email.getText().toString().trim();


        if(name1.equals("")||pass1.equals("")||confrmPass.equals("")||email1.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!CustomFunctions.isEmailValid(email1))
        {
            Toast.makeText(this, "Invalid email.", Toast.LENGTH_SHORT).show();
            return;
        }
        //password match validation
        if(!pass1.equals(confrmPass))
        {
            Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            try{
            String query1="INSERT into WardanInfo(Name,Password,ConfrmPass,Email) VALUES('"+name1+"','"+pass1+"','"+confrmPass+"','"+email1+"');";
            db.execSQL(query1);
            Toast.makeText(getApplicationContext(),"Registered warden now login",Toast.LENGTH_SHORT).show();
            Intent wardenLogin=new Intent(this,Warden_Login.class);
                wardenLogin.putExtra("wardenName",name1);
               this.finish();
                }
            catch(Exception e)
            {
                Toast.makeText(this,"exception"+e.toString(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*Email validation*/
    public static class CustomFunctions {

        public static boolean isEmailValid(String email) {
            return email.contains("@");
        }

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
        if(v==bRegi)
        {
      if(TextUtils.isEmpty(eName.getText()))
        { eName.setError("This field is required");
            focusView=eName;} // validations
            if(TextUtils.isEmpty(ePass.getText()))
            {ePass.setError("Password field is required");}
            if(TextUtils.isEmpty(eConfirmpass.getText()))
            {eConfirmpass.setError("Password field is required");}
            if(TextUtils.isEmpty(email.getText()))
            {email.setError("Password field is required");}
            else{ InsertWardenData();}
        }

        if(v==bCancel)
        {
            AlertDialog.Builder alert1=new AlertDialog.Builder(this);
            alert1.setMessage("Are you sure to Cancel!! ");
            alert1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Register_Warden.this, Admin_login.class);
                    startActivity(i);
                }
            });
            alert1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertbuild=alert1.create();
            alertbuild.show();
        }
        if(v==bRegisterWardenBack)
        {
            Intent backadmin=new Intent(this,LoginOptionButton.class);
            this.finish();
            startActivity(backadmin);
        }
    }
}




