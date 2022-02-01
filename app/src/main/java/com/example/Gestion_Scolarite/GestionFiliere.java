package com.example.Gestion_Scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class GestionFiliere extends AppCompatActivity {
    Button btn_add;
    ArrayList<String> filieresList = new ArrayList<>();
    TextInputEditText filiere;
    TextInputEditText module;

    public boolean verifyExistance(ArrayList<String> list, String txt) {
        for (String s : list) {
            if (s.contains(txt))
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_filiere);

        filiere = findViewById(R.id.Nom_filiere);
        module = findViewById(R.id.Nom_module);

        btn_add = findViewById(R.id.btn_add_filiere);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var1 = module.getText().toString().trim();
                String var2 = filiere.getText().toString().trim();

                MyDatabaseHelper mydb = new MyDatabaseHelper(GestionFiliere.this);
                filieresList = mydb.readFilieres();
                if (var1 == null || var1.equals("") || var1.length() == 0 || var2.equals("") || var2.length() == 0){
                    Toast.makeText(getBaseContext(),"Il faut compléter les champs", Toast.LENGTH_LONG).show();
                }else if (verifyExistance(filieresList,var2)){
                    mydb.addModule(var1);
                    Toast.makeText(getBaseContext(),"Le module est ajouté ", Toast.LENGTH_LONG).show();
                }else{
                    mydb.addModule(var1);
                    mydb.addFiliere(var2);
                }

                module.setText(null);
                filiere.setText(null);
            }
        });


    }
}