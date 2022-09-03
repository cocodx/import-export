package io.github.cocodx.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.cocodx.dto.UserDto;
import io.github.cocodx.entity.User;
import io.github.cocodx.service.UserService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amazfit
 * @date 2022-08-30 上午4:01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MinioClient minioClient;

    @RequestMapping("/test")
    public List<User> findList(){
        return userService.findList();
    }

    /**
     * 普通导出excel
     * @param response
     * @throws IOException
     */
    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        String fileName = "user.xlsx";
        OutputStream outputStream = responseSetHeader(response, fileName);
        List<User> userData = userService.findList();
    }

    @RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String realPath = ResourceUtils.getURL("classpath:").getPath()+"/excel";
        FileInputStream fileInputStream = new FileInputStream(new File(realPath, "user_template.xlsx"));
        ServletOutputStream outputStream = (ServletOutputStream) responseSetHeader(response, "user_template.xlsx");
        IOUtils.copy(fileInputStream,outputStream);
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(outputStream);

    }

    /**
     * 导出之前，将文件流写入minio
     * @param response
     * @throws IOException
     */
    @RequestMapping("/download2")
    public String download2(HttpServletResponse response) throws Exception {
        String fileName = "user.xlsx";
        //创建流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        List<User> userData = userService.findList();

        String bucket = "user";

        //检查桶是否存在
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(fileName)
                .stream(bis, bos.size(), -1)
                .contentType("application/vnd.ms-excel;charset=UTF-8")
                .build()
        );

        //返回下载路径
        return "http://localhost:9000/"+bucket+"/"+fileName;

        //最后要关闭流
    }

    /**
     * 追加写数据
     */
    public void writeFileData() throws FileNotFoundException {

    }

    /**
     * 返回输出excel格式
     * @param response
     * @return
     * @throws IOException
     */
    private OutputStream responseSetHeader(HttpServletResponse response,String fileName) throws IOException {
        response.setCharacterEncoding("UTF-8");
        /** xlsx **/
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName);
        return response.getOutputStream();
    }

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


    /**
     * 解析文件，根据传的字段中文名称去解析excel文件
     * @param file
     * @param fileHeads
     */
    @RequestMapping("/importFile")
    public void importFile(MultipartFile file,List<Map<String,Object>> fileHeads){
        List<List<Map<String,Object>>> paramHead = new ArrayList<>();

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
        File fileExcel = new File("d:/添加表头.xls");
        //xls
        Workbook workbook = new HSSFWorkbook();
        Sheet sheetAt = workbook.getSheetAt(0);
        //获取第一行
        Row row = sheetAt.getRow(0);
        for (int i = 0; i < fileColumn.size(); i++) {
            Map<String, Integer> map = fileColumn.get(i);

            Map<String, Integer> propertyColumn = new HashMap<>();
            for (String chineseName:map.keySet()){
                String propertyName = excelHead.get(chineseName);
                Integer index = map.get(chineseName);
                propertyColumn.put(propertyName,index);
                Cell cell = row.createCell(index);
                cell.setCellValue(chineseName);
            }
            fileColumn2.add(propertyColumn);
        }
    }

    /**
     * 根据excel文件，读取前10行数据
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/analysisTenRows")
    public Object analysisTenRows(MultipartFile file) throws IOException {
        //获取后缀名
        String fileSuffix = ".xlsx";
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<Map<Integer,String>> list = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        for (int i=0;i<=9;i++){
            Row row = sheet.getRow(i);
            Map<Integer,String> mapOjb = new HashMap<>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                mapOjb.put(j,formatter.formatCellValue(row.getCell(j)));
            }
            list.add(mapOjb);
        }
        workbook.close();
        return list;
    }


    /**
     * 测试接收List<Map<String,Integer>>
     * @param param [{"账号":2,"企业名称":4,"机构":3,"用户名称":0,"电子邮箱":5,"姓名":1},{"电子邮箱":3,"手机号":4}]
     * @return
     */
    @RequestMapping("/testListMap")
    public Object testListMap(@RequestParam("param") List<String> param){
        System.out.println(param);
        for (int i = 0; i < param.size(); i++) {
            String s = param.get(i);
            JSONObject jsonObject = JSONUtil.parseObj(s);
        }
        return param;
    }

}
