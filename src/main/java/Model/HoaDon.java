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
public class HoaDon extends BaseModel {

    private String ma;
    private NguoiDung nguoiDung;
    private KhachHang khachHang;
    private Double tongTien = 0.0;
    private Double thanhTien = 0.0;
    private Double tienMat = 0.0;
    private Double tienChuyenKhoan = 0.0;
    private String maChuyenKhoan;
    private String hinhThuc;
    private Date ngayThanhToan;
    private String moTa;

    public HoaDon() {
    }

    public HoaDon(String ma, NguoiDung nguoiDung, KhachHang khachHang, String maChuyenKhoan, String hinhThuc, Date ngayThanhToan, String moTa) {
        this.ma = ma;
        this.nguoiDung = nguoiDung;
        this.khachHang = khachHang;
        this.maChuyenKhoan = maChuyenKhoan;
        this.hinhThuc = hinhThuc;
        this.ngayThanhToan = ngayThanhToan;
        this.moTa = moTa;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Double getTienMat() {
        return tienMat;
    }

    public void setTienMat(Double tienMat) {
        this.tienMat = tienMat;
    }

    public Double getTienChuyenKhoan() {
        return tienChuyenKhoan;
    }

    public void setTienChuyenKhoan(Double tienChuyenKhoan) {
        this.tienChuyenKhoan = tienChuyenKhoan;
    }

    public String getMaChuyenKhoan() {
        return maChuyenKhoan;
    }

    public void setMaChuyenKhoan(String maChuyenKhoan) {
        this.maChuyenKhoan = maChuyenKhoan;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }



    @Override
    public String toString() {
        return "HoaDon{" + "ma=" + ma + ", nguoiDung=" + nguoiDung + ", khachHang=" + khachHang + ", tongTien=" + tongTien + ", thanhTien=" + thanhTien + ", ngayThanhToan=" + ngayThanhToan + ", moTa=" + moTa + '}';
    }

}
