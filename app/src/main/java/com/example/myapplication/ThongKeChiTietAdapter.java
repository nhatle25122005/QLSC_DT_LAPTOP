package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.fragment.ThongkechitietFragment;

import java.util.ArrayList;
import java.util.List;

public class ThongKeChiTietAdapter extends RecyclerView.Adapter<ThongKeChiTietAdapter.ViewHolder> {
    private Context context;
    private List<SanPhamThongKe> list;

    public ThongKeChiTietAdapter(Context context, ArrayList<SanPhamThongKe> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thong_ke, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPhamThongKe sp = list.get(position);
        holder.tvTenKhachHang.setText("Khách hàng: " + sp.getTenKhachHang());
        holder.tvNhanVien.setText("Nhân viên: " + sp.getTenNhanVien());
        holder.tvMaSP.setText("Mã SP: " + sp.getMaSP());
        holder.tvTenSP.setText("Tên sản phẩm: " + sp.getTenSP());
        holder.tvGia.setText("Giá: " + sp.getGiaTien() + " đ");
        holder.tvTinhTrang.setText("Tình trạng: " + sp.getTinhTrang());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhachHang,tvNhanVien,tvMaSP, tvTenSP, tvGia, tvTinhTrang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSP = itemView.findViewById(R.id.tvMaSP); // <-- phải là TextView
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGia = itemView.findViewById(R.id.tvGia);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            tvTenKhachHang = itemView.findViewById(R.id.textViewTenKhachHang);
            tvNhanVien = itemView.findViewById(R.id.textViewNhanVien);
        }
    }

}
