/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.HoaDon;
import Model.KhachHang;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
//        KhachHang kh = new KhachHang();
//        kh.setMa("aaa");
//        System.out.println(kh.getNgayTao());
        HoaDon hd = new HoaDon();
        System.out.println(new KhachHangRepository().getObj("6"));
    }
}
