package com.mybatis.imitateMybatis;

import org.junit.Test;

/**
 * @author chenyantao
 * @date 2017/11/10.
 */
public class TestM {
    @Test
    public void test1(){
        MySqlSession mySqlSession = new MySqlSession("mybatis-config.xml","com/mybatis/mapper/UserMapper.xml");
//        List<String> param = new ArrayList<>();
//        param.add("chen");
        String result = mySqlSession.select2("selectUserDetail");
        System.out.println(result);
    }
}
