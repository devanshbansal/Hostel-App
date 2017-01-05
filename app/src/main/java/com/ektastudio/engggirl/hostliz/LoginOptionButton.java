package com.ektastudio.engggirl.hostliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LoginOptionButton extends AppCompatActivity implements View.OnClickListener {

    Button badmin,bWarden,bStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option_button);
               badmin=(Button)findViewById(R.id.adminButton1);
        bWarden=(Button)findViewById(R.id.WardenButton1);
        bStudent=(Button)findViewById(R.id.StudentButton1);

        badmin.setOnClickListener(this);
        bWarden.setOnClickListener(this);
        bStudent.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
         if(v==badmin)
        {
        Intent admin=new Intent(LoginOptionButton.this,Admin_login.class);
        startActivity(admin);
        }
        if(v==bWarden)
        {
            Intent warden=new Intent(LoginOptionButton.this,Warden_Login.class);
            startActivity(warden);
        }
        if(v==bStudent)
        {
        Intent InfoStudent=new Intent(this,student_info_zone.class);
            startActivity(InfoStudent);
        }

    }
}
