package com.ektastudio.engggirl.hostliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;34

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Thread t1=new Thread(){
            public void run()
            {
                try{
                    sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }

                finally
                {
                    Intent main=new Intent(MainActivity.this,LoginOptionButton.class);
                    startActivity(main);
                    finish();
                }
            }
        };
        t1.start();
    }


    }

