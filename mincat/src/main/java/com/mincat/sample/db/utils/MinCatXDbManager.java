package com.mincat.sample.db.utils;

import com.mincat.sample.utils.LogUtils;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * @author Gin
 * @描述 数据库管理类
 * @解释1 项目不是特别大, 创建一个数据库对应多张表即可, 这样便于维护
 * @解释2 如果不是保存集合类数据, 既网络获取的Json数据没必要使用数据库保存, 可以使用 sharedPreferences
 * @解释3 这里并没有做数据库数据更新操作, 因为我们要频繁访问网络,
 * 数据要根据网络请求下的数据去更换, 简单的方法就是在获取数据之前先将数据库清空然后再将新数据插入到数据库中
 */

public class MinCatXDbManager {

    public static final String TAG = MinCatXDbManager.class.getName();

    /**
     * 数据库名称
     */
    private static String dbName = "my.db";

    /**
     * 数据库版本号
     */
    private static int dbVersion = 1;

    /**
     * 是否支持数据库事务操作
     */
    private static boolean isTransaction = true;

    private static DbManager db;


    /**
     * @描述 这个方法 是单利的可以放心使用
     */
    public static DbManager createDb() {

        //本地数据的初始化
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()

                //设置数据库名
                .setDbName(dbName)

                //设置数据库版本,每次启动应用时将会检查该版本号,
                .setDbVersion(dbVersion)

                // 发现数据库版本低于这里设置的值将进行数据库升级并触发DbUpgradeListener

                //设置是否开启事务,默认为false关闭事务
                .setAllowTransaction(isTransaction)

                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager dbManager, TableEntity<?> tableEntity) {

                    }
                })
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {

                        db.getDatabase().enableWriteAheadLogging();
                    }
                })

                // 设置数据库创建时的Listener
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });

        // 设置数据库升级时的Listener,这里可以执行相关数据库表的相关修改,比如alter语句增加字段等
        // .setDbDir(null);//设置数据库.db文件存放的目录,默认为包名下databases目录下

        return x.getDb(daoConfig);
    }

    /**
     * 向数据库中添加数据
     *
     * @param list
     */
    public static void insertDb(List<?> list) {


        try {
            db = createDb();
            db.saveBindingId(list);

            LogUtils.i(TAG, "向数据库中添加数据成功：" + list);
        } catch (DbException e) {

            LogUtils.i(TAG, "向数据库中添加数据错误：" + e);
        }
    }


    /**
     * @param cls  实体类 也是表名
     * @param list 实体类对应的集合
     * @return
     * @描述 根据表查询数据
     */
    public static List<?> selectFromWhereTable(Class<?> cls, List<?> list) {

        try {
            db = createDb();
            list = db.findAll(cls);


            if (list.size() == 0) {
                LogUtils.i(TAG, "表中无数据");
            } else {

                LogUtils.i(TAG, "查询" + cls + "表成功,返回结果:" + list);
            }

            return list;

        } catch (DbException e) {
            LogUtils.i(TAG, "查询" + cls + "表失败,错误信息:" + e);
            return null;
        }

    }

    /**
     * 删除全部数据
     *
     * @param cls 实体类既是某张表
     */
    public static void deleteWhereTable(Class<?> cls) {

        try {
            db = createDb();
            db.delete(cls);

            LogUtils.i(TAG, "删除" + cls + "表成功");
        } catch (DbException e) {

            LogUtils.i(TAG, "删除" + cls + "表失败:错误信息:" + e);
        }

    }
}
