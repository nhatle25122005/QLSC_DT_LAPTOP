package com.example.myapplication.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Linhkien;
import com.example.myapplication.LinhkienAdapter;
import com.example.myapplication.R;
import com.example.myapplication.database.LinhkienDAO;
import com.example.myapplication.database.LinhkienDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class LinhkienFragment extends Fragment {
    // Khai báo các biến giao diện
    private EditText edtTen, edtGia, edtSoluong, edtMota;
    private Button btnThem, btnSua, btnXoa;
    private RecyclerView rcvLinhkien;
    private LinhkienAdapter linhkienAdapter;
    private List<Linhkien> linhkienList;
    private LinhkienDAO linhkienDAO;
    private int selectedId = -1; // Lưu id của item đang chọn để sửa/xóa

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linhkien, container, false);
        initView(view);
        setupRecyclerView();
        setupDatabase();
        loadData();
        setupListeners();
        return view;
    }

    // Ánh xạ view
    private void initView(View view) {
        edtTen = view.findViewById(R.id.edtTenLinhkien);
        edtGia = view.findViewById(R.id.edtGiaLinhkien);
        edtSoluong = view.findViewById(R.id.edtSoluongLinhkien);
        edtMota = view.findViewById(R.id.edtMotaLinhkien);
        btnThem = view.findViewById(R.id.btnThemLinhkien);
        btnSua = view.findViewById(R.id.btnSuaLinhkien);
        btnXoa = view.findViewById(R.id.btnXoaLinhkien);
        rcvLinhkien = view.findViewById(R.id.rcvLinhkien);
    }

    // Khởi tạo RecyclerView và Adapter
    private void setupRecyclerView() {
        linhkienList = new ArrayList<>();
        linhkienAdapter = new LinhkienAdapter(linhkienList);
        rcvLinhkien.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvLinhkien.setAdapter(linhkienAdapter);

        // Xử lý chọn item để sửa/xóa
        linhkienAdapter.setOnItemClickListener(new LinhkienAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Linhkien linhkien) {
                selectedId = linhkien.getId();
                edtTen.setText(linhkien.getTen());
                edtGia.setText(String.valueOf(linhkien.getGia()));
                edtSoluong.setText(String.valueOf(linhkien.getSoluong()));
                edtMota.setText(linhkien.getMota());
            }
        });
    }

    // Khởi tạo database và DAO
    private void setupDatabase() {
        linhkienDAO = LinhkienDatabase.getInstance(getContext()).linhkienDAO();
    }

    // Load dữ liệu từ DB lên danh sách (chạy ở background thread)
    private void loadData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            final List<Linhkien> data = linhkienDAO.getAll();
            // Cập nhật UI phải chạy trên main thread
            requireActivity().runOnUiThread(() -> {
                linhkienList.clear();
                linhkienList.addAll(data);
                linhkienAdapter.notifyDataSetChanged();
            });
        });
    }

    // Thiết lập sự kiện cho các nút CRUD
    private void setupListeners() {
        // Thêm linh kiện
        btnThem.setOnClickListener(v -> {
            if (validateInput()) {
                Linhkien linhkien = new Linhkien();
                linhkien.setTen(edtTen.getText().toString().trim());
                linhkien.setGia(Double.parseDouble(edtGia.getText().toString().trim()));
                linhkien.setSoluong(Integer.parseInt(edtSoluong.getText().toString().trim()));
                linhkien.setMota(edtMota.getText().toString().trim());
                Executors.newSingleThreadExecutor().execute(() -> {
                    linhkienDAO.insert(linhkien);
                    requireActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        clearInput();
                        loadData();
                    });
                });
            }
        });

        // Sửa linh kiện
        btnSua.setOnClickListener(v -> {
            if (selectedId == -1) {
                Toast.makeText(getContext(), "Vui lòng chọn linh kiện để sửa", Toast.LENGTH_SHORT).show();
                return;
            }
            if (validateInput()) {
                Linhkien linhkien = new Linhkien();
                linhkien.setId(selectedId);
                linhkien.setTen(edtTen.getText().toString().trim());
                linhkien.setGia(Double.parseDouble(edtGia.getText().toString().trim()));
                linhkien.setSoluong(Integer.parseInt(edtSoluong.getText().toString().trim()));
                linhkien.setMota(edtMota.getText().toString().trim());
                Executors.newSingleThreadExecutor().execute(() -> {
                    linhkienDAO.update(linhkien);
                    requireActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        clearInput();
                        selectedId = -1;
                        loadData();
                    });
                });
            }
        });

        // Xóa linh kiện
        btnXoa.setOnClickListener(v -> {
            if (selectedId == -1) {
                Toast.makeText(getContext(), "Vui lòng chọn linh kiện để xóa", Toast.LENGTH_SHORT).show();
                return;
            }
            Linhkien linhkien = new Linhkien();
            linhkien.setId(selectedId);
            Executors.newSingleThreadExecutor().execute(() -> {
                linhkienDAO.delete(linhkien);
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    clearInput();
                    selectedId = -1;
                    loadData();
                });
            });
        });
    }

    // Kiểm tra dữ liệu nhập vào
    private boolean validateInput() {
        if (TextUtils.isEmpty(edtTen.getText()) || TextUtils.isEmpty(edtGia.getText()) ||
                TextUtils.isEmpty(edtSoluong.getText())) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Xóa dữ liệu trên form
    private void clearInput() {
        edtTen.setText("");
        edtGia.setText("");
        edtSoluong.setText("");
        edtMota.setText("");
    }
}
