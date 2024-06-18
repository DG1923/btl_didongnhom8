package com.example.baitaplonnhom8;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonnhom8.database.DatabaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaiTapAdapter extends RecyclerView.Adapter<BaiTapAdapter.ViewHolder> {
    public BaiTapAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public BaiTapAdapter(Context context) {
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
    public BaiTapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_bai_tap,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_MABT));

        String nameEx =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_TENBT));
        String anhminhhoa = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_ANHMINHHOA));
        int time = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_THOIGIANYC));
        String trangThai = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DB_BAITAP_TRANGTHAI));


        holder.txtTenBaiTap.setText(nameEx);
        holder.txt_TrangThai.setText(trangThai);
        holder.txtThoiGianYeuCau.setText(time+" mins");

        try {
            InputStream inputStream = context.getAssets().open(anhminhhoa);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.imageViewHinhAnh.setImageBitmap(bitmap);
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
        TextView txt_TrangThai;
        TextView txtThoiGianYeuCau;
        TextView txtTenBaiTap;
        ImageView imageViewHinhAnh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiTap = itemView.findViewById(R.id.textViewTenBaiTap);
            txt_TrangThai = itemView.findViewById(R.id.textViewTrangThai);
            txtThoiGianYeuCau = itemView.findViewById(R.id.textViewThoiGianYeuCau);
            imageViewHinhAnh = itemView.findViewById(R.id.imageViewHinhAnh);
        }
    }
}
