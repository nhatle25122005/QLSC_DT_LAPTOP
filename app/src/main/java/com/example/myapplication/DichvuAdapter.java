package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.database.DichvuDatabase;

import java.util.List;
import java.util.concurrent.Executors;

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
        holder.btnDelete.setOnClickListener(v -> {
            Context context = v.getContext();
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Dichvu item = listDichvu.get(pos);

                // Xác nhận xóa
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc muốn xóa dịch vụ này?")
                        .setPositiveButton("Xóa", (dialog, which) -> {
                            // Xóa trong database (phải chạy thread riêng)
                            Executors.newSingleThreadExecutor().execute(() -> {
                                DichvuDatabase.getInstance(context)
                                        .dichvuDAO()
                                        .deleteDichvu(item);
                            });

                            // Xóa trong danh sách và cập nhật UI
                            listDichvu.remove(pos);
                            notifyItemRemoved(pos);
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });

        holder.btnEdit.setOnClickListener(v -> {
            Context context = v.getContext();
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Dichvu item = listDichvu.get(pos);

                // Inflate layout chứa form edit
                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_dichvu, null);

                EditText edtMaPhieu = dialogView.findViewById(R.id.edtMaPhieu);
                EditText edtTenSP = dialogView.findViewById(R.id.edtTenSanPham);
                EditText edtYeuCau = dialogView.findViewById(R.id.edtYeuCau);
                EditText edtThanhTien = dialogView.findViewById(R.id.edtThanhTien);
                EditText edtNgayTao = dialogView.findViewById(R.id.edtNgayTao);
                EditText edtTinhTrang = dialogView.findViewById(R.id.edtTinhTrang);
                EditText edtGhiChu = dialogView.findViewById(R.id.edtGhiChu);

                // Gán dữ liệu cũ
                edtMaPhieu.setText(item.getMaPhieu());
                edtTenSP.setText(item.getTenSanPham());
                edtYeuCau.setText(item.getYeuCau());
                edtThanhTien.setText(String.valueOf(item.getThanhTien()));
                edtNgayTao.setText(item.getNgayTao());
                edtTinhTrang.setText(item.getTinhTrang());
                edtGhiChu.setText(item.getGhiChu());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chỉnh sửa dịch vụ");
                builder.setView(dialogView);

                builder.setPositiveButton("Lưu", (dialog, which) -> {
                    item.setMaPhieu(edtMaPhieu.getText().toString());
                    item.setTenSanPham(edtTenSP.getText().toString());
                    item.setYeuCau(edtYeuCau.getText().toString());

                    try {
                        item.setThanhTien((int) Double.parseDouble(edtThanhTien.getText().toString()));
                    } catch (NumberFormatException e) {
                        item.setThanhTien(0);
                    }

                    item.setNgayTao(edtNgayTao.getText().toString());
                    item.setTinhTrang(edtTinhTrang.getText().toString());
                    item.setGhiChu(edtGhiChu.getText().toString());

                    // Cập nhật vào Room nếu có hàm update
                    Executors.newSingleThreadExecutor().execute(() -> {
                        DichvuDatabase.getInstance(context)
                                .dichvuDAO()
                                .updateDichvu(item);  // cần có hàm này trong DAO
                    });

                    notifyItemChanged(pos);
                });

                builder.setNegativeButton("Hủy", null);
                builder.show();
            }
        });


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
        ImageView btnDelete;
        ImageView btnEdit;

        public DichvuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvYeuCau = itemView.findViewById(R.id.tvYeuCau);
            tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
            tvNgayTao = itemView.findViewById(R.id.tvNgayTao);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            statusColor = itemView.findViewById(R.id.status_color); // View đổi màu
        }
    }
}
