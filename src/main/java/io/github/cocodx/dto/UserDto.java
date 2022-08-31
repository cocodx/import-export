package io.github.cocodx.dto;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName t_user
 *
 * @ExcelCollection 一对多
 * @ExcelEntity 一对一
 */
@Data
@ExcelTarget("userDto")
public class UserDto implements Serializable {
    /**
     * name 生成excel中的列名
     *
     */
    @Excel(name = "编号")
    private Long id;

    /**
     * 
     */
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 
     */
    @Excel(name = "密码")
    private String password;

    /**
     * 
     */
    @Excel(name = "别名")
    private String nickName;

    /**
     * 
     */
    @Excel(name = "真实姓名")
    private String trueName;

    /**
     * 
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 
     */
    @Excel(name = "身份证")
    private Integer idNumber;

    @Excel(name = "年龄" , suffix = " &&&" ,replace = {"20岁_20"})
    private Integer age;

    @Excel(name="状态" ,replace = {"激活_1","锁定_0"})
    private String status;

    /**
     * 忽略这个属性
     */
    @ExcelIgnore
    private Long createBy;

    /**
     * 
     */
    @ExcelIgnore
    @Excel(name = "更新者")
    private Long updateBy;

    /**
     * 
     */
    @Excel(name = "创建时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 
     */
    @Excel(name = "更新时间")
    private Date updateTime;

    /**
     * 
     */
    @Excel(name = "备注")
    private String remark;


    /**
     * 用户的喜好
     */
    @Excel(name = "爱好",width = 20.0)
    @ExcelIgnore
    private List<String> habbys;

    @Excel(name = "爱好",width = 20.0)
    private String habbysStr;

    public String getHabbysStr() {
        StringBuilder stringBuilder = new StringBuilder();
        habbys.stream().forEach(e->{
            stringBuilder.append(e).append("、");
        });
        return stringBuilder.toString();
    }
}