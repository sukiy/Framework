package entity;

import annotation.Component;
import annotation.Scope;

/**
 * Created by Sukiy on 2017/12/7.
 */
@Component("stu")
public class Stu {

    private int sno;
    private String sname;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
