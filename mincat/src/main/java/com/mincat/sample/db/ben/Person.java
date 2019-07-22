package com.mincat.sample.db.ben;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @author Gin
 * @描述 创建 person表
 */
// 表名
@Table(name = "person")
public class Person {

    // 列名 id为自增长
    @Column(name = "ID", isId = true, autoGen = true)
    public int id;
    @Column(name = "NAME")
    public String name;
    @Column(name = "AGE")
    public int age;
    @Column(name = "SEX")
    public String sex;

    @Override
    public String toString() {
        return "person [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }
}
