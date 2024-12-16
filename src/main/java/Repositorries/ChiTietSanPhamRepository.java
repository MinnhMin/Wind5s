/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.ChiTietSanPham;
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
public class ChiTietSanPhamRepository {

    // Khai báo các repository
    SanPhamRepository spRepo;
    MauSacRepository msRepo;
    KichThuocRepository ktRepo;
    ChatLieuRepository clRepo;

    // Constructor khởi tạo các repository con
    public ChiTietSanPhamRepository() {
        spRepo = new SanPhamRepository();
        msRepo = new MauSacRepository();
        ktRepo = new KichThuocRepository();
        clRepo = new ChatLieuRepository();
    }

    // Hàm lấy tất cả ChiTietSanPham
    public List<ChiTietSanPham> getAll() {
        List<ChiTietSanPham> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, IdSanPham, IdMauSac, IdKichThuoc, IdChatLieu, MoTa, SoLuong, GiaNhap, GiaBan, NgayTao,NgaySua,TinhTrang ,XoaMem FROM dbo.CHITIETSANPHAM WHERE XoaMem = 0";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham obj = new ChiTietSanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setSanPham(spRepo.getObj(rs.getString("IdSanPham")));
                obj.setMauSac(msRepo.getObj(rs.getString("IdMauSac")));
                obj.setKichThuoc(ktRepo.getObj(rs.getString("IdKichThuoc")));
                obj.setChatLieu(clRepo.getObj(rs.getString("IdChatLieu")));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setGiaNhap(rs.getDouble("GiaNhap"));
                obj.setGiaBan(rs.getDouble("GiaBan"));
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

    // Hàm lấy Id lớn nhất trong bảng
    public String getIdMax() {
        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.CHITIETSANPHAM";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Trả về Id lớn nhất hoặc -1 nếu không có
                return rs.getString(1) == null ? "-1" : rs.getString(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Nếu không lấy được Id, trả về một giá trị ngẫu nhiên
        return Math.floor(Math.random() + 100) + "";
    }

    // Hàm lấy tất cả ChiTietSanPham theo tên sản phẩm
    public List<ChiTietSanPham> getAllByTen(String ten) {
        List<ChiTietSanPham> lst = new ArrayList<>();
        String sql = "SELECT dbo.CHITIETSANPHAM.Id, dbo.SANPHAM.Ma, IdSanPham, IdMauSac, IdKichThuoc,IdChatLieu, dbo.CHITIETSANPHAM.MoTa, SoLuong, GiaNhap, GiaBan, dbo.CHITIETSANPHAM.NgayTao, dbo.CHITIETSANPHAM.NgaySua,dbo.CHITIETSANPHAM.TinhTrang, dbo.CHITIETSANPHAM.XoaMem FROM dbo.CHITIETSANPHAM JOIN dbo.SANPHAM ON SANPHAM.Id = CHITIETSANPHAM.IdSanPham WHERE dbo.CHITIETSANPHAM.XoaMem = 0  AND dbo.SANPHAM.Ten LIKE ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham obj = new ChiTietSanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setSanPham(spRepo.getObj(rs.getString("IdSanPham")));
                obj.setMauSac(msRepo.getObj(rs.getString("IdMauSac")));
                obj.setKichThuoc(ktRepo.getObj(rs.getString("IdKichThuoc")));
                obj.setChatLieu(clRepo.getObj(rs.getString("IdChatLieu")));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setGiaNhap(rs.getDouble("GiaNhap"));
                obj.setGiaBan(rs.getDouble("GiaBan"));
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
        return lst; // Trả về danh sách chi tiết sản phẩm theo tên
    }

    // Hàm lấy ChiTietSanPham theo tên và tình trạng
    public List<ChiTietSanPham> getAllByTenAndTinhTrang(String ten, Integer tinhTrang) {
        List<ChiTietSanPham> lst = new ArrayList<>();
        String sql = "SELECT dbo.CHITIETSANPHAM.Id, dbo.SANPHAM.Ma, IdSanPham, IdMauSac, IdKichThuoc, IdChatLieu, dbo.CHITIETSANPHAM.MoTa, SoLuong, GiaNhap, GiaBan, dbo.CHITIETSANPHAM.NgayTao, dbo.CHITIETSANPHAM.NgaySua,dbo.CHITIETSANPHAM.TinhTrang, dbo.CHITIETSANPHAM.XoaMem FROM dbo.CHITIETSANPHAM JOIN dbo.SANPHAM ON SANPHAM.Id = CHITIETSANPHAM.IdSanPham WHERE dbo.CHITIETSANPHAM.XoaMem = 0 AND CHITIETSANPHAM.TinhTrang = ? AND dbo.SANPHAM.Ten LIKE ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tinhTrang);
            ps.setString(2, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham obj = new ChiTietSanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setSanPham(spRepo.getObj(rs.getString("IdSanPham")));
                obj.setMauSac(msRepo.getObj(rs.getString("IdMauSac")));
                obj.setKichThuoc(ktRepo.getObj(rs.getString("IdKichThuoc")));
                obj.setChatLieu(clRepo.getObj(rs.getString("IdChatLieu")));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setGiaNhap(rs.getDouble("GiaNhap"));
                obj.setGiaBan(rs.getDouble("GiaBan"));
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
        return lst; // Trả về danh sách chi tiết sản phẩm theo tên và tình trạng
    }

    // Hàm lấy ChiTietSanPham theo Id
    public ChiTietSanPham getObj(String id) {
        ChiTietSanPham obj = null;
        String sql = "SELECT Id, Ma, IdSanPham, IdMauSac, IdKichThuoc, IdChatLieu, MoTa, SoLuong, GiaNhap, GiaBan, NgayTao,NgaySua,TinhTrang , XoaMem FROM dbo.CHITIETSANPHAM WHERE XoaMem = 0 AND Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new ChiTietSanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setSanPham(spRepo.getObj(rs.getString("IdSanPham")));
                obj.setMauSac(msRepo.getObj(rs.getString("IdMauSac")));
                obj.setKichThuoc(ktRepo.getObj(rs.getString("IdKichThuoc")));
                obj.setChatLieu(clRepo.getObj(rs.getString("IdChatLieu")));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setGiaNhap(rs.getDouble("GiaNhap"));
                obj.setGiaBan(rs.getDouble("GiaBan"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj; // Trả về đối tượng ChiTietSanPham
    }

    // Hàm lấy ChiTietSanPham theo mã
    public ChiTietSanPham getObjByMa(String ma) {
        ChiTietSanPham obj = null;
        String sql = "SELECT Id, Ma, IdSanPham, IdMauSac, IdKichThuoc, IdChatLieu, MoTa, SoLuong, GiaNhap, GiaBan, NgayTao,NgaySua,TinhTrang , XoaMem FROM dbo.CHITIETSANPHAM WHERE XoaMem = 0 AND Ma = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new ChiTietSanPham();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setSanPham(spRepo.getObj(rs.getString("IdSanPham")));
                obj.setMauSac(msRepo.getObj(rs.getString("IdMauSac")));
                obj.setKichThuoc(ktRepo.getObj(rs.getString("IdKichThuoc")));
                obj.setChatLieu(clRepo.getObj(rs.getString("IdChatLieu")));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setSoLuong(rs.getInt("SoLuong"));
                obj.setGiaNhap(rs.getDouble("GiaNhap"));
                obj.setGiaBan(rs.getDouble("GiaBan"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));
                obj.setXoaMem(rs.getBoolean("XoaMem"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj; // Trả về đối tượng ChiTietSanPham
    }

    // Hàm thêm ChiTietSanPham mới
    public Boolean add(ChiTietSanPham obj) {

        String sql = "INSERT INTO dbo.CHITIETSANPHAM(Ma,IdSanPham,IdMauSac,IdKichThuoc, IdChatLieu,MoTa,SoLuong,GiaNhap,GiaBan,NgayTao,NgaySua,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setInt(2, obj.getSanPham().getId());
            ps.setInt(3, obj.getMauSac().getId());
            ps.setInt(4, obj.getKichThuoc().getId());
            ps.setInt(5, obj.getChatLieu().getId());
            ps.setString(6, obj.getMoTa());
            ps.setInt(7, obj.getSoLuong());
            ps.setDouble(8, obj.getGiaNhap());
            ps.setDouble(9, obj.getGiaBan());
            ps.setDate(10, new java.sql.Date(new Date().getTime()));
            ps.setDate(11, new java.sql.Date(new Date().getTime()));
            ps.setInt(12, obj.getTinhTrang());
            ps.setBoolean(13, false);

            return ps.executeUpdate() > 0; // Kiểm tra việc thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu không thành công
    }

    // Hàm cập nhật ChiTietSanPham
    public Boolean update(Integer id, ChiTietSanPham obj) {

        String sql = "UPDATE dbo.CHITIETSANPHAM \n"
                + "SET Ma = ? ,IdSanPham = ? ,IdMauSac = ? ,IdKichThuoc = ? ,IdChatLieu = ? ,MoTa = ? ,SoLuong = ? ,GiaNhap = ? ,GiaBan = ? ,NgaySua = ? ,TinhTrang = ? ,XoaMem = ? \n"
                + " WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());
            ps.setInt(2, obj.getSanPham().getId());
            ps.setInt(3, obj.getMauSac().getId());
            ps.setInt(4, obj.getKichThuoc().getId());
            ps.setInt(5, obj.getChatLieu().getId());
            ps.setString(6, obj.getMoTa());
            ps.setInt(7, obj.getSoLuong());
            ps.setDouble(8, obj.getGiaNhap());
            ps.setDouble(9, obj.getGiaBan());
            ps.setDate(10, new java.sql.Date(obj.getNgaySua().getTime()));
            ps.setInt(11, obj.getTinhTrang());
            ps.setBoolean(12, obj.getXoaMem());
            ps.setInt(13, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoa mem
    public Boolean delete(Integer id) {

        String sql = "UPDATE dbo.CHITIETSANPHAM SET XoaMem = 1 WHERE Id = ?";
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

        String sql = "DELETE dbo.CHITIETSANPHAM WHERE Id = ?";
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm kiểm tra tính trùng lặp của ChiTietSanPham
    public Integer checkDuclicate(ChiTietSanPham ctsp, Integer id) {
        String sql;
        if (id != null) {
            sql = "SELECT Count(Id) FROM dbo.CHITIETSANPHAM WHERE XoaMem = 0  AND IdSanPham = ? AND IdMauSac = ? AND IdKichThuoc = ? AND IdChatLieu = ? And id != ?";
        } else {
            sql = "SELECT Count(Id) FROM dbo.CHITIETSANPHAM WHERE XoaMem = 0 AND IdSanPham = ? AND IdMauSac = ? AND IdKichThuoc = ? AND IdChatLieu = ? ";
        }
        Integer result = 0;
        try (Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ctsp.getSanPham().getId());
            ps.setInt(2, ctsp.getMauSac().getId());
            ps.setInt(3, ctsp.getKichThuoc().getId());
            ps.setInt(4, ctsp.getChatLieu().getId());

            if (id != null) {
                ps.setInt(5, id);
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
