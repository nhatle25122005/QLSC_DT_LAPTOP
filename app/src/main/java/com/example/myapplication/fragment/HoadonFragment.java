package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.database.HoaDonDatabase;
import com.example.myapplication.HoaDon;

public class HoadonFragment extends Fragment {

    EditText edtNgayLap, edtTongTien, edtMaKhach, edtMaNhanVien, edtGhiChu;
    Button btnThem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hoadon, container, false);

        edtNgayLap = view.findViewById(R.id.edtNgayLap);
        edtTongTien = view.findViewById(R.id.edtTongTien);
        edtMaKhach = view.findViewById(R.id.edtMaKhach);
        edtMaNhanVien = view.findViewById(R.id.edtMaNhanVien);
        edtGhiChu = view.findViewById(R.id.edtGhiChu);
        btnThem = view.findViewById(R.id.btnThemHoaDon);


        btnThem.setOnClickListener(v -> {
            String ngayLap = edtNgayLap.getText().toString().trim();
            double tongTien = Double.parseDouble(edtTongTien.getText().toString().trim());
            int maKhach = Integer.parseInt(edtMaKhach.getText().toString().trim());
            int maNhanVien = Integer.parseInt(edtMaNhanVien.getText().toString().trim());
            String ghiChu = edtGhiChu.getText().toString().trim();

            HoaDon hoaDon = new HoaDon(ngayLap, tongTien, maKhach, maNhanVien, ghiChu);
            HoaDonDatabase.getInstance(getContext()).hoaDonDAO().insert(hoaDon);

            Toast.makeText(getContext(), "Đã thêm hóa đơn!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
