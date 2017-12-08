package utils;

import annotation.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 找出有注解的类的完整类名
 * Created by Sukiy on 2017/12/7.
 */
public class AnnoScanUtil {

    //获取当前项目的绝对路径
    private final static String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
    //定义一个集合，用于存放所有类的完整类名
    private static List<String> list=new ArrayList<String>();
    //定义一个集合，用于存放使用了component注解的完整类名
    private static List<String > compList=new ArrayList<String>();
    //扫描的入口方法
    public static List<String> scan() throws ClassNotFoundException {
        readFile(path);
        getComponent();
        for (String a:compList){
            System.out.println(a);
        }
        return compList;
    }

    //读取文件信息
    private static void readFile(String path){
        //构建文件对象
        File f=new File(path);
        File[] files=f.listFiles();
        if (files!=null){
            for (File file:files){
                if (file.isFile()){
                    //如果是文件就要进行文件名的解析
                    String className=resolveClass(file.getAbsolutePath());

                    list.add(className);
                }else {
                    //如果是目录，那么就执行递归，继续遍历目录
                    readFile(file.getAbsolutePath());
                }
            }
        }
    }

    //文件名解析
    private static String resolveClass(String classPath){
        String className=classPath.substring(path.length()-1,classPath.length());
        className=className.replace(".class","").replace("\\",".");
        return className;
    }

    //获取使用component注解的类
    private static void getComponent() throws ClassNotFoundException {
        for (String str:list){
            Class<?> clazz=Class.forName(str);

            //判断是否是一个接口
            if (!clazz.isInterface()&&clazz.isAnnotationPresent(Component.class)){
                compList.add(clazz.getName());
            }
        }
    }

}
