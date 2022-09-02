package io.github.cocodx.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.ToString;

/**
 * @author amazfit
 * @date 2022-09-02 上午11:56
 **/
@Data
@ToString
@ExcelTarget("testReadDto")
public class TestReadDto {
    @Excel(name = "机构")
    private String group;
    @Excel(name = "归属机构")
    private String inGroup;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "用户名称")
    private String userName;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "用户分属公司")
    private String company;
    @Excel(name = "电子邮箱")
    private String email;
    @Excel(name = "手机号")
    private String mobile;
    @Excel(name = "真实姓名")
    private String trueName;
    @Excel(name = "昵称")
    private String nickName;
}
