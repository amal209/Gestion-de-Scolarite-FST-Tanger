package com.example.Gestion_Scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn_gest_notes;
    private Button btn_gest_filiere;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn1 = findViewById(R.id.gestion_etudiant);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGestionEtudiant();
            }
        });

        btn_gest_notes = findViewById(R.id.gestion_notes);
        btn_gest_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGestionNotes();
            }
        });

        btn_gest_filiere = findViewById(R.id.gestion_modules);
        btn_gest_filiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGestionModules();
            }
        });

    }

    public void openGestionEtudiant(){
        Intent intent = new Intent(this, GestionEtudiants.class);
        startActivity(intent);
    }

    public void openGestionNotes(){
        Intent intent2 = new Intent(this,GestionNotes.class);
        startActivity(intent2);
    }

    public void openGestionModules(){
        Intent intent3 = new Intent(this, GestionFiliere.class);
        startActivity(intent3);
    }
}