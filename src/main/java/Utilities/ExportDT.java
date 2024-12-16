/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import ViewModel.ThongKeView;
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
public class ExportDT {
    
    // Các chỉ số của cột trong bảng Excel
    public static final int COLUMN_THANG = 0; // thang
    public static final int COLUMN_SOLUONG = 1; // so luong ban
//    public static final int COLUMN_KHOANCHI = 2;
    public static final int COLUMN_DOANHTHU = 2; // doanh thu
//    public static final int COLUMN_TIENLOI = 4;
    
    // ghi dữ liệu từ danh sách ThongKeView vào tệp Excel
    public static void writeExcel(List<ThongKeView> list, String excelFilePath) throws IOException {
        // Lấy đối tượng Workbook tương ứng với định dạng của tệp Excel
        Workbook workbook = getWorkbook(excelFilePath);
        
        // Tạo một sheet mới trong Workbook với tên "Doanh Thu"
        Sheet sheet = (Sheet) workbook.createSheet("Doanh Thu");
        int rowIndex = 0;
        // Ghi tiêu đề
        writeHeader(sheet, rowIndex);
        rowIndex++;
        
        // Duyệt qua danh sách và ghi từng dòng vào sheet
        for (ThongKeView x : list) {
            Row row = sheet.createRow(rowIndex);
            writeBook(x, row);
            rowIndex++;
        }
        
        // Tự động điều chỉnh kích thước cột cho phù hợp với dữ liệu
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
    
    // Lấy đối tượng Workbook (Excel) dựa trên phần mở rộng tệp
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // ghi tiêu đề các cột vào dòng đầu tiên của sheet
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // Tạo kiểu định dạng cho tiêu đề cột
        CellStyle cellStyle = createStyleHeader(sheet);
        
         // Tạo một dòng mới cho tiêu đề
        Row row = sheet.createRow(rowIndex);
        
        // Tạo và ghi nội dung vào các cột
        Cell cell = row.createCell(COLUMN_THANG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tháng");

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Lượng Bán Ra");
//        
//        cell = row.createCell(COLUMN_KHOANCHI);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Khoản Chi");

        cell = row.createCell(COLUMN_DOANHTHU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("DoanhThu");

//        cell = row.createCell(COLUMN_TIENLOI);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("Tiền Lời");
    }
    
    // ghi dữ liệu của một đối tượng ThongKeView vào dòng Excel
    private static void writeBook(ThongKeView dt, Row row) {
        
        //// Tạo đối tượng để định dạng ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
         // Ghi các giá trị từ đối tượng ThongKeView vào các ô trong dòng
        Cell cell = row.createCell(COLUMN_THANG);
        cell.setCellValue(dt.getThang());

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellValue(dt.getSoLuong());

//        cell = row.createCell(COLUMN_KHOANCHI);
//        cell.setCellValue(dt.getKhoanChi());
        cell = row.createCell(COLUMN_DOANHTHU);
        cell.setCellValue(dt.getDoanhThu());
//
//        cell = row.createCell(COLUMN_TIENLOI);
//        cell.setCellValue(dt.getTienLoi());

    }

    // Tạo kiểu định dạng cho tiêu đề các cột trong sheet
    private static CellStyle createStyleHeader(Sheet sheet) {
        // Tạo font chữ cho tiêu đề
        Font font = sheet.getWorkbook().createFont();
//        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Tạo kiểu định dạng cho cell
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    
    // vđiều chỉnh kích thước các cột trong sheet.
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    
    // Ghi Workbook ra tệp Excel.
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
