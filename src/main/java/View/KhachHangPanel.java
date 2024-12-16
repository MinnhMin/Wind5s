/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.KhachHang;
import Repositorries.KhachHangRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhachHangPanel extends javax.swing.JPanel {

    private KhachHang khachHang = null;
    private KhachHangRepository khRepo;
    private List<KhachHang> lstKhachHang;
    private DefaultTableModel tblModelKhachHang;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public KhachHangPanel() {
        initComponents();
        khRepo = new KhachHangRepository();
        lstKhachHang = new ArrayList<>();
        tblModelKhachHang = (DefaultTableModel) tblKhachHang.getModel();
        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
        lstKhachHang = khRepo.getAllByTen("");
        loadTableKhachHang(lstKhachHang);
        clearForm();
    }
    
    //Load table khác hàng truyền vào 1 lst danh sashc khashc hàng
    public void loadTableKhachHang(List<KhachHang> lst) {
        int i = 0;
        tblModelKhachHang.setRowCount(0);
        for (KhachHang kh : lst) {
            tblModelKhachHang.addRow(new Object[]{
                ++i,
                kh.getMa(),
                kh.getTen(),
                sdf.format(kh.getNgaySinh()),
                kh.getGioiTinh() == 0 ? "Nam" : "Nữ",
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi()
            });
        }

    }

    //bắt validate date email
    public boolean isValidateEmail(String email) {
        String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Boolean b = email.matches(EMAIL);
        return b;
    }

    public boolean isValidatePhone(String sdt) {
        String Phone = "0[0-9]{9,10}";
        Boolean b = sdt.matches(Phone);
        return b;
    }

    //Get dât tên form khách hàng
    public KhachHang getFormKhachHang() {
        KhachHang kh = new KhachHang();
        String ten = txtTenKH.getText();
        Date ngaySinh = dateNgaySinh.getDate();
        String sdt = txtSdt.getText();
        String email = txtEmail.getText();
        String diaChi = txtDiaCHi.getText();

        if (ten.trim().length() == 0 || sdt.trim().length() == 0 || email.trim().length() == 0 || diaChi.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return null;
        }
        
        if (!isValidateEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email Không Đúng Định Dạng");
            return null;
        }

        if (!isValidatePhone(sdt)) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Không Đúng Định Dạng");
            return null;
        }
        if (dateNgaySinh.getDate().after(new Date())) {
           JOptionPane.showMessageDialog(this, "Ngày sinh không được lớn hơn ngày hiện tại.");
           return null;
        }
        String ma = "KH0" + (Integer.valueOf(khRepo.getIdMax()) + 1);
        kh.setMa(ma);
        kh.setTen(ten);
        kh.setDiaChi(diaChi);
        kh.setEmail(email);
        kh.setSdt(sdt);
        kh.setNgaySinh(ngaySinh);
        if (rdoNam.isSelected() == true) {
            kh.setGioiTinh(0);
        } else {
            kh.setGioiTinh(1);
        }

        return kh;

    }

    public void clearForm() {
        txtTenKH.setText("");
        txtMaKH.setText("KH0" + (Integer.valueOf(khRepo.getIdMax()) + 1));
        txtDiaCHi.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        dateNgaySinh.setDate(new Date());
        rdoNam.setSelected(true);
    }

    //add khách hàng
    public void addKhachHang() {
        KhachHang kh = getFormKhachHang();
        if (kh == null) {
            return;
        }
        if (khRepo.checkSdt(kh.getSdt(), null) > 0) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Đã Được Sử Dụng");
            return;
        }
        if (khRepo.checkEmail(kh.getEmail(), null) > 0) {
            JOptionPane.showMessageDialog(this, "Email Này Đã Được Sử Dụng");
            return;
        }
        if (khRepo.add(kh)) {
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }
        lstKhachHang = khRepo.getAllByTen(txtTimKiem.getText());
        loadTableKhachHang(lstKhachHang);
        clearForm();

    }
    //Update kahschh hàng
    public void updateKhachHang() {
        int index = tblKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        KhachHang khachHang = this.lstKhachHang.get(index);
        KhachHang kh = getFormKhachHang();
        if (kh == null) {
            return;
        }
        if (khRepo.checkSdt(kh.getSdt(), khachHang.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Đã Được Sử Dụng");
            return;
        }
        if (khRepo.checkEmail(kh.getEmail(), khachHang.getId()) > 0) {
            JOptionPane.showMessageDialog(this, "Email Này Đã Được Sử Dụng");
            return;
        }
        khachHang.setTen(kh.getTen());
        khachHang.setSdt(kh.getSdt());
        khachHang.setDiaChi(kh.getDiaChi());
        khachHang.setGioiTinh(kh.getGioiTinh());
        khachHang.setNgaySinh(kh.getNgaySinh());
        khachHang.setEmail(kh.getEmail());
        khachHang.setTinhTrang(kh.getTinhTrang());

        if (khRepo.update(String.valueOf(khachHang.getId()), khachHang)) {
            JOptionPane.showMessageDialog(this, "Update Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Update Thất Bại");
        }
        lstKhachHang = khRepo.getAllByTen(txtTimKiem.getText());
        loadTableKhachHang(lstKhachHang);
        clearForm();
    }

    //xóa khách hàng
    public void deleteKhachHang() {
        int index = tblKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        KhachHang khachHang = this.lstKhachHang.get(index);
        if (khRepo.delete(String.valueOf(khachHang.getId()))) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        lstKhachHang = khRepo.getAllByTen(txtTimKiem.getText());
        loadTableKhachHang(lstKhachHang);
        clearForm();

    }

    
    //click khách hàng và view data khahsch hngaf đc chọn len form
    public void clickTable() {

        int index = tblKhachHang.getSelectedRow();
        txtMaKH.setText(tblKhachHang.getValueAt(index, 1).toString());
        txtTenKH.setText(tblKhachHang.getValueAt(index, 2).toString());
        String gioiTinh = tblKhachHang.getValueAt(index, 4).toString();
        String ngaySinh = tblKhachHang.getValueAt(index, 3).toString();
        txtSdt.setText(tblKhachHang.getValueAt(index, 5).toString());
        txtEmail.setText(tblKhachHang.getValueAt(index, 6).toString());
        txtDiaCHi.setText(tblKhachHang.getValueAt(index, 7).toString());

        if (gioiTinh.equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdonu.setSelected(true);
        }

        try {
            dateNgaySinh.setDate(sdf.parse(ngaySinh));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblMaKH = new javax.swing.JLabel();
        lblTenKh = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtTenKH = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaCHi = new javax.swing.JTextArea();
        lblDiaChi = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblSDT = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Khách Hàng", "Tên Khách Hàng", "Ngày Sinh", "Giới Tính", "SDT", "Email", "Địa Chỉ"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane24.setViewportView(tblKhachHang);

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\loupe.png")); // NOI18N
        jLabel1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblMaKH.setText("Mã KH");

        lblTenKh.setText("Tên KH");

        jLabel3.setText("Ngày Sinh");

        lblGioiTinh.setText("Giới Tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        txtMaKH.setEditable(false);

        txtDiaCHi.setColumns(20);
        txtDiaCHi.setRows(5);
        jScrollPane1.setViewportView(txtDiaCHi);

        lblDiaChi.setText("Địa Chỉ");

        lblEmail.setText("Email");

        lblSDT.setText("SDT");

        btnThem.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\plus.png")); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\pen.png")); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\bin.png")); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon("D:\\Wind5s_v5\\wind5s\\src\\main\\java\\icon\\refresh.png")); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGioiTinh)
                    .addComponent(lblTenKh)
                    .addComponent(lblMaKH)
                    .addComponent(jLabel3))
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 67, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addComponent(lblSDT)
                            .addComponent(lblDiaChi))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdonu)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKH)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSDT)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(3, 3, 3))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenKh)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSua))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDiaChi)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdonu)
                                    .addComponent(rdoNam)
                                    .addComponent(lblGioiTinh)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset)))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteKhachHang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        clickTable();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateKhachHang();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addKhachHang();
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        lstKhachHang = khRepo.getAllByTen(txtTimKiem.getText());
        loadTableKhachHang(lstKhachHang);
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextArea txtDiaCHi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
