package handler;

import annotation.Inject;
import factory.BeanFactory;

import java.lang.reflect.Field;

/**
 * 字段注入器
 */
public class FieldInjectHandler{

    public void execute(Object target, Class<?> targetClass, BeanFactory factory) {
        // 遍历当前类中所有的属性(包括私有的),便于解析是否带有注解
        for (Field field : targetClass.getDeclaredFields()) {
            // 判断属性中是否定义了Inject注解类型
            if (field.isAnnotationPresent(Inject.class)) {
                // 获取该属性上的Resource注解
                Inject annotation = field.getAnnotation(Inject.class);
                // 根据注解name属性的值,从容器获取bean实例(递归调用)
                Object property = factory.getBean(annotation.name());
                // 如果属性是私有的,先打开访问开关
                field.setAccessible(true);
                // 给当前的field属性赋值(注入)
                injectField(field, target, property);
            }
        }
    }

    /**
     * 给当前属性注入
     * @param field
     * @param target
     * @param property
     */
    private void injectField(Field field, Object target, Object property) {
        try {
            field.setAccessible(true);
            field.set(target, property);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Inject property fail.", e);
        }
    }
}
