package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DichvuAdapter extends RecyclerView.Adapter<DichvuAdapter.DichvuViewHolder> {

    private List<Dichvu> listDichvu;

    public DichvuAdapter(List<Dichvu> listDichvu) {
        this.listDichvu = listDichvu;
    }

    @NonNull
    @Override
    public DichvuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dichvu, parent, false);
        return new DichvuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DichvuViewHolder holder, int position) {
        Dichvu dichvu = listDichvu.get(position);

        holder.tvMaPhieu.setText(dichvu.getMaPhieu());
        holder.tvTenSanPham.setText(dichvu.getTenSanPham());
        holder.tvYeuCau.setText(dichvu.getYeuCau());
        holder.tvThanhTien.setText(String.valueOf(dichvu.getThanhTien()));
        holder.tvNgayTao.setText(dichvu.getNgayTao());
        holder.tvTinhTrang.setText(dichvu.getTinhTrang());
        holder.tvGhiChu.setText(dichvu.getGhiChu());

        // Đổi màu theo tình trạng
        String tinhTrang = dichvu.getTinhTrang();
        if (tinhTrang != null) {
            switch (tinhTrang.toLowerCase()) {
                case "chờ xử lý":
                    holder.statusColor.setBackgroundColor(Color.RED);
                    break;
                case "đang xử lý":
                    holder.statusColor.setBackgroundColor(Color.YELLOW);
                    break;
                case "hoàn thành":
                    holder.statusColor.setBackgroundColor(Color.GREEN);
                    break;
                default:
                    holder.statusColor.setBackgroundColor(Color.GRAY);
                    break;
            }
        } else {
            holder.statusColor.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return listDichvu != null ? listDichvu.size() : 0;
    }

    public static class DichvuViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPhieu, tvTenSanPham, tvYeuCau, tvThanhTien, tvNgayTao, tvTinhTrang, tvGhiChu;
        View statusColor;

        public DichvuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvYeuCau = itemView.findViewById(R.id.tvYeuCau);
            tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
            tvNgayTao = itemView.findViewById(R.id.tvNgayTao);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
            statusColor = itemView.findViewById(R.id.status_color); // View đổi màu
        }
    }
}
