/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ChiTietSanPham;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Repositorries.ChiTietSanPhamRepository;
import Repositorries.HoaDonChiTietRepository;
import Repositorries.HoaDonRepository;
import Utilities.ExportPDF;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class HoaDonPanel extends javax.swing.JPanel {

    DefaultTableModel tblModelHoaDon;
    DefaultTableModel tblModelSp;

    List<HoaDon> lstHoaDon = null;
    List<ChiTietSanPham> lstSanPham = null;
    
    HoaDonRepository hdRepo;
    HoaDonChiTietRepository hdctRepo;
    ChiTietSanPhamRepository ctspRepo;

    public HoaDonPanel() {
        initComponents();
        tblModelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        tblModelSp = (DefaultTableModel) tblSanPham.getModel();

        hdRepo = new HoaDonRepository();
        ctspRepo = new ChiTietSanPhamRepository();
        hdctRepo = new HoaDonChiTietRepository();

        rdoAll.setSelected(true);
        loadTableHoaDon(hdRepo.getAll());
    }
    //Load table hóa đơn truyền vài 1 lst hóa đơn
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
                hd.getNgayTao(),
                hd.getNgayThanhToan(),
                hd.getTinhTrang() == 0 ? "Chờ thanh toán" : hd.getTinhTrang() == 1 ? "Đã thanh toán" : "Đã hủy"
            });
        }
    }
    //Load table sản phẩm truyền vài 1 lst Sản phẩm
    public void loadTableSanPham(List<ChiTietSanPham> lst) {
        int i = 0;
        tblModelSp.setRowCount(0);

        this.lstSanPham = lst;
        for (ChiTietSanPham ctsp : lst) {
            tblModelSp.addRow(new Object[]{
                ++i,
                ctsp.getSanPham().getMa(),
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

    //click vào table hóa đơn rồi load dữ liệu sản phảm của hóa đơn đó
    public void clickTableHoaDon() {
        int index = tblHoaDon.getSelectedRow();
        HoaDon hd = this.lstHoaDon.get(index);

        List<HoaDonChiTiet> lstHdct = hdctRepo.getAllByHoaDon(String.valueOf(hd.getId()));
        List<ChiTietSanPham> lstCtsp = new ArrayList<>();
        for (HoaDonChiTiet hoaDonChiTiet : lstHdct) {
            ChiTietSanPham ctsp = hoaDonChiTiet.getChiTietSanPham();
            ctsp.setSoLuong(hoaDonChiTiet.getSoLuong());
            ctsp.setGiaBan(hoaDonChiTiet.getDonGia());
            lstCtsp.add(ctsp);
            
        }
        loadTableSanPham(lstCtsp);

    }
    // xuất hóa đơn pdf
    public void inHoaDon(){
        int index = tblHoaDon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hóa Đơn");
            return;
        }
        
        
        HoaDon hoaDon = lstHoaDon.get(index);
        if (hoaDon.getTinhTrang() !=1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hóa Đơn Đã Thanh Toán");
            return;
        }
        
         int chk = JOptionPane.showConfirmDialog(this, "In Hóa Đơn ?");
        if (chk == JOptionPane.YES_OPTION) {
            try {
                ExportPDF.ExportPDF(hoaDon);
                JOptionPane.showMessageDialog(this, "In Thành Công");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "In Thất Bại");
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        rdoAll = new javax.swing.JRadioButton();
        rdoCho = new javax.swing.JRadioButton();
        rdoDa = new javax.swing.JRadioButton();
        rdoHuy = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Tên NV", "Tên KH", "Ngày tạo ", "Ngày thanh toán", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
        }

        buttonGroup1.add(rdoAll);
        rdoAll.setText("Tất cả");
        rdoAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAllActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCho);
        rdoCho.setText("Chờ thanh toán");
        rdoCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDa);
        rdoDa.setText("Đã thanh toán");
        rdoDa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHuy);
        rdoHuy.setText("Đã hủy");
        rdoHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHuyActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\loupe.png")); // NOI18N
        jLabel1.setText("Tìm Kiếm");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\receipt.png")); // NOI18N
        jButton1.setText("In Hóa Đơn");
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoAll)
                        .addGap(18, 18, 18)
                        .addComponent(rdoCho)
                        .addGap(18, 18, 18)
                        .addComponent(rdoDa)
                        .addGap(18, 18, 18)
                        .addComponent(rdoHuy)
                        .addGap(29, 29, 29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoAll)
                            .addComponent(rdoCho)
                            .addComponent(rdoDa)
                            .addComponent(rdoHuy))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

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
        jScrollPane3.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHuyActionPerformed
        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang("", 2));

    }//GEN-LAST:event_rdoHuyActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        clickTableHoaDon();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void rdoChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChoActionPerformed
        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang("", 0));
        List<ChiTietSanPham> lst = new ArrayList<>();
        loadTableSanPham(lst);

    }//GEN-LAST:event_rdoChoActionPerformed

    private void rdoAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAllActionPerformed
        loadTableHoaDon(hdRepo.getAll());
        List<ChiTietSanPham> lst = new ArrayList<>();
        loadTableSanPham(lst);

    }//GEN-LAST:event_rdoAllActionPerformed

    private void rdoDaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaActionPerformed
        loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang("", 1));
        List<ChiTietSanPham> lst = new ArrayList<>();
        loadTableSanPham(lst);

    }//GEN-LAST:event_rdoDaActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (rdoAll.isSelected() == true) {
            loadTableHoaDon(hdRepo.getAllByMa(txtTimKiem.getText()));
        } else if (rdoCho.isSelected() == true) {
            loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang(txtTimKiem.getText(), 0));
        } else if (rdoDa.isSelected() == true) {
            loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang(txtTimKiem.getText(), 1));
        } else if (rdoHuy.isSelected() == true) {
            loadTableHoaDon(hdRepo.getAllByMaAndTinhTrang(txtTimKiem.getText(), 2));
        } else {
            loadTableHoaDon(hdRepo.getAllByMa(txtTimKiem.getText()));
        }


    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      inHoaDon();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdoAll;
    private javax.swing.JRadioButton rdoCho;
    private javax.swing.JRadioButton rdoDa;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
