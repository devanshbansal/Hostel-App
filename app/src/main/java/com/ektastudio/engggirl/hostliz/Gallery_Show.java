package com.ektastudio.engggirl.hostliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Gallery_Show extends AppCompatActivity  implements View.OnClickListener{
    Animation fadeIn,fadeOut;
    ViewFlipper viewf;
    Button bstart,bstop,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery__show);
        viewf=(ViewFlipper)findViewById(R.id.View1);
        bstart=(Button)findViewById(R.id.buttonStart);
        bstop=(Button)findViewById(R.id.buttonStop);
        back=(Button)findViewById(R.id.buttonwardentop);

        fadeIn= AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeOut=AnimationUtils.loadAnimation(this,android.R.anim.fade_out);


        viewf.setInAnimation(fadeIn);
        viewf.setOutAnimation(fadeOut);
        bstart.setOnClickListener(this);
        bstop.setOnClickListener(this);
back.setOnClickListener(this);

      //  viewf.setAutoStart(true);
        viewf.setFlipInterval(4000);
        //viewf.startFlipping();

    }

    @Override
    public void onClick(View v)
    {
        if(v==bstart)
        {
            try{
                viewf.startFlipping();
                bstart.setEnabled(false);
                bstop.setEnabled(true);
            }
            catch(Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        if(v==bstop)
        {
            try{
                viewf.stopFlipping();
                bstart.setEnabled(true);
                bstop.setEnabled(false);
            } catch(Exception e)
            {
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
            }

        }
        if(v==back)
        {
            Intent back=new Intent(this,student_info_zone.class);
            this.finish();
            startActivity(back);

        }
    }
    }


