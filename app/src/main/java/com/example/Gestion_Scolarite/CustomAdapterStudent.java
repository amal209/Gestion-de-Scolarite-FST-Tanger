package com.example.Gestion_Scolarite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterStudent extends RecyclerView.Adapter<CustomAdapterStudent.MyViewHolder> {

    private Context context;
    private ArrayList<String> student_cne, student_name1, student_name2, student_filiere;


    CustomAdapterStudent(Context context, ArrayList student_cne, ArrayList student_name1, ArrayList student_name2, ArrayList student_filiere){
        this.context =  context;
        this.student_cne = student_cne;
        this.student_name1 = student_name1;
        this.student_name2 = student_name2;
        this.student_filiere = student_filiere;

    }

    @NonNull
    @Override
    public CustomAdapterStudent.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.students_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterStudent.MyViewHolder holder, int position) {
        holder.student_cne_txt.setText(String.valueOf(student_cne.get(position)));
        holder.student_name1_txt.setText(String.valueOf(student_name1.get(position)));
        holder.student_name2_txt.setText(String.valueOf(student_name2.get(position)));
        holder.student_filiere_txt.setText(String.valueOf(student_filiere.get(position)));
    }

    @Override
    public int getItemCount() {
        return student_cne.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView student_cne_txt, student_name1_txt, student_name2_txt, student_filiere_txt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_cne_txt = itemView.findViewById(R.id.student_cne_txt);
            student_name1_txt = itemView.findViewById(R.id.student_name1_txt);
            student_name2_txt = itemView.findViewById(R.id.student_name2_txt);
            student_filiere_txt = itemView.findViewById(R.id.student_filiere_txt);
        }
    }
}
