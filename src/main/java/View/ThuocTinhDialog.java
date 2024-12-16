/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Model.ChiTietSanPham;
import Model.ThuongHieu;
import Model.KichThuoc;
import Model.ChatLieu;
import Model.MauSac;
import Model.XuatXu;
import Repositorries.ThuongHieuRepository;
import Repositorries.KichThuocRepository;
import Repositorries.ChatLieuRepository;
import Repositorries.MauSacRepository;
import Repositorries.XuatXuRepository;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ThuocTinhDialog extends javax.swing.JDialog {

    MauSacRepository msRepo;
    KichThuocRepository ktRepo;
    ThuongHieuRepository thRepo;
    XuatXuRepository xxRepo;
    ChatLieuRepository clRepo;

    DefaultTableModel tblModelMauSac;
    DefaultTableModel tblModelKichThuoc;
    DefaultTableModel tblModelThuongHieu;
    DefaultTableModel tblModelXuatXu;
    DefaultTableModel tblModelChatLieu;

    List<MauSac> lstMauSac = new ArrayList<>();
    List<KichThuoc> lstKichThuoc = new ArrayList<>();
   
    List<ThuongHieu> lstThuongHieu = new ArrayList<>();
    List<XuatXu> lstXuatXu = new ArrayList<>();
    List<ChatLieu> lstChatLieu = new ArrayList<>();

    MauSac mauSac = null;
    KichThuoc khoiLuong = null;
    ThuongHieu hang = null;
    XuatXu nguonGoc = null;
    ChatLieu loai = null;

    public ThuocTinhDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/logo_wind5s.png")).getImage());
        msRepo = new MauSacRepository();
        ktRepo = new KichThuocRepository();
        thRepo = new ThuongHieuRepository();
        xxRepo = new XuatXuRepository();
        clRepo = new ChatLieuRepository();
        initTable();
        loadTable();

        clearFormChatLieu();
        clearFormXuatXu();
        clearFormThuongHieu();
        clearFormKichThuoc();
        clearFormMauSac();

    }
    
    public void selectTab(int tabIndex) {
        tabThuocTinh.setSelectedIndex(tabIndex);
    }

    public void initTable() {
        tblModelKichThuoc = (DefaultTableModel) tblKichThuoc.getModel();
        tblModelThuongHieu = (DefaultTableModel) tblThuongHieu.getModel();
        tblModelChatLieu = (DefaultTableModel) tblChatLieu.getModel();
        tblModelMauSac = (DefaultTableModel) tblMauSac.getModel();
        tblModelXuatXu = (DefaultTableModel) tblXuatXu.getModel();
        
    }

    public void loadTable() {

        loadTableThuongHieu(thRepo.getAll());
        loadTableMauSac(msRepo.getAll());
        loadTableKichThuoc(ktRepo.getAll());
        loadTableChatLieu(clRepo.getAll());
        loadTableXuatXu(xxRepo.getAll());
    }

    public void loadTableMauSac(List<MauSac> lst) {
        int i = 0;
        lstMauSac = lst;
        tblModelMauSac.setRowCount(0);
        for (MauSac obj : lst) {
            tblModelMauSac.addRow(new Object[]{
                ++i,
                obj.getMa(),
                obj.getTen()
            });
        }
    }

    public void loadTableKichThuoc(List<KichThuoc> lst) {
        int i = 0;
        lstKichThuoc = lst;
        tblModelKichThuoc.setRowCount(0);
        for (KichThuoc obj : lst) {
            tblModelKichThuoc.addRow(new Object[]{
                ++i,
                obj.getMa(),
                obj.getTen()
            });
        }
    }

    public void loadTableThuongHieu(List<ThuongHieu> lst) {
        int i = 0;
        lstThuongHieu = lst;
        tblModelThuongHieu.setRowCount(0);

        for (ThuongHieu obj : lst) {
            tblModelThuongHieu.addRow(new Object[]{
                ++i,
                obj.getMa(),
                obj.getTen()
            });
        }
    }

    public void loadTableChatLieu(List<ChatLieu> lst) {
        int i = 0;
        lstChatLieu = lst;
        tblModelChatLieu.setRowCount(0);

        for (ChatLieu obj : lst) {
            tblModelChatLieu.addRow(new Object[]{
                ++i,
                obj.getMa(),
                obj.getTen()
            });
        }
    }

    public void loadTableXuatXu(List<XuatXu> lst) {
        int i = 0;
        lstXuatXu = lst;
        tblModelXuatXu.setRowCount(0);

        for (XuatXu obj : lst) {
            tblModelXuatXu.addRow(new Object[]{
                ++i,
                obj.getMa(),
                obj.getTen()
            });
        }
    }

    public Integer clickTable(JTable tbl, JTextField txtMa, JTextField txtTen) {
        int index = tbl.getSelectedRow();
        String ma = tbl.getValueAt(index, 1).toString();
        String ten = tbl.getValueAt(index, 2).toString();
        txtMa.setText(ma);
        txtTen.setText(ten);
        return index;
    }

    public void clearFormMauSac() {
        txtMaMs.setText("MS0" + (Integer.valueOf(msRepo.getIdMax()) + 1));
        txtTenMs.setText("");
    }

    public void clearFormKichThuoc() {
        txtMaKt.setText("KT0" + (Integer.valueOf(ktRepo.getIdMax()) + 1));
        txtTenKt.setText("");
    }

    public void clearFormThuongHieu() {
        txtMaThuongHieu.setText("TH0" + (Integer.valueOf(thRepo.getIdMax()) + 1));
        txtTenThuongHieu.setText("");
    }

    public void clearFormXuatXu() {
        txtMaXuatXu.setText("XX0" + (Integer.valueOf(xxRepo.getIdMax()) + 1));
        txtTenXuatXu.setText("");
    }

    public void clearFormChatLieu() {
        txtMaChatLieu.setText("CL0" + (Integer.valueOf(clRepo.getIdMax()) + 1));
        txtTenChatLieu.setText("");
    }

    public MauSac getFormMauSac() {
        MauSac obj = new MauSac();
        if (txtTenMs.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống");
            return null;
        }
        obj.setMa("MS0" + (Integer.valueOf(msRepo.getIdMax()) + 1));
        obj.setTen(txtTenMs.getText());
        return obj;
    }

    public KichThuoc getFormKichThuoc() {
        KichThuoc obj = new KichThuoc();
        if (txtTenKt.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống");
            return null;
        }
        obj.setMa("KT0" + (Integer.valueOf(ktRepo.getIdMax()) + 1));
        obj.setTen(txtTenKt.getText());
        return obj;
    }


    public ThuongHieu getFormThuongHieu() {
        ThuongHieu obj = new ThuongHieu();
        if (txtTenThuongHieu.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống");
            return null;
        }
        obj.setMa("TH0" + (Integer.valueOf(thRepo.getIdMax()) + 1));
        obj.setTen(txtTenThuongHieu.getText());
        return obj;
    }

    public XuatXu getFormXuatXu() {
        XuatXu obj = new XuatXu();
        if (txtTenXuatXu.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống");
            return null;
        }
        obj.setMa("XX0" + (Integer.valueOf(xxRepo.getIdMax()) + 1));
        obj.setTen(txtTenXuatXu.getText());
        return obj;
    }

    public ChatLieu getFormChatLieu() {
        ChatLieu obj = new ChatLieu();
        if (txtTenChatLieu.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không Được Để Trống");
            return null;
        }
        obj.setMa("CL0" + (Integer.valueOf(clRepo.getIdMax()) + 1));
        obj.setTen(txtTenChatLieu.getText());
        return obj;
    }

    public void addMauSac() {
        MauSac obj = getFormMauSac();
        if (obj == null) {
            return;
        }
         if (msRepo.checkDuclicate(obj.getTen(), null) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (msRepo.add(obj)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableMauSac(msRepo.getAll());
    }

    public void addKichThuoc() {
        KichThuoc obj = getFormKichThuoc();
        if (obj == null) {
            return;
        }
         if (ktRepo.checkDuclicate(obj.getTen(), null) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (ktRepo.add(obj)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableKichThuoc(ktRepo.getAll());
    }

    public void addThuongHieu() {
        ThuongHieu obj = getFormThuongHieu();
        if (obj == null) {
            return;
        }
         if (thRepo.checkDuclicate(obj.getTen(), null) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (thRepo.add(obj)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableThuongHieu(thRepo.getAll());
    }

    public void addXuatXu() {
        XuatXu obj = getFormXuatXu();
        if (obj == null) {
            return;
        }
        if (xxRepo.checkDuclicate(obj.getTen(), null) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (xxRepo.add(obj)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableXuatXu(xxRepo.getAll());
    }

    public void addChatLieu() {
        ChatLieu obj = getFormChatLieu();
        if (obj == null) {
            return;
        }
        if (clRepo.checkDuclicate(obj.getTen(), null) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (clRepo.add(obj)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableChatLieu(clRepo.getAll());
    }

    public void updateMauSac() {
        int index = tblMauSac.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        MauSac obj = getFormMauSac();
        MauSac OBJ = lstMauSac.get(index);
        OBJ.setTen(obj.getTen());
        
        if (msRepo.checkDuclicate(obj.getTen(), OBJ.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (msRepo.update(OBJ.getId(), OBJ)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableMauSac(msRepo.getAll());
    }

    public void updateKichThuoc() {
        int index = tblKichThuoc.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        KichThuoc obj = getFormKichThuoc();
        KichThuoc OBJ = lstKichThuoc.get(index);
        OBJ.setTen(obj.getTen());
        
        if (ktRepo.checkDuclicate(obj.getTen(), OBJ.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (ktRepo.update(OBJ.getId(), OBJ)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableKichThuoc(ktRepo.getAll());
    }

    public void updateThuongHieu() {
        int index = tblThuongHieu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        ThuongHieu obj = getFormThuongHieu();
        ThuongHieu OBJ = lstThuongHieu.get(index);
        OBJ.setTen(obj.getTen());
        
         if (thRepo.checkDuclicate(obj.getTen(), OBJ.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (thRepo.update(OBJ.getId(), OBJ)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableThuongHieu(thRepo.getAll());
    }

    public void updateXuatXu() {
        int index = tblXuatXu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        XuatXu obj = getFormXuatXu();
        XuatXu OBJ = lstXuatXu.get(index);
        OBJ.setTen(obj.getTen());
        
         if (xxRepo.checkDuclicate(obj.getTen(), OBJ.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (xxRepo.update(OBJ.getId(), OBJ)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableXuatXu(xxRepo.getAll());
    }

    public void updateChatLieu() {
        int index = tblChatLieu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        ChatLieu obj = getFormChatLieu();
        ChatLieu OBJ = lstChatLieu.get(index);
        OBJ.setTen(obj.getTen());
        
         if (clRepo.checkDuclicate(obj.getTen(), OBJ.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Tên này đã được sử dụng");
            return;
        }
        if (clRepo.update(OBJ.getId(), OBJ)) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableChatLieu(clRepo.getAll());
    }

    public void deleteMauSac() {
        int index = tblMauSac.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Xác Nhận Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        MauSac OBJ = lstMauSac.get(index);
        if (msRepo.delete(OBJ.getId())) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableMauSac(msRepo.getAll());
    }

    public void deleteKichThuoc() {
        int index = tblKichThuoc.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Xác Nhận Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        KichThuoc OBJ = lstKichThuoc.get(index);
        if (ktRepo.delete(OBJ.getId())) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableKichThuoc(ktRepo.getAll());
    }

    public void deleteThuongHieu() {
        int index = tblThuongHieu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Xác Nhận Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        ThuongHieu OBJ = lstThuongHieu.get(index);

        if (thRepo.delete(OBJ.getId())) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableThuongHieu(thRepo.getAll());
    }

    public void deleteXuatXu() {
        int index = tblXuatXu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Xác Nhận Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        XuatXu OBJ = lstXuatXu.get(index);

        if (xxRepo.delete(OBJ.getId())) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableXuatXu(xxRepo.getAll());
    }

    public void deleteChatLieu() {
        int index = tblChatLieu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Xác Nhận Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        ChatLieu OBJ = lstChatLieu.get(index);

        if (clRepo.delete(OBJ.getId())) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTableChatLieu(clRepo.getAll());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabThuocTinh = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaMs = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenMs = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblKichThuoc = new javax.swing.JTable();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtMaKt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTenKt = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblThuongHieu = new javax.swing.JTable();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtMaThuongHieu = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTenThuongHieu = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblXuatXu = new javax.swing.JTable();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtMaXuatXu = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTenXuatXu = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtMaChatLieu = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTenChatLieu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý thuộc tính");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản Lý Thuộc Tính"));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        tabThuocTinh.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        tblMauSac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblMauSacKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblMauSac);

        jButton6.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton6.setText("Thêm");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        jButton7.setText("Sửa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        jButton8.setText("Xóa");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton9.setText("Làm mới");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã");

        txtMaMs.setEditable(false);

        jLabel4.setText("Tên");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTenMs, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaMs)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaMs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenMs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabThuocTinh.addTab("Màu Sắc", jPanel8);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        tblKichThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblKichThuoc);

        jButton26.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton26.setText("Thêm");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        jButton27.setText("Sửa");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        jButton28.setText("Xóa");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton29.setText("Làm mới");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel13.setText("Mã");

        txtMaKt.setEditable(false);

        jLabel14.setText("Tên");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTenKt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaKt)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton29, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap())))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtMaKt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTenKt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabThuocTinh.addTab("Kích Thước ", jPanel13);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        tblThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblThuongHieu);

        jButton34.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton34.setText("Thêm");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        jButton35.setText("Sửa");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        jButton36.setText("Xóa");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton37.setText("Làm mới");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jLabel17.setText("Mã");

        txtMaThuongHieu.setEditable(false);

        jLabel18.setText("Tên");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaThuongHieu)))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 383, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton34, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txtMaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtTenThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabThuocTinh.addTab("Thương Hiệu ", jPanel15);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        tblXuatXu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblXuatXuMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblXuatXu);

        jButton38.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton38.setText("Thêm");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        jButton39.setText("Sửa");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        jButton40.setText("Xóa");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton41.setText("Làm mới");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jLabel19.setText("Mã");

        txtMaXuatXu.setEditable(false);

        jLabel20.setText("Tên");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaXuatXu)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtMaXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtTenXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton39)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabThuocTinh.addTab("Xuất Xứ ", jPanel16);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên"
            }
        ));
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblChatLieu);

        jButton42.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        jButton42.setText("Thêm");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        jButton43.setText("Sửa");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        jButton44.setText("Xóa");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        jButton45.setText("Làm mới");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jLabel21.setText("Mã");

        txtMaChatLieu.setEditable(false);

        jLabel22.setText("Tên");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaChatLieu)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtMaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabThuocTinh.addTab("Chất Liệu ", jPanel17);

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\close.png")); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabThuocTinh, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        clearFormChatLieu();
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        deleteChatLieu();
        clearFormChatLieu();
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        updateChatLieu();
        clearFormChatLieu();
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        addChatLieu();
        clearFormChatLieu();
    }//GEN-LAST:event_jButton42ActionPerformed

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        clickTable(tblChatLieu, txtMaChatLieu, txtTenChatLieu);
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        clearFormXuatXu();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        deleteXuatXu();
        clearFormXuatXu();
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        updateXuatXu();
        clearFormXuatXu();
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        addXuatXu();

        clearFormXuatXu();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void tblXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXuatXuMouseClicked
        clickTable(tblXuatXu, txtMaXuatXu, txtTenXuatXu);
    }//GEN-LAST:event_tblXuatXuMouseClicked

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        clearFormThuongHieu();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        deleteThuongHieu();
        clearFormThuongHieu();
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        updateThuongHieu();
        clearFormThuongHieu();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        addThuongHieu();

        clearFormThuongHieu();
    }//GEN-LAST:event_jButton34ActionPerformed

    private void tblThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieuMouseClicked
        clickTable(tblThuongHieu, txtMaThuongHieu, txtTenThuongHieu);
    }//GEN-LAST:event_tblThuongHieuMouseClicked

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        clearFormKichThuoc();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        deleteKichThuoc();
        clearFormKichThuoc();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        updateKichThuoc();
        clearFormKichThuoc();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        addKichThuoc();

        clearFormKichThuoc();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void tblKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocMouseClicked
        clickTable(tblKichThuoc, txtMaKt, txtTenKt);
    }//GEN-LAST:event_tblKichThuocMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        clearFormMauSac();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        deleteMauSac();
        clearFormMauSac();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        updateMauSac();
        clearFormMauSac();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        addMauSac();

        clearFormMauSac();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tblMauSacKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblMauSacKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMauSacKeyReleased

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        clickTable(tblMauSac, txtMaMs, txtTenMs);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThuocTinhDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThuocTinhDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThuocTinhDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThuocTinhDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThuocTinhDialog dialog = new ThuocTinhDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane tabThuocTinh;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblKichThuoc;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblThuongHieu;
    private javax.swing.JTable tblXuatXu;
    private javax.swing.JTextField txtMaChatLieu;
    private javax.swing.JTextField txtMaKt;
    private javax.swing.JTextField txtMaMs;
    private javax.swing.JTextField txtMaThuongHieu;
    private javax.swing.JTextField txtMaXuatXu;
    private javax.swing.JTextField txtTenChatLieu;
    private javax.swing.JTextField txtTenKt;
    private javax.swing.JTextField txtTenMs;
    private javax.swing.JTextField txtTenThuongHieu;
    private javax.swing.JTextField txtTenXuatXu;
    // End of variables declaration//GEN-END:variables
}
