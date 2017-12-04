package eud.Util;

import java.sql.SQLException;

/**
 * Created by Sukiy on 2017/12/4.
 */
public class SQLExecutorContext {

    private static ThreadLocal<SQLExecutor> local;

    public static SQLExecutor currentSQLExcetor(){
        if (local.get()==null){
            local.set(new SQLExecutor((DbConn.getConnection())));
        }
        return local.get();
    }

    //移除当前线程的SQLExcetor
    public void close(){
        local.remove();
    }
}

