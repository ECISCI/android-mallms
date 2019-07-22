package com.mincat.sample.db.ben;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Date;

/**
 * @author Gin
 * @描述 创建 book表
 */
// 表名
@Table(name = "book")
public class Book {

    // 列名, id为自增长
    @Column(name = "ID", isId = true, autoGen = true)
    public int id;
    @Column(name = "NAME")// 列明
    public String name;
    @Column(name = "PRICE")
    public float price;
    @Column(name = "DATE")
    public Date date;

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", date='" + date + '\'' + '}';
    }

}
