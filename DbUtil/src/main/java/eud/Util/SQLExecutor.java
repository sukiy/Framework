package eud.Util;

import java.sql.*;

/**
 * Created by Sukiy on 2017/12/4.
 */
public class SQLExecutor {

    private Connection connection;
    private boolean autoClose=true;

    public SQLExecutor(Connection connection){
        this.connection=connection;
    }

    //开启事务
    public void beginTransaction(){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        autoClose=false;
    }

    //提交事务
    public void commit() throws SQLException {
        if (!connection.getAutoCommit()){
            try {
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                close();
            }
        }
    }

    //回滚事务
    public  void rollBack() {
        try {
            connection.rollback();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //执行事务
    public <T>T executeQuery(String sql,ResultSetHandler<T> handler,Object... args) throws SQLException {
        if (connection==null){
            throw new SQLException("null connection...");
        }
        if (sql==null){
            close();
            throw new SQLException("null sql statement...");
        }
        PreparedStatement ps=connection.prepareStatement(sql);
        setParams(args,ps);
        ResultSet rs=ps.executeQuery();
        T t= null;
        try {
            t = handler.handle(rs);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 执行DML语句
     * @param sql
     * @param args
     * @return
     * @throws SQLException
     * @Object... args 动态参数，可变参数
     */
    public int executeUpdate(String sql,Object... args) throws SQLException {
        if (connection==null){
            throw  new SQLException("null connection...");
        }
        if (sql==null){
            throw new SQLException("null sql...");
        }
        int row=0;
        PreparedStatement ps=null;

        ps=connection.prepareStatement(sql);
        setParams(args,ps);

        row=ps.executeUpdate();
        close(ps);
        if (autoClose){
            close();
        }
        return row;
    }

    //替换参数
    private void setParams(Object[] params,PreparedStatement ps){
        for (int i=0;i<params.length;i++){
            try {
                ps.setObject(i+1,params[i]);
            } catch (SQLException e) {
                throw new RuntimeException("Exception in setParams...");
            }
        }
    }


    //关闭结果集
    private void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    //关闭语句对象
    private void close(Statement st) throws SQLException {
        st.close();
    }

    //关闭连接
    private void close() throws SQLException {
        if (connection!=null&&!connection.isClosed()){
            connection.close();
        }
    }

}
