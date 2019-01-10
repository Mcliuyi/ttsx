package com.ly.load.admin;

import com.ly.bean.User;
import com.ly.dao.UserDao;
import com.ly.load.BaseLoad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManageLoad extends BaseLoad {
    public UserManageLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * 查询所有用户信息
     * @return
     * @throws SQLException
     */
    public ArrayList<User> getUser() throws SQLException {
        ArrayList<User> userArrayList;

        UserDao userDao = new UserDao();

        userArrayList = userDao.query();

        userDao.close();

        return userArrayList;
    }

}
