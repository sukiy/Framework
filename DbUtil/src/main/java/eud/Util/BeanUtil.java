package eud.Util;


import eud.annotation.Column;
import jdk.nashorn.internal.ir.LiteralNode;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 将结果集转换成Bean对象
 */
public class BeanUtil {

    private static Map<Class,Object> beanMap=new HashMap<Class, Object>();

    static {
        beanMap.put(Integer.TYPE,Integer.valueOf(0));
        beanMap.put(Short.TYPE,Short.valueOf((short) 0));
        beanMap.put(Byte.TYPE,Byte.valueOf((byte) 0));
        beanMap.put(Float.TYPE,Float.valueOf(0f));
        beanMap.put(Double.TYPE,Double.valueOf(0d));
        beanMap.put(Long.TYPE,Long.valueOf(0L));
        beanMap.put(Boolean.TYPE,Boolean.FALSE);
        beanMap.put(Character.TYPE,Character.valueOf((char) 0));
    }

    //根据class对象创建bean对象，并将结果集中的数据赋值给bean
    public static <T>T createBean(ResultSet rs,Class<T> clazz){
        T t=null;

        try {
            t=clazz.newInstance();
            ResultSetMetaData md=rs.getMetaData();
            for (int i=1;i<=md.getColumnCount();i++){
                String columnName=md.getColumnLabel(i);
                setBeanMap(t,columnName,clazz,rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 为bean对象赋值
     * @param bean bean对象
     * @param columnName  数据库中的列名
     * @param clazz bean对象对应的class对象
     * @param rs 结果集对象
     */
    public static void setBeanMap(Object bean,String columnName,Class clazz,ResultSet rs) throws Exception {
        /**
         * getFields()只能获取public的字段，包括父类的
         * getDeclaredFields()只能获取自己声明的各种字段，包括public，protected，private
         */
        Field[] fields=clazz.getDeclaredFields();
        for (Field f:fields){
            //成员变量为private时进行此操作
            f.setAccessible(true);
            String tagName=f.isAnnotationPresent(Column.class)?
                    f.getAnnotation(Column.class).values():f.getName();

            if (tagName.equalsIgnoreCase(columnName)){
                Object value= ColumnUtil.columnConvert(columnName,rs,f.getType());
                //空值处理
                if (value==null&&f.getType().isPrimitive()){
                    value=beanMap.get(f.getType());
                }
                f.set(bean,value);
                break;
            }
        }

    }
}
