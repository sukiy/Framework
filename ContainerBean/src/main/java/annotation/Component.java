package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识受容器管理的类
 * Created by Sukiy on 2017/12/7.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {

    //用户定义类在容器中的唯一标识（相当于xm配置文件的id属性）
    String value();
}
