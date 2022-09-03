package io.github.cocodx;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amazfit
 * @date 2022-08-31 下午8:56
 **/
public class Test1 {

    /**
     * 创建一个xls,是03版本的，支持65537最大写入
     */
    @Test
    public void test1()throws Exception{
        //创建一个工作簿
        Workbook wb = new HSSFWorkbook();
        //创建一个工作表 xls
        Sheet sheet = wb.createSheet("用户数据");
        //创建一个行
        Row row = sheet.createRow(0);
        //创建一个单元格
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("用户名");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("真实姓名");

        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("刚子哥");
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue("刘刚");

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\用poi搞出来的工作铺子03.xls");
        wb.write(fileOutputStream);
        //关闭流
        fileOutputStream.close();
    }

    /**
     * 创建xlsx
     */
    @Test
    public void test2(){
        //获取根目录
        System.out.println(System.getProperty("user.dir"));

    }

    /**
     * 把第一行写入到最后一行
     */
    @Test
    public void test3()throws Exception{
        File file = new File("d:/续写文件.xls");
        File file1 = new File("d:/续写文件1.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(file1);
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Row firstRow = sheet.getRow(0);
        Row lastRow = sheet.createRow(sheet.getLastRowNum() + 1);
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            Cell cell = lastRow.createCell(i);
            cell.setCellValue(firstRow.getCell(i).getStringCellValue());
        }
        workbook.write(outputStream);
        outputStream.close();
        List<Cell> firstRowCells = new ArrayList<>();
    }

    /**
     *
     */
    public void test(){

    }

}
