package eud.Util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据行转换处理
 * Created by Sukiy on 2017/12/4.
 */
public class RowProcessor {

    /**
     * 将结果集转换成bean对象
     * @param rs
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toBean(ResultSet rs,Class<T> type){
         return BeanUtil.createBean(rs,type);
    }

    /**
     * 将结果集存入map集合
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static Map<String,Object> toMap(ResultSet resultSet) throws SQLException {
        Map<String,Object> map=new HashMap<String, Object>();
        //创建操作工具
        ResultSetMetaData mtd=resultSet.getMetaData();
        //遍历结果集
        for (int i=1;i<mtd.getColumnCount();i++){
            map.put(mtd.getColumnLabel(i),resultSet.getObject(i));
        }
        return map;
    }

    /**
     *将结果集存放在数组
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static Object[] toArray(ResultSet resultSet) throws SQLException {
        ResultSetMetaData mtd=resultSet.getMetaData();
        Object[] objects=new Object[mtd.getColumnCount()];
        for (int i=0;i<mtd.getColumnCount();i++){
            objects[i]=resultSet.getObject(i+1);
        }
        return objects;
    }

}
