/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ChiTietSanPham;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.NguoiDung;
import Repositorries.ChiTietSanPhamRepository;
import Repositorries.HoaDonChiTietRepository;
import Repositorries.HoaDonRepository;
import Utilities.ExportPDF;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BanHangPanel extends javax.swing.JPanel {

    DefaultTableModel tblModelHoaDon;
    DefaultTableModel tblModelHoaDonTT;
    DefaultTableModel tblModelGioHang;
    DefaultTableModel tblModelSanPham;

    HoaDonRepository hdRepo;
    HoaDonChiTietRepository hdctRepo;
    ChiTietSanPhamRepository ctspRepo;

    List<HoaDon> lstHoaDon = null;
    List<HoaDon> lstHoaDonTT = null;
    List<HoaDonChiTiet> lstHoaDonChiTiet = null;
    List<ChiTietSanPham> lstSanPham = null;

    HoaDon hoaDon = null;
    NguoiDung nguoiDung = null;
    KhachHang khachHang = null;
    
    public BanHangPanel() {
        initComponents();
        hdRepo = new HoaDonRepository();
        hdctRepo = new HoaDonChiTietRepository();
        ctspRepo = new ChiTietSanPhamRepository();
        this.nguoiDung = HomeFrm.getNguoiDung();

        initTable();

        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang("", 0));
        loadTableHoaDonTT(hdRepo.getAllByMaAndTinhTrang("", 1));
        loadTableSanPham(ctspRepo.getAllByTenAndTinhTrang("", 0));

        loadFormKhachHang(khachHang);

    }
    
    // Khởi tạo các table model 
    public void initTable() {
        tblModelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHoaDonTT = (DefaultTableModel) tblHoaDonTT.getModel();
        tblModelSanPham = (DefaultTableModel) tblSanPham.getModel();
        tblModelGioHang = (DefaultTableModel) tblGioHang.getModel();

    }
    //Load table hóa đơn truyền vào 1 danh sách hóa đơn
    public void loadTableHoaDon(List<HoaDon> lst) {
        int i = 0;
        tblModelHoaDon.setRowCount(0);
        this.lstHoaDon = lst;
        for (HoaDon hd : lst) {
            tblModelHoaDon.addRow(new Object[]{
                ++i,
                hd.getMa(),
                hd.getNguoiDung() == null ? "Nhân Viên Ảo" : hd.getNguoiDung().getTen(),
                hd.getKhachHang() == null ? "Khách Lẻ" : hd.getKhachHang().getTen(),
                hd.getNgayTao()
            });
        }
    }
    //Load table hóa đơn truyền vào 1 danh sách hóa đơn chi tiết
    public void loadTableHoaDonTT(List<HoaDon> lst) {
        int i = 0;
        tblModelHoaDonTT.setRowCount(0);
        this.lstHoaDonTT = lst;
        for (HoaDon hd : lst) {
            tblModelHoaDonTT.addRow(new Object[]{
                ++i,
                hd.getMa(),
                hd.getNguoiDung() == null ? "Nhân Viên Ảo" : hd.getNguoiDung().getTen(),
                hd.getKhachHang() == null ? "Khách Lẻ" : hd.getKhachHang().getTen(),
                hd.getNgayTao()
            });
        }
    }
    //Load table sản phẩm truyền vào 1 danh sách chi tiết sản phẩm
    public void loadTableSanPham(List<ChiTietSanPham> lst) {
        int i = 0;
        tblModelSanPham.setRowCount(0);
        this.lstSanPham = lst;
        for (ChiTietSanPham ctsp : lst) {
            tblModelSanPham.addRow(new Object[]{
                ++i,
                ctsp.getMa(),
                ctsp.getSanPham().getTen(),
                ctsp.getMauSac().getTen(),
                ctsp.getKichThuoc().getTen(),
                ctsp.getSanPham().getThuongHieu().getTen(),
                ctsp.getSanPham().getXuatXu().getTen(),
                ctsp.getChatLieu().getTen(),
                ctsp.getGiaBan(),
                ctsp.getSoLuong()
            });
        }
    }
 //Load table giỏ hàng truyền vào 1 danh sách giỏ hàng
    public void loadTableGioHang(List<HoaDonChiTiet> lst) {
        int i = 0;
        tblModelGioHang.setRowCount(0);
        this.lstHoaDonChiTiet = lst;
        for (HoaDonChiTiet hdct : lst) {
            tblModelGioHang.addRow(new Object[]{
                ++i,
                hdct.getChiTietSanPham().getSanPham().getMa(),
                hdct.getChiTietSanPham().getSanPham().getTen(),
                hdct.getSoLuong(),
                hdct.getDonGia(),
                hdct.getSoLuong() * hdct.getDonGia()
            });
        }
        loadFormHoaDon(this.hoaDon, lst);
    }
    
    //click table hóa đơn sẽ lấy thứ tự trong danh sách hóa đơn và view sản phẩm đang có gtrong giỏ hàng và thông tin hóa đươn đó
    public void clickHoaDon() {
        int index = tblHoaDon.getSelectedRow();
        this.hoaDon = lstHoaDon.get(index);
        List<HoaDonChiTiet> lst = hdctRepo.getAllByHoaDon(String.valueOf(this.hoaDon.getId()));
        loadTableGioHang(lst);
        loadFormKhachHang(this.hoaDon.getKhachHang());

    }

    //xóa hơn đơn và trả sản phẩm có trong giờ hàng về ban đầu 
    public void huyHoaDon() {
        int index = tblHoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Hủy Hóa Đơn ?");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        this.hoaDon = lstHoaDon.get(index);
        this.hoaDon.setTinhTrang(2);
        hdRepo.update(this.hoaDon.getId(), hoaDon);
        deleteAllGioHang();
        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang("", 0));

    }

    //click table sản phẩm mở modal nhập số lượng và thêm sản phẩm vào giờ hàng
    public void clickSanPham() {
        Boolean isSanPham = false;
        if (this.hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hóa Đơn");
            return;
        }
        int index = tblSanPham.getSelectedRow();
        ChiTietSanPham ctsp = this.lstSanPham.get(index);
        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Vui Lòng Nhập Số Lượng"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Số");
            return;
        }
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Số Lon Hon 0");
            return;
        }
        if (soLuong > ctsp.getSoLuong()) {
            JOptionPane.showMessageDialog(this, "Số Lượng Không Khả Dụng");
            return;
        }
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        for (HoaDonChiTiet hd : lstHoaDonChiTiet) {
            if (hd.getChiTietSanPham().getId() == ctsp.getId()) {
                isSanPham = true;
                hdct = hd;
            }
        }
        if (isSanPham) {
            hdct.setSoLuong(hdct.getSoLuong() + soLuong);
            hdct.setDonGia(ctsp.getGiaBan());
            hdctRepo.update(hdct.getId(), hdct);
        } else {
            hdct.setHoaDon(this.hoaDon);
            hdct.setChiTietSanPham(ctsp);
            hdct.setDonGia(ctsp.getGiaBan());
            hdct.setSoLuong(soLuong);
            hdctRepo.add(hdct);
        }
        ctsp.setSoLuong(ctsp.getSoLuong() - soLuong);
        ctspRepo.update(ctsp.getId(), ctsp);
        loadTableGioHang(hdctRepo.getAllByHoaDon(String.valueOf(this.hoaDon.getId())));
        loadTableSanPham(ctspRepo.getAllByTenAndTinhTrang(txtTimKiemSanPham.getText(), 0));
    }

    //click chọn sản phẩm trong giỏ hàng và mở dailog thay đổi số lượng nhập 0 để xóa sản phẩm
    public void clickGioHang() {

        int index = tblGioHang.getSelectedRow();

        HoaDonChiTiet hdct = this.lstHoaDonChiTiet.get(index);
        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Vui Lòng Nhập Số Lượng"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Số");
            return;
        }
        if (soLuong < 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Số Lon Hon 0");
            return;
        }
        ChiTietSanPham ctsp = hdct.getChiTietSanPham();

        if (soLuong <= 0) {
            ctsp.setSoLuong(ctsp.getSoLuong() + hdct.getSoLuong());
            hdctRepo.deletePhy(hdct.getId());

        } else {
            if (soLuong >= ctsp.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số Lượng Không Đủ");
                return;
            }
            ctsp.setSoLuong(ctsp.getSoLuong() + hdct.getSoLuong() - soLuong);
            hdct.setSoLuong(soLuong);
            hdctRepo.update(hdct.getId(), hdct);
        }
        ctspRepo.update(ctsp.getId(), ctsp);
        List<HoaDonChiTiet> lstHdct = hdctRepo.getAllByHoaDon(String.valueOf(this.hoaDon.getId()));
        loadTableGioHang(lstHdct);
        loadTableSanPham(ctspRepo.getAllByTenAndTinhTrang(txtTimKiemSanPham.getText(), 0));

    }
    //xóa tất cả sản phẩm có trong giỏ hàng
    public void deleteAllGioHang() {
        for (HoaDonChiTiet hdct : this.lstHoaDonChiTiet) {
            ChiTietSanPham ctsp = hdct.getChiTietSanPham();
            ctsp.setSoLuong(ctsp.getSoLuong() + hdct.getSoLuong());
            ctspRepo.update(ctsp.getId(), ctsp);
            hdctRepo.deletePhy(hdct.getId());
        }
        loadTableGioHang(hdctRepo.getAllByHoaDon(String.valueOf(this.hoaDon.getId())));
        loadTableSanPham(ctspRepo.getAllByTenAndTinhTrang(txtTimKiemSanPham.getText(), 0));

    }
    
    //Tạo hóa đơn max là 5 cái
    public void taoHoaDon() {
        HoaDon hd = new HoaDon();
        String maHd = "HD" + Math.random();
        if (lstHoaDon.size() >= 5) {
            JOptionPane.showMessageDialog(this, "Chỉ có thể tạo tối đa 5 hóa đơn");
            return;
        }
        hd.setMa("HD" + (Integer.valueOf(hdRepo.getIdMax()) + 1));

        if (this.nguoiDung != null) {
            hd.setNguoiDung(this.nguoiDung);
        }

        if (this.khachHang != null) {
            hd.setKhachHang(this.khachHang);
        }
        hdRepo.add(hd);
        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang("", 0));

    }

    public void loadFormKhachHang(KhachHang kh) {

        txtMaKh.setText(kh == null ? "Khách Lẻ" : kh.getMa());
        txtTenKh.setText(kh == null ? "Khách Lẻ" : kh.getTen());
    }

    public void loadFormHoaDon(HoaDon hd, List<HoaDonChiTiet> lst) {
        txtMahd.setText(hd.getMa());
        txtTenNv.setText(hd.getNguoiDung() == null ? "Nhân Viên Ảo" : hd.getNguoiDung().getTen());

        Double tongTien = 0.0;
        for (HoaDonChiTiet hdct : lst) {
            tongTien = tongTien + (hdct.getDonGia() * hdct.getSoLuong());
        }
        txtTongTien.setText(String.valueOf(tongTien));

    }
    // tự động tính tiền cho khách
    public void tienThua() {
        if (this.hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
            return;
        }
        if (txtTienKhachCk.getText().trim().length() == 0 && txtTienKhachDua.getText().trim().length() == 0) {
            return;
        }

        Double tongTien = Double.valueOf(txtTongTien.getText());
        Double tienKhachDua = 0.0;
        Double tienChuyenKhoan = 0.0;
        Double tienGiamGia = 0.0;
        if (txtTienKhachDua.getText().trim().length() != 0) {
            try {
                tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tiền Khách Đưa Là Sô");
                txtTienKhachDua.setText("");
                txtTraLai.setText("");
                return;
            }
            if (tienKhachDua < 0) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tiền Khách Đưa Lớn Hơn 0");
                txtTienKhachDua.setText("");
                txtTraLai.setText("");
                return;
            }
        }
        if (txtTienKhachCk.getText().trim().length() != 0) {
            try {
                tienChuyenKhoan = Double.valueOf(txtTienKhachCk.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tiền Khách CK Là Sô");
                txtTienKhachCk.setText("");
                txtTraLai.setText("");
                return;
            }

            if (tienChuyenKhoan < 0) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tiền Khách CK Lớn Hơn 0");
                txtTienKhachCk.setText("");
                txtTraLai.setText("");
                return;
            }
        }

        Double tien = tienKhachDua + tienChuyenKhoan;
        txtTraLai.setText(tien - tongTien + "");

    }
    
    //thanh toán hóa đơn
    public void thanhToan() {
        if (this.hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hóa Đơn");
            return;
        }

        if (Double.valueOf(txtTongTien.getText()) == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Có Gì Để Thanh Toán");
            txtTienKhachCk.setText("");
            txtTienKhachDua.setText("");
            txtTraLai.setText("");
            return;
        }

        if (Double.valueOf(txtTraLai.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Đủ Tiền Thanh Toán");
            return;
        }

        int chkTT = JOptionPane.showConfirmDialog(this, "Xác Nhận Thanh Toán ?");
        if (chkTT != JOptionPane.YES_OPTION) {
            return;
        }

        this.hoaDon.setTinhTrang(1);
        this.hoaDon.setTongTien(Double.valueOf(txtTongTien.getText()));
        this.hoaDon.setNgayThanhToan(new Date());
        if (txtTienKhachDua.getText().trim().length() != 0) {
            this.hoaDon.setTienMat(Double.valueOf(txtTienKhachDua.getText()));
        }
        if (txtTienKhachCk.getText().trim().length() != 0) {
            this.hoaDon.setTienChuyenKhoan(Double.valueOf(txtTienKhachCk.getText()));
        }
        this.hoaDon.setHinhThuc(cboHinhThuc.getSelectedItem().toString());

        hdRepo.update(this.hoaDon.getId(), this.hoaDon);

        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang(txtTimKiemHoaDon.getText(), 0));
        loadTableHoaDonTT(hdRepo.getAllByMaAndTinhTrang(txtTimKiemHoaDonTT.getText(), 1));
        JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");

        int chk = JOptionPane.showConfirmDialog(this, "In Hóa Đơn ?");
        if (chk == JOptionPane.YES_OPTION) {
            try {
                ExportPDF.ExportPDF(this.hoaDon);
                JOptionPane.showMessageDialog(this, "In Thành Công");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "In Thất Bại");
            }

        }

        clearForm();
    }
    
    //reser form trên màn bán hàng về ban đầu 
    public void clearForm() {
        this.hoaDon = null;
        this.khachHang = null;
        lstHoaDonChiTiet = null;
        tblModelGioHang.setRowCount(0);
        txtMahd.setText("");
        txtTenNv.setText("");

        txtTongTien.setText("");
        txtTienKhachDua.setText("");
        txtTraLai.setText("");
        cboHinhThuc.setSelectedIndex(0);
        txtTienKhachCk.setText("");
        txtTienKhachDua.setText("");
        txtTienKhachCk.setEnabled(false);

        txtMaKh.setText("Khách Lẻ");
        txtTenKh.setText("Khách Lẻ");
//        txtMaKm.setText("");
//        txtTenKm.setText("");
    }

    //chọn khách hàng 
    public void chonKhachHang() {
        KhachHangDialog khDialog = new KhachHangDialog(null, true);
        khDialog.setVisible(true);
        this.khachHang = khDialog.getKhachHang();
        if (this.khachHang != null) {

            txtMaKh.setText(this.khachHang.getMa());
            txtTenKh.setText(this.khachHang.getTen());

            if (this.hoaDon != null) {

                if (this.nguoiDung != null) {
                    this.hoaDon.setNguoiDung(this.nguoiDung);
                }
                if (this.khachHang != null) {
                    this.hoaDon.setKhachHang(this.khachHang);
                }

                hdRepo.update(this.hoaDon.getId(), this.hoaDon);
                loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang(txtTimKiemHoaDon.getText(), 0));
            }

        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDonTT = new javax.swing.JTable();
        txtTimKiemHoaDonTT = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtMahd = new javax.swing.JTextField();
        txtTenNv = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtTraLai = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboHinhThuc = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtTienKhachCk = new javax.swing.JTextField();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton4.setText("Tạo HD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HD", "Nhân Viên", "Khách Hàng", "Ngày Tạo"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chờ Thanh Toán", jPanel5);

        tblHoaDonTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HD", "Nhân Viên", "Khách Hàng", "Ngày Tạo"
            }
        ));
        jScrollPane4.setViewportView(tblHoaDonTT);

        txtTimKiemHoaDonTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHoaDonTTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtTimKiemHoaDonTT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiemHoaDonTT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đã Thanh Toán", jPanel6);

        jButton5.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\cancel.png")); // NOI18N
        jButton5.setText("Hủy");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtTimKiemHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHoaDonKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\loupe.png")); // NOI18N
        jLabel6.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\remove-from-cart.png")); // NOI18N
        jButton1.setText("Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên", "Màu sắc ", "Kích Thước", "Thương Hiệu", "Xuất Xứ", "Chất Liệu", "Giá bán", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        txtTimKiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSanPhamActionPerformed(evt);
            }
        });
        txtTimKiemSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSanPhamKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\loupe.png")); // NOI18N
        jLabel9.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Mã KH:");

        txtMaKh.setEditable(false);

        jLabel2.setText("Tên KH: ");

        txtTenKh.setEditable(false);

        jButton6.setText("KH Lẻ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\check.png")); // NOI18N
        jButton7.setText("Chọn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(txtMaKh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Mã HĐ: ");

        jLabel4.setText("Tên NV: ");

        jLabel7.setText("Tổng tiền: ");

        jLabel11.setText("Tiền trả lại: ");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pay.png")); // NOI18N
        jButton3.setText("Thanh toán");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtMahd.setEditable(false);

        txtTenNv.setEditable(false);
        txtTenNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNvActionPerformed(evt);
            }
        });

        txtTongTien.setEditable(false);

        txtTraLai.setEditable(false);

        jLabel12.setText("Tiền khách đưa:");

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel5.setText("Hình Thức TT:");

        cboHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt ", "Chuyển Khoản ", "Tiền Mặt & Chuyển Khoản" }));
        cboHinhThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucActionPerformed(evt);
            }
        });

        jLabel8.setText("Tiền Khach Ck:");

        txtTienKhachCk.setEnabled(false);
        txtTienKhachCk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachCkKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNv)
                            .addComponent(txtMahd)
                            .addComponent(txtTongTien)
                            .addComponent(cboHinhThuc, 0, 205, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDua)
                            .addComponent(txtTienKhachCk)
                            .addComponent(txtTraLai))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cboHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTienKhachCk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(229, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonKeyReleased
        // Lọc danh sách hóa đơn dựa trên từ khóa tìm kiếm
        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang(txtTimKiemHoaDon.getText(), 0));

    }//GEN-LAST:event_txtTimKiemHoaDonKeyReleased

    private void txtTimKiemHoaDonTTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonTTKeyReleased
        // Lọc danh sách sản phẩm dựa trên từ khóa tìm kiếm
        loadTableHoaDonTT(hdRepo.getAllByMaAndTinhTrang(txtTimKiemHoaDonTT.getText(), 1));
    }//GEN-LAST:event_txtTimKiemHoaDonTTKeyReleased

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        clickHoaDon();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        clickSanPham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        clickGioHang();
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteAllGioHang();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         // Tạo hóa đơn mới
        taoHoaDon();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Hủy hóa đơn
        huyHoaDon();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtTimKiemSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamKeyReleased
        loadTableSanPham(ctspRepo.getAllByTenAndTinhTrang(txtTimKiemSanPham.getText(), 0));
    }//GEN-LAST:event_txtTimKiemSanPhamKeyReleased

    private void txtTimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSanPhamActionPerformed

    private void txtTienKhachCkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachCkKeyReleased
        tienThua();
    }//GEN-LAST:event_txtTienKhachCkKeyReleased

    private void cboHinhThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucActionPerformed
        int index = cboHinhThuc.getSelectedIndex();
        
        // Nếu chọn hình thức thanh toán chuyển khoản
        if (index == 2) {
            txtTienKhachDua.setEnabled(true);
            txtTienKhachCk.setEnabled(true);
        
        // Nếu chọn hình thức thanh toán chuyển khoản
        } else if (index == 1) {
            txtTienKhachCk.setEnabled(true);
            txtTienKhachDua.setEnabled(false);
            
        // Nếu chọn hình thức thanh toán tiền mặt
        } else {
            txtTienKhachCk.setEnabled(false);
            txtTienKhachDua.setEnabled(true);
        }
        txtTraLai.setText("");
        txtTienKhachCk.setText("");
        txtTienKhachDua.setText("");
    }//GEN-LAST:event_cboHinhThucActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        tienThua();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        thanhToan();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Chọn khách hàng cho hóa đơn
        chonKhachHang();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //k hi nhấn nút hủy xóa thông tin khách hàng
        this.khachHang = null;
        loadFormKhachHang(khachHang);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void txtTenNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNvActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboHinhThuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonTT;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaKh;
    private javax.swing.JTextField txtMahd;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTenNv;
    private javax.swing.JTextField txtTienKhachCk;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTimKiemHoaDonTT;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTraLai;
    // End of variables declaration//GEN-END:variables
}
