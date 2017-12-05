package eud.Util.HandleImpl;

import eud.Util.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sukiy on 2017/12/5.
 */
public class BeanListHandler extends AbstractListHandler{

    private Class<?> clazz;

    public BeanListHandler(Class<?> clazz){
        this.clazz=clazz;
    }

    protected Object getRow(ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException {
        return RowProcessor.toBean(resultSet,clazz);
    }
}
