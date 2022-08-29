package io.github.cocodx.controller;

import com.alibaba.excel.EasyExcel;
import io.github.cocodx.dto.UserDto;
import io.github.cocodx.entity.User;
import io.github.cocodx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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

    @RequestMapping("/test")
    public List<User> findList(){
        return userService.findList();
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        String fileName = "user.xlsx";
        OutputStream outputStream = responseSetHeader(response, fileName);
        List<User> userData = userService.findList();
        EasyExcel.write(outputStream).head(UserDto.class).sheet("用户数据").doWrite(userData);
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
