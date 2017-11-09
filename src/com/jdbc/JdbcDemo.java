package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author yantao.chen
 * @date 2017/11/7.
 */
public class JdbcDemo {
    private static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://47.91.77.217:3306/zzttravel?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String pwd = "jg9n3gkzeKisJV7";
            connection = DriverManager.getConnection(url,user,pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void QueryDemo(){
        Connection con = getConnection();
        try {
            StringBuffer b = new StringBuffer();
            String sql = "select * from pv_info where country = ? ";
//            Statement st = con.createStatement();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,"中国");
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
            System.out.println(b.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QueryDemo();
    }
}
