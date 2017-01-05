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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Room_Allocation extends AppCompatActivity implements  View.OnClickListener, AdapterView.OnItemSelectedListener{

    public SQLiteDatabase db;
    TextInputLayout tName,tRoll;
    TextView textadmin;
    private EditText studentnameInput,studentRollnoInput;
    private Button btnAllocate,btnCancel,bRegistedBack;
    private Spinner spHostel,spFloor,spRooms;

    ArrayAdapter<String> adapterValue;


    private static final String[] hostelname={"Select Hostel","Aravali","Girnar","Nilgiri","Shivalik","Castle Hostel","Wonder Lodge"};
    private static final String[] aravalifloor1={"Select","Floor1","Floor2","Floor3","Floor4"};
    private static final String[] girnarfloor1={"Select","Floor1","Floor2","Floor3","Floor4","Floor5"};
    private static final String[] nilgirifloor1={"Select","Floor1","Floor2","Floor3","Floor4","Floor5","Floor6"};
    private static final String[] shivalikfloor1={"Select","Floor1","Floor2","Floor3","Floor4","Floor5"};
    private static final String[] castleflorr={"Select","Floor1","Floor2","Floor3","Floor4","Floor5"};
    private static final String[] wonderfloor={"Select","Floor1","Floor2","Floor3","Floor4","Floor5"};
    private static final String[] floor1={"Select","Room 1","Room 2","Room 3","Room 4","Room 5","Room 6"};
    private static final String[] floor2={"Select","Room 7","Room 8","Room 9","Room 10","Room 11","Room 12"};
    private static final String[] floor3={"Select","Room 13","Room 14","Room 15","Room 16","Room 17","Room 18"};
    private static final String[] floor4={"Select","Room 19","Room 20","Room 21","Room 22","Room 23","Room 24"};
    private static final String[] floor5={"Select","Room 25","Room 26","Room 27","Room 28","Room 29","Room 30"};
    private static final String[] floor6={"Select","Room 31","Room 32","Room 33","Room 34","Room 35","Room 36"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDataBase();
        setContentView(R.layout.activity_room__allocation);

        studentnameInput=(EditText)findViewById(R.id.editName);
        studentRollnoInput=(EditText)findViewById(R.id.editRollno);
        tName=(TextInputLayout)findViewById(R.id.textInputName);
        tRoll=(TextInputLayout)findViewById(R.id.textInputRoll);
        btnAllocate=(Button)findViewById(R.id.btnAllocate);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        bRegistedBack=(Button)findViewById(R.id.buttonRoomAllocationtop);
        String user=getIntent().getStringExtra("Username");// this below three lines are used to show admin name on next page

        textadmin=(TextView)findViewById(R.id.TextAdmin);
        textadmin.setText(user);


        spFloor=(Spinner)findViewById(R.id.spinnerFloor);
        spRooms=(Spinner)findViewById(R.id.spinnerRoom);

        adapterValue=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerdesign,R.id.spinnertext,hostelname);

        spHostel=(Spinner)findViewById(R.id.spinnerHostel);
        spHostel.setAdapter(adapterValue);
        spHostel.setOnItemSelectedListener(this);

        btnAllocate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        bRegistedBack.setOnClickListener(this);

        spFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String floorPos=String.valueOf(spFloor.getSelectedItem());
                if(floorPos.contentEquals("Floor1"))
                {
                    ArrayAdapter<String>floor11=new ArrayAdapter<String>(getBaseContext(),R.layout.spinnerdesign,R.id.spinnertext,floor1);
                    spRooms.setAdapter(floor11);
                }
                if(floorPos.contentEquals("Floor2"))
                {
                    ArrayAdapter<String>floor22=new ArrayAdapter<String>(getBaseContext(),R.layout.spinnerdesign,R.id.spinnertext,floor2);
                    spRooms.setAdapter(floor22);
                }
                if(floorPos.contentEquals("Floor3"))
                {
                    ArrayAdapter<String>floor33=new ArrayAdapter<String>(getBaseContext(),R.layout.spinnerdesign,R.id.spinnertext,floor3);
                    spRooms.setAdapter(floor33);
                }
                if(floorPos.contentEquals("Floor4"))
                {
                    ArrayAdapter<String>floor44=new ArrayAdapter<String>(getBaseContext(),R.layout.spinnerdesign,R.id.spinnertext,floor4);
                    spRooms.setAdapter(floor44);
                }
                if(floorPos.contentEquals("Floor5"))
                {
                    ArrayAdapter<String>floor55=new ArrayAdapter<String>(getBaseContext(),R.layout.spinnerdesign,R.id.spinnertext,floor5);
                    spRooms.setAdapter(floor55);
                }
                if(floorPos.contentEquals("Floor6"))
                {
                    ArrayAdapter<String>floor66=new ArrayAdapter<String>(getBaseContext(),R.layout.spinnerdesign,R.id.spinnertext,floor6);
                    spRooms.setAdapter(floor66);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }
    public void createDataBase()
    {
        db=openOrCreateDatabase("HostelInfo",MODE_PRIVATE,null);
    }

    public void insertIntoData()
    {
        try {
            String studenyname = studentnameInput.getText().toString().trim();
            String studenyRoll = studentRollnoInput.getText().toString().trim();
            String studenyHostel = String.valueOf(spHostel.getSelectedItem());
            String studenyFloor = String.valueOf(spFloor.getSelectedItem());
            String studenyRoom = String.valueOf(spRooms.getSelectedItem());



            if (studenyname.equals("") || studenyRoll.equals("") || studenyHostel.equals("") || studenyFloor.equals("") || studenyRoom.equals("")) {
                Toast.makeText(Room_Allocation.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            } else {
                //Toast.makeText(AllocateRoomsToStudent1.this,"Good",Toast.LENGTH_SHORT).show();

                String qyery1 = "INSERT INTO StudentInfo(Name,Roll_no,Hostel_name,Floor_name,Room_no) VALUES('" + studenyname + "','" + studenyRoll + "','" + studenyHostel + "','" + studenyFloor + "','" + studenyRoom + "');";
                db.execSQL(qyery1);
                Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(Room_Allocation.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }



    public boolean ValidateStudentName()
    {
        if(studentnameInput.getText().toString().trim().isEmpty())
        {
            tName.setError("Please fill Student name");
            requestFocus(tName);
            return false;
        }
        else
            tName.setErrorEnabled(false);
        return true;
    }
    public boolean ValidateStudentRollno()
    {
        if(studentRollnoInput.getText().toString().trim().isEmpty())
        {
            tRoll.setError("Please fill Student Roll no.");
            requestFocus(tRoll);
            return false;
        }
        else
            tRoll.setErrorEnabled(false);
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
        if(v==btnAllocate)
        {

            insertIntoData();

            //    Toast.makeText(getApplicationContext(),"Hello again",Toast.LENGTH_LONG).show();

        }
        if(v==btnCancel)
        {
            AlertDialog.Builder alert1=new AlertDialog.Builder(this);
            alert1.setMessage("Are you sure to cancel!");
            alert1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Room_Allocation.this, MainActivity.class);
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
        if(v==bRegistedBack)
        {
            Intent backadmin=new Intent(this,Admin_login.class);
            this.finish();
            startActivity(backadmin);

        }

    }







    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String spinnerHostel=String.valueOf(spHostel.getSelectedItem());
        if(spinnerHostel.contentEquals("Aravali"))
        {
            ArrayAdapter<String>aravalifloorValues=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerdesign,R.id.spinnertext,aravalifloor1);
            spFloor.setAdapter(aravalifloorValues);
        }
        if(spinnerHostel.contentEquals("Girnar"))
        {
            ArrayAdapter<String>girnarfloorValues=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerdesign,R.id.spinnertext,girnarfloor1);
            spFloor.setAdapter(girnarfloorValues);
        }
        if(spinnerHostel.contentEquals("Nilgiri"))
        {
            ArrayAdapter<String>nilgirifloorValues=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerdesign,R.id.spinnertext,nilgirifloor1);
            spFloor.setAdapter(nilgirifloorValues);
        }
        if(spinnerHostel.contentEquals("Shivalik"))
        {
            ArrayAdapter<String>shivalikfloorValues=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerdesign,R.id.spinnertext,shivalikfloor1);
            spFloor.setAdapter(shivalikfloorValues);
        }
        if(spinnerHostel.contentEquals("Castle Hostel"))
        {
            ArrayAdapter<String>shivalikfloorValues=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerdesign,R.id.spinnertext,castleflorr);
            spFloor.setAdapter(shivalikfloorValues);
        }
        if(spinnerHostel.contentEquals("Wonder Lodge"))
        {
            ArrayAdapter<String>shivalikfloorValues=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinnerdesign,R.id.spinnertext,wonderfloor);
            spFloor.setAdapter(shivalikfloorValues);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
    }




