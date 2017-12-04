package eud.dao;


import eud.Util.DbConn;
import eud.Util.HandleImpl.BeanHandler;
import eud.Util.ResultSetHandler;
import eud.Util.SQLExecutor;
import eud.entity.Stu;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/11/30.
 */
public class StuDao {


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
        /*Users user = new Users();
        user.setAccount("bl5657");
        user.setNickName("mmp");
        user.setPassWord("123");
        int row = new UserDao().save(user);
        System.out.println(row);*/

        Stu stu=new StuDao().findStuById(1);
        System.out.println(stu.getSname());
        System.out.println(stu.getSex());
    }

}
