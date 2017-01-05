package com.ektastudio.engggirl.hostliz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class Warden_TakeAttendence extends AppCompatActivity implements View.OnClickListener {

    TableLayout tablemain;
    SQLiteDatabase db;
    private Cursor c;
    TextView textwarden;
    private static String home="Home";
    private static String info="info";
    private static String star="Rate";
    private static String logout="logout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_take_attendence);

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

        FloatingActionMenu actionMenu= new FloatingActionMenu.Builder(this).addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(fab).build();


        tablemain = (TableLayout) findViewById(R.id.TableLayoutData);

        String user=getIntent().getStringExtra("wardenName");
        textwarden=(TextView)findViewById(R.id.TextWarden);
        textwarden.setText(user);
        createDataBase();
        c = db.rawQuery("select Name, Roll_no, Hostel_name, Floor_name, Room_no from StudentInfo", null);

        int rows = c.getCount();

        int cols = 6;
        tablemain.removeAllViews();
        BuildTable(rows, cols, c);
   // c.close();
    }

    public void createDataBase() {
        db = openOrCreateDatabase("HostelInfo", Context.MODE_PRIVATE, null);
    }


    private void BuildTable(int rows, int column, Cursor c) {

        TableRow rowhead = new TableRow(this);
        rowhead.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvhead = new TextView(this);
        tvhead.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tvhead.setPadding(5, 5, 5, 5);
        tvhead.setText("Name");
        rowhead.addView(tvhead);

        TextView tvhead2 = new TextView(this);
        tvhead2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tvhead2.setPadding(5, 5, 5, 5);
        tvhead2.setText("Rol No.");
        rowhead.addView(tvhead2);

        TextView tvhead3 = new TextView(this);
        tvhead3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tvhead3.setPadding(5, 5, 5, 5);
        tvhead3.setText("Hostel");
        rowhead.addView(tvhead3);

        TextView tvhead4 = new TextView(this);
        tvhead4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tvhead4.setPadding(5, 5, 5, 5);
        tvhead4.setText("Floor");
        rowhead.addView(tvhead4);

        TextView tvhead5 = new TextView(this);
        tvhead5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tvhead5.setPadding(5, 5, 5, 5);
        tvhead5.setText("Room no");
        rowhead.addView(tvhead5);

        TextView tvhead6 = new TextView(this);
        tvhead6.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tvhead6.setPadding(5, 5, 5, 5);
        tvhead6.setText("Attendence");
        rowhead.addView(tvhead6);

        tablemain.addView(rowhead);
//outer loop
        int i = 1;
        while (c.moveToNext()) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            // inner for loop
            for (int j = 0; j < column; j++) {

                if (j == 5) {
                   // String markedAttend = c.getString(j);
                    RadioGroup rg = new RadioGroup(this);
                    rg.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    RadioButton b1 = new RadioButton(this);
                    b1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    b1.setText("Present");

                    RadioButton b2 = new RadioButton(this);
                    b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    b2.setText("Absent");

                    rg.addView(b1);
                    rg.addView(b2);
                    row.addView(rg);

                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override

                        public void onCheckedChanged(RadioGroup group, int checkedId) {

                            RadioButton selectetBtn = (RadioButton) findViewById(checkedId);
                           TableRow r = (TableRow) group.getParent();
                            TextView t = (TextView) r.getChildAt(1);
                            String studentRoll = t.getText().toString();
                            try{
                            String query="select * from StudentAttendence where Roll_no='"+studentRoll+"'";
                            Cursor innerC = db.rawQuery(query, null);
                            if (innerC.getCount() > 0) {
                                String update="update StudentAttendence set Attendence='"+selectetBtn.getText()+"' where Roll_no='"+studentRoll+"'";
                                db.execSQL(update);
                            } else {
                                String insertattendence="insert into StudentAttendence(Roll_no,Attendence) values("+studentRoll+",'"+selectetBtn.getText()+"')";
                                db.execSQL(insertattendence);
                            }
                            innerC.close();
                            Toast.makeText(getApplicationContext(), " Marked " + selectetBtn.getText() + " for StudentRoll " + studentRoll, Toast.LENGTH_SHORT).show();
                            }catch(Exception e)
                            {
                                Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });



                }//|| j == 4
                else if (j == 1) {
                    TextView tv = new TextView(Warden_TakeAttendence.this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(String.valueOf(c.getInt(j)));
                    row.addView(tv);
                } else {
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(c.getString(j));
                    row.addView(tv);
                }

            }
            i++;
            tablemain.addView(row);
        }



    }

    public void headermenu(View v)
    {
        int fetchId=v.getId();

        if(fetchId==R.id.home_icon)
        {Intent home=new Intent(this,LoginOptionButton.class);
            startActivity(home);
        }
        if(fetchId==R.id.info_icon)
        {
            // Toast.makeText(getBaseContext(),"soon",Toast.LENGTH_SHORT).show();
            Intent info=new Intent(this,Profile.class);
            this.finish();
            startActivity(info);

        }
        if(fetchId==R.id.location_icon)
        {
            Intent contact=new Intent(this,Contact_us.class);
            this.finish();
            startActivity(contact);
        }
        if(fetchId==R.id.feedback)
        {
            Toast.makeText(this,"feedback",Toast.LENGTH_SHORT).show();
        }
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
            AlertDialog.Builder alert1=new AlertDialog.Builder(this);
            alert1.setMessage("Are you sure to Logout!! ");
            alert1.setPositiveButton("logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
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
    }
}
