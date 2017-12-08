package controller;

import annotation.Component;
import entity.Stu;

/**
 * Created by Sukiy on 2017/12/7.
 */
@Component(value = "stuDao")
public class StuDao implements IStuDao {


    public void addStu(Stu stu) {
        System.out.println("insert into ...");
    }
}
