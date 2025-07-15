package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Dichvu;
import com.example.myapplication.DichvuAdapter;
import com.example.myapplication.R;
import com.example.myapplication.database.DichvuDAO;
import com.example.myapplication.database.DichvuDatabase;

import java.util.ArrayList;
import java.util.List;

public class DichvuFragment extends Fragment {

    private EditText editMaphieu, editTensanpham, editYeucau, editThanhtien, editNgaytao, editTinhtrang, editGhichu;
    private Button btnAddyeucau;
    private RecyclerView rcvPhieutiepNhan;

    private DichvuAdapter dichvuAdapter;
    private List<Dichvu> listDichvu;
    private DichvuDAO dichvuDAO;

    private void initView(View view) {
        editMaphieu = view.findViewById(R.id.editMaphieu);
        editTensanpham = view.findViewById(R.id.editTensanpham);
        editYeucau = view.findViewById(R.id.editYeucau);
        editThanhtien = view.findViewById(R.id.editThanhtien);
        editNgaytao = view.findViewById(R.id.editNgaytao);
        editTinhtrang = view.findViewById(R.id.editTinhtrang);
        editGhichu = view.findViewById(R.id.editGhichu);
        btnAddyeucau = view.findViewById(R.id.btnAddyeucau);
        rcvPhieutiepNhan = view.findViewById(R.id.rcvPhieutiepNhan);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dichvu, container, false);
        initView(view);

        // Khởi tạo Room DAO
        dichvuDAO = DichvuDatabase.getInstance(getContext()).dichvuDAO();

        // Lấy danh sách từ DB
        listDichvu = new ArrayList<>(dichvuDAO.getListDichvu());

        // Setup Adapter
        dichvuAdapter = new DichvuAdapter(listDichvu);
        rcvPhieutiepNhan.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvPhieutiepNhan.setAdapter(dichvuAdapter);

        // Xử lý nút thêm
        btnAddyeucau.setOnClickListener(v -> {
            String maPhieu = editMaphieu.getText().toString();
            String tenSanPham = editTensanpham.getText().toString();
            String yeuCau = editYeucau.getText().toString();
            int thanhTien = Integer.parseInt(editThanhtien.getText().toString());
            String ngayTao = editNgaytao.getText().toString();
            String tinhTrang = editTinhtrang.getText().toString();
            String ghiChu = editGhichu.getText().toString();

            Dichvu dichvu = new Dichvu(maPhieu, tenSanPham, yeuCau, thanhTien, ngayTao, tinhTrang, ghiChu);

            dichvuDAO.insertDichvu(dichvu);

            listDichvu.clear();
            listDichvu.addAll(dichvuDAO.getListDichvu());
            dichvuAdapter.notifyDataSetChanged();
        });



        return view;
    }
}
