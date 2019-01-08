package com.ly.load;

import com.ly.bean.Adress;
import com.ly.bean.User;
import com.ly.dao.AddressDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class AddrLoad extends BaseLoad {
    public AddrLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }


    public Adress queryAddr(){
        User user;
        Adress adress = null;
        AddressDao addressDao = new AddressDao();
        int addressId;
        HttpSession session = request.getSession();

        //获取登录用户信息
        user = (User)session.getAttribute("user");
        addressId = user.getAddressId();

        try {
            adress = addressDao.query("id", addressId);
        } catch (SQLException e) {
            System.out.println("[ERROR:] addressServlet 通过地址id查询对应地址失败");
            e.printStackTrace();
        }finally {
            addressDao.close();
        }

        return adress;
    }

}
