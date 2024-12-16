/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class SanPham extends BaseModel{
    private String ma;
    private String ten;
    private ThuongHieu thuongHieu;
    private XuatXu xuatXu;
    private String moTa;
    private String countSpct;

    public SanPham() {
    }

    public SanPham(String ma, String ten, ThuongHieu thuongHieu, XuatXu xuatXu, String moTa, String countSpct) {
        this.ma = ma;
        this.ten = ten;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.moTa = moTa;
        this.countSpct = countSpct;
    }

    public String getCountSpct() {
        return countSpct;
    }

    public void setCountSpct(String countSpct) {
        this.countSpct = countSpct;
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

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public XuatXu getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(XuatXu xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

   

    @Override
    public String toString() {
        return ten ;
    }
    
    
}
