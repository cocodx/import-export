package io.github.cocodx;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.github.cocodx.dto.CardDto;
import io.github.cocodx.dto.UserDto;
import io.github.cocodx.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
            return userDto;
        }).collect(Collectors.toList());
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息列表", "用户信息"), UserDto.class, collect);
        //导出excel，通过流写入指定位置
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\easypoi.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }
}
