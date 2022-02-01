package com.example.Gestion_Scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class GestionEtudiants extends AppCompatActivity {

    Spinner spinner_filiere;
    ArrayList<String> FilieresList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Button btn_add_student;
    Button show_students;
    TextInputEditText cne;
    TextInputEditText student_name;
    TextInputEditText student_name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_etudiants);

        MyDatabaseHelper mydb = new MyDatabaseHelper(GestionEtudiants.this);
        FilieresList = mydb.readFilieres();

        spinner_filiere = findViewById(R.id.spinner_filiere2);

        adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, FilieresList);
        spinner_filiere.setAdapter(adapter);

        cne = findViewById(R.id.Student_CNE_N);
        student_name = findViewById(R.id.Student_NAME);
        student_name2 = findViewById(R.id.Student_PRENOM);
        show_students = findViewById(R.id.show_all_student);

        show_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllstudents();
            }
        });

        btn_add_student = findViewById(R.id.add_student);
        btn_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = cne.getText().toString().trim();
                String b = student_name.getText().toString().trim();
                String c = student_name2.getText().toString().trim();
                String d = spinner_filiere.getSelectedItem().toString();

                MyDatabaseHelper mydb = new MyDatabaseHelper(GestionEtudiants.this);
                mydb.addStudent(a,b,c,d);

                cne.setText(null);
                student_name.setText(null);
                student_name2.setText(null);
            }
        });




    }

    public void showAllstudents(){
        Intent intent = new Intent(this, all_students.class);
        startActivity(intent);
    }
}