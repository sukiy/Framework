package eud.Util.HandleImpl;

import eud.Util.ResultSetHandler;
import eud.Util.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sukiy on 2017/12/4.
 */
public class BeanHandler<T> implements ResultSetHandler<T> {

    private Class<T> beanClazz;

    public BeanHandler(Class<T> beanClazz){
        this.beanClazz = beanClazz;
    }


    public T handle(ResultSet rs) throws SQLException{
        return rs.next()? RowProcessor.toBean(rs,beanClazz):null;
    }
}
