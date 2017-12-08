package Main;

import controller.StuController;
import controller.StuService;
import entity.Stu;
import factory.ContainerFactory;

/**
 * Created by Sukiy on 2017/12/7.
 */
public class Test {

    public static void main(String[] args) {
        /*
            获取带注解的类的完整类名
        ContainerFactory factor=new ContainerFactory();
        Object stuService = factor.getBean("stuService");
        */
        Stu stu=new Stu();
        ContainerFactory factory=new ContainerFactory();
        StuService con=factory.getBean("stuService", StuService.class);
        System.out.println(con);
       // con.addStu(stu);
    }
}
