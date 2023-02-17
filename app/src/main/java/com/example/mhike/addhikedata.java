package com.example.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginsqlite.R;

import java.util.ArrayList;
import java.util.Calendar;

public class addhikedata extends AppCompatActivity {
    Button pickDateBtn,btnInsert, btnRetrive;;
    EditText etFirst, etLast, desc;
    TextView selectedDateTV;
    SeekBar sBar;
    TextView tView,mm,gp,text3,park1,pr;
    RadioGroup radioGroup;
    String level, date, str2,str3;
    CheckBox ch, ch1, ch2, ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11;
    String msg=" ",msg2=" ",park=" ";
    private Switch switchView;
    public String j1,j2;
    DBHelper myDB;
    private ArrayList<String> lngList;

    // Intent intent = getIntent();
    //String j1 =a.MapsActivity;
    //final String j2 = intent.getStringExtra("f2");


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhikedata);


        btnInsert = findViewById(R.id.btnInsert);



        pickDateBtn = findViewById(R.id.idBtnPickDate);
        selectedDateTV = findViewById(R.id.idTVSelectedDate);
        //gp = findViewById(R.id.gp);
      //  text3 = findViewById(R.id.text3);
       // park1 = findViewById(R.id.park);

        sBar = (SeekBar) findViewById(R.id.seekBar1);
        pr = (TextView) findViewById(R.id.pr);
        pr.setText("0");
        //  submit = (Button)findViewById(R.id.submit);
        radioGroup = (RadioGroup)findViewById(R.id.groupradio);
        // on below line we are adding click listener for our pick date button

        ch=(CheckBox)findViewById(R.id.checkBox1);
        ch1=(CheckBox)findViewById(R.id.checkBox2);
        ch2=(CheckBox)findViewById(R.id.checkBox3);
        ch3=(CheckBox)findViewById(R.id.checkBox4);
        ch4=(CheckBox)findViewById(R.id.checkBox5);

        ch5=(CheckBox)findViewById(R.id.checkBo1);
        ch6=(CheckBox)findViewById(R.id.checkBo2);
        ch7=(CheckBox)findViewById(R.id.checkBo3);
        ch8=(CheckBox)findViewById(R.id.checkBo4);
        ch9=(CheckBox)findViewById(R.id.checkBo5);
        ch10=(CheckBox)findViewById(R.id.checkBo6);

        switchView = findViewById(R.id.idSwitch);

        desc = findViewById(R.id.EditTex);

        mm = findViewById(R.id.gp);
        myDB = new DBHelper(this);

        Intent in = getIntent();
          j1 =in.getStringExtra("f1");
          j2 = in.getStringExtra("f2");



        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        addhikedata.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view
                                selectedDateTV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        //--------------------------------start seekbar----------------------------------------
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pr.setText(pval+"km");
            }
        });


        //----------------------------end of seek bar --------------------------------------
        //-------------------------------------------start of radio button
        // Add the Listener to the RadioGroup
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup
                        .OnCheckedChangeListener() {
                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId)
                    {

                        // Get the selected Radio Button
                        RadioButton
                                radioButton
                                = (RadioButton)group
                                .findViewById(checkedId);
                        level = radioButton.getText().toString();
                    }

                });
        //-------------------------------------------------radio end-------------------------
        //------------------------------------------------chk box1 start------------------

        ///----------------------------------------------chk box 1 end------------------------------------

        btnInsert.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                date = selectedDateTV.getText().toString();
                str2 = pr.getText().toString();
                str3 = desc.getText().toString();
                lngList = new ArrayList<>();



                if(ch.isChecked())
                    msg = msg + " Child friendly ";
                if(ch1.isChecked())
                    msg = msg + " Pet friendly ";
                if(ch2.isChecked())
                    msg = msg + " wheelchair friendly ";
                if(ch3.isChecked())
                    msg = msg + " paved ";
                if(ch4.isChecked())
                    msg = msg + " pram friendly ";

                if(ch5.isChecked())
                    msg2 = msg2 + " Waterfall ";
                if(ch6.isChecked())
                    msg2 = msg2 + " Forest ";
                if(ch7.isChecked())
                    msg2 = msg2 + " River ";
                if(ch8.isChecked())
                    msg2 = msg2 + " Wild life ";
                if(ch9.isChecked())
                    msg2 = msg2 + " Cave ";
                if(ch10.isChecked())
                    msg2 = msg2 + " Beach ";

                if (switchView.isChecked()) {
                    // on below line we are setting text
                    // if switch is checked.
                    park="Available";
                } else {
                    // on below line we are setting the text
                    // if switch is un checked
                    park="Not Available";
                }
                myDB.insertHikeData(j1,j2,date,str2,level,msg,msg2,park,str3);
                if (date.equals("Date")) {
                    Toast.makeText(addhikedata.this, "Fill ALL REQUIRED FIELDS", Toast.LENGTH_SHORT).show();
                    selectedDateTV.setTextColor(Color.RED);
                    //gp.setTextColor(Color.RED);
                    //text3.setTextColor(Color.RED);
                   // park1.setTextColor(Color.RED);

                } else {
                    movenext();
                    //lngList.add(j1);

                }


            }


        });


    }
    public void movenext(){
        Intent intent =new Intent(this , hikepreview.class);
        intent.putExtra("g1", j1);
        intent.putExtra("g2", j2);
        intent.putExtra("m1", date);
        intent.putExtra("m2", str2);
        intent.putExtra("m3", level);
        intent.putExtra("m4", msg);
        intent.putExtra("m5", msg2);
        intent.putExtra("m6", park);
        intent.putExtra("m7", str3);
        startActivity(intent);

    }



}
