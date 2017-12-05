package eud.Util.HandleImpl;

import eud.Util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sukiy on 2017/12/5.
 */
public abstract class AbstractListHandler<T> implements ResultSetHandler<List<T>> {


    public List<T> handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        List<T> rows=new ArrayList<T>();
        while (rs.next()){
            rows.add(getRow(rs));
        }
        return rows;
    }

    protected abstract T getRow(ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException;
}
