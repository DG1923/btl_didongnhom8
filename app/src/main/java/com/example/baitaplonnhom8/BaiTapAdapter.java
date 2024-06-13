package com.example.baitaplonnhom8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BaiTapAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BaiTap> baiTapList;

    public BaiTapAdapter(Context context, int layout, List<BaiTap> baiTapList) {
        this.context = context;
        this.layout = layout;
        this.baiTapList = baiTapList;
    }

    @Override
    public int getCount() {
        return baiTapList.size();
    }

    @Override
    public BaiTap getItem(int position) {
        return baiTapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.txtTen = convertView.findViewById(R.id.textViewTenBaiTap);
            holder.txtThoiGian = convertView.findViewById(R.id.textViewThoiGianYeuCau);
            holder.imgHinhAnh = convertView.findViewById(R.id.imageViewHinhAnh);
            holder.txtTrangThai = convertView.findViewById(R.id.textViewTrangThai);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BaiTap baiTap = getItem(i);

        holder.txtTen.setText(baiTap.getTenBaiTap());
        holder.txtThoiGian.setText(String.valueOf(baiTap.getThoiGianYeuCau()) + "s");
        holder.txtTrangThai.setText(baiTap.getTrangThai());
        holder.imgHinhAnh.setImageResource(baiTap.getHinhAnh());

        return convertView;
    }

    private static class ViewHolder {
        TextView txtTen;
        TextView txtThoiGian;
        ImageView imgHinhAnh;
        TextView txtTrangThai;
    }
}
