package com.example.Gestion_Scolarite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterBulletins extends RecyclerView.Adapter<CustomAdapterBulletins.MyViewHolder> {

    private Context context;
    private ArrayList<String> student_cne, module_name, module_mark;

    CustomAdapterBulletins(Context context, ArrayList student_cne, ArrayList module_name, ArrayList module_mark){
        this.context = context;
        this.student_cne = student_cne;
        this.module_name = module_name;
        this.module_mark = module_mark;

    }

    @NonNull
    @Override
    public CustomAdapterBulletins.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bulletins_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterBulletins.MyViewHolder holder, int position) {
        holder.student_cne_txt2.setText(String.valueOf(student_cne.get(position)));
        holder.module_name_txt.setText(String.valueOf(module_name.get(position)));
        holder.module_mark_txt.setText(String.valueOf(module_mark.get(position)));

    }

    @Override
    public int getItemCount() {
        return student_cne.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView student_cne_txt2, module_name_txt, module_mark_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_cne_txt2 = itemView.findViewById(R.id.student_cne_txt2);
            module_name_txt = itemView.findViewById(R.id.module_name_txt);
            module_mark_txt = itemView.findViewById(R.id.module_mark_txt);
        }
    }
}
