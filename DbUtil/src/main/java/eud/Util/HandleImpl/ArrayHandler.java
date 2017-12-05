package eud.Util.HandleImpl;

import eud.Util.ResultSetHandler;
import eud.Util.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sukiy on 2017/12/5.
 */
public class ArrayHandler implements ResultSetHandler<Object[]> {

    public Object[] handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        return rs.next()? RowProcessor.toArray(rs):null;
    }
}
