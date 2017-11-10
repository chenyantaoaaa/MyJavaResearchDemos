package com.mybatis.mapper;

import com.pojo.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author yantao.chen
 * @date 2017/11/8.
 */
public class SqlSessionTest {
    private UserMapper mapper;

    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        InputStream fis = null;
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession   = sqlSessionFactory.openSession();
            this.sqlSession = sqlSession;
//            mapper = sqlSession.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1() {
        UserInfo info = new UserInfo();
        List<UserInfo> userInfoList = this.mapper.selectUserDetail();
        System.out.println(userInfoList);
    }

    @Test
    public void test2(){
        List<UserInfo> list = sqlSession.selectList("com.mybatis.mapper.UserMapper.selectUserDetail");
        System.out.println(list);
    }
}
