package com.chic.android.ui.open.objectbox;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.apihint.Internal;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

@Entity
public class User{

    @Id
    long id;

    String name;
    String mobile;
    String age;
    String sex;

    @Generated(563814797)
    @Internal
    /** This constructor was generated by ObjectBox and may change any time. */
    public User(long id, String name, String mobile, String age, String sex) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.sex = sex;
    }
    @Generated(586692638)
    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
