package controller;

import annotation.Component;
import annotation.Inject;
import entity.Stu;
import factory.ContainerFactory;

/**
 * Created by Sukiy on 2017/12/7.
 */
@Component("stuController")
public class StuController {

    private IStuService stuService;

    //set方法注入
    @Inject(name = "stuService")
    public void setStuService(IStuService stuService) {
        this.stuService = stuService;
    }

    public void addStu(Stu stu){
        //System.out.println(stuService);
        stuService.addStu(stu);
    }
}
