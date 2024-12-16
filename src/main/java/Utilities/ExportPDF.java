/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Repositorries.HoaDonChiTietRepository;
import Repositorries.HoaDonRepository;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ExportPDF {

    //https://stackjava.com/library/doc-ghi-file-ms-word-bang-java-voi-apache-poi.html
    public static void main(String[] args) throws IOException {
        Document document = new Document();

        try {
            FileOutputStream out = new FileOutputStream(new File("./ExportHoaDon/HelloWorld.pdf"));
            // khởi tạo một PdfWriter truyền vào document và FileOutputStream
            PdfWriter.getInstance(document, out);
            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            document.add(new Paragraph("A Hello World PDF document."));
            // đóng file
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // thuc hien xuat thong tin hoa don duoi dạng PDF
    public static void ExportPDF(HoaDon hoaDon) {
        Document document = new Document();

        try {
            // Tạo file PDF với tên Mã HĐ
            FileOutputStream out = new FileOutputStream(new File("./ExportHoaDon/" + hoaDon.getMa() + ".pdf"));
            PdfWriter.getInstance(document, out);
            document.open();
            
            // Đọc font 
            BaseFont baseFont = BaseFont.createFont("./font/calibrii.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font vietnameseFont = new Font(baseFont, 18, Font.NORMAL);

            // Tiêu đề hóa đơn
            Paragraph title = new Paragraph("Hóa Đơn Thanh Toán", vietnameseFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(16);
            document.add(title);

            // Thông tin hóa đơn
            document.add(createHoaDonInfo(hoaDon, vietnameseFont));

            // Thông tin sản phẩm/dịch vụ (nếu có)
            document.add(createProductInfo(hoaDon, vietnameseFont));

            // Tổng tiền, tiền nhận, tiền thừa
            document.add(createTongTienInfo(hoaDon, vietnameseFont));

            // Đóng file
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // thong tin hoa don 
    private static Paragraph createHoaDonInfo(HoaDon hoaDon, Font font) {
        Paragraph hoaDonInfo = new Paragraph();
        hoaDonInfo.setAlignment(Element.ALIGN_LEFT);

        hoaDonInfo.add(new Chunk("Ngày lập: " + hoaDon.getNgayTao(), font));
        hoaDonInfo.add(new Chunk("\nMã hóa đơn: " + hoaDon.getMa(), font));
        KhachHang kh = new KhachHang();
        kh = hoaDon.getKhachHang();
        if (kh == null) {
            hoaDonInfo.add(new Chunk("\nTên khách hàng: Khách Lẻ", font));
        } else {
            hoaDonInfo.add(new Chunk("\nTên khách hàng: " + kh.getTen(), font));
        }

        hoaDonInfo.add(new Chunk("\n\n"));

        return hoaDonInfo;
    }
    
    // tao bang thong tin san pham
    private static PdfPTable createProductInfo(HoaDon hoaDon, Font font) {
        HoaDonChiTietRepository hdctRepo = new HoaDonChiTietRepository();
        PdfPTable table = new PdfPTable(4); // 3 cột cho Mã SP, Tên SP, Giá
        table.setWidthPercentage(100);
        
        // tieu de cac cot
        PdfPCell cell1 = new PdfPCell(new Phrase("Mã Sản Phẩm", font));
        PdfPCell cell2 = new PdfPCell(new Phrase("Tên Sản Phẩm", font));
        PdfPCell cell3 = new PdfPCell(new Phrase("Số Lượng", font));
        PdfPCell cell4 = new PdfPCell(new Phrase("Đơn Giá", font));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        // Thêm thông tin sản phẩm/dịch vụ vào bảng
        for (HoaDonChiTiet hdct : hdctRepo.getAllByHoaDon(String.valueOf(hoaDon.getId()))) {
            table.addCell(new Phrase(hdct.getChiTietSanPham().getMa(), font));
            table.addCell(new Phrase(hdct.getChiTietSanPham().getSanPham().getTen(), font));
            table.addCell(new Phrase(String.valueOf(hdct.getSoLuong()), font));
            Double giaTien = hdct.getDonGia();
            int soLuong = hdct.getSoLuong();
            table.addCell(new Phrase(String.valueOf(giaTien * soLuong), font));
        }

        return table;
    }
    
    // thong tin tong tien 
    private static Paragraph createTongTienInfo(HoaDon hoaDon, Font font) {
        Paragraph tongTienInfo = new Paragraph();
        tongTienInfo.setAlignment(Element.ALIGN_RIGHT);

        Double tienMat = hoaDon.getTienMat();
        Double tienChuyenKhoan = hoaDon.getTienChuyenKhoan();
        Double tongTien = hoaDon.getTongTien();

        Double tienThua = (tienMat + tienChuyenKhoan) - tongTien;

        tongTienInfo.add(new Chunk("\n\nTổng Tiền: " + tongTien, font));
        tongTienInfo.add(new Chunk("\nHình Thức Thanh Toán: " + hoaDon.getHinhThuc(), font));
       
        tongTienInfo.add(new Chunk("\nTiền Khách Đưa: " + (tienMat + tienChuyenKhoan), font));
        tongTienInfo.add(new Chunk("\nTiền Thừa: " + tienThua, font));

        return tongTienInfo;
    }
}
