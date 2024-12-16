/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.HoaDonChiTiet;
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
public class HoaDonChiTietRepository {

    HoaDonRepository hdRepo;
    ChiTietSanPhamRepository ctspRepo;

    public HoaDonChiTietRepository() {
        hdRepo = new HoaDonRepository();
        ctspRepo = new ChiTietSanPhamRepository();
    }
    
    // Lấy tất cả các bản ghi
    public List<HoaDonChiTiet> getAll() {
        List<HoaDonChiTiet> lst = new ArrayList<>();
        String sql = "SELECT Id, IdHd, IdCtsp, DonGia, SoLuong, NgayTao,NgaySua,TinhTrang FROM dbo.HOADONCHITIET";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet obj = new HoaDonChiTiet();
                obj.setId(rs.getInt("Id"));
                obj.setHoaDon(hdRepo.getObj(rs.getString("IdHd")));
                obj.setChiTietSanPham(ctspRepo.getObj(rs.getString("IdCtsp")));

                obj.setDonGia(rs.getDouble("DonGia"));
                obj.setSoLuong(rs.getInt("SoLuong"));
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
    
    // Lấy tất cả các bản ghi "HoaDonChiTiet" theo ID hóa đơn.
    public List<HoaDonChiTiet> getAllByHoaDon(String id) {
        List<HoaDonChiTiet> lst = new ArrayList<>();
        String sql = "SELECT Id, IdHd, IdCtsp, DonGia, SoLuong, NgayTao,NgaySua,TinhTrang FROM dbo.HOADONCHITIET WHERE IdHd = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet obj = new HoaDonChiTiet();
                obj.setId(rs.getInt("Id"));
                obj.setHoaDon(hdRepo.getObj(rs.getString("IdHd")));
                obj.setChiTietSanPham(ctspRepo.getObj(rs.getString("IdCtsp")));
                obj.setDonGia(rs.getDouble("DonGia"));
                obj.setSoLuong(rs.getInt("SoLuong"));
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
    
    //Lấy một bản ghi "HoaDonChiTiet" theo ID.
    public HoaDonChiTiet getObj(String id) {
        HoaDonChiTiet obj = null;
        String sql = "SELECT Id, IdHd, IdCtsp, DonGia, SoLuong, NgayTao,NgaySua,TinhTrang FROM dbo.HOADONCHITIET  where id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new HoaDonChiTiet();
                obj.setId(rs.getInt("Id"));
                obj.setHoaDon(hdRepo.getObj(rs.getString("IdHd")));
                obj.setChiTietSanPham(ctspRepo.getObj(rs.getString("IdCtsp")));
                obj.setDonGia(rs.getDouble("DonGia"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    // Thêm một bản ghi "HoaDonChiTiet"
    public Boolean add(HoaDonChiTiet obj) {

        String sql = "INSERT INTO dbo.HOADONCHITIET(IdHd,IdCtsp,DonGia,SoLuong,NgayTao,NgaySua,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?,?,?)";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getHoaDon().getId());
            ps.setInt(2, obj.getChiTietSanPham().getId());
            ps.setDouble(3, obj.getDonGia());
            ps.setInt(4, obj.getSoLuong());
            ps.setDate(5, new java.sql.Date(obj.getNgayTao().getTime()));
            ps.setDate(6, new java.sql.Date(obj.getNgaySua().getTime()));
            ps.setInt(7, obj.getTinhTrang());
            ps.setBoolean(8, obj.getXoaMem());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Cập nhật thông tin của một bản ghi "HoaDonChiTiet".
    public Boolean update(Integer id, HoaDonChiTiet obj) {

        String sql = "UPDATE dbo.HOADONCHITIET SET IdHd = ? ,IdCtsp = ? ,DonGia = ? ,SoLuong = ? ,NgayTao = ? ,NgaySua = ? ,TinhTrang = ? ,XoaMem = ?  WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, obj.getHoaDon().getId());
            ps.setInt(2, obj.getChiTietSanPham().getId());
            ps.setDouble(3, obj.getDonGia());
            ps.setInt(4, obj.getSoLuong());
            ps.setDate(5, new java.sql.Date(obj.getNgayTao().getTime()));
            ps.setDate(6, new java.sql.Date(obj.getNgaySua().getTime()));
            ps.setInt(7, obj.getTinhTrang());
            ps.setBoolean(8, obj.getXoaMem());
            ps.setInt(9, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Xoa mem
    public Boolean delete(Integer id) {

        String sql = "UPDATE dbo.HOADONCHITIET SET XoaMem = 1 WHERE Id = ?";
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

        String sql = "DELETE dbo.HOADONCHITIET WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
