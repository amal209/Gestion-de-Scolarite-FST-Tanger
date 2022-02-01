package com.example.Gestion_Scolarite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class all_bulletins extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper mydb;
    ArrayList<String> student_cne, module_name, module_mark;
    CustomAdapterBulletins customAdapterBulletins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bulletins);

        recyclerView = findViewById(R.id.recycle_all_bulletins);

        mydb = new MyDatabaseHelper(all_bulletins.this);
        student_cne = new ArrayList<>();
        module_name = new ArrayList<>();
        module_mark = new ArrayList<>();

        storeDataInArrays();

        customAdapterBulletins = new CustomAdapterBulletins(all_bulletins.this, student_cne, module_name, module_mark);
        recyclerView.setAdapter(customAdapterBulletins);
        recyclerView.setLayoutManager(new LinearLayoutManager(all_bulletins.this));


    }

    void storeDataInArrays(){
        Cursor cursor = mydb.readAllBulletins();
        if (cursor.getCount()==0){
            Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                student_cne.add(cursor.getString(1));
                module_name.add(cursor.getString(3));
                module_mark.add(cursor.getString(2));
            }
        }
    }

}