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

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private Context context;

    public ExerciseAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
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

    private Cursor cursor;


    @NonNull
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        String nameEx =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_TENBT));
        String anhminhhoa = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_ANHMINHHOA));
        int time = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_THOIGIANYC));
        int nhom = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_MAMH));
        holder.txtNameEx.setText(nameEx);
        holder.txtTime.setText(time + " mins");
        String nhombt = "";
        if(nhom == 6){
            nhombt = "Bài tập giảm mỡ";
        }else if(nhom == 7 ){
            nhombt = "Bài tập tăng cơ";
        }else if(nhom == 8){
            nhombt = "Bài tập duy trì thể trạng";
        }
        holder.txtNhom.setText(nhombt);

        //load anh tu duong dan
        try {
            InputStream inputStream = context.getAssets().open(anhminhhoa);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.img.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
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
    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }
}
