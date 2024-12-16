/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class SanPhamView {
    private String ma;
    private String ten;
    private Date ngayTao;
    private int slTon;
    private Double donGia;
    private int slBan;
    private Double doanhThu;

    public SanPhamView() {
    }



    

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }



    public int getSlTon() {
        return slTon;
    }

    public void setSlTon(int slTon) {
        this.slTon = slTon;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public int getSlBan() {
        return slBan;
    }

    public void setSlBan(int slBan) {
        this.slBan = slBan;
    }

    public Double getDoanhThu() {
        return doanhThu;
    }



    public void setDoanhThu(Double doanhThu) {
        this.doanhThu = doanhThu;
    }

    @Override
    public String toString() {
        return "SanPhamView{" + "ma=" + ma + ", ten=" + ten + ", ngayTao=" + ngayTao + ", slTon=" + slTon + ", donGia=" + donGia + ", slBan=" + slBan + ", doanhThu=" + doanhThu + '}';
    }

    
}
