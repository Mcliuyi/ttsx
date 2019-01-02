package com.ly.dao;

import com.ly.bean.User;
import com.ly.util.Config;
import com.ly.util.JDBCutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDao {

    private JDBCutil jdbCutil;
    private ResultSet rs;
    private String sql;

    /**
     * 初始化链接数据库
     */
    public UserDao(){
        this.jdbCutil = new JDBCutil();
    }

    /**
     * 关闭数据库链接
     */
    public void close(){
        this.jdbCutil.close();
    }

    /**
     * 根据id查询用户
     * @param id
     * @return ResultSet
     * @throws SQLException
     */
    public User query(int id) throws SQLException {
        User user = new User();
        this.sql = "select id, uname, upwd, email, phone,address_id from userInfo where id=?";
        this.rs = this.jdbCutil.query(this.sql, id);

        while (rs.next()){
            user.setId(rs.getInt("id"));
            user.setUname(rs.getString("uname"));
            user.setUpwd(rs.getString("upwd"));
            user.setEamil(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setAddressId(rs.getInt("address_id"));
        }
        return user;
    }


    /**
     * 根据name查询用户
     * @param  name
     * @return ResultSet
     * @throws SQLException
     */
    public User query(String name) throws SQLException {
        User user = new User();
        this.sql = "select id, uname, upwd, email, phone,address_id from userInfo where uname=?";
        this.rs = this.jdbCutil.query(this.sql, name);

        while (rs.next()){
            user.setId(rs.getInt("id"));
            user.setUname(rs.getString("uname"));
            user.setUpwd(rs.getString("upwd"));
            user.setEamil(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setAddressId(rs.getInt("address_id"));
        }
        return user;
    }

    /**
     * 添加用户
     * @param user
     * @return
     * @throws SQLException
     */
    public Boolean add(User user) throws SQLException {
        Boolean rlt ;
        this.sql = "insert into userInfo(uname, upwd, email, status, create_time, isadmin, isdel) values(?,?,?,?,?,?,?)";
        rlt = this.jdbCutil.update(this.sql, user.getUname(), user.getUpwd(), user.getEamil(), user.getStatus(), user.getCreateTime(), user.getIsAdmin(), user.getIsdel());
        return rlt;
    }


    /**
     * 判断用户是否存在
     * @param user
     * @return
     */
    public Boolean exits(User user){
        Boolean rlt;
        if(user.getUname() == null && user.getUpwd() == null && user.getId() == 0){
            rlt = false;
        }else {
            rlt = true;
        }
        return rlt;
    }

    /**
     * 设置用户最后登录时间
     * @param id 用户id
     * @throws SQLException
     */
    public void setEndTime(int id) throws SQLException {

         this.sql = "update userInfo set end_time = ? where id = ?";
         this.jdbCutil.update(this.sql, Config.getTime(), id);
    }


}
