package com.example.myapplication;

public class Menu {
    private String ten;
    private String Mota;
    private int Hinh;

    public Menu(String ten, String mota, int hinh) {
        this.ten = ten;
        Mota = mota;
        Hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public String getMota() {
        return Mota;
    }

    public int getHinh() {
        return Hinh;
    }
}
