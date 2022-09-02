package io.github.cocodx.poi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import io.github.cocodx.dto.TestReadDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author amazfit
 * @date 2022-09-02 上午11:38
 **/
public class WriteHead {

    static Map<String,String> excelHead = new HashMap<>();
    static {
        excelHead.put("机构","group");
        excelHead.put("归属机构","inGroup");
        excelHead.put("状态","status ");
        excelHead.put("用户名称","userName");
        excelHead.put("密码","password");
        excelHead.put("用户分属公司","company");
        excelHead.put("电子邮箱","email");
        excelHead.put("手机号","mobile");
        excelHead.put("真实姓名","trueName");
        excelHead.put("昵称","nickName");
    }

    //写表头
    //然后再通过easypoi去进行读取数据，返回数据再进行解析
    @Test
    public void writeHead()throws Exception{
        Map<String,Integer> columnFile1 = new HashMap<>();
        columnFile1.put("机构",1);
        columnFile1.put("归属机构",2);
        columnFile1.put("状态",3);
        columnFile1.put("用户分属公司",4);
        columnFile1.put("电子邮箱",6);
        columnFile1.put("手机号",8);
        List<Map<String,Integer>> fileColumn = new ArrayList<>();
        fileColumn.add(columnFile1);

        Map<String,Integer> columnFile2 = new HashMap<>();
        columnFile2.put("电子邮箱",6);
        columnFile2.put("手机号",8);
        fileColumn.add(columnFile2);

        List<Map<String,Integer>> fileColumn2 = new ArrayList<>();
        //我给他添加一行表头吗
//        File fileExcel = new File("d:/添加表头.xls");
//        //xls
//        Workbook workbook = new HSSFWorkbook();
//        Sheet sheetAt = workbook.getSheetAt(0);
        //获取第一行

        for (int i = 0; i < fileColumn.size(); i++) {
            Map<String, Integer> map = fileColumn.get(i);

            Map<String, Integer> propertyColumn = new HashMap<>();

            Workbook workbook = new HSSFWorkbook();
            Sheet sheetAt = workbook.createSheet("用户数据");
            File fileExcel = new File("d:/表头"+ UUID.randomUUID() +".xls");
            Row row = sheetAt.createRow(0);

            for (String chineseName:map.keySet()){
                String propertyName = excelHead.get(chineseName);
                Integer index = map.get(chineseName);
                propertyColumn.put(propertyName,index);

                Cell cell = row.createCell(index);
                cell.setCellValue(chineseName);

            }

            FileOutputStream outputStream = new FileOutputStream(fileExcel);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

            fileColumn2.add(propertyColumn);
        }
    }

    /**
     * 读取excel文件，easypoi
     */
    @Test
    public void readExcel()throws Exception{
        File file = new File("d:/表头9aa2cdbc-8631-43e8-8c07-035dc2549518.xls");
        FileInputStream stream = new FileInputStream(file);
        ImportParams importParams = new ImportParams();
        importParams.setStartSheetIndex(0);
        //去掉空行
        importParams.setNeedVerify(true);
        List<TestReadDto> objects = ExcelImportUtil.importExcel(stream, TestReadDto.class, new ImportParams());
        for(TestReadDto testReadDto:objects){
            System.out.println(testReadDto);
        }
    }

    /**
     * 判断后缀名是否相同
     */
    @Test
    public void getEndWith(){
        String fileName = "name.txt";
        System.out.println(fileName.endsWith(".txt"));
    }
}
