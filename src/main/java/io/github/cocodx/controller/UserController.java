package io.github.cocodx.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.github.cocodx.dto.UserDto;
import io.github.cocodx.entity.User;
import io.github.cocodx.service.UserService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        EasyExcel.write(outputStream).head(UserDto.class).sheet("用户数据").doWrite(userData);
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
        EasyExcel.write(bos).head(UserDto.class).sheet("用户数据").doWrite(userData);

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
        ExcelWriter excelWriter = null;
        try{
            excelWriter = EasyExcel.write()
                    //指定模板文件
                    .withTemplate(new FileInputStream("123"))
                    //指定目标文件
                    .file(new File("xxxx.xlsx")).autoCloseStream(false).build();

            WriteSheet writeSheet = EasyExcel.writerSheet("用户数据").build();
            List<User> users = new ArrayList<>();
            excelWriter.write(users,writeSheet);
        }finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
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
}
