package com.ektastudio.engggirl.hostliz;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class student_info_zone extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ImageView display,image1,image2,image3,image4,image5;
    private static String home="Home";
    private static String info="info";
    private static String star="Rate";
    private static String logout="logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info_zone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ImageView imag1=new ImageView(this);//set image where u wanna make
        imag1.setImageResource(R.drawable.ic_home_black_24dp);//set sources of images

        ImageView image2=new ImageView(this);
        image2.setImageResource(R.drawable.ic_info_black_24dp);

        ImageView image3=new ImageView(this);
        image3.setImageResource(R.drawable.ic_stars_black_24dp);

        ImageView image4=new ImageView(this);
        image4.setImageResource(R.drawable.ic_power_settings_new_black_24dp);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        SubActionButton button1=itemBuilder.setContentView(imag1).build();
        button1.setTag(home);
        button1.setOnClickListener(this);


        SubActionButton button2=itemBuilder.setContentView(image2).build();
        button2.setTag(info);
        button2.setOnClickListener(this);

        SubActionButton button3=itemBuilder.setContentView(image3).build();
        button3.setTag(star);
        button3.setOnClickListener(this);

        SubActionButton button4=itemBuilder.setContentView(image4).build();
        button4.setTag(logout);
        button4.setOnClickListener(this);

        FloatingActionMenu actionMenu= new FloatingActionMenu.Builder(this).addSubActionView(button1).addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(fab).build();
        image1=(ImageView)findViewById(R.id.image1);
        image2=(ImageView)findViewById(R.id.image2);
        image3=(ImageView)findViewById(R.id.image3);
        image4=(ImageView)findViewById(R.id.image4);
        image5=(ImageView)findViewById(R.id.image5);
        display=(ImageView)findViewById(R.id.imageSelected);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setImageResource(R.drawable.pic1);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setImageResource(R.drawable.pic2);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setImageResource(R.drawable.pic3);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setImageResource(R.drawable.pic6);
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setImageResource(R.drawable.pic5);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);

        } else
        {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            Intent profile=new Intent(this,Profile.class);
            this.finish();
            startActivity(profile);

        } else if (id == R.id.nav_gallery)
        {
            Intent gallery=new Intent(this,student_info_zone.class);
            startActivity(gallery);
        } else if (id == R.id.nav_slideshow)
        {
            Intent slide_show=new Intent(this,Gallery_Show.class);
            startActivity(slide_show);
        } else if (id == R.id.nav_rate)
        {
        Intent rate=new Intent(this,Rating_Bar.class);
            startActivity(rate);
        } else if (id == R.id.nav_contact)
        {
Intent contact=new Intent(this,Contact_us.class);
            startActivity(contact);
        } else if (id == R.id.nav_send)
        {
            Log.i("send mail","");
            String[] emailTo={"ekta0075dushanj@gmail.com"};
            String[] feedback={"Hosteliz App feedback "};
         Intent emailIntent=new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plan/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emailTo);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,feedback);
            try{
             startActivity(Intent.createChooser(emailIntent,"sending mail"));
                Log.i("finished mail","");
            }
            catch(Exception e)
            {
                Toast.makeText(this,"Error occured please provide internet connection",Toast.LENGTH_LONG).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getTag().equals(home))
        {
            Intent home=new Intent(this,LoginOptionButton.class);
            this.finish();
            startActivity(home);
        }
       if(v.getTag().equals(info))
        {
            Intent info=new Intent(this,Profile.class);
            startActivity(info);
        }
     if(v.getTag().equals(star))
        {
            Intent rate=new Intent(this,Rating_Bar.class);
            startActivity(rate);
        }
     if(v.getTag().equals(logout))
        {
         /*   AlertDialog.Builder alertlog=new AlertDialog.Builder(this);
            alertlog.setTitle("Sure to logout");
            alertlog.setPositiveButton("logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent logout = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(logout);

                }
            });
            alertlog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });*/
            Toast.makeText(this,"logout",Toast.LENGTH_SHORT);
        }
    }
}
