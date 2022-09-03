package io.github.cocodx;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.*;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * csv 测试
 * @author amazfit
 * @date 2022-09-04 上午4:11
 **/
public class CsvTest {


    /**
     * 使用hutool工具，读取csv
     */
    @Test
    public void test(){
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(FileUtil.file("d:/test.csv"));
        List<CsvRow> rows = data.getRows();
        for (CsvRow csvRow:rows){
            List<String> rawList = csvRow.getRawList();
            System.out.println(rawList);
        }
    }

    /**
     * 导入的时候，通过
     * 返回前10条数据，将文件记录到导入表中，返回id主键。
     *
     * 在上传的时候，传ids就好了，再是中文顺序
     */

    @Test
    public void test2(){
        ArrayList<Map<String, Integer>> maps = new ArrayList<>();
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        map1.put("用户名称",0);
        map1.put("姓名",1);
        map1.put("账号",2);
        map1.put("机构",3);
        map1.put("企业名称",4);
        map1.put("电子邮箱",5);

        map2.put("电子邮箱",3);
        map2.put("手机号",4);

        maps.add(map1);
        maps.add(map2);
        System.out.println(JSONUtil.parse(maps));
    }
}
