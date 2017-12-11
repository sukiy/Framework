package controller;

import annotation.Component;
import annotation.Scope;
import entity.Stu;

/**
 * Created by Sukiy on 2017/12/7.
 */
@Component(value = "stuDao")
@Scope("Singleton")
public class StuDao implements IStuDao {


    public void addStu() {
        System.out.println("insert into ...");
    }
}
