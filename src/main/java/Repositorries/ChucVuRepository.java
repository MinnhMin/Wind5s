/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.ChucVu;
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
public class ChucVuRepository {

    // Lấy tất cả các bản ghi "ChucVu" chưa bị xóa (XoaMem = 0).
    public List<ChucVu> getAll() {
        List<ChucVu> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.CHUCVU WHERE XoaMem = 0";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu obj = new ChucVu();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));
                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    // Lấy một đối tượng ChucVu theo tên (dùng LIKE để tìm kiếm).
    public ChucVu getObjByTen(String ten) {
        ChucVu obj = null;

        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.CHUCVU WHERE Ten LIKE ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new ChucVu();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Lấy một đối tượng ChucVu theo ID
    public ChucVu getObj(String id) {
        ChucVu obj = null;

        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.CHUCVU WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new ChucVu();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    //Thêm một bản ghi ChucVu mới vào cơ sở dữ liệu.
    public Boolean add(ChucVu obj) {

        String sql = "INSERT INTO dbo.CHUCVU(Ma,Ten,NgayTao,NgaySua,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?)";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getMa());
            ps.setString(3, obj.getTen());
            ps.setDate(4, new java.sql.Date(obj.getNgayTao().getTime()));
            ps.setDate(5, new java.sql.Date(obj.getNgaySua().getTime()));
            ps.setInt(6, obj.getTinhTrang());
            ps.setBoolean(7, obj.getXoaMem());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin của một bản ghi ChucVu.
    public Boolean update(Integer id, ChucVu obj) {

        String sql = "UPDATE dbo.CHUCVU SET Ma = ?, Ten = ?, NgaySua = ?, TinhTrang = ? WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setString(2, obj.getTen());
            ps.setDate(3, new java.sql.Date(obj.getNgayTao().getTime()));
            ps.setInt(4, obj.getTinhTrang());
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa mềm
    public Boolean delete(Integer id) {

        String sql = "UPDATE dbo.CHUCVU SET XoaMem = 1 WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa vĩnh viễn
    public Boolean deletePhy(Integer id) {

        String sql = "DELETE dbo.CHUCVU WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
