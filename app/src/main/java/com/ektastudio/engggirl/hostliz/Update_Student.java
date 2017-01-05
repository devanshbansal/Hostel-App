package com.ektastudio.engggirl.hostliz;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.Toast;

public class Update_Student extends AppCompatActivity implements View.OnClickListener {
EditText eUser,eRoll,eHostel,eFloor,eRoom;
    Button bUpdate,bBack;
    private Cursor c;
    private SQLiteDatabase db;

//private static final String SELECT_SQL = "SELECT * FROM StudentInfo ";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__student);

      /*  getSupportActionBar().setHomeButtonEnabled(true);// for call back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        eUser=(EditText)findViewById(R.id.editUser);
        eRoll=(EditText)findViewById(R.id.editRoll);
        eHostel=(EditText)findViewById(R.id.editHostel);
        eFloor=(EditText)findViewById(R.id.editFloor);
        eRoom=(EditText)findViewById(R.id.editRoom);
        bUpdate=(Button)findViewById(R.id.button);
        bBack=(Button)findViewById(R.id.buttonWardenLoginTop);
        bUpdate.setOnClickListener(this);
        bBack.setOnClickListener(this);

        createDataBase();
  String roll=  getIntent().getStringExtra("rollno");
        eRoll.setText(roll);
  c = db.rawQuery("select * from StudentInfo where Roll_no='"+roll+"'", null);
       // c=db.rawQuery("select * from StudentInfo where Roll_no='"+eRoll+"'",null);
//Toast.makeText(this,"SELECT * FROM StudentInfo"+roll,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Roll no"+roll,Toast.LENGTH_LONG).show();
         c.moveToFirst();
             ShowRecord();
    }
    public void ShowRecord()
    {
        String Username1=c.getString(0);
        String Rollno1=c.getString(1);
        String Hostel1=c.getString(2);
        String Floor1=c.getString(3);
        String Room1=c.getString(4);
        eUser.setText(Username1);
        eRoll.setText(Rollno1);
        eHostel.setText(Hostel1);
        eFloor.setText(Floor1);
        eRoom.setText(Room1);


    }
    public void createDataBase()
    {
        db = openOrCreateDatabase("HostelInfo", MODE_PRIVATE, null);
    }

    public void UpdateStudent()
    {
        String Name=eUser.getText().toString().trim();
        String Roll=eRoll.getText().toString().trim();
        String Hostel=eHostel.getText().toString().trim();
        String Floor=eFloor.getText().toString().trim();
        String Room=eRoom.getText().toString().trim();

        String query_update="UPDATE  StudentInfo SET Name='"+Name+"',Hostel_name='"+Hostel+"',Floor_name='"+Floor+"',Room_no='"+Room+"' WHERE Roll_no="+Roll+";";

        if(Name.equals("")||Hostel.equals("")||Floor.equals("")||Room.equals(""))
        {
            Toast.makeText(getBaseContext(), "fill data", Toast.LENGTH_LONG).show();

            return;
        }
            db.execSQL(query_update);
        Toast.makeText(getApplicationContext(), "Record Updated Saved",Toast.LENGTH_LONG).show();
       }

        @Override
    public void onClick(View v)
        {
    if(v==bUpdate)
    {
    UpdateStudent();
    }
    if(v==bBack)
    {
        Intent back_detail=new Intent(this,View_Students_Details.class);
        this.finish();
        startActivity(back_detail);
    }
        }
}
