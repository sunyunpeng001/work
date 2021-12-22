package com.example.controller;

import com.example.util.ExportExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExcelController {

    @RequestMapping("/exportExcel")
    public void  exportExcel(HttpServletResponse response) {
        String fileName  = "DownTest.xlsx";
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            OutputStream out = response.getOutputStream();
            List<List<String>> data = new ArrayList<List<String>>();
            for (int i = 1; i < 5; i++) {
                List rowData = new ArrayList();
                rowData.add(String.valueOf(i));
                rowData.add("张三1");
                rowData.add("18");
                data.add(rowData);
            }
            String[] headers1 = { "列1", "列2","列3" };

            List<List<String>> data2 = new ArrayList<List<String>>();
            for (int j = 1; j < 4; j++) {
                List rowData = new ArrayList();
                rowData.add(String.valueOf(j));
                rowData.add("张三2");
                rowData.add("19");
                data2.add(rowData);
            }
            String[] headers2 = { "列11", "列22","列33" };
            ExportExcelUtils eeu = new ExportExcelUtils();
            HSSFWorkbook workbook = new HSSFWorkbook();
            eeu.exportExcel(workbook, 0, "上海", headers1, data, out);
            eeu.exportExcel(workbook, 1, "深圳", headers2, data2, out);
            //原理就是将所有的数据一起写入，然后再关闭输入流。
            workbook.write(out);
            out.close();
        }catch (Exception e){

        }


    }

}
