package com.example.baitaplonnhom8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class vecticalExerciseAdapter extends RecyclerView.Adapter<vecticalExerciseAdapter.ViewHolder> {
    private List<Exercise> exercises;
    public vecticalExerciseAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public vecticalExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vecticalExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.txt_name.setText(exercise.getName());
        holder.txt_group.setText(exercise.getCategory());
        holder.txt_time.setText(exercise.getTime());
        holder.img_exercise.setImageResource(exercise.getImageResource());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        TextView txt_group;
        TextView txt_time;
        ImageView img_exercise;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name_ex);
            txt_group = itemView.findViewById(R.id.txt_nhom);
            txt_time = itemView.findViewById(R.id.txt_time);
            img_exercise = itemView.findViewById(R.id.img);
        }
    }
}
