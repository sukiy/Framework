package eud.Util;

import eud.entity.Stu;

import java.sql.*;

/**
 * Created by Sukiy on 2017/12/4.
 */
public class DbConn {

    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/test?userUnicode=true&characterEncoding=utf-8";
    private static String user="root";
    private static String password="root";

    //注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
    }

    //获取数据库连接
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭数据库资源
    public static void closeDb(ResultSet rs, Statement st,Connection conn){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (st!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws SQLException {
        Connection conn=DbConn.getConnection();
        System.out.println(conn.getAutoCommit());

    }
}
