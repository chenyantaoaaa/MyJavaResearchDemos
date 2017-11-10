package com.mybatis.imitateMybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * @author chenyantao
 * @date 2017/11/10.
 */
public class MySqlSession {
    private MyConfiguration myConfiguration;

    private MyMapper myMapper;

    public MySqlSession(String configName,String mapperName) {
        myConfiguration = new MyConfiguration(configName);
        myMapper = new MyMapper(mapperName,configName);
    }

    private Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(myConfiguration.getMyEnviroment().getDriver());
            String url = myConfiguration.getMyEnviroment().getUrl();
            String user = myConfiguration.getMyEnviroment().getUsername();
            String pwd = myConfiguration.getMyEnviroment().getPassword();
            connection = DriverManager.getConnection(url,user,pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 传入sql字符串进行查询
     * @param sql
     * @param param
     * @return
     */
    public String select1(String sql, List<String> param){
        Connection con = getConnection();
        try {
            StringBuffer b = new StringBuffer();

            PreparedStatement st = con.prepareStatement(sql);
            int paramNum = 1;
            for (String s : param) {
                st.setString(paramNum,s);
                paramNum++;
            }
            st.execute();
            ResultSet rs = st.getResultSet();
            ResultSetMetaData data = rs.getMetaData();
            int col = data.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <=col; i++) {
                    b.append(data.getColumnName(i) + "=");
                    b.append(rs.getString(i) + "\t");
                }
                b.append("\r\n");
            }
            return b.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 传入mapper中配置的sqlId进行查询
     * @param sqlId
     * @return
     */
    public String select2(String sqlId){
        Connection con = getConnection();
        String sql = null;
        sql = myMapper.getSqlById(sqlId);
        try {
            StringBuffer b = new StringBuffer();
            PreparedStatement st = con.prepareStatement(sql);
            st.execute();
            ResultSet rs = st.getResultSet();
            ResultSetMetaData data = rs.getMetaData();
            int col = data.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <=col; i++) {
                    b.append(data.getColumnName(i) + "=");
                    b.append(rs.getString(i) + "\t");
                }
                b.append("\r\n");
            }
            return b.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MyConfiguration getMyConfiguration() {
        return myConfiguration;
    }

    public void setMyConfiguration(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public MyMapper getMyMapper() {
        return myMapper;
    }

    public void setMyMapper(MyMapper myMapper) {
        this.myMapper = myMapper;
    }
}
