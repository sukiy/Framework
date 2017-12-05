package eud.Util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sukiy on 2017/12/4.
 */
public interface ResultSetHandler<T> {

    /**
     * 结果集转换，将查询的结果封装成不同的对象类型
     * @param rs
     * @return
     * @throws SQLException
     */
     T handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException;
}
