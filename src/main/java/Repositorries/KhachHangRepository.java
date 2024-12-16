/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.KhachHang;
import Utilities.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangRepository {
    
    // Lấy tất cả khách hàng chưa bị xóa
    public List<KhachHang> getAll() {
        List<KhachHang> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgaySinh, GioiTinh, Sdt, Email,DiaChi, NgayTao, NgaySua, TinhTrang  FROM dbo.KHACHHANG WHERE XoaMem = 0";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang obj = new KhachHang();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgaySinh(rs.getDate("NgaySinh"));
                obj.setGioiTinh(rs.getInt("GioiTinh"));
                obj.setSdt(rs.getString("Sdt"));
                obj.setEmail(rs.getString("Email"));
                obj.setDiaChi(rs.getString("DiaChi"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    // Lấy danh sách khách hàng theo tên
    public List<KhachHang> getAllByTen(String ten) {
        List<KhachHang> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgaySinh, GioiTinh, Sdt, Email,DiaChi, NgayTao, NgaySua, TinhTrang  FROM dbo.KHACHHANG WHERE XoaMem = 0 AND Ten LIKE ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang obj = new KhachHang();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgaySinh(rs.getDate("NgaySinh"));
                obj.setGioiTinh(rs.getInt("GioiTinh"));
                obj.setSdt(rs.getString("Sdt"));
                obj.setEmail(rs.getString("Email"));
                obj.setDiaChi(rs.getString("DiaChi"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
       
    // Lấy ID lớn nhất hiện tại trong bảng khách hàng
    public String getIdMax() {
        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.KHACHHANG";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getString(1) == null ? "-1" : rs.getString(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Math.floor(Math.random() + 100) + "";
    }
    
    // Lấy thông tin khách hàng theo ID
    public KhachHang getObj(String id) {
        KhachHang obj = null;
        String sql = "SELECT Id, Ma, Ten, NgaySinh, GioiTinh, Sdt, Email,DiaChi, NgayTao, NgaySua, TinhTrang  FROM dbo.KHACHHANG WHERE XoaMem = 0 AND Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new KhachHang();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgaySinh(rs.getDate("NgaySinh"));
                obj.setGioiTinh(rs.getInt("GioiTinh"));
                obj.setSdt(rs.getString("Sdt"));
                obj.setEmail(rs.getString("Email"));
                obj.setDiaChi(rs.getString("DiaChi"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
            }
            conn.close();
        } catch (Exception e) {
        }
        return obj;
    }
    
     // Kiểm tra số điện thoại có bị trùng hay không
public Integer checkSdt(String sdt, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.KHACHHANG WHERE XoaMem = 0 AND Sdt = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.KHACHHANG WHERE XoaMem = 0 AND Sdt = ? ";
        }
        Integer result = 0;
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sdt);
            if (id != null) {
                ps.setInt(2, id);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
        }
        return result;
    }
    
// Kiểm tra email có bị trùng hay không
    public Integer checkEmail(String email, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.KHACHHANG WHERE XoaMem = 0 AND Email = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.KHACHHANG WHERE XoaMem = 0 AND Email = ?";
        }
        Integer result = 0;
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            if (id != null) {
                ps.setInt(2, id);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
        }
        return result;
    }
    
    // Thêm khách hàng mới
    public Boolean add(KhachHang kh) {
        String sql = "INSERT INTO dbo.KHACHHANG(Ma,Ten,NgaySinh,GioiTinh,Sdt,Email,DiaChi,NgayTao,NgaySua,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMa());
            ps.setString(2, kh.getTen());
            ps.setDate(3, new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setInt(4, kh.getGioiTinh());
            ps.setString(5, kh.getSdt());
            ps.setString(6, kh.getEmail());
            ps.setString(7, kh.getDiaChi());
            ps.setDate(8, new java.sql.Date(kh.getNgayTao().getTime()));
            ps.setDate(9, new java.sql.Date(kh.getNgaySua().getTime()));
            ps.setInt(10, kh.getTinhTrang());
            ps.setBoolean(11, kh.getXoaMem());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Cập nhật thông tin khách hàng
    public Boolean update(String id, KhachHang obj) {
        String sql = "UPDATE dbo.KHACHHANG \n"
                + "SET Ma = ?, Ten =? , NgaySinh = ?, GioiTinh = ?, Sdt = ?, Email = ?, DiaChi = ?, NgaySua = ?, TinhTrang =?\n"
                + "WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setString(2, obj.getTen());
            ps.setDate(3, new java.sql.Date(obj.getNgaySinh().getTime()));
            ps.setInt(4, obj.getGioiTinh());
            ps.setString(5, obj.getSdt());
            ps.setString(6, obj.getEmail());
            ps.setString(7, obj.getDiaChi());
            ps.setDate(8, new java.sql.Date(obj.getNgaySua().getTime()));
            ps.setInt(9, obj.getTinhTrang());
            ps.setString(10, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
    
    // Xóa mem
    public Boolean delete(String id){
        String sql = "UPDATE dbo.KHACHHANG SET XoaMem = 1 WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return false;
    }
}
