package io.github.cocodx.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

/**
 * 创建excel
 * @author amazfit
 * @date 2022-08-31 下午8:10
 **/
public class Test1 {

    public static void main(String[] args)throws Exception{
        //创建一个工作簿
        Workbook wb = new HSSFWorkbook();
        //创建一个工作表
        Sheet sheet = wb.createSheet("用户数据");
        //创建一个行
        Row row = sheet.createRow(0);
        //创建一个单元格
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("用户名");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("真实姓名");

        Row row2 = sheet.createRow(1);
        Cell cell21 = row.createCell(0);
        cell21.setCellValue("刚子哥");
        Cell cell22 = row.createCell(1);
        cell22.setCellValue("刘刚");

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\用poi搞出来的工作铺子03.xls");
        wb.write(fileOutputStream);
        //关闭流
        fileOutputStream.close();
    }
}
