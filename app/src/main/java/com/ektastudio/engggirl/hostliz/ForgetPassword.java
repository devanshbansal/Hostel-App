package com.ektastudio.engggirl.hostliz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout tForget;
    AutoCompleteTextView editForget;
    SQLiteDatabase db;
    Button bGetPassword,bSign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        createDataBase();

        tForget=(TextInputLayout)findViewById(R.id.TextForgetPass);
        editForget=(AutoCompleteTextView)findViewById(R.id.EditPassword);
        bGetPassword=(Button)findViewById(R.id.buttongetPassword);
        bSign_in=(Button)findViewById(R.id.button3);

        bGetPassword.setOnClickListener(this);
        bSign_in.setOnClickListener(this);

    }
    public void createDataBase()
    {
        db=openOrCreateDatabase("HostelInfo",MODE_PRIVATE,null);
    }

    public  void GetPasswordMtd()
    {
String email=editForget.getText().toString();
        if(!CustomFunctions.isEmailValid(email))
        {Toast.makeText(this,"Invalid Email",Toast.LENGTH_LONG).show();
        return;
        }
      //  else{Toast.makeText(this,"here Email",Toast.LENGTH_LONG).show();}
 String query_forget="select Password from WardanInfo where Email='"+email+"'";
            Cursor c=db.rawQuery(query_forget,null);
            if(!c.moveToNext())
            { Toast.makeText(this, "User does not exist.", Toast.LENGTH_SHORT).show();
                return;}
        String password=c.getString(0);
        AlertDialog.Builder alerPass=new AlertDialog.Builder(this);
        alerPass.setTitle("Your password is:");
        alerPass.setMessage(password);
        alerPass.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
AlertDialog alert=alerPass.create();
        alert.show();
    }
    public static class CustomFunctions {

        public static boolean isEmailValid(String email) {
            return email.contains("@");
        }

    }
    public boolean ValidateEmail()
    {
        if(editForget.getText().toString().trim().isEmpty()){
        tForget.setError("Please enter email");
            requestFocus(tForget);
        return false;}
        else
            tForget.setErrorEnabled(false);
        return true;
    }



    public void requestFocus(View v)
    {
        if(v.requestFocus())
        {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }
    @Override
    public void onClick(View v)
    {
    if(v==bGetPassword)
    {

        if(!ValidateEmail())
        {return;}
        GetPasswordMtd();

    }
        if(v==bSign_in)
        {
            Intent wardenLogin=new Intent(this,Warden_Login.class);
            this.finish();
            startActivity(wardenLogin);
        }

    }

}
