package io.github.cocodx.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

/**
 * @author amazfit
 * @date 2022-09-01 下午5:32
 **/
public class PoiTest1 {


    @Test
    public void test1()throws Exception{
        Workbook workbook = new HSSFWorkbook();
        Sheet first_sheet = workbook.createSheet("first sheet");
        Row row = first_sheet.createRow(2);
        row.setHeightInPoints(25);

        createCell(workbook,row,  0);
        createCell(workbook,row,  1);
        createCell(workbook,row,  2);
        createCell(workbook,row,  3);
        createCell(workbook,row,  4);

        FileOutputStream outputStream = new FileOutputStream("d:/单元格式.xls");
        workbook.write(outputStream);
        outputStream.close();
    }

    /**
     * 创建一个单元格，设置指定的对齐方式
     * @param workbook
     * @param row
     * @param column
     */
    private static void createCell(Workbook workbook,Row row,Integer column){
        Cell cell = row.createCell(column);//创建单元格
        cell.setCellValue("");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
    }
}
