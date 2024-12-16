/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ChiTietSanPham;
import Model.ThuongHieu;
import Model.KichThuoc;
import Model.ChatLieu;
import Model.MauSac;
import Model.XuatXu;
import Model.SanPham;
import Repositorries.ChiTietSanPhamRepository;
import Repositorries.ThuongHieuRepository;
import Repositorries.KichThuocRepository;
import Repositorries.ChatLieuRepository;
import Repositorries.MauSacRepository;
import Repositorries.XuatXuRepository;
import Repositorries.SanPhamRepository;
import Utilities.ExportDT;
import Utilities.ExportSP;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class SanPhamPanel extends javax.swing.JPanel {

    int index = -1;
    int indexSp = -1;

    ChiTietSanPhamRepository ctspRepo;
    SanPhamRepository spRepo;
    MauSacRepository msRepo;
    KichThuocRepository ktRepo;
    ThuongHieuRepository thRepo;
    XuatXuRepository xxRepo;
    ChatLieuRepository clRepo;

    DefaultTableModel tblModelSp;
    DefaultTableModel tblModelSpql;

    List<ChiTietSanPham> lstChiTietSanPham;
    List<SanPham> lstSanPham;

    public SanPhamPanel() {
        initComponents();

        ctspRepo = new ChiTietSanPhamRepository();
        spRepo = new SanPhamRepository();
        msRepo = new MauSacRepository();
        ktRepo = new KichThuocRepository();
        thRepo = new ThuongHieuRepository();
        xxRepo = new XuatXuRepository();
        clRepo = new ChatLieuRepository();
        tblModelSp = (DefaultTableModel) tblSanPham.getModel();
        tblModelSpql = (DefaultTableModel) tblSanPhamQl.getModel();
        initCbo();
        loadTableSanPhamChiTiet(ctspRepo.getAll());
        loadTableSanPham(spRepo.getAll());
        txtMa.setText("SP0" + (Integer.valueOf(spRepo.getIdMax()) + 2));
    }

    public void initCbo() {
        initCboSanPham();
        initCboMauSac();
        initCboKichThuoc();
        initCboThuongHieu();
        initCboXuatXu();
        initCboChatLieu();
    }

    //load data cbo sản phẩm 
    public void initCboSanPham() {
        DefaultComboBoxModel<SanPham> boxModel = new DefaultComboBoxModel<>();
        cboSanPham.removeAllItems();
        cboSanPham.setModel((DefaultComboBoxModel) boxModel);
        for (SanPham sp : spRepo.getAll()) {
            boxModel.addElement(sp);
        }
    }
//load data cbo màu sắc

    public void initCboMauSac() {
        DefaultComboBoxModel<MauSac> boxModel = new DefaultComboBoxModel<>();
        cboMauSac.removeAllItems();
        cboMauSac.setModel((DefaultComboBoxModel) boxModel);
        for (MauSac ms : msRepo.getAll()) {
            boxModel.addElement(ms);
        }
    }
//load data cbo Kích thuócc

    public void initCboKichThuoc() {
        DefaultComboBoxModel<KichThuoc> boxModel = new DefaultComboBoxModel<>();
        cboKichThuoc.removeAllItems();
        cboKichThuoc.setModel((DefaultComboBoxModel) boxModel);
        for (KichThuoc otp : ktRepo.getAll()) {
            boxModel.addElement(otp);
        }
    }
//load data cbo thương hiệu

    public void initCboThuongHieu() {
        DefaultComboBoxModel<ThuongHieu> boxModel = new DefaultComboBoxModel<>();
        cboThuongHieu.removeAllItems();
        cboThuongHieu.setModel((DefaultComboBoxModel) boxModel);
        for (ThuongHieu otp : thRepo.getAll()) {
            boxModel.addElement(otp);
        }
    }
//load data cbo xuất xứ

    public void initCboXuatXu() {
        DefaultComboBoxModel<XuatXu> boxModel = new DefaultComboBoxModel<>();
        cboXuatXu.removeAllItems();
        cboXuatXu.setModel((DefaultComboBoxModel) boxModel);
        for (XuatXu otp : xxRepo.getAll()) {
            boxModel.addElement(otp);
        }
    }
//load data cbo chất liệu

    public void initCboChatLieu() {
        DefaultComboBoxModel<ChatLieu> boxModel = new DefaultComboBoxModel<>();
        cboChatLieu.removeAllItems();
        cboChatLieu.setModel((DefaultComboBoxModel) boxModel);
        for (ChatLieu otp : clRepo.getAll()) {
            boxModel.addElement(otp);
        }
    }
//load table sản phaarmrm chi tiết truyền vào 1 lst

    public void loadTableSanPhamChiTiet(List<ChiTietSanPham> lst) {
        int i = 0;
        tblModelSp.setRowCount(0);

        this.lstChiTietSanPham = lst;
        for (ChiTietSanPham ctsp : lst) {
            tblModelSp.addRow(new Object[]{
                ++i,
                ctsp.getMa(),
                ctsp.getSanPham(),
                ctsp.getMauSac(),
                ctsp.getKichThuoc(),
                ctsp.getSanPham().getThuongHieu(),
                ctsp.getSanPham().getXuatXu(),
                ctsp.getChatLieu(),
                ctsp.getGiaNhap(),
                ctsp.getGiaBan(),
                ctsp.getSoLuong(),
                ctsp.getMoTa(),
                ctsp.getNgayTao(),
                ctsp.getTinhTrang() == 0 ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh"
            });
        }
    }
//load table sản paharm tuyền vào 1 lst sản phẩm

    public void loadTableSanPham(List<SanPham> lst) {
        int i = 0;
        tblModelSpql.setRowCount(0);
        this.lstSanPham = lst;
        for (SanPham sp : lst) {
            tblModelSpql.addRow(new Object[]{
                ++i,
                sp.getMa(),
                sp.getTen(),
                sp.getThuongHieu(),
                sp.getXuatXu(),
                sp.getCountSpct() == null ? 0 : sp.getCountSpct(),
                sp.getMoTa(),
                sp.getTinhTrang() == 0 ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh",
                sp.getNgayTao(),
                sp.getNgaySua()
            });
        }
        initCboSanPham();
    }

    //click table sản phẩm chi tieswt và vieww lên form
    public void clickSanPhamChiTiet() {
        index = tblSanPham.getSelectedRow();

        String ma = tblModelSp.getValueAt(index, 1).toString();
//        ChiTietSanPham ctsp = ctspRepo.getObjByMa(ma);

        Object sanPham = tblModelSp.getValueAt(index, 2).toString();
        Object mauSac = tblModelSp.getValueAt(index, 3);
        Object khoiLuong = tblModelSp.getValueAt(index, 4);
//        Object thuongHieu = tblModelSp.getValueAt(index, 5);
//        Object xuatXu = tblModelSp.getValueAt(index, 6);
        Object chatLieu = tblModelSp.getValueAt(index, 7);
        String giaNhap = tblModelSp.getValueAt(index, 8).toString();
        String giaBan = tblModelSp.getValueAt(index, 9).toString();
        String soLuong = tblModelSp.getValueAt(index, 10).toString();
        String moTa = tblModelSp.getValueAt(index, 11).toString();
        String trangThai = tblModelSp.getValueAt(index, 13).toString();

        txtSoLuong.setText(soLuong);
        txtGiaNhap.setText(giaNhap);
        txtGiaBan.setText(giaBan);
        txtCongDung.setText(moTa);

        cboTrangThai.setSelectedItem(trangThai);
        int sanPhamCount = cboSanPham.getItemCount();
        for (int i = 0; i < sanPhamCount; i++) {
            Object l = cboSanPham.getItemAt(i);
            if (l.toString().equals(sanPham.toString())) {
                cboSanPham.setSelectedIndex(i);
                break;
            }
        }

        int mauSacCount = cboMauSac.getItemCount();
        for (int i = 0; i < mauSacCount; i++) {
            Object l = cboMauSac.getItemAt(i);
            if (l.toString().equals(mauSac.toString())) {
                cboMauSac.setSelectedIndex(i);
                break;
            }
        }

        int khoiLuongCount = cboKichThuoc.getItemCount();
        for (int i = 0; i < khoiLuongCount; i++) {
            Object l = cboKichThuoc.getItemAt(i);
            if (l.toString().equals(khoiLuong.toString())) {
                cboKichThuoc.setSelectedIndex(i);
                break;
            }
        }

        int chatLieuCount = cboChatLieu.getItemCount();
        for (int i = 0; i < chatLieuCount; i++) {
            Object l = cboChatLieu.getItemAt(i);
            if (l.toString().equals(chatLieu.toString())) {
                cboChatLieu.setSelectedIndex(i);
                break;
            }
        }
    }

    public void clickSanPham() {
        indexSp = tblSanPhamQl.getSelectedRow();

        String ma = tblModelSpql.getValueAt(indexSp, 1).toString();
        String ten = tblModelSpql.getValueAt(indexSp, 2).toString();
        Object thuongHieu = tblModelSpql.getValueAt(indexSp, 3);
        Object xuatXu = tblModelSpql.getValueAt(indexSp, 4);
        String mota = tblModelSpql.getValueAt(indexSp, 6).toString();
        String trangThai = tblModelSpql.getValueAt(indexSp, 7).toString();
        txtMa.setText(ma);
        txtTen.setText(ten);
        txtSpqlMota.setText(mota);
        cboSpqlTT.setSelectedItem(trangThai);
        int thuongHieuCount = cboThuongHieu.getItemCount();
        for (int i = 0; i < thuongHieuCount; i++) {
            Object l = cboThuongHieu.getItemAt(i);
            if (l.toString().equals(thuongHieu.toString())) {
                cboThuongHieu.setSelectedIndex(i);
                break;
            }
        }

        int xuatXuCount = cboXuatXu.getItemCount();
        for (int i = 0; i < xuatXuCount; i++) {
            Object l = cboXuatXu.getItemAt(i);
            if (l.toString().equals(xuatXu.toString())) {
                cboXuatXu.setSelectedIndex(i);
                break;
            }
        }

    }

    //get data trên form sản phẩm chi tiết
    public ChiTietSanPham getFormChiTietSanPham() {
        ChiTietSanPham ctsp = new ChiTietSanPham();

        SanPham sp = (SanPham) cboSanPham.getSelectedItem();
        MauSac ms = (MauSac) cboMauSac.getSelectedItem();
        KichThuoc kl = (KichThuoc) cboKichThuoc.getSelectedItem();
        ChatLieu chatLieu = (ChatLieu) cboChatLieu.getSelectedItem();
        Integer trangThai = cboTrangThai.getSelectedIndex();
        String moTa = txtCongDung.getText();

        Integer soLuong = 0;
        Double giaBan = 0.0;
        Double giaNhap = 0.0;

        if (txtGiaBan.getText().trim().length() == 0 || txtGiaNhap.getText().trim().length() == 0 || txtSoLuong.getText().length() == 0 || txtCongDung.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống");
            return null;
        }

        try {
            soLuong = Integer.valueOf(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là số");
            return null;
        }
        try {
            giaBan = Double.valueOf(txtGiaBan.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá bán là số");
            return null;
        }
        try {
            giaNhap = Double.valueOf(txtGiaNhap.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá nhập là số");
            return null;
        }
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            return null;
        }
        if (giaBan <= 0) {
            JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0");
            return null;
        }
        if (giaNhap <= 0) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0");
            return null;
        }
        if (giaBan < giaNhap) {
            JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn hoặc bằng giá nhập");
            return null;
        }

        ctsp.setSanPham(sp);
        ctsp.setMauSac(ms);
        ctsp.setKichThuoc(kl);
        ctsp.setChatLieu(chatLieu);
        ctsp.setTinhTrang(trangThai);
        ctsp.setMoTa(moTa);
        ctsp.setGiaNhap(giaNhap);
        ctsp.setGiaBan(giaBan);
        ctsp.setSoLuong(soLuong);

        return ctsp;
    }

    //get datadaa trên form sản phẩm
    public SanPham getFormSanPham() {
        SanPham sp = new SanPham();
        if (txtMa.getText().trim().length() == 0 || txtTen.getText().trim().length() == 0 || txtSpqlMota.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống");
            return null;
        }
        ThuongHieu thuongHieu = (ThuongHieu) cboThuongHieu.getSelectedItem();
        XuatXu xx = (XuatXu) cboXuatXu.getSelectedItem();
        sp.setThuongHieu(thuongHieu);
        sp.setXuatXu(xx);
        sp.setTen(txtTen.getText().trim());
        sp.setMoTa(txtSpqlMota.getText().trim());
        sp.setTinhTrang(cboSpqlTT.getSelectedIndex());
        return sp;
    }

    //add sản phẩm chi tiết
    public void addSanPhamChiTiet() {
        ChiTietSanPham ctsp = getFormChiTietSanPham();
        if (ctsp == null) {
            return;
        }
        ctsp.setMa("SPCT0" + (Integer.valueOf(ctspRepo.getIdMax()) + 1));
        if (ctspRepo.checkDuclicate(ctsp, null) > 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm chi tiết này đã tồn tại");
            return;
        }
        if (ctspRepo.add(ctsp)) {
            JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Thất Bại");
        }
        loadTableSanPhamChiTiet(ctspRepo.getAll());

        clearFormSanPhamChiTiet();
    }

    public void addSanPham() {
        SanPham sp = getFormSanPham();
        if (sp == null) {
            return;
        }
        sp.setMa("SP0" + (Integer.valueOf(spRepo.getIdMax()) + 2));
        if (spRepo.checkDuclicate(sp, null) > 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã tồn tại");
            return;
        }
        if (spRepo.add(sp)) {
            JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Thất Bại");
        }
        loadTableSanPham(spRepo.getAll());

        clearFormSanPham();
    }

    //sửa sản phẩm chi  tiết
    public void updateSanPhamChiTiet() {
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm Chi Tiết");
            return;
        }

        ChiTietSanPham ctsp = getFormChiTietSanPham();
        if (ctsp == null) {
            return;
        }
        ChiTietSanPham chiTietSanPham = this.lstChiTietSanPham.get(index);

        chiTietSanPham.setSanPham(ctsp.getSanPham());
        chiTietSanPham.setMoTa(ctsp.getMoTa());
        chiTietSanPham.setMauSac(ctsp.getMauSac());
        chiTietSanPham.setKichThuoc(ctsp.getKichThuoc());
        chiTietSanPham.setChatLieu(ctsp.getChatLieu());
        chiTietSanPham.setTinhTrang(ctsp.getTinhTrang());
        chiTietSanPham.setGiaNhap(ctsp.getGiaNhap());
        chiTietSanPham.setGiaBan(ctsp.getGiaBan());
        chiTietSanPham.setSoLuong(ctsp.getSoLuong());
        chiTietSanPham.setNgaySua(new Date());
        if (ctspRepo.checkDuclicate(chiTietSanPham, chiTietSanPham.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm chi tiết này đã tồn tại");
            return;
        }
        if (ctspRepo.update(chiTietSanPham.getId(), chiTietSanPham)) {
            JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Chi Tiết Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Chi Tiết Thất Bại");
        }
        loadTableSanPhamChiTiet(ctspRepo.getAll());
        clearFormSanPhamChiTiet();
    }

    //update sản phẩm
    public void updateSanPham() {
        if (indexSp == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm");
            return;
        }

        SanPham sp = getFormSanPham();
        if (sp == null) {
            return;
        }
        SanPham sanPham = this.lstSanPham.get(indexSp);

        sanPham.setTen(sp.getTen());
        sanPham.setThuongHieu(sp.getThuongHieu());
        sanPham.setXuatXu(sp.getXuatXu());
        sanPham.setMoTa(sp.getMoTa());
        sanPham.setTinhTrang(sp.getTinhTrang());

        if (spRepo.checkDuclicate(sanPham, sanPham.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm này đã tồn tại");
            return;
        }
        if (spRepo.update(sanPham.getId(), sanPham)) {
            JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Thất Bại");
        }
        loadTableSanPham(spRepo.getAll());
        clearFormSanPham();
    }

    // xóa sản phẩm chi tiết
    public void deleteSanPhamChiTiet() {
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm Chi Tiết");
            return;
        }
        ChiTietSanPham chiTietSanPham = this.lstChiTietSanPham.get(index);
        chiTietSanPham.setXoaMem(true);
        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (ctspRepo.update(chiTietSanPham.getId(), chiTietSanPham)) {
            JOptionPane.showMessageDialog(this, "Xóa Sản Phẩm Chi Tiết Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Sản Phẩm Chi Tiết Thất Bại");
        }
        loadTableSanPhamChiTiet(ctspRepo.getAll());
    }

    //xóa sản phẩm 
    public void deleteSanPham() {
        if (indexSp == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm");
            return;
        }
        SanPham sanPham = this.lstSanPham.get(indexSp);
        sanPham.setXoaMem(true);
        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (spRepo.delete(sanPham.getId())) {
            JOptionPane.showMessageDialog(this, "Xóa Sản Phẩm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Sản Phẩm Thất Bại");
        }
        loadTableSanPham(spRepo.getAll());
    }

    public void clearFormSanPhamChiTiet() {
        txtCongDung.setText("");
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        cboSanPham.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);
    }

    public void clearFormSanPham() {
        txtMa.setText("SP0" + (Integer.valueOf(spRepo.getIdMax()) + 2));
        txtTen.setText("");
        cboThuongHieu.setSelectedIndex(0);
        cboXuatXu.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        tabSanpham = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        cboXuatXu = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        Xóa = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cboSpqlTT = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSpqlMota = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamQl = new javax.swing.JTable();
        txtSpql = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCongDung = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jFormattedTextField1.setText("jFormattedTextField1");

        tabSanpham.setBackground(new java.awt.Color(255, 255, 255));
        tabSanpham.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabSanphamAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabSanpham.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabSanphamStateChanged(evt);
            }
        });
        tabSanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabSanphamMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Mã");

        jLabel2.setText("Tên Sản Phẩm");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtMa.setEditable(false);
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        cboXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Thương Hiệu");

        jLabel7.setText("Xuất Xứ");

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        Xóa.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        Xóa.setText("Xóa");
        Xóa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XóaActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng Thái");

        cboSpqlTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Kinh Doanh", "Ngừng Kinh Doanh" }));

        jLabel11.setText("Mô Tả");

        txtSpqlMota.setColumns(20);
        txtSpqlMota.setRows(5);
        jScrollPane4.setViewportView(txtSpqlMota);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(129, 129, 129)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboSpqlTT, 0, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Xóa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboSpqlTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jButton12))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton11)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addGap(10, 10, 10)
                        .addComponent(Xóa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPhamQl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Thương Hiệu", "Xuất Xứ", "Số Lương Chi Tiết", "Mô Tả", "Trạng Thái", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tblSanPhamQl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamQlMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamQl);

        txtSpql.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSpqlKeyReleased(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\loupe.png")); // NOI18N
        jLabel13.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtSpql, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSpql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabSanpham.addTab("Quản lý sản phẩm", jPanel5);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Màu Sắc");

        jLabel4.setText("Kích Thước");

        jLabel8.setText("Giá Nhập");

        jLabel9.setText("Số Lượng");

        txtGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhapActionPerformed(evt);
            }
        });

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel12.setText("Trạng Thái");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("Chất Liệu");

        jLabel15.setText("Giá Bán");

        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Kinh Doanh", "Ngừng Kinh Doanh" }));

        jLabel16.setText("Mô Tả");

        txtCongDung.setColumns(20);
        txtCongDung.setRows(5);
        jScrollPane3.setViewportView(txtCongDung);

        jLabel5.setText("Sản Phẩm");

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\excel.png")); // NOI18N
        jButton13.setText("Export Excel");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\categorization.png")); // NOI18N
        jButton9.setText("Quản Lý Thuộc Tính");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton8.setText("Làm mới");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        jButton7.setText("Xóa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        jButton6.setText("Sửa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton5.setText("Thêm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChatLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9)
                    .addComponent(jButton13)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6))
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2)
                                    .addComponent(jLabel14))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Màu Sắc ", "Kích Thước", "Thương Hiệu", "Xuất Xứ", "Chất Liệu", "Giá Nhập", "Giá Bán", "Số Lượng", "Mô Tả", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

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

        jLabel17.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\loupe.png")); // NOI18N
        jLabel17.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabSanpham.addTab("Sản Phẩm Chi Tiết", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabSanpham)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabSanpham)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void txtGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaNhapActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtTimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSanPhamActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        clickSanPhamChiTiet();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        addSanPhamChiTiet();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        updateSanPhamChiTiet();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        deleteSanPhamChiTiet();
        clearFormSanPhamChiTiet();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearFormSanPhamChiTiet();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ThuocTinhDialog thuocTinhDialog = new ThuocTinhDialog(new javax.swing.JFrame(), true);
        thuocTinhDialog.setVisible(true);
        initCbo();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtTimKiemSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamKeyReleased
        loadTableSanPhamChiTiet(ctspRepo.getAllByTen(txtTimKiemSanPham.getText()));
    }//GEN-LAST:event_txtTimKiemSanPhamKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addSanPham();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ThuocTinhDialog thuocTinhDialog = new ThuocTinhDialog(new javax.swing.JFrame(), true);
        thuocTinhDialog.selectTab(0);
        thuocTinhDialog.setVisible(true);
        initCbo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ThuocTinhDialog thuocTinhDialog = new ThuocTinhDialog(new javax.swing.JFrame(), true);
        thuocTinhDialog.selectTab(1);
        thuocTinhDialog.setVisible(true);
        initCbo();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        ThuocTinhDialog thuocTinhDialog = new ThuocTinhDialog(new javax.swing.JFrame(), true);
        thuocTinhDialog.selectTab(2);
        thuocTinhDialog.setVisible(true);
        initCbo();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        ThuocTinhDialog thuocTinhDialog = new ThuocTinhDialog(new javax.swing.JFrame(), true);
        thuocTinhDialog.selectTab(3);
        thuocTinhDialog.setVisible(true);
        initCbo();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ThuocTinhDialog thuocTinhDialog = new ThuocTinhDialog(new javax.swing.JFrame(), true);
        thuocTinhDialog.selectTab(4);
        thuocTinhDialog.setVisible(true);
        initCbo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblSanPhamQlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamQlMouseClicked
        clickSanPham();
    }//GEN-LAST:event_tblSanPhamQlMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateSanPham();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void XóaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XóaActionPerformed
        deleteSanPham();
    }//GEN-LAST:event_XóaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearFormSanPham();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSpqlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSpqlKeyReleased
        loadTableSanPham(spRepo.getAllByTen(txtSpql.getText()));
    }//GEN-LAST:event_txtSpqlKeyReleased

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        JFileChooser fileChooser = new JFileChooser("./file/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Export Excel");
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                int chk = JOptionPane.showConfirmDialog(this, "Xác nhận xuất file ?");
                if (chk == JOptionPane.YES_OPTION) {
                    ExportSP.writeExcel(ctspRepo.getAll(), fileToSave.getAbsolutePath() + filter.getDescription());
                    JOptionPane.showMessageDialog(this, "Xuất File Excel thành công");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Xuất File Excel thất bại");
            }
            System.out.println("Save as file: " + fileToSave.getAbsolutePath() + filter.getDescription());

        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void tabSanphamAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabSanphamAncestorAdded
    }//GEN-LAST:event_tabSanphamAncestorAdded

    private void tabSanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabSanphamMouseClicked
        if (tabSanpham.getSelectedIndex() == 1) {
            loadTableSanPhamChiTiet(ctspRepo.getAll());
        } else {
            loadTableSanPham(spRepo.getAll());
        }

    }//GEN-LAST:event_tabSanphamMouseClicked

    private void tabSanphamStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabSanphamStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabSanphamStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Xóa;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboSpqlTT;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane tabSanpham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamQl;
    private javax.swing.JTextArea txtCongDung;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSpql;
    private javax.swing.JTextArea txtSpqlMota;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiemSanPham;
    // End of variables declaration//GEN-END:variables
}
