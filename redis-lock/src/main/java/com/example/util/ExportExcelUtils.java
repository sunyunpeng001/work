package com.example.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.OutputStream;
import java.util.List;

public class ExportExcelUtils {

    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)18); //设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗
        font.setFontName("宋体"); //设置字体名字
        HSSFCellStyle style = workbook.createCellStyle(); //设置样式;
        style.setFont(font); //在样式用应用设置的字体;
        style.setWrapText(false); //设置自动换行;
        style.setAlignment(HorizontalAlignment.CENTER); //设置水平对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER); //设置垂直对齐的样式为居中对齐;
        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont(); // 设置字体
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setWrapText(false);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    public void exportExcel(HSSFWorkbook workbook, int sheetNum,
                            String sheetTitle, String[] headers, List<List<String>> result,
                            // 生成一个表格
                            OutputStream out) throws Exception {
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellStyle(this.getColumnTopStyle(workbook));
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 1;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    HSSFCell cell = row.createCell((short) cellIndex);
                    /*cell.setEncoding(HSSFCell.ENCODING_UTF_16);*/
                    cell.setCellValue(str.toString());
                    cell.setCellStyle(this.getStyle(workbook));
                    cellIndex++;
                }
                index++;
            }
        }

    }
}
