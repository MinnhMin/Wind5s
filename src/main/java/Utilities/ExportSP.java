/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;
import Model.ChiTietSanPham;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author admin
 */
public class ExportSP {
    
    // Các chỉ số cột trong file Excel
    public static final int COLUMN_MA = 0;
    public static final int COLUMN_SP = 1;
    public static final int COLUMN_SOLUONG = 2;
    public static final int COLUMN_GIABAN = 3;
    public static final int COLUMN_GIANHAP = 4;
    public static final int COLUMN_CHAT_LIEU = 5;
    public static final int COLUMN_KICH_THUOC = 6;
    public static final int COLUMN_MAUSAC = 7;
    public static final int COLUMN_CHATLIEU = 8;
    public static final int COLUMN_XUATXU = 9;    
    public static final int COLUMN_THUONG_HIEU = 10;
    public static final int COLUMN_MOTA = 11;
    public static final int COLUMN_NGAYTAO = 12;
    public static final int COLUMN_NGAYSUA = 13;
    public static final int COLUMN_TINHTRANG = 14;
    public static final int COLUMN_HINHANH = 15;
    
    // xuất danh sách các chi tiết sản phẩm vào file Excel.
    public static void writeExcel(List<ChiTietSanPham> list, String excelFilePath) throws IOException {
        
        //// Tạo Workbook tương ứng với file Excel
        Workbook workbook = getWorkbook(excelFilePath);
        
        // Tạo sheet "Sản Phẩm" trong workbook
        Sheet sheet = workbook.createSheet("Sản Phẩm");
        int rowIndex = 0;
        
        // Ghi tiêu đề cho sheet
        writeHeader(sheet, rowIndex);
        rowIndex++;
        
        // Ghi thông tin chi tiết sản phẩm vào từng dòng trong sheet
        for (ChiTietSanPham x : list) {
            Row row = sheet.createRow(rowIndex);
            writeBook(x, row);
            rowIndex++;
        }
        
        // Điều chỉnh độ rộng cột cho phù hợp với nội dung
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        
        // Lưu file Excel ra ổ đĩa
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
    
    //Tạo Workbook (Excel) tương ứng với định dạng của file.
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            // Dùng XSSFWorkbook cho file .xlsx
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            // Dùng HSSFWorkbook cho file .xls
            workbook = new HSSFWorkbook();
        } else {
            // Ném lỗi nếu file không phải định dạng Excel
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Ghi tiêu đề cho các cột trong sheet Excel.
    private static void writeHeader(Sheet sheet, int rowIndex) {

        CellStyle cellStyle = createStyleHeader(sheet);

        Row row = sheet.createRow(rowIndex);

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã");

        cell = row.createCell(COLUMN_SP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Sản Phẩm");

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Lượng");

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá Bán");

        cell = row.createCell(COLUMN_GIANHAP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá Nhập");

        cell = row.createCell(COLUMN_CHAT_LIEU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chất Liệu");

        cell = row.createCell(COLUMN_KICH_THUOC);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Kích Thước");

        cell = row.createCell(COLUMN_MAUSAC);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Màu Sắc");

        cell = row.createCell(COLUMN_CHATLIEU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chất Liệu");

        cell = row.createCell(COLUMN_XUATXU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Xuất Xứ");
        
        cell = row.createCell(COLUMN_THUONG_HIEU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thương Hiệu");
            
        cell = row.createCell(COLUMN_MOTA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mô Tả");

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Tạo");

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Sửa");

        cell = row.createCell(COLUMN_TINHTRANG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tình Trạng");

    }
    
    // Ghi dữ liệu chi tiết sản phẩm vào các ô trong một dòng.
    private static void writeBook(ChiTietSanPham ctd, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellValue(ctd.getMa());

        cell = row.createCell(COLUMN_SP);
        cell.setCellValue(ctd.getSanPham().getTen());

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellValue(ctd.getSoLuong());

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellValue(ctd.getGiaBan().doubleValue());

        cell = row.createCell(COLUMN_GIANHAP);
        cell.setCellValue(ctd.getGiaNhap().doubleValue());

        cell = row.createCell(COLUMN_CHAT_LIEU);
        cell.setCellValue(ctd.getSanPham().getTen());

        cell = row.createCell(COLUMN_KICH_THUOC);
        cell.setCellValue(ctd.getKichThuoc().getTen());

        cell = row.createCell(COLUMN_MAUSAC);
        cell.setCellValue(ctd.getMauSac().getTen());

        cell = row.createCell(COLUMN_CHATLIEU);
        cell.setCellValue(ctd.getChatLieu().getTen());

        cell = row.createCell(COLUMN_XUATXU);
        cell.setCellValue(ctd.getSanPham().getXuatXu().getTen());
        
        cell = row.createCell(COLUMN_THUONG_HIEU);
        cell.setCellValue(ctd.getSanPham().getThuongHieu().getTen());
        
        cell = row.createCell(COLUMN_MOTA);
        cell.setCellValue(ctd.getMoTa());

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellValue(sdf.format(ctd.getNgayTao()));

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellValue(sdf.format(ctd.getNgaySua()));

        cell = row.createCell(COLUMN_TINHTRANG);
        if (ctd.getTinhTrang() == 0) {
            cell.setCellValue("Ðang Kinh Doanh");
        } else if (ctd.getTinhTrang() == 1) {
            cell.setCellValue("Ngừng Kinh Doanh");
        } else {
            cell.setCellValue("Nulls");
        }
       

    }

    // Tạo style cho tiêu đề của các cột
    private static CellStyle createStyleHeader(Sheet sheet) {
        // Tạo font
        Font font = sheet.getWorkbook().createFont();
//        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Tạo style
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    
    //điều chỉnh kích thước cột trong sheet.
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    
    //Lưu workbook vào file Excel.
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try ( OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
