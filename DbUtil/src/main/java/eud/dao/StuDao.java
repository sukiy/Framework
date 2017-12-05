package eud.dao;


import eud.Util.DbConn;
import eud.Util.HandleImpl.BeanHandler;
import eud.Util.ResultSetHandler;
import eud.Util.SQLExecutor;
import eud.entity.Stu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/11/30.
 */
public class StuDao {


    //添加学生信息
    public int addStu(Stu stu){
        int row=0;
        String sql="insert into Stu(s_name,s_sex) values(?,?)";
        SQLExecutor se=new SQLExecutor(DbConn.getConnection());
        se.beginTransaction();
        try {
            row=se.executeUpdate(sql,stu.getSname(),stu.getSex());
            se.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            se.rollBack();
        }
        return row;
    }

    //根据id查询学生信息
    public Stu findStuById(int id){
        String sql="select * from tb_stu where s_no=?";
        Stu stu=null;
        SQLExecutor se=new SQLExecutor(DbConn.getConnection());
        ResultSetHandler<Stu> handler=new BeanHandler<Stu>(Stu.class);

        try {
            stu=se.executeQuery(sql,handler,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stu;
    }


    public static void main(String[] args) {
        /*
        根据id查询单条记录
        Stu stu=new StuDao().findStuById(1);
        System.out.println(stu.getSname()+","+stu.getSex());*/


    }

}
