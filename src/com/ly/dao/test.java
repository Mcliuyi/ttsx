package com.ly.dao;

import com.ly.bean.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args){
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUname("测试");
        user.setUpwd("123456");
        user.setCreateTime();
        int ad = 1;
        user.setIsAdmin(ad);
        try {
            //System.out.println(userDao.add(user));
            user = userDao.query(1);
            System.out.println("name: " + user.getUname() + "\tpwd: " + user.getUpwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
