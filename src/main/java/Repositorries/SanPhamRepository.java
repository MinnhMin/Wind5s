/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.SanPham;
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
public class SanPhamRepository {

    ThuongHieuRepository thRepo;
    XuatXuRepository xxRepo;

    public SanPhamRepository() {
        thRepo = new ThuongHieuRepository();
        xxRepo = new XuatXuRepository();
    }
    
    // Lấy tất cả các sản phẩm từ bảng SANPHAM chưa bị xóa mềm (XoaMem = 0)
    // kết hợp với thông tin chi tiết từ bảng CHITIETSANPHAM
    // Sử dụng JOIN
    public List<SanPham> getAll() {
        List<SanPham> lst = new ArrayList<>();
        String sql = "SELECT sp.Id, sp.Ma, Ten, IdThuongHieu, IdXuatXu,  sp.NgayTao, sp.NgaySua, sp.TinhTrang,sp.XoaMem,sp.Mota, sum(spct.SoLuong) AS spctCount FROM dbo.SANPHAM sp LEFT JOIN  dbo.CHITIETSANPHAM spct ON sp.Id = spct.IdSanPham WHERE sp.XoaMem = 0 GROUP BY sp.Id,sp.Ma,sp.Ten, sp.IdThuongHieu,sp.IdXuatXu,sp.NgayTao,sp.NgaySua,sp.TinhTrang,sp.XoaMem,sp.Mota";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham obj = new SanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setThuongHieu(thRepo.getObj(rs.getString("IdThuongHieu")));
                obj.setXuatXu(xxRepo.getObj(rs.getString("IdXuatXu")));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));
                obj.setMoTa(rs.getString("Mota"));             
                obj.setCountSpct(rs.getString("spctCount"));

                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    // Tìm kiếm các sản phẩm theo tên
    public List<SanPham> getAllByTen(String ten) {
        List<SanPham> lst = new ArrayList<>();
        String sql = "SELECT sp.Id, sp.Ma, Ten, IdThuongHieu, IdXuatXu,  sp.NgayTao, sp.NgaySua, sp.TinhTrang,sp.XoaMem,sp.Mota, sum(spct.SoLuong) AS spctCount FROM dbo.SANPHAM sp LEFT JOIN  dbo.CHITIETSANPHAM spct ON sp.Id = spct.IdSanPham WHERE sp.XoaMem = 0 AND sp.Ten LIKE ? GROUP BY sp.Id,sp.Ma,sp.Ten, sp.IdThuongHieu,sp.IdXuatXu,sp.NgayTao,sp.NgaySua,sp.TinhTrang,sp.XoaMem,sp.Mota";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham obj = new SanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setThuongHieu(thRepo.getObj(rs.getString("IdThuongHieu")));
                obj.setXuatXu(xxRepo.getObj(rs.getString("IdXuatXu")));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));
                obj.setMoTa(rs.getString("Mota"));
                obj.setCountSpct(rs.getString("spctCount"));
                
                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    // Lấy thông tin chi tiết của một sản phẩm dựa trên Id
    public SanPham getObj(String id) {
        SanPham obj = null;
        String sql = "SELECT Id, Ma, Ten,IdThuongHieu, IdXuatXu, NgayTao, NgaySua, TinhTrang,XoaMem,Mota FROM dbo.SANPHAM WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new SanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setThuongHieu(thRepo.getObj(rs.getString("IdThuongHieu")));
                obj.setXuatXu(xxRepo.getObj(rs.getString("IdXuatXu")));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));      
                obj.setMoTa(rs.getString("Mota"));


            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    // Lấy ID lớn nhất
    public String getIdMax() {
        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.SANPHAM";
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
    
    // Lấy sản phẩm dựa trên mã sản phẩm (Ma)
    public SanPham getObjByMa(String ma) {
        SanPham obj = null;
        String sql = "SELECT Id, Ma, Ten,IdThuongHieu, IdXuatXu, NgayTao, NgaySua, TinhTrang,XoaMem,Mota FROM dbo.SANPHAM WHERE XoaMem = 0 AND Ma = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new SanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setTen(rs.getString("Ten"));
                obj.setThuongHieu(thRepo.getObj(rs.getString("IdThuongHieu")));
                obj.setXuatXu(xxRepo.getObj(rs.getString("IdXuatXu")));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));
                obj.setMoTa(rs.getString("Mota"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    // Thêm một sản phẩm mới 
    public Boolean add(SanPham obj) {

        String sql = "INSERT INTO dbo.SANPHAM(Ma,Ten,NgayTao,NgaySua,TinhTrang,XoaMem, IdThuongHieu,IdXuatXu, MoTa)VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setString(2, obj.getTen());
            ps.setDate(3, new java.sql.Date(obj.getNgayTao().getTime()));
            ps.setDate(4, new java.sql.Date(obj.getNgaySua().getTime()));
            ps.setInt(5, obj.getTinhTrang());
            ps.setBoolean(6, obj.getXoaMem());
            ps.setInt(7, obj.getThuongHieu().getId());
            ps.setInt(8, obj.getXuatXu().getId());            
            ps.setString(9, obj.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Cập nhật thông tin sản phẩm theo Id
    public Boolean update(Integer id, SanPham obj) {

        String sql = "UPDATE dbo.SANPHAM SET Ma = ?, Ten = ?,IdThuongHieu = ? ,IdXuatXu = ? , NgaySua = ?, TinhTrang = ?, MoTa = ?  WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setString(2, obj.getTen());
            ps.setInt(3, obj.getThuongHieu().getId());
            ps.setInt(4, obj.getXuatXu().getId());
            ps.setDate(5, new java.sql.Date(obj.getNgayTao().getTime()));
            ps.setInt(6, obj.getTinhTrang());            
            ps.setString(7, obj.getMoTa());
            ps.setInt(8, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Xoa mem
    public Boolean delete(Integer id) {

        String sql = "UPDATE dbo.SANPHAM SET XoaMem = 1 WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
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

        String sql = "DELETE dbo.SANPHAM WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     
    // Kiểm tra xem sản phẩm có bị trùng lặp
     public Integer checkDuclicate(SanPham sp, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.SANPHAM WHERE XoaMem = 0 AND Ten = ? AND IdThuongHieu = ? AND IdXuatXu = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.SANPHAM WHERE XoaMem = 0 AND Ten = ? AND IdThuongHieu = ? AND IdXuatXu = ?  ";
        }
        Integer result = 0;
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getTen());   
            ps.setInt(2, sp.getThuongHieu().getId());
            ps.setInt(3, sp.getXuatXu().getId());

            if (id != null) {
                ps.setInt(4, id);
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
