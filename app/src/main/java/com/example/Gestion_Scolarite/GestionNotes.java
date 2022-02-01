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

public class GestionNotes extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Spinner spinner_module;
    ArrayList<String> modulesList = new ArrayList<>();
    Button btn_note;
    Button show_bulletins;
    TextInputEditText cne;
    TextInputEditText module_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_notes);

        MyDatabaseHelper mydb = new MyDatabaseHelper(GestionNotes.this);
        modulesList = mydb.readModules();

        spinner_module = findViewById(R.id.spinner_module_N);
        adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,modulesList);
        spinner_module.setAdapter(adapter);

        cne = findViewById(R.id.Student_CNE);
        module_mark = findViewById(R.id.id_note_N);

        show_bulletins = findViewById(R.id.btn_affich_bulltins);
        show_bulletins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllBulletins();
            }
        });

        btn_note = findViewById(R.id.btn_ajout_note);
        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = cne.getText().toString().trim();
                String b = spinner_module.getSelectedItem().toString();
                String c = module_mark.getText().toString().trim();


                MyDatabaseHelper mydb = new MyDatabaseHelper(GestionNotes.this);
                mydb.addNote(a,b,c);

                cne.setText(null);
                module_mark.setText(null);
            }
        });

    }

    public void showAllBulletins(){
        Intent intent = new Intent(this, all_bulletins.class);
        startActivity(intent);
    }

}