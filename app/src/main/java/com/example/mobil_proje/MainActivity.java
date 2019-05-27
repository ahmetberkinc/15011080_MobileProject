package com.example.mobil_proje;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnNoteListener{
    private ArrayList<ExampleNote> mExampleNotes;
    private RecyclerView mrecyclerView;
    private ExampleAdapter madapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView not_Ekle;
    private Button save_on_phone;
    private int color;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.features,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.delete_notes){
            mExampleNotes.clear();
            buildRecyclerView();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        //buildRecyclerView();
        not_Ekle=findViewById(R.id.not_Ekle);
        save_on_phone=findViewById(R.id.save_on_phone);
        save_on_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        not_Ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CreateNote=new Intent(getApplicationContext(),CreateNoteScreen.class);
                startActivityForResult(CreateNote,1);
            }
        });
        
    }
    private void saveData(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(mExampleNotes);
        editor.putString("task list",json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("task list",null);
        Type type=new TypeToken<ArrayList<ExampleNote>>() {}.getType();
        mExampleNotes=gson.fromJson(json,type);

        if(mExampleNotes==null){
            mExampleNotes=new ArrayList<>();
        }
        buildRecyclerView();

    }

    public void buildRecyclerView(){
        mrecyclerView=findViewById(R.id.recyclerView);
        //mrecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(getApplicationContext());
        madapter=new ExampleAdapter(mExampleNotes,this);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(madapter);
        RecyclerView.ItemDecoration divider =new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        mrecyclerView.addItemDecoration(divider);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //YENI NOT EKLEME
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                mExampleNotes.add(new ExampleNote(data.getExtras().getString("TITLE"),data.getExtras().getString("CONTEXT"),
                        data.getExtras().getString("DAY"),
                        data.getExtras().getString("MONTH"),data.getExtras().getString("YEAR"),
                        data.getExtras().getString("PRIORITY"),data.getIntExtra("COLOR",0)));
                buildRecyclerView();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"Geri Tusuna Bastin",Toast.LENGTH_LONG).show();
            }
        }else if(requestCode==2){
            //EDİT NOTE KISMI
            if(resultCode==RESULT_OK){
                ExampleNote e=new ExampleNote(data.getExtras().getString("TITLE"),data.getExtras().getString("CONTEXT"),
                        data.getExtras().getString("DAY"),
                        data.getExtras().getString("MONTH"),data.getExtras().getString("YEAR"),
                        data.getExtras().getString("PRIORITY"),data.getIntExtra("COLOR",0));
                Log.d("Editten çektiğim", ": " + e.getColor());
                mExampleNotes.set(data.getIntExtra("POSITION",0),e);
                buildRecyclerView();
            }else if(resultCode==RESULT_FIRST_USER){
                mExampleNotes.remove(data.getIntExtra("POSITION",0));
                buildRecyclerView();
            }
           else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),"Geri Tusuna Bastin",Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    public void onNoteClick(int position) {
        Intent intent=new Intent(getApplicationContext(),EditNoteScreen.class);
        ExampleNote clickednote=mExampleNotes.get(position);
        intent.putExtra("TITLE",mExampleNotes.get(position).getTitle());
        intent.putExtra("DAY",mExampleNotes.get(position).getGun());
        intent.putExtra("MONTH",mExampleNotes.get(position).getAy());
        intent.putExtra("YEAR",mExampleNotes.get(position).getYil());
        intent.putExtra("CONTEXT",mExampleNotes.get(position).getNcontext());
        intent.putExtra("PRIORITY",mExampleNotes.get(position).getPriority());
        intent.putExtra("POSITION",position);
        intent.putExtra("COLOR",mExampleNotes.get(position).getColor());
        startActivityForResult(intent,2);
    }
}