package eud.Util.HandleImpl;

import eud.Util.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Sukiy on 2017/12/5.
 */
public class MapListHandler extends AbstractListHandler<Map<String,Object>>{

    protected Map<String, Object> getRow(ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException {
        return RowProcessor.toMap(resultSet);
    }
}
