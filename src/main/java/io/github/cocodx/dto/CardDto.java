package io.github.cocodx.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @author amazfit
 * @date 2022-08-31 下午11:32
 **/
@ExcelTarget("card")
@Data
public class CardDto implements Serializable {

    @Excel(name = "身份证号码",width = 20.0)
    private String no;

    @Excel(name = "籍贯",width = 40.0)
    private String address;
}
