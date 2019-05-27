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

public class EditNoteScreen extends AppCompatActivity {
    Spinner day;
    Spinner month;
    Spinner year;
    Spinner minute;
    Spinner hour;
    Context context=this;
    EditText title;
    int yer;
    EditText ncontext;
    EditText priority;
    Button save_note;
    Button delete_note;
    Button change_color;
    public int default_color;
    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note_screen);
        layout=findViewById(R.id.layout);
        change_color=(Button)findViewById(R.id.change_color);
        String str = getIntent().getExtras().getString("TITLE");
        String str2 = getIntent().getExtras().getString("PRIORITY");
        String str3 = getIntent().getExtras().getString("CONTEXT");
        yer = getIntent().getIntExtra("POSITION",0);
        Log.d("Editten cektigim posi", ": " + yer);
        default_color= getIntent().getIntExtra("COLOR",0);
        //default_color= ContextCompat.getColor(this,R.color.colorPrimary);
        day=(Spinner)findViewById(R.id.day);
        month=(Spinner)findViewById(R.id.month);
        year=(Spinner)findViewById(R.id.year);
        minute=(Spinner)findViewById(R.id.minute);
        hour=(Spinner)findViewById(R.id.hour);
        title=(EditText)findViewById(R.id.title);
        ncontext=(EditText)findViewById(R.id.ncontext);
        priority=(EditText)findViewById(R.id.priority);
        save_note=(Button)findViewById(R.id.save_note);
        delete_note=(Button)findViewById(R.id.delete_note);
        title.setText(str);
        priority.setText(str2);
        ncontext.setText(str3);
        layout.setBackgroundColor(default_color);
        change_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor();
            }
        });
        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis=new Intent();
                Toast.makeText(context, "Note Changed", Toast.LENGTH_SHORT).show();
                gecis.putExtra("TITLE",title.getText().toString());
                gecis.putExtra("DAY",day.getSelectedItem().toString());
                gecis.putExtra("MONTH",month.getSelectedItem().toString());
                gecis.putExtra("YEAR",year.getSelectedItem().toString());
                gecis.putExtra("CONTEXT",ncontext.getText().toString());
                gecis.putExtra("PRIORITY",priority.getText().toString());
                gecis.putExtra("POSITION",yer);
                gecis.putExtra("COLOR",default_color);
                Log.d("Editten yollagım", ": " + default_color);
                setResult(RESULT_OK,gecis);
                finish();
            }
        });
        delete_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sil=new Intent();
                Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
                setResult(RESULT_FIRST_USER,sil);
                finish();
            }
        });


    }
    public  void changeColor(){
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(this, default_color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                if(default_color!=color)
                default_color=color;
                layout.setBackgroundColor(default_color);
            }
        });
        colorPicker.show();
    }



}
