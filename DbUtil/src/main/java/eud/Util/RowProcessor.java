package eud.Util;

import java.sql.ResultSet;

/**
 * 数据行转换处理
 * Created by Sukiy on 2017/12/4.
 */
public class RowProcessor {

    public static <T> T toBean(ResultSet rs,Class<T> type){
         return BeanUtil.createBean(rs,type);
    }
}
