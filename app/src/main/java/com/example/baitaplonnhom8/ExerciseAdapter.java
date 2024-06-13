package com.example.baitaplonnhom8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private List<Exercise> exerciseList;
    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.img.setImageResource(exercise.getImageResource());
        holder.txtNameEx.setText(exercise.getName());
        holder.txtTime.setText(exercise.getTime());
        holder.txtNhom.setText(exercise.getCategory());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtNameEx;
        TextView txtTime;
        TextView txtNhom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtNameEx = itemView.findViewById(R.id.txt_name_ex);
            txtTime = itemView.findViewById(R.id.txt_time);
            txtNhom = itemView.findViewById(R.id.txt_nhom);
        }
    }
}
