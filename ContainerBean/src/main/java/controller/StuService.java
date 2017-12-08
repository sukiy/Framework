package controller;

import annotation.Component;
import annotation.Inject;
import entity.Stu;

/**
 * Created by Sukiy on 2017/12/7.
 */
@Component("stuService")
public class StuService implements IStuService {

    private IStuDao stuDao;

    //set方法注入
    @Inject(name = "userDao")
    public void setStuDao(IStuDao stuDao) {
        this.stuDao = stuDao;
    }

    public void addStu(Stu stu) {
        stuDao.addStu(stu);
    }
}
