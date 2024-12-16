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
public class NguoiDung extends BaseModel{
    private ChucVu chucVu;
    private String ma;
    private String ten;
    private Date ngaySinh;
    private String email;
    private String sdt;
    private Integer gioiTinh;
    private String diaChi;
    private String taiKhoan;
    private String matKhau;

    public NguoiDung(ChucVu chucVu, String ma, String ten, Date ngaySinh, String email, String sdt, Integer gioiTinh, String diaChi, String taiKhoan, String matKhau) {
        this.chucVu = chucVu;
        this.ma = ma;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }


  

    public NguoiDung() {
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "NguoiDung{" + "chucVu=" + chucVu + ", ma=" + ma + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", email=" + email + ", sdt=" + sdt + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + '}';
    }

    
}
