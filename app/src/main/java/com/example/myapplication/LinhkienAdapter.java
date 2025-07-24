package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter cho RecyclerView hiển thị danh sách linh kiện.
 */
public class LinhkienAdapter extends RecyclerView.Adapter<LinhkienAdapter.LinhkienViewHolder> {
    private List<Linhkien> linhkienList;

    // Constructor nhận vào danh sách linh kiện
    public LinhkienAdapter(List<Linhkien> linhkienList) {
        this.linhkienList = linhkienList;
    }

    @NonNull
    @Override
    public LinhkienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linhkien, parent, false);
        return new LinhkienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinhkienViewHolder holder, int position) {
        Linhkien linhkien = linhkienList.get(position);
        holder.tvTen.setText(linhkien.getTen());
        holder.tvGia.setText("Giá: " + linhkien.getGia() + "đ");
        holder.tvSoluong.setText("Số lượng: " + linhkien.getSoluong());
        holder.tvMota.setText(linhkien.getMota());
        // Bắt sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(linhkien);
            }
        });
    }

    @Override
    public int getItemCount() {
        return linhkienList != null ? linhkienList.size() : 0;
    }

    // ViewHolder cho item linh kiện
    public static class LinhkienViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvGia, tvSoluong, tvMota;
        public LinhkienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTenLinhkien);
            tvGia = itemView.findViewById(R.id.tvGiaLinhkien);
            tvSoluong = itemView.findViewById(R.id.tvSoluongLinhkien);
            tvMota = itemView.findViewById(R.id.tvMotaLinhkien);
        }
    }

    /**
     * Interface lắng nghe sự kiện click vào item linh kiện
     */
    public interface OnItemClickListener {
        void onItemClick(Linhkien linhkien);
    }
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Hàm cập nhật danh sách linh kiện
    public void setLinhkienList(List<Linhkien> linhkienList) {
        this.linhkienList = linhkienList;
        notifyDataSetChanged();
    }
}