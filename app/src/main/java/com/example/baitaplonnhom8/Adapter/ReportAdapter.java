package com.example.baitaplonnhom8.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonnhom8.R;
import com.example.baitaplonnhom8.database.Models.Task;

import java.time.Instant;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder>{
    private List<Task> taskList;

    // Constructor to initialize with a list of tasks
    public ReportAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        return new ReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // ViewHolder class
    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTaskName;
        private TextView tvDuration;
        private ImageView ivTaskImage;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tv_task_name);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            ivTaskImage = itemView.findViewById(R.id.iv_task_image);
        }

        public void bind(Task task) {
            tvTaskName.setText(task.getTENBT());
            tvDuration.setText("Duration: " + task.getTHOIGIANTHUCTE() + " mins");


        }
    }
}
