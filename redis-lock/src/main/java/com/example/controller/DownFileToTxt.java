package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DownFileToTxt {

    @RequestMapping("/exportTxt")
    public void  exportTxt(HttpServletResponse response) {
        List list = new ArrayList();
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");

        response.setContentType("text/plain");// 一下两行关键的设置
        response.addHeader("Content-Disposition","attachment;filename=20211220.txt");
        BufferedOutputStream buff = null;
        StringBuffer stringBuffer = new StringBuffer();
        String tab = "  ";
        ServletOutputStream outSTr = null;
        try {
            outSTr = response.getOutputStream();

            buff = new BufferedOutputStream(outSTr);
            for (int i=0;i<list.size();i++){
                stringBuffer.append(list.get(i)+" ,");
            }

            buff.write(stringBuffer.toString().getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
