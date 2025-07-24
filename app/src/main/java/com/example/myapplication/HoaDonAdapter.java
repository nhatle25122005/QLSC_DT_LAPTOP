package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.HoaDon;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder> {

    private List<HoaDon> list;

    public HoaDonAdapter(List<HoaDon> list) {
        this.list = list;
    }

    public void setList(List<HoaDon> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hoadon, parent, false);
        return new HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDon hd = list.get(position);
        holder.tvNgayLap.setText("Ngày lập: " + hd.ngayLap);
        holder.tvTongTien.setText("Tổng tiền: " + hd.tongTien);
        holder.tvMaKhach.setText("Mã khách: " + hd.maKhach);
        holder.tvMaNhanVien.setText("Mã nhân viên: " + hd.maNhanVien);
        holder.tvGhiChu.setText("Ghi chú: " + hd.ghiChu);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgayLap, tvTongTien, tvMaKhach, tvMaNhanVien, tvGhiChu;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgayLap = itemView.findViewById(R.id.tvNgayLap);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            tvMaKhach = itemView.findViewById(R.id.tvMaKhach);
            tvMaNhanVien = itemView.findViewById(R.id.tvMaNhanVien);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
        }
    }
}
