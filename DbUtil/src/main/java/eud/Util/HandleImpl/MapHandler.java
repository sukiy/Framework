package eud.Util.HandleImpl;

import eud.Util.ResultSetHandler;
import eud.Util.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Sukiy on 2017/12/5.
 */
public class MapHandler implements ResultSetHandler<Map<String,Object>>{

    public Map<String, Object> handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        return rs.next()? RowProcessor.toMap(rs):null;
    }
}
