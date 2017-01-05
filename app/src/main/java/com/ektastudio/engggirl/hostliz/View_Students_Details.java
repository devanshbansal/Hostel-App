package com.ektastudio.engggirl.hostliz;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.w3c.dom.Text;

public class View_Students_Details extends AppCompatActivity implements View.OnClickListener {

    TableLayout tablemain;
    SQLiteDatabase db;
    private Cursor c;
private static String home="Home";
    private static String info="info";
    private static String star="Rate";
    private static String logout="logout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__students__details);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /* floating action sub buttons  */

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

        tablemain = (TableLayout) findViewById(R.id.tableVew);


        createDataBase();
        c = db.rawQuery("select Name, Roll_no, Hostel_name, Floor_name, Room_no from StudentInfo", null);

        int rows = c.getCount();

        int cols = 6;
        tablemain.removeAllViews();
        BuildTable(rows, cols, c);

    }
    public void createDataBase()
    {
        db=openOrCreateDatabase("HostelInfo", Context.MODE_PRIVATE,null);
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
        tvhead6.setText("Update");
        rowhead.addView(tvhead6);

 /*     TextView tvhead7 = new TextView(this);
        tvhead6.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tvhead6.setPadding(5, 5, 5, 5);
        tvhead6.setText("Delete");
        rowhead.addView(tvhead7);   */


        tablemain.addView(rowhead);

        int i = 1;
        while (c.moveToNext()) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < column; j++) {

                if (j == 5) {

                    ImageButton imag1 = new ImageButton(this);
                    imag1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                  imag1.setImageResource(R.drawable.ic_mode_edit_black_24dp);
                   // imag.setBackgroundColor(R.color.colorPrimaryDark);

                    ImageButton imag2 = new ImageButton(this);
                    imag2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    imag2.setImageResource(R.drawable.ic_delete_black_24dp);

                    row.addView(imag1);
                    row.addView(imag2);
                    imag1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TableRow r = (TableRow) v.getParent();
                            TextView t = (TextView) r.getChildAt(1);
                            String studentRoll = t.getText().toString();
                            try {
                                String query = "select * from StudentAttendence where Roll_no='" + studentRoll + "'";
                                Cursor innerC = db.rawQuery(query, null);
                                if (innerC.getCount() > 0) {
                               //  Toast.makeText(getBaseContext(),"EditText"+studentRoll,Toast.LENGTH_LONG).show();
                                  Intent update = new Intent(View_Students_Details.this, Update_Student.class);
                                    update.putExtra("rollno", studentRoll);
                                    startActivity(update);
                                }
                                innerC.close();
                            } catch (Exception e) {
                                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    imag2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TableRow r=(TableRow)v.getParent();
                            TextView t = (TextView) r.getChildAt(1);
                            String studentRoll = t.getText().toString();
                            try{
                                String query="select * from StudentAttendence where Roll_no='"+studentRoll+"'";
                                Cursor innerC = db.rawQuery(query, null);
                                if (innerC.getCount() > 0)
                                {
                                  //Toast.makeText(getBaseContext(),"EditText"+studentRoll,Toast.LENGTH_LONG).show();
                                   Intent delete=new Intent(View_Students_Details.this,Delete_Student.class);
                                    delete.putExtra("rollno",studentRoll);
                                    startActivity(delete);
                                }
                                innerC.close();
                            }
                            catch(Exception e)
                            {Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();}
                        }
                    });
                   }


                   //|| j == 4
                else if (j == 1) {
                    TextView tv = new TextView(View_Students_Details.this);
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
