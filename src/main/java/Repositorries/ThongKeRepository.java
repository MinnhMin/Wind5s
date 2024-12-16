/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Utilities.DbContext;
import ViewModel.SanPhamView;
import ViewModel.ThongKeView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeRepository {
    
    // lấy doanh thu và số lượng bán cho một tháng và năm cụ thể
    public List<ThongKeView> getDoanhThu(String month, String year) {
        List<ThongKeView> lst = new ArrayList<>();

        String sql = "SELECT MONTH(dbo.HOADON.NgayThanhToan) AS 'month' , SUM(dbo.HOADONCHITIET.SoLuong) AS 'so luong', SUM(dbo.HOADONCHITIET.SoLuong * dbo.HOADONCHITIET.DonGia) AS 'doan thu' FROM dbo.HOADON JOIN dbo.HOADONCHITIET ON HOADONCHITIET.IdHd = HOADON.Id JOIN dbo.CHITIETSANPHAM ON CHITIETSANPHAM.Id = HOADONCHITIET.IdCtsp WHERE dbo.HOADON.TinhTrang = 1 AND YEAR(dbo.HOADON.NgayThanhToan) LIKE ? AND MONTH(dbo.HOADON.NgayThanhToan) LIKE ? GROUP BY YEAR(dbo.HoaDon.NgayThanhToan) , MONTH(dbo.HoaDon.NgayThanhToan)";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + year + "%");
            ps.setString(2, "%" + month + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeView tk = new ThongKeView();
                tk.setThang(rs.getString(1));
                tk.setSoLuong(rs.getString(2));
                tk.setDoanhThu(rs.getString(3));
                lst.add(tk);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;

    }
    
    // doanh thu theo sản phẩm
    // 3 bảng SANPHAM, CHITIETSANPHAM, và HOADONCHITIET để tính toán số lượng bán và doanh thu của từng sản phẩm.
    public List<SanPhamView> getDoanhThuSanPham() {
        List<SanPhamView> lst = new ArrayList<>();

        String sql = "SELECT SANPHAM.Ma AS 'ma', Ten AS 'ten', CHITIETSANPHAM.NgayTao AS 'ngayTao', CHITIETSANPHAM.SoLuong AS 'soLuongton', GiaBan AS'giaban', SUM(HOADONCHITIET.SoLuong) AS 'soluongBan', SUM(HOADONCHITIET.SoLuong*DonGia) AS 'doanhThu' FROM dbo.CHITIETSANPHAM JOIN dbo.SANPHAM ON SANPHAM.Id = CHITIETSANPHAM.IdSanPham JOIN dbo.HOADONCHITIET ON HOADONCHITIET.IdCtsp = CHITIETSANPHAM.Id GROUP BY SANPHAM.Ma, Ten, CHITIETSANPHAM.NgayTao, CHITIETSANPHAM.SoLuong, GiaBan,DonGia";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamView sp = new SanPhamView();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                sp.setNgayTao(rs.getDate(3));
                sp.setSlTon(rs.getInt(4));
                sp.setDonGia(rs.getDouble(5));
                sp.setSlBan(rs.getInt(6));
                sp.setDoanhThu(rs.getDouble(7));
                lst.add(sp);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;

    }
    
    // Tính toán tổng doanh thu cho một khoảng thời gian cụ thể 
    public String getDoanhThuValue(String value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String doanhThu = "";

        String sql = "";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = null;
            if (value.equalsIgnoreCase("day")) {
                sql = "SELECT SUM(TongTien) AS 'Tong tien' FROM HoaDon WHERE TinhTrang = 1 AND DAY(NgayThanhToan)  = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, day);
            } else if (value.equalsIgnoreCase("month")) {
                sql = "SELECT SUM(TongTien) AS 'Tong tien' FROM HoaDon WHERE TinhTrang = 1 AND YEAR(NgayThanhToan) = ? AND MONTH(NgayThanhToan) = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, year);
                ps.setInt(2, month);

            } else if (value.equalsIgnoreCase("year")) {
                sql = "SELECT SUM(TongTien) AS 'Tong tien' FROM HoaDon WHERE TinhTrang = 1 AND YEAR(NgayThanhToan) = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, year);

            } else {
                sql = "SELECT SUM(TongTien) AS 'Tong tien' FROM HoaDon";
                ps = conn.prepareStatement(sql);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doanhThu = rs.getString(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }
    
    // Lấy danh sách các tháng 
    public List<String> getThang(String year) {
        List<String> lst = new ArrayList<>();

        String sql = "SELECT MONTH(NgayThanhToan) AS 'thang' FROM dbo.HoaDon WHERE TinhTrang = 1 AND YEAR(NgayThanhToan) LIKE ? GROUP BY MONTH(NgayThanhToan) ORDER BY MONTH(NgayThanhToan) DESC";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + year + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString(1));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;

    }
    
    // Lấy danh sách các năm
    public List<String> getNam() {
        List<String> lst = new ArrayList<>();

        String sql = "SELECT YEAR(NgayThanhToan) AS 'YEAR' FROM dbo.HoaDon WHERE TinhTrang = 1 GROUP BY YEAR(NgayThanhToan) ORDER BY YEAR(NgayThanhToan) DESC";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString(1));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;

    }
}
