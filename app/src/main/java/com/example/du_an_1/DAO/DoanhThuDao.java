package com.example.du_an_1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.database.DbHelper;

public class DoanhThuDao {
    DbHelper dbHelper;

    public DoanhThuDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc) {
        String formattedNgayBatDau = ngaybatdau.replaceAll("/", ""); // Chuyển đổi thành "yyyyMMdd"
        String formattedNgayKetThuc = ngayketthuc.replaceAll("/", ""); // Chuyển đổi thành "yyyyMMdd"

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(gioHang.TONG_GIA) AS doanhThu " +
                "FROM gioHang " +
                "WHERE substr(gioHang.NGAY_DAT, 7) || substr(gioHang.NGAY_DAT, 4, 2) || substr(gioHang.NGAY_DAT, 1, 2) " +
                "BETWEEN ? AND ?", new String[]{formattedNgayBatDau, formattedNgayKetThuc});

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}
