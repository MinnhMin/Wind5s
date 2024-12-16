/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonChiTiet extends BaseModel{
   private HoaDon hoaDon;
   private ChiTietSanPham chiTietSanPham;
   private Double donGia;
   private Integer soLuong;

    public HoaDonChiTiet(HoaDon hoaDon, ChiTietSanPham chiTietSanPham, Double donGia, Integer soLuong) {
        this.hoaDon = hoaDon;
        this.chiTietSanPham = chiTietSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public HoaDonChiTiet() {
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietSanPham getChiTietSanPham() {
        return chiTietSanPham;
    }

    public void setChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
   
    
    
}
