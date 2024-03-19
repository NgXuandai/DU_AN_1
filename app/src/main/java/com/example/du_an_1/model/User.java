package com.example.du_an_1.model;

public class User {

    int maUser;
    String maDN;
    String matKhau;
    String hoTen;
    String sDt;
    int vaiTro;

    public User() {
    }

    public User(int maUser, String maDN, String matKhau, String hoTen, String sDt, int vaiTro) {
        this.maUser = maUser;
        this.maDN = maDN;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.sDt = sDt;
        this.vaiTro = vaiTro;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getMaDN() {
        return maDN;
    }

    public void setMaDN(String maDN) {
        this.maDN = maDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getsDt() {
        return sDt;
    }

    public void setsDt(String sDt) {
        this.sDt = sDt;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

}
