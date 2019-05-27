package com.example.mobil_proje;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;

public class CreateNoteScreen extends AppCompatActivity {
    Spinner day;
    Spinner month;
    Spinner year;
    Spinner minute;
    Spinner hour;
    Context context=this;
    EditText title;
    EditText ncontext;
    EditText priority;
    Button save_note;
    Button set_color;
    public int default_color;
    ConstraintLayout mlyaout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_screen);
        day=(Spinner)findViewById(R.id.day);
        month=(Spinner)findViewById(R.id.month);
        year=(Spinner)findViewById(R.id.year);
        minute=(Spinner)findViewById(R.id.minute);
        hour=(Spinner)findViewById(R.id.hour);
        title=(EditText)findViewById(R.id.title);
        ncontext=(EditText)findViewById(R.id.ncontext);
        mlyaout=(ConstraintLayout)findViewById(R.id.layout);
        priority=(EditText)findViewById(R.id.priority);
        save_note=(Button)findViewById(R.id.save_note);
        set_color=(Button)findViewById(R.id.set_color);
        default_color= ContextCompat.getColor(this,R.color.cardview_light_background);
        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis=new Intent();
                Toast.makeText(context, "Note Created", Toast.LENGTH_SHORT).show();
                gecis.putExtra("TITLE",title.getText().toString());
                gecis.putExtra("DAY",day.getSelectedItem().toString());
                gecis.putExtra("MONTH",month.getSelectedItem().toString());
                gecis.putExtra("YEAR",year.getSelectedItem().toString());
                gecis.putExtra("PRIORITY",priority.getText().toString());
                gecis.putExtra("CONTEXT",ncontext.getText().toString());
                gecis.putExtra("COLOR",default_color);
                setResult(RESULT_OK,gecis);
                finish();
            }
        });
        set_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

    }

    public  void openColorPicker(){
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(this, default_color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                if(default_color!=color) {
                    default_color = color;
                    mlyaout.setBackgroundColor(default_color);
                    Log.d("mytag", "onNoteClick: " + default_color);
                }
            }
        });
        colorPicker.show();
    }




}
