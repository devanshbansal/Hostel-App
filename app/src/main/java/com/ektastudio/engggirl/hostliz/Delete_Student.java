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

public class Delete_Student extends AppCompatActivity implements View.OnClickListener {
    EditText eUser,eRoll,eHostel,eFloor,eRoom;
    Button bDelete,backTop;
    private Cursor c;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__student);

        eUser=(EditText)findViewById(R.id.editUser);
        eRoll=(EditText)findViewById(R.id.editRoll);
        eHostel=(EditText)findViewById(R.id.editHostel);
        eFloor=(EditText)findViewById(R.id.editFloor);
        eRoom=(EditText)findViewById(R.id.editRoom);
        bDelete=(Button)findViewById(R.id.button);
        backTop=(Button)findViewById(R.id.buttonWardenLoginTop);
        bDelete.setOnClickListener(this);
        backTop.setOnClickListener(this);

        createDataBase();
        String roll=  getIntent().getStringExtra("rollno");
        eRoll.setText(roll);
        c = db.rawQuery("select * from StudentInfo where Roll_no='"+roll+"'", null);
        Toast.makeText(this, "Roll no" + roll, Toast.LENGTH_LONG).show();
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

    public void DeleteRecord()
    {
        AlertDialog.Builder alertdelte=new AlertDialog.Builder(this);
        alertdelte.setTitle("Are you sure to delete!!");
        alertdelte.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String roll = eRoll.getText().toString().trim();
                String query_delete = "DELETE FROM StudentInfo WHERE Roll_no=" + roll + ";";
                db.execSQL(query_delete);
                Toast.makeText(getApplicationContext(), "Record Delete", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alert=alertdelte.create();
        alert.show();
    }

    @Override
    public void onClick(View v)
    {
        if(v==bDelete)
        { DeleteRecord();}
        if(v==backTop)
        {
            Intent back_detail=new Intent(this,View_Students_Details.class);
        this.finish();
            startActivity(back_detail);
        }


    }
}
