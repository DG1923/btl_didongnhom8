package com.example.baitaplonnhom8;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonnhom8.database.DatabaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class vecticalExerciseAdapter extends RecyclerView.Adapter<vecticalExerciseAdapter.ViewHolder> {
    public vecticalExerciseAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public vecticalExerciseAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    private Context context;
    private Cursor cursor;
    @NonNull
    @Override
    public vecticalExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vecticalExerciseAdapter.ViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        String nameEx =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_TENBT));
        String anhminhhoa = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_ANHMINHHOA));
        int time = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_THOIGIANYC));
        int nhom = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_MAMH));
        String nhombt = "";
        if(nhom == 6){
            nhombt = "Bài tập giảm mỡ";
        }else if(nhom == 7 ){
            nhombt = "Bài tập tăng cơ";
        }else if(nhom == 8){
            nhombt = "Bài tập duy trì thể trạng";
        }else if(nhom == 9){
            nhombt = "Fast Warmup";
        }

        holder.txt_name.setText(nameEx);
        holder.txt_group.setText(nhombt);
        holder.txt_time.setText(time + " mins");
        try {
            InputStream inputStream = context.getAssets().open(anhminhhoa);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.img_exercise.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
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
