package com.example.baitaplonnhom8;

import static java.lang.System.in;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonnhom8.database.DatabaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;
    private DatabaseHelper databaseHelper;

    public SubjectAdapter(Context context, Cursor cursor, DatabaseHelper databaseHelper) {
        this.context = context;
        this.cursor = cursor;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            int maMH = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_MONHOC_MAMH));
            Cursor monHocCursor = databaseHelper.getMonHocByMaMH(maMH);
            List<Integer> soluong_hoanthanh = new ArrayList<Integer>();
            if (monHocCursor.moveToFirst()) {
                String tenMonHoc = monHocCursor.getString(monHocCursor.getColumnIndexOrThrow(DatabaseHelper.DB_MONHOC_TENMH));
                String anhMonHoc = monHocCursor.getString(monHocCursor.getColumnIndexOrThrow(DatabaseHelper.DB_MONHOC_ANHMH));

                holder.txtSubjectName.setText(tenMonHoc);
                int taskCount = getTaskCountForMonHoc(maMH);
                float progress = getProgressForMonHoc(maMH);
                while (monHocCursor.moveToNext()){
                    String trangthai = monHocCursor.getString(monHocCursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_TRANGTHAI));
                    if(trangthai == "Incomplete"){
                        soluong_hoanthanh.add(1);
                    }
                }

                holder.txtTaskCount.setText("Số task: " + cursor.getCount());
                holder.txtProgress.setText("Tiến độ: " + soluong_hoanthanh.size()/cursor.getCount() + "%");

                // Load image from assets
                try {
                    InputStream inputStream = context.getAssets().open(anhMonHoc);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    holder.imgSubject.setImageBitmap(bitmap);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            holder.itemView.setOnClickListener(v-> {
                Intent intent = new Intent(context, DuongMain.class);
                intent.putExtra("maMH",maMH);
                context.startActivity(intent);
            });
            monHocCursor.close();
        }
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSubject;
        TextView txtSubjectName;
        TextView txtTaskCount;
        TextView txtProgress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSubject = itemView.findViewById(R.id.imgsub);
            txtSubjectName = itemView.findViewById(R.id.txtNameSub);
            txtTaskCount = itemView.findViewById(R.id.txtNumTask);
            txtProgress = itemView.findViewById(R.id.txtTienDo);
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

    private int getTaskCountForMonHoc(int maMH) {
        Cursor cursor = databaseHelper.getBaiTapByMaMH(maMH);
        int taskCount = cursor.getCount();
        cursor.close();
        return taskCount;
    }

    private float getProgressForMonHoc(int maMH) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM BAITAP WHERE MAMH = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(maMH)});
        cursor.moveToFirst();
        int totalTasks = cursor.getInt(0);
        cursor.close();

        query = "SELECT COUNT(*) FROM BAITAP WHERE MAMH = ? AND TRANGTHAI = 'Complete'";
        cursor = db.rawQuery(query, new String[]{String.valueOf(maMH)});
        cursor.moveToFirst();
        int completedTasks = cursor.getInt(0);
        cursor.close();

        if (totalTasks == 0) {
            return 0;
        }
        return (float) completedTasks / totalTasks * 100;
    }
}
