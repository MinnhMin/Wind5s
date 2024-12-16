/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.NguoiDung;
import Utilities.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NguoiDungRepository {

    ChucVuRepository cvRepo;

    public NguoiDungRepository() {
        cvRepo = new ChucVuRepository();
    }
    
    //  Lấy danh sách tất cả người dùng có trạng thái XoaMem = 0
    public List<NguoiDung> getAll() {
        List<NguoiDung> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgaySinh, GioiTinh, Sdt, Email,DiaChi, MatKhau, NgayTao, NgaySua, TrangThai , IdCV  FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND IdCV = 1";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung obj = new NguoiDung();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgaySinh(rs.getDate("NgaySinh"));
                obj.setGioiTinh(rs.getInt("GioiTinh"));
                obj.setSdt(rs.getString("Sdt"));
                obj.setEmail(rs.getString("Email"));
                obj.setDiaChi(rs.getString("DiaChi"));
                obj.setMatKhau(rs.getString("MatKhau"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TrangThai"));
                obj.setChucVu(cvRepo.getObj(rs.getString("IdCV")));
                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    // Lấy danh sách người dùng theo tên (Ten)
    public List<NguoiDung> getAllByTen(String ten) {
        List<NguoiDung> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgaySinh, GioiTinh, Sdt, Email,DiaChi, MatKhau, NgayTao, NgaySua, TrangThai , IdCV  FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND IdCV = 1 AND Ten LIKE ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung obj = new NguoiDung();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgaySinh(rs.getDate("NgaySinh"));
                obj.setGioiTinh(rs.getInt("GioiTinh"));
                obj.setSdt(rs.getString("Sdt"));
                obj.setEmail(rs.getString("Email"));
                obj.setDiaChi(rs.getString("DiaChi"));
                obj.setMatKhau(rs.getString("MatKhau"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TrangThai"));
                obj.setChucVu(cvRepo.getObj(rs.getString("IdCV")));
                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    // Lấy thông tin người dùng dựa trên Id
    public NguoiDung getObj(String id) {
        NguoiDung obj = null;
        String sql = "SELECT Id, Ma, Ten, NgaySinh, GioiTinh, Sdt, Email,DiaChi, MatKhau, NgayTao, NgaySua, TrangThai , IdCV  FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new NguoiDung();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgaySinh(rs.getDate("NgaySinh"));
                obj.setGioiTinh(rs.getInt("GioiTinh"));
                obj.setSdt(rs.getString("Sdt"));
                obj.setEmail(rs.getString("Email"));
                obj.setDiaChi(rs.getString("DiaChi"));
                obj.setMatKhau(rs.getString("MatKhau"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TrangThai"));
                obj.setChucVu(cvRepo.getObj(rs.getString("IdCV")));

            }
            conn.close();
        } catch (Exception e) {
        }
        return obj;
    }
    
    // Lấy ID người dùng lớn nhất 
    public String getIdMax() {
        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.NGUOIDUNG";
        try (Connection conn = DbContext.getConnection()) {
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
    
    // Lấy người dùng dựa trên tài khoản (số điện thoại Sdt), có thể dùng trong xác thực đăng nhập.
    public NguoiDung getObjByTaiKhoanAndMatKhau(String taiKhoan) {
        NguoiDung obj = null;
        String sql = "SELECT Id, Ma, Ten, NgaySinh, GioiTinh, Sdt, Email,DiaChi, MatKhau, NgayTao, NgaySua, TrangThai , IdCV  FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND Sdt = ? ";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new NguoiDung();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgaySinh(rs.getDate("NgaySinh"));
                obj.setGioiTinh(rs.getInt("GioiTinh"));
                obj.setSdt(rs.getString("Sdt"));
                obj.setEmail(rs.getString("Email"));
                obj.setDiaChi(rs.getString("DiaChi"));
                obj.setMatKhau(rs.getString("MatKhau"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TrangThai"));
                obj.setChucVu(cvRepo.getObj(rs.getString("IdCV")));

            }
            conn.close();
        } catch (Exception e) {
        }
        return obj;
    }
    
    // Kiểm tra xem số điện thoại có bị trùng
    public Integer checkSdt(String sdt, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND Sdt = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND Sdt = ? ";
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
    
    // Check email
    public Integer checkEmail(String email, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND Email = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.NGUOIDUNG WHERE XoaMem = 0 AND Email = ?";
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
    
    // Thêm một người dùng mới
    public Boolean add(NguoiDung nd) {
        String sql = "INSERT INTO dbo.NGUOIDUNG(IdCV,Ma,Ten,NgaySinh,GioiTinh,Sdt,Email,DiaChi,MatKhau,NgayTao,NgaySua,TrangThai,XoaMem)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, nd.getChucVu().getId());
            ps.setString(2, nd.getMa());
            ps.setString(3, nd.getTen());
            ps.setDate(4, new java.sql.Date(nd.getNgaySinh().getTime()));
            ps.setInt(5, nd.getGioiTinh());
            ps.setString(6, nd.getSdt());
            ps.setString(7, nd.getEmail());
            ps.setString(8, nd.getDiaChi());
            ps.setString(9, nd.getMatKhau());
            ps.setDate(10, new java.sql.Date(new Date().getTime()));
            ps.setDate(11, new java.sql.Date(new Date().getTime()));
            ps.setInt(12, nd.getTinhTrang());
            ps.setBoolean(13, nd.getXoaMem());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //Cập nhật thông tin của người dùng
    public Boolean update(String id, NguoiDung obj) {
        String sql = "UPDATE dbo.NGUOIDUNG \n"
                + "SET Ma = ?, Ten =? , NgaySinh = ?, GioiTinh = ?, Sdt = ?, Email = ?, DiaChi = ?, NgaySua = ?, TrangThai =?\n"
                + "WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setString(2, obj.getTen());
            ps.setDate(3, new java.sql.Date(obj.getNgaySinh().getTime()));
            ps.setInt(4, obj.getGioiTinh());
            ps.setString(5, obj.getSdt());
            ps.setString(6, obj.getEmail());
            ps.setString(7, obj.getDiaChi());
            ps.setDate(8, new java.sql.Date(new Date().getTime()));
            ps.setInt(9, obj.getTinhTrang());
            ps.setString(10, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
    
    // Xóa mềm
    public Boolean delete(String id) {
        String sql = "UPDATE dbo.NGUOIDUNG SET XoaMem = 1 WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
