package com.example.du_an_1.model;

import android.net.Uri;

public class Type_Of_Food {
    private int maLoai;
    private String tenLoai;

    private String hinhAnh;
    private int trangthai;

    public Type_Of_Food(int maLoai, String tenLoai, String hinhAnh, int trangthai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.hinhAnh = hinhAnh;
        this.trangthai = trangthai;
    }
    public Type_Of_Food(int maLoai, String tenLoai, String hinhAnh) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.hinhAnh = hinhAnh;
    }


    public Type_Of_Food() {
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Type_Of_Food(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int id) {
        this.maLoai = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
//    public Uri hienthi(Context context) {
//        byte[] imageData = getHinhAnh();// Mảng byte chứa dữ liệu hình ảnh
//        String tempFileName = "temp_image.jpg";
//        Uri uri;
//        // Tạo đường dẫn tới tập tin ảnh tạm
//        File tempFile = new File(context.getCacheDir(), tempFileName);
//        // Ghi dữ liệu blob vào tập tin ảnh tạm
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
//            fileOutputStream.write(imageData);
//            fileOutputStream.close();
//            uri = Uri.fromFile(tempFile);
//            return uri;
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
