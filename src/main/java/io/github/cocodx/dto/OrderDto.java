package io.github.cocodx.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @author amazfit
 * @date 2022-08-31 下午11:38
 **/
@ExcelTarget("orders")
@Data
public class OrderDto implements Serializable {

    @Excel(name = "订单编号",width = 20.0)
    private String no;

    @Excel(name = "订单名称",width = 15.0)
    private String name;
}
