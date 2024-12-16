/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.ThuongHieu;
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
public class ThuongHieuRepository {
    
    // Lấy danh sách tất cả thương hiệu chưa bị xóa mềm
    public List<ThuongHieu> getAll() {
        List<ThuongHieu> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.THUONGHIEU WHERE XoaMem = 0";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu obj = new ThuongHieu();
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
    
    // Lấy thông tin thương hiệu theo ID
    public ThuongHieu getObj(String id) {
        ThuongHieu obj = null;
        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.THUONGHIEU WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new ThuongHieu();
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
    
    // Lấy giá trị ID lớn nhất
    public String getIdMax() {
        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.THUONGHIEU";
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
    
    // Thêm mới một thương hiệu
    public Boolean add(ThuongHieu obj) {

        String sql = "INSERT INTO dbo.THUONGHIEU(Ma,Ten,NgayTao,NgaySua,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?)";
        try ( Connection conn = DbContext.getConnection()) {
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
    
    // Cập nhật thông tin 
    public Boolean update(Integer id, ThuongHieu obj) {

        String sql = "UPDATE dbo.THUONGHIEU SET Ma = ?, Ten = ?, NgaySua = ?, TinhTrang = ? WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
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

        String sql = "UPDATE dbo.THUONGHIEU SET XoaMem = 1 WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Xoa vv
    public Boolean deletePhy(Integer id) {

        String sql = "DELETE dbo.THUONGHIEU WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Kiểm tra xem có thương hiệu trùng tên với ID không
     public Integer checkDuclicate(String name, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.THUONGHIEU WHERE XoaMem = 0 AND Ten = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.THUONGHIEU WHERE XoaMem = 0 AND Ten = ? ";
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
