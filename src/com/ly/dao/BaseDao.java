package com.ly.dao;

import com.ly.util.JDBCutil;

import java.sql.ResultSet;

public class BaseDao {
    protected JDBCutil jdbCutil;
    protected ResultSet rs;
    protected String sql;

    /**
     * 初始化链接数据库
     */
    public BaseDao(){
        this.jdbCutil = new JDBCutil();
    }

    /**
     * 关闭数据库链接
     */
    public void close(){
        this.jdbCutil.close();
    }
}
