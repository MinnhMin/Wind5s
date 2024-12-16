/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.ChatLieu;
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
public class ChatLieuRepository {
    
    //Lay dsban ghi chua bi xoa mem
    public List<ChatLieu> getAll() {
        List<ChatLieu> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.CHATLIEU WHERE XoaMem = 0";
        try (Connection conn = DbContext.getConnection()) {
            // Chuẩn bị và thực thi câu lệnh SQL
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu obj = new ChatLieu();
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
        return lst; //tra ve ds doi tuong
    }
    
    //Lấy một đối tượng "ChatLieu" theo ID
    public ChatLieu getObj(String id) {
        ChatLieu obj = null;
        String sql = "SELECT Id, Ma, Ten, NgayTao, NgaySua, TinhTrang,XoaMem FROM dbo.CHATLIEU WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new ChatLieu();
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
        return obj;  // Trả về đối tượng ChatLieu hoặc null
    }
    
    // Lấy giá trị ID lớn nhất trong bảng "CHATLIEU"
    public String getIdMax() {
        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.CHATLIEU";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Lấy giá trị lớn nhất nếu có

                return rs.getString(1) == null ? "-1" : rs.getString(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Math.floor(Math.random() + 100) + "";
    }
    
    // Add ChatLieu
    public Boolean add(ChatLieu obj) {

        String sql = "INSERT INTO dbo.CHATLIEU(Ma,Ten,NgayTao,NgaySua,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?)";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setString(2, obj.getTen());
            ps.setDate(3, new java.sql.Date(new Date().getTime()));
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.setInt(5, obj.getTinhTrang());
            ps.setBoolean(6, obj.getXoaMem());

            return ps.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu thêm thất bại
    }
    
    // Update ChatLieu
    public Boolean update(Integer id, ChatLieu obj) {

        String sql = "UPDATE dbo.CHATLIEU SET Ma = ?, Ten = ?, NgaySua = ?, TinhTrang = ? WHERE Id = ?";
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
    
    // Xoa mem
    public Boolean delete(Integer id) {

        String sql = "UPDATE dbo.CHATLIEU SET XoaMem = 1 WHERE Id = ?";
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

        String sql = "DELETE dbo.CHATLIEU WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Kiem tra trung
    public Integer checkDuclicate(String name, Integer id) {
        String sql;
        if (id != null) {
             // Nếu ID không null, kiểm tra tên trùng lặp nhưng loại trừ ID hiện tại
            sql = "SELECT Count(Id) FROM dbo.CHATLIEU WHERE XoaMem = 0 AND Ten = ? And id != ?";
        } else {
            // Nếu ID null, chỉ kiểm tra tên trùng lặp
            sql = "SELECT Count(Id) FROM dbo.CHATLIEU WHERE XoaMem = 0 AND Ten = ? ";
        }
        Integer result = 0;
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            if (id != null) {
                ps.setInt(2, id); // Gán ID nếu có
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Lấy kết quả số lượng bản ghi trùng lặp
                result = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
        }
        return result; // Trả về số lượng bản ghi trùng lặp
    }
}
