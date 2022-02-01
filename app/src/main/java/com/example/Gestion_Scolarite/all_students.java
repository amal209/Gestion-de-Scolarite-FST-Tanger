package com.example.Gestion_Scolarite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class all_students extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper mydb;
    ArrayList<String> student_cne, student_name1, student_name2, student_filiere;
    CustomAdapterStudent customAdapterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        recyclerView = findViewById(R.id.recycle_all_student);

        mydb = new MyDatabaseHelper(all_students.this);
        student_cne = new ArrayList<>();
        student_name1 = new ArrayList<>();
        student_name2 = new ArrayList<>();
        student_filiere = new ArrayList<>();

        storeDataInArrays();

        customAdapterStudent = new CustomAdapterStudent(all_students.this,student_cne,student_name1,student_name2,student_filiere);
        recyclerView.setAdapter(customAdapterStudent);
        recyclerView.setLayoutManager(new LinearLayoutManager(all_students.this));

    }

    void storeDataInArrays(){
        Cursor cursor = mydb.readAllStudent();
        if (cursor.getCount()==0){
            Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                student_cne.add(cursor.getString(0));
                student_name1.add(cursor.getString(1));
                student_name2.add(cursor.getString(2));
                student_filiere.add(cursor.getString(3));
            }
        }
    }

}