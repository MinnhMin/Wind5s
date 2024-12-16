/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.KichThuoc;
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
public class KichThuocRepository {

    // Trả về tất cả các kích thước chưa bị xóa (XoaMem = 0).
    public List<KichThuoc> getAll() {
        List<KichThuoc> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.KICHTHUOC WHERE XoaMem = 0";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichThuoc obj = new KichThuoc();
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

    // Trả về ID lớn nhất hiện tại trong bảng KICHTHUOC.
    public String getIdMax() {
        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.KICHTHUOC";
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

    //Trả về thông tin của một kích thước theo ID.
    public KichThuoc getObj(String id) {
        KichThuoc obj = null;
        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.KICHTHUOC WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new KichThuoc();
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

    //Thêm một kích thước mới vào bảng
    public Boolean add(KichThuoc obj) {

        String sql = "INSERT INTO dbo.KICHTHUOC(Ma,Ten,NgayTao,NgaySua,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?)";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setString(2, obj.getTen());
            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.setInt(5, obj.getTinhTrang());
            ps.setBoolean(6, obj.getXoaMem());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin của kích thước theo ID.
    public Boolean update(Integer id, KichThuoc obj) {

        String sql = "UPDATE dbo.KICHTHUOC SET Ma = ?, Ten = ?, NgaySua = ?, TinhTrang = ? WHERE Id = ?";
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

    // Xóa mem
    public Boolean delete(Integer id) {

        String sql = "UPDATE dbo.KICHTHUOC SET XoaMem = 1 WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoa vinh vien
    public Boolean deletePhy(Integer id) {

        String sql = "DELETE dbo.KICHTHUOC WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem tên kích thước đã tồn tại hay chưa
    public Integer checkDuclicate(String name, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.KICHTHUOC WHERE XoaMem = 0 AND Ten = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.KICHTHUOC WHERE XoaMem = 0 AND Ten = ? ";
        }
        Integer result = 0;
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
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
}
