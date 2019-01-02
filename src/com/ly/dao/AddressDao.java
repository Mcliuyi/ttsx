package com.ly.dao;

import com.ly.bean.Adress;
import com.ly.util.JDBCutil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao extends BaseDao{

    /**
     * 根据地址id查询地址信息
     * @param field 字段名
     * @param id 地址id 或 用户id
     * @return 地址信息对象
     */
    public Adress query(String field, int id) throws SQLException {
        Adress adress = new Adress();
        this.sql = "select id, name, addr, code, phone from address where " +  field  + " = ? ";
        this.rs = this.jdbCutil.query(sql, id);
        while (this.rs.next()){
            adress.setId(this.rs.getInt(id));
            adress.setName(this.rs.getString("name"));
            adress.setAddr(this.rs.getString("addr"));
            adress.setCode(this.rs.getString("code"));
            adress.setPhone(this.rs.getString("phone"));
        }
        return adress;
    }


    /**
     * 根据地址id查询地址信息
     * @param field 字段名
     * @param id 地址id 或 用户id
     * @return 地址信息对象
     */
    public Boolean update(Adress adress, String field, int id) throws SQLException {
        Boolean rlt;
        this.sql = "update address set name=?, addr=?, code=?, phone=? where " +  field  + " = ? ";
        rlt = this.jdbCutil.update(sql, adress.getName(), adress.getAddr(), adress.getCode(), adress.getPhone(), id);
        return rlt;
    }




}
