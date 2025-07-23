package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;
import com.example.myapplication.SanPhamThongKe;
import com.example.myapplication.ThongKeChiTietAdapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThongkechitietFragment extends Fragment {

    EditText edtTenSP, edtGia, edtTenKhachHang;
    Spinner spinnerTinhTrang;
    Button btnThem;
    TextView txtTongDon, txtDoanhThu, txtDaSua, txtDangXuLy;
    RecyclerView recyclerView;


    DatabaseHelper dbHelper;
    ArrayList<SanPhamThongKe> list = new ArrayList<>();

    ThongKeChiTietAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongkechitiet, container, false);

        // Ánh xạ
        edtTenKhachHang = view.findViewById(R.id.edtTenKhachHang);
        edtTenSP = view.findViewById(R.id.edtTenSP);
        Spinner spinnerNhanVien;
        String[] danhSachNhanVien = {
                "Nguyễn Thanh Toàn",
                "Đặng Hoàng Phúc",
                "Lê Minh Nhật",
                "Trần Duy Bảo",
                "Nguyễn Thành Đạt"
        };
        spinnerNhanVien = view.findViewById(R.id.spinnerNhanVien);

        ArrayAdapter<String> adapterNhanVien = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_spinner_item, danhSachNhanVien
        );
        adapterNhanVien.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNhanVien.setAdapter(adapterNhanVien);
        spinnerNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tenNhanVien = danhSachNhanVien[position];
                Toast.makeText(requireContext(), "Đã chọn: " + tenNhanVien, Toast.LENGTH_SHORT).show();
                // TODO: xử lý lọc dữ liệu thống kê theo tên nhân viên nếu cần
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không chọn gì
            }
        });
        edtGia = view.findViewById(R.id.edtGia);
        spinnerTinhTrang = view.findViewById(R.id.spinnerTinhTrang);
        btnThem = view.findViewById(R.id.btnThem);

        txtTongDon = view.findViewById(R.id.txtTongDon);
        txtDoanhThu = view.findViewById(R.id.txtDoanhThu);
        txtDaSua = view.findViewById(R.id.txtDaSua);
        txtDangXuLy = view.findViewById(R.id.txtDangXuLy);
        recyclerView = view.findViewById(R.id.recyclerView);

        dbHelper = new DatabaseHelper(getContext());
        adapter = new ThongKeChiTietAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Xử lý thêm dịch vụ
        btnThem.setOnClickListener(v -> {
            String tenKhachHang = edtTenKhachHang.getText().toString();
            String tensp = edtTenSP.getText().toString();
            int gia = Integer.parseInt(edtGia.getText().toString());
            String tinhtrang = spinnerTinhTrang.getSelectedItem().toString();
            String tenNhanVien = spinnerNhanVien.getSelectedItem().toString(); // 👈 thêm dòng này

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ten_khach_hang", tenKhachHang);  // cột đã tạo trong DB
            values.put(DatabaseHelper.COLUMN_TENSP, tensp);
            values.put(DatabaseHelper.COLUMN_GIA, gia);
            values.put(DatabaseHelper.COLUMN_TINHTRANG, tinhtrang);
            values.put("ten_nhan_vien", tenNhanVien); // 👈 nhớ lưu vào DB nếu cần
            db.insert(DatabaseHelper.TABLE_NAME, null, values);

            loadData(); // cập nhật giao diện
            edtTenKhachHang.setText("");
            edtTenSP.setText("");
            edtGia.setText("");
            spinnerTinhTrang.setSelection(0);

            Toast.makeText(requireContext(),
                    "Đã thêm:\nSản phẩm: " + tensp +
                            "\nKhách hàng: " + tenKhachHang +
                            "\nNhân viên sửa chữa: " + tenNhanVien,
                    Toast.LENGTH_LONG).show(); // 👈 Toast đầy đủ
        });
        loadData();

        return view;
    }

    private void loadData() {
        list.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);

        int tongDon = 0, tongGia = 0, daSua = 0, dangXuLy = 0;

        while (cursor.moveToNext()) {
            String tenSP = cursor.getString(1);
            int gia = cursor.getInt(2);
            String tinhtrang = cursor.getString(3);
            String tenKhachHang = cursor.getString(4);
            String tenNhanVien = cursor.getString(5);

            list.add(new SanPhamThongKe(tenSP, tenKhachHang, tenNhanVien, gia, tinhtrang));
            tongDon++;
            tongGia += gia;
            if (tinhtrang.equalsIgnoreCase("Đã sửa xong")) daSua++;
            else if (tinhtrang.equalsIgnoreCase("Đang xử lý")) dangXuLy++;
        }

        cursor.close();
        adapter.notifyDataSetChanged();

        txtTongDon.setText("Tổng đơn: " + tongDon);
        txtDoanhThu.setText("Tổng doanh thu: " + tongGia + " đ");
        txtDaSua.setText("Đã sửa xong: " + daSua);
        txtDangXuLy.setText("Đang xử lý: " + dangXuLy);
    }
}
