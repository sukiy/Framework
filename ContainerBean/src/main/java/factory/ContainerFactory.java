package factory;

import annotation.Component;
import annotation.Scope;
import utils.AnnoScanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 容器工厂（判断是单例还是原型）
 * Created by Sukiy on 2017/12/7.
 */
public class ContainerFactory {

    //单例的容器(Singleton)
    private static Map<String,Object> singleton=new HashMap<String, Object>();

    //原型的容器(prototype)
    private static Map<String,Definition> prototype=new HashMap<String, Definition>();

    //初始化单例，原型容器
    public ContainerFactory(){
        try {
            initPrototype();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        initSingleton();

    }

    //初始化原型的容器
    private void initPrototype() throws ClassNotFoundException {
        List<String> list = AnnoScanUtil.scan();
        for (String str : list) {
            Class<?> clazz = Class.forName(str);
            String id = clazz.getAnnotation(Component.class).value();
            String className = str;
            String scope = "singleton";
            if(clazz.getAnnotation(Scope.class)!=null){
                scope = clazz.getAnnotation(Scope.class).value();
            }
            Definition df = new Definition();
            df.setId(id);
            df.setClassName(className);
            df.setScope(scope);
            //将df放入容器中
            prototype.put(id,df);
        }
    }

    //初始化单例容器
    private void initSingleton() {
        for (String key : prototype.keySet()) {
            Definition def = prototype.get(key);
            if ("singleton".equals(def.getScope())) {
                try {
                    singleton.put(key, Class.forName(def.getClassName()).newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Object getBean(String name){
        return getContainerBean(name);
    }

    public <T> T getBean(String name,Class<T> clazz){
        return (T)getContainerBean(name);
    }

    private Object getContainerBean(String name){
        //获取作用域属性
        String scope=prototype.get(name).getScope();

        try {
            return ("singleton".equals(scope)) ? singleton.get(name):Class.forName(prototype.get(name).getClassName()).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
