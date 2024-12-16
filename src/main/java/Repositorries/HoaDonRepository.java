/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorries;

import Model.HoaDon;
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
public class HoaDonRepository {

    NguoiDungRepository ndRepo;
    KhachHangRepository khRepo;

    public HoaDonRepository() {
        ndRepo = new NguoiDungRepository();
        khRepo = new KhachHangRepository();
    }
    
     /*
     * Phương thức này truy vấn tất cả các hóa đơn từ cơ sở dữ liệu và trả về dưới dạng danh sách.
     * Dữ liệu được sắp xếp theo Id giảm dần.
     *
     * @return List<HoaDon> danh sách tất cả các hóa đơn
     */
    public List<HoaDon> getAll() {
        List<HoaDon> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, IdND, IdKH, TongTien, ThanhTien,TienMat, TienChuyenKhoan, MaChuyenKhoan, HinhThucTT, NgayThanhToan, NgayTao, NgaySua, MoTa, TinhTrang FROM dbo.HOADON ORDER BY Id DESC";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon obj = new HoaDon();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setNguoiDung(ndRepo.getObj(rs.getString("IdND")));
                obj.setKhachHang(khRepo.getObj(rs.getString("IdKH")));
                obj.setTongTien(rs.getDouble("TongTien"));
                obj.setThanhTien(rs.getDouble("ThanhTien"));
                obj.setTienMat(rs.getDouble("TienMat"));
                obj.setTienChuyenKhoan(rs.getDouble("TienChuyenKhoan"));
                obj.setMaChuyenKhoan(rs.getString("MaChuyenKhoan"));
                obj.setHinhThuc(rs.getString("HinhThucTT"));
                obj.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    /*
     * Phương thức này truy vấn các hóa đơn theo mã hóa đơn (Ma), trả về danh sách hóa đơn khớp với mã tìm kiếm.
     *
     * @param ma mã hóa đơn cần tìm kiếm
     * @return List<HoaDon> danh sách các hóa đơn tìm được
     */
    public List<HoaDon> getAllByMa(String ma) {
        List<HoaDon> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, IdND, IdKH, TongTien, ThanhTien,TienMat, TienChuyenKhoan, MaChuyenKhoan, HinhThucTT, NgayThanhToan, NgayTao, NgaySua, MoTa, TinhTrang FROM dbo.HOADON WHERE Ma LIKE ?  ORDER BY Id DESC";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon obj = new HoaDon();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setNguoiDung(ndRepo.getObj(rs.getString("IdND")));
                obj.setKhachHang(khRepo.getObj(rs.getString("IdKH")));
                obj.setTongTien(rs.getDouble("TongTien"));
                obj.setThanhTien(rs.getDouble("ThanhTien"));
                obj.setTienMat(rs.getDouble("TienMat"));
                obj.setTienChuyenKhoan(rs.getDouble("TienChuyenKhoan"));
                obj.setMaChuyenKhoan(rs.getString("MaChuyenKhoan"));
                obj.setHinhThuc(rs.getString("HinhThucTT"));
                obj.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    /*
     * Phương thức này truy vấn các hóa đơn theo mã hóa đơn và tình trạng (TinhTrang), trả về danh sách hóa đơn khớp với cả hai tiêu chí.
     *
     * @param ma mã hóa đơn cần tìm kiếm
     * @param tinhTrang tình trạng của hóa đơn
     * @return List<HoaDon> danh sách các hóa đơn tìm được
     */
    public List<HoaDon> getAllByMaAndTinhTrang(String ma, Integer tinhTrang) {
        List<HoaDon> lst = new ArrayList<>();
        String sql = "SELECT Id, Ma, IdND, IdKH, TongTien, ThanhTien,TienMat, TienChuyenKhoan, MaChuyenKhoan, HinhThucTT, NgayThanhToan, NgayTao, NgaySua, MoTa, TinhTrang FROM dbo.HOADON WHERE Ma LIKE ? AND TinhTrang = ?  ORDER BY Id DESC";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ma + "%");
            ps.setInt(2, tinhTrang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon obj = new HoaDon();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setNguoiDung(ndRepo.getObj(rs.getString("IdND")));
                obj.setKhachHang(khRepo.getObj(rs.getString("IdKH")));
                obj.setTongTien(rs.getDouble("TongTien"));
                obj.setThanhTien(rs.getDouble("ThanhTien"));
                obj.setTienMat(rs.getDouble("TienMat"));
                obj.setTienChuyenKhoan(rs.getDouble("TienChuyenKhoan"));
                obj.setMaChuyenKhoan(rs.getString("MaChuyenKhoan"));
                obj.setHinhThuc(rs.getString("HinhThucTT"));
                obj.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

                lst.add(obj);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    // Lấy thông tin hóa đơn theo ID
    public HoaDon getObj(String id) {
        HoaDon obj = null;
        String sql = "SELECT Id, Ma, IdND, IdKH, TongTien, ThanhTien,TienMat, TienChuyenKhoan, MaChuyenKhoan, HinhThucTT, NgayThanhToan, NgayTao, NgaySua, MoTa, TinhTrang FROM dbo.HOADON where id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj = new HoaDon();
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setNguoiDung(ndRepo.getObj(rs.getString("IdND")));
                obj.setKhachHang(khRepo.getObj(rs.getString("IdKH")));
                obj.setTongTien(rs.getDouble("TongTien"));
                obj.setThanhTien(rs.getDouble("ThanhTien"));
                obj.setTienMat(rs.getDouble("TienMat"));
                obj.setTienChuyenKhoan(rs.getDouble("TienChuyenKhoan"));
                obj.setMaChuyenKhoan(rs.getString("MaChuyenKhoan"));
                obj.setHinhThuc(rs.getString("HinhThucTT"));
                obj.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    // Lấy ID lớn nhất trong bảng hóa đơn
    public String getIdMax() {

        String sql = "SELECT MAX(Id) AS MaxId FROM dbo.HOADON";
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
        return String.valueOf((int) Math.floor(Math.random() + 100) + 1);
    }
    
    // Lấy thông tin hóa đơn theo mã hóa đơn
    public HoaDon getObjByMa(String ma) {
        HoaDon obj = new HoaDon();
        String sql = "SELECT Id, Ma, IdND, IdKH, TongTien, ThanhTien, NgayThanhToan, NgayTao, NgaySua, MoTa, TinhTrang FROM dbo.HOADON where Ma = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setId(rs.getInt("Id"));
                obj.setMa(rs.getString("Ma"));
                obj.setNguoiDung(ndRepo.getObj(rs.getString("IdND")));
                obj.setKhachHang(khRepo.getObj(rs.getString("IdKH")));
                obj.setTongTien(rs.getDouble("TongTien"));
                obj.setThanhTien(rs.getDouble("ThanhTien"));
                obj.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgaySua(rs.getDate("NgaySua"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setTinhTrang(rs.getInt("TinhTrang"));

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    // Thêm một hóa đơn mới vào cơ sở dữ liệu
    public Boolean add(HoaDon obj) {

        String sql = "INSERT INTO dbo.HOADON(Ma,IdND,IdKH,TongTien,ThanhTien,NgayThanhToan,NgayTao,NgaySua,MoTa,TinhTrang,XoaMem)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa()); // Gán giá trị mã hóa đơn
            
            // Gán các giá trị còn lại cho các trường trong cơ sở dữ liệu
            if (obj.getNguoiDung() != null) {
                ps.setInt(2, obj.getNguoiDung().getId()); 
            } else {
                ps.setNull(2, java.sql.Types.INTEGER); // Nếu không có người dùng, set null
            }
            if (obj.getKhachHang() != null) {
                ps.setInt(3, obj.getKhachHang().getId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER); // Nếu không có khách hàng, set null
            }
            if (obj.getTongTien() != null) {
                ps.setDouble(4, obj.getTongTien());
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE); // Nếu không có tổng tiền, set null
            }

            if (obj.getThanhTien() != null) {
                ps.setDouble(5, obj.getThanhTien());
            } else {
                ps.setNull(5, java.sql.Types.DOUBLE); // Nếu không có thanh tiền, set null
            }

            if (obj.getNgayThanhToan() != null) {
                ps.setDate(6, new java.sql.Date(obj.getNgayThanhToan().getTime()));
            } else {

                ps.setNull(6, java.sql.Types.DATE);
            }
            ps.setDate(7, new java.sql.Date(obj.getNgayTao().getTime()));
            ps.setDate(8, new java.sql.Date(obj.getNgaySua().getTime()));
            ps.setString(9, obj.getMoTa());
            ps.setInt(10, obj.getTinhTrang());
            ps.setBoolean(11, obj.getXoaMem());

            return ps.executeUpdate() > 0; // Nếu có ít nhất 1 dòng được cập nhật, trả về true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Cập nhật thông tin hóa đơn
    public Boolean update(Integer id, HoaDon obj) {

        String sql = "UPDATE dbo.HOADON\n"
                + "SET Ma= ? ,IdND= ? ,IdKH= ? ,TongTien= ? ,ThanhTien= ? ,TienMat = ?, TienChuyenKhoan = ?, MaChuyenKhoan = ?, HinhThucTT = ?, NgayThanhToan= ? ,NgaySua= ? ,MoTa= ? ,TinhTrang = ? WHERE Id = ?";
        try ( Connection conn = DbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getMa());

            if (obj.getNguoiDung() != null) {
                ps.setInt(2, obj.getNguoiDung().getId());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            if (obj.getKhachHang() != null) {
                ps.setInt(3, obj.getKhachHang().getId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            if (obj.getTongTien() != null) {
                ps.setDouble(4, obj.getTongTien());
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
            }

            if (obj.getThanhTien() != null) {
                ps.setDouble(5, obj.getThanhTien());
            } else {
                ps.setNull(5, java.sql.Types.DOUBLE);
            }
            
            if (obj.getTienMat() != null) {
                ps.setDouble(6, obj.getThanhTien());
            } else {
                ps.setNull(6, java.sql.Types.DOUBLE);
            }

            
            if (obj.getTienChuyenKhoan()!= null) {
                ps.setDouble(7, obj.getThanhTien());
            } else {
                ps.setNull(7, java.sql.Types.DOUBLE);
            }
            ps.setString(8, obj.getMaChuyenKhoan());
            ps.setString(9, obj.getHinhThuc());


            if (obj.getNgayThanhToan() != null) {
                ps.setDate(10, new java.sql.Date(obj.getNgayThanhToan().getTime()));
            } else {

                ps.setNull(10, java.sql.Types.DATE);
            }
            ps.setDate(11, new java.sql.Date(new Date().getTime())); // Lấy ngày sửa đổi
            ps.setString(12, obj.getMoTa()); 
            ps.setInt(13, obj.getTinhTrang());
            ps.setInt(14, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Xoa mem
    public Boolean delete(Integer id) {

        String sql = "UPDATE dbo.HOADON SET XoaMem = 1 WHERE Id = ?";
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

        String sql = "DELETE dbo.HOADON WHERE Id = ?";
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
