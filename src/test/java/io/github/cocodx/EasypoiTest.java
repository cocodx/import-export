package io.github.cocodx;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import io.github.cocodx.dto.CardDto;
import io.github.cocodx.dto.OrderDto;
import io.github.cocodx.dto.UserDto;
import io.github.cocodx.entity.User;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author amazfit
 * @date 2022-08-31 下午10:53
 **/
public class EasypoiTest {

    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setUserName("xiaochen_"+i);
            user.setAddress("shenzhen baoan");
            user.setCreateTime(new Date());
            users.add(user);
        }
        return users;
    }

    @Test
    public void testExport() throws IOException {
        List<User> users = getUsers();
        //参数1：exportParams：导出配置对象
        List<UserDto> collect = users.stream().map(item -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(item, userDto);
            userDto.setAge(20);
            userDto.setStatus("1");
            userDto.setHabbys(Arrays.asList("打篮球","看书","看片"));

            CardDto cardDto = new CardDto();
            cardDto.setNo("123456");
            cardDto.setAddress("北京市朝阳区国贸大厦三层507A");
            userDto.setCardDto(cardDto);

            List<OrderDto> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                OrderDto orderDto = new OrderDto();
                orderDto.setNo(UUID.randomUUID().toString());
                orderDto.setName("超短连衣裙123456");
                list.add(orderDto);
            }
            userDto.setOrderDtos(list);
            return userDto;
        }).collect(Collectors.toList());
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息列表", "用户信息"), UserDto.class, collect);
        //导出excel，通过流写入指定位置
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\easypoi.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }


    /**
     * CsvExportUtil 导出csv文件
     * 根据文件格式，和导出列，生成模板
     *
     * 然后easyExcel根据 这个文件去进行读写
     */
    @Test
    public void templateExport() throws IOException {
        Map<Integer,String> param = new HashMap<>();
        param.put(0,"序号");
        param.put(1,"登录账号");
        param.put(2,"用户昵称");
        param.put(3,"员工姓名");
        param.put(4,"归属机构");
        param.put(5,"归属公司");
        param.put(6,"状态");
        param.put(7,"更新时间");
        param.put(8,"电子邮箱");
        param.put(9,"手机号码");
        param.put(10,"办公号码");
        //创建一个工作簿
        Workbook wb = new HSSFWorkbook();
        //创建一个工作表
        Sheet sheet = wb.createSheet("用户数据");
        //创建一个行
        Row row = sheet.createRow(0);
        for (int i = 0; i < param.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(param.get(i));
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\根据格式和列导出模板.xls");
        wb.write(fileOutputStream);
        //关闭流
        fileOutputStream.close();

    }

    /**
     *
     */
    @Test
    public void exportDataWithTemplate() throws IOException {
        try {
            //表头
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
            //构造对象等同于@Excel
            entity.add(new ExcelExportEntity("省份","province",30));
            entity.add(new ExcelExportEntity("城市","city",30));
//            ExcelExportEntity excelentity = new ExcelExportEntity("姓名", "name");
//            excelentity.setNeedMerge(true);
//            entity.add(excelentity);
//            entity.add(new ExcelExportEntity("性别", "sex"));
//            excelentity = new ExcelExportEntity(null, "students");

//            List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
//            temp.add(new ExcelExportEntity("姓名", "name"));
//            temp.add(new ExcelExportEntity("性别", "sex"));
//            //构造List等同于@ExcelCollection
//            excelentity.setList(temp);
//            entity.add(excelentity);

            //数据
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < 10; i++) {
                Map<String,Object> value = new HashMap<>();
                value.put("province","北京");
                value.put("city","北京");
                list.add(value);
            }

            //把我们构造好的bean对象放到params就可以了
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entity, list);
            FileOutputStream fos = new FileOutputStream("D:\\easypoi自由导出列名.csv");
            workbook.write(fos);
            fos.close();
            workbook.close();


            //追加一行
            Map<String,Object> param = new HashMap<>();
            Workbook workbook1 = ExcelExportUtil.exportExcel(new TemplateExportParams("D:\\easypoi自由导出列名.xls"), param);

            Sheet sheetAt = workbook1.getSheetAt(0);
            int lastRowNum = sheetAt.getLastRowNum();
            Row row = sheetAt.createRow(lastRowNum + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue("追加一行");
            FileOutputStream fos1 = new FileOutputStream("D:\\easypoi自由导出列名.xls");
            workbook1.write(fos1);
            fos1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义，追加自定义内容操作
     */
    @Test
    public void testAddFileMethod()throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\用poi搞出来的工作铺子03.xls");
        Map<String,Object> param = new HashMap<>();
//        param.put("序号","key");
//        param.put("登录账号","userName");
        Workbook workbook = ExcelExportUtil.exportExcel(new TemplateExportParams("D:\\根据格式和列导出模板.xls"), param);

        //拿第一个sheet
        Sheet sheetAt = workbook.getSheetAt(0);

        int lastRowNum = sheetAt.getLastRowNum();

        for (int i = 1; i < 11; i++) {
            Row row = sheetAt.createRow(lastRowNum + i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(i);
            }
        }

        System.out.println(lastRowNum);

//        FileOutputStream fileOutputStream = new FileOutputStream("D:\\续写文件.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();

    }

    @Test
    public void test(){
        System.out.println(Math.ceil(11/2.00));
    }

}
